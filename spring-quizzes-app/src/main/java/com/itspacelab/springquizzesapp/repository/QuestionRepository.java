package com.itspacelab.springquizzesapp.repository;

import com.itspacelab.springquizzesapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllByQuiz_Id(int id);
}
