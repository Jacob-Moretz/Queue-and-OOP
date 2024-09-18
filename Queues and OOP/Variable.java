public abstract class Variable extends Product
{
    //CONSTRUCTORS 
    private String product;
    private double price;
    
    public Variable(String pro, double pri)
    {
        product = pro;
        price = pri;
    }
    public abstract double cost();

   public String toString()
   {
        return("Name:"+product+"Price: "+price);
   }

 
    
}
