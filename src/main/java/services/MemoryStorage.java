/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chee Bhagyani
 */
public class MemoryStorage {
    private static MemoryStorage memoryStorage = new MemoryStorage();
    private Map<String,String> hashMap = new HashMap<String, String>();
    
    private MemoryStorage(){
    }

    public static MemoryStorage getStorage(){
        return  memoryStorage;
    }

    public String getId(String sessionId){
        for(Map.Entry<String,String> e : hashMap.entrySet()){
            if(sessionId.equals(e.getKey())){
                return  e.getValue();
            }
        }
        return null;
    }
    
    public void addId(String sessionId, String token){
        hashMap.put(sessionId,token);
    }
}
