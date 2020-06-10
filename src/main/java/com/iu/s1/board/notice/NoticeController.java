package com.iu.s1.board.notice;

import java.lang.ProcessBuilder.Redirect;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.omg.CORBA.DynAnyPackage.TypeMismatch;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.s1.board.BoardVO;
import com.iu.s1.util.Pager;

@Controller
@RequestMapping("/notice/**/")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}

	@GetMapping("noticeWrite")
	public ModelAndView setInsert() throws Exception {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardWrite");
		mv.addObject("path", "Write");
		mv.addObject("boardVO", new BoardVO());

		return mv;
	}

	// springform 처리
	// 바인딩리설트는 검증하려는 것 바로 뒤에
	@PostMapping("noticeWrite")
	public ModelAndView setInsert(@Valid NoticeVO noticeVO, BindingResult bindingResult, RedirectAttributes rd,
			MultipartFile[] files) throws Exception {

		ModelAndView mv = new ModelAndView();

		if (bindingResult.hasErrors()) {
			mv.setViewName("board/boardWrite");
			mv.addObject("path", "Write");

		} else {
			noticeService.setInsert(noticeVO, files);
			mv.setViewName("redirect:./noticeList");
		}

		return mv;
	}

	@GetMapping("noticeList")
	// @PageableDefault(page = 0, size = 5, sort = { "num" }, direction =
	// Direction.DESC) Pageable pageable,@RequestParam(defaultValue = "20") String
	// search, @RequestParam(defaultValue = "title") String kind
	public ModelAndView getSelectList(Pager pager) throws Exception {

		ModelAndView mv = new ModelAndView();
		// Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"num");

		// List<NoticeVO> ar = noticeService.getSelectList(pageable, search, kind);
		List<NoticeVO> ar = noticeService.getSelectList(pager);
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");

		return mv;
	}

	@GetMapping("noticeSelect")
	public ModelAndView getSelectOne(NoticeVO noticeVO) throws Exception {

		ModelAndView mv = new ModelAndView();
		noticeVO = noticeService.getSelectOne(noticeVO);

		mv.addObject("vo", noticeVO);
		mv.setViewName("board/boardSelect");

		return mv;
	}

//	@GetMapping("noticeUpdate")
//	public ModelAndView setUpdate(ModelAndView mv, BoardVO boardVO) throws Exception {
//
//		boardVO = noticeService.getSelectOne(boardVO);
//
//		mv.addObject("vo", boardVO);
//		mv.addObject("path", "Update");
//		mv.setViewName("board/boardWrite");
//
//		return mv;
//	}
//
//	@PostMapping("noticeUpdate")
//	public ModelAndView setUpdate(BoardVO boardVO, RedirectAttributes rd) throws Exception {
//
//		ModelAndView mv = new ModelAndView();
//		int result = noticeService.setUpdate(boardVO);
//
//		mv.setViewName("redirect:./noticeList");
//		rd.addFlashAttribute("result", result);
//
//		return mv;
//	}
//
//	@GetMapping("noticeDelete")
//	public ModelAndView setDelete(BoardVO boardVO, RedirectAttributes rd) throws Exception {
//
//		ModelAndView mv = new ModelAndView();
//		int result = noticeService.setDelete(boardVO);
//
//		rd.addFlashAttribute("result", result);
//		mv.setViewName("redirect:./noticeList");
//
//		return mv;
//	}

}
