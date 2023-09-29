package com.itspacelab.springquizzesapp.service;

import com.itspacelab.springquizzesapp.dto.CreateQuizRequestDto;
import com.itspacelab.springquizzesapp.dto.QuizDto;
import com.itspacelab.springquizzesapp.entity.Quiz;

import java.util.List;

public interface QuizService {
    Quiz findQuizById(int id);

    QuizDto create(CreateQuizRequestDto dto);

    List<Quiz> findAll();
}
