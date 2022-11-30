package com.springboot.insurtechbackend.dao;
import com.alibaba.fastjson.JSONObject;
import com.springboot.insurtechbackend.CommonUtils.SingelJdbcConnect;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

public class UserProcessDao {

    public static  List<Map<String, Object>>  checkUserLogin( String userName ,String passWord) {
        System.out.println("into checkUserLogin");
        //run sql must add below code
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        System.out.println("1");
        List<Map<String, Object>>  result = template.queryForList("select *  from user " +
                "left join vehicleinfo on user.UserID =vehicleinfo.userID " +
                "left join  arpersonalinfo  on user.UserID =arpersonalinfo.userID");
        System.out.println( "  "+result.get(1).get("FirstName"));
        System.out.println("XXX "+result.toString());
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx"+jsonxx);
        return result ;

    }
    //the main function for testing sql
//    This method is used to test whether the sql is correct
    public static void main(String[] args) {
//        getUserList();
//        checkUserLogin("junlong","1");


//        JdbcTemplate template =  SingelJdbcConnect.showSingleTyepValue();
//
//        String sql1 = "INSERT INTO address(UserID,Address,Apt,City, State,ZipCode) "
//                + "VALUES (?,?,?,?,?,?);";
//        int daddressId = template.update(sql1,1,"ss","ss","ss","ss","ss");
//        System.out.println("daddressId"+daddressId);

//        get Mainkey of address table

//        selectUser();

//        Date testTime=new Date();
//        registerData("tom","123123123","Manager","123123123","12",testTime,"weri@sdf","123123","momer","zhao");

    }
    public static void registerData(String UserName, String Password, String UserType,
                                    String CreditCardID, String AddressID, Date DateOfBirth,
                                    String Email, String PhoneNo, String LastName,String FirstName,
                                    String address ,String city ,String state ,String zipCode , String apt,String fullAddress) {
        System.out.println("into registerData");
        JdbcTemplate template =  SingelJdbcConnect.showSingleTyepValue();

        try {
            String sql = "INSERT INTO user(UserName,Password,UserType,CreditCardID, AddressID,Email,PhoneNo,LastName,FirstName,DateOfBirth,FullAddress" +
                    ",Address,Apt,City,State,ZipCode) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            int dd= template.update(sql,UserName,Password,UserType,CreditCardID, AddressID,Email,PhoneNo
                    ,LastName,FirstName,DateOfBirth,fullAddress,address,apt,city,state,zipCode);
            System.out.println("dddd"+dd);
        } catch (Exception e) {
            System.out.println("insert data error");
        }


        Map<String, Object> resulMap =UserProcessDao.selectUserbyEmail( Email);
        String UserID = resulMap.get("UserID").toString();
        System.out.println("UserID"+UserID);
        System.out.println("UserID"+resulMap.get("UserID"));



    }
    public static HashMap<String, Object> selectUser()   {
        HashMap<String, Object> hm = new HashMap<String, Object>();
        try {
            JdbcTemplate template =  SingelJdbcConnect.showSingleTyepValue();
            String sql = "select * from  User";
            ResultSet rs = (ResultSet) template.queryForMap(sql);
            System.out.println("rs"+rs);
            while (rs.next()) {
                hm.put(rs.getString("UserName"), JSONObject.toJSONString(rs));
                System.out.println("Json data"+JSONObject.toJSONString(rs));
            }
        } catch (Exception e) {
            System.out.println("some worry");
        }
        return hm;
    }
    public static Map<String, Object> selectUserbyEmail(String Email)   {
        Map<String, Object> hm = new HashMap<String, Object>();
        try {
            JdbcTemplate template =  SingelJdbcConnect.showSingleTyepValue();
            String sql = "select * from  User where Email=? ";
            hm = template.queryForMap(sql,Email);
            System.out.println("rs"+hm.toString());
        } catch (Exception e) {
            System.out.println("some worry");
        }
        return hm;
    }
    public static List<Map<String, Object>>  getUserByType(String UserType)   {
        List<Map<String, Object>>  result;
        JdbcTemplate template =  SingelJdbcConnect.showSingleTyepValue();
        String sql = "select * from  User where UserType=? ";
        result = template.queryForList(sql,UserType);
        System.out.println("rs"+result.toString());
        return result;

    }
    public static List<Map<String, Object>>  getUserByType(int userID)   {

        List<Map<String, Object>>  result;
        JdbcTemplate template =  SingelJdbcConnect.showSingleTyepValue();
        String sql = "select * from  User where UserID=? ";
        result = template.queryForList(sql,userID);
        System.out.println("rs"+result.toString());
        return result;
    }
    public static int updateUserInfoById(int UserID,String UserName, String Password, String UserType,
                                    String CreditCardID, String AddressID, Date DateOfBirth,
                                    String Email, String PhoneNo, String LastName,String FirstName,
                                    String address ,String city ,String state ,String zipCode , String apt,String fullAddress) {
        System.out.println("into updateUserInfoById");
        JdbcTemplate template =  SingelJdbcConnect.showSingleTyepValue();



        String SqlStr ="update  user  set  UserName=?, Password=?, UserType=?, " +
                "CreditCardID=?, AddressID=?, DateOfBirth=?, Email=? , PhoneNo=?, LastName=?, FirstName=?, " +
                "Address=?, City=?, State=?, ZipCode=?, Apt=?, FullAddress=?where UserID= ?" ;
        int result = template.update(SqlStr,UserName,Password,UserType,CreditCardID,AddressID,DateOfBirth,Email,PhoneNo,
                LastName,FirstName,address,city,state,zipCode,apt,fullAddress,UserID);
        System.out.println("result"+result);

        return result;

    }


}
