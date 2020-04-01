package mongo;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

public class MongoHelper extends IllegalArgumentException {
    private static MongoDatabase mongoDatabase;
    public MongoHelper(String host, int port, int timeout, String group) {
        // 连接到 mongodb 服务

        List<ServerAddress> serverAddressList = new ArrayList<>();
        ServerAddress serverAddress = new ServerAddress(host, port);
        serverAddressList.add(serverAddress);


//        MongoClient mongoClient = new MongoClient(host, port);
        Builder build = new Builder();
        build.cursorFinalizerEnabled(true);
        // options.autoConnectRetry(true);// 自动重连true
//         options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
        build.connectionsPerHost(50);// 连接池设置为300个连接,默认为100
        /**设置与数据库建立连接时最长时间为1分钟*/
        build.connectTimeout(1000 * 60 * 1);
        build.serverSelectionTimeout(timeout); //设置超时时间

        /**一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为，此处为 2分钟
         * 如果超过 maxWaitTime 都没有获取到连接的话，该线程就会抛出 Exception
         * */
        build.maxWaitTime(1000 * 60 * 2);
        build.socketTimeout(0);// 套接字超时时间，0无限制
        build.threadsAllowedToBlockForConnectionMultiplier(50);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
        build.writeConcern(WriteConcern.SAFE);//
        build.build();
        MongoClientOptions mongoClientOptions = build.build();


        MongoClient mongoClient = new MongoClient(serverAddressList, mongoClientOptions);
        String connectPoint = mongoClient.getConnectPoint();
        System.out.println(connectPoint);

        // 连接到数据库
//        try{
            mongoDatabase = mongoClient.getDatabase(group);
//        }
//        MongoCredential credential = MongoCredential.createCredential("user", "test", "pass".toCharArray());
        System.out.println("Connect to MongoDBdatabase successfully....");
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

}
