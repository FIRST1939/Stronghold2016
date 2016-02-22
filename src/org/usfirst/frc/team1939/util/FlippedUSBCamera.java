package org.usfirst.frc.team1939.util;

import static edu.wpi.first.wpilibj.Timer.delay;

import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.FlipAxis;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.RawData;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.vision.USBCamera;

public class FlippedUSBCamera extends USBCamera {
	public static String kDefaultCameraName = "cam0";

	private static String ATTR_VIDEO_MODE = "AcquisitionAttributes::VideoMode";
	private static String ATTR_WB_MODE = "CameraAttributes::WhiteBalance::Mode";
	private static String ATTR_WB_VALUE = "CameraAttributes::WhiteBalance::Value";
	private static String ATTR_EX_MODE = "CameraAttributes::Exposure::Mode";
	private static String ATTR_EX_VALUE = "CameraAttributes::Exposure::Value";
	private static String ATTR_BR_MODE = "CameraAttributes::Brightness::Mode";
	private static String ATTR_BR_VALUE = "CameraAttributes::Brightness::Value";

	public class WhiteBalance {
		public static final int kFixedIndoor = 3000;
		public static final int kFixedOutdoor1 = 4000;
		public static final int kFixedOutdoor2 = 5000;
		public static final int kFixedFluorescent1 = 5100;
		public static final int kFixedFlourescent2 = 5200;
	}

	private Pattern m_reMode = Pattern
			.compile("(?<width>[0-9]+)\\s*x\\s*(?<height>[0-9]+)\\s+(?<format>.*?)\\s+(?<fps>[0-9.]+)\\s*fps");

	private String m_name = kDefaultCameraName;
	private int m_id = -1;
	private boolean m_active = false;
	private boolean m_useJpeg = true;
	private int m_width = 320;
	private int m_height = 240;
	private int m_fps = 30;
	private String m_whiteBalance = "auto";
	private int m_whiteBalanceValue = -1;
	private String m_exposure = "auto";
	private int m_exposureValue = -1;
	private int m_brightness = 50;
	private boolean m_needSettingsUpdate = true;

	public FlippedUSBCamera() {
		openCamera();
	}

	public FlippedUSBCamera(String name) {
		this.m_name = name;
		openCamera();
	}

