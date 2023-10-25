package com.core.backend.service;

public interface CourseService {

	public String checkNumber(int number);

	public String printConversion(double kilometersPerHour);

	public String printMegaBytesAndKiloBytes(int kiloBytes);

	public boolean shouldWakeUp(boolean barking, int hourOfDay);

	public boolean isLeapYear(int year);

	public boolean areEqualByThreeDecimalPlaces(double num1, double num2);

	public boolean hasEqualSum(int a, int b, int c);

	public boolean hasTeen(int num1, int num2, int num3);

	public boolean isTeen(int num4);

	public double areaCalculator(double radius);

	public double areaCalculator(double x, double y);

	public String printYearsAndDays(long minutes);

	public String printEqual(int num1, int num2, int num3);

	public boolean isCatPlaying(boolean summer, int temperature);

	public String printNumberInWord(int number);

	public int getDaysInMonth(int month, int year);

	public int sumOdd(int start, int end);

	public boolean isPalindrome(int number);

	public int sumFirstAndLastDigit(int number);

	public boolean hasSharedDigit(int numOne, int numTwo);

	public int getEvenDigitSum(int number);

	public boolean hasSameLastDigit(int numOne, int numTwo, int numThree);

	public boolean isValid(int numFour);

}
