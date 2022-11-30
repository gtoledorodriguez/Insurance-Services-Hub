package com.springboot.insurtechbackend.dao;

import com.alibaba.fastjson.JSONObject;
import com.springboot.insurtechbackend.CommonUtils.SingelJdbcConnect;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonDao {
    public static void main(String[] args) {
        getcurrentUserLocation(1);
    }
    public static List<Map<String, Object>> userWithAgentsOneMile(int userId ) {


        List<Map<String, Object>> resultUser  = UserProcessDao.getUserByType(userId);
        System.out.println("JSONObject.toJSONString(resultUser)"+ JSONObject.toJSONString(resultUser));
        double user_lat = (double) resultUser.get(0).get("user_lat");
        double user_long = (double) resultUser.get(0).get("user_long");
        String Email = (String) resultUser.get(0).get("Email");

        System.out.println("XXXuser_lat"+user_lat +"  "+"user_long"+user_long+"  "+"Email "+ Email);

        System.out.println("into getAllServiceListOrByUserID");
        List<Map<String, Object>>  result;
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        String SqlStr="select * from (SELECT Email, UserName , FullAddress,PhoneNo ,user_lat,user_long ," +
                "(st_distance_sphere(point(?,?),point(user_long,user_lat ))) AS distance " +
                "FROM user  where   user_lat is  not null  and  Email != ? order by distance) AS wer " +
                "where distance< 1609";
        System.out.println("SqlStr"+SqlStr);
        result = template.queryForList(SqlStr,user_long,user_lat,Email);
        System.out.println("JSONObject.toJSONString(result)"+ JSONObject.toJSONString(result));

        return result;

    }

    public static List<Map<String, Object>> getcurrentUserLocation(int userId ) {
        System.out.println("into userWithAgentsOneMile");

        List<Map<String, Object>> resultUser  = UserProcessDao.getUserByType(userId);
        System.out.println("JSONObject.toJSONString(resultUser)"+ JSONObject.toJSONString(resultUser));
        Map<String,Object> Map=resultUser.get(0);
        double user_lat = (double) Map.get("user_lat");
        double user_long = (double) Map.get("user_long");
        String Email = (String) Map.get("Email");
        System.out.println("XXXuser_lat"+user_lat +"  "+"user_long"+user_long+"  "+"Email "+ Email);
        Map<String,Object>  resultItem = new HashMap<>();
        resultItem.put("Email",Map.get("Email"));
        resultItem.put("FullAddress",Map.get("FullAddress"));
        resultItem.put("PhoneNo",Map.get("PhoneNo"));
        resultItem.put("UserName",Map.get("UserName"));
        resultItem.put("user_lat",Map.get("user_lat"));
        resultItem.put("user_long",Map.get("user_long"));
        List<Map<String, Object>> result =new ArrayList<>();
        result.add(resultItem);

        return result;

    }


    }
