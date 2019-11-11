/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;

/**
 *
 * @author Иван
 */
class Stakan {
    public long mrid;
    public long id;
    public List<List<String>> asks;
    public List<List<String>> bids;
    public long ts;
    public String ch;
    public long version;
    public long getId (){
        return id;
    } 
    public List<List<String>> getAsks (){
        return asks;
    }
    public List<List<String>> getBids (){
        return bids;
    }
    public long getMrId (){
        return mrid;
    }
    public long getTs (){
        return ts;
    } 
    public long getVersion (){
        return version;
    } 
    public String getCh (){
    return ch;
    }
    
}
