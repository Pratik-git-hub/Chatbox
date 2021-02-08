
package Frames;

import client_server.client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;
import java.util.Scanner;
import javax.swing.WindowConstants;

public class login_page extends javax.swing.JFrame {
    static Socket socket;
    static client cli;
    Thread thread;
    PrintWriter out;
    String data;
    BufferedReader in1;
    
  // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/";

   //  Database userid and pass
    static final String USER = "root";
    static final String PASS = "5615";
    Connection conn = null;
    Statement stmt = null;
    String sql;
    public login_page() {
        cli = new client();
        this.socket = cli.getter_s();
        initComponents();
        
        try {
          // Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

          //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

          //Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();
          //CREATE DATABASE
            sql = "CREATE DATABASE IF NOT EXISTS CHAT_BOX";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
            
         //READING AND SENDING USER NAME TO SERVER   
            in1 = new BufferedReader(new InputStreamReader (this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream());
            data="";
            data = in1.readLine();
            System.out.println("recieved : "+data.toString());
            if(data.toString().equals("NICK")) {
                System.out.println("enter nickname : ");
            }
        }
	catch(Exception e) {
            System.out.println( e.getMessage());
            System.exit(0);
	}
        
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        id = new javax.swing.JLabel();
        id_enter = new javax.swing.JTextField();
        login_b = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CHAT APPLICATION");
        setBackground(java.awt.Color.black);
        setBounds(new java.awt.Rectangle(300, 300, 400, 400));
        setFocusable(false);
        setForeground(new java.awt.Color(0, 0, 0));
        setIconImages(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        id.setBackground(new java.awt.Color(102, 102, 102));
        id.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        id.setForeground(new java.awt.Color(0, 51, 204));
        id.setText("enter your id :");

        id_enter.setBackground(new java.awt.Color(153, 153, 153));
        id_enter.setFont(new java.awt.Font("Arial Black", 0, 15)); // NOI18N
        id_enter.setForeground(new java.awt.Color(102, 0, 0));
        id_enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_enterActionPerformed(evt);
            }
        });

        login_b.setBackground(new java.awt.Color(0, 102, 255));
        login_b.setText("login");
        login_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_bActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(212, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(id_enter, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(211, 211, 211))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(login_b)
                        .addGap(376, 376, 376))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_enter, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(login_b)
                .addGap(212, 212, 212))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
// FUNCTION FOR ON CLICK LOGIN BUTTON
    private void login_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_bActionPerformed
        String str = id_enter.getText();
       //GETING AUTHENTICATION FROM SERVER AND VALIDATING USER_ID AND SENDING IT
        if(!str.equals("")){
            try {
                
                BufferedReader in1 = new BufferedReader(new InputStreamReader (this.socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());		
	   	out.write(id_enter.getText());
                out.flush();
                data = in1.readLine()  ;
                System.out.println("recieved sdfsdf : " +data);
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
            chat_frame chat = new chat_frame(str,socket);
            chat.setVisible(true);
            
        }
       
        this.dispose();
        
    }//GEN-LAST:event_login_bActionPerformed
//FUNCTION FOR ACTION ON CLICKING EXIT BUTTON
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
     //DELETING DATABSE ON CLICKING OF EXIT BUTTON   
        try{
            System.out.println("deleting database");
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

    private void id_enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_enterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_enterActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new login_page().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel id;
    private javax.swing.JTextField id_enter;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login_b;
    // End of variables declaration//GEN-END:variables
}
