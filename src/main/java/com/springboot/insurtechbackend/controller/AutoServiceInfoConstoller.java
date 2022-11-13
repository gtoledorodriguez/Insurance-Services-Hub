package com.springboot.insurtechbackend.controller;

import com.springboot.insurtechbackend.dao.AutoServiceDao;
import com.springboot.insurtechbackend.model.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/service")
public class AutoServiceInfoConstoller {
    //get all Service  info
    @PostMapping("/allService")
    @ResponseBody
    public ResultInfo getServiceList(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");

        result= AutoServiceDao.getAllServiceListOrByUserID("","2");
        resultBox.setData(result);
        return resultBox;
    }

    //Get all service data of this user.
    @PostMapping("/listByID")
    @ResponseBody
    public ResultInfo getUserService(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");

        String userId = (String) map.get("userID");
        if (userId==null){
            System.out.println("no userid");
            resultBox.setFlag("0");
            resultBox.setErrorMsg("no userid, process error");
            return resultBox;
        }
        // select if by id,then return
        result= AutoServiceDao.getAllServiceListOrByUserID(userId,"1");
        // need implement
        resultBox.setData(result);
        return resultBox;

    }


}
