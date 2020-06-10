package com.iu.s1.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	// 1. 폴더 생성
	// 2. 저장할 파일명 생성
	// 3. 파일 HDD 저장

	// 1. FileCopyUtils 클래스 사용
	public String saveByUtils(MultipartFile file, String path) throws Exception {
		File f = new File(path);
		if (!f.exists()) {
			// resources/memberUpload
			// resources/upload/member
			f.mkdirs();
		}

		// a. 저장할 파일명 생성
		String fileName = this.makeNameByUUID(file.getOriginalFilename());

		f = new File(f, fileName);
		// b. HDD에 저장
		FileCopyUtils.copy(file.getBytes(), f);

		return fileName;
	}

	// 2. MultipartFile
	public String saveByTransfer(MultipartFile file, String path) throws Exception {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}

		String fileName = this.makeNameByUUID(file.getOriginalFilename());

		f = new File(f, fileName);

		file.transferTo(f);

		return fileName;

	}

	// 3. OutputStream
	public String saveByStream(MultipartFile file, String path) throws Exception {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}

		String fileName = this.makeNameByUUID(file.getOriginalFilename());

		f = new File(f, fileName);

		FileOutputStream fs = new FileOutputStream(f);
		fs.write(file.getBytes());
		fs.close();
		return fileName;
	}

	private String makeNameByUUID(String name) {
		String result = UUID.randomUUID().toString();
		result = result + "_" + name;
		return result;
	}

	private String makeNameByTime(String name) {
		Calendar ca = Calendar.getInstance();
		Long l = ca.getTimeInMillis();

		String result = name.substring(0, name.indexOf("."));
		result = result + "_" + l;
		result = result + name.substring(name.lastIndexOf("."));

		return result;
	}

	// file Delete
	public int deleteFile(String fileName, String path) throws Exception {
		File file = new File(path, fileName);
		boolean check = false;
		int result = 0;
		if (file.exists()) {
			check = file.delete();
		}

		if (check) {
			result = 1;
		}

		return result;
	}

}
