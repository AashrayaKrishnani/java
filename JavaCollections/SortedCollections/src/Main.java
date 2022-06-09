import java.util.Map;

public class Main {

    private static StockList stockList = new StockList();
    private static Basket firstBasket = new Basket("The firstBasket");
    private static Basket challengeBasket = new Basket("The BasketAfterChallenge!");


    public static void main(String[] args) {

        // Initializing 'stockList' with items ;D
        {
            StockItem temp = new StockItem("Bread", 40, 100);
            stockList.addStock(temp);

            temp = new StockItem("Cake", 600, 10);
            stockList.addStock(temp);

            temp = new StockItem("Car", 100000, 5);
            stockList.addStock(temp);

            temp = new StockItem("Chair", 1000, 50);
            stockList.addStock(temp);

            temp = new StockItem("Cup", 30, 200);
            stockList.addStock(temp);

            // Checking addStock() function if StockItem already exists
            temp = new StockItem("Cup", 30, 200);
            stockList.addStock(temp);

            temp = new StockItem("Door", 4000, 4);
            stockList.addStock(temp);

            temp = new StockItem("Juice", 60, 31);
            stockList.addStock(temp);

            temp = new StockItem("Phone", 15000, 90);
            stockList.addStock(temp);
        }

        // Printing Entire StockList.
        {
            System.out.println("\n[1] Printing entire 'stockList' after Initialization!");
            System.out.println("--------------------------------------");
            System.out.println(stockList);
            System.out.println("\n--------------------------------------");
        }

        // Basket Code XD
        {
            System.out.println("\n[2] Testing Basket Code with sellItem() and then printing the basket! :D");
            System.out.println("--------------------------------------\n");

            sellItem(firstBasket, "Car",1);
            System.out.println(firstBasket);

            sellItem(firstBasket, "Juice",10);
            System.out.println(firstBasket);

            sellItem(firstBasket, "Titanium",2000);
            System.out.println(firstBasket);

            sellItem(firstBasket, "Anti Matter",2000);
            System.out.println(firstBasket);

            sellItem(firstBasket, "Cup",200);
            System.out.println(firstBasket);

            System.out.println("\n--------------------------------------");

        }

        // Printing Entire StockList again after taking out stuff for the basket!
        System.out.println(stockList);

        // Unmodifiable Map Check
        {
            StockItem temp = new StockItem("someStuff", 9999, 1);
            // stockList.getItems().put(temp.getName(), temp);  // This line will throw exception! XD

            System.out.println("\n[3] To show that Collections.unmodifiableMap(map) returns an unmodifiable map, but its object references can be obtained and changed.");
            System.out.println("--------------------------------------\n");

            // However, the Map is unmodifiable, but the objects references it has are not! XD
            System.out.println("Original value of stockList.get(\"Cup\")  --> " + stockList.get("Cup"));

            System.out.println("--Now Modifying the object Reference from the Unmodifiable MAP.");
            StockItem cup = stockList.getItems().get("Cup") ;
            if(cup!= null)
                cup.adjustStock(-100);

            System.out.println("Changed value of stockList.get(\"Cup\")  --> " + stockList.get("Cup"));
            System.out.println("\n--------------------------------------");
        }

        // Unmodifiable Map with No Object References of StockItems
        {
            System.out.println("\n[4] Printing 'stockList' items using stockList.getPriceList() unmodifiable map but with No" +
                    " Object References of StockItems ");
            System.out.println("--------------------------------------\n");

            int count = 0;

            for(Map.Entry<String, Double> mapEntry : stockList.getPriceList().entrySet()){
                count++;
                System.out.println( count + ". " + mapEntry.getKey() + " costs " + mapEntry.getValue() + " Rs. each.");
            }
            System.out.println("\n--------------------------------------");
        }

        // Some more Basket Code after Challenge :)
        {

            System.out.println("\n[5] Some More Basket Code After The Coding Challenge!");
            System.out.println("--------------------------------------\n");

            sellItem(challengeBasket, "Cup", 100);
            sellItem(challengeBasket, "Juice", 5);

            System.out.println("\n[-] Testing sell item to remove 1 'Car' from 'firstBasket' which doesn't have any cars:- " );
            System.out.println("[*] Number of Cars removed --> '" + sellItem(challengeBasket, "Car", 1) + "' {return value of sellItem(challengeBasket, \"Car\", 1)}" );

            System.out.println(challengeBasket);

            removeItem(firstBasket, "Car", 1);
            removeItem(firstBasket, "Cup", 9);
            removeItem(firstBasket, "Juice", 2);

            System.out.println(firstBasket);

            System.out.println("[*] 'stockList' and 'firstBasket' before calling checkOut() :-\n");
            System.out.println(stockList);
            System.out.println(firstBasket);

            System.out.println("[*] Calling checkOut(firstBasket) :-\n");
            checkOut(firstBasket);
            System.out.println(firstBasket);

            System.out.println("[*] stockList after calling checkOut() :-\n");
            System.out.println(stockList);

            System.out.println("\n[***] stockList doesn't change because it already deducted reserved items once " +
                    "they were added, so checkOut didn't affect total cost or quantity! :D");
            System.out.println("[*] The 'Reserved' values at the end however, do change after calling checkOut() :D\n " );


        }

    }

    public static int sellItem(Basket basket, String itemName, int quantity){
        // Retrieving item from stockList:
        StockItem item = stockList.get(itemName);

        if(quantity <= 0 ){
            System.out.println("\n[-] Invalid quantity.");
            return 0;
        }

        if(item == null){
            System.out.println("\n[-] We Don't Sell " + itemName);
            return 0;
        }
        else if( stockList.reserveStock(itemName, quantity) != 0){     // sellStock() returns 0 if quantity if invalid
            return basket.addToBasket(item, quantity) ;
        }
        else{
            return 0;
        }
    }

    public static int removeItem(Basket basket, String itemName, int quantity){
        // Retrieving item from stockList:
        StockItem item = stockList.get(itemName);

        if(quantity <= 0 ){
            System.out.println("\n[-] Invalid quantity.");
            return 0;
        }

        if(item == null){
            System.out.println("\n[-] We Don't Sell " + itemName);
            return 0;
        }
        else if( basket.removeFromBasket(item, quantity) == quantity){     // sellStock() returns 0 if quantity if invalid
            return stockList.unReserveStock(itemName, quantity) ;
        }
        else{
            return 0;
        }
    }

    public static void checkOut(Basket basket){

        for(Map.Entry<StockItem, Integer> entry : basket.getItems().entrySet() ){

            StockItem item = entry.getKey();
            int quantity = entry.getValue();

            stockList.sellStock( item.getName(), quantity);

        }

        basket.clearBasket();

    }

}
