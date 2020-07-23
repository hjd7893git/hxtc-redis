package cm1;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisServiceList {
    private ShardedJedisPool shardedJedisPool;

    public RedisServiceList() {
        this.shardedJedisPool = new InitShardedJedisPool().getShardedJedisPool();
    }

    public RedisServiceList(String host, int port, String password, int timeout) throws Exception {
        this.shardedJedisPool = InitRedisShardedJedisPool.getInitRedisShardedJedisPool().getShardedJedisPool(host, port, password, timeout);
    }

//    private ShardedJedisPool shardedJedisPool = InitShardedJedisPool.getShardedJedisPool();

    private <T> T execute(Function<T, ShardedJedis> fun) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
            return fun.callback(shardedJedis);
        } finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
    }

    /**
     * 执行set操作
     *
     * @param value
     * @return
     */
    public Long lpush(final String key, final String value,int expireTime) {
        return this.execute(new Function<Long, ShardedJedis>() {
            public Long callback(ShardedJedis e) {
                e.expire(key, expireTime); //默认有效时间为10分钟
                return e.lpush(key, value);
            }
        });
    }

    /**
     * 移除并获取列表末端一个元素
     *
     * @param key
     * @return
     */
    public String rpop(final String key) {
        return this.execute(new Function<String, ShardedJedis>() {
            public String callback(ShardedJedis e) {
                String val = e.rpop(key);
                return val;
            }
        });
    }
    public String rpop2(final String key, int count) {
        return this.execute(new Function<String, ShardedJedis>() {
            public String callback(ShardedJedis e) {
                e.ltrim(key, 0,  count);
                String val = e.rpop(key);
                return val;
            }
        });
    }

    public Long clean(final String key) {
        return this.execute((Function<Long, ShardedJedis>) e -> e.del(key));
    }

    /**
     * 移除并获取列表中的第5个元素，按照时间要求
     */
    public String lpop(final String key,final long time) {
        final String mok = this.execute(new Function<String, ShardedJedis>() {
            public String callback(ShardedJedis e) {
                return e.lindex(key, time);
            }
        });
        if (mok == null) return ""; //当缓存数量少于5时，start
//        if (!existsKey(key) && Long.parseLong(mok.split("\\|")[2]) >= Long.parseLong(time))
        else {
            this.execute(new Function<Long, ShardedJedis>() {
                public Long callback(ShardedJedis e) {
                    return e.lrem(key, 0L, mok);
                }
            });
            return mok;
        }
//        else return "";
    }

    /**
     * 判断列表是否为空
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
     * 获得列表数据长度
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

    public String indexde(final String key,final long index) {
        return this.execute(new Function<String, ShardedJedis>() {
            public String callback(ShardedJedis e) {
                return e.lindex(key, index);
            }
        });
    }

    /**
     * 执行set操作并且设置生存时间，单位为：秒
     *
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value, final Integer seconds) {
        return this.execute(new Function<String, ShardedJedis>() {
            public String callback(ShardedJedis e) {
                String str = e.set(key, value);
                e.expire(key, seconds);
                return str;
            }
        });
    }
    /**
     * 执行get操作
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return this.execute(new Function<String, ShardedJedis>() {
            public String callback(ShardedJedis e) {
                return e.get(key);
            }
        });
    }


}


