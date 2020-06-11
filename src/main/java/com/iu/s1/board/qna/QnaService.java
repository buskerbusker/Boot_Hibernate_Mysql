package com.iu.s1.board.qna;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;

@Service
public class QnaService {

	@Autowired
	private QnaRepository qnaRepository;
	@Value("${board.qna.filePath}")
	private String filePath;
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;

	public Page<QnaVO> boardList(Pageable pageable) throws Exception {

		return qnaRepository.findAll(pageable);
	}

	public QnaVO boardWrite(QnaVO qnaVO) throws Exception {

		qnaVO = qnaRepository.save(qnaVO);
		qnaVO.setRef(qnaVO.getRef());
		qnaVO = qnaRepository.save(qnaVO);
		return qnaVO;

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
