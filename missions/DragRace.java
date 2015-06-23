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
		int eastTacho;
		int westTacho;
		int northTacho;
		int southTacho;
		int offset;
		//RConsole.open();
		texBot.setAllPower(50);
		texBot.moveEast();
		while (true) {
			northTacho = texBot.motorNorth.getTachoCount();
			southTacho = -1*texBot.motorSouth.getTachoCount();
			offset = northTacho - southTacho;
			//RConsole.println(/*"West: "+westTacho+"\tEast: "+eastTacho+*/"\tNorth: "+northTacho+"\tSouth: "+southTacho+"\tOffset: "+offset);
			Delay.msDelay(50);
			
			if(texBot.getDistanceEast() > 600) {
				break;
			}
		 }
		texBot.moveWest();
		Delay.msDelay(1000);
		texBot.stop();
		
		Delay.msDelay(2000);
		
	}
	

}
