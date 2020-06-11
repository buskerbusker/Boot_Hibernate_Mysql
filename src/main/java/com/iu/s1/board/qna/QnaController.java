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
			@PageableDefault(size = 10, page = 0, direction = Direction.DESC, sort = { "num" }) Pageable pageable,
			String kind, String search) throws Exception {

		ModelAndView mv = new ModelAndView();

		System.out.println(search);
		System.out.println(kind);

		Page<QnaVO> page = qnaService.boardList(pageable,search,kind);

		// 컬럼 갯수
		System.out.println(page.getContent().size());
		// 한페이지 글갯수. 위의 size
		System.out.println(page.getSize());
		// 총글의 갯수
		System.out.println(page.getTotalElements());
		// 총페이지의 수
		System.out.println(page.getTotalPages());
		// 다음 및 이전페이지 true false
		System.out.println(page.hasNext());
		System.out.println(page.hasPrevious());
		// 페이지 번호
		System.out.println(page.getNumber());
		System.out.println(page.isFirst());
		System.out.println(page.isLast());

		mv.addObject("page", page);
		mv.setViewName("board/boardList");

//		if (page.getNumber() > page.getTotalPages() || page.getNumber() < 0) {
//			mv.setViewName("board/boardList?page=0");
//		}

		return mv;
	}

	@GetMapping("qnaWrite")
	public ModelAndView boardWrite() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardVO", new QnaVO());
		mv.addObject("path", "Write");
		mv.setViewName("board/boardWrite");
		return mv;
	}

	@PostMapping("qnaWrite")
	public ModelAndView boardWrite(QnaVO qnaVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.boardWrite(qnaVO);
		mv.setViewName("redirect:./qnaList");

		return mv;
	}

}
