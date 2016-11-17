
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3TouchSensor;

public class PennVelger {
	private final int antPenner = 3;
	private final int makshastighet = 150;
	private boolean pennNede = false;
	private boolean skiftePenn = false;
	private int slack = 45;
	private int pennNr = 1;
	
	private EV3LargeRegulatedMotor motorZ;
	
	/*
	public PennVelger(EV3LargeRegulatedMotor motorZ, EV3TouchSensor endestoppZ, int antPenner){
		this.motorZ = motorZ;
		this.endestoppZ = endestoppZ;
		this.antPenner = antPenner;
		
		motorZ.setSpeed(makshastighet);
	}
	*/
	public PennVelger(EV3LargeRegulatedMotor motorZ){
		this.motorZ = motorZ;
	}
	
	//TODO: Lag metoden!
	public void velgPenn(int nr){
		if(nr<1 || nr> antPenner){
			throw new IllegalArgumentException("Ugyldig penn nr!");
		}
		if(!pennNede && !skiftePenn && nr != pennNr){
			motorZ.rotate(slack);
			skiftePenn = true;
		}else if(!pennNede && skiftePenn && nr != pennNr){
			if(nr == pennNr+1 || nr == pennNr -2){
				motorZ.rotate(200);
			}else if(nr == pennNr +2 || nr == pennNr -1){
				motorZ.rotate(400);
			}
			pennNr = nr;
		}
	}
	
	//TODO: Test metoden
	public void ned(){
		if(!pennNede && skiftePenn){
			motorZ.rotate(-slack);
			skiftePenn = false;
		}
		if(!pennNede){
			motorZ.rotate(-375);
			pennNede = true;
		}
		
	}
	//TODO: Test metoden
	public void opp(){
		if(pennNede){
			motorZ.rotate(-375);
			pennNede = false;
		}
	}
}
