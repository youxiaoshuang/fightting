package com.youdows.fightting.service.impl;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * Created by youxiaoshuang on 16/8/15.
 * Projiect fightting
 * Author youxiaoshuang
 */
public class RedisService{
    private static RedisService instanse = null;
    private static JedisCluster jedisCluster = null;
    private static String host = "127.0.0.1";
    private static int port = 6379;

    public static  synchronized RedisService getInstanse(){
        if(instanse==null){
            instanse = new RedisService();
            init();
            return instanse;
        }else{
            return instanse;
        }
    }

    private static void init(){
        HostAndPort hostAndPort = new HostAndPort(host,port);
        jedisCluster = new JedisCluster(hostAndPort);
    }

    private void set(String key,Object value){
    }
}
