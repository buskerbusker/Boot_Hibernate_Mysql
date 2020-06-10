package com.iu.s1.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class FilePathGenerator {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ServletContext servletContext;

	// static/upload/notice
	// static/upload/qna
	public File getUserResourceLoader(String path) throws Exception {

		// ResourceLoader
		// classes 경로를 받아오기 위해 사용
		// 생성하려는 디렉토리가 없으면 에러를 발생
		// Default로 만들어진 static 경로를 이용해서 File객체를 생성
		// 하위 디렉토리를 child로 사용해 디렉토리 생성

		String defaultPath = "classpath:/static/";

		File file = resourceLoader.getResource(defaultPath).getFile();

		file = new File(file, path);

		if (!file.exists()) {
			file.mkdirs();
		}

		System.out.println(file.getAbsolutePath());
		return file;
	}

	public File getUseClassPathResouce(String path) throws Exception {

		// classpathresource
		// classes 경로를 받아오기 위해 사용
		// resourceloader와 같음
		// 시작 경로에 있는 classpath 를 제외하고 동일함
		// 개발자가 직접 객체를 생성

		String defaultPath = "static";

		ClassPathResource classPathResource = new ClassPathResource(defaultPath);

		File file = classPathResource.getFile();

		file = new File(file, path);

		if (!file.exists()) {
			file.mkdirs();
		}

		System.out.println(file.getAbsolutePath());
		return file;

	}

	public File getUseServletContext(String path) throws Exception {

		// Servlet Context
		// classpath가 아니라 webapp 하위에 만들어짐
		// 생성할 디렉토리가 static 이라면
		// webapp 하위에 static 폴더가 하나더 생성

		path = servletContext.getRealPath(path);
		File file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}

		System.out.println(path);

		return file;
	}

}
