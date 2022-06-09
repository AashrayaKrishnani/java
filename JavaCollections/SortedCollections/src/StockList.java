import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {

    private final Map<String, StockItem> list;

    public StockList() {
            this.list = new LinkedHashMap<>();  // LinkedHaspMaps are Sorted :D
    }

    public int addStock(StockItem item){
        if(item != null){
            // MAP.getOrDefault(Key, DefaultValue) returns Object if Key Matches.
            // Else it returns the Default Value passed in the arguments.
            StockItem inStockOrNot = list.getOrDefault( item.getName(), item );
            if( inStockOrNot != item){  // If 'item' is already present in list, and default value 'item' is not returned.
                // Adding Stock Of Already Present Value to Current Item.
                item.adjustStock( inStockOrNot.getQuantity() );
            }
            this.list.put(item.getName(), item);  // Overriding if already present item or else simply adding.
            return item.getQuantity();
        }
        return 0;
    }

    public int reserveStock(String itemName, int quantity){

        StockItem inStockOrNot = list.get( itemName );

        if(inStockOrNot != null && quantity > 0){
            return inStockOrNot.reserveStock(quantity);
        }
        return 0;
    }

    public int unReserveStock(String itemName, int quantity){

        StockItem inStockOrNot = list.get( itemName );

        if(inStockOrNot != null && quantity > 0){
            return inStockOrNot.unReserveStock(quantity);
        }
        return 0;
    }

// Old sellStock() before implementing Reserve and UnReserve Functionality in StockItem class :)
//
//    public int sellStock( String itemName, int quantity){
//
//        StockItem inStockOrNot = this.list.getOrDefault(itemName, null);
//
//        if( (inStockOrNot != null)  &&
//                (inStockOrNot.getQuantity() >= quantity &&
//                                                    quantity > 0)    ){
//            inStockOrNot.adjustStock(-quantity);
//            return quantity;
//        }
//        return 0 ;
//    }

    public int sellStock( String itemName, int quantity){
        StockItem inStockOrNot = this.list.getOrDefault(itemName, null);

        if( (inStockOrNot != null)  && quantity > 0){
            return inStockOrNot.finaliseStock(quantity);
        }
        return 0 ;
    }

    public StockItem get(String itemName){
        return list.get(itemName);
    }

    public Map<String, Double> getPriceList(){
        Map<String, Double> prices = new LinkedHashMap<>();

        for(Map.Entry<String, StockItem> maptEntry: list.entrySet()) {
            prices.put(maptEntry.getKey(), maptEntry.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> getItems(){
        return Collections.unmodifiableMap(list) ;  // Returns a ReadOnly Version of Our Map.
        // It is faster than returning a deep copy through 'new HashMap<>(ourMap)' as it doesn't create a new map :)

        // Raises UnsupportedOperationException if someone tries to modify it lol XD
    }

    @Override
    public String toString() {
        String s = "\nStock List :-\n\n";

        double totalCost = 0;
        int count = 0;
        int quantityCount = 0;
        int totalReserved = 0;

        // The list.entrySet() returns a set of all entries in the list of form Map<Key,Value> as Set<Entry<Key,Value>>
        // The Entry interface in Map.Entry<Key,Value> Allows us to work with Map Entries :)
        for( Map.Entry<String, StockItem> mapEntry: this.list.entrySet()){

            StockItem item = mapEntry.getValue();

            double price = item.getPrice();
            int quantity = item.getQuantity();

            double itemTotalPrice = price * quantity;
            count++;

            s += count + ". " + item.getName() + ": " + quantity + " available" + " at "
                        + price + " each equal(s) --> " + String.format( "%.2f", itemTotalPrice) + " [Reserved = " + item.getReserved() + "]" + "\n";

            totalCost += itemTotalPrice;
            quantityCount += quantity;
            totalReserved += item.getReserved();
        }

        s += "\nTotal cost of " + (quantityCount) + " items(available) is --> " + totalCost + ", while " +
                totalReserved + " items are reserved.\n";

        return s;
    }
}
