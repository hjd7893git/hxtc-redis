package mongo;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoHelper extends IllegalArgumentException {
    private static MongoDatabase mongoDatabase;
    public MongoHelper(String host, int port, String group) {
        // ���ӵ� mongodb ����
        MongoClient mongoClient = new MongoClient(host, port);
        // ���ӵ����ݿ�
        mongoDatabase = mongoClient.getDatabase(group);
//        MongoCredential credential = MongoCredential.createCredential("user", "test", "pass".toCharArray());
        System.out.println("Connect to database successfully");
    }

    public  MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

}
