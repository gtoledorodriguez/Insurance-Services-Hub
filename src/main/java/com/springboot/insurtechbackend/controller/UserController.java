package com.springboot.insurtechbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.insurtechbackend.CommonUtils.Utilities;
import com.springboot.insurtechbackend.dao.UserProcessDao;
import com.springboot.insurtechbackend.model.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    @ResponseBody
//通过@ResponseBody 方式，需要在@RequestMapping 中，添加produces = "application/json;charset=UTF-8",设定返回值的类型。
    public ResultInfo loginHandle(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        System.out.println("xx" + map.toString());
//        传入的数据
        String Password = (String) map.get("password");
        String email = (String) map.get("email");
        System.out.println("Password" + Password);
        System.out.println("email" + email);
        String UserName = email;
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        String responseJson = null;
        try {
            result = UserProcessDao.checkUserLogin("", "");

        } catch (Exception e) {
            System.out.println("Query database error");
            resultBox.setFlag("0");
            resultBox.setErrorMsg("process error");
            responseJson = JSONObject.toJSONString(resultBox);
            return resultBox;
        }
        int flag = 1;
        if (result.size() < 1) {
            resultBox.setFlag("0");
            resultBox.setErrorMsg("not this user info");
        } else {
            for (int i = 0; i < result.size(); i++) {
//                HashMap itemMap = (HashMap) result.get(i);
                if (UserName.equals(result.get(i).get("Email")) && Password.equals(result.get(i).get("Password"))) {
                    flag = 0;
                    System.out.println("find data");
                    HttpSession session = request.getSession(true);
                    session.setAttribute("UserName", result.get(i).get("UserName"));
                    session.setAttribute("Password", result.get(i).get("Password"));
                    session.setAttribute("Email", result.get(i).get("Email"));
                    session.setAttribute("UserType", result.get(i).get("UserType"));
                    resultBox.setData(result.get(i));
                    responseJson = JSONObject.toJSONString(resultBox);
                    return resultBox;
                }
            }
        }
        if (flag == 1) {
            resultBox.setFlag("0");
            resultBox.setErrorMsg("not this user info");
            responseJson = JSONObject.toJSONString(resultBox);
            System.out.println("result" + responseJson);
            return resultBox;
        } else {
            responseJson = JSONObject.toJSONString(resultBox);
            System.out.println("result" + responseJson);
            return resultBox;
        }
//        return resultBox;
    }

    @PostMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Utilities utility = new Utilities(request, null);
        utility.logout(); //delect session
        System.out.println("into HttpServlet");

        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        String responseJson = JSONObject.toJSONString(resultBox);
        System.out.println();
        return responseJson;

    }
    @PostMapping("/registration")
    @ResponseBody
    //regist user info
    public ResultInfo registration(@RequestBody Map<String, Object> map,HttpServletRequest request, HttpServletResponse response) {
        System.out.println("into registration");
        System.out.println("xx" + map.toString());
//        传入的数据

//        String UserName = (String) map.get("UserName");
        String Password = (String) map.get("Password");
        String Repassword = (String) map.get("Repassword");
        String UserType = (String) map.get("UserType");
        String CreditCardID = (String) map.get("CreditCardID");

        String AddressID = (String) map.get("AddressID");
        String Email = (String) map.get("Email");
        String LastName = (String) map.get("LastName");
        String FirstName = (String) map.get("FirstName");
        String DateOfBirth = (String) map.get("DateOfBirth");
        String PhoneNo = (String) map.get("PhoneNo");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//改为需要的东西
        String UserName = FirstName+" "+ LastName;

        String address = (String) map.get("address");
        String city = (String) map.get("city");
        String state = (String) map.get("state");
        String zipCode = (String) map.get("zipCode");
        String apt = (String) map.get("apt");
        String fullAddress =  address+", "+city+", "+state+", "+zipCode;


        Date realDate = null;
        try {
            realDate =formatter.parse(DateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        if (!Password.equals(Repassword)) {
            resultBox.setFlag("0");
            resultBox.setErrorMsg("Passwords doesn't match!");
        }else {
            HashMap<String, Object> hm=new HashMap<String, Object>();
            hm=UserProcessDao.selectUser();
            List<Map<String, Object>> result  = UserProcessDao.checkUserLogin("","");
            int flag=0;
            for (int i = 0; i < result.size(); i++) {
                if (Email.equals( result.get(i).get("Email"))){
                    flag =1;
                }
            }
            if(flag ==1){
                resultBox.setFlag("0");
                resultBox.setErrorMsg("User already exist as " + Email);
            }else {
                //inser data
                UserProcessDao.registerData(UserName,Password,UserType,CreditCardID, AddressID,realDate,Email,PhoneNo,LastName,FirstName
                ,address,city,state,zipCode,apt,fullAddress);
                HttpSession session = request.getSession(true);
            }
        }
        String reJson = JSONObject.toJSONString(resultBox);
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        System.out.println("jsonxx"+reJson);
        resultBox.setData(reJson);
        System.out.println("jsonxx"+reJson);
         return resultBox;
    }
}
