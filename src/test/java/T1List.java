import cm1.RedisServiceList;

/**
 * Created by Administrator on 2019/9/12.
 */
public class T1List {
    public static void main(String[] args) throws Exception{
        /**
         * config set requirepass  [XXX]    设置临时密码
         * config get requirepass           获取临时密码
         */
        RedisServiceList redisService = new RedisServiceList("192.168.0.92",6379,"",500);
        redisService.lpush("T1","1");
        redisService.lpush("T1","2");
        redisService.lpush("T1","3");
        System.out.println(redisService.llen("T1"));
//        boolean es = redisService.existsKey("BII.1");
//        System.out.println(es);
        //c

    }
}
