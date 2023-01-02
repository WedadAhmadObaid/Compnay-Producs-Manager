
package company_products_manager;

public class Product {
    private final int id;
    private final String name;
    private final float price;
    private final String addDate;
    private final byte[] image;
 
    public Product(int id, String name, float price, String addDate, byte[] image)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.addDate = addDate;
        this.image = image;
    }
 
    public int getId()
    {
        return id;
    }
 
    public String getName()
    {
        return name;
    }
 
    public float getPrice()
    {
        return price;
    }
 
    public String getAddDate()
    {
        return addDate;
    }
 
    public byte[] getImage()
    {
        return image;
    }
    
}