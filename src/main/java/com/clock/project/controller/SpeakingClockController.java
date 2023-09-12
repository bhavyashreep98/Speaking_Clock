package com.clock.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.clock.project.service.SpeakingClockService;

@RestController
public class SpeakingClockController {

	@Autowired
	SpeakingClockService speakingClockService;

	@GetMapping(value = "/time/{time}")
	public ResponseEntity<String> getTimeInWords(@PathVariable String time) {
		return speakingClockService.getTimeInWords(time);
	}
}
