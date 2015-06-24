package missions;
import lejos.util.Delay;
public class DragRaceAlt {
	static TetrixRobot texBot = new TetrixRobot();
	public static void goForward() {
		texBot.setAllPower(100);
		texBot.moveSouth();
		texBot.moveWest();
		while (texBot.getDistanceWest() > 11) {
			if (texBot.binaryNorthWest()) {
				texBot.moveEast();
				//texBot.setEastPower(35);
				Delay.msDelay(400);
			} else if (texBot.binarySouth()) {
				texBot.moveNorth();
				//texBot.setNorthPower(35);
				Delay.msDelay(400);
			}
			texBot.moveSouth();
			texBot.moveWest();
			texBot.setAllPower(100);
			Delay.msDelay(5);
		}
		texBot.stop();
	}
	public static void main(String[] args) {	
		goForward();
		texBot.setAllPower(80);
		texBot.moveSouth();
		Delay.msDelay(500);
		texBot.spinLeft();
		Delay.msDelay(1200);
		texBot.stop();
		goForward();
	}
	

}
