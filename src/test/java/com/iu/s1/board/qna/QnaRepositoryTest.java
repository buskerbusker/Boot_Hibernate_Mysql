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

		for (int i = 0; i < 100; i++) {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setTitle("title"+i);
			qnaVO.setWriter("writer"+i);
			qnaVO.setContents("contents"+i);

			qnaVO = qnaRepository.save(qnaVO);

			qnaVO.setRef(qnaVO.getNum());
			qnaVO = qnaRepository.save(qnaVO);

		}

		//assertNotNull(qnaVO);

	}
}
