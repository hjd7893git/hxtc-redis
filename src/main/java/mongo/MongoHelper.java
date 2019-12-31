package mongo;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoHelper extends IllegalArgumentException {
    private static MongoDatabase mongoDatabase;
    public MongoHelper(String host, int port, String group) {
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient(host, port);
        // 连接到数据库
        mongoDatabase = mongoClient.getDatabase(group);
//        MongoCredential credential = MongoCredential.createCredential("user", "test", "pass".toCharArray());
        System.out.println("Connect to database successfully");
    }

    public  MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

}
