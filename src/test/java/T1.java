import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.developer.SerializationFeature;
import pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/9/11.
 */
public class T1 {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        RedisService redisService = new RedisService();
        redisService.set("hjd","789");
        System.out.println(redisService.get("hjd"));

        List<String>  ssl = new ArrayList<String>();
        ssl.add("12");
        ssl.add("12");
        ssl.add("12");
        ssl.add("12");

        String sd  = redisService.set("hjd1", mapper.writeValueAsString(ssl));
        System.out.println(sd);
        System.out.println(redisService.get("hjd1"));


        User user = new User("hjd",25);
        redisService.set("hjd2", mapper.writeValueAsString(user));
        System.out.println(redisService.get("hjd2"));



    }
}
