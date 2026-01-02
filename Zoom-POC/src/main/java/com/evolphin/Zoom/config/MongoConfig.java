// package com.evolphin.Zoom.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.mongodb.MongoDatabaseFactory;
// import org.springframework.data.mongodb.MongoTransactionManager;
// import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

// @Configuration
// public class MongoConfig {

//     @Value("${spring.data.mongodb.uri}")
//     private String mongoUri;

//     @Bean
//     public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
//         return new MongoTransactionManager(dbFactory);
//     }

//     @Bean
//     public MongoDatabaseFactory mongoDatabaseFactory() {
//         return new SimpleMongoClientDatabaseFactory(mongoUri);
//     }
// }
