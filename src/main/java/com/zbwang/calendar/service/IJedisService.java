package com.zbwang.calendar.service;

import java.util.Set;

public interface IJedisService {

	void sadd(String key, String member);

	void srem(String key, String member);

	Set<String> smembers(String key);

	Long scard(String key);

	Long getLong(String key);

	String get(String key);

	Long del(String key);

	Long incr(String key);

	Long decr(String key);
}
