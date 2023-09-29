package com.itspacelab.springquizzesapp.service.impl;

import com.itspacelab.springquizzesapp.dto.CreateQuizRequestDto;
import com.itspacelab.springquizzesapp.dto.QuestionFromAngularDto;
import com.itspacelab.springquizzesapp.dto.QuestionOptionFromAngularDto;
import com.itspacelab.springquizzesapp.dto.QuizDto;
import com.itspacelab.springquizzesapp.entity.Question;
import com.itspacelab.springquizzesapp.entity.QuestionOption;
import com.itspacelab.springquizzesapp.entity.Quiz;
import com.itspacelab.springquizzesapp.entity.types.QuestionType;
import com.itspacelab.springquizzesapp.repository.QuestionOptionRepository;
import com.itspacelab.springquizzesapp.repository.QuestionRepository;
import com.itspacelab.springquizzesapp.repository.QuizRepository;
import com.itspacelab.springquizzesapp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;

    @Override
    public Quiz findQuizById(int id) {
        return quizRepository.findById(id).orElse(null);
    }

    @Override
    public QuizDto create(CreateQuizRequestDto dto) {
        List<QuestionFromAngularDto> questions = dto.getQuestions();
        List<List<QuestionOptionFromAngularDto>> options = dto.getOptions();

        String quizTitle = dto.getTitle();

        Quiz savedQuiz = quizRepository.save(Quiz.builder()
                .createdDateTime(LocalDateTime.now())
                .title(quizTitle)
                .build()
        );

        for (int i = 0; i < questions.size(); i++) {
            Question savedQuestion = questionRepository.save(
                    Question.builder()
                            .questionType(questions.get(i).getQuestionType().equals("SINGLE_SELECT")
                                    ? QuestionType.SINGLE_SELECT : QuestionType.MULTI_SELECT)
                            .score(questions.get(i).getScore())
                            .title(questions.get(i).getTitle())
                            .quiz(savedQuiz)
                            .build());

            for (QuestionOptionFromAngularDto option : options.get(i)) {
                questionOptionRepository.save(
                        QuestionOption.builder()
                                .isCorrect(option.getIsCorrect())
                                .question(savedQuestion)
                                .title(option.getTitle())
                                .build()
                );
            }
        }


        return QuizDto.builder()
                .createdDateTime(savedQuiz.getCreatedDateTime())
                .title(savedQuiz.getTitle())
                .id(savedQuiz.getId())
                .build();
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }
}
