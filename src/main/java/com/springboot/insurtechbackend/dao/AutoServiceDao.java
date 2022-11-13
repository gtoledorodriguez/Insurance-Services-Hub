package com.springboot.insurtechbackend.dao;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.springboot.insurtechbackend.CommonUtils.SingelJdbcConnect;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class AutoServiceDao {
    static DataSource dataSource = null;
    static Connection conn = null;

    public static void main(String[] args) {

        AutoServiceDao.getAllServiceListOrByUserID("1","1");
    }
    // 2 is all ,1 is by id
    public static List<Map<String, Object>> getAllServiceListOrByUserID(String userId,String op ) {

        System.out.println("into getAllServiceListOrByUserID");
        List<Map<String, Object>>  result;
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();

//       StringUtils.isNotBlank(str)//判断字符串不为空

        System.out.println("1");
        String SqlStr ="";
        SqlStr=SqlStr+"select * from autoservice";

        if(op=="1"){
            System.out.println("into 1");
            SqlStr=SqlStr+" where  autoservice.AutoServiceID in (select AutoServiceID from serviceorder  where userid =?)";
            result = template.queryForList(SqlStr,userId);

        }else {
            result = template.queryForList(SqlStr);
            System.out.println("into 2");
        }

        System.out.println("XXX "+result.toString());
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx"+jsonxx);
        return result ;
    }

}
