package com.xzc.algri.leetcode;

import java.util.*;

/**
 * @ClassName TimeMap
 * @Description /**
 * * Your TimeMap object will be instantiated and called as such:
 * * TimeMap obj = new TimeMap();
 * * obj.set(key,value,timestamp);
 * * String param_2 = obj.get(key,timestamp);
 * @Author zhichao.xing
 * @Date 2021/7/10 11:43
 * @Version 1.0
 **/
public class TimeMap {

//    Map<String, List<Integer>> keyTm=new HashMap();
//    Map<String,String> tmVal=new HashMap();
//
//    /** Initialize your data structure here. */
//    public TimeMap() {
//
//    }
//
//    public void set(String key, String value, int timestamp) {
//        List tmList = keyTm.get(key);
//        if(tmList == null){
//            tmList=new ArrayList();
//            keyTm.put(key,tmList);
//        }
//        tmList.add(timestamp);
//        tmVal.put(key+"_"+timestamp,value);
//    }
//
//    public String get(String key, int timestamp) {
//        List tmList= keyTm.getOrDefault(key,new ArrayList<>());
//        if(tmList.isEmpty()){
//            return "";
//        }
//        Integer size = tmList.size();
//        for(int i=size-1;i>=0;i--){
//            Integer current = (Integer)tmList.get(i);
//            if(current.compareTo(timestamp)<=0) {
//                return  tmVal.get(key+"_"+current);
//            }
//        }
//        return "";
//    }

    public static void main(String[] args) {
        TimeMap obj = new TimeMap();
        obj.set("foo", "bar", 1);
        String foo = obj.get("foo", 12);
        System.out.println(foo);
    }

//================================================================
//================================================================
// 华丽的分割线
//================================================================
//================================================================
//================================================================
    /**
     * @Description
     * @Author xzc
     * @Date 16:57 2021/7/10
     * @return
     **/
    Map<String, TreeMap<Integer, String>> map = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        Map.Entry<Integer, String> entry = map.getOrDefault(key, new TreeMap<>()).floorEntry(timestamp);
        return entry == null ? "" : entry.getValue();
    }
}
