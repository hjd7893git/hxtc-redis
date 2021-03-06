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
        poolConfig.setMaxTotal(5); //最大连接数，在指定时刻通过pool能够获取到的最大的连接的jedis个数 10
        poolConfig.setMaxIdle(1); // 最大空闲连接数,  最大能够保持idle的数量，控制一个pool最多有多少个状态为idle的jedis实例 8
        poolConfig.setMaxWaitMillis(1000);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);

        //设置Redis信息
        String host = "127.0.0.1";
        JedisShardInfo shardInfo1 = new JedisShardInfo(host, 6379, 500);

//        shardInfo1.setPassword("test123");

//        JedisShardInfo shardInfo2 = new JedisShardInfo(host, 6380, 500);
//        shardInfo2.setPassword("test123");
//        JedisShardInfo shardInfo3 = new JedisShardInfo(host, 6381, 500);
//        shardInfo3.setPassword("test123");

        //初始化ShardedJedisPool
        List<JedisShardInfo> infoList = Arrays.asList(shardInfo1);
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(poolConfig, infoList);
        return shardedJedisPool;
    }
}
