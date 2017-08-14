package com.github.ethanpang.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
import redis.clients.util.Pool;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * redis基本的操作
 *
 * @author victor
 */
@Component
public class RedisUtil implements JedisCommands, MultiKeyCommands {
    @Autowired
    Pool<Jedis> pool;

    public RedisUtil(Pool<Jedis> pool) {
        this.pool = pool;
    }

    public Jedis getJedis() {
        return pool.getResource();
    }

    @Override
    public String set(String key, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.set(key, value);
        }
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, long time) {
        try (Jedis jedis = getJedis()) {
            return jedis.set(key, value, nxxx, expx, time);
        }
    }

    @Override
    public String set(String key, String value, String nxxx) {
        return null;
    }

    @Override
    public String get(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.get(key);
        }
    }

    @Override
    public Boolean exists(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.exists(key);
        }
    }

    @Override
    public Long persist(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.persist(key);
        }
    }

    @Override
    public String type(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.type(key);
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        try (Jedis jedis = getJedis()) {
            return jedis.expire(key, seconds);
        }
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        try (Jedis jedis = getJedis()) {
            return jedis.pexpire(key, milliseconds);
        }
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        try (Jedis jedis = getJedis()) {
            return jedis.expireAt(key, unixTime);
        }
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        try (Jedis jedis = getJedis()) {
            return jedis.pexpireAt(key, millisecondsTimestamp);
        }
    }

    @Override
    public Long ttl(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.ttl(key);
        }
    }

    @Override
    public Long pttl(String key) {
        return null;
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        try (Jedis jedis = getJedis()) {
            return jedis.setbit(key, offset, value);
        }
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.setbit(key, offset, value);
        }
    }

    @Override
    public Boolean getbit(String key, long offset) {
        try (Jedis jedis = getJedis()) {
            return jedis.getbit(key, offset);
        }
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.setrange(key, offset, value);
        }
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        try (Jedis jedis = getJedis()) {
            return jedis.getrange(key, startOffset, endOffset);
        }
    }

    @Override
    public String getSet(String key, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.getSet(key, value);
        }
    }

    @Override
    public Long setnx(String key, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.setnx(key, value);
        }
    }

    @Override
    public String setex(String key, int seconds, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.setex(key, seconds, value);
        }
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.psetex(key, milliseconds, value);
        }
    }

    @Override
    public Long decrBy(String key, long integer) {
        try (Jedis jedis = getJedis()) {
            return jedis.decrBy(key, integer);
        }
    }

    @Override
    public Long decr(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.decr(key);
        }
    }

    @Override
    public Long incrBy(String key, long integer) {
        try (Jedis jedis = getJedis()) {
            return jedis.incrBy(key, integer);
        }
    }

    @Override
    public Double incrByFloat(String key, double value) {
        try (Jedis jedis = getJedis()) {
            return jedis.incrByFloat(key, value);
        }
    }

    @Override
    public Long incr(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.incr(key);
        }
    }

    @Override
    public Long append(String key, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.append(key, value);
        }
    }

    @Override
    public String substr(String key, int start, int end) {
        try (Jedis jedis = getJedis()) {
            return jedis.substr(key, start, end);
        }
    }

    @Override
    public Long hset(String key, String field, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.hset(key, field, value);
        }
    }

    @Override
    public String hget(String key, String field) {
        try (Jedis jedis = getJedis()) {
            return jedis.hget(key, field);
        }
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.hsetnx(key, field, value);
        }
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        try (Jedis jedis = getJedis()) {
            return jedis.hmset(key, hash);
        }
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        try (Jedis jedis = getJedis()) {
            return jedis.hmget(key, fields);
        }
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        try (Jedis jedis = getJedis()) {
            return jedis.hincrBy(key, field, value);
        }
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        return null;
    }

    @Override
    public Boolean hexists(String key, String field) {
        try (Jedis jedis = getJedis()) {
            return jedis.hexists(key, field);
        }
    }

    @Override
    public Long hdel(String key, String... field) {
        try (Jedis jedis = getJedis()) {
            return jedis.hdel(key, field);
        }
    }

    @Override
    public Long hlen(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.hlen(key);
        }
    }

    @Override
    public Set<String> hkeys(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.hkeys(key);
        }
    }

    @Override
    public List<String> hvals(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.hvals(key);
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.hgetAll(key);
        }
    }

    @Override
    public Long rpush(String key, String... string) {
        try (Jedis jedis = getJedis()) {
            return jedis.rpush(key, string);
        }
    }

    @Override
    public Long lpush(String key, String... string) {
        try (Jedis jedis = getJedis()) {
            return jedis.lpush(key, string);
        }
    }

    @Override
    public Long llen(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.llen(key);
        }
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.lrange(key, start, end);
        }
    }

    @Override
    public String ltrim(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.ltrim(key, start, end);
        }
    }

    @Override
    public String lindex(String key, long index) {
        try (Jedis jedis = getJedis()) {
            return jedis.lindex(key, index);
        }
    }

    @Override
    public String lset(String key, long index, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.lset(key, index, value);
        }
    }

    @Override
    public Long lrem(String key, long count, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.lrem(key, count, value);
        }
    }

    @Override
    public String lpop(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.lpop(key);
        }
    }

    @Override
    public String rpop(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.rpop(key);
        }
    }

    @Override
    public Long sadd(String key, String... member) {
        try (Jedis jedis = getJedis()) {
            return jedis.sadd(key, member);
        }
    }

    @Override
    public Set<String> smembers(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.smembers(key);
        }
    }

    @Override
    public Long srem(String key, String... member) {
        try (Jedis jedis = getJedis()) {
            return jedis.srem(key, member);
        }
    }

    @Override
    public String spop(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.spop(key);
        }
    }

    @Override
    public Set<String> spop(String key, long count) {
        try (Jedis jedis = getJedis()) {
            return jedis.spop(key, count);
        }
    }

    @Override
    public Long scard(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.scard(key);
        }
    }

    @Override
    public Boolean sismember(String key, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.sismember(key, member);
        }
    }

    @Override
    public String srandmember(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.srandmember(key);
        }
    }

    @Override
    public List<String> srandmember(String key, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.srandmember(key, count);
        }
    }

    @Override
    public Long strlen(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.strlen(key);
        }
    }

    @Override
    public Long zadd(String key, double score, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zadd(key, score, member);
        }
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams params) {
        return null;
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        try (Jedis jedis = getJedis()) {
            return jedis.zadd(key, scoreMembers);
        }
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        return null;
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrange(key, start, end);
        }
    }

    @Override
    public Long zrem(String key, String... member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrem(key, member);
        }
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zincrby(key, score, member);
        }
    }

    @Override
    public Double zincrby(String key, double score, String member, ZIncrByParams params) {
        return null;
    }

    @Override
    public Long zrank(String key, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrank(key, member);
        }
    }

    @Override
    public Long zrevrank(String key, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrank(key, member);
        }
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrange(key, start, end);
        }
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeWithScores(key, start, end);
        }
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeWithScores(key, start, end);
        }
    }

    @Override
    public Long zcard(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.zcard(key);
        }
    }

    @Override
    public Double zscore(String key, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zscore(key, member);
        }
    }

    @Override
    public List<String> sort(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.sort(key);
        }
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        try (Jedis jedis = getJedis()) {
            return jedis.sort(key, sortingParameters);
        }
    }

    @Override
    public Long zcount(String key, double min, double max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zcount(key, min, max);
        }
    }

    @Override
    public Long zcount(String key, String min, String max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zcount(key, min, max);
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByScore(key, min, max);
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByScore(key, min, max);
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeByScore(key, max, min);
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByScore(key, min, max, offset, count);
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeByScore(key, max, min);
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByScore(key, min, max, offset, count);
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByScoreWithScores(key, min, max);
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByScoreWithScores(key, min, max);
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.zremrangeByRank(key, start, end);
        }
    }

    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        try (Jedis jedis = getJedis()) {
            return jedis.zremrangeByScore(key, start, end);
        }
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        try (Jedis jedis = getJedis()) {
            return jedis.zremrangeByScore(key, start, end);
        }
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zlexcount(key, min, max);
        }
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByLex(key, min, max);
        }
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByLex(key, min, max, offset, count);
        }
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeByLex(key, max, min);
        }
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrangeByLex(key, max, min, offset, count);
        }
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zremrangeByLex(key, min, max);
        }
    }

    @Override
    public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.linsert(key, where, pivot, value);
        }
    }

    @Override
    public Long lpushx(String key, String... string) {
        try (Jedis jedis = getJedis()) {
            return jedis.lpushx(key, string);
        }
    }

    @Override
    public Long rpushx(String key, String... string) {
        try (Jedis jedis = getJedis()) {
            return jedis.rpushx(key, string);
        }
    }

    @Deprecated
    @Override
    public List<String> blpop(String arg) {
        try (Jedis jedis = getJedis()) {
            return jedis.blpop(arg);
        }
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.blpop(timeout, key);
        }
    }

    @Deprecated
    @Override
    public List<String> brpop(String arg) {
        try (Jedis jedis = getJedis()) {
            return jedis.brpop(arg);
        }
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.brpop(timeout, key);
        }
    }

    @Override
    public Long del(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.del(key);
        }
    }

    @Override
    public String echo(String string) {
        try (Jedis jedis = getJedis()) {
            return jedis.echo(string);
        }
    }

    @Override
    public Long move(String key, int dbIndex) {
        try (Jedis jedis = getJedis()) {
            return jedis.move(key, dbIndex);
        }
    }

    @Override
    public Long bitcount(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.bitcount(key);
        }
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.bitcount(key, start, end);
        }
    }

    @Override
    public Long bitpos(String key, boolean value) {
        return null;
    }

    @Override
    public Long bitpos(String key, boolean value, BitPosParams params) {
        return null;
    }

    @Deprecated
    @Override
    public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
        try (Jedis jedis = getJedis()) {
            return jedis.hscan(key, cursor);
        }
    }

    @Deprecated
    @Override
    public ScanResult<String> sscan(String key, int cursor) {
        try (Jedis jedis = getJedis()) {
            return jedis.sscan(key, cursor);
        }
    }

    @Deprecated
    @Override
    public ScanResult<Tuple> zscan(String key, int cursor) {
        try (Jedis jedis = getJedis()) {
            return jedis.zscan(key, cursor);
        }
    }

    @Override
    public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
        try (Jedis jedis = getJedis()) {
            return jedis.hscan(key, cursor);
        }
    }

    @Override
    public ScanResult<Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return null;
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        try (Jedis jedis = getJedis()) {
            return jedis.sscan(key, cursor);
        }
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        return null;
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        try (Jedis jedis = getJedis()) {
            return jedis.zscan(key, cursor);
        }
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return null;
    }

    @Override
    public Long pfadd(String key, String... elements) {
        try (Jedis jedis = getJedis()) {
            return jedis.pfadd(key, elements);
        }
    }

    @Override
    public long pfcount(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.pfcount(key);
        }
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        return null;
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return null;
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        return null;
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return null;
    }

    @Override
    public List<String> geohash(String key, String... members) {
        return null;
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return null;
    }

    @Override
    public Long del(String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.del(keys);
        }
    }

    @Override
    public Long exists(String... keys) {
        return null;
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.blpop(timeout, keys);
        }
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.brpop(timeout, keys);
        }
    }

    @Override
    public List<String> blpop(String... args) {
        try (Jedis jedis = getJedis()) {
            return jedis.blpop(args);
        }
    }

    @Override
    public List<String> brpop(String... args) {
        try (Jedis jedis = getJedis()) {
            return jedis.brpop(args);
        }
    }

    @Override
    public Set<String> keys(String pattern) {
        try (Jedis jedis = getJedis()) {
            return jedis.keys(pattern);
        }
    }

    @Override
    public List<String> mget(String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.mget(keys);
        }
    }

    @Override
    public String mset(String... keysvalues) {
        try (Jedis jedis = getJedis()) {
            return jedis.mset(keysvalues);
        }
    }

    @Override
    public Long msetnx(String... keysvalues) {
        try (Jedis jedis = getJedis()) {
            return jedis.msetnx(keysvalues);
        }
    }

    @Override
    public String rename(String oldkey, String newkey) {
        try (Jedis jedis = getJedis()) {
            return jedis.rename(oldkey, newkey);
        }
    }

    @Override
    public Long renamenx(String oldkey, String newkey) {
        try (Jedis jedis = getJedis()) {
            return jedis.renamenx(oldkey, newkey);
        }
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        try (Jedis jedis = getJedis()) {
            return jedis.rpoplpush(srckey, dstkey);
        }
    }

    @Override
    public Set<String> sdiff(String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.sdiff(keys);
        }
    }

    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.sdiffstore(dstkey, keys);
        }
    }

    @Override
    public Set<String> sinter(String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.sinter(keys);
        }
    }

    @Override
    public Long sinterstore(String dstkey, String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.sinterstore(dstkey, keys);
        }
    }

    @Override
    public Long smove(String srckey, String dstkey, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.smove(srckey, dstkey, member);
        }
    }

    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        try (Jedis jedis = getJedis()) {
            return jedis.sort(key, sortingParameters, dstkey);
        }
    }

    @Override
    public Long sort(String key, String dstkey) {
        try (Jedis jedis = getJedis()) {
            return jedis.sort(key, dstkey);
        }
    }

    @Override
    public Set<String> sunion(String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.sunion(keys);
        }
    }

    @Override
    public Long sunionstore(String dstkey, String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.sunionstore(dstkey, keys);
        }
    }

    @Override
    public String watch(String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.watch(keys);
        }
    }

    @Override
    public String unwatch() {
        try (Jedis jedis = getJedis()) {
            return jedis.unwatch();
        }
    }

    @Override
    public Long zinterstore(String dstkey, String... sets) {
        try (Jedis jedis = getJedis()) {
            return jedis.zinterstore(dstkey, sets);
        }
    }

    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        try (Jedis jedis = getJedis()) {
            return jedis.zinterstore(dstkey, params, sets);
        }
    }

    @Override
    public Long zunionstore(String dstkey, String... sets) {
        try (Jedis jedis = getJedis()) {
            return jedis.zunionstore(dstkey, sets);
        }
    }

    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        try (Jedis jedis = getJedis()) {
            return jedis.zunionstore(dstkey, params, sets);
        }
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        try (Jedis jedis = getJedis()) {
            return jedis.brpoplpush(source, destination, timeout);
        }
    }

    @Override
    public Long publish(String channel, String message) {
        try (Jedis jedis = getJedis()) {
            return jedis.publish(channel, message);
        }
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        try (Jedis jedis = getJedis()) {
            jedis.subscribe(jedisPubSub, channels);
        }
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        try (Jedis jedis = getJedis()) {
            jedis.psubscribe(jedisPubSub, patterns);
        }
    }

    @Override
    public String randomKey() {
        try (Jedis jedis = getJedis()) {
            return jedis.randomKey();
        }
    }

    @Override
    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        try (Jedis jedis = getJedis()) {
            return jedis.bitop(op, destKey, srcKeys);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public ScanResult<String> scan(int cursor) {
        try (Jedis jedis = getJedis()) {
            return jedis.scan(cursor);
        }
    }

    @Override
    public ScanResult<String> scan(String cursor) {
        try (Jedis jedis = getJedis()) {
            return jedis.scan(cursor);
        }
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        return null;
    }

    @Override
    public String pfmerge(String destkey, String... sourcekeys) {
        try (Jedis jedis = getJedis()) {
            return jedis.pfmerge(destkey, sourcekeys);
        }
    }

    @Override
    public long pfcount(String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.pfcount(keys);
        }
    }
}
