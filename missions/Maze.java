package missions;

import java.util.Queue;

import lejos.nxt.comm.RConsole;

//import lejos.nxt.comm.RConsole;
import lejos.util.Delay;

public class Maze {
	static TetrixRobot texBot = new TetrixRobot();
	static Directions currDirection = Directions.NORTH;
	static Queue<Cell> cells = new Queue<Cell>();
	static short x=0,
			y=0;
	static Cell currentCell;
	static Directions vertAllign, horizAllign;
	static int offset = 45;
	public static void main(String[] args) {
		//RConsole.open();
		texBot.setAllPower(40);
		scoutingRun();
	}
	public static void scoutingRun() {
		while(true) {
			currentCell = new Cell(x,y);
			updateCurrentCell();
			//RConsole.println("X:\t"+currentCell.x+"\tY:\t"+currentCell.y+"\tN:\t"+currentCell.north.name()+"\tW:\t"+currentCell.west.name()+"\tE:\t"+currentCell.east.name()+"\tS:\t"+currentCell.west.name());
			allign();
			//RConsole.println("Facing:\t"+currDirection.name());
			decideMove();
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
			} else if (currentCell.east==Cell.WallState.NONE){
				move(Directions.EAST);//RConsole.println("Decided to move EAST");
			} else if (currentCell.north==Cell.WallState.NONE){
				move(Directions.NORTH);//RConsole.println("Decided to move NORTH");
			} else if (currentCell.west==Cell.WallState.NONE) {
				move(Directions.WEST);//RConsole.println("Decided to move WEST");
			}
			break;
		}
	}
	public static void updateCurrentCell() {
		int north,west,east,south;
		north = (int) texBot.getDistanceNorthLeft();
		west = (int) texBot.getDistanceWestLeft();
		east = (int) texBot.getDistanceEastLeft();
		south = (int) texBot.getDistanceSouthLeft();
		//RConsole.println("North:\t"+north+"\tWest:\t"+west+"\tEast:\t"+east+"\tSouth\t"+south);
		currentCell.north = north<51?Cell.WallState.WALL:Cell.WallState.NONE;
		currentCell.west = west<51?Cell.WallState.WALL:Cell.WallState.NONE;
		currentCell.east = east<51?Cell.WallState.WALL:Cell.WallState.NONE;
		currentCell.south = south<51?Cell.WallState.WALL:Cell.WallState.NONE;
		
	}
	public static int degreesFromCM(double cm) {
		return (int) Math.round(15.04*cm);
	}
	public static void allignNorth() {
		//RConsole.println("Alligned NORTH");
		texBot.setAllPower(20);
		 do {
			texBot.moveSouth();
		} while(texBot.binaryNorthLeft()||texBot.binaryNorthRight());
		Delay.msDelay(25);
		texBot.stop();
		long time = System.currentTimeMillis();
		texBot.setAllPower(35);
		while(!texBot.binaryNorthLeft()||!texBot.binaryNorthRight()) {
			if(System.currentTimeMillis()>time+3000) {
				allign();
				break;
			}
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
		vertAllign = Directions.NORTH;
	}
	public static void allignWest() {
		//RConsole.println("Alligned WEST");
		texBot.setAllPower(20);
		do {
			texBot.moveEast();
		} while(texBot.binaryWestLeft()||texBot.binaryWestRight());
		Delay.msDelay(25);
		texBot.stop();
		long time = System.currentTimeMillis();
		texBot.setAllPower(35);
		while(!texBot.binaryWestLeft()||!texBot.binaryWestRight()) {
			if(System.currentTimeMillis()>time+3000) {
				allign();
				break;
			}
			if(!texBot.binaryWestRight()) {
				texBot.motorNorth.forward();
				//texBot.motorEast.forward();
			} else {
				texBot.motorNorth.stop();
			}
			if(!texBot.binaryWestLeft()) {
				texBot.motorSouth.backward();
				//texBot.motorEast.backward();
			} else {
				texBot.motorSouth.stop();
			}
		}
		texBot.stop();
		horizAllign = Directions.WEST;
	}
	public static void allignSouth() {
		//RConsole.println("Alligned SOUTH");
		texBot.setAllPower(20);
		do{
			texBot.moveNorth();
		}while(texBot.binarySouthLeft()||texBot.binarySouthRight());
		Delay.msDelay(25);
		texBot.stop();		
		long time = System.currentTimeMillis();
		texBot.setAllPower(35);
		while(!texBot.binarySouthLeft()||!texBot.binarySouthRight()) {
			if(System.currentTimeMillis()>time+3000) {
				allign();
				break;
			}
			if(!texBot.binarySouthLeft()) {
				texBot.motorEast.backward();
				//texBot.motorNorth.backward();
			} else {
				texBot.motorEast.stop();
			}
			if(!texBot.binarySouthRight()) {
				texBot.motorWest.forward();
				//texBot.motorNorth.forward();
			} else {
				texBot.motorWest.stop();
			}
		}
		texBot.stop();
		vertAllign = Directions.SOUTH;
	}
	public static void allignEast() {
		//RConsole.println("Alligned EAST");
		texBot.setAllPower(20);
		do {
			texBot.moveWest();
		} while (texBot.binaryEastLeft()||texBot.binaryEastRight());
		Delay.msDelay(25);
		texBot.stop();
		long time = System.currentTimeMillis();
		texBot.setAllPower(35);
		while(!texBot.binaryEastLeft()||!texBot.binaryEastRight()) {
			if(System.currentTimeMillis()>time+3000) {
				allign();
				break;
			}
			if(!texBot.binaryEastLeft()) {
				texBot.motorNorth.backward();
				//texBot.motorWest.backward();
			} else {
				texBot.motorNorth.stop();
			}
			if(!texBot.binaryEastRight()) {
				texBot.motorSouth.forward();
				//texBot.motorWest.forward();
			} else {
				texBot.motorSouth.stop();
			}
		}
		texBot.stop();
		horizAllign = Directions.EAST;
	}
	public static void move(Directions dir) {
		texBot.setAllPower(40);
		texBot.stop();
		int travel = 1085;
		switch(dir) {
		case NORTH:
			if(vertAllign==Directions.SOUTH) {
				travel+=offset;
			} else if (vertAllign==Directions.NORTH) {
				travel-=offset;
			}
			texBot.motorWest.resetTachoCount();
			texBot.motorEast.resetTachoCount();
			texBot.moveNorth();
			while (texBot.motorEast.getTachoCount()<travel && texBot.motorWest.getTachoCount()>-travel) {
				if(texBot.motorEast.getTachoCount()>travel) {
					texBot.motorEast.stop();
				} 
				if(texBot.motorWest.getTachoCount()<-travel) {
					texBot.motorWest.stop();
				}
			}
			texBot.stop();
			y+=1;
			cells.addElement(currentCell);
			currDirection = Directions.NORTH;
			break;
		case WEST:
			if(horizAllign==Directions.WEST) {
				travel-=offset;
			} else if (horizAllign==Directions.EAST) {
				travel+=offset;
			}
			texBot.motorNorth.resetTachoCount();
			texBot.motorSouth.resetTachoCount();			
			texBot.moveWest();
			while (texBot.motorNorth.getTachoCount()<travel && texBot.motorSouth.getTachoCount()>-travel) {
				if(texBot.motorNorth.getTachoCount()>travel) {
					texBot.motorNorth.stop();
				} 
				if(texBot.motorSouth.getTachoCount()<-travel) {
					texBot.motorSouth.stop();
				}
			}			texBot.stop();
			x-=1;
			cells.addElement(currentCell);
			currDirection = Directions.WEST;
			break;
		case SOUTH:			
			if(vertAllign==Directions.SOUTH) {
				travel-=offset;
			} else if (vertAllign==Directions.NORTH) {
				travel+=offset;
			}
			texBot.motorWest.resetTachoCount();
			texBot.motorEast.resetTachoCount();
			texBot.moveSouth();
			while (texBot.motorEast.getTachoCount()>-travel && texBot.motorWest.getTachoCount()<travel) {
				if(texBot.motorEast.getTachoCount()<-travel) {
					texBot.motorEast.stop();
				} 
				if(texBot.motorWest.getTachoCount()>travel) {
					texBot.motorWest.stop();
				}
			}
			texBot.stop();
			y-=1;
			cells.addElement(currentCell);
			currDirection = Directions.SOUTH;
			break;
		case EAST:
			if(horizAllign==Directions.WEST) {
				travel+=offset;
			} else if (horizAllign==Directions.EAST) {
				travel-=offset;
			}
			texBot.motorSouth.resetTachoCount();
			texBot.motorNorth.resetTachoCount();
			texBot.moveEast();
			while (texBot.motorNorth.getTachoCount()>-travel && texBot.motorSouth.getTachoCount()<travel) {
				if(texBot.motorNorth.getTachoCount()<-travel) {
					texBot.motorNorth.stop();
				} 
				if(texBot.motorSouth.getTachoCount()>travel) {
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
