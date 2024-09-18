public class Fixed extends Product
{   
    //CONSTRUCTORS 
    private String product;
    private double price;
    public Fixed(String pro, double pri)
    {
        product = pro;
        price = pri;
    }
    public double cost()
    {
        return price;
    }
   
    public String toString()
    {
        String ans =("Name:" + product +" Price:"+price+"\n"); //might have to use super.toString()
        return ans;
    }
    
    
}
