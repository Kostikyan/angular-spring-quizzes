package com.itspacelab.springquizzesapp.service.impl;

import com.itspacelab.springquizzesapp.entity.QuestionOption;
import com.itspacelab.springquizzesapp.repository.QuestionOptionRepository;
import com.itspacelab.springquizzesapp.service.QuestionOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {

    private final QuestionOptionRepository questionOptionRepository;

    @Override
    public List<QuestionOption> findAllByQuestionId(int id) {
        return questionOptionRepository.findAllByQuestionId(id);
    }
}
