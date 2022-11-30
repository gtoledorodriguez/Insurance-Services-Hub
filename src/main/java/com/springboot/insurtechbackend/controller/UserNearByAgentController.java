package com.springboot.insurtechbackend.controller;

import com.springboot.insurtechbackend.dao.CommonDao;
import com.springboot.insurtechbackend.dao.UserProcessDao;
import com.springboot.insurtechbackend.model.ResultInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/nearBy")

public class UserNearByAgentController {

    @PostMapping("/userWithAgentsOneMile")
    @ResponseBody

    public ResultInfo userWithAgentsOneMile(@RequestBody Map<String, Object> map, HttpServletRequest request) {

        int userIDInt = (int) map.get("userID");
        System.out.println("into userWithAgentsOneMile");
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        List<Map<String, Object>> result = null;
//        String userID=String.valueOf(userIDInt);
        result = CommonDao.userWithAgentsOneMile(userIDInt);

        System.out.println("result"+result.toString());
        resultBox.setData(result);
        return resultBox;

    }
    @PostMapping("/getcurrentUserLocation")
    @ResponseBody
    public ResultInfo getcurrentUserLocation(@RequestBody Map<String, Object> map, HttpServletRequest request) {

        int userIDInt = (int) map.get("userID");
        System.out.println("into getcurrentUserLocation");
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        List<Map<String, Object>> result = null;
//        String userID=String.valueOf(userIDInt);
        result = CommonDao.getcurrentUserLocation(userIDInt);

        System.out.println("result"+result.toString());
        resultBox.setData(result);
        return resultBox;

    }

    }
