package com.query.one.stop.queryonestopspring.repository;

import com.query.one.stop.queryonestopspring.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {
}
