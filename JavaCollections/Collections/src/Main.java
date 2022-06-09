import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

        public static void main(String[] args) {

            Theatre theatre = new Theatre("PVR Cinemas", 20, 25) ;

           // theatre.getSeats();

//            if(theatre.reserveSeat("H11"))
//                System.out.println("Please Pay.");
//
//            if(theatre.reserveSeat("H11"))
//                System.out.println("Please Pay.");
//
//            if(theatre.cancelSeat("H11"))
//                System.out.println("Refund initiated.");
//
//            if(theatre.cancelSeat("H12"))
//                System.out.println("Refund initiated.");

            // Making a shallow copy ( Reference copy ) of the ArrayList in theatre.
            ArrayList<Theatre.Seat> seatCopy = new ArrayList<>(theatre.seats);
            printSeats(seatCopy);

            seatCopy.get(0).reserve(); // Reserves Seat "A01" in seatCopy
            theatre.reserveSeat("A01"); // Says already reserved because we reserved it's reference above.

            // Again to show that seatCopy is a reference Copy.
            // But The Lists are SEPERATE.
            // Meaning any changes to the LISTS will be Different.
            // Any changes to the OBJECTS in the lists will be SAME!
            System.out.println("Reversing order of seats in seatCopy");
            Collections.reverse(seatCopy);  // We Can also use Collections.shuffle() to shuffle them.
            System.out.println("Printing seatCopy -");
            printSeats(seatCopy);
            System.out.println("Printing theatre.seats without changing it :-");
            printSeats(theatre.seats);

            Theatre.Seat minSeat = Collections.min(seatCopy);
            Theatre.Seat maxSeat = Collections.max(seatCopy);
            System.out.println("Min Seat Number = " + minSeat.getSeatNumber()) ;
            System.out.println("Max Seat Number = " + maxSeat.getSeatNumber()) ;

            System.out.println("\nSorting out seatsCopy using sortList().");
            sortList(seatCopy);
            System.out.println("Printing seatCopy -");
            printSeats(seatCopy);

//            List<Theatre.Seat> newList = new ArrayList<>(theatre.seats.size());
//            Collections.copy(newList, theatre.seats);
//           Above Code Generates An Error, because it creates an ArrayList with required size.
//           But the list is actually empty, and has no Objects for the Collections.copy() method to copy into!


//            However the code below works ;D  Plus above error could have been checked by printing newList.size()

            Theatre test = new Theatre("Test", 0, 0);

            List<Theatre.Seat> newList = new ArrayList<>(theatre.seats.size());
            for(int i = 0 ; i < theatre.seats.size() ; i++){
                Theatre.Seat obj = test.new Seat(String.valueOf(i), 0);
                newList.add(obj);
            }

            Collections.copy(newList, theatre.seats);

            System.out.println("Printing newList :-");
            printSeats(newList);

            // To check if newList() is a shallow copy or a Deep Copy

            newList.get(1).reserve();
            if(theatre.reserveSeat("A02") == true)
                System.out.println("newList is a DeepCopy of theatre.seats");
            else
                System.out.println("newList is a ShallowCopy of theatre.seats");


            // To Use Comparator declared in Theatre class.
            List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());   // Making a shallow copy of theatre.seats
            priceSeats.add( theatre.new Seat("X01", 1000.00));
            priceSeats.add( theatre.new Seat("A00", 1.00));
            priceSeats.add( theatre.new Seat("Y01", 123.00));
            priceSeats.add( theatre.new Seat("H28", 9999.00));
            priceSeats.add( theatre.new Seat("A22", 0));

            System.out.println("\nSorting out priceSeats using Collections.sort() and PRICE_ORDER_ASCENDING COMPARATOR  ");
            Collections.sort(priceSeats, Theatre.PRICE_ORDER_ASCENDING);
            System.out.println("Printing seatCopy -");
            printSeats(priceSeats);

            System.out.println("\nSorting out priceSeats using Collections.sort() and PRICE_ORDER_DESCENDING COMPARATOR  ");
            Collections.sort(priceSeats, Theatre.PRICE_ORDER_DESCENDING);
            System.out.println("Printing seatCopy -");
            printSeats(priceSeats);

        }

        public static void printSeats(List<Theatre.Seat> list){

            for(Theatre.Seat seat : list){
                System.out.print(" " + seat.getSeatNumber() + "@" + seat.getPrice());
            }

            System.out.println("\n--------------------------------\n");

        }

        public static void sortList(List<? extends Theatre.Seat> list){
            for(int i = 0 ; i < list.size() - 1; i++){
                for(int j=0; j < list.size() -1-i ; j++){
                      if( list.get(j).compareTo( list.get(j+1) ) > 0)
                           Collections.swap(list, j, j+1);
                }
            }

        }

}























