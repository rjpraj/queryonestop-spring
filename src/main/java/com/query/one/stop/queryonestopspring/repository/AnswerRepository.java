package com.query.one.stop.queryonestopspring.repository;

import com.query.one.stop.queryonestopspring.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answers,Long> {
}
