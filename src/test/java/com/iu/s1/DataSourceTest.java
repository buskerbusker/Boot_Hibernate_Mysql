package com.iu.s1;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataSourceTest {

	@Autowired
	private DataSource dataSource;

	@Test
	void dataSourcetest() throws Exception {
		assertNotNull(dataSource.getConnection());
	}

}
