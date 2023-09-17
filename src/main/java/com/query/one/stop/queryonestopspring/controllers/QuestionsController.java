package com.query.one.stop.queryonestopspring.controllers;

import com.query.one.stop.queryonestopspring.dto.AllQuestionResponseDto;
import com.query.one.stop.queryonestopspring.dto.QuestionDto;
import com.query.one.stop.queryonestopspring.dto.SingleQuestionDto;
import com.query.one.stop.queryonestopspring.services.questions.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuestionsController {

    private final QuestionService questionService; // questions are final

    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/questions")
    public ResponseEntity<?> postQuestion(@RequestBody QuestionDto questionDto){
        System.out.println(questionDto.getBody() + " " + questionDto.getUserId());
        QuestionDto createdQuestionDto = questionService.addQuestion(questionDto);
        System.out.println(questionDto.getBody() + " " + questionDto.getUserId());
        if(createdQuestionDto ==  null) return new ResponseEntity<>("Something went Wrong", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestionDto);
    }

    @GetMapping("/questions/{pageNumber}")
    public ResponseEntity<AllQuestionResponseDto> getAllQuestions(@PathVariable int pageNumber){
        AllQuestionResponseDto allQuestionResponseDto = questionService.getAllQuestions(pageNumber);
        return ResponseEntity.ok(allQuestionResponseDto);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long questionId){
        SingleQuestionDto singleQuestionDto = questionService.getQuestionById(questionId);
        if(singleQuestionDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(singleQuestionDto); // return 200 ok response for get
    }

}
