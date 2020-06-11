package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iu.s1.board.notice.NoticeVO;

public interface QnaRepository extends JpaRepository<QnaVO, Long> {

	public Page<QnaVO> findByTitleContaining(String search, Pageable pageable);

	public Page<QnaVO> findByWriterContaining(String search, Pageable pageable);

	public Page<QnaVO> findByContentsContaining(String search, Pageable pageable);
}
