public class StockItem implements Comparable<StockItem>{

    private final String name;
    private double price ;
    private int quantityStock ;
    private int reserved = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public int getReserved() {
        return reserved;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantityStock - reserved;
    }

    public int reserveStock(int quantity){
        if(quantity <= this.getQuantity() && quantity > 0 ){
            reserved += quantity;
            return quantity;
        }
        return 0;
    }

    public int unReserveStock(int quantity){
        if(quantity <= this.reserved  && quantity > 0 ){
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    public int finaliseStock(int quantity){

        if( quantity <= reserved && quantity > 0 ){
            this.quantityStock -= quantity;
            this.reserved -= quantity;
            return quantity;
        }
        return 0 ;
    }

    public void adjustStock(int quantity){
        int newQuantity = this.quantityStock + quantity;

        if(newQuantity>=0){
            this.quantityStock = newQuantity;
        }
    }

    @Override
    public int compareTo(StockItem o) {

        if(o==null){
            throw new NullPointerException("Null 'StockItem' passed to compare against - " + this.getName());
        }

        System.out.println(this.getName() + ".compareTo() called against - " + o.getName() +".");
        return this.getName().compareTo( o.getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() + 10;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            System.out.println("\nSame Reference passed as Argument. \n" + this.getName() + ".equals() called\n");
            return true;
        }
        if (anObject instanceof StockItem) {
            StockItem aStockItem = (StockItem) anObject;
            System.out.println("\n" + this.getName() + ".equals() called");
            System.out.println("To check equality against StockItem named - " + aStockItem.getName());
            boolean result = this.getName().equals(aStockItem.getName());
            System.out.println("Result = " + result + "\n");

            return result;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getName() + ": Available quantity --> " + this.getQuantity() + " {" + this.quantityStock
                + "-" + this.reserved+ "}" + " at " + this.getPrice() + " each.";
    }
}
