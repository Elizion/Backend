package com.core.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.model.Circle;
import com.core.backend.model.Printer;
import com.core.backend.service.CourseService;
import com.core.backend.util.ResponseHandler;

@RestController
@RequestMapping("/course")
public class CourseController {
	 
	@Autowired
	public CourseService courseService;
	
	@GetMapping("/positiveNegativeZero")
	@ResponseBody
	public ResponseEntity<?> positiveNegativeZero(@RequestParam int number) {
		String typeNumber = null;		
		try {						
			typeNumber = this.courseService.checkNumber(number);			
		} catch (Exception e) {			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}				
		return ResponseHandler.generateResponseSuccess("End point positiveNegativeZero.", HttpStatus.OK, typeNumber);		
	}	
    
	@GetMapping("/speedConverter")
	@ResponseBody
	public ResponseEntity<?> speedConverter(@RequestParam double kilometersPerHour) {		
		String conversion = null;		
		try {						
			conversion = this.courseService.printConversion(kilometersPerHour);			
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}				
		return ResponseHandler.generateResponseSuccess("End point speedConverter", HttpStatus.OK, conversion);		
	}
	
	@GetMapping("/printMegaBytesAndKiloBytes")
	@ResponseBody
	public ResponseEntity<?> printMegaBytesAndKiloBytes(@RequestParam int kiloBytes) {		
		String result = null;		
		try {			
			result = this.courseService.printMegaBytesAndKiloBytes(kiloBytes);		
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point printMegaBytesAndKiloBytes", HttpStatus.OK, result);		
	}

	@GetMapping("/barkingDog")
	@ResponseBody
	public ResponseEntity<?> barkingDog(@RequestParam boolean barking, @RequestParam int hourOfDay) {		
		boolean result = false;		
		try {			
			result = this.courseService.shouldWakeUp(barking, hourOfDay);		
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point barkingDog", HttpStatus.OK, result);		
	}
	
	@GetMapping("/isLeapYear")
	@ResponseBody
	public ResponseEntity<?> isLeapYear(@RequestParam int year) {		
		boolean result = false;		
		try {			
			result = this.courseService.isLeapYear(year);		
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point isLeapYear", HttpStatus.OK, result);		
	}

	@GetMapping("/areEqualByThreeDecimalPlaces")
	@ResponseBody
	public ResponseEntity<?> areEqualByThreeDecimalPlaces(@RequestParam double num1, @RequestParam double num2) {		
		boolean result = false;		
		try {			
			result = this.courseService.areEqualByThreeDecimalPlaces(num1, num2);		
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point areEqualByThreeDecimalPlaces", HttpStatus.OK, result);		
	}
	
	@GetMapping("/hasEqualSum")
	@ResponseBody
	public ResponseEntity<?> hasEqualSum(@RequestParam int a, @RequestParam int b, @RequestParam int c) {		
		boolean result = false;		
		try {			
			result = this.courseService.hasEqualSum(a, b, c);		
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point hasEqualSum", HttpStatus.OK, result);		
	}

	@GetMapping("/hasTeen")
	@ResponseBody
	public ResponseEntity<?> hasTeen(@RequestParam int num1, @RequestParam int num2, @RequestParam int num3) {		
		boolean result = false;		
		try {			
			result =  this.courseService.hasTeen(num1, num2, num3);

		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point hasTeen", HttpStatus.OK, result);		
	}
	
	@GetMapping("/isTeen")
	@ResponseBody
	public ResponseEntity<?> isTeen(@RequestParam int num4) {		
		boolean result = false;		
		try {				
			result = this.courseService.isTeen(num4);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point isTeen", HttpStatus.OK, result);		
	}
	
	@GetMapping("/areaCircle")
	@ResponseBody
	public ResponseEntity<?> areaCircle(@RequestParam double radius) {		
		double result = 0;		
		try {				
			result = this.courseService.areaCalculator(radius);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point areaCircle", HttpStatus.OK, result);		
	}
	
	@GetMapping("/areaRectangle")
	@ResponseBody
	public ResponseEntity<?> areaRectangle(@RequestParam double x, @RequestParam double y) {		
		double result = 0;		
		try {				
			result = this.courseService.areaCalculator(x, y);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point areaRectangle", HttpStatus.OK, result);		
	}
	
	@GetMapping("/printYearsAndDays")
	@ResponseBody
	public ResponseEntity<?> areaRectangle(@RequestParam long minutes) {		
		String resumen = null;
		try {				
			resumen = this.courseService.printYearsAndDays(minutes);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point printYearsAndDays", HttpStatus.OK, resumen);		
	}

	@GetMapping("/printEqual")
	@ResponseBody
	public ResponseEntity<?> printEqual(@RequestParam int num1, @RequestParam int num2, @RequestParam int num3) {		
		String resumen = null;
		try {				
			resumen = this.courseService.printEqual(num1, num2, num3);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point printEqual", HttpStatus.OK, resumen);		
	}
	
	@GetMapping("/isCatPlaying")
	@ResponseBody
	public ResponseEntity<?> isCatPlaying(@RequestParam boolean summer, @RequestParam int temperature) {		
		boolean result = false;
		try {				
			result = this.courseService.isCatPlaying(summer, temperature);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point isCatPlaying", HttpStatus.OK, result);		
	}
	
	@GetMapping("/printNumberInWord")
	@ResponseBody
	public ResponseEntity<?> printNumberInWord(@RequestParam int number) {		
		String result = null;
		try {				
			result = this.courseService.printNumberInWord(number);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point printNumberInWord", HttpStatus.OK, result);		
	}
		
	@GetMapping("/getDaysInMonth")
	@ResponseBody
	public ResponseEntity<?> getDaysInMonth(@RequestParam int month, @RequestParam int year) {		
		int result = 0;
		try {				
			result = this.courseService.getDaysInMonth(month, year);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point getDaysInMonth", HttpStatus.OK, result);		
	}

	@GetMapping("/sumOdd")
	@ResponseBody
	public ResponseEntity<?> sumOdd(@RequestParam int start, @RequestParam int end) {		
		int result = 0;
		try {				
			result = this.courseService.sumOdd(start, end);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point sumOdd", HttpStatus.OK, result);		
	}

	@GetMapping("/isPalindrome")
	@ResponseBody
	public ResponseEntity<?> isPalindrome(@RequestParam int number) {		
		boolean result = false;
		try {				
			result = this.courseService.isPalindrome(number);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point isPalindrome", HttpStatus.OK, result);		
	}
	
	@GetMapping("/sumFirstAndLastDigit")
	@ResponseBody
	public ResponseEntity<?> sumFirstAndLastDigit(@RequestParam int number) {		
		int result = 0;
		try {				
			result = this.courseService.sumFirstAndLastDigit(number);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point isPalindrome", HttpStatus.OK, result);		
	}
	
	@GetMapping("/hasSharedDigit")
	@ResponseBody
	public ResponseEntity<?> hasSharedDigit(@RequestParam int numOne, @RequestParam int numTwo) {		
		boolean result = false;
		try {				
			result = this.courseService.hasSharedDigit(numOne, numTwo);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point isPalindrome", HttpStatus.OK, result);		
	}
	
	@GetMapping("/getEvenDigitSum")
	@ResponseBody
	public ResponseEntity<?> getEvenDigitSum(@RequestParam int number) {		
		int result = 0;
		try {				
			result = this.courseService.getEvenDigitSum(number);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point isPalindrome", HttpStatus.OK, result);		
	}
	
	@GetMapping("/hasSameLastDigit")
	@ResponseBody
	public ResponseEntity<?> hasSameLastDigit(@RequestParam int numOne, @RequestParam int numTwo, @RequestParam int numThree) {		
		boolean result = false;
		try {				
			result = this.courseService.hasSameLastDigit(numOne, numTwo, numThree);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point isPalindrome", HttpStatus.OK, result);		
	}
	
	@GetMapping("/isValid")
	@ResponseBody
	public ResponseEntity<?> isValid(@RequestParam int numFour) {
		boolean result = false;
		try {				
			result = this.courseService.isValid(numFour);
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
		return ResponseHandler.generateResponseSuccess("End point isPalindrome", HttpStatus.OK, result);		
	}

	@GetMapping("/encapsulation")
	@ResponseBody
	public ResponseEntity<?> encapsulation(@RequestParam int tonerLevelReq, @RequestParam boolean duplexReq, @RequestParam int pagesPrintedReq) {		
		boolean result = false;		
		try {							
			Printer printer = new Printer(50, true);
			System.out.println(printer.addToner(30));
			System.out.println("initial page count = " +printer.getPagesPrinted());			
			int pagesPrinted = printer.printPages(4);
			System.out.println("Pages printed was " + pagesPrinted +" new total print count for printer = " +printer.getPagesPrinted());			
			pagesPrinted = printer.printPages(2);
			System.out.println("Pages printed was " + pagesPrinted +" new total print count for printer = " +printer.getPagesPrinted());			
			pagesPrinted = printer.printPages(4);
			System.out.println("Pages printed was " + pagesPrinted +" new total print count for printer = " +printer.getPagesPrinted());
			
			/*												
			Example output:			
			100
			initial page count = 0
			Printing in duplex mode
			Pages printed was 2 new total print count for printer = 2
			Printing in duplex mode
			Pages printed was 1 new total print count for printer = 3
			Tips:
			*/			
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}				
		return ResponseHandler.generateResponseSuccess("End point isPalindrome", HttpStatus.OK, result);	
	}
	
	@GetMapping("/abstract")
	@ResponseBody
	public ResponseEntity<?> abstractExample(@RequestParam float radio) {		
		
		try {				
			
			Circle circle = new Circle();			
			float area = circle.calculateArea(radio);			
			System.out.println(area);			
			
		} catch (Exception e) {						
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		return ResponseHandler.generateResponseSuccess("End point isPalindrome", HttpStatus.OK, null);
		
	}
	
}
