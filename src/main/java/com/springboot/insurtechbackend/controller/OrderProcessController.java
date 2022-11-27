package com.springboot.insurtechbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.insurtechbackend.dao.OrderProcessDao;
import com.springboot.insurtechbackend.model.ResultInfo;
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

    @PostMapping("/byID")
    @ResponseBody
    //  get service order info by userID
    public ResultInfo getOrderById(@RequestBody Map<String, Object> map, HttpServletRequest request) {

        String userId = (String) map.get("userID");
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();

        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        result= OrderProcessDao.getOderListByName(userId);
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
    public void modifyOderInfoById(@RequestBody Map<String, Object> map, HttpServletRequest request) {
    //this function need implement

    }


    }
