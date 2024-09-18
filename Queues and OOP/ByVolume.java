public class ByVolume extends Variable
{
    private String product;
    private double volume;
    private double price;
    
    //fix
    public ByVolume(String pro, double vol, double pri)
    {
        super(pro, pri);
        product = pro;
        volume = vol;
        price = pri;
        
    }
    public double cost()
    {
        return price;
    }
    public double Oz()
    {
        return volume;
    }
    public String toString()
    {
        String ans = ("Name: "+ product + " Volume: "+ volume+" oz " + " Price: " + price+ "\n");
        return ans;
    }
    
}
