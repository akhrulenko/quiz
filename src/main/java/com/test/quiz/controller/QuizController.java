package com.test.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.quiz.model.Quiz;
import com.test.quiz.service.QuizService;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

	private final QuizService service;

	@Autowired
	public QuizController(QuizService service) {
		this.service = service;
	}

	@GetMapping
	public List<Quiz> getAll(@RequestParam(name = "title", required = false, defaultValue = "") String title,
			@RequestParam(name = "startDate", required = false, defaultValue = "0") Long startDate,
			@RequestParam(name = "endDate", required = false) Long endDate,
			@RequestParam(name = "actiity", required = false) Boolean actiity,
			@RequestParam(name = "orderBy") String orderBy,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum) {
		return service.findAll(title, startDate, endDate, actiity, orderBy, pageSize, pageNum);
	}

	@PostMapping
	public Quiz create(@RequestBody Quiz quiz) {
		return service.create(quiz);
	}

	@PutMapping
	public Quiz update(@RequestBody Quiz quiz) {
		return service.update(quiz);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}
}
