package com.itspacelab.springquizzesapp.service;

import com.itspacelab.springquizzesapp.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findAllByQuizId(int id);
}
