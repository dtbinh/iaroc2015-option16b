// MotionListener.java

/*
This software is part of the NxtJLib library.
It is Open Source Free Software, so you may
- run the code for any purpose
- study how the code works and adapt it to your needs
- integrate all or parts of the code in your own programs
- redistribute copies of the code
- improve the code and release your improvements to the public
However the use of the code is entirely your responsibility.
*/

package ch.aplu.nxt;

/**
 * Class with a declaration of a callback method when the movement stops.
 */
public interface MotionListener 
{
  /**
   * Called when the movement stops.
   */
   public void motionStopped();
}