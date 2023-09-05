package com.example.petspace;

import android.content.Context;
import android.os.StrictMode;

import net.sourceforge.jtds.jdbc.Driver;

import java.security.Policy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar(){
        Connection conn = null;

        try{
            StrictMode.ThreadPolicy policy;
            policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            //dados da coneção
            String[] dadosConnSomee = {"PetSpace.mssql.somee.com","PetSpace","CaioSilva_SQLLogin_1","bj8g3g8o2r"};


            //variaveis com a url do sql
            String connSomee = "jdbc:jtds:sqlserver://"+dadosConnSomee[0]+";"+
                    "databaseName="+dadosConnSomee[1]+";user="+dadosConnSomee[2]+";password="+dadosConnSomee[3]+"";

            conn = DriverManager.getConnection(connSomee);

        }catch (SQLException e){
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
