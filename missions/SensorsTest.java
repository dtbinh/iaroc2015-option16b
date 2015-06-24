package missions;

import lejos.util.Delay;

public class SensorsTest {
	public static void main(String[] args) {
		TetrixRobot texBot = new TetrixRobot();
		//texBot.turnLeft90();
		texBot.spinLeft();
		Delay.msDelay(250);
	}
}
