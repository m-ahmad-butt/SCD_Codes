package org.example.layerdPRACTICE.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import org.example.jdbc.database;

public class shopDB implements shop {
    Connection conn = database.getInstance().getConnection();

    @Override
  public void save (ArrayList<Hashtable<String,Object>> items){
    String sql = "Insert into Item VALUES(?,?,?)";
    
    try(PreparedStatement pt = conn.prepareStatement(sql)){
    for(Hashtable<String,Object> tb:items){
                pt.setObject(1, tb.get("code"));
                pt.setObject(2, tb.get("quantity"));
                pt.setObject(3, tb.get("price"));
                pt.addBatch();
    }
    pt.executeBatch();
}
 catch(SQLException E){
    E.printStackTrace();
 }

  }

  @Override
  public ArrayList<Hashtable<String,Object>> load(){
    ArrayList<Hashtable<String,Object>> list =new ArrayList<>(); 
    String sql="Select * from Item";
    try{
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(sql);

    ResultSetMetaData meta = rs.getMetaData();
    int col = meta.getColumnCount();

   while(rs.next()){
       Hashtable<String,Object>row = new Hashtable<>();
     for(int i=1;i<=col;++i){
        row.put(meta.getColumnName(i),rs.getObject(i));
    }
    list.add(row);
   }

   for(Hashtable<String,Object>tb:list ){
    System.out.println(tb);
   }

   }catch(SQLException e){
    e.printStackTrace();
   }
   return list;


  }

  @Override
  public int del(String code){
    String sql = "Delete from Item where code = ?";
    try(PreparedStatement pt = conn.prepareStatement(sql)){
        pt.setObject(1, code);
        return pt.executeUpdate();
    }catch(SQLException e){
        e.printStackTrace();
        return 0;
    }
  }
}
