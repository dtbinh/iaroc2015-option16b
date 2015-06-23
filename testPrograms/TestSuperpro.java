package testPrograms;

import lejos.nxt.comm.RConsole;
import lejos.util.Delay;
import ch.aplu.nxt.NxtRobot;
import ch.aplu.nxt.SensorPort;
import ch.aplu.nxt.SuperProSensor;

public class TestSuperpro {

	public static void main(String[] args)
	{
		NxtRobot robot = new NxtRobot();
		SuperProSensor sensor = new SuperProSensor(SensorPort.S1);
		robot.addPart(sensor);
		int[] values = new int[4];
		RConsole.open();
		while(true)
		{
			sensor.readAnalog(values);
			RConsole.println("West Large:\t" + values[1] + "\tSouth Medium:\t" + values[2] + "\tEast Large:\t" + values[3]);
			Delay.msDelay(50);
		}
	}
}
