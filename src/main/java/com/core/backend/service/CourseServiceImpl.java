package com.core.backend.service;

import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	@Override
	public String checkNumber(int number){
		String typeNumber = null;
        if(number > 0){
        	typeNumber = "positive.";
        }else if(number < 0){
        	typeNumber =  "negative.";
        }else{
        	typeNumber = "zero.";
        }        
        return typeNumber;
    }
	
	public long toMilesPerHour(double kilometersPerHour) {
        return kilometersPerHour < 0.0D ? -1L : Math.round(kilometersPerHour / 1.609D);
    }

	@Override
	public String printConversion(double kilometersPerHour) {
		String conversion = null;
        if (kilometersPerHour < 0.0D) {
        	conversion = "Invalid Value.";
        } else {
            long milesPerHour = toMilesPerHour(kilometersPerHour);
            conversion = kilometersPerHour + " kilometros/h = " + milesPerHour + " millas/h.";
        }
        return conversion;
    }

	@Override
	public String printMegaBytesAndKiloBytes(int kiloBytes) {
		String result = null;
		if (kiloBytes < 0){
            result = "Invalid Value";
        } else {
            int megabytes = (kiloBytes/1024);
            int kiloRemainder = kiloBytes%1024;
            result = kiloBytes+" KB = "+megabytes+" MB and "+kiloRemainder+" KB";
        }		
		return result;		
	}
	
	@Override
	public boolean shouldWakeUp(boolean barking,int hourOfDay){
        if (hourOfDay <0 || hourOfDay >23){
            return false;
        }
        if (barking == true && hourOfDay <8 || hourOfDay >22){
            return true;
        }else{
            return false;
        }
	}
	
	@Override
	public boolean isLeapYear(int year){
        if(year < 1 && year > 9999){
            return false;
        } else if((year % 4 == 0 && year % 100 != 0) && (year >= 1 && year <= 9999)|| year % 400 == 0 && (year >= 1 && year <= 9999)){
            return true;
        } else {
            return false;
        }
    }
	
	@Override
	public boolean areEqualByThreeDecimalPlaces(double num1, double num2) {
		System.out.println(num1);
        System.out.println(num2);
        num1 *= 1000;
        num2 *= 1000;
        System.out.println(num1);
        System.out.println(num2);
        int a = (int) num1;
        int b = (int) num2;        
        if(a == b){
            return true;
        }
        return false;
    }
	
	@Override
	public boolean hasEqualSum(int a, int b, int c) {
        if (a+b == c){
        	return true;
        }
        return false;
    }
	
	@Override
	public boolean hasTeen(int num1, int num2, int num3){
        if ((num1 >= 13 && num1 <= 19) || (num2 >= 13 && num2 <= 19) || (num3 >= 13 && num3 <= 19)) {
            return true;
        }
        return false;
    }
    
	@Override
    public boolean isTeen(int num4) {
        if (num4 >= 13 && num4 <= 19) {
            return true;
        }
        return false;
    }
    
	@Override
	public double areaCalculator(double radius) {
        if (radius < 0.0) {
            return -1.0;
        } else {
            return (Math.PI * radius * radius);
        }

    }

	@Override
    public double areaCalculator(double x, double y) {
        if((x < 0.0) || (y < 0.0)) {
            return -1.0;
        } else {
            return (x * y);
        }
    }

	@Override
	public String printYearsAndDays(long minutes) {		
		String resumen = null;	
		if (minutes <0) {
			resumen = "Invalid Value.";
        } else {
            long years = minutes / 525600;
            long minutesRemaining = (minutes - (years * 525600));
            long daysRemaining = minutesRemaining / 1440;
            resumen = minutes + " minutos igual a " + years + " a\u00f1os y " + daysRemaining + " dias.";
        }
		return resumen;
    }
	
	@Override
	public String printEqual(int num1, int num2, int num3) {
		String result = null;
        if((num1 < 0) || (num2 <0 ) || (num3 < 0)){
        	result = "Invalid Value";
        } else if(num1 == num2 && num1 == num3 && num2 == num3){
        	result = "All numbers are equal";
        } else if(num1 != num2 && num1 != num3 && num2 != num3){
        	result = "All numbers are different";
        } else {
        	result = "Neither all are equal or different";
        }
		return result;
    }
	
	@Override
	public boolean isCatPlaying(boolean summer, int temperature) {
        if (summer == true) {
            if (temperature >= 25 && temperature <= 45) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (temperature >= 25 && temperature <= 35) {
                return true;
            }
            else {
                return false;
            }
        }
    }
	
	@Override
	public String printNumberInWord(int number) {
		String numberName = null;
        switch(number){
            case 0:
            	numberName = "ZERO";
                break;
            case 1:
            	numberName = "ONE";
                break;
            case 2:
            	numberName = "TWO";
                break;
            case 3:
            	numberName = "THREE";
                break;
            case 4:
            	numberName = "FOUR";
                break;
            case 5:
            	numberName = "FIVE";
                break;
            case 6:
            	numberName = "SIX";
                break;
            case 7:
            	numberName = "SEVEN";
                break;
            case 8:
            	numberName = "EIGHT";
                break;
            case 9:
            	numberName = "NINE";
                break;
            default:
            	numberName = "OTHER";
                break;
        }
		return numberName;
    }
	
	public boolean isLeapYear2(int year){
        return (year >= 1 && year <= 9999 && (year%4 == 0 && year%100 != 0 || year%400==0));
    }
	
	@Override
	public int getDaysInMonth(int month, int year) {
        if(year<0 || year>9999){
            return -1;
        }
        boolean isLeap = this.isLeapYear2(year);
        int days;
        switch(month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                days = 31;
                break;

            case 2:
                days = 28;
                if (isLeap) days += 1;
                break;

            case 4: case 6: case 9: case 11:
                days = 30;
                break;

            default: days = -1;
                break;
        }
        return days;
    }
	
	public boolean isOdd(int number) {
        if(number<0){
            return false;
        }
        if(number%2 == 1){
            return true;
        }
        return false;
    }

	@Override
	public  int sumOdd(int start, int end) {
	     if((start < 0) || (end < 0) || (start>end)){
	        return -1;
	     }
	     int sum = 0;
	     for(int i = start; i<=end; i++){
	        if(this.isOdd(i)){
	            sum+=i;
	         }
	     }
	     return sum;
	}
 
	@Override
	public boolean isPalindrome(int number){		   
        int reverse = 0;
        int temp = number;   
        while(temp!=0){
            reverse *= 10;
            reverse += temp%10;
            temp /= 10;
        }
        return (reverse == number);
    }
	
	@Override
	public int sumFirstAndLastDigit(int number){
        if (number<0){
            return -1;
        }
        int sum = number%10;
        while(number>=10){
            number/=10;
        }
        return sum+number;
    }
	
	@Override
	public boolean hasSharedDigit(int numOne, int numTwo){
        if(numOne < 10 || numOne > 99 || numTwo < 10 || numTwo > 99){
            return false;
        }
        return (numOne%10 == numTwo%10  || numOne%10 == (numTwo/10) || numOne/10 == numTwo%10  || numOne/10 == (numTwo/10));
    }
	
	@Override
	public int getEvenDigitSum(int number){
       if(number<0){
           return -1;
        }
       int sum = 0;
       while(number>0){
           if(number%2==0){
               sum+=number%10;
            }
           number/=10;
        }
       return sum;
	}
	
	@Override
	public boolean hasSameLastDigit(int numOne, int numTwo, int numThree){
        if(numOne < 10 || numOne > 1000 ||numTwo < 10 || numTwo > 1000 || numThree < 10 || numThree > 1000) return false;
        return (numOne%10 == numTwo%10 || numOne%10 == numThree%10 || numTwo%10 == numThree%10);
    }
	
	@Override
    public boolean isValid(int numFour){
        if(numFour<10 || numFour>1000){
            return false;
        }else{
            return true;
        }
    }
    
}
