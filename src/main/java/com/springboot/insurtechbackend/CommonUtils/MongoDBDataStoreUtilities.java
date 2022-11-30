package com.springboot.insurtechbackend.CommonUtils;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import com.springboot.insurtechbackend.model.Bestrating;
import com.springboot.insurtechbackend.model.Mostsold;
import com.springboot.insurtechbackend.model.Mostsoldzip;
import com.springboot.insurtechbackend.model.Review;

import java.util.*;

public class MongoDBDataStoreUtilities {
    static DBCollection customerReviews;

    public static DBCollection getConnection() {
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        DB db = mongo.getDB("ServiceReviews");
        customerReviews = db.getCollection("customerReviews");
        return customerReviews;
    }


    public static String insertReview(String name, String age, String occupation, String state, String zipcode, String maker, String type, String year, String milage, String vehicle_type, String service, String rating, String review_text) {
        try {
            getConnection();
            System.out.println(name + age + occupation + state + zipcode + maker + type + year + milage + vehicle_type + service + rating + review_text);
            BasicDBObject doc = new BasicDBObject("title", "customerReviews").
                    append("userName", name).
                    append("userAge", Integer.parseInt(age)).
                    append("userOccupation", occupation).
                    append("userState", state).
                    append("userZipcode", zipcode).
                    append("vehicleMaker", maker).
                    append("vehicleType", type).
                    append("vehicleYear", Integer.parseInt(year)).
                    append("vehicleMilage", milage).
                    append("serviceVehicleType", vehicle_type).
                    append("serviceChosen", service).
                    append("reviewRating", Integer.parseInt(rating)).
                    append("reviewText", review_text);
            customerReviews.insert(doc);
            return "Successfull";
        } catch (Exception e) {
            return "UnSuccessfull";
        }

    }
//userid,serviceVehicleType,userOccupation,productuserState,vehicleMaker,manufacturername,reviewrating,vehicleYear,vehicleMilage,serviceVehicleType,serviceChosen,manufacturerrebate,zipcode,retailercity,retailerstate,reviewdate,reviewtext

