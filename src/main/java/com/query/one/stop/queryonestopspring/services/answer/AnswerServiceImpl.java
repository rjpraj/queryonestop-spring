package com.query.one.stop.queryonestopspring.services.answer;


import com.query.one.stop.queryonestopspring.dto.AnswerDto;
import com.query.one.stop.queryonestopspring.entity.Answers;
import com.query.one.stop.queryonestopspring.entity.Questions;
import com.query.one.stop.queryonestopspring.entity.User;
import com.query.one.stop.queryonestopspring.repository.AnswerRepository;
import com.query.one.stop.queryonestopspring.repository.QuestionRepository;
import com.query.one.stop.queryonestopspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService{

    private final UserRepository userRepository;

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }


    @Override
    public AnswerDto postAnswer(AnswerDto answerDto) {
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        Optional<Questions> optionalQuestions = questionRepository.findById(answerDto.getQuestionId());
        if(optionalUser.isPresent() && optionalQuestions.isPresent()){
            Answers answers = new Answers();
            answers.setBody(answerDto.getBody());
            answers.setCreatedDate(new Date());
            answers.setUser(optionalUser.get());
            answers.setQuestions(optionalQuestions.get());
            Answers createdAnswers = answerRepository.save(answers);
            AnswerDto createdAnswerDto = new AnswerDto();
            createdAnswerDto.setId(createdAnswers.getId());
            createdAnswerDto.setQuestionId(createdAnswerDto.getQuestionId());
            return createdAnswerDto;
        }
        return null;
    }
}
