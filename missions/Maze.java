package missions;

import java.util.Queue;

//import lejos.nxt.comm.RConsole;
import lejos.util.Delay;

public class Maze {
	static TetrixRobot texBot = new TetrixRobot();
	static Directions currDirection = Directions.NORTH;
	static Queue<Cell> cells = new Queue<Cell>();
	static short x=0,
			y=0;
	static Cell currentCell;
	public static void main(String[] args) {
		//RConsole.open();
		texBot.setAllPower(40);
		while(true) {
			currentCell = new Cell(x,y);
			updateCurrentCell();
			//RConsole.println("X:\t"+currentCell.x+"\tY:\t"+currentCell.y+"\tN:\t"+currentCell.north.name()+"\tW:\t"+currentCell.west.name()+"\tE:\t"+currentCell.east.name()+"\tS:\t"+currentCell.west.name());
			allign();
			Delay.msDelay(500);
			//RConsole.println("Facing:\t"+currDirection.name());
			decideMove();
			Delay.msDelay(500);
		}
	}
	public static void decideMove() {
		switch(currDirection) {
		case NORTH:
			if(currentCell.east==Cell.WallState.NONE) {
				move(Directions.EAST);//RConsole.println("Decided to move EAST");
			} else if (currentCell.north==Cell.WallState.NONE){
				move(Directions.NORTH);//RConsole.println("Decided to move NORTH");
			} else if (currentCell.west==Cell.WallState.NONE){
				move(Directions.WEST); //RConsole.println("Decided to move WEST");
			} else if (currentCell.south==Cell.WallState.NONE) {
				move(Directions.SOUTH);//RConsole.println("Decided to move SOUTH");
			}
			break;
		case WEST:
			if(currentCell.north==Cell.WallState.NONE) {
				move(Directions.NORTH);//RConsole.println("Decided to move NORTH");
			} else if (currentCell.west==Cell.WallState.NONE){
				move(Directions.WEST);//RConsole.println("Decided to move WEST");
			} else if (currentCell.south==Cell.WallState.NONE){
				move(Directions.SOUTH);//RConsole.println("Decided to move SOUTH");
			} else if (currentCell.east==Cell.WallState.NONE) {
				move(Directions.EAST);//RConsole.println("Decided to move EAST");
			}
			break;
		case SOUTH:
			if(currentCell.west==Cell.WallState.NONE) {
				move(Directions.WEST);//RConsole.println("Decided to move WEST");
			} else if (currentCell.south==Cell.WallState.NONE){
				move(Directions.SOUTH);//RConsole.println("Decided to move SOUTH");
			} else if (currentCell.east==Cell.WallState.NONE){
				move(Directions.EAST);//RConsole.println("Decided to move EAST");
			} else if (currentCell.north==Cell.WallState.NONE) {
				move(Directions.NORTH);//RConsole.println("Decided to move NORTH");
			}
			break;
		case EAST:
			if(currentCell.south==Cell.WallState.NONE) {
				move(Directions.SOUTH);//RConsole.println("Decided to move SOUTH");
			} else if (currentCell.west==Cell.WallState.NONE){
				move(Directions.WEST);//RConsole.println("Decided to move WEST");
			} else if (currentCell.north==Cell.WallState.NONE){
				move(Directions.NORTH);//RConsole.println("Decided to move NORTH");
			} else if (currentCell.east==Cell.WallState.NONE) {
				move(Directions.EAST);//RConsole.println("Decided to move EAST");
			}
			break;
		}
	}
	public static void updateCurrentCell() {
		currentCell.north = texBot.getDistanceNorthLeft()<50?Cell.WallState.WALL:Cell.WallState.NONE;
		currentCell.west = texBot.getDistanceWestLeft()<50?Cell.WallState.WALL:Cell.WallState.NONE;
		currentCell.east = texBot.getDistanceEastLeft()<50?Cell.WallState.WALL:Cell.WallState.NONE;
		currentCell.south = texBot.getDistanceSouthLeft()<50?Cell.WallState.WALL:Cell.WallState.NONE;
	}
	public static int degreesFromCM(double cm) {
		return (int) Math.round(15.04*cm);
	}
	public static void allignNorth() {
		//RConsole.println("Alligned NORTH");
		texBot.setAllPower(20);
		while(texBot.binaryNorthLeft()||texBot.binaryNorthRight()) {
			texBot.moveSouth();
		}
		texBot.stop();
		while(!texBot.binaryNorthLeft()||!texBot.binaryNorthRight()) {
			if(!texBot.binaryNorthLeft()) {
				texBot.motorWest.backward();
			} else {
				texBot.motorWest.stop();
			}
			if(!texBot.binaryNorthRight()) {
				texBot.motorEast.forward();
			} else {
				texBot.motorEast.stop();
			}
		}
		texBot.stop();
	}
	public static void allignWest() {
		//RConsole.println("Alligned WEST");
		texBot.setAllPower(20);
		while(texBot.binaryWestLeft()||texBot.binaryWestRight()) {
			texBot.moveEast();
		}
		texBot.stop();
		while(!texBot.binaryWestLeft()||!texBot.binaryWestRight()) {
			if(!texBot.binaryWestRight()) {
				texBot.motorNorth.forward();
			} else {
				texBot.motorNorth.stop();
			}
			if(!texBot.binaryWestLeft()) {
				texBot.motorSouth.backward();
			} else {
				texBot.motorSouth.stop();
			}
		}
		texBot.stop();
	}
	public static void allignSouth() {
		//RConsole.println("Alligned SOUTH");
		texBot.setAllPower(20);
		while(texBot.binarySouthLeft()||texBot.binarySouthRight()) {
			texBot.moveNorth();
		}
		texBot.stop();
		while(!texBot.binarySouthLeft()||!texBot.binarySouthRight()) {
			if(!texBot.binarySouthLeft()) {
				texBot.motorEast.backward();
			} else {
				texBot.motorEast.stop();
			}
			if(!texBot.binarySouthRight()) {
				texBot.motorWest.forward();
			} else {
				texBot.motorWest.stop();
			}
		}
		texBot.stop();
	}
	public static void allignEast() {
		//RConsole.println("Alligned EAST");
		texBot.setAllPower(20);
		while(texBot.binaryEastLeft()||texBot.binaryEastRight()) {
			texBot.moveWest();
		}
		texBot.stop();
		while(!texBot.binaryEastLeft()||!texBot.binaryEastRight()) {
			if(!texBot.binaryEastLeft()) {
				texBot.motorNorth.backward();
			} else {
				texBot.motorNorth.stop();
			}
			if(!texBot.binaryEastRight()) {
				texBot.motorSouth.forward();
			} else {
				texBot.motorSouth.stop();
			}
		}
		texBot.stop();
	}
	public static void move(Directions dir) {
		texBot.setAllPower(40);
		texBot.stop();
		switch(dir) {
		case NORTH:
			texBot.motorWest.resetTachoCount();
			texBot.motorEast.resetTachoCount();
			texBot.moveNorth();
			while (texBot.motorEast.getTachoCount()<1070 && texBot.motorWest.getTachoCount()>-1070) {
				if(texBot.motorEast.getTachoCount()>1070) {
					texBot.motorEast.stop();
				} 
				if(texBot.motorWest.getTachoCount()<-1070) {
					texBot.motorWest.stop();
				}
			}
			texBot.stop();
			y+=1;
			cells.addElement(currentCell);
			currDirection = Directions.NORTH;
			break;
		case WEST:
			texBot.motorNorth.resetTachoCount();
			texBot.motorSouth.resetTachoCount();			
			texBot.moveWest();
			while (texBot.motorNorth.getTachoCount()<1070 && texBot.motorSouth.getTachoCount()>-1070) {
				if(texBot.motorNorth.getTachoCount()>1070) {
					texBot.motorNorth.stop();
				} 
				if(texBot.motorSouth.getTachoCount()<-1070) {
					texBot.motorSouth.stop();
				}
			}			texBot.stop();
			x-=1;
			cells.addElement(currentCell);
			currDirection = Directions.WEST;
			break;
		case SOUTH:
			texBot.motorWest.resetTachoCount();
			texBot.motorEast.resetTachoCount();
			texBot.moveSouth();
			while (texBot.motorEast.getTachoCount()>-1070 && texBot.motorWest.getTachoCount()<1070) {
				if(texBot.motorEast.getTachoCount()<-1070) {
					texBot.motorEast.stop();
				} 
				if(texBot.motorWest.getTachoCount()>1070) {
					texBot.motorWest.stop();
				}
			}
			texBot.stop();
			y-=1;
			cells.addElement(currentCell);
			currDirection = Directions.SOUTH;
			break;
		case EAST:
			texBot.motorSouth.resetTachoCount();
			texBot.motorNorth.resetTachoCount();
			texBot.moveEast();
			while (texBot.motorNorth.getTachoCount()>-1070 && texBot.motorSouth.getTachoCount()<1070) {
				if(texBot.motorNorth.getTachoCount()<-1070) {
					texBot.motorNorth.stop();
				} 
				if(texBot.motorSouth.getTachoCount()>1070) {
					texBot.motorSouth.stop();
				}
			}
			texBot.stop();
			x+=1;
			cells.addElement(currentCell);
			currDirection = Directions.EAST;
			break;
		}
	}
	public static void allign() {
		if (currentCell.north == Cell.WallState.WALL) {
			allignNorth();
		}
		if (currentCell.south == Cell.WallState.WALL) {
			allignSouth();
		}
		if (currentCell.west == Cell.WallState.WALL) {
			allignWest();
		}
		if (currentCell.east == Cell.WallState.WALL) {
			allignEast();
		}
	}
	private enum Directions {
		NORTH,WEST,SOUTH,EAST
	}
	private static class Cell {
		public int x,y;
		public WallState north,
			south,
			east,
			west;
		public Cell(int x,int y) {
			this.x=x;
			this.y=y;
		}
		private static enum WallState {
			NONE,WALL,VIRTUAL_WALL;
		}
	}
}
