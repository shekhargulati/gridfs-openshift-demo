package com.gridfs.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;

@Configuration
public class MongoDBConfig {

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception{
		SimpleMongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new Mongo("localhost", 27017), "storage");
		return mongoDbFactory;
	}
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception{
		MongoDbFactory dbFactory = mongoDbFactory();
		MongoConverter converter = mongoConverter();
		GridFsTemplate gridFsTemplate = new GridFsTemplate(dbFactory, converter);
		return gridFsTemplate;
	}

	@Bean
	public MongoConverter mongoConverter() throws Exception{
		MongoMappingContext mappingContext = new MongoMappingContext();
		MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(mongoDbFactory(), mappingContext);
		return mappingMongoConverter;
	}

	
}
