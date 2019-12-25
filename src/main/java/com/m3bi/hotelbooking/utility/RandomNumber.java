package com.m3bi.hotelbooking.utility;

import java.util.Random;

public class RandomNumber {
	
	private static Random rand = new Random(); 
	public static long getRandomNumber() {
		return rand.nextInt(1000);
	}

}
