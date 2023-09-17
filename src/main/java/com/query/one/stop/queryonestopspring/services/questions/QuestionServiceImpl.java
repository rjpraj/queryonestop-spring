package com.query.one.stop.queryonestopspring.services.questions;

import com.query.one.stop.queryonestopspring.dto.AllQuestionResponseDto;
import com.query.one.stop.queryonestopspring.dto.QuestionDto;
import com.query.one.stop.queryonestopspring.dto.SingleQuestionDto;
import com.query.one.stop.queryonestopspring.entity.Questions;
import com.query.one.stop.queryonestopspring.entity.User;
import com.query.one.stop.queryonestopspring.repository.QuestionRepository;
import com.query.one.stop.queryonestopspring.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public static final int SEARCH_RESULT_PER_PAGE = 5;

    public QuestionServiceImpl(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionDto addQuestion(QuestionDto questionDto) {
        Optional<User> optionalUser = userRepository.findById(questionDto.getUserId());
        if(optionalUser.isPresent()){ // if user is present we will save question and return question dto
            Questions question = new Questions();
            question.setTitle(questionDto.getTitle());
            question.setBody(questionDto.getBody());
            question.setTags(questionDto.getTags());
            question.setCreatedDate(new Date());
            question.setUser(optionalUser.get());

            Questions createdQuestion = questionRepository.save(question);
            QuestionDto createdQuestionDto = new QuestionDto();
            createdQuestionDto.setId(createdQuestion.getId());
            createdQuestionDto.setTitle(createdQuestion.getTitle());
            return createdQuestionDto;
        }
        return null; // if optional user not present return null
    }

    @Override
    public AllQuestionResponseDto getAllQuestions(int pageNumber) {
        Pageable paging = PageRequest.of(pageNumber,SEARCH_RESULT_PER_PAGE);
        Page<Questions> questionsPage = questionRepository.findAll(paging);
        AllQuestionResponseDto allQuestionResponseDto = new AllQuestionResponseDto();
        allQuestionResponseDto.setQuestionDtoList(questionsPage.getContent()
                .stream().map(Questions::getQuestionDto).collect(Collectors.toList()));
        allQuestionResponseDto.setPageNumber(questionsPage.getPageable().getPageNumber());
        allQuestionResponseDto.setTotalPages(questionsPage.getTotalPages());
        return allQuestionResponseDto;
    }

    @Override
    public SingleQuestionDto getQuestionById(Long questionId) {
        Optional<Questions> optionalQuestions = questionRepository.findById(questionId);
        SingleQuestionDto singleQuestionDto = new SingleQuestionDto();
        if(optionalQuestions.isPresent()){
            Questions questions = new Questions();
            singleQuestionDto.setQuestionDto(questions.getQuestionDto());
            return singleQuestionDto;
        }
        return null;
    }
}
