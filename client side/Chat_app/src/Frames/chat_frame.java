
package Frames;

import static Frames.chat_frame.*;
import client_server.client;
import chat_app.Chat_app;
import java.io.*;
import javax.swing.*;
import java.net.Socket;
import java.sql.*;

import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date;


public class chat_frame extends javax.swing.JFrame {
    static String user_name;
    static Socket socket;
    PrintWriter out;
    String index;
    static SimpleDateFormat formatter;
    static Date date;
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/";

   //  Database userid and pass
    static final String USER = "root";
    static final String PASS = "5615";
     public static  Connection conn = null;
     public static Statement stmt = null;
    
    public chat_frame(String str,Socket socket) {
       //INITILIZATION OF COMPONRNTS 
        initComponents();
      //GETTING ELEMENTS FROM LOGIN FRAME
        this.user_name = str;
        System.out.println(str);
        this.socket = socket;
        this.userid.setText("your user id : "+str);
        this.display.setEditable(false);
        
        try{
        
        // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

          //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

          //Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();
        //  CREATE DATABASE
            String sql = "USE CHAT_BOX";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
     //RUNNING THREAD TO RECIEVE MESSAGES   
        RecieveT recieve = new RecieveT(socket,display,user_list);
	Thread thread= new Thread(recieve);
	thread.setPriority(1);
	thread.start();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        userid = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        user_list = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        display = new javax.swing.JTextArea();
        message = new javax.swing.JTextField();
        send = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WELCOME");
        setBounds(new java.awt.Rectangle(100, 100, 900, 800));
        setIconImages(null);
        setPreferredSize(new java.awt.Dimension(853, 700));
        setSize(new java.awt.Dimension(120, 120));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setAutoscrolls(true);
        jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);

        userid.setText("user id is : ");

        user_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        user_list.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        user_list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                user_listValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(user_list);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userid)
                .addContainerGap(229, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(userid, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
        );

        display.setEditable(false);
        display.setColumns(20);
        display.setRows(5);
        jScrollPane1.setViewportView(display);

        send.setText("send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(message)
                    .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//SELECTING THE USER FROM LIST OF CONNECTED USERS
    private void user_listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_user_listValueChanged
        index = user_list.getSelectedValue();
    }//GEN-LAST:event_user_listValueChanged
//FUNCTION FOR ACTION ON CLICKING EXIT BUTTON
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //DELETING DATABSE ON CLICKING OF EXIT BUTTON   
        try{
             System.out.println("Start deleting database");
             stmt.executeUpdate("DROP DATABASE IF EXISTS CHAT_BOX");
             stmt.close();
             conn.close();
             System.out.println("DATABASE DELETED SUCCESSFULLY");
        }
        catch(Exception q){
            System.out.println(q.getMessage());
        }
       
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        String str = message.getText();
        formatter = new SimpleDateFormat();
        date = new Date();
        
      //SELECTING USER FROM LIST IF NULL  
        if(index == null){
            index="all";
            user_list.setSelectedIndex(0);
        }
        
      //APPENDING MESSAGE AND TIME TO DISPLAY AREA
        display.append("                                     "+formatter.format(date)+"\n");
        display.append("send to "+index+" : "+str+"\n");
        try {
            
         //SENDING MESSAGE TO SERVER 
            out = new PrintWriter(socket.getOutputStream());		
	   	out.write("from to : "+index+" :"+message.getText());
                out.flush();
                
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
        }
        
      //CLEARING THE TEXT AREA FOR NEW MESSAGE
         message.setText("");
    }//GEN-LAST:event_sendActionPerformed

//MAIN FUNCTION
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chat_frame(user_name,socket).setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea display;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField message;
    private javax.swing.JButton send;
    private javax.swing.JList<String> user_list;
    private javax.swing.JLabel userid;
    // End of variables declaration//GEN-END:variables
}

//CLASS OF THREAD 
class RecieveT implements Runnable{
    Socket socket ;
    Scanner scan=new Scanner(System.in);
    DataInputStream in ;
    PrintWriter out;
    String data;
    JTextArea display;
    JList<String> user_list;
    DefaultListModel list;
    
    public RecieveT(Socket socket,JTextArea display,JList<String> user_list ){
	this.socket=socket;
        this.display = display;
        this.user_list=user_list;
        list = new DefaultListModel();
     
    //ADDING " ALL " ELEMENT TO LIST
        list.addElement("all");
        user_list.setModel(list);
        
     //INSTANTIATING OBJECT OF CLASS DATE AND TIME
        formatter = new SimpleDateFormat();
        date = new Date();
    }
    @Override
    public void run() {
	   		
        try {
            
          //INSTANTIATING OBJECT OF CLASS BUFFEREDREADER TO RECIEVE MESSAGES
            BufferedReader in1 = new BufferedReader(new InputStreamReader (this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream());
            data="";
            while((data = in1.readLine() ) != null ) {
                
              // LOG 
                System.out.println("recieved : "+data.toString());
                
              //CONDITION TO CHECK IF NEW CLIENT IS ADDED TO SERVER 
                if(data.startsWith("joined by :")){
                    String modify="";
                    
                  // IF CLIENT HAS JOINED SERVER THEN GETTING USER NAME FROM RECIEVED MESSAGE
                    for (int j=11;j<data.length();j++){
                        modify += data.charAt(j);
                    }
                    
                 //ADDING USERNAME TO LIST
                    list.addElement(modify);
                    user_list.setModel(list);
                }
                
              //FOR CHECKING IF CLIENT HAS LEFT THE SERVER
                else if (data.startsWith("chat is been left by :")){
                    String modify="";
                    
                  // IF CLIENT HAS LEFT SERVER THEN GETTING USER NAME FROM RECIEVED MESSAGE
                    for (int j=22;j<data.length();j++){
                        modify += data.charAt(j);
                    }
                    System.out.println(modify );
                    for (int k=0;k<user_list.getModel().getSize();k++){
                        System.out.println("k " + user_list.getModel().getElementAt(k));
                        
                      //REMOVING USERNAME FROM LIST WHO HAS LEFT CHAT
                        if(modify.equals(user_list.getModel().getElementAt(k))){
                            list.removeElementAt(k);
                            user_list.setModel(list);
                        }
                    }
                }
                
              //APPENDING ALL MESSAGES TO DISPLAY AREA WITH TIME AND DATE
                display.append("                                     "+formatter.format(date)+"\n");
                display.append(data+"\n");
            }
          //LOG
            System.out.println("recieved : " +data);
	   		
	}
	catch(Exception e) {
            System.out.println( e.getMessage());
            System.exit(0);
	}
    }
    
}