package com.revature.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.clients.FlashcardClient;
import com.revature.models.Flashcard;
import com.revature.models.Quiz;
import com.revature.repositories.QuizRepository;

@RestController
public class QuizController {

	@Autowired
	private CircuitBreakerFactory<?, ?> cbFactory;

	@Autowired
	private QuizRepository quizDao;

	@Autowired
	private FlashcardClient flashcardClient;

	@GetMapping("/port")
	public ResponseEntity<String> retrievePort() {
		return this.cbFactory.create("flashcard-port").run(
				// Positive Case
				() -> ResponseEntity.ok(this.flashcardClient.getPort()),

				// Negative Case
				throwable -> retrievePortFallback());
	}

	public ResponseEntity<String> retrievePortFallback() {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("flashcard-service is currently unavailable. Please check back later.");
	}

	@GetMapping
	public ResponseEntity<List<Quiz>> findAll() {
		List<Quiz> all = quizDao.findAll();

		if (all.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(all);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Quiz> findById(@PathVariable("id") int id) {
		Optional<Quiz> optional = quizDao.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}

		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Quiz> insert(@RequestBody Quiz quiz) {
		int id = quiz.getId();

		if (id != 0) {
			return ResponseEntity.badRequest().build();
		}

		quizDao.save(quiz);
		return ResponseEntity.status(201).body(quiz);
	}

	@GetMapping("/cards")
	public ResponseEntity<List<Flashcard>> getCards() {
		return this.cbFactory.create("getCards").run(
				// Positive Case
				() -> {
					List<Flashcard> all = flashcardClient.findAll();

					if (all.isEmpty()) {
						return ResponseEntity.noContent().build();
					}

					return ResponseEntity.ok(all);
				},

				// Negative Case
				throwable -> getCardsFallback());
	}

	public ResponseEntity<List<Flashcard>> getCardsFallback() {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Collections.emptyList());
	}

	@GetMapping("/cards/{id}")
	public ResponseEntity<List<Flashcard>> getCardsFromQuiz(@PathVariable("id") int id) {
		return this.cbFactory.create("getCardsFromQuiz").run(

				// Positive Case
				() -> {
					Optional<Quiz> optional = this.quizDao.findById(id);

					if (optional.isPresent()) {
						Quiz q = optional.get();

						List<Integer> ids = q.getCards();

						return ResponseEntity.ok(this.flashcardClient.findByIds(ids));
					}

					return ResponseEntity.badRequest().build();
				},

				// Negative Case
				throwable -> getCardsFromQuizFallback());
	}

	public ResponseEntity<List<Flashcard>> getCardsFromQuizFallback() {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Collections.emptyList());
	}
}
