/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;
/**
 *
 * @author MSI PC
 */
public class Encode {
    //sha-1 
    public static String toSHA1(String pass){
        String salt = "asjrlkmcoewjtjle;oxqskjhd";
        String result =null;
        
        pass= pass + salt;
        try{
            byte[] dataBytes = pass.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
