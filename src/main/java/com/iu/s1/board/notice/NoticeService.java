package com.iu.s1.board.notice;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;
import com.iu.s1.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;

	@Value("${board.notice.filePath}")
	private String filePath;

	public void setInsert(NoticeVO noticeVO, MultipartFile[] files) throws Exception {

		File file = pathGenerator.getUserResourceLoader(filePath);
		List<NoticeFileVO> noticeFileVOs = new ArrayList<NoticeFileVO>();

		for (MultipartFile multipartFile : files) {
			if (multipartFile.getSize() <= 0) {
				continue;
			}
			String fileName = fileManager.saveFileCopy(multipartFile, file);

			NoticeFileVO noticeFileVO = new NoticeFileVO();

			noticeFileVO.setFileName(fileName);
			noticeFileVO.setOriName(multipartFile.getOriginalFilename());
			noticeFileVO.setNoticeVO(noticeVO);
			noticeFileVOs.add(noticeFileVO);

			System.out.println(fileName);
		}
		noticeVO.setNoticeFileVOs(noticeFileVOs);
		noticeRepository.save(noticeVO);
	}

	public NoticeVO getSelectOne(NoticeVO noticeVO) throws Exception {
		Optional<NoticeVO> opt = noticeRepository.findById(noticeVO.getNum());
		noticeVO = opt.get();
		return noticeVO;
	}

	// Pageable pageable, String search, String kind
	public List<NoticeVO> getSelectList(Pager pager) throws Exception {
		List<NoticeVO> ar = new ArrayList<NoticeVO>();
		pager.makeRow();
		pager.makePage(noticeRepository.count());
		Pageable pageable = PageRequest.of((int) pager.getStartRow(), pager.getPerPage(), Sort.Direction.DESC, "num");
		ar = noticeRepository.findAll();

		if (pager.getKind().equals("bw")) {
			ar = noticeRepository.findByWriterContaining(pager.getSearch(), pageable);
		} else if (pager.getKind().equals("bt")) {
			ar = noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
		} else if (pager.getKind().equals("bc")) {
			ar = noticeRepository.findByContentsContaining(pager.getSearch(), pageable);
		}

//		if (kind.equals("title")) {
//			ar = noticeRepository.findByTitleContaining(search, pageable);
//		} else if (kind.equals("writer")) {
//			ar = noticeRepository.findByWriterContaining(search, pageable);
//		} else if (kind.equals("contents")) {
//			ar = noticeRepository.findByContentsContaining(search, pageable);
//		}
		return ar;
	}

//	public long boardCount(Pager pager) throws Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	public NoticeFileVO fileDown(NoticeFileVO noticeFileVO) throws Exception {
//		return noticeFileRepository.fileDown(noticeFileVO);
//	}

}
