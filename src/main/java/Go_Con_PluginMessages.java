/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author WatPowerUser
 */
public class Go_Con_PluginMessages {
    public static final String Bundle_Name = Go_Con_PluginI18n.BUNDLE_NAME;
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(Bundle_Name);
    public static final String Plugin_Name = "Go_Con_Plugin.Name";
    public static final String Plugin_Description = "Go_Con_Plugin.Description";
    public static final String Plugin_Short_name = "Go_Con_Plugin.ShortName";
    private Go_Con_PluginMessages(){
        super();
    }
    public static String getString(String key){
        try
        {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch(MissingResourceException e)
        {
            return '!' + key + '!';
        }
    }
}
