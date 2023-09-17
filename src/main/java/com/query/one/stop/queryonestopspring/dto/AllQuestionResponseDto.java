package com.query.one.stop.queryonestopspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllQuestionResponseDto {
    private List<QuestionDto> questionDtoList;

    private Integer totalPages;

    private Integer pageNumber;

}
