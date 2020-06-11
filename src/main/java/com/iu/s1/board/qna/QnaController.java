package com.iu.s1.board.qna;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/qna/**")
public class QnaController {

	@Autowired
	private QnaService qnaService;

	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}

	@GetMapping("qnaList")
	public ModelAndView boardList(
			@PageableDefault(size = 10, page = 0, direction = Direction.DESC, sort = { "num" }) Pageable pageable)
			throws Exception {

		ModelAndView mv = new ModelAndView();

		Page<QnaVO> page = qnaService.boardList(pageable);

		System.out.println(page.getContent().size());
		System.out.println(page.getSize());
		// 총글의 갯수
		System.out.println(page.getTotalElements());
		// 총페이지의 수
		System.out.println(page.getTotalPages());
		// 다음 및 이전페이지 true false
		System.out.println(page.hasNext());
		System.out.println(page.hasPrevious());

		mv.addObject("page", page);
		mv.setViewName("board/boardList");

		return mv;
	}

	@GetMapping("qnaWrite")
	public ModelAndView boardWrite() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardVO", new QnaVO());
		mv.setViewName("board/boardWrite");
		return mv;
	}

	@PostMapping("qnaWrite")
	public ModelAndView setInsert(@Valid QnaVO qnaVO, BindingResult bindingResult, RedirectAttributes rd,
			MultipartFile[] files) throws Exception {

		ModelAndView mv = new ModelAndView();

		if (bindingResult.hasErrors()) {
			mv.setViewName("board/boardWrite");
			mv.addObject("path", "Write");

		} else {
			qnaService.setInsert(qnaVO, files);
			mv.setViewName("redirect:./qnaList");
		}

		return mv;
	}

}
