package missions;

import lejos.nxt.comm.RConsole;
import lejos.util.Delay;

public class SensorsTest {
	public static void main(String[] args) {
		TetrixRobot texBot = new TetrixRobot();
		RConsole.open();
		while (true) {
			RConsole.println("N:\t"+(int)texBot.getDistanceNorth()+"\tW:\t"+(int)texBot.getDistanceWest()+"\tE:\t"+(int)texBot.getDistanceEast()+"\tS:\t"+(int)texBot.getDistanceSouth());
			Delay.msDelay(100);
		}
	}
}
