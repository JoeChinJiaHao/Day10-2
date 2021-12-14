package com.example;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class HttpClientConnection implements Runnable {
    private final Socket socket;
    private List<String> dList;
public HttpClientConnection(Socket socket1,List<String> dList1){
    socket=socket1;
    dList=dList1;
}

@Override
public void run() {
    // TODO Auto-generated method stub
    boolean flag=true;
    //PrintWriter out = null;
    BufferedReader in = null;
    HttpWriter hWrite=null;
    String line="";
    ByteArrayOutputStream baos=null;
    try{
        //out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        line = in.readLine();
        hWrite = new HttpWriter(socket.getOutputStream());
        baos = new ByteArrayOutputStream();
    }catch(IOException e){
        System.out.println("Error at read and write.");
    }
    while(flag){
        try{
            if(!line.substring(0,3).equals("GET")){
                hWrite.writeString("HTTP/1.1 405 Method Not Allowed\r\n\r\n"+line.substring(0,3)+" not supported\r\n");
                hWrite.flush();
                flag=false;
            }else{
                //get the resource name
                Scanner scan = new Scanner(line);
                List<String> cList = new ArrayList<String>();
                while(scan.hasNext()){
                    cList.add(scan.next());
                }
                scan.close();
                String resourceName = cList.get(1);
                if(resourceName.equals("/")){
                    resourceName="/index.html";
                }
                Boolean fileInside =false;
                String workingDir="";
                //check if resoruce exist
                for(String str2:dList){
                    String ss = str2+resourceName;
                    File file = new File(ss);
                    if(file.exists()){
                        workingDir=ss;
                        fileInside=true;
                        break;
                    }
                }
                if(fileInside==false){
                    hWrite.writeString("HTTP/1.1 404 Not Found\r\n\r\n"+resourceName+" not found \r\n");
                    hWrite.flush();
                    flag=false;
                }else{
                    System.out.println(workingDir);
                        if(!workingDir.substring(workingDir.length()-4,workingDir.length()).equals(".png")){
                            String reply="HTTP/1.1 200 OK\r\nContent-Type: image/png\r\n\r\n";
                            hWrite.writeString(reply);
                            hWrite.flush();
                            //add resource content as bytes
                            File file = new File(workingDir);
                            byte[] bytes=Files.readAllBytes(Paths.get(workingDir));
                            hWrite.writeBytes(bytes);
                            hWrite.flush();
                            flag=false;
                        }else{
                            String reply2="HTTP/1.1 200 OK\r\n\r\n";
                            hWrite.writeString(reply2);
                            hWrite.flush();
                            //add resource content as bytes
                             File file = new File(workingDir);
                            BufferedImage bI =  ImageIO.read(new FileInputStream(file));
                            ImageIO.write(bI, "png", baos);
                            byte[] bytes = baos.toByteArray();
                            hWrite.writeBytes(bytes);
                            hWrite.flush();
                            flag=false;

                        }
                    }
                
            }
        }catch(Exception e){
            System.out.println("Error has occurred");
            e.printStackTrace();
            break;
        }finally{
            try {
                hWrite.close();
                in.close();
                socket.close();
            } catch (Exception e) {
                
                e.printStackTrace();
            }

            
        }


    }
    


}

}
