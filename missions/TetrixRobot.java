package missions;
import ch.aplu.nxt.SensorPort;
import lejos.nxt.I2CPort;
import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.nxt.addon.tetrix.TetrixEncoderMotor;
import lejos.nxt.addon.tetrix.TetrixMotorController;
import lejos.nxt.comm.RConsole;
import lejos.util.Delay;
import ch.aplu.nxt.NxtRobot;
import ch.aplu.nxt.SuperProSensor;

public class TetrixRobot {		
	NxtRobot robot = new NxtRobot();
	SuperProSensor superPro1 = new SuperProSensor(SensorPort.S1);
	TetrixControllerFactory factory1 = new TetrixControllerFactory((I2CPort) SensorPort.S2); // front left controller
	TetrixControllerFactory factory2 = new TetrixControllerFactory((I2CPort) SensorPort.S3); // back right controller 
	TetrixMotorController controller1 = factory1.newMotorController(); // front left controller 
	TetrixMotorController controller2 = factory2.newMotorController(); // back right controller
	TetrixEncoderMotor motorNorth = controller1.getEncoderMotor(TetrixMotorController.MOTOR_1);
	TetrixEncoderMotor motorSouth = controller2.getEncoderMotor(TetrixMotorController.MOTOR_1);
	TetrixEncoderMotor motorWest = controller1.getEncoderMotor(TetrixMotorController.MOTOR_2); //forward left motor
	TetrixEncoderMotor motorEast = controller2.getEncoderMotor(TetrixMotorController.MOTOR_2); // forward right motor
	int[] values = new int[4];
	
	public TetrixRobot(){
		robot.addPart(superPro1);
	}
	private double largeCM(int input) {
		return -0.000001d*Math.pow(input, 3)  + 0.0018d*Math.pow(input,2) - 1.0408d*input + 239.13d;
	}
	public double getDistanceEast() {
		superPro1.readAnalog(values);
		return largeCM(values[2]);		
	}
	public double getDistanceWest() {
		return 0;
	}
	public void setNorthMotor(int power) {
		motorNorth.setPower(power);
	}
	public void setSouthMotor(int power) {
		motorSouth.setPower(power);
	}
	public void setWestMotor(int power) {
		motorWest.setPower(power);
	}
	public void setEastMotor(int power) {
		motorEast.setPower(power);
	}
	public void stop() {
		motorNorth.stop();
		motorWest.stop();
		motorSouth.stop();
		motorEast.stop();
	}
	public void moveNorth () {
		motorWest.forward();
		motorEast.forward();
	}
	public void moveSouth () {
		motorWest.backward();
		motorEast.backward();
	}
	

}
