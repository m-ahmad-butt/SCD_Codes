package org.example.layerdPRACTICE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;

import org.example.layerdPRACTICE.dao.shopDB;
import org.example.layerdPRACTICE.model.Item;

public class service {
   private shopDB sh;
   
   public service(){
    sh=new shopDB();
   }

   void addItem(ArrayList<Item> itm){
    ArrayList<Hashtable<String,Object>> itCum=new ArrayList<>();

    for(Item it:itm){
        Hashtable<String,Object> temp = new Hashtable<>();
        temp.put("code",it.getCode());
        temp.put("quantity",it.getQuantity());
        temp.put("price",it.getPrice());
       itCum.add(temp);
    }

    sh.save(itCum);
   }

   ArrayList<Item> getItems(){
    ArrayList<Hashtable<String,Object>> itCum=sh.load();
    ArrayList<Item> it = new ArrayList<>();
    for(Hashtable<String,Object> h:itCum){
        String code = (String)h.get("code");
        int quantity = ((Number)h.get("quantity")).intValue();
        BigDecimal price = new BigDecimal(h.get("price").toString());
        Item itPom = new Item(code, quantity, price);
        it.add(itPom);
    }
return it;
   }
}
