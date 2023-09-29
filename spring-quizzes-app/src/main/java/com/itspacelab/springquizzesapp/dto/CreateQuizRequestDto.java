package com.itspacelab.springquizzesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuizRequestDto {
    private String title;
    private List<List<QuestionOptionFromAngularDto>> options;
    private List<QuestionFromAngularDto> questions;
}
