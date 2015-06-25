package missions;
import lejos.util.Delay;
public class DragRace {
	static TetrixRobot texBot = new TetrixRobot();
	public static void goForward() {
		texBot.setAllPower(100);
		texBot.moveNorth(); // will not stop north motors unless code explicitly tells them to
		while (texBot.getDistanceNorthLeft() > 50) { //while distance is greater than 50cm
			if (texBot.binaryWestRight() || texBot.binaryWestLeft()) { 
				texBot.moveEast();
				texBot.setEastPower(35);
				Delay.msDelay(400);
			} else if (texBot.binaryEastLeft() || texBot.binaryEastRight()) {
				texBot.moveWest();
				texBot.setWestPower(35);
				Delay.msDelay(400);
			} else { // if both binary sensors don't sense anything north and south motors do nothing
				texBot.motorNorth.stop();
				texBot.motorSouth.stop();
			}
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
