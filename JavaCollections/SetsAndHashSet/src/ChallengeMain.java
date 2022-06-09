public class ChallengeMain {

    public static void main(String[] args) {

        Labrador rover = new Labrador("Rover");
        Dog rover2 = new Dog("Rover");

        System.out.println("\nrover is a Labrador. \nrover2 is a Dog.\n");

        System.out.println( "rover2.equals(rover) = " + rover2.equals(rover));
        System.out.println( "rover.equals(rover2) = " + rover.equals(rover2));
    }

}
