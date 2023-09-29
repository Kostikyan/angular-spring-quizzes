package com.itspacelab.springquizzesapp.service.impl;

import com.itspacelab.springquizzesapp.dto.CreateAnswerDto;
import com.itspacelab.springquizzesapp.dto.UserDto;
import com.itspacelab.springquizzesapp.entity.Answer;
import com.itspacelab.springquizzesapp.entity.QuestionOption;
import com.itspacelab.springquizzesapp.entity.User;
import com.itspacelab.springquizzesapp.repository.AnswerRepository;
import com.itspacelab.springquizzesapp.service.AnswerService;
import com.itspacelab.springquizzesapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final UserService userService;
    private final AnswerRepository answerRepository;

    @Override
    public void save(CreateAnswerDto createAnswerDto) {
        User user = userService.findByUsername(((UserDto) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername());

        List<List<QuestionOption>> answersListList = createAnswerDto.getAnswers();

        answersListList.forEach(answersList -> {
            for (QuestionOption questionOption : answersList) {
                Answer answer = Answer.builder()
                        .dateTime(LocalDateTime.now())
                        .question(questionOption.getQuestion())
                        .user(user)
                        .questionOption(questionOption)
                        .build();
                answerRepository.save(answer);
            }
        });

    }
}
