package com.itspacelab.springquizzesapp.service;

import com.itspacelab.springquizzesapp.entity.QuestionOption;

import java.util.List;

public interface QuestionOptionService {

    List<QuestionOption> findAllByQuestionId(int id);
}
