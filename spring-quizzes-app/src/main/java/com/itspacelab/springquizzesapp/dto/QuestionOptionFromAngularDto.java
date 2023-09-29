package com.itspacelab.springquizzesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionFromAngularDto {
    private Boolean isCorrect;
    private String title;
}
