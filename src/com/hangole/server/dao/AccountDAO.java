package com.hangole.server.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

/**
 * Created by dsm_025 on 2017-03-27.
 */
public class AccountDAO {
    private static AccountDAO instance = new AccountDAO();
    private MongoClient mongoClient;
    private DB db;
    private DBCollection coll;

    private AccountDAO(){
        mongoClient = new MongoClient("localhost", 27017);
        WriteConcern w = new WriteConcern(1, 2000);
        mongoClient.setWriteConcern(w);
        db = mongoClient.getDB("");
        coll = db.getCollection("");
    }

    public static AccountDAO getInstance(){
        return instance;
    }
}
