package com.iu.s1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;

	private NoticeVO noticeVO;
	private List<NoticeFileVO> noticeFileVO;

	// @BeforeEach
	public void befoerEach() {
		noticeVO = new NoticeVO();
		noticeVO.setTitle("title2");
		noticeVO.setWriter("writer2");
		noticeVO.setContents("contents2");
		List<NoticeFileVO> noticeFileVOs = new ArrayList<NoticeFileVO>();
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("filename1");
		noticeFileVO.setOriName("oriname1");

		noticeFileVO.setNoticeVO(noticeVO);
		noticeFileVOs.add(noticeFileVO);

		noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("filename2");
		noticeFileVO.setOriName("oriname2");

		noticeFileVO.setNoticeVO(noticeVO);
		noticeFileVOs.add(noticeFileVO);

		noticeVO.setNoticeFileVOs(noticeFileVOs);

	}

	// insert
	//@Test
	public void inserTest() throws Exception {
		
		for(int i = 0; i<100; i++) {
			noticeVO = new NoticeVO();
			noticeVO.setTitle("title"+i);
			noticeVO.setContents("contents"+i);
			noticeVO.setWriter("writer"+i);
			
			noticeVO = noticeRepository.save(noticeVO);
		}

		assertNotNull(noticeVO);
	}

	// update
	// @Test
	public void updateTest() throws Exception {

		noticeVO.setNum(2);
		noticeVO.setTitle("update Title");

		noticeVO = noticeRepository.save(noticeVO);
		assertNotNull(noticeVO);

	}

	// delete
	// @Test
	public void deleteTest() throws Exception {
		noticeRepository.deleteById(3L);
		boolean check = noticeRepository.existsById(3L);
		assertEquals(false, check);
	}

	// selectList
	// @Test
	public void selectListTest() throws Exception {
		List<NoticeVO> ar = noticeRepository.findAll();
		for (NoticeVO noticeVO : ar) {
			System.out.println(noticeVO.getTitle());
		}
		assertNotEquals(0, ar.size());
	}

	// selectOne
	// @Test
	public void selectOneTest() throws Exception {
		Optional<NoticeVO> opt = noticeRepository.findById(6L);
		// if (opt.isPresent()) null인지 먼저 판단하는 코드
		noticeVO = opt.get();
		System.out.println(noticeVO.getNoticeFileVOs().get(0).getFileName());
		assertNotNull(noticeVO);
	}

	// @Test
	public void customTest() {
		List<NoticeVO> ar = noticeRepository.findByNumGreaterThanOrderByNumDesc(0L);
		for (NoticeVO noticeVO : ar) {
			System.out.println(noticeVO.getNum());
		}
	}

	//@Test
	public void customTest2() {
		List<NoticeVO> ar = noticeRepository.findByNumBetween(6L, 10L);
		for (NoticeVO noticeVO : ar) {
			System.out.println(noticeVO.getNum());
		}
	}
	
	//@Test
	public void customTest3() {
		List<NoticeVO> ar = noticeRepository.findByTitleLikeOrderByNumDesc("123");
		for (NoticeVO noticeVO : ar) {
			System.out.println(noticeVO.getNum());
		}
	}

}
