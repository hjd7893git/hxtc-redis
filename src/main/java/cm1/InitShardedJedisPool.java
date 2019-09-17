package cm1;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/9/11.
 */
public class InitShardedJedisPool {
    public static ShardedJedisPool getShardedJedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(2);
        poolConfig.setMaxIdle(1);
        poolConfig.setMaxWaitMillis(2000);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);

        //设置Redis信息
        String host = "127.0.0.1";
        JedisShardInfo shardInfo1 = new JedisShardInfo(host, 6379, 500);

//        shardInfo1.setPassword("test123");

        //初始化ShardedJedisPool
        List<JedisShardInfo> infoList = Arrays.asList(shardInfo1);
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(poolConfig, infoList);
        return shardedJedisPool;
    }
}
