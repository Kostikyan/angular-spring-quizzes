package com.itspacelab.springquizzesapp.service.impl;

import com.itspacelab.springquizzesapp.entity.Question;
import com.itspacelab.springquizzesapp.repository.QuestionRepository;
import com.itspacelab.springquizzesapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public List<Question> findAllByQuizId(int id) {
        return questionRepository.findAllByQuiz_Id(id);
    }
}
