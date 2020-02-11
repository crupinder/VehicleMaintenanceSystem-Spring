package com.rupinder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback")
public class Feedback {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String feedbackString;
	private boolean isRead;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFeedbackString() {
		return feedbackString;
	}
	public void setFeedbackString(String feedbackString) {
		this.feedbackString = feedbackString;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
}
