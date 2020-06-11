package com.iu.s1.board.qna;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;
import com.iu.s1.util.Pager;

@Service
@Transactional(rollbackOn = Exception.class)
public class QnaService {

	@Autowired
	private QnaRepository qnaRepository;
	@Value("board.qna.filePath")
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;
	private String filePath;

	public Page<QnaVO> boardList(Pager pager) throws Exception {

		pager.makeRow();
		Pageable pageable = PageRequest.of((int) pager.getStartRow(), pager.getPerPage(),
				Sort.by("ref").descending().and(Sort.by("step").ascending()));

		Page<QnaVO> ar = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);

		if (pager.getKind().equals("writer") || pager.getKind() == null) {
			ar = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);
		} else if (pager.getKind().equals("title")) {
			ar = qnaRepository.findByTitleContaining(pager.getSearch(), pageable);
		} else if (pager.getKind().equals("contents")) {
			ar = qnaRepository.findByContentsContaining(pager.getSearch(), pageable);
		}

		return ar;
	}

	public QnaVO boardWrite(QnaVO qnaVO) throws Exception {

		qnaVO = qnaRepository.save(qnaVO);
		qnaVO.setRef(qnaVO.getNum());
		return qnaRepository.save(qnaVO);

	}

	public void setInsert(QnaVO qnaVO, MultipartFile[] files) throws Exception {

		File file = pathGenerator.getUserResourceLoader(filePath);
		List<QnaFileVO> qnaFileVOs = new ArrayList<QnaFileVO>();

		for (MultipartFile multipartFile : files) {
			if (multipartFile.getSize() <= 0) {
				continue;
			}
			String fileName = fileManager.saveFileCopy(multipartFile, file);

			QnaFileVO qnaFileVO = new QnaFileVO();

			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(multipartFile.getOriginalFilename());
			qnaFileVO.setQnaVO(qnaVO);
			qnaFileVOs.add(qnaFileVO);

			System.out.println(fileName);
		}
		qnaVO.setQnaFileVOs(qnaFileVOs);
		qnaRepository.save(qnaVO);
		qnaVO.setRef(qnaVO.getNum());
		qnaRepository.save(qnaVO);
	}

}
