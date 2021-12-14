package com.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;

public class Main {
    public static void main(String[] args) throws IOException {
        Path curPath =Paths.get("");
        String curPathStr = curPath.toAbsolutePath().toString().replace("\\", "/");
        System.out.println(curPathStr);
        String docR = curPathStr+"/com/example/static";//+"/com/exmaple/static";
        List<String> listOfDir = new ArrayList<String>();
        Integer portNumber = 3000;
        String temp;


        List<String> inList = new ArrayList<String>();
        if (args.length>0 ){
            for(String s:args){
            //System.out.println(s);
            inList.add(s);
            }
            for(String s:inList){
                if(s.equals("--port")){
                    //System.out.println(inList.get(inList.indexOf(s)+1));
                    portNumber=Integer.valueOf(inList.get(inList.indexOf(s)+1));
                }else if(s.equals("--docRoot")){
                    //handle here
                    temp=inList.get(inList.indexOf(s)+1).replace("./", "/");
                    Scanner scan = new Scanner(temp);
                    scan.useDelimiter(":");
                    while(scan.hasNext()){
                        String holder = scan.next();
                        //System.out.println("hi");
                        listOfDir.add(curPathStr + holder);
                    }
                    scan.close();
                }
            }
        }else{
            System.out.println("no args entered, default value used");
            listOfDir.add(docR);
        }
        System.out.println("Port Number is "+portNumber.toString());
        System.out.println("Directory is as follows:");
        for(String s:listOfDir){
            System.out.println(s);
        }
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //call the Server
        HttpServer myServer = new HttpServer();
        myServer.StartUp(portNumber,listOfDir);

        
    }
}
