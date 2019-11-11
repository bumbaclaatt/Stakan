/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import au.com.bytecode.opencsv.CSVReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Иван
 */
public class WriteDeltasTest {
    
    
    @Test
    public void testMain() throws Exception {
    boolean Result =true;
    int StakanQuantity = 0;
    Basic Basic1 = new Basic();
    Delty Delty1 = new Delty();
    ArrayList<String> BasicList = Basic1.main();
    ArrayList<String> DeltyList = Delty1.main();
    StakanQuantity = BasicList.size();
    for (int k =0;k<=StakanQuantity;k++){
      if (BasicList.get(k).equals(DeltyList.get(k)) == false){
         Result = false;  
      }
    }   
     assertEquals(Result,true); 
    }
    
}
class Delty {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public ArrayList<String> main() throws IOException 
    {
        int i = 0 ;
        String StakanJson;
        int MaxNumberAsks ;
        int MaxNumberBids ;
        ArrayList<String> ReturnStakan = new ArrayList<String>();
        
        try
        (FileWriter fw = new FileWriter( "c:\\2.csv" )) {
            try
                (CSVReader reader = new CSVReader(new FileReader("c:\\5.csv"),'\n')) {
                String [] nextLine;
                while ((nextLine = reader.readNext()) != null)
                {
                    for(String token : nextLine)
                    {
                            StakanJson = token.substring(30);
                            Stakan current = GSON.fromJson(StakanJson,Stakan.class);
                            List<List<String>> CurrentAsks = current.getAsks();
                            List<List<String>> CurrentBids = current.getBids();
                            if (i==0){                              
                                fw.write(token+"\n");
                                i = i+1;
                            }
                            else{
                                MaxNumberAsks = CurrentAsks.size() - 1;
                                MaxNumberBids = CurrentBids.size() -1;
                                for (int k = 0;k <= MaxNumberAsks;k++){
                                    if (CurrentAsks.get(MaxNumberAsks-k).get(1).equals("0") == true){
                                        CurrentAsks.remove(MaxNumberAsks-k);
                                    }
                                } 
                                for (int k = 0;k <= MaxNumberBids;k++){
                                    if (CurrentBids.get(MaxNumberBids-k).get(1).equals("0") == true){
                                        CurrentBids.remove(MaxNumberBids-k);
                                    }
                                }
                                fw.write(token.substring(0,30)+"{\"mrid\":"+current.getMrId()+",\"id\":"+current.getId()+",\"bids\":"+CurrentBids.toString()+",\"asks\":"+CurrentAsks.toString()+",\"ts\":"+current.getTs()+",\"ch\":"+current.getCh()+",\"version\":"+current.getVersion()+"}"+"\n");
                            String l = token.substring(0,30)+"{\"mrid\":"+current.getMrId()+",\"id\":"+current.getId()+",\"bids\":"+CurrentBids.toString()+",\"asks\":"+CurrentAsks.toString()+",\"ts\":"+current.getTs()+",\"ch\":"+current.getCh()+",\"version\":"+current.getVersion()+"}";
                            ReturnStakan.add(l);
                            }
                            
                        
                    }
                }
            }
            catch (FileNotFoundException e) {
            }
        }
        return ReturnStakan;
    }
}
class Basic {
    public ArrayList<String> main() throws IOException 
    {
        int i=0;
        char Symbol14;
        char IndicatorStakan = 'd';
        ArrayList<String> BasicStakan = new ArrayList<String>();
            try
                (CSVReader reader = new CSVReader(new FileReader("c:\\3.csv"),',')) {
                String [] nextLine;
                while ((nextLine = reader.readNext()) != null)
                {
                    for(String token : nextLine)
                    {
                        Symbol14 = token.charAt(14);
                        if (Symbol14 == IndicatorStakan) {
                           BasicStakan.add(i,token);
                           i=i+1;
  
                        }
                    }
                }
            }  
            catch (FileNotFoundException e) {
            }
            
     return BasicStakan;       
    }
}

