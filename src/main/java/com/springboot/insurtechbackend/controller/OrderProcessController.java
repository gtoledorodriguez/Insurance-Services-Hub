package com.springboot.insurtechbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.insurtechbackend.CommonUtils.SingelJdbcConnect;
import com.springboot.insurtechbackend.dao.AutoServiceDao;
import com.springboot.insurtechbackend.dao.OrderProcessDao;
import com.springboot.insurtechbackend.model.AutoService;
import com.springboot.insurtechbackend.model.ResultInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//@Controller
@RequestMapping("/order")
public class OrderProcessController {

    @PostMapping("/getOrderbyID")
    @ResponseBody
    //  get service order info by userID
    public ResultInfo getOrderByOrderId(@RequestBody Map<String, Object> map, HttpServletRequest request) {

        String orderID = (String) map.get("orderID");
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();

        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        result= OrderProcessDao.getOderListByName(orderID);
        String responseJson = null;

        if (result.size() < 1) {
            resultBox.setFlag("0");
            resultBox.setErrorMsg("not this user order info");
        }else {
            resultBox.setData(result);
            responseJson = JSONObject.toJSONString(resultBox);
            return resultBox;
        }
        return resultBox;
    }
    //Modify a single service
    @PostMapping("/queryAllOderInfo")
    @ResponseBody
    public ResultInfo queryAllOderInfo(@RequestBody Map<String, Object> map, HttpServletRequest request) {
    //this function need implement
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        result=OrderProcessDao.queryAllOderInfo();
        resultBox.setData(result);
        return resultBox;
    }
    @PostMapping("/modifyOderInfoByOrderId")
    @ResponseBody
    public ResultInfo modifyOderInfoById(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        //this function need implement
        int serviceOrderID = (int) map.get("ServiceOrderID");
        String Description = (String)map.get("Description");
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        double Discount = (double)map.get("Discount");
        double Total = (double)map.get("Total");
        int price = (int)map.get("price");
        String AgentID = (String)map.get("AgentID");
        String Status = (String)map.get("Status");
        int AutoServiceID = (int)map.get("AutoServiceID");
        double totalPrice = (double)map.get("totalPrice");

        String SqlStr ="update  serviceorder  set  Description=?, Discount=?, Total=?, " +
                "AgentID=?, Status=?, AutoServiceID=?, totalPrice=? where serviceOrderID= ?" ;
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        int result = template.update(SqlStr,Description,Discount,Total,AgentID,Status,AutoServiceID,totalPrice,serviceOrderID);
        System.out.println("result"+result);
        resultBox.setData(result);

        return resultBox;
    }

    @PostMapping("/delectOrderById")
    @ResponseBody
    public ResultInfo delectOrderById(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        //this function need implement
        AutoService autoService =new AutoService();
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        int oderId = (int) map.get("oderId");
        int result=OrderProcessDao.delectOrderById(oderId);
        resultBox.setData(result);
        return  resultBox;
    }
    @PostMapping("/addOrder")
    @ResponseBody
    public ResultInfo addOrder(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String Description = (String)map.get("Description");
        ResultInfo resultBox = new ResultInfo();

        String UserID = (String)map.get("UserID");
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        double Discount = (double)map.get("Discount");
        double Total = (double)map.get("Total");
        int price = (int)map.get("price");
        String AgentID = (String)map.get("AgentID");
        String Status = (String)map.get("Status");
        int AutoServiceID = (int)map.get("AutoServiceID");
        double totalPrice  = (double)(Total * price);
        totalPrice =totalPrice-Discount;

        String SqlStr ="INSERT INTO  serviceorder (UserID, Description, Discount, Total, " +
                "AgentID, Status, AutoServiceID, totalPrice) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        int result = template.update(SqlStr,UserID,Description,Discount,Total,AgentID,Status,AutoServiceID,totalPrice);
        System.out.println("result"+result);

        resultBox.setData(result);
        return resultBox;

    }
    @PostMapping("/getOrderByEmail")
    @ResponseBody
    //  get service order info by userID
    public ResultInfo getOrderByEmail(@RequestBody Map<String, Object> map, HttpServletRequest request) {

        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");

        String Email = (String) map.get("Email");
        result= OrderProcessDao.getOrderByEmail(Email);

        resultBox.setData(result);
        return resultBox;
    }


    }
