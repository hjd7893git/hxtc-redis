import cm1.RedisServiceList;

/**
 * Created by Administrator on 2019/9/12.
 */
public class T1List {
    public static void main(String[] args) throws Exception{
        RedisServiceList redisService = new RedisServiceList("127.0.0.1",6378,500);
//        boolean es = redisService.existsKey("BII.1");
//        System.out.println(es);
    }
}
