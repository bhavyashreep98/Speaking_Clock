package com.clock.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.clock.project.service.SpeakingClockService;

@SpringBootTest
class SpeakingClockApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	SpeakingClockService speakingClockService;

	@BeforeEach
	void setUp() {
		speakingClockService = new SpeakingClockService();
	}

	@Test
	void testBadRequest() {
		System.out.println(speakingClockService.getTimeInWords("56:98"));

		assertEquals(ResponseEntity.badRequest().body("Please enter valid hours and minutes value!"),
				speakingClockService.getTimeInWords("56:98"));
	}

	@Test
	void testSuccess() {
		assertEquals(ResponseEntity.ok("It's eight fourty five"), speakingClockService.getTimeInWords("08:45"));
	}

	@Test
	void testSuccessMidnight() {
		assertEquals(ResponseEntity.ok("It's Midnight"), speakingClockService.getTimeInWords("00:00"));
	}

	@Test
	void testSuccessMidday() {
		assertEquals(ResponseEntity.ok("It's Midday"), speakingClockService.getTimeInWords("12:00"));
	}

	@Test
	void testInvalidTime() {
		System.out.println(speakingClockService.getTimeInWords("56:98"));

		assertEquals(ResponseEntity.badRequest().body("Invalid time input!"),
				speakingClockService.getTimeInWords("56:98:67"));
	}

	@Test
	void testInternalServerError() {
		System.out.println(speakingClockService.getTimeInWords("5698"));

		assertEquals(ResponseEntity.internalServerError().body("Error while generating time in words. Plaese check your input!"),
				speakingClockService.getTimeInWords("56.98"));
	}
}
