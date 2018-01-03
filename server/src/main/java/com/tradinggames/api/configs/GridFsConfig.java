//package com.tradinggames.api.configs;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoCredential;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;
//
//@Configuration
//public class GridFsConfig extends AbstractMongoConfiguration{
//
//    @Bean
//    public GridFsTemplate gridFsTemplate() throws Exception {
//        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return "tradinggames";
//    }
//
//    @Override
//    public MongoClient mongoClient() {
//        return new MongoClient("mongodb://cooluser:coolpass@ds121665.mlab.com:21665");
//    }
//}
