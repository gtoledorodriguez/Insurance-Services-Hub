package com.springboot.insurtechbackend.controller;

import com.springboot.insurtechbackend.dao.AutoServiceDao;
import com.springboot.insurtechbackend.dao.TrendingDao;
import com.springboot.insurtechbackend.model.AutoService;
import com.springboot.insurtechbackend.model.Bestrating;
import com.springboot.insurtechbackend.model.ResultInfo;
import com.springboot.insurtechbackend.repository.ServiceRepository;
import com.springboot.insurtechbackend.respository.ServiceRepositoryCURD;
import org.omg.CORBA.portable.ValueOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//@Controller
@RequestMapping("/trending")
//
public class TrendingController {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ServiceRepositoryCURD serviceRepositoryCURD;
    //get all Service  info
    // @PostMapping("/allService")
    // @ResponseBody
    // public ResultInfo getServiceList(@RequestBody Map<String, Object> map, HttpServletRequest request) {
    //     List<Map<String, Object>> result = null;
    //     ResultInfo resultBox = new ResultInfo();
    //     resultBox.setFlag("1");
    //     resultBox.setErrorMsg("successfully");
    //     result= AutoServiceDao.getAllServiceListOrByUserID("","2");
    //     resultBox.setData(result);
    //     return resultBox;
    // }

    @GetMapping("/getTopServicesByMostBought")
//    @PostMapping("/getTopServicesByMostBought")
    @ResponseBody
    public ResultInfo getTopServicesByMostBought_service(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");

        result= TrendingDao.getAllServicesByMostBought();
        resultBox.setData(result);
        return resultBox;
    }

    @GetMapping("/getBestServicesSoldByAgent")
//    @PostMapping("/getBestServicesSoldByAgent")
    @ResponseBody
    public ResultInfo getBestServicesSoldByAgent_service(@RequestParam(name="agentID",defaultValue="-1") int AgentID, @RequestBody Map<String, Object> map, HttpServletRequest request) {
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
//        System.out.println(map.keySet());
//        System.out.println(map.get("userID"));
//        int AgentID = Integer.valueOf((String) map.get("userID"));
        System.out.println(AgentID);
        //int AgentID = (int) map.get("userID");
        if (!(AgentID > -1)){
            System.out.println("agentID");
            resultBox.setFlag("0");
            resultBox.setErrorMsg("no agentID, process error");
            return resultBox;
        }
        System.out.println("agentID "+AgentID);
        result= TrendingDao.getAllServicesSoldByAgent(AgentID);
        resultBox.setData(result);
        return resultBox;
    }

    @GetMapping("/getBestAgentsByService")
//    @PostMapping("/getBestAgentsByService")
    @ResponseBody
    public ResultInfo getBestAgentsByService_service(@RequestParam(name="autoServiceID",defaultValue="-1")  int AutoServiceID, @RequestBody Map<String, Object> map, HttpServletRequest request) {
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
//        System.out.println(map.keySet());
//        System.out.println(map.get("userID"));
//        int AutoServiceID = Integer.valueOf((String) map.get("userID"));
        System.out.println(AutoServiceID);
        //int AutoServiceID = (int) map.get("userID");
        if (!(AutoServiceID > -1)){
            System.out.println("autoServiceID");
            resultBox.setFlag("0");
            resultBox.setErrorMsg("no autoServiceID, process error");
            return resultBox;
        }
        System.out.println("autoServiceID "+AutoServiceID);
        result= TrendingDao.getBestAgentsByService(AutoServiceID);
        resultBox.setData(result);
        return resultBox;
    }

    @GetMapping("/getTopServicesByBestRated")
//    @PostMapping("/getTopServicesByBestRated")
    @ResponseBody
    public ResultInfo getTopServicesByBestRated_service(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        ArrayList<Bestrating> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");

        result= TrendingDao.getTopServicesByBestRated();
        resultBox.setData(result);
        return resultBox;
    }

    }



