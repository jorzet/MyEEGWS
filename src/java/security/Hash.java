/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class Hash {
    private byte[] digest = null;
    private String stringHash;

    public byte[] getHash(byte[] password){
        try{
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(password);
            digest = sha256.digest();
        } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
        }
        return digest;
    }

    public String toString(){
        stringHash =new String(digest);
        return stringHash;
    }

    public boolean compareHash(String withHash){
        return stringHash.equals(withHash);
    }
}