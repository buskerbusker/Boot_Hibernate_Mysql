package com.iu.s1.board.qna;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.board.notice.NoticeFileVO;
import com.iu.s1.board.notice.NoticeVO;
import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;

@Service
@Transactional(rollbackOn = Exception.class)
public class QnaService {

	@Autowired
	private QnaRepository qnaRepository;
	@Value("${board.qna.filePath}")
	private String filePath;
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;

	public void boardList() throws Exception {

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

	public QnaVO boardReply(QnaVO qnaVO) throws Exception {

		QnaVO childVO = new QnaVO();
		childVO.setTitle(qnaVO.getTitle());
		childVO.setWriter(qnaVO.getWriter());
		childVO.setContents(qnaVO.getContents());

		// update
		// ref 가 부모의 ref와 같고 step이 부모의 step보다 큰것들을 찾아
		// step을 1씩 증가

		// 1. 부모의 정보 조회
		qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		List<QnaVO> ar = qnaRepository.findByRefAndStepGreterThan(qnaVO.getRef(), qnaVO.getStep());

		for (QnaVO q : ar) {
			q.setStep(q.getStep() + 1);
		}

		qnaRepository.saveAll(ar);

		// 자기자신의 ref는 부모의 ref
		// 자기자신의 step은 부모의 step+1
		// 자기자신의 depth는 부모의 depth + 1

		childVO.setRef(qnaVO.getRef());
		childVO.setStep(qnaVO.getStep() + 1);
		childVO.setDepth(qnaVO.getDepth() + 1);
		qnaRepository.save(childVO);

		return childVO;
	}

	public QnaVO boardSelect(QnaVO qnaVO) throws Exception {
		qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		qnaVO.setHit(qnaVO.getHit() + 1);
		return qnaRepository.save(qnaVO);

	}

	public boolean boardDelete(QnaVO qnaVO) throws Exception {

		qnaRepository.deleteById(qnaVO.getNum());
		return qnaRepository.existsById(qnaVO.getNum());

	}

}
