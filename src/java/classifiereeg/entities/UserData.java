/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifiereeg.entities;

import java.io.Serializable;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class UserData implements Serializable {
    private int idUser;
    private int idSpetialist;
    private String completeName;
    private String spetialistName;
    
    
    public UserData(int idUser, String completeName, String spetialist){
        
    }
}
