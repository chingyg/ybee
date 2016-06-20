package com.utils;

import java.util.Random;

/**
 * Expands on the Random class with my own implemented methods.
 * 
 * @author chingyg
 *
 */
public class RandomUtils
{
	private Random r;

	public RandomUtils()
	{
		r = new Random();
	}

	/**
	 * Generates a random ASCII number between 33 and 126 inclusive, then
	 * converts it to string.
	 * 
	 * @return A character of the random ASCII number, in string format.
	 */
	public char generateChar()
	{
		final int MIN = 33;
		final int MAX = 126;

		int newNumber = r.nextInt((MAX - MIN) + 1) + MIN;

		return (char) newNumber;

	}

}
