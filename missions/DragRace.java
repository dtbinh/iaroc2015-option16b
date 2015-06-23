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
		//int northTacho;
		//int southTacho;
		int offset;
		int poweroffset = 0;
		RConsole.open();
		texBot.setAllPower(50);
		texBot.moveNorth();
		while (true) {
			//northTacho = texBot.motorNorth.getTachoCount();
			//southTacho = texBot.motorSouth.getTachoCount();
			eastTacho = texBot.motorEast.getTachoCount();
			westTacho = texBot.motorWest.getTachoCount();
			offset = eastTacho+westTacho; //west motor is moving backwards so the encoder will give negative values
			if(offset>1) {
				texBot.setWestPower(55);
				texBot.setEastPower(45);
			} else if (offset<-1) {
				texBot.setWestPower(45);
				texBot.setEastPower(55);
			}
			if (texBot.binaryWest()) {
				texBot.motorNorth.backward();
				texBot.motorSouth.forward();
			} else if (texBot.binaryEast()) {
				texBot.motorNorth.forward();
				texBot.motorSouth.backward();
			} else {
				texBot.motorNorth.stop();
				texBot.motorSouth.stop();
			}

			RConsole.println("West: "+westTacho+"\tEast: "+eastTacho+"\tOffset: "+offset+"\tPoweroffset: \t" + poweroffset);
			Delay.msDelay(10);
			
			if(texBot.getDistanceNorth() > 800) {
				break;
			}
		 }
		Delay.msDelay(1000);
		texBot.stop();
		
		Delay.msDelay(2000);
		
	}
	

}
