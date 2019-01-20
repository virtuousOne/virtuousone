package com.demo.virtuousone.controller;

import com.demo.virtuousone.common.ApiConstant;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/20/0020
 * Description: redis的基本使用
 */
@RestController
public class RedisDemoController {
    private static Logger logger = LoggerFactory.getLogger(RedisDemoController.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    // 数据结构 ----string
    @RequestMapping(value = "redis/string", method = RequestMethod.GET)
    public Map<String, Object> testString() {
        Map<String, Object> map = Maps.newHashMap();
        stringRedisTemplate.opsForValue().set("1", "2");
        // 设置超时一分钟
        redisTemplate.opsForValue().set("55", "那", 1, TimeUnit.MINUTES);
        String s = stringRedisTemplate.opsForValue().get("1");
        String s1 = (String) redisTemplate.opsForValue().get("55");
        logger.info("中文打印" + s + "：" + s1);
        map.put("code", 200);
        return map;
    }

    // redis的数据结构  --hash
    @RequestMapping(value = "redis/hash", method = RequestMethod.GET)
    public Map<String, Object> testHash() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", 200);
        HashOperations<String, String, String> ops = stringRedisTemplate.opsForHash();
        String key = "user";
        if (!stringRedisTemplate.hasKey(key)) {
            ops.put(key, "姓名", "吴晨轩");
            ops.put(key, "年龄", "20");
            ops.put(key, "sex", "男");
            logger.info("set success");
        } else {
            logger.info("已经存在了 key is exist");
            Map<String, String> entries = ops.entries(key);
            map.put("dat", entries);
        }
        return map;

    }

    // redis的数据结构 -----list
    @RequestMapping(value = "redis/list", method = RequestMethod.GET)
    public Map<String, Object> testList() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", 200);
        ListOperations<String, String> ops = stringRedisTemplate.opsForList();
        String key = "name";
        if (!stringRedisTemplate.hasKey(key)) {
            ops.leftPush(key, "吴");
            ops.leftPush(key, "宸");
            ops.leftPush(key, "煊");
            logger.info("set Success");
        } else {
            logger.info("key is exist");
            Long size = ops.size(key);
            // 从第几个到第几个读取
            List<String> list = ops.range(key, 0, size);
            for (String value : list) {
                logger.info("中文大一" + value);
            }
        }
        return map;
    }

    // redis的数据类型  --- set
    @RequestMapping(value = "redis/set", method = RequestMethod.GET)
    public Map<String, Object> testSet() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", 200);
        SetOperations<String, String> ops = stringRedisTemplate.opsForSet();
        String key = "likes";
        if (!stringRedisTemplate.hasKey(key)) {
            Long add = ops.add(key, "sport", "bask", "test");
            logger.info("set成功了" + add);
            // 移除
            ops.remove("likes", "test");
        } else {
            Set<String> members = ops.members(key);
            for (String value : members) {
                logger.info(value);
            }
        }
        return map;
    }

    // redis的数据类型 -- zset（set sort）
    @RequestMapping(value = "redis/setSort", method = RequestMethod.GET)
    public Map<String, Object> testSetSort() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", 200);
        ZSetOperations<String, String> ops = stringRedisTemplate.opsForZSet();
        String key = "foods";
        if (!stringRedisTemplate.hasKey(key)) {
            ops.add(key, "fruite", 0);
            ops.add(key, "apple", 5);
            ops.add(key, "test", 3);
            ops.add(key, "read", 3);
        } else {
            Set<String> sets = ops.rangeByScore(key, 0, 100);
            for (String value : sets) {
                logger.info(value);
            }
        }
        return map;
    }


    /**
     * 分布式锁的解决方案---- redis（redis解决分布式锁）
     *
     * @param lockStr
     * @param lockTime
     * @return
     */
    public Boolean setLock(String lockStr, Long lockTime) {
        // 给redis设置一个key，如果返回成功，说明拿到了锁。
        if (redisTemplate.opsForValue().setIfAbsent(String.format(ApiConstant.LOCK, lockStr), "1")) {
            // 给redis设置过期时间
            redisTemplate.expire(String.format(ApiConstant.LOCK, lockStr), lockTime, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    public Boolean unLock(String lockStr) {
        redisTemplate.delete(String.format(ApiConstant.LOCK, lockStr));
        return true;

    }


}
