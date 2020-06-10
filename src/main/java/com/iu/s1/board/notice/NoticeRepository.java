package com.iu.s1.board.notice;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {

	public int countByTitleContaining(String search);

	// select * from notice where num > 0 order by num desc
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(long num);

	// select * from notice where num between 6 and 10
	public List<NoticeVO> findByNumBetween(long num1, long num2);

	// select * from notice where title like ?? order by num desc;
	public List<NoticeVO> findByTitleLikeOrderByNumDesc(String param);

	public List<NoticeVO> findByTitleContaining(String search, Pageable pageable);

	public List<NoticeVO> findByWriterContaining(String search, Pageable pageable);

	public List<NoticeVO> findByContentsContaining(String search, Pageable pageable);
	
}
