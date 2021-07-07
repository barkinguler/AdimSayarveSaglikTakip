/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author brkn_
 */
@Path("testPost")
@XmlRootElement
public class Post {


    @XmlElement
    public String KULLANICI;
    @XmlElement
    public String SIFRE;


    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public ArrayList<testModel> create(Post input) {


        Connection con1 = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "kullanici", "123");
            PreparedStatement preparedStatement = con1.prepareStatement("INSERT INTO ADIM VALUES (?,?)");

            preparedStatement.setString(1, input.KULLANICI);
            preparedStatement.setString(2, input.SIFRE);

            preparedStatement.executeUpdate();

            con1.close();
            preparedStatement.close();

            if (con1 != null) System.out.print("calıstı");


        } catch (Exception e) {

            testController ulas = new testController();
            ulas.hatagönder();
        }
        return null;
    }


}
