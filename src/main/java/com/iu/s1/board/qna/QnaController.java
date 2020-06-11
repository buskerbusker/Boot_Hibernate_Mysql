package com.iu.s1.board.qna;

import javax.validation.Valid;

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

	private QnaService qnaService;

	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
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
