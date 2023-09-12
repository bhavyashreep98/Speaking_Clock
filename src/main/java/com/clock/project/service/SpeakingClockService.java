package com.clock.project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpeakingClockService {

	public ResponseEntity<String> getTimeInWords(String time) {
		try {
			String[] str = time.split(":");
			if (str.length > 2) {
				return ResponseEntity.badRequest().body("Invalid time input!");
			}
			int hours = Integer.parseInt(str[0]);
			int minutes = Integer.parseInt(str[1]);
			if ((hours < 0 || hours > 23) || (minutes < 0 || minutes > 59)) {
				return ResponseEntity.badRequest().body("Please enter valid hours and minutes value!");
			}
			if (hours == 0 && minutes == 0) {
				return ResponseEntity.ok("It's Midnight");
			}
			if (hours == 12 && minutes == 0) {
				return ResponseEntity.ok("It's Midday");
			}
			String hoursWord = numberToWord(hours);
			String minuteWord = numberToWord(minutes);
			return ResponseEntity.ok("It's " + hoursWord + " " + minuteWord);
		} catch (

		Exception e) {
			return ResponseEntity.internalServerError()
					.body("Error while generating time in words. Plaese check your input!");
		}
	}

	public String numberToWord(int number) {
		String[] oneDigit = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		String[] twoDigit = { "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
				"nineteen" };
		String[] multipleOfTen = { "ten", "twenty", "thirty", "fourty", "fifty" };

		String word = null;
		if (number > 0 && number <= 9) {
			word = oneDigit[number - 1];
		} else {
			int n = number % 10;
			if (n == 0) {
				n = n / 10;
				word = multipleOfTen[n - 1];
			} else {
				int m = number / 10;
				if (m == 1) {
					word = twoDigit[n - 1];
				} else {
					word = multipleOfTen[m - 1] + " " + oneDigit[n - 1];
				}
			}
		}
		return word;
	}

}
