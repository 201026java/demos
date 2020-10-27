package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.clients.FlashcardClient;
import com.revature.clients.QuizClient;
import com.revature.models.Flashcard;
import com.revature.models.Quiz;
import com.revature.models.QuizComposite;

@RestController
public class QuizCompositeController {
	
	@Autowired
	private FlashcardClient flashcardClient;
	
	@Autowired
	private QuizClient quizClient;

	@GetMapping("/{id}")
	public ResponseEntity<QuizComposite> compile(@PathVariable("id") int id) {
		Quiz q = this.quizClient.findById(id);
		
		List<Integer> ids = q.getCards();
		
		List<Flashcard> cards = this.flashcardClient.findByIds(ids);
		
		QuizComposite composite = new QuizComposite(q, cards);
		
		return ResponseEntity.ok(composite);
	}
	
	@GetMapping
	public ResponseEntity<List<QuizComposite>> compileAll() {
		List<Flashcard> allcards = this.flashcardClient.findAll();
		
		List<Quiz> allquizzes = this.quizClient.findAll();
		
		if(allquizzes == null) {
			allquizzes = new ArrayList<>();
		}
		
		if(allcards == null) {
			allcards = new ArrayList<>();
		}
		
		Map<Integer, Flashcard> cardMap = allcards.stream()
				.collect(Collectors.toMap(Flashcard::getId, Function.identity()));
		
		List<QuizComposite> result = allquizzes.stream().map( (Quiz q) -> {
			List<Flashcard> cards = q.getCards().stream()
					.map(cardMap::get)
					.collect(Collectors.toList());
			
			return new QuizComposite(q, cards);
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok(result);
	}
}
