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

	@GetMapping("qnaReply")

	public ModelAndView boardUpdate(QnaVO qnaVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("path", "Reply");
		mv.addObject("boardVO", qnaVO);
		mv.setViewName("board/boardWrite");
		return mv;
	}

	@PostMapping("qnaReply")
	public ModelAndView boardReply(QnaVO qnaVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.boardReply(qnaVO);
		mv.setViewName("redirect:./qnaList");

		return mv;
	}

	// 조회수 업데이트
	@GetMapping("qnaSelect")
	public ModelAndView boardSelect(QnaVO qnaVO) throws Exception {
		ModelAndView mv = new ModelAndView();

		qnaVO = qnaService.boardSelect(qnaVO);

		return mv;
	}

	// 삭제
	@GetMapping("qnaDelete")
	public ModelAndView boardDelete(QnaVO qnaVO) throws Exception {
		ModelAndView mv = new ModelAndView();

		boolean result = qnaService.boardDelete(qnaVO);

		if (!result) {

		}
		mv.setViewName("redirect:./qnaList");
		return mv;
	}

}
