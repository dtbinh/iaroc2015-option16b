package missions;
//import lejos.nxt.comm.RConsole;  // put in a spin around after x amount east west movements // put in the allignment code for the ir beacon // increase power for side to side movements

import lejos.util.Delay;

public class MineralRescue {
	public static void main(String[] args) {
		TetrixRobot texBot = new TetrixRobot();
		// int ave = 0;
		int count = 1;
		int dirChangeCount = 0;
		int[] seekerDirections = new int[5];
		int beaconDirection = 0;
		boolean foundBeacon = false;
		texBot.setAllPower(45);
		texBot.moveNorth();
		Delay.msDelay(1000);
		texBot.motorEast.stop();
		texBot.motorWest.stop();
		while (foundBeacon == false) {
			texBot.setAllPower(45);
			texBot.moveNorth();
			
			long time = System.currentTimeMillis();
			while (texBot.binaryNorthLeft() == true || texBot.binaryNorthRight() == true) //while it sees a wall
			{
				if (time + 1000 < System.currentTimeMillis() && texBot.binaryNorthLeft() == false && texBot.binaryNorthRight() == false) //untill a second has passed and no wall found
				{
					break;
				}
				if (dirChangeCount < 3) {
					if (!texBot.binaryWestRight() || !texBot.binaryWestLeft())  // if somethings west move east
					{																
						dirChangeCount++;
						texBot.moveEast();
					} else if (!texBot.binaryEastLeft() || !texBot.binaryEastRight()) { // somethings east move west 
						dirChangeCount++;
						texBot.moveWest();
					} else {
						texBot.moveEast();
					}
				} else {
					texBot.stop();
					
				}
			}
				Delay.msDelay(1000);
				texBot.motorEast.stop();
				texBot.motorWest.stop();
			
				if (count % 2 == 0) 
				{// decides wether to move west or east
					texBot.setAllPower(30);
					texBot.moveEast();
					while (texBot.getDistanceEastRight() > 50 && texBot.getDistanceEastRight() > 50 && foundBeacon == false) // while no wall
					{seekerDirections = texBot.seeker.getSensorValues(); // refreshes sensor values
						for (int i = 0; i < 5; i++) // iteration through all
													// sensors
						{
							if (seekerDirections[i] > 15) // if one sensor sees
															// a ir large value
							{
								beaconDirection = i;
								texBot.stop();
								foundBeacon = true;
							}
						}
						Delay.msDelay(5);

					}
					count++;
					texBot.motorNorth.stop();
					texBot.motorSouth.stop();
				}

				else // decides wether to move east or west
				{
					texBot.setAllPower(30);
					texBot.moveWest();
					while (texBot.getDistanceWestRight() > 50 && texBot.getDistanceWestRight() > 50	&& foundBeacon == false) // while no wall
					{seekerDirections = texBot.seeker.getSensorValues();//refreshes sensor values
						for (int i = 0; i < 5; i++) // iteration through all
													// sensors
						{
							if (seekerDirections[i] > 15) // if one sensor sees
															// a ir large value
							{
								beaconDirection = i;
								texBot.stop();
								foundBeacon = true;
							}
						}
						Delay.msDelay(5);

					}
					count++;
					texBot.motorNorth.stop();
					texBot.motorSouth.stop();
				}
			}
		}
	}

