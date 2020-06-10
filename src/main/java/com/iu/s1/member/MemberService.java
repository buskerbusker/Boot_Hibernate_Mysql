package com.iu.s1.member;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;
import com.iu.s1.util.FileSaver;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;
	@Autowired
	private FileSaver fileSaver;

	@Value("${member.filePath}")
	private String filePath;

	// 회원가입
	public MemberVO memberJoin(MemberVO memberVO, MultipartFile avatar, HttpSession session) throws Exception {

		String path = session.getServletContext().getRealPath("/resources/memberUpload");
		System.out.println(path);
		String fileName = fileSaver.saveByTransfer(avatar, path);

		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName(fileName);
		memberFileVO.setOriName(avatar.getOriginalFilename());

		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);

		memberVO = memberRepository.save(memberVO);
		return memberVO;
	}

	// 로그인
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
	}

	// 회원탈퇴
	public void memberDelete(String id) throws Exception {
		memberRepository.deleteById(id);
	}

	// 회원정보수정
	public MemberVO memberUpdate(MemberVO memberVO) throws Exception {
		memberVO = memberRepository.save(memberVO);
		return memberVO;
	}

	// 검증메서드 추가
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult) throws Exception {

		boolean result = false;

		// 1. 기본 제공 검증
		// result = bindingResult.hasErrors();
		if (bindingResult.hasErrors()) {
			result = true;
		}

		// 2. pw 일치하는지 검증
		if (!memberVO.getPw().equals(memberVO.getPwCheck())) {
			bindingResult.rejectValue("pwCheck", "memberVO.pw.notSame");
			result = true;
		}

		// 3. ID 중복 확인 여부

		if (memberRepository.existsById(memberVO.getId())) {
			bindingResult.rejectValue("id", "memberVO.id.same");
			result = true;
		}
		return result;
	}

}
