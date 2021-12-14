package com.example;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    
    
    public Socket socket;
    public ServerSocket serverSocket;
    
    public void StartUp(Integer portNum,List<String> dirList) throws IOException{
        //server start here 
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        serverSocket = new ServerSocket(portNum);
        System.out.println("Listening at port: " + portNum.toString());
        //check if path exist
        for(String s:dirList){
           //check if path is readable by server 
            File file = new File(s);
            if (file.exists()){
                System.out.println("path exist!");
            }else{
                System.exit(1);
            }
            if(file.isDirectory()){
                System.out.println("path is a directory!");
            }else{
                System.exit(1);
            }
            if(file.canRead()){
                System.out.println("path is readable!");
            }else{
                System.exit(1);
            }


        }
        

    }

}