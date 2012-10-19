package com.gridfs.demo.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MongoDBConfig.class)
public class MongoDBConfigTest {

	@Autowired
	GridFsTemplate gridFsTemplate;
	
	@Test
	public void shouldCreateInstanceOfGridFsTemplate() {
		assertNotNull(gridFsTemplate);
		InputStream inputStream = this.getClass().getResourceAsStream("/MongoDB.png");
		GridFSFile gridFSFile = gridFsTemplate.store(inputStream, "MongoDB.png");
		
		List<GridFSDBFile> files = gridFsTemplate.find(null);
		
		assertEquals(1, files.size());
		
		gridFsTemplate.delete(null);
		
	}

}
