package com.query.one.stop.queryonestopspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name="body",length=1024) // 1024 kb
    private String body;

    private Date createdDate;

    @ManyToOne(fetch=FetchType.LAZY,optional = false) // one user can post many question / answers
    @JoinColumn(name="user_id",nullable = false) // will create a join from user table with column user_id in answers table
    @OnDelete(action = OnDeleteAction.CASCADE) // if user deleted on it's record deleted inc quest answers
    @JsonIgnore
    private User user;

    @ManyToOne(fetch=FetchType.LAZY,optional = false) // one question can have many answers
    @JoinColumn(name="question_id",nullable = false) // will create a join from user table with column user_id in answers table
    @OnDelete(action = OnDeleteAction.CASCADE) // if user deleted on it's record deleted inc quest answers
    @JsonIgnore
    private Questions questions;

}
