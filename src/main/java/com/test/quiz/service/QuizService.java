package com.test.quiz.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.quiz.model.Question;
import com.test.quiz.model.Quiz;
import com.test.quiz.repository.QuizRepository;

@Service
public class QuizService {

	private final QuizRepository repository;

	@Autowired
	public QuizService(QuizRepository repository) {
		this.repository = repository;
	}

	public Quiz findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}

	public List<Quiz> findAll(String title, Long startDate, Long endDate, Boolean actiity, String orderBy,
			Integer pageSize, Integer pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(Order.asc(orderBy)));
		List<Boolean> actiities = new ArrayList<>();
		if (actiity == null) {
			actiities.add(true);
			actiities.add(false);
		} else {
			actiities.add(actiity);
		}
		endDate = endDate == null ? Long.MAX_VALUE : endDate;
		return repository.findAll("%" + title.toLowerCase() + "%", actiities, startDate / 1000, endDate / 1000,
				pageable);
	}

	public Quiz save(Quiz quiz) {
		quiz.getQuestions().sort(Comparator.comparing(Question::getDisplayOrder));
		return repository.save(quiz);
	}

	@Transactional
	public Quiz create(Quiz quiz) {
		quiz.getQuestions().forEach(q -> q.setQuiz(quiz));
		return save(quiz);
	}

	@Transactional
	public Quiz update(Quiz quiz) {
		Quiz curQuiz = findById(quiz.getId());
		quiz.getQuestions().forEach(qst -> qst.setQuiz(curQuiz));
		curQuiz.getQuestions().clear();
		quiz.getQuestions().forEach(qst -> curQuiz.addQuestion(qst));
		return save(curQuiz);
	}

	public void delete(Quiz quiz) {
		repository.delete(quiz);
	}

	public void deleteById(Long id) {
		Quiz quiz = findById(id);
		delete(quiz);
	}

}
