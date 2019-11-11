package com.mycompany.mavenproject1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 import au.com.bytecode.opencsv.CSVReader;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;


public class WriteDeltas {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) throws IOException 
    {
        int i = 0 ;
        char Symbol14;
        char IndicatorStakan = 'd';
        String StakanJson;
        List<List<String>> lastArrayAsks = null;
        List<List<String>> lastArrayBids = null;
        
        try
        (FileWriter fw = new FileWriter( "c:\\5.csv" )) {
            try
                (CSVReader reader = new CSVReader(new FileReader("c:\\3.csv"),',')) {
                String [] nextLine;
                while ((nextLine = reader.readNext()) != null)
                {
                    for(String token : nextLine)
                    {
                        Symbol14 = token.charAt(14);
                        if (Symbol14 == IndicatorStakan) {
                            StakanJson = token.substring(30);
                            Stakan currentStakan = GSON.fromJson(StakanJson,Stakan.class);
                            List<List<String>> currentArrayAsks = currentStakan.getAsks();
                            List<List<String>> currentArrayBids = currentStakan.getBids();
                            if (i==0){                              
                                fw.write(token+"\n");
                                lastArrayAsks = currentStakan.getAsks();
                                lastArrayBids = currentStakan.getBids();
                                i = i+1;
                            }
                            else{
                                List<List<String>> resultArrayAsks = currentStakan.getAsks();
                                List<List<String>> resultArrayBids = currentStakan.getBids();
                                for (int k = 0;k <= 149;k++){                                   
                                    for (int l = 0;l<= 149;l++) {
                                        if (lastArrayAsks.get(k).get(0).equals(currentArrayAsks.get(l).get(0)) == true ){
                                            l= 149;                                            
                                        }
                                        else if (l == 149){
                                            if (lastArrayAsks.get(k).get(0).equals(currentArrayAsks.get(l).get(0)) == false){
                                                ArrayList<String> DeltaAsk = new ArrayList<String>();
                                                DeltaAsk.add(lastArrayAsks.get(k).get(0));
                                                DeltaAsk.add("0");
                                                resultArrayAsks.add(DeltaAsk);
                                            }
                                            
                                        }
                                    }
                                } 
                                for (int k = 0;k <= 149;k++){
                                    for (int l = 0;l<= 149;l++) {
                                        if (lastArrayBids.get(k).get(0).equals(currentArrayBids.get(l).get(0)) == true ){
                                            l= 149;
                                        }
                                        else if (l == 149){
                                            if (lastArrayBids.get(k).get(0).equals(currentArrayBids.get(l).get(0)) == false ){
                                       
                                                ArrayList<String> DeltaBid = new ArrayList<String>();
                                                DeltaBid.add(lastArrayBids.get(k).get(0));
                                                DeltaBid.add("0");
                                                resultArrayBids.add(DeltaBid);
                                            }
                                            
                                        }
                                    }
                                }
                                fw.write(token.substring(0,30)+"{\"mrid\":"+currentStakan.getMrId()+",\"id\":"+currentStakan.getId()+",\"bids\":"+resultArrayBids.toString()+",\"asks\":"+resultArrayAsks.toString()+",\"ts\":"+currentStakan.getTs()+",\"version\":"+currentStakan.getVersion()+",\"ch\":"+currentStakan.getCh()+"}"+"\n");
                                lastArrayAsks = currentStakan.getAsks();
                                lastArrayBids = currentStakan.getBids();
                            }
                            
                        }
                    }
                }
            }
            catch (FileNotFoundException e) {
            }
        }        
    }
}