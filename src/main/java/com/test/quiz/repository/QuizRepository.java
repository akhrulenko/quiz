package com.test.quiz.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.quiz.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	@Query(value = "SELECT * FROM quizzes WHERE LOWER(title) LIKE ?1 AND activity IN (?2) AND start_date >= to_timestamp(?3) AND end_date <= to_timestamp(?4)", nativeQuery = true)
	public List<Quiz> findAll(String title, List<Boolean> actiities, Long startDate, Long endDate, Pageable Pageable);

}
