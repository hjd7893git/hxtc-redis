package mongo;


import cn.hutool.json.JSONUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MongoHelper mongoHelper = new MongoHelper("192.168.220.132", 27017, "test");
        MongoDatabase mongoDatabase = mongoHelper.getMongoDatabase();

        MongoCollection<Document> collection = mongoDatabase.getCollection("person");
        System.out.println("集合 "+"person"+" 选择成功");

        List<Person> list = new ArrayList<Person>();

        /**
         * 1. 获取迭代器FindIterable<Document>
         * 2. 获取游标MongoCursor<Document>
         * 3. 通过游标遍历检索出的文档集合
         * */
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            list.add( JSONUtil.toBean(mongoCursor.next().toJson(), Person.class));
        }
        System.out.println(list);

//        }catch(Exception e){
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//        }

    }
}
