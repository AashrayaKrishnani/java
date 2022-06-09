import java.util.Scanner;

public class X {

    private int x ;

    public X() {

        System.out.print("Enter the number to see it's table from 1 to 12 : ");

        Scanner x = new Scanner(System.in);

        this.x = x.nextInt();

        System.out.println();

    }



    public void x(){

        System.out.println("Table of " + this.x + " from 1 to 12 is as follows - \n");

        for(int x = 1 ; x <=12 ; x++ ){

            System.out.println( x + " times " + this.x + " = " + (x*this.x));

        }

    }

}
