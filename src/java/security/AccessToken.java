/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class AccessToken {

    private static final String TOKEN_NAME_FILE = "com.eeg.pt1_v1.security.AccessToken";
    private static final String TOKEN_NAME = "token_name";

    public AccessToken(){}

    public static void setAccessToken(String token){
        
    }
    public static String getAccessToken(String jsonUser){
        return new Hash().getHash(jsonUser.getBytes()).toString();
    }
}

