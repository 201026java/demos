package com.revature.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.revature.models.Flashcard;
import com.revature.repositories.QuizRepository;

@Service
public class MessageService {
	
	@Autowired
	private QuizRepository quizDao;

	@KafkaListener(topics = "quiz-flashcard")
	public void processFlashcardEvent(FlashcardEvent event) {
		switch(event.getOperation()) {
		case DELETE:
			Flashcard removed = event.getFlashcard();
			this.quizDao.deleteCard(removed.getId());
			break;
		default:
			break;
		}
	}
}
