package org.usfirst.frc.team1939.util;

import java.util.function.DoubleSupplier;

public class PIDTimer {

	private DoubleSupplier getError;
	private double margin;
	private double time;
	
	private double timeSettled = 0;
	
	public PIDTimer(DoubleSupplier getPosition, double setpoint, double margin, double time){
		this(()->Math.abs(getPosition.getAsDouble()-setpoint),margin, time);
	}
	
	public PIDTimer(DoubleSupplier getError, double margin, double time){
		this.getError = getError;
		this.margin = margin;
		this.time = time;
	}
	
	public void update(){
		if(getError.getAsDouble()>margin){
			timeSettled = 0;
		}else if(timeSettled==0){
			timeSettled = System.currentTimeMillis();
		}
	}
	
	public boolean isDone(){
		return timeSettled != 0 && System.currentTimeMillis()-timeSettled > time;
	}
	
}
