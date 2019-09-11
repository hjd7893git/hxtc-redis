/**
 * Created by Administrator on 2019/9/11.
 */
public class T1 {
    public static void main(String[] args) {
        RedisService redisService = new RedisService();
        redisService.set("hjd","789");
        System.out.println(redisService.get("hjd"));

    }
}
