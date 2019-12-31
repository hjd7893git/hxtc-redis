package mongo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.bson.json.StrictJsonWriter;
import pojo.StatisticsExchange;
import pojo.StatisticsMachine;
import pojo.StatisticsNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MongoDBAPI {
    private MongoDatabase mongoDatabase;

    public MongoDBAPI(String host, int port, String group) {
        MongoHelper mongoHelper = new MongoHelper(host, port, group);
        mongoDatabase = mongoHelper.getMongoDatabase();
    }

    public void saveStatisticsMachine(String statisticsmachine) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("StatisticsMachine");
        Document document = new Document(BeanUtil.beanToMap(JSONUtil.toBean(statisticsmachine, StatisticsMachine.class)));
        collection.insertOne(document);
    }

    public void saveStatisticsExchange(String statisticsexchange) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("StatisticsExchange");
        Document document = new Document(BeanUtil.beanToMap(JSONUtil.toBean(statisticsexchange, StatisticsExchange.class)));
        collection.insertOne(document);
    }

    public void saveStatisticsNode(String statisticsnode) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("StatisticsNode");
        Document document = new Document(BeanUtil.beanToMap(JSONUtil.toBean(statisticsnode, StatisticsNode.class)));
        collection.insertOne(document);
    }


    public List<String> findByDateAll(String tag, String allDate_1, String allDate_2, Long clusterId) {
        //去除 返回结果中的"$numberLong"
        JsonWriterSettings build = JsonWriterSettings.builder()
                .outputMode(JsonMode.EXTENDED)
                .int64Converter((Long value, StrictJsonWriter writer) -> writer.writeNumber(Long.toString(value)))
                .int32Converter((Integer value, StrictJsonWriter writer) -> writer.writeNumber(Integer.toString(value)))
                .build();
        MongoCollection<Document> collection = null;
        switch (tag) {
            case "StatisticsMachine":
                collection = mongoDatabase.getCollection("StatisticsMachine");
                break;
            case "StatisticsExchange":
                collection = mongoDatabase.getCollection("StatisticsExchange");
                break;
            case "StatisticsNode":
                collection = mongoDatabase.getCollection("StatisticsNode");
                break;
            default:
                collection = null;
        }
        Bson filter = Filters.and(Filters.lte("gatherDatetime", allDate_2), Filters.gte("gatherDatetime", allDate_1), Filters.eq("clusterId", clusterId));
        //指定查询过滤器查询
        FindIterable<Document> findIterable = collection.find(filter);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<String> rest = new ArrayList<>();
        while (cursor.hasNext()) {
            Document next = cursor.next();
            next.remove("_id");
            rest.add(next.toJson(build));
//            System.out.println(cursor.next().toJson());
        }
        return rest;
    }

    //历史分页查询
    public String findByDataTake(String allDate_1, String allDate_2, Long clusterId) {
        //去除 返回结果中的"$numberLong"
        JsonWriterSettings build = JsonWriterSettings.builder()
                .outputMode(JsonMode.EXTENDED)
                .int64Converter((Long value, StrictJsonWriter writer) -> writer.writeNumber(Long.toString(value)))
                .int32Converter((Integer value, StrictJsonWriter writer) -> writer.writeNumber(Integer.toString(value)))
                .build();
        MongoCollection<Document> collection = mongoDatabase.getCollection("StatisticsExchange");
        if (StrUtil.isNotEmpty(allDate_1)) { //向左分页
            Bson filter = Filters.lt("gatherDatetime", allDate_1);
            FindIterable<Document> findIterable = collection.find(filter).sort(new Document().append("gatherDatetime", -1)).limit(1);
            MongoCursor<Document> cursor = findIterable.iterator();
            if (cursor.hasNext()) return cursor.next().getString("gatherDatetime");
            else return null;
        } else {                            //向右分页
            Bson filter = Filters.gt("gatherDatetime", allDate_2);
            FindIterable<Document> findIterable = collection.find(filter).sort(new Document().append("gatherDatetime", 1)).limit(1);
            MongoCursor<Document> cursor = findIterable.iterator();
            if (cursor.hasNext()) return cursor.next().getString("gatherDatetime");
            else return null;
        }
    }


    public String[] historyCalendar(Long clusterId, String date) throws ParseException {
        String date1 = date.substring(0, 6) + "01000000";
        String date2 = dateTimeExec(date.substring(0, 6) + "01000000");
        MongoCollection<Document> collection = mongoDatabase.getCollection("StatisticsExchange");
        Bson filter = Filters.and(Filters.lte("gatherDatetime", date2), Filters.gte("gatherDatetime", date1), Filters.eq("clusterId", clusterId));
        //时间切割
        BasicDBObject my_time = new BasicDBObject();
        my_time.append("$substr", new Object[]{"$gatherDatetime", 0, 8});
        //设定查询别名
        DBObject time = new BasicDBObject();
        time.put("new_time_stamp", my_time);

        AggregateIterable<Document> findIterable = collection.aggregate(Arrays.asList(
                //查询条件   //此查询组是有顺序的
                Aggregates.match(filter),
                //查询显示字段 //类似于通道
                Aggregates.project((Bson) time),
                //对集合进行条件过滤
                Aggregates.group("$new_time_stamp")
        ));
        /**
         * db.StatisticsExchange.aggregate({
         *     $project: {
         *         new_time_stamp: {
         *             $substr: ["$gatherDatetime", 0, 8]
         *         }
         *     }
         * }, {
         *     $group: {
         *         _id: "$new_time_stamp"
         *     }
         * });
         */
        MongoCursor<Document> cursor = findIterable.iterator();
        List<String> datas = new ArrayList<>();
        while (cursor.hasNext()) {
            datas.add(cursor.next().getString("_id"));
        }
        return datas.toArray(new String[]{});
    }

    private String dateTimeExec(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = sdf.parse(time);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH, 1);
        return sdf.format(instance.getTime());
    }
}
