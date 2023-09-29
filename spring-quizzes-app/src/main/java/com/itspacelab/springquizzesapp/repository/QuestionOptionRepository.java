package com.itspacelab.springquizzesapp.repository;

import com.itspacelab.springquizzesapp.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer> {

    List<QuestionOption> findAllByQuestionId(int id);
}
