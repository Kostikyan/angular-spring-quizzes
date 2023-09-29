package com.itspacelab.springquizzesapp.repository;

import com.itspacelab.springquizzesapp.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
