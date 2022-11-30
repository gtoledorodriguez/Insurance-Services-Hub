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


    public static List<Map<String, Object>> getOderListByName(String orderId ) {
        System.out.println("into getOderListByName");
        List<Map<String, Object>>  result;
        //run sql must add below code
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        System.out.println("1");

        result = template.queryForList("select * from serviceorder left join user on serviceorder.UserID=user.UserID  left join  auto_service on  serviceorder.AutoServiceID =auto_service.auto_service_id left join vehicleinfo on  user.UserID=vehicleinfo.userId where ServiceOrderID=?",orderId);
        System.out.println("XXX "+result.toString());
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx"+jsonxx);
        return result ;

    }
    public static List<Map<String, Object>> queryAllOderInfo( ) {
        System.out.println("into getOderListByName");
        List<Map<String, Object>>  result;
        //run sql must add below code
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        System.out.println("1");
        result = template.queryForList("select * from serviceorder left join user on serviceorder.UserID=user.UserID  " +
                "left join  auto_service on  serviceorder.AutoServiceID =auto_service.auto_service_id left join vehicleinfo on  user.UserID=vehicleinfo.userId  ");
        System.out.println("XXX "+result.toString());
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx"+jsonxx);
        return result ;
    }
    public static int delectOrderById(int orderID ) {
        System.out.println("into delectOrderById");
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        String SqlStr="DELETE FROM serviceorder WHERE ServiceOrderID =? ";
        int result = template.update(SqlStr,orderID);
        return result;

    }
    public static List<Map<String, Object>>  getOrderByEmail(String Email ) {
        System.out.println("into getOrderByEmail");
        List<Map<String, Object>>  result;
        //run sql must add below code
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        System.out.println("1");
        result = template.queryForList("select * from serviceorder left join user on serviceorder.UserID=user.UserID  left join  auto_service on  serviceorder.AutoServiceID =auto_service.auto_service_id " +
                "left join vehicleinfo on  user.UserID=vehicleinfo.userId where serviceorder.UserID= (select UserID from user where Email=?) ",Email);
        System.out.println("XXX "+result.toString());
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx"+jsonxx);
        return result ;
    }


    }
