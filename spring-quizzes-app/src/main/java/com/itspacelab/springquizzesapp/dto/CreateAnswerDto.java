package com.itspacelab.springquizzesapp.dto;

import com.itspacelab.springquizzesapp.entity.QuestionOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnswerDto {
    private List<List<QuestionOption>> answers;
}
