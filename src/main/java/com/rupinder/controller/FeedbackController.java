package com.rupinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupinder.dao.FeedbackRepo;
import com.rupinder.model.Feedback;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	FeedbackRepo feedbackRepo;
	
	@PostMapping("/give_feedback")   // Remember to add isRead property separately
	public Feedback giveFeedback(@RequestBody Feedback feedback) {
		return feedbackRepo.save(feedback);
	}
	
	@GetMapping("/get_all_feedbacks")
	public List<Feedback> getAllFeedbacks(){
		return feedbackRepo.findAll();
	}
	
	@DeleteMapping("/delete_feedback/{fid}")
	public ResponseEntity<String> deleteFeedbackById(@PathVariable("fid") int id) {
		feedbackRepo.deleteById(id);
		return new ResponseEntity<>("Feedback deleted", HttpStatus.OK);
	}
}
