package com.iu.s1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.ExampleMatcher;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

//	@Test
//	void loginTest() {
//		MemberVO memberVO = new MemberVO();
//		memberVO.setId("iu5");
//		memberVO.setPw("1234");
//		memberVO = memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
//		System.out.println(memberVO.getMemberFileVO().getFileName());
//		System.out.println(memberVO.getMemberFileVO().getOriName());
//		assertNotNull(memberVO);
//	}

//	@Test
//	void idCheck() {
//		MemberVO memberVO = new MemberVO();
//		boolean check = memberRepository.existsById("iu");
//		System.out.println(check);
//	}

//	@Test
//	void insertTest() {
//		MemberVO memberVO = new MemberVO();
//		memberVO.setId("iu5");
//		memberVO.setPw("iu5");
//		memberVO.setName("iu5");
//		memberVO.setEmail("asd@asd5.com");
//		memberVO.setPhone("015-1234-5678");
//
//		MemberFileVO memberFileVO = new MemberFileVO();
//		memberFileVO.setFileName("fileName");
//		memberFileVO.setOriName("oriName");
//
//		// member에는 인서트가 되지만 file에는 되지 않음
//		memberVO.setMemberFiileVO(memberFileVO);
//		memberRepository.save(memberVO);

//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setId("iu6");
//		memberVO2.setPw("iu6");
//		memberVO2.setName("iu6");
//		memberVO2.setEmail("asd@asd6.com");
//		memberVO2.setPhone("016-1234-5678");
//
//		List<MemberVO> ar = new ArrayList<>();
//
//		ar.add(memberVO);
//		ar.add(memberVO2);
//
//		ar = memberRepository.saveAll(ar);
//
//		assertNotNull(ar);

//	}

	//@Test
	public void insertTest2() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu5");
		memberVO.setPw("1234");
		memberVO.setName("iu5");
		memberVO.setEmail("asd@asd5.com");
		memberVO.setPhone("015-1234-5678");
		memberVO.setAge(12);

		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("fileName");
		memberFileVO.setOriName("oriName");

		// member에는 인서트가 되지만 file에는 되지 않음
		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);
		memberRepository.save(memberVO);
	}

	// @Test
	void updateTest() {

		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu5");
		memberVO.setPw("123456");
		memberVO.setName("iu5");
		memberVO.setEmail("asd@asd5.com");
		memberVO.setPhone("015-1234-5678");
		memberVO.setAge(15);

		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("Change 2 File Name");
		memberFileVO.setOriName("Change 2 Ori Name");
		memberFileVO.setFileNum(2);

		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);

		memberVO = memberRepository.save(memberVO);

		assertNotNull(memberVO);
	}

	//@Test
	void deleteTest() {

		memberRepository.deleteById("iu5");
		// iu3 iu4 지우기
//		MemberVO memberVO = new MemberVO();
//		MemberVO memberVO2 = new MemberVO();
//
//		List<MemberVO> ar = new ArrayList<>();
//
//		memberVO.setId("iu3");
//		memberVO2.setId("iu4");
//
//		ar.add(memberVO);
//		ar.add(memberVO2);
//
//		memberRepository.deleteAll(ar);
//
//		assertNotNull(ar);

	}

}
