import java.util.*;

public class Theatre {

    private final String theatreName;
    protected List<Seat> seats ;

    static final Comparator<Seat> PRICE_ORDER_ASCENDING = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {

            if(seat1.getPrice() < seat2.getPrice())
            return -1;
            else if(seat1.getPrice() > seat2.getPrice())
            return 1;
            else return 0;
        }
    };

    static final Comparator<Seat> PRICE_ORDER_DESCENDING = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if(seat1.getPrice() < seat2.getPrice())
                return 1;
            else if(seat1.getPrice() > seat2.getPrice())
                return -1;
            else return 0;
        }
    };



    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;
        this.seats = new ArrayList<>() ;



        int lastRow = 'A' + (numRows -1) ;

        for( char row = 'A' ; row <= lastRow ; row++){

            for(int seatNum = 1 ; seatNum <= seatsPerRow ; seatNum++){

                double price = 500.00 ;

                if( row == lastRow )
                    price += 250.00;

                Seat seat = new Seat( row + String.format("%02d", seatNum), price);
                // The "%02d" Here is used for decimal int conversion (d)
                // to add left padding of width '2'
                // With value '0'
                // Meaning '1' becomes '01' while '11' stays '11'.
                seats.add(seat);
            }

        }

        Collections.sort(seats);

    }

    public String getTheatreName() {
        return theatreName;
    }

    public boolean reserveSeat( String seatNumber ){

        Seat requestedSeat = new Seat(seatNumber, 0) ;

//        for(Seat seat : seats){
//            System.out.print(".");
//            if(seat.getSeatNumber().equals(seatNumber))
//                requestedSeat = seat ;
//        }

        int foundSeat = Collections.binarySearch(seats, requestedSeat, null) ;

        if(foundSeat<0) {
            System.out.println("[+] Seat Not Found, Sorry.");
            return false;
        }
        // Reaching here means it got the seat!
        requestedSeat = seats.get( foundSeat ) ;
//        System.out.println("requested seat number --> " + requestedSeat.getSeatNumber());
        return requestedSeat.reserve() ;
    }

    public boolean cancelSeat( String seatNumber ){
        Seat cancelledSeat = new Seat(seatNumber, 0) ;

//        for(Seat seat : seats){
//            if(seat.getSeatNumber().equals(seatNumber))
//                cancelledSeat = seat ;
//        }

        int foundSeat = Collections.binarySearch(seats, cancelledSeat, null) ;

        if(foundSeat < 0 ){
            System.out.println("[+] Seat Not Found, Sorry.");
            return false;
        }
        cancelledSeat = seats.get(foundSeat);
        return cancelledSeat.cancel() ;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    // For Testing
    public void printSeats(){
        for(Seat seat: seats){
            System.out.println(seat.getSeatNumber());
        }
    }
        public class Seat implements Comparable<Seat> {
            private final String seatNumber;
            private boolean reserved = false ;
            private double price ;

            public Seat(String seatNumber, double price) {
                this.seatNumber = seatNumber;
                this.price = price;
            }

            @Override
            public int compareTo(Seat o) {
                return this.seatNumber.compareToIgnoreCase(o.getSeatNumber());
            }

            public String getSeatNumber() {
                 return seatNumber;
              }



            public boolean isReserved() {
                return reserved;
            }


            public boolean reserve() {
                if(!this.reserved){
                    System.out.println("[+] Seat " + this.seatNumber +" now reserved.");
                    this.reserved = true;
                    return true;
                }
                else{
                    System.out.println("[+] Seat " + this.seatNumber + " already reserved, Sorry.");
                    return false ;
                }

            }

            public boolean cancel(){
                if(this.reserved){
                    this.reserved = false ;
                    System.out.println("[+] Seat reservation for " + this.seatNumber + " cancelled successfully.");
                    return true;
                } else {
                    System.out.println("[+] Seat " + this.seatNumber + " was not reserved in the first place." +
                                         "\n" + "Cancellation unsuccessful.");
                    return false;
                }
            }
            public double getPrice() {
                return price;
            }
        }

}

































