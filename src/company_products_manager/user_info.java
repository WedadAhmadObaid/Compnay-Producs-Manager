/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company_products_manager;

/**
 *
 * @author Eng. Wedad Obaid
 */
public class user_info {
    private final String name;
    private final float password;
    private final String addDate;
    private final String adress;
     public user_info( String name, float password, String adress ,String addDate)
    {
        this.name = name;
        this.password = password;
        this.adress = adress;
        this.addDate = addDate;
    }
     
    public String getName()
    {
        return name;
    }
 
    public float getPassword()
    {
        return password;
    }
 
    public String getAdress()
    {
        return adress;
    }
 
   public String getAddDate()
    {
        return addDate;
    }
 
    
}
