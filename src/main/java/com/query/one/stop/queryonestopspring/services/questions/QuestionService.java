package com.query.one.stop.queryonestopspring.services.questions;

import com.query.one.stop.queryonestopspring.dto.AllQuestionResponseDto;
import com.query.one.stop.queryonestopspring.dto.QuestionDto;
import com.query.one.stop.queryonestopspring.dto.SingleQuestionDto;

public interface QuestionService {
    QuestionDto addQuestion(QuestionDto questionDto);

    AllQuestionResponseDto getAllQuestions(int pageNumber);

    SingleQuestionDto getQuestionById(Long questionId);
}
