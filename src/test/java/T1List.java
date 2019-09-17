import cm1.RedisServiceList;

/**
 * Created by Administrator on 2019/9/12.
 */
public class T1List {
    public static void main(String[] args) {
        RedisServiceList redisService = new RedisServiceList();
        boolean es = redisService.existsKey("BII.1");
        System.out.println(es);
    }
}
