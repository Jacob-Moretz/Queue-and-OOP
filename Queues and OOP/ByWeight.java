public class ByWeight extends Variable
{
    private String product;
    private double weight;
    private double price;
    
    //fix
    public ByWeight(String pro, double wei, double pri)
    {
        super(pro, pri);
        product = pro;
        weight = wei;
        price = pri;
        
    }
    public double cost()
    {
        return price;
    }
    public double Oz()
    {
        return weight;
    }
    public String toString()
    {
        String ans = ("Name: "+ product + " Weight: " + weight+" oz " + " Price: " + price + "\n");
        return ans;
    }
}
