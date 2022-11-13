package com.springboot.insurtechbackend.dao;

import com.alibaba.fastjson.JSONObject;
import com.springboot.insurtechbackend.CommonUtils.SingelJdbcConnect;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class OrderProcessDao {
    static DataSource dataSource = null;
    static Connection conn = null;

    public static void main(String[] args) {

    }


    public static List<Map<String, Object>> getOderListByName(String userId ) {
        System.out.println("into getOderListByName");
        List<Map<String, Object>>  result;
        //run sql must add below code
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        System.out.println("1");
        result = template.queryForList("select * from serviceorder " +
                "left join  autoservice on serviceorder.AutoServiceID =autoservice.AutoServiceID where serviceorder.UserID =?",userId);
        System.out.println("XXX "+result.toString());
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx"+jsonxx);
        return result ;

    }
}
