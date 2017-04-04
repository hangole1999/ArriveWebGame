package com.hangole.server.dao;

import com.mongodb.*;


/**
 * Created by dsm_025 on 2017-03-27.
 */
public class AccountDAO {
    private static AccountDAO instance = new AccountDAO();
    private MongoClient mongoClient;
    private DB db;
    private DBCollection coll;

    private AccountDAO()  {
        mongoClient = new MongoClient("localhost", 27017);
        WriteConcern w = new WriteConcern(1, 2000);
        mongoClient.setWriteConcern(w);
        db = mongoClient.getDB("users");
        coll = db.getCollection("users");
    }

    public static AccountDAO getInstance(){
        return instance;
    }

    public String getPasswordFromID(String id){
        BasicDBObject basicDBObject = new BasicDBObject("id", id);
        basicDBObject.get("id");
        DBCursor cursor = coll.find(basicDBObject);
        BasicDBObject temp;
        if((temp = (BasicDBObject) cursor.next()) == null){
            return null;
        }
        return temp.get("psw").toString();
    }

    public void insertSignUpInfo(String email, String pwd) {
        try {
            boolean isInsert = false;

            DBCursor cursor = coll.find();
            while (cursor.hasNext()) {
                if (cursor.next().get("email").equals(email)) {
                    System.out.println("이메일 중복!");
                    isInsert = true;
                }
            }

            if (isInsert == false) {
                BasicDBObject doc = new BasicDBObject();
                doc.put("email", email);
                doc.put("password", pwd);

                coll.insert(doc);
            }
        }catch (MongoQueryException m){
            System.out.println("insertSignUpInfo 메소드 오류");
            m.printStackTrace();
        }
    }
}