	@Override
	public synchronized void openCamera() {
		if (this.m_id != -1)
			return; // Camera is already open
		for (int i = 0; i < 3; i++) {
			try {
				this.m_id = NIVision.IMAQdxOpenCamera(this.m_name,
						NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			} catch (VisionException e) {
				if (i == 2)
					throw e;
				delay(2.0);
				continue;
			}
			break;
		}
	}

	@Override
	public synchronized void closeCamera() {
		if (this.m_id == -1)
			return;
		NIVision.IMAQdxCloseCamera(this.m_id);
		this.m_id = -1;
	}

	@Override
	public synchronized void startCapture() {
		if (this.m_id == -1 || this.m_active)
			return;
		NIVision.IMAQdxConfigureGrab(this.m_id);
		NIVision.IMAQdxStartAcquisition(this.m_id);
		this.m_active = true;
	}

	@Override
	public synchronized void stopCapture() {
		if (this.m_id == -1 || !this.m_active)
			return;
		NIVision.IMAQdxStopAcquisition(this.m_id);
		NIVision.IMAQdxUnconfigureAcquisition(this.m_id);
		this.m_active = false;
	}

	@Override
	public synchronized void updateSettings() {
		boolean wasActive = this.m_active;
		// Stop acquistion, close and reopen camera
		if (wasActive)
			stopCapture();
		if (this.m_id != -1)
			closeCamera();
		openCamera();

		// Video Mode
		NIVision.dxEnumerateVideoModesResult enumerated = NIVision.IMAQdxEnumerateVideoModes(this.m_id);
		NIVision.IMAQdxEnumItem foundMode = null;
		int foundFps = 1000;
		for (NIVision.IMAQdxEnumItem mode : enumerated.videoModeArray) {
			Matcher m = this.m_reMode.matcher(mode.Name);
			if (!m.matches())
				continue;
			if (Integer.parseInt(m.group("width")) != this.m_width)
				continue;
			if (Integer.parseInt(m.group("height")) != this.m_height)
				continue;
			double fps = Double.parseDouble(m.group("fps"));
			if (fps < this.m_fps)
				continue;
			if (fps > foundFps)
				continue;
			String format = m.group("format");
			boolean isJpeg = format.equals("jpeg") || format.equals("JPEG");
			if (this.m_useJpeg && !isJpeg || !this.m_useJpeg && isJpeg)
				continue;
			foundMode = mode;
			foundFps = (int) fps;
		}
		if (foundMode != null) {
			System.out.println("found mode " + foundMode.Value + ": " + foundMode.Name);
			if (foundMode.Value != enumerated.currentMode)
				NIVision.IMAQdxSetAttributeU32(this.m_id, ATTR_VIDEO_MODE, foundMode.Value);
		}

		// White Balance
		if (this.m_whiteBalance == "auto")
			NIVision.IMAQdxSetAttributeString(this.m_id, ATTR_WB_MODE, "Auto");
		else {
			NIVision.IMAQdxSetAttributeString(this.m_id, ATTR_WB_MODE, "Manual");
			if (this.m_whiteBalanceValue != -1)
				NIVision.IMAQdxSetAttributeI64(this.m_id, ATTR_WB_VALUE, this.m_whiteBalanceValue);
		}

		// Exposure
		if (this.m_exposure == "auto")
			NIVision.IMAQdxSetAttributeString(this.m_id, ATTR_EX_MODE, "AutoAperaturePriority");
		else {
			NIVision.IMAQdxSetAttributeString(this.m_id, ATTR_EX_MODE, "Manual");
			if (this.m_exposureValue != -1) {
				long minv = NIVision.IMAQdxGetAttributeMinimumI64(this.m_id, ATTR_EX_VALUE);
				long maxv = NIVision.IMAQdxGetAttributeMaximumI64(this.m_id, ATTR_EX_VALUE);
				long val = minv + (long) ((maxv - minv) * (this.m_exposureValue / 100.0));
				NIVision.IMAQdxSetAttributeI64(this.m_id, ATTR_EX_VALUE, val);
			}
		}

		// Brightness
		NIVision.IMAQdxSetAttributeString(this.m_id, ATTR_BR_MODE, "Manual");
		long minv = NIVision.IMAQdxGetAttributeMinimumI64(this.m_id, ATTR_BR_VALUE);
		long maxv = NIVision.IMAQdxGetAttributeMaximumI64(this.m_id, ATTR_BR_VALUE);
		long val = minv + (long) ((maxv - minv) * (this.m_brightness / 100.0));
		NIVision.IMAQdxSetAttributeI64(this.m_id, ATTR_BR_VALUE, val);

		// Restart acquisition
		if (wasActive)
			startCapture();
	}

	@Override
	public synchronized void setFPS(int fps) {
		if (fps != this.m_fps) {
			this.m_needSettingsUpdate = true;
			this.m_fps = fps;
		}
	}

	@Override
	public synchronized void setSize(int width, int height) {
		if (width != this.m_width || height != this.m_height) {
			this.m_needSettingsUpdate = true;
			this.m_width = width;
			this.m_height = height;
		}
	}

	/** Set the brightness, as a percentage (0-100). */
	@Override
	public synchronized void setBrightness(int brightness) {
		if (brightness > 100)
			this.m_brightness = 100;
		else if (brightness < 0)
			this.m_brightness = 0;
		else
			this.m_brightness = brightness;
		this.m_needSettingsUpdate = true;
	}

	/** Get the brightness, as a percentage (0-100). */
	@Override
	public synchronized int getBrightness() {
		return this.m_brightness;
	}

	/** Set the white balance to auto. */
	@Override
	public synchronized void setWhiteBalanceAuto() {
		this.m_whiteBalance = "auto";
		this.m_whiteBalanceValue = -1;
		this.m_needSettingsUpdate = true;
	}

	/** Set the white balance to hold current. */
	@Override
	public synchronized void setWhiteBalanceHoldCurrent() {
		this.m_whiteBalance = "manual";
		this.m_whiteBalanceValue = -1;
		this.m_needSettingsUpdate = true;
	}

	/** Set the white balance to manual, with specified color temperature. */
	@Override
	public synchronized void setWhiteBalanceManual(int value) {
		this.m_whiteBalance = "manual";
		this.m_whiteBalanceValue = value;
		this.m_needSettingsUpdate = true;
	}

	/** Set the exposure to auto aperature. */
	@Override
	public synchronized void setExposureAuto() {
		this.m_exposure = "auto";
		this.m_exposureValue = -1;
		this.m_needSettingsUpdate = true;
	}

	/** Set the exposure to hold current. */
	@Override
	public synchronized void setExposureHoldCurrent() {
		this.m_exposure = "manual";
		this.m_exposureValue = -1;
		this.m_needSettingsUpdate = true;
	}

	/** Set the exposure to manual, as a percentage (0-100). */
	@Override
	public synchronized void setExposureManual(int value) {
		this.m_exposure = "manual";
		if (value > 100)
			this.m_exposureValue = 100;
		else if (value < 0)
			this.m_exposureValue = 0;
		else
			this.m_exposureValue = value;
		this.m_needSettingsUpdate = true;
	}

	@Override
	public synchronized void getImage(Image image) {
		if (this.m_needSettingsUpdate || this.m_useJpeg) {
			this.m_needSettingsUpdate = false;
			this.m_useJpeg = false;
			updateSettings();
		}

		NIVision.IMAQdxGrab(this.m_id, image, 1);
		NIVision.imaqFlip(image, image, FlipAxis.HORIZONTAL_AXIS);
	}

	@Override
	public synchronized void getImageData(ByteBuffer data) {
		if (this.m_needSettingsUpdate || !this.m_useJpeg) {
			this.m_needSettingsUpdate = false;
			this.m_useJpeg = true;
			updateSettings();
		}

		Image image = NIVision.imaqCreateImage(ImageType.IMAGE_COMPLEX, 0);
		getImage(image);
		RawData rawData = NIVision.imaqFlatten(image, NIVision.FlattenType.FLATTEN_IMAGE,
				NIVision.CompressionType.COMPRESSION_JPEG, 50);
		data = rawData.getBuffer();
		data.limit(data.capacity() - 1);
		data.limit(getJpegSize(data));
	}

	private static int getJpegSize(ByteBuffer data) {
		if (data.get(0) != (byte) 0xff || data.get(1) != (byte) 0xd8)
			throw new VisionException("invalid image");
		int pos = 2;
		while (true) {
			try {
				byte b = data.get(pos);
				if (b != (byte) 0xff)
					throw new VisionException("invalid image at pos " + pos + " (" + data.get(pos) + ")");
				b = data.get(pos + 1);
				if (b == (byte) 0x01 || b >= (byte) 0xd0 && b <= (byte) 0xd7) // various
					pos += 2;
				else if (b == (byte) 0xd9) // EOI
					return pos + 2;
				else if (b == (byte) 0xd8) // SOI
					throw new VisionException("invalid image");
				else if (b == (byte) 0xda) { // SOS
					int len = (data.get(pos + 2) & 0xff) << 8 | data.get(pos + 3) & 0xff;
					pos += len + 2;
					// Find next marker. Skip over escaped and RST markers.
					while (data.get(pos) != (byte) 0xff || data.get(pos + 1) == (byte) 0x00
							|| data.get(pos + 1) >= (byte) 0xd0 && data.get(pos + 1) <= (byte) 0xd7)
						pos += 1;
				} else { // various
					int len = (data.get(pos + 2) & 0xff) << 8 | data.get(pos + 3) & 0xff;
					pos += len + 2;
				}
			} catch (IndexOutOfBoundsException ex) {
				throw new VisionException("invalid image: could not find jpeg end " + ex.getMessage());
			}
		}
	}
}
