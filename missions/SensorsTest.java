package missions;

import lejos.nxt.comm.RConsole;
import lejos.util.Delay;

public class SensorsTest {
	public static void main(String[] args) {
		TetrixRobot texBot = new TetrixRobot();
		RConsole.open();
		while (true) {
			texBot.updateSensors();
			for(int i:texBot.binaries) {
				RConsole.print(String.valueOf(i));
			}
			RConsole.println("");
			Delay.msDelay(50);
		}
	}
}
