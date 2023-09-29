package com.itspacelab.springquizzesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionFromAngularDto {
    private String questionType;
    private String score;
    private String title;
}
