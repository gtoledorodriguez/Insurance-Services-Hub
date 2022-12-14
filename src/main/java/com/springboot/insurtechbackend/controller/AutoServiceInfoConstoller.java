package com.springboot.insurtechbackend.controller;

import com.springboot.insurtechbackend.dao.AutoServiceDao;
import com.springboot.insurtechbackend.model.AutoService;
import com.springboot.insurtechbackend.model.ResultInfo;
import com.springboot.insurtechbackend.repository.ServiceRepository;
import com.springboot.insurtechbackend.respository.ServiceRepositoryCURD;
import org.omg.CORBA.portable.ValueOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/service")
//
public class AutoServiceInfoConstoller {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ServiceRepositoryCURD serviceRepositoryCURD;
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
    @PostMapping("/getAllServiceDate")
    @ResponseBody
    public ResultInfo getAllServiceDate(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");

        result= AutoServiceDao.getAllServiceDate();
        resultBox.setData(result);
        return resultBox;
    }

    @PostMapping("/getUserAllAuto_service")
    @ResponseBody
    public ResultInfo getUserAllAuto_service(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        List<Map<String, Object>> result = null;
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        int userId = (int) map.get("userID");
        System.out.println("userID"+userId);
        result= AutoServiceDao.getUserAllAuto_service(userId);
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
    @GetMapping("/{type}")
//    public ResponseEntity<List<AutoService>> findAll() {
//        List<AutoService> services = new ArrayList<>();
//        services = serviceRepository.findAll();
//        System.out.println(services);
//        return new ResponseEntity<>(services, HttpStatus.OK);
//    }
    public ResponseEntity<List<AutoService>> getAutoServicesByType(@PathVariable("type") int type) {
        try{
            System.out.println("into getAutoServicesByType function");
            List<AutoService> services = new ArrayList<AutoService>();

            serviceRepository.findAllByType(type).forEach(services::add);
            System.out.println(services);

            if (services.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/testReinventory")
    @ResponseBody
    public VerifyError testReinventory(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        System.out.println("into testReinventory");
        Optional<AutoService> sdf1 =serviceRepositoryCURD.findById(1);
        System.out.println(sdf1.get().getCollision());

        return null;

    }
//    @PostMapping("/delectService")
//    @ResponseBody
//    public ResultInfo delectService(@RequestBody Map<String, Object> map, HttpServletRequest request) {
//
//    }
    @PostMapping("/delectService")
    @ResponseBody
    public ResultInfo delectService(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        System.out.println("into delectService");
        AutoService autoService =new AutoService();
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        int serviceId = (int)map.get("serviceId");
        int re = AutoServiceDao.delectServiceDateById(serviceId);
        resultBox.setData(re);
        return  resultBox;
    }

    @PostMapping("/insertService")
    @ResponseBody
    public ResultInfo insertService(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        System.out.println("into insertService");
        AutoService autoService =new AutoService();
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        //inser data
        autoService.setService_name((String) map.get("service_name"));
        autoService.setCollision((int) map.get("collision"));
        autoService.setComprehensive((int)map.get("comprehensive"));
        autoService.setCreate_time(new Date());
        autoService.setLiability_property((int) map.get("liability_property"));
        autoService.setLiability_bodily((int) map.get("liability_bodily"));
        autoService.setMedical((int) map.get("medical"));
        autoService.setUm_bodily((int) map.get("um_bodily"));
        autoService.setUm_property((int) map.get("um_property"));
        autoService.setRental((int) map.get("rental"));
        autoService.setType((int) map.get("type"));
        AutoService save = serviceRepositoryCURD.save(autoService);
        System.out.println("return"+save);
        resultBox.setData(save);
        return resultBox;

    }
    @PostMapping("/updateService")
    @ResponseBody
    public ResultInfo updateService(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        System.out.println("into updateService");
        AutoService autoService =new AutoService();
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        System.out.println("into updateService");

        int serviceId = (int)map.get("serviceId");
        if(String.valueOf(serviceId).equals("")){
            resultBox.setFlag("0");
            resultBox.setErrorMsg("error Parameter error");
            return resultBox;
        }
        autoService.setAuto_service_id(serviceId);
        autoService.setService_name((String) map.get("service_name"));
        autoService.setCollision((int) map.get("collision"));
        autoService.setComprehensive((int)map.get("comprehensive"));
        autoService.setCreate_time(new Date());
        autoService.setLiability_property((int) map.get("liability_property"));
        autoService.setMedical((int) map.get("medical"));
        autoService.setUm_bodily((int) map.get("um_bodily"));
        autoService.setUm_property((int) map.get("um_property"));
        autoService.setRental((int) map.get("rental"));
        autoService.setType((int) map.get("type"));
        AutoService save = serviceRepositoryCURD.save(autoService);
        System.out.println("return"+save);
        resultBox.setData(save);
        return resultBox;

    }
    @PostMapping("/getServiceByServiceID")
    @ResponseBody
    public ResultInfo getServiceByServiceID(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        ResultInfo resultBox = new ResultInfo();
        resultBox.setFlag("1");
        resultBox.setErrorMsg("successfully");
        System.out.println("into updateService");
        List<Map<String, Object>> result = null;

        int serviceId = (int)map.get("serviceId");



        result= AutoServiceDao.getServiceByServiceID(serviceId);
        resultBox.setData(result);
        return resultBox;

    }

    }



