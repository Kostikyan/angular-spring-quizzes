package com.itspacelab.springquizzesapp.endpoint;

import com.itspacelab.springquizzesapp.dto.CreateQuizRequestDto;
import com.itspacelab.springquizzesapp.dto.GetQuizDataRequest;
import com.itspacelab.springquizzesapp.dto.QuizDto;
import com.itspacelab.springquizzesapp.dto.SendQuizDataDto;
import com.itspacelab.springquizzesapp.entity.Question;
import com.itspacelab.springquizzesapp.entity.QuestionOption;
import com.itspacelab.springquizzesapp.entity.Quiz;
import com.itspacelab.springquizzesapp.service.QuestionOptionService;
import com.itspacelab.springquizzesapp.service.QuestionService;
import com.itspacelab.springquizzesapp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class QuizEndpoint {

    private final QuizService quizService;
    private final QuestionService questionService;
    private final QuestionOptionService questionOptionService;

    @PostMapping("/create-quiz")
    public ResponseEntity<QuizDto> createQuiz(@RequestBody CreateQuizRequestDto dto) {
        QuizDto quizDto = quizService.create(dto);
        return ResponseEntity.ok(quizDto);
    }

    @GetMapping("/get-quizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> all = quizService.findAll();
        if(all.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(all);
    }

    @PostMapping("/get-data-by-quiz-id")
    public ResponseEntity<SendQuizDataDto> getDataByQuizId(@RequestBody GetQuizDataRequest getQuizDataRequest) {
        int id = getQuizDataRequest.getId();
        Quiz quizById = quizService.findQuizById(id);

        String title = quizById.getTitle(); // 1
        List<Question> questions = questionService.findAllByQuizId(id); // 2
        List<QuestionOption> options = new ArrayList<>(); // 3

        for (Question question : questions) {
            options.addAll(questionOptionService.findAllByQuestionId(question.getId()));
        }

        if(title == null || questions.isEmpty() || options.isEmpty()) return ResponseEntity.notFound().build();

        SendQuizDataDto toSend = SendQuizDataDto.builder()
                .options(options)
                .questions(questions)
                .title(title)
                .build();
        return ResponseEntity.ok(toSend);
    }
}
