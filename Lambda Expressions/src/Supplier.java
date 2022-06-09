import java.util.List;
import java.util.Random;

public class Supplier{

    // The 'Supplier' functional interface allows us to get values/objects via predefined set of operations :D

    public static void main(String[] args) {
        
        // To Print a Random Number

        Random random = new Random();
        
        java.util.function.Supplier<Integer> supplier = () -> random.nextInt(100);

        System.out.println(supplier.get());

    }


}