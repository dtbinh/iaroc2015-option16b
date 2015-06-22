package missions;
import lejos.util.Delay;
import lejos.nxt.comm.RConsole;
public class DragRace {

	public static void main(String[] args) {	
		TetrixRobot texBot = new TetrixRobot();
		texBot.motorWest.resetTachoCount();
		texBot.motorEast.resetTachoCount();
		texBot.motorNorth.resetTachoCount();
		texBot.motorSouth.resetTachoCount();
		texBot.moveNorth(); 
		int eastTacho;
		int westTacho;
		RConsole.open();
		while (true) {
			eastTacho = texBot.motorEast.getTachoCount();
			westTacho = texBot.motorWest.getTachoCount();
			RConsole.println("West: "+westTacho+"\tEast: "+eastTacho);
			Delay.msDelay(50);
			if(false) {
				break;
			}
		}
		
		Delay.msDelay(1000);
		
		
		Delay.msDelay(2000);
		
	}
	

}
