package com.itspacelab.springquizzesapp.dto;

import com.itspacelab.springquizzesapp.entity.Question;
import com.itspacelab.springquizzesapp.entity.QuestionOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendQuizDataDto {
    private String title;
    private List<Question> questions;
    private List<QuestionOption> options;
}
