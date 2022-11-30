package com.springboot.insurtechbackend.dao;

import com.alibaba.fastjson.JSONObject;
import com.springboot.insurtechbackend.CommonUtils.MongoDBDataStoreUtilities;
import com.springboot.insurtechbackend.CommonUtils.SingelJdbcConnect;
import com.springboot.insurtechbackend.model.Bestrating;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrendingDao {
    static DataSource dataSource = null;
    static Connection conn = null;

    public static void main(String[] args) {
        // AutoServiceDao.insertServiceDate();

        // AutoServiceDao.delectServiceDateById(31);
        // AutoServiceDao.getAllServiceDate();
        // AutoServiceDao.getAllServiceListOrByUserID("1","1");
    }
    // 2 is all ,1 is by id /
    // public static List<Map<String, Object>> getAllServiceListOrByUserID(String
    // userId,String op ) {
    // System.out.println("into getAllServiceListOrByUserID");
    // List<Map<String, Object>> result;
    // JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();

    // // StringUtils.isNotBlank(str)//判断字符串不为空
    // System.out.println("1");
    // String SqlStr ="";
    // SqlStr=SqlStr+"select * from autoservice";

    // if(op=="1"){
    // System.out.println("into 1");
    // SqlStr=SqlStr+" where autoservice.AutoServiceID in (select AutoServiceID from
    // serviceorder where userid =?)";
    // result = template.queryForList(SqlStr,userId);

    // }else {
    // result = template.queryForList(SqlStr);
    // System.out.println("into 2");
    // }

    // System.out.println("XXX "+result.toString());
    // String jsonxx = JSONObject.toJSONString(result);
    // System.out.println("jsonxx"+jsonxx);
    // return result ;
    // }
    // public static List<Map<String, Object>> getUserAllAuto_service( int userID )
    // {
    // System.out.println("into getUserAllAuto_service");
    // List<Map<String, Object>> result;
    // JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
    // String SqlStr="select * from auto_service where auto_service.auto_service_id
    // in (select AutoServiceID from serviceorder where userid =?)";
    // result = template.queryForList(SqlStr,userID);
    // return result ;
    // }

    // auto_service table
    public static List<Map<String, Object>> getAllServicesByMostBought() {
        System.out.println("into getAllServicesByMostBought");
        List<Map<String, Object>> result;
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        String SqlStr = "";
        SqlStr = SqlStr
                + "select AutoServiceID, count(*) as sold FROM insurancedbz.serviceorder GROUP BY AutoServiceID order by sold desc limit 5;";
        result = template.queryForList(SqlStr);
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx" + jsonxx);
        return result;

    }

    public static List<Map<String, Object>> getAllServicesSoldByAgent(int agentID) {
        System.out.println("into getAllServicesSoldByAgent");
        System.out.println(agentID);
        List<Map<String, Object>> result;
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        String SqlStr = "select AgentID ,AutoServiceID, count(*) as sold FROM insurancedbz.serviceorder Where AgentID=? GROUP BY AutoServiceID order by sold desc limit 5;";
        result = template.queryForList(SqlStr, agentID);
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx" + jsonxx);
        return result;
    }

    public static List<Map<String, Object>> getBestAgentsByService(int AutoServiceID) {
        System.out.println("into getBestAgentsByService");
        System.out.println(AutoServiceID);
        List<Map<String, Object>> result;
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        String SqlStr = "select AgentID FROM insurancedbz.serviceorder Where AutoServiceID=? GROUP BY AgentID order by count(*) desc limit 5;";
        result = template.queryForList(SqlStr, AutoServiceID);
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx" + jsonxx);
        return result;
    }

    public static ArrayList<Bestrating> getTopServicesByBestRated() {
        System.out.println("into getTopServicesByBestRated");
        ArrayList<Bestrating> result = new ArrayList<Bestrating>();
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        try {
            ArrayList<Bestrating> hm = MongoDBDataStoreUtilities.topProducts();
//        String userName = "";
//        String reviewRating = "";
//        String reviewText = "";
//        String price = "";
//        String zipcode = "";

            //if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
            if (hm == null) {
                System.out.println("Mongo Db server is not up and running");
            } else {
                result = hm;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx" + jsonxx);
        return result;
    }

    // public static int delectServiceDateById( int serviceId) {
    // System.out.println("into delectServiceDateById");

    // JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
    // String SqlStr ="";
    // SqlStr=SqlStr+"DELETE FROM auto_service WHERE auto_service_id =? ";
    // int result = template.update(SqlStr,serviceId);
    // System.out.println("result"+result);
    // return result ;

    // }
    // public static int insertServiceDate( ) {
    // System.out.println("into insertServiceDateById");
    // JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
    // String SqlStr ="INSERT INTO auto_service (collision, comprehensive,
    // create_time, liability_bodily, liability_property, " +
    // "medical, rental, service_name, type, um_bodily, um_property) " +
    // "VALUES (251110, 250, '2022-11-04', 50000, 100000, 50000, 150, 'Diamond', 0,
    // 50000, 100000)";

    // int result = template.update(SqlStr);
    // System.out.println("result"+result);

    // //创建sql语句
    // return result;
    // }
    // public static int updateServiceDate( ) {
    //
    // String sql = "update auto_service SET collision=?
    // ,comprehensive=?,create_time=?,liability_bodily=?,liability_property=?" +
    // ",medical=?,rental=?service_name=?,name=?,name=?,name=?" +
    // " where auto_service_id =?";
    // int rows = jdbcTemplate.update(sql,"Lucy","NewYork");
    //
    // int result = template.update(SqlStr,serviceId);
    //
    // return 1;
    // }

}
