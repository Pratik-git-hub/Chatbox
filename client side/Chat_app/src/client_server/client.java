/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_server;

import java.net.Socket;
import java.util.Scanner;


public class client {
    static String str="";
    static String data="";
    private static Socket socket;
    Thread thread;
    public client(){
    try{
	Scanner scan=new Scanner(System.in);
	socket = new Socket("localhost" , 55555 );
	
    }
    catch(Exception e){
            System.out.println(e.getMessage());
    }
    }
    public Socket getter_s(){
        
        return socket;
    }
    public void setter_s(){
        this.socket = socket;
    }
    public void setter_T(){
        this.thread = thread;
    }
    public Thread getter_T(){
        
        return thread;
    }
}
