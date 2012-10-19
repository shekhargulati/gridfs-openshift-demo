package com.gridfs.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
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
	public MongoDbFactory mongoDbFactory() throws Exception {
		String openshiftMongoDbHost = System.getenv("OPENSHIFT_NOSQL_DB_HOST");
		int openshiftMongoDbPort = Integer.parseInt(System
				.getenv("OPENSHIFT_NOSQL_DB_PORT"));
		String username = System.getenv("OPENSHIFT_NOSQL_DB_USERNAME");
		String password = System.getenv("OPENSHIFT_NOSQL_DB_PASSWORD");
		Mongo mongo = new Mongo(openshiftMongoDbHost, openshiftMongoDbPort);
		UserCredentials userCredentials = new UserCredentials(username,
				password);
		String databaseName = "gridfs";
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo,
				databaseName, userCredentials);
		return mongoDbFactory;
	}
	
	/*@Bean
	public MongoDbFactory mongoDbFactory() throws Exception{
		SimpleMongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new Mongo("localhost", 27017), "storage");
		return mongoDbFactory;
	}*/
	
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
