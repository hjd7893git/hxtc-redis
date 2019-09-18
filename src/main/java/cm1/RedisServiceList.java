package cm1;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisServiceList {
    private ShardedJedisPool shardedJedisPool;

    public RedisServiceList() {
        this.shardedJedisPool = new InitShardedJedisPool().getShardedJedisPool();
    }

    public RedisServiceList(String host, int port, int timeout) throws Exception  {
        this.shardedJedisPool = InitRedisShardedJedisPool.getInitRedisShardedJedisPool().getShardedJedisPool(host, port, timeout);
    }

//    private ShardedJedisPool shardedJedisPool = InitShardedJedisPool.getShardedJedisPool();

    private <T> T execute(Function<T, ShardedJedis> fun) {
        ShardedJedis shardedJedis = null;
        try {
            // �����ӳ��л�ȡ��jedis��Ƭ����
            shardedJedis = shardedJedisPool.getResource();
            return fun.callback(shardedJedis);
        } finally {
            if (null != shardedJedis) {
                // �رգ���������Ƿ���Ч����Ч��Żص����ӳ��У���Ч������״̬
                shardedJedis.close();
            }
        }
    }

    /**
     * ִ��set����
     *
     * @param value
     * @return
     */
    public Long lpush(final String key, final String value) {
        return this.execute(new Function<Long, ShardedJedis>() {
            public Long callback(ShardedJedis e) {
                return e.lpush(key, value);
            }
        });
    }

    /**
     * �Ƴ�����ȡ�б�ĩ��һ��Ԫ��
     *
     * @param key
     * @return
     */
    public String rpop(final String key) {
        return this.execute(new Function<String, ShardedJedis>() {
            public String callback(ShardedJedis e) {
                return e.rpop(key);
            }
        });
    }

    /**
     * �Ƴ�����ȡ�б��еĵ�һ��Ԫ��
     */
    public String lpop(final String key, String time) {
        final String mok = this.execute(new Function<String, ShardedJedis>() {
            public String callback(ShardedJedis e) {
                return e.lindex(key, 0);
            }
        });
        if (!existsKey(key) && Long.parseLong(mok.split("\\|")[2]) >= Long.parseLong(time)) {
            this.execute(new Function<Long, ShardedJedis>() {
                public Long callback(ShardedJedis e) {
                    return e.lrem(key, 0L, mok);
                }
            });
            return mok;
        } else return "";
    }

    /**
     * �ж��б��Ƿ�Ϊ��
     *
     * @param key
     * @return
     */
    public Boolean existsKey(final String key) {
        return this.execute(new Function<Boolean, ShardedJedis>() {
            public Boolean callback(ShardedJedis e) {
                return e.llen(key) == 0;
            }
        });
    }

    /**
     * ����б����ݳ���
     *
     * @param key
     * @return
     */
    public Long llen(final String key) {
        return this.execute(new Function<Long, ShardedJedis>() {
            public Long callback(ShardedJedis e) {
                return e.llen(key);
            }
        });
    }


}


