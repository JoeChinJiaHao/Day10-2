package com.example;

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
    HttpServer(){};
    ExecutorService threadPool = Executors.newFixedThreadPool(3);
    public Socket socket;
    public ServerSocket serverSocket;
    public void kk(){
        System.out.println("ok");
    }
    public void StartUp(Integer portNum, List<String> dirList) throws IOException{
        //server start here 
        serverSocket = new ServerSocket(portNum);
        System.out.println("Listening at port" + portNum.toString());
        //check if path exist
    }

}