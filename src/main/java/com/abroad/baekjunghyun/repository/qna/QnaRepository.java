package com.abroad.baekjunghyun.repository.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abroad.baekjunghyun.model.qna.Board;

public interface QnaRepository extends JpaRepository<Board, Integer>{

}
