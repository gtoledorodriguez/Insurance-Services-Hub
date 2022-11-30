package com.springboot.insurtechbackend.controller;

import com.springboot.insurtechbackend.CommonUtils.SingelJdbcConnect;
import com.springboot.insurtechbackend.dao.AutoServiceDao;
import com.springboot.insurtechbackend.dao.ReportInfoDam;
import com.springboot.insurtechbackend.model.ResultInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//@Controller
@RequestMapping("/report")
public class ReportController {

    @PostMapping("/totalOfeveryProductSold")
    @ResponseBody
    //ale report1: get service order info by userID
    public ResultInfo totalOfEveryProductSold(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        System.out.println("into updateService");
        List<Map<String, Object>> result = null;
        result= ReportInfoDam.totalOfEveryProductSold();
        resultBox.setData(result);
        return resultBox;

    }

    @PostMapping("/dailySaleReport")
    @ResponseBody
    //sale report2: get daily server Sale Report
    public ResultInfo dailySaleReport(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();

        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        System.out.println("into updateService");
        List<Map<String, Object>> result = null;
        result = template.queryForList(" select left(orderTime,10)as dayTime , SUM(totalPrice) as daliySaleTaltol,count(*) as dailySaleCount from serviceorder group by left(orderTime,10)");

        result= ReportInfoDam.totalOfEveryProductSold();
        resultBox.setData(result);
        return resultBox;
    }
    @PostMapping("/getAgentSaleperformance")
    @ResponseBody
    //sale report3: Get different agent sales performance data
    public ResultInfo getAgentSaleperformance(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();

        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        System.out.println("into updateService");
        List<Map<String, Object>> result = null;
        result = template.queryForList("select user.Email as agentEmail,PhoneNo,user.UserName AS agentName,SUM(totalPrice) as agentSaleTotal,count(*) as agentSaleServiceAcount  from serviceorder left join user on  serviceorder.AgentID= user.UserID  GROUP BY  serviceorder.AgentID");
        result= ReportInfoDam.totalOfEveryProductSold();
        resultBox.setData(result);
        return resultBox;
    }

    @PostMapping("/showServerAmountAndPrice")
    @ResponseBody
    //inventory  report1: Get different server  available amount  and price
    public ResultInfo showServerAmountAndPrice(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();

        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        System.out.println("into updateService");
        List<Map<String, Object>> result = null;
        result = template.queryForList(" select auto_service_id , CONCAT(service_name,'_type_',type) as serviceFullName ,Rental as price , amount  as ailableQuantity from auto_service where  amount != 0");

        resultBox.setData(result);
        return resultBox;

    }
    @PostMapping("showSaleServerAndCount/")
    @ResponseBody
    //inventory  report2: Show the number of sales of products that have been sold
    public ResultInfo showSaleServerAndCount(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();

        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        System.out.println("into updateService");
        List<Map<String, Object>> result = null;
        result = template.queryForList("select FT.auto_service_id ,FT.serviceFullName,Rental  as price ,count(*) as saleAcount from  serviceorder  left join ( SELECT  *,CONCAT(service_name,'_type_',type) as serviceFullName  FROM auto_service) as FT on   serviceorder.AutoServiceID=FT.auto_service_id  GROUP BY  FT.serviceFullName");

        resultBox.setData(result);
        return resultBox;

    }
}
