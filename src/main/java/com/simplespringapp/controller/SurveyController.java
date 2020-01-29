package com.simplespringapp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.simplespringapp.service.SurveyService;
import com.springsimpleapp.model.Question;

@RestController
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;

	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveQuestions(@PathVariable String surveyId) {
		return surveyService.retrieveQuestions(surveyId);
	}
	
	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionToSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion) {
		
		
		
		/*
		 * 
		 * formatted like
		 * 
		 * id : question 100 desc: dfasdf etc.
		 */
		
		
		
		
		
		Question question =  surveyService.addQuestion(surveyId, newQuestion);
		
		if (question==null)
		return	ResponseEntity.noContent().build();
		
		//success and status URI of the new resource in response header 
		// URI surveys/{surveyId}/questions/{questionId
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(question.getId()).toUri();       //created location takes current requested uri, append /id & replace with question.getID


		//status
		
		//response entity has methods to create responses to specific statuses
		//return created status
		return ResponseEntity.created(location).build();
		
	}
	
	

	// GET "/surveys/{surveyId}/questions/{questionId}"
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForQuestion(@PathVariable String surveyId,
			@PathVariable String questionId) {
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
}

