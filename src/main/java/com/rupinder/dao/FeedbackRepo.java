package com.rupinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupinder.model.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
	
}
