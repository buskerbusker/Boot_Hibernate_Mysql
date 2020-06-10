package com.iu.s1.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**/")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("memberLogin")
	public void memberLogin(@CookieValue(value = "cId", required = false) String cId, Model model) {
		System.out.println(cId);
		// model.addAttribute("cId", cId);
	}

	@PostMapping("memberLogin")
	public ModelAndView memberLogin(ModelAndView mv, String remember, MemberVO memberVO, HttpSession session,
			HttpServletResponse response) throws Exception {
		Cookie cookie = new Cookie("cId", "");

		if (remember != null) {
			/* cookie = new Cookie("cId", memberVO.getId()); */
			cookie.setValue(memberVO.getId());
		}

		/* cookie.setMaxAge(0); */
		/* cookie.setValue(newValue); */
		response.addCookie(cookie);

		memberVO = memberService.memberLogin(memberVO);
		System.out.println(memberVO);
		if (memberVO != null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		} else {
			mv.setViewName("redirect:../");
			// mv.addObject("result", "Login Fail");
			// mv.addObject("path", "./memberJoin");
			// mv.setViewName("common/result");
		}

		// 로그인 성공이면 index
		// 로그인 실패 하면 로그인 실패 alert login form 이동

		return mv;
	}

	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:../";
	}

	@GetMapping("memberJoin")
	public ModelAndView memberJoin() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberVO", new MemberVO());
		// mv.addObject("file", new MemberVO());
		mv.setViewName("member/memberJoin");
		return mv;
	}

	@PostMapping("memberJoin")
	public ModelAndView memberJoin(@Valid MemberVO memberVO, BindingResult bindingResult, MultipartFile avatar,
			HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("member/memberLogin");

		boolean result = memberService.memberError(memberVO, bindingResult);

		if (result) {
			mv.setViewName("member/memberJoin");
		} else {
			memberService.memberJoin(memberVO, avatar, session);
			mv.setViewName("member/memberLogin");
		}

//		if (bindingResult.hasErrors()) {
//			mv.setViewName("member/memberJoin");
//		} else {
//			mv.setViewName("member/memberLogin");
//		}

		return mv;
	}

	@GetMapping("memberDelete")
	public ModelAndView memberDelete(HttpSession session) throws Exception {

		ModelAndView mv = new ModelAndView();
		String id = ((MemberVO) session.getAttribute("member")).getId();
		memberService.memberDelete(id);

		mv.setViewName("redirect:./memberLogin");

		return mv;
	}

	@GetMapping("memberUpdate")
	public ModelAndView memberUpdate(MemberVO memberVO, HttpSession session) throws Exception {

		ModelAndView mv = new ModelAndView();
		String id = ((MemberVO) session.getAttribute("member")).getId();

		memberVO.setId(id);
		memberVO.setAge(((MemberVO) session.getAttribute("member")).getAge());
		memberVO.setEmail(((MemberVO) session.getAttribute("member")).getEmail());
		memberVO.setName(((MemberVO) session.getAttribute("member")).getName());
		memberVO.setPhone(((MemberVO) session.getAttribute("member")).getPhone());
		memberVO.setPw(((MemberVO) session.getAttribute("member")).getPw());
		// memberVO.setPwCheck(((MemberVO)
		// session.getAttribute("member")).getPwCheck());
		mv.addObject("memberVO", memberVO);
		mv.setViewName("./member/memberUpdate");

		return mv;

	}

	@PostMapping("memberUpdate")
	public ModelAndView memberUpdate(@Valid MemberVO memberVO, BindingResult bindingResult, HttpSession session)
			throws Exception {

		ModelAndView mv = new ModelAndView();

		String id = ((MemberVO) session.getAttribute("member")).getId();
		System.out.println(id);

		// boolean result = memberService.memberError(memberVO, bindingResult);

//		if (result) {
//			mv.setViewName("member/memberJoin");
//		} else {
		memberVO.setId(id);
		memberService.memberUpdate(memberVO);
		mv.setViewName("member/memberLogin");
		// }

		return mv;

	}

}