    public static HashMap<String, ArrayList<Review>> selectReview() {
        HashMap<String, ArrayList<Review>> reviews = null;

        try {

            getConnection();
            DBCursor cursor = customerReviews.find();
            reviews = new HashMap<String, ArrayList<Review>>();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!reviews.containsKey(obj.getString("serviceVehicleType"))) {
                    ArrayList<Review> arr = new ArrayList<Review>();
                    reviews.put(obj.getString("serviceVehicleType"), arr);
                }
                ArrayList<Review> listReview = reviews.get(obj.getString("serviceVehicleType"));
                Review review = new Review(obj.getString("userName"), obj.getString("userAge"), obj.getString("userOccupation"), obj.getString("userState"), obj.getString("userZipcode"), obj.getString("vehicleMaker"), obj.getString("vehicleType"),
                        obj.getString("vehicleYear"), obj.getString("vehicleMilage"), obj.getString("serviceVehicleType"), obj.getString("serviceChosen"), obj.getString("reviewRating"), obj.getString("reviewText"));
                //add to review hashmap
                listReview.add(review);

            }
            return reviews;
        } catch (Exception e) {
            reviews = null;
            return reviews;
        }


    }


    public static ArrayList<Bestrating> topProducts() {
        ArrayList<Bestrating> Bestrate = new ArrayList<Bestrating>();
        try {

            getConnection();
            int retlimit = 5;
            DBObject sort = new BasicDBObject();
            sort.put("reviewRating", -1);
            DBCursor cursor = customerReviews.find().limit(retlimit).sort(sort);
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                String prodcutnm = obj.get("serviceVehicleType").toString();
                String service = obj.get("serviceChosen").toString();
                String rating = obj.get("reviewRating").toString();
                if(!(rating.equals("-1"))){
                    Bestrating best = new Bestrating(prodcutnm, service,rating);
                    Bestrate.add(best);
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Bestrate;
    }

    public static ArrayList<Mostsoldzip> mostsoldZip() {
        ArrayList<Mostsoldzip> mostsoldzip = new ArrayList<Mostsoldzip>();
        try {

	 /*MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);

		DB db = mongo.getDB("ServiceReviews");
		customerReviews= db.getCollection("customerReviews");*/
            getConnection();
            DBObject groupProducts = new BasicDBObject("_id", "$userZipcode");
            groupProducts.put("count", new BasicDBObject("$sum", 1));
            DBObject group = new BasicDBObject("$group", groupProducts);
            DBObject limit = new BasicDBObject();
            limit = new BasicDBObject("$limit", 5);

            DBObject sortFields = new BasicDBObject("count", -1);
            DBObject sort = new BasicDBObject("$sort", sortFields);
            AggregationOutput output = customerReviews.aggregate(group, sort, limit);
            for (DBObject res : output.results()) {

                String zipcode = (res.get("_id")).toString();
                String count = (res.get("count")).toString();
                Mostsoldzip mostsldzip = new Mostsoldzip(zipcode, count);
                mostsoldzip.add(mostsldzip);

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mostsoldzip;
    }

    public static ArrayList<Mostsold> mostsoldProducts() {
        ArrayList<Mostsold> mostsold = new ArrayList<Mostsold>();
        try {


            MongoClient mongo;
            mongo = new MongoClient("localhost", 27017);

            DB db = mongo.getDB("ServiceReviews");
            customerReviews = db.getCollection("customerReviews");
            DBObject groupProducts = new BasicDBObject("_id", "$serviceVehicleType");
            groupProducts.put("count", new BasicDBObject("$sum", 1));
            DBObject group = new BasicDBObject("$group", groupProducts);
            DBObject limit = new BasicDBObject();
            limit = new BasicDBObject("$limit", 5);

            DBObject sortFields = new BasicDBObject("count", -1);
            DBObject sort = new BasicDBObject("$sort", sortFields);
            AggregationOutput output = customerReviews.aggregate(group, sort, limit);

            for (DBObject res : output.results()) {


                String servicename = (res.get("_id")).toString();
                String count = (res.get("count")).toString();
                Mostsold mostsld = new Mostsold(servicename, count);
                mostsold.add(mostsld);

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mostsold;
    }

    //Get all the reviews grouped by product and zip code;
    public static ArrayList<Review> selectReviewForChart() {


        ArrayList<Review> reviewList = new ArrayList<Review>();
        try {

            getConnection();
            Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
            dbObjIdMap.put("userZipcode", "$userZipcode");
            dbObjIdMap.put("serviceVehicleType", "$serviceVehicleType");
            DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
            groupFields.put("count", new BasicDBObject("$sum", 1));
            DBObject group = new BasicDBObject("$group", groupFields);

            DBObject projectFields = new BasicDBObject("_id", 0);
            projectFields.put("userZipcode", "$_id");
            projectFields.put("serviceVehicleType", "$serviceVehicleType");
            projectFields.put("reviewCount", "$count");
            DBObject project = new BasicDBObject("$project", projectFields);

            DBObject sort = new BasicDBObject();
            sort.put("reviewCount", -1);

            DBObject orderby = new BasicDBObject();
            orderby = new BasicDBObject("$sort",sort);


            AggregationOutput aggregate = customerReviews.aggregate(group, project, orderby);

            for (DBObject result : aggregate.results()) {

                BasicDBObject obj = (BasicDBObject) result;
                Object o = com.mongodb.util.JSON.parse(obj.getString("userZipcode"));
                BasicDBObject dbObj = (BasicDBObject) o;
                Review review = new Review(dbObj.getString("serviceVehicleType"), dbObj.getString("userZipcode"),
                        obj.getString("reviewCount"), null);
                reviewList.add(review);

            }
            return reviewList;

        }

        catch (

                Exception e) {
            reviewList = null;

            return reviewList;
        }

    }


}
