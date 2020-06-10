package com.iu.s1.util;

import java.io.File;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.iu.s1.member.MemberFileVO;

@Component
public class FileDown extends AbstractView {

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		// 저장 경로 확인
		String path = "classpath:/static/";
		path = path + (String) model.get("path");
		MemberFileVO boardFileVO = (MemberFileVO) model.get("fileVO");

		path = path + boardFileVO.getFileName();

		File file = resourceLoader.getResource(path).getFile();

		// 한글 처리
		response.setCharacterEncoding("UTF-8");

		// 총 파일의 크기
		response.setContentLengthLong(file.length());

		// 다운로드시 파일 이름을 인코딩
		String fileName = URLEncoder.encode(boardFileVO.getOriName(), "UTF-8");

		// header 설정
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");

		// HDD에서 파일을 읽고
		FileInputStream fi = new FileInputStream(file);
		// Client로 전송 준비
		OutputStream os = response.getOutputStream();
		// 전송
		FileCopyUtils.copy(fi, os);

		os.close();
		fi.close();

	}

}
