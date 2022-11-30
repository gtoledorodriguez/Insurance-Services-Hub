package com.springboot.insurtechbackend.dao;

import com.alibaba.fastjson.JSONObject;
import com.springboot.insurtechbackend.CommonUtils.SingelJdbcConnect;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class ReportInfoDam {

    //sale report
    public static List<Map<String, Object>> totalOfEveryProductSold() {
        System.out.println("into getOderListByName");
        List<Map<String, Object>>  result;
        //run sql must add below code
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        System.out.println("1");

        result = template.queryForList(" select FT.serviceFullName,SUM(totalPrice) as totalSalePrice ,SUM(Total) as quantity , FT.auto_service_id from  serviceorder  left join ( SELECT  *,CONCAT(service_name,'_type_',type) as serviceFullName  FROM auto_service) as FT " +
                "on   serviceorder.AutoServiceID=FT.auto_service_id  GROUP BY  FT.serviceFullName");
        System.out.println("XXX "+result.toString());
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx"+jsonxx);
        return result ;

    }

    }
