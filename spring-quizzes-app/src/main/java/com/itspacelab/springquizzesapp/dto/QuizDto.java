package com.itspacelab.springquizzesapp.dto;

import com.itspacelab.springquizzesapp.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizDto {
    private int id;
    private LocalDateTime createdDateTime;
    private String title;
    private List<Question> questions;
}
