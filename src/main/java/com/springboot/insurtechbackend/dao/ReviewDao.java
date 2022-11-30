package com.springboot.insurtechbackend.dao;

import com.alibaba.fastjson.JSONObject;
import com.springboot.insurtechbackend.CommonUtils.MongoDBDataStoreUtilities;
import com.springboot.insurtechbackend.CommonUtils.SingelJdbcConnect;
import com.springboot.insurtechbackend.model.Review;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class ReviewDao {
    static DataSource dataSource = null;
    static Connection conn = null;

    public static void main(String[] args) {
        // AutoServiceDao.insertServiceDate();

        // AutoServiceDao.delectServiceDateById(31);
        // AutoServiceDao.getAllServiceDate();
        // AutoServiceDao.getAllServiceListOrByUserID("1","1");
    }

    // auto_service table
//    public static List<Map<String, Object>> getAllServicesByMostBought() {
//        System.out.println("into getAllServicesByMostBought");
//        List<Map<String, Object>> result;
//        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
//        String SqlStr = "";
//        SqlStr = SqlStr
//                + "select AutoServiceID, count(*) as sold FROM insurancedbz.serviceorder GROUP BY AutoServiceID order by sold desc limit 5;";
//        result = template.queryForList(SqlStr);
//        String jsonxx = JSONObject.toJSONString(result);
//        System.out.println("jsonxx" + jsonxx);
//        return result;
//
//    }
//
//    public static List<Map<String, Object>> getAllServicesSoldByAgent(int agentID) {
//        System.out.println("into getAllServicesSoldByAgent");
//        System.out.println(agentID);
//        List<Map<String, Object>> result;
//        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
//        String SqlStr = "select AgentID ,AutoServiceID, count(*) as sold FROM insurancedbz.serviceorder Where AgentID=? GROUP BY AutoServiceID order by sold desc limit 5;";
//        result = template.queryForList(SqlStr, agentID);
//        String jsonxx = JSONObject.toJSONString(result);
//        System.out.println("jsonxx" + jsonxx);
//        return result;
//    }
//
//    public static List<Map<String, Object>> getBestAgentsByService(int AutoServiceID) {
//        System.out.println("into getBestAgentsByService");
//        System.out.println(AutoServiceID);
//        List<Map<String, Object>> result;
//        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
//        String SqlStr = "select AgentID FROM insurancedbz.serviceorder Where AutoServiceID=? GROUP BY AgentID order by count(*) desc limit 5;";
//        result = template.queryForList(SqlStr, AutoServiceID);
//        String jsonxx = JSONObject.toJSONString(result);
//        System.out.println("jsonxx" + jsonxx);
//        return result;
//    }

    public static String storeReview(String name, String age, String occupation, String state, String zipcode, String maker, String type, String year, String milage, String vehicle_type, String service, String rating, String review_text) {
        System.out.println("into storeReview");
        //System.out.println(AutoServiceID);

        String message = MongoDBDataStoreUtilities.insertReview(name, age, occupation, state, zipcode, maker, type, year, milage, vehicle_type, service, rating, review_text);
//        List<Map<String, Object>> result;
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        String result = "Empty";
        if (!message.equals("Successfull")) {
            result = "Unsuccessful";
        } else {
            HashMap<String, ArrayList<Review>> reviews = new HashMap<String, ArrayList<Review>>();
            try {
                reviews = MongoDBDataStoreUtilities.selectReview();
            } catch (Exception e) {

            }
            if (reviews == null) {
                reviews = new HashMap<String, ArrayList<Review>>();
            }
            // if there exist product review already add it into same list for vehicle_type or create a new record with product name

            if (!reviews.containsKey(vehicle_type)) {
                ArrayList<Review> arr = new ArrayList<Review>();
                reviews.put(vehicle_type, arr);
            }
            ArrayList<Review> listReview = reviews.get(vehicle_type);
            Review review = new Review(name, age, occupation, state, zipcode, maker, type, year, milage, vehicle_type, service, rating, review_text);
            listReview.add(review);

            result = "Successfull";
            // add Reviews into database
//            String jsonxx = JSONObject.toJSONString(result);
//            return "Successfull";
        }

//        List<Map<String, Object>> result;
//        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
//        String SqlStr = "select AgentID FROM insurancedbz.serviceorder Where AutoServiceID=? GROUP BY AgentID order by count(*) desc limit 5;";
//        result = template.queryForList(SqlStr, AutoServiceID);
        String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx" + jsonxx);
        return result;
    }

    public static ArrayList<Review> viewReviewsByVehicleType(String vehicle_type) {
        System.out.println("into viewReviewsByVehicleType");
        System.out.println(vehicle_type);
        ArrayList<Review> result = new ArrayList<Review>();
        JdbcTemplate template = SingelJdbcConnect.showSingleTyepValue();
        //String SqlStr = "select AgentID ,AutoServiceID, count(*) as sold FROM insurancedbz.serviceorder Where AgentID=? GROUP BY AutoServiceID order by sold desc limit 5;";
        //result = template.queryForList(SqlStr, agentID);

        try {
        String vehicle_type_service = vehicle_type;
        HashMap<String, ArrayList<Review>> hm = MongoDBDataStoreUtilities.selectReview();
        String userName = "";
        String reviewRating = "";
        String reviewText = "";
        String price = "";
        String zipcode = "";

        //if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
        if (hm == null) {
            System.out.println("Mongo Db server is not up and running");
        } else {
            if (!hm.containsKey(vehicle_type_service)) {
                System.out.println("There are no reviews for this product.");
            } else {
                result = hm.get(vehicle_type_service);
                for (Review r : hm.get(vehicle_type_service)) {

                    vehicle_type_service = r.getVehicleType();

                    userName = r.getUserName();

//                    price = r.getPrice();

                    zipcode = r.getUserZipcode();

                    reviewRating = r.getReviewRating().toString();

                    reviewText = r.getReviewText();

                }

            }
        }

    }catch( Exception e){
        System.out.println(e.getMessage());
    }


    String jsonxx = JSONObject.toJSONString(result);
        System.out.println("jsonxx"+jsonxx);
        return result;
}
}
