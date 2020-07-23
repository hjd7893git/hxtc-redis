package cm1;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/9/11.
 */
public class InitRedisShardedJedisPool {
    private static InitRedisShardedJedisPool initRedisShardedJedisPool = new InitRedisShardedJedisPool();
    public static InitRedisShardedJedisPool getInitRedisShardedJedisPool() {
        return initRedisShardedJedisPool;
    }
    public static ShardedJedisPool getShardedJedisPool(String host, int port, String password, int timeout) throws Exception {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5); //�������������ָ��ʱ��ͨ��pool�ܹ���ȡ�����������ӵ�jedis���� 10
        poolConfig.setMaxIdle(1); // ������������,  ����ܹ�����idle������������һ��pool����ж��ٸ�״̬Ϊidle��jedisʵ�� 8
        poolConfig.setMaxWaitMillis(1000);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);

        //����Redis��Ϣ
        JedisShardInfo shardInfo1 = new JedisShardInfo(host, port, timeout);
        if (!"".equals(password)) shardInfo1.setPassword(password);
        System.out.println("--->redis"+host+":"+port+"���ӳɹ���"+shardInfo1.createResource().ping());


//        JedisShardInfo shardInfo2 = new JedisShardInfo(host, 6380, 500);
//        shardInfo2.setPassword("test123");
//        JedisShardInfo shardInfo3 = new JedisShardInfo(host, 6381, 500);
//        shardInfo3.setPassword("test123");

        //��ʼ��ShardedJedisPool
        List<JedisShardInfo> infoList = Arrays.asList(shardInfo1);
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(poolConfig, infoList);
        return shardedJedisPool;
    }
}
