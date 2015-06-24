package missions;

import java.util.Queue;

public class Maze {
	static TetrixRobot texBot = new TetrixRobot();
	static Directions direction;
	static Queue<Cell> cells = new Queue<Cell>();
	static short x=0,
			y=0;
	static Cell currentCell;
	public static void main(String[] args) {
		
		currentCell = new Cell(x,y);
		updateCurrentCell();
		move(Directions.NORTH)
	}
	public static void updateCurrentCell() {
		currentCell.north = texBot.getDistanceNorth()<50?Cell.WallState.WALL:Cell.WallState.NONE;
		currentCell.west = texBot.getDistanceWest()<50?Cell.WallState.WALL:Cell.WallState.NONE;
		currentCell.east = texBot.getDistanceEast()<50?Cell.WallState.WALL:Cell.WallState.NONE;
		currentCell.south = texBot.getDistanceSouth()<50?Cell.WallState.WALL:Cell.WallState.NONE;
	}
	public static int degreesFromCM(double cm) {
		return (int) Math.round(15.04*cm);
	}
	public static void allignWest() {
		
	}
	public static void move(Directions dir) {
		switch(dir) {
		case NORTH:
			texBot.motorEast.resetTachoCount();
			texBot.moveNorth();
			while (texBot.motorWest.getTachoCount()<1053) {}	
			break;
		case WEST:
			break;
		case EAST:
			break;
		case SOUTH:
			break;
		}
	}
	private enum Directions {
		NORTH,SOUTH,EAST,WEST
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
