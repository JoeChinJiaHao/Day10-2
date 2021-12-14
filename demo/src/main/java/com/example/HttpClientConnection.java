package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpClientConnection implements Runnable {
    private final Socket socket;
    
public HttpClientConnection(Socket socket1){
    socket=socket1;

}

@Override
public void run() {
    // TODO Auto-generated method stub
    boolean flag=true;
    PrintWriter out = null;
    BufferedReader in = null;
    String line;
    try{
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        line = in.readLine();
    }catch(IOException e){
        System.out.println("Error at read and write.");
    }
    while(flag){
        try{

        }catch(Exception e){
            System.out.println("Error has occurred");
            e.printStackTrace();
            break;
        }


    }


}

}
