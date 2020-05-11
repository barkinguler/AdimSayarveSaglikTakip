/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import oracle.jdbc.driver.Message;
import org.apache.derby.iapi.services.i18n.MessageService;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 *
 * @author brkn_
 */
@Path("testController")
public class testController {
    //MessageService msg=new MessageService();
            
            
    @GET
    @Path("/getData")
    @Produces(MediaType.APPLICATION_JSON)
            
    


    public ArrayList<testModel> getDataInJson() {
   
   Gson json=new Gson();
   
    
    
   
   
         
         
        ArrayList<testModel> tmm=new ArrayList<>();
        
        
        
        String a="";
  

          Connection con1=null;
        
        try {
            
            Class.forName("oracle.jdbc.OracleDriver");
            
             con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kullanici","123");
             
            //con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/stok?useUnicode=true&characterEncoding=utf8&useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");


            if (con1 != null) System.out.print("calıstı");


        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"hata1"+ e);
        }
   try {
       
   Statement st=con1.createStatement();
   
    ResultSet rs=st.executeQuery("select KULLANICI,SIFRE from ADIM");
   
    while(rs.next()){
        
        testModel tm=new testModel();
        tm.setKULLANICI(rs.getString("KULLANICI"));
        tm.setSIFRE(rs.getString("SIFRE"));
        
       
        tmm.add(tm);}
    
     
     con1.close();
     st.close();
     rs.close();
     
    
    }
   catch (Exception e){
   JOptionPane.showMessageDialog(null,"hata2"+ e);
   }
        
        
        
 
    return tmm;
    }
    
     @GET
    @Path("/getadim")
    @Produces(MediaType.APPLICATION_JSON)
  public ArrayList<testModel> adımtoplam() {
  
      ArrayList<testModel> tmm=new ArrayList<>();
        
        
        
        String a="";
  

          Connection con1=null;
        
        try {
            
            Class.forName("oracle.jdbc.OracleDriver");
            
             con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kullanici","123");
             
            //con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/stok?useUnicode=true&characterEncoding=utf8&useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");


            if (con1 != null) System.out.print("calıstı");


        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"hata1"+ e);
        }
   try {
       
   Statement st=con1.createStatement();
   
    ResultSet rs=st.executeQuery("select KULLANICI,SUM(ADIM) AS TOPLAM,max(ADIM) as MAX,AVG(ADIM) AS ORT FROM ADIMLAR GROUP BY KULLANICI");
   
    while(rs.next()){
        
        testModel tm=new testModel();
        tm.setKULLANICI(rs.getString("KULLANICI"));
        tm.setTOPLAM(rs.getInt("TOPLAM"));
        tm.setMAX(rs.getInt("MAX"));
        tm.setORT(rs.getFloat("ORT"));
        
       
        tmm.add(tm);}
    
     
     
     con1.close();
     st.close();
     rs.close();
    
    }
   catch (Exception e){
   JOptionPane.showMessageDialog(null,"hata2"+ e);
   }
  
  return tmm;
  }
   
  
  
   @GET
    @Path("/getgecmis")
    @Produces(MediaType.APPLICATION_JSON)
  public ArrayList<testModel> adımgecmis() {
  
      ArrayList<testModel> tmm=new ArrayList<>();
        
        
        
        String a="";
  

          Connection con1=null;
        
        try {
            
            Class.forName("oracle.jdbc.OracleDriver");
            
             con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kullanici","123");
             
            //con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/stok?useUnicode=true&characterEncoding=utf8&useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");


            if (con1 != null) System.out.print("calıstı");

                
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"hata1"+ e);
        }
   try {
       
   Statement st=con1.createStatement();
  
    ResultSet rs=st.executeQuery("select *  FROM ADIMLAR order by TARIH desc ");
   
    while(rs.next()){
        
        testModel tm=new testModel();
        tm.setKULLANICI(rs.getString("KULLANICI"));
        tm.setTARIH(rs.getString("TARIH"));
        tm.setADIM(rs.getInt("ADIM"));
        
       
        tmm.add(tm);}
    
     
     con1.close();
     st.close();
     rs.close();
     
    
    }
   catch (Exception e){
   JOptionPane.showMessageDialog(null,"hata2"+ e);
   }
  
  return tmm;
  }
  
 
  
   @GET
    @Path("/kilogecmis")
    @Produces(MediaType.APPLICATION_JSON)
  public ArrayList<testModel> kilogecmis() {
  
      ArrayList<testModel> tmm=new ArrayList<>();
        
        
        
        String a="";
  

          Connection con1=null;
        
        try {
            
            Class.forName("oracle.jdbc.OracleDriver");
            
             con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kullanici","123");
           
            //con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/stok?useUnicode=true&characterEncoding=utf8&useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");


            if (con1 != null) System.out.print("calıstı");


        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"hata1"+ e);
        }
   try {
       
   Statement st=con1.createStatement();
 
    ResultSet rs=st.executeQuery("select *  FROM KITLE order by TARIH desc");
   
    while(rs.next()){
        
        testModel tm=new testModel();
        tm.setKULLANICI(rs.getString("KULLANICI"));
        tm.setTARIH(rs.getString("TARIH"));
        tm.setKILO(rs.getFloat("KILO"));
        
       
        tmm.add(tm);}
    
     
     
     con1.close();
     rs.close();
     st.close();
    
    }
   catch (Exception e){
   JOptionPane.showMessageDialog(null,"hata2"+ e);
   }
  
  return tmm;
  }
  
  
  @GET
    @Path("/getHata")
    @Produces(MediaType.APPLICATION_JSON)
 public ArrayList<testModel> hatagönder(){
ArrayList<testModel> tmm=new ArrayList<>();

          Connection con1=null;
        
        try {
            
            Class.forName("oracle.jdbc.OracleDriver");
            
             con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kullanici","123");
             
            //con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/stok?useUnicode=true&characterEncoding=utf8&useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");


            if (con1 != null) System.out.print("calıstı");


        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"hata1"+ e);
        }
   try {
       
   Statement st=con1.createStatement();
   
    ResultSet rs=st.executeQuery("select *  FROM ADIM");
   
    while(rs.next()){
        
        testModel tm=new testModel();
        tm.setKULLANICI(rs.getString("KULLANICI"));
        tm.setTARIH(rs.getString("SIFRE"));
        
        
       
        tmm.add(tm);}
    
     
     
     con1.close();
     rs.close();
     st.close();
    
    }
   catch (Exception e){
   JOptionPane.showMessageDialog(null,"hata2"+ e);
   }
return tmm;
}
    
    
}

