package com.zbwang.calendar.service.impl;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbwang.calendar.service.IJedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JedisServiceImpl implements IJedisService {

	private static final Logger serviceLog = LoggerFactory.getLogger("service");
	@Autowired
	private JedisPool jedisPool;

	@Override
	public void sadd(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.sadd(key, member);
		}
		catch (Exception e) {
			serviceLog.error("Fail to operate redis.", e);
		}
		finally {
			if (jedisPool != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void srem(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.srem(key, member);
		}
		catch (Exception e) {
			serviceLog.error("Fail to operate redis.", e);
		}
		finally {
			if (jedisPool != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> smembers(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.smembers(key);
		}
		catch (Exception e) {
			serviceLog.error("Fail to operate redis.", e);
		}
		finally {
			if (jedisPool != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return Collections.emptySet();
	}

	@Override
	public Long scard(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.scard(key);
		}
		catch (Exception e) {
			serviceLog.error("Fail to operate redis.", e);
		}
		finally {
			if (jedisPool != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return 0L;
	}

	@Override
	public Long getLong(String key) {
		return NumberUtils.toLong(get(key));
	}

	@Override
	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		}
		catch (Exception e) {
			serviceLog.error("Fail to operate redis.", e);
		}
		finally {
			if (jedisPool != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return "";
	}

	@Override
	public Long del(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.del(key);
		}
		catch (Exception e) {
			serviceLog.error("Fail to operate redis.", e);
		}
		finally {
			if (jedisPool != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return 0L;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incr(key);
		}
		catch (Exception e) {
			serviceLog.error("Fail to operate redis.", e);
		}
		finally {
			if (jedisPool != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return 0L;
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.decr(key);
		}
		catch (Exception e) {
			serviceLog.error("Fail to operate redis.", e);
		}
		finally {
			if (jedisPool != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return 0L;
	}
}
