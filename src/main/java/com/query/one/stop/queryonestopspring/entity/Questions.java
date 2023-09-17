package com.query.one.stop.queryonestopspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.query.one.stop.queryonestopspring.dto.QuestionDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob // used to tell this field is going to store large data
    @Column(name="body",length = 1024)
    private String body;

    private Date createdDate;

    @ElementCollection(targetClass = String.class)
    private List<String> tags;

    @ManyToOne(fetch = FetchType.LAZY,optional = false) // one user can post many questions
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public QuestionDto getQuestionDto(){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(id);
        questionDto.setTitle(title);
        questionDto.setBody(body);
        questionDto.setTags(tags);
        questionDto.setCreatedDate(createdDate);
        questionDto.setUserId(Long.valueOf(user.getId()));
        questionDto.setUsername(user.getName());
        return questionDto;
    }

}
