package com.iu.s1.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaRepositoryTest {

	@Autowired
	private QnaRepository qnaRepository;
	@Autowired
	private QnaService qnaService;

	@Test
	public void inserTest() {

		QnaVO qnaVO = new QnaVO();
		qnaVO.setTitle("title2");
		qnaVO.setWriter("writer2");
		qnaVO.setContents("contents2");

		qnaVO = qnaRepository.save(qnaVO);

		qnaVO.setRef(qnaVO.getNum());
		qnaVO = qnaRepository.save(qnaVO);

		assertNotNull(qnaVO);

	}

	@Test
	public void insertTest2() {

		QnaVO qnaVO = new QnaVO();
		qnaVO.setTitle("title2");
		qnaVO.setWriter("writer2");
		qnaVO.setContents("contents2");

		qnaVO = qnaService.setInsert(qnaVO, files);

		assertNotNull(qnaVO);

	}
}
