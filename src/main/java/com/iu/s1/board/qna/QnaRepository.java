package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnaVO, Long> {
	
	public List<QnaVO> findByRefAndStepGreterThan(long ref,long step);

}
