import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {

    private final String name;
    private final Map<StockItem, Integer> list;   // StockItem for items purchased, Integer for their quantity. ;)

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();    // TreeMap is also sorted like LinkedHashMap
    }

    public int addToBasket(StockItem item,  int quantity) {
        if( (item!=null) && (quantity>0)){
            int inBasketOrNot = list.getOrDefault(item, 0);
            list.put(item, inBasketOrNot + quantity);
            return inBasketOrNot;
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity){

        if(item != null  && quantity > 0 ){
            int inBasket = list.getOrDefault(item, 0);
            int newQuantity = inBasket - quantity;

            if(newQuantity>0){
                list.put(item, newQuantity);
                return quantity;
            }
            else if (newQuantity==0){
                list.remove(item);
                return quantity;
            }
        }
        return 0;
    }

    public Map<StockItem, Integer> getItems(){
        return Collections.unmodifiableMap(this.list);
    }

    public void clearBasket(){
        this.list.clear();
    }

    @Override
    public String toString() {
        String s = "\n'" + this.name + "' Basket :-\n\n";

        double totalCost = 0;
        int count = 0;
        int quantityCount = 0 ;

        // The list.entrySet() returns a set of all entries in the list of form Map<Key,Value> as Set<Entry<Key,Value>>
        // The Entry interface in Map.Entry<Key,Value> Allows us to work with Map Entries :)
        for( Map.Entry<StockItem, Integer> mapEntry: this.list.entrySet()){

            StockItem item = mapEntry.getKey();
            int quantity = mapEntry.getValue();

            double price = item.getPrice();

            double itemTotalPrice = price * quantity;
            count++;

            s += count + ". " + item.getName() + ": " + quantity + " at "
                    + price + " each equal(s) --> " + String.format( "%.2f", itemTotalPrice) + "\n";

            totalCost += itemTotalPrice;
            quantityCount += quantity;

        }

        s += "\nTotal cost of " + (quantityCount) + " " + ( (count * quantityCount) > 1 ? "items" : "item")
                + " in basket is --> " + totalCost +"\n";

        return s;
    }

}
