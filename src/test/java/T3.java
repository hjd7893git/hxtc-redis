import mongo.MongoDBAPI;

import java.text.ParseException;
import java.util.List;

public class T3 {
    public static void main(String[] args) throws ParseException {
        MongoDBAPI mongoHelper = new MongoDBAPI("192.168.220.132", 27017, "test");
        List<String> all = mongoHelper.findByDateAll("StatisticsExchange", "20191227145804", "20191227145812",3L);
//        System.out.println(all);

        String Te = mongoHelper.findByDataTake( "", "20191227185636",3L);
        System.out.println(Te);

    }
}
