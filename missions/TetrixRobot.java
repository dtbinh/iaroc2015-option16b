package missions;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.nxt.addon.tetrix.TetrixEncoderMotor;
import lejos.nxt.addon.tetrix.TetrixMotorController;
import ch.aplu.nxt.NxtRobot;
import ch.aplu.nxt.SuperProSensor;

public class TetrixRobot {		
	NxtRobot robot = new NxtRobot();
	SuperProSensor superPro1 = new SuperProSensor(ch.aplu.nxt.SensorPort.S1);
	TetrixControllerFactory factory1 = new TetrixControllerFactory(SensorPort.S2); // front left controller
	TetrixControllerFactory factory2 = new TetrixControllerFactory(SensorPort.S3); // back right controller 
	TetrixMotorController controller1 = factory1.newMotorController(); // front left controller 
	TetrixMotorController controller2 = factory2.newMotorController(); // back right controller
	TetrixEncoderMotor motorNorth = controller1.getEncoderMotor(TetrixMotorController.MOTOR_1);
	TetrixEncoderMotor motorSouth = controller2.getEncoderMotor(TetrixMotorController.MOTOR_1);
	TetrixEncoderMotor motorWest = controller1.getEncoderMotor(TetrixMotorController.MOTOR_2); //forward left motor
	TetrixEncoderMotor motorEast = controller2.getEncoderMotor(TetrixMotorController.MOTOR_2); // forward right motor
	int[] values = new int[4];
	int[] binaries = new int[8];
	
	public TetrixRobot(){
		robot.addPart(superPro1);
	}
	public void updateSensors() {
		superPro1.readAnalog(values);
		superPro1.readDigital(binaries);
	}
	private double largeCM(int input) {
		return -0.000001d*Math.pow(input, 3)  + 0.0018d*Math.pow(input,2) - 1.0408d*input + 239.13d;
	}
	public double getDistanceNorth() {
		if(binaryNorth()){
			return 10;
		} else {
			superPro1.readAnalog(values);
			return largeCM(values[3]);
		}
	}
	public double getDistanceEast() {
		if(binaryWest()) {
			return 10;
		} else {
			superPro1.readAnalog(values);
			return largeCM(values[2]);
		}
	}
	public double getDistanceWest() {
		if(binaryWest()) {
			return 10;
		} else {
			superPro1.readAnalog(values);
			return largeCM(values[1]);
		}
	}
	public double getDistanceSouth() {
		if(binaryWest()) {
			return 10;
		} else {
			superPro1.readAnalog(values);
			return 51;//TODO
		}
	}
	public boolean binaryNorth() {
		superPro1.readDigital(binaries);
		return binaries[2]==0;
	}
	public boolean binaryNorthWest() {
		superPro1.readDigital(binaries);
		return binaries[4]==0;
	}
	public boolean binaryWest() {
		superPro1.readDigital(binaries);
		return binaries[0]==0;
	}
	public boolean binaryEast() {
		superPro1.readDigital(binaries);
		return binaries[3]==0;
	}
	public boolean binarySouth() {
		superPro1.readDigital(binaries);
		return binaries[1]==0;
	}
	public void setAllPower(int power) {
		setNorthPower(power);
		setSouthPower(power);
		setWestPower(power);
		setEastPower(power);
	}
	public void setNorthPower(int power) {
		motorNorth.setPower(power);
	}
	public void setSouthPower(int power) {
		motorSouth.setPower(power);
	}
	public void setWestPower(int power) {
		motorWest.setPower(power);
	}
	public void setEastPower(int power) {
		motorEast.setPower(power);
	}
	public void stop() {
		motorNorth.stop();
		motorWest.stop();
		motorSouth.stop();
		motorEast.stop();
	}
	public void moveNorth () {
		motorWest.backward();
		motorEast.forward();
	}
	public void moveSouth () {
		motorWest.forward();
		motorEast.backward();
	}
	public void moveEast() {
		motorNorth.backward();
		motorSouth.forward();
	}
	public void moveWest() {
		motorNorth.forward();
		motorSouth.backward();
	}
	public void spinLeft(){
		motorEast.forward();
		motorWest.forward();
		motorSouth.forward();
		motorNorth.forward();
	}
	public void spinRight(){
		motorEast.backward();
		motorWest.backward();
		motorSouth.backward();
		motorNorth.backward();
	}
	public void turnLeft90() {
		motorNorth.rotate(520, true);
		motorEast.rotate(520, true);
		motorWest.rotate(52, true);
		motorSouth.rotate(520, false);
	}
	

}
