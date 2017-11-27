/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessRules;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ing_ragde
 */
public class JSONErrorBuilder {
    public static String buildJson(int Error, String Message){
        JSONObject json = new JSONObject();
        try {
            json.put("Message", Message);
            json.put("Error", Error);
        } catch (JSONException ex) {
            Logger.getLogger(JSONErrorBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json.toString();
    }
}
