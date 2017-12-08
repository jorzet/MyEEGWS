/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

/**
 *
 * @author Jorge
 */
public class Code {
    private static String[] elements = {"a","b","c","d","e","f","g","h","i"
                                ,"j","k","l","m","n","o","p","q","r"
                                ,"s","t","u","v","w","x","y","z","0"
                                ,"1","2","3","4","5","6","7","8","9"};
    
    public static String generateConfirmationCode(){
        String code = "";
        
        for(int i = 0; i < 5; i++){
            int random = (int) (Math.random() * elements.length);
            code = code + elements[random];
        }
        
        return code;
    }
}
