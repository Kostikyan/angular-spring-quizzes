package com.itspacelab.springquizzesapp.entity;

import com.itspacelab.springquizzesapp.entity.types.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;

    private String title;
    private String score;

    @ManyToOne
    private Quiz quiz;
}
