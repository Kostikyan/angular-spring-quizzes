package com.itspacelab.springquizzesapp.endpoint;

import com.itspacelab.springquizzesapp.dto.CreateAnswerDto;
import com.itspacelab.springquizzesapp.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@Slf4j
public class AnswersEndpoint {

    private final AnswerService answerService;


    @PostMapping("/create-answers")
    public ResponseEntity<String> createAnswers(
            @RequestBody CreateAnswerDto createAnswerDto
    ) {
        answerService.save(createAnswerDto);
        return ResponseEntity.ok("answers saved");
    }
}
