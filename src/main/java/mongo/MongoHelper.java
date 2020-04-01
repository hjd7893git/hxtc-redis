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
        // ���ӵ� mongodb ����

        List<ServerAddress> serverAddressList = new ArrayList<>();
        ServerAddress serverAddress = new ServerAddress(host, port);
        serverAddressList.add(serverAddress);


//        MongoClient mongoClient = new MongoClient(host, port);
        Builder build = new Builder();
        build.cursorFinalizerEnabled(true);
        // options.autoConnectRetry(true);// �Զ�����true
//         options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
        build.connectionsPerHost(50);// ���ӳ�����Ϊ300������,Ĭ��Ϊ100
        /**���������ݿ⽨������ʱ�ʱ��Ϊ1����*/
        build.connectTimeout(1000 * 60 * 1);
        build.serverSelectionTimeout(timeout); //���ó�ʱʱ��

        /**һ���̷߳������ݿ��ʱ���ڳɹ���ȡ��һ���������ݿ�����֮ǰ����ȴ�ʱ��Ϊ���˴�Ϊ 2����
         * ������� maxWaitTime ��û�л�ȡ�����ӵĻ������߳̾ͻ��׳� Exception
         * */
        build.maxWaitTime(1000 * 60 * 2);
        build.socketTimeout(0);// �׽��ֳ�ʱʱ�䣬0������
        build.threadsAllowedToBlockForConnectionMultiplier(50);// �̶߳���������������߳������˶��оͻ��׳���Out of semaphores to get db������
        build.writeConcern(WriteConcern.SAFE);//
        build.build();
        MongoClientOptions mongoClientOptions = build.build();


        MongoClient mongoClient = new MongoClient(serverAddressList, mongoClientOptions);
        String connectPoint = mongoClient.getConnectPoint();
        System.out.println(connectPoint);

        // ���ӵ����ݿ�
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
