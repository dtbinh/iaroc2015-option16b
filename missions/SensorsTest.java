package missions;

import lejos.nxt.comm.RConsole;
import lejos.util.Delay;

public class SensorsTest {
	public static void main(String[] args) {
		TetrixRobot texBot = new TetrixRobot();
		RConsole.open();
		while (true) {
			RConsole.println("North left: " + texBot.binaryNorthLeft() + "\tNorth right:" + texBot.binaryNorthRight() + "\tWest left:" + texBot.binaryWestLeft() + "\tWest right:" + texBot.binaryWestRight() + "\tEast left:" + texBot.binaryEastLeft() + "\tEast right:" + texBot.binaryEastRight() + "\tSouth left:" + texBot.binarySouthLeft() + "\tSouth right" + texBot.binarySouthRight());
			Delay.msDelay(100);
		}
	}
}
