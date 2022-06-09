import java.util.Scanner;
public class Main {

    private static final Scanner sc = new Scanner(System.in) ;
    private static final MyLinkedList myLinkedList = new MyLinkedList(null)  ;

    public static void main( String[] args ) {

        System.out.println("\n Welcome To the MyLinkedList Program In The Mobile Phone. \n \n");

        printInstructions();
        int input  ;

        while(true){

            if( sc.hasNextInt()  ) {
                input = sc.nextInt();
                sc.nextLine() ;

//                switch (input) {
//                    case 0 -> {
////                        System.out.println("\n Thank You!!! : )");
////                        System.exit(0);
////                    }
//                    case 1 -> add();
//                    case 2 -> remove();
//                    case 3 -> printList() ;
//                    case 4 -> search();
//                    case 5 -> update();
//                    default -> printInstructions();
//                }

            }
            else{
                System.out.println("Please Enter An INTEGER Value.") ;
                sc.nextLine() ;
            }



        }

    }

    public static void printInstructions() {

        System.out.println("\n Press ");
        System.out.println("\t '0' To Exit. ");
        System.out.println("\t '1' To Add Item to the list. ");
        System.out.println("\t '2' To Remove Item From the List. ");
//        System.out.println("\t '3' To Remove A Contact. ");
//        System.out.println("\t '4' To Search A Contact/Name/Number. ");
//        System.out.println("\t '5' To Update A Contact Number Or Name ");
//        System.out.println("\t '6' To Exit out of the Program. ");


    }
}
