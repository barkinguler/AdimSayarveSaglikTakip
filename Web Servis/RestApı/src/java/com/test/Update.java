/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author brkn_
 */
@Path("testUpdate")
@XmlRootElement
public class Update {
    @XmlElement
    public String KULLANICI;
    @XmlElement
    public int ADIM;

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public void create(Post input) {

        Connection con1 = null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        String b = dateFormat.format(date);

        Connection conn = null;

        CallableStatement cStmt = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "kullanici", "123");


            cStmt = con1.prepareCall("{call ADIM_EKLE(?,?)}");
            cStmt.setString(1, input.KULLANICI);
            cStmt.setString(2, b);

            cStmt.execute();
            con1.close();
            cStmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }
}
