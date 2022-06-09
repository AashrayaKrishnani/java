import java.util.Scanner;

public class Main {

    private static final MobilePhone mainPhone = new MobilePhone() ;
    private static final Scanner sc = new Scanner(System.in) ;

    public static void main( String[] args ) {

        System.out.println("\n Welcome To the Contact List Program In The Mobile Phone. \n \n");

          printInstructions();
          int input  ;

          while(true){

              if( sc.hasNextInt()  ) {
                  input = sc.nextInt();
                  sc.nextLine() ;

                  switch (input) {
                      case 0 -> printInstructions();
                      case 1 -> printList();
                      case 2 -> add();
                      case 3 -> remove();
                      case 4 -> search();
                      case 5 -> update();
                      case 6 -> {
                          System.out.println("\n Thank You!!! : )");
                          System.exit(0);
                      }
                      default -> System.out.println("Invalid Value");
                  }

              }
              else{
                  System.out.println("Please Enter An INTEGER Value.") ;
                  sc.nextLine() ;
              }



          }

    }

    private static void remove() {

        System.out.println("\n Press");
        System.out.println("\t '1' to Remove A Contact by Entering Name And Number. ");
        System.out.println("\t '2' to Remove A Contact by Entering it's POSITION in the list. ");
        System.out.println("\t '3' to Remove A Contact by Entering It's Name Only. ");

        int input ;
        if( sc.hasNextInt()) {
            input = sc.nextInt() ;
            sc.nextLine() ;

            switch (input) {
                case 1 -> {
                    System.out.println("\n Enter Name : ");
                    String name = sc.hasNext() ? sc.nextLine() : null;
                    System.out.println("\n Enter Number : ");
                    String number = sc.hasNext() ? sc.nextLine() : null;
                    Contact obj = new Contact(name, number);
                    mainPhone.removeContact(obj);
                }
                case 2 -> {
                    System.out.println("\n Enter Position : ");
                    int index = sc.hasNextInt() ? sc.nextInt() - 1 : 0;
                    sc.nextLine();
                    mainPhone.removeContact(index);
                }
                case 3 -> {
                    System.out.println("\n Enter Name : ");
                    String name2 = sc.hasNext() ? sc.nextLine() : null;
                    mainPhone.removeContact(name2);
                }
                default -> System.out.println("Invalid Value.");
            }
        }
        else{
            System.out.println("Invalid Value.");
        }


    }

    private static void update() {

        System.out.println("\n Press");
        System.out.println("\t '1' to Update A Contact With an Entirely New Contact. ");
        System.out.println("\t '2' to Update A Contact's Name. ");
        System.out.println("\t '3' to Update A Contact's Number. ");

        int input ;
        if( sc.hasNextInt()) {
            input = sc.nextInt() ;
            sc.nextLine() ;
            switch (input) {
                case 1 -> {
                    System.out.println("\n Enter Old Contact's Position : ");
                    int pos = sc.hasNextInt() ? sc.nextInt() : -1;
                    sc.nextLine();
                    System.out.println("\n Enter New Contact's Name : ");
                    String newName = sc.hasNext() ? sc.nextLine() : null;
                    System.out.println("\n Enter New Contact's Number : ");
                    String newNumber = sc.hasNext() ? sc.nextLine() : null;
                    Contact newObj = new Contact(newName, newNumber);
                    mainPhone.modifyContact((pos - 1), newObj);
                }
                case 2 -> {
                    System.out.println("\n Enter New Name : ");
                    String nameNew = sc.hasNext() ? sc.nextLine() : null;
                    System.out.println("\n Enter Either Old Contact's Name or Contact's Position : ");
                    int contactIndex;
                    String nameOld ;
                    if (sc.hasNextInt()) {
                        contactIndex = sc.nextInt() - 1;
                        sc.nextLine();
                        mainPhone.modifyContactName(contactIndex, nameNew);
                    } else {
                        nameOld = sc.hasNext() ? sc.nextLine() : null;

                        mainPhone.modifyContactName(nameOld, nameNew);
                    }
                }
                case 3 -> {
                    System.out.println("\n Enter New Number : ");
                    String numberNew = sc.hasNext() ? sc.nextLine() : null;
                    System.out.println("\n Enter Old Contact's Position : ");
                    int indexContact ;
                    if (sc.hasNextInt()) {
                        indexContact = sc.nextInt() - 1;
                        sc.nextLine();
                        mainPhone.modifyContactNumber(indexContact, numberNew);
                    }
                }
                default -> System.out.println("Invalid Value.");
            }
        }
        else{
            System.out.println("Invalid Value.");
        }


    }

    private static void search() {

        System.out.println("\n Press");
        System.out.println("\t '1' to Search For A Contact ");
        System.out.println("\t '2' to Search For A Name ");
        System.out.println("\t '3' to Search For A Number ");

        int input ;
        if( sc.hasNextInt()) {
            input = sc.nextInt() ;
            sc.nextLine() ;

            switch (input) {
                case 1 -> {
                    System.out.println("\n Enter Name : ");
                    String name = sc.hasNext() ? sc.nextLine() : null;
                    System.out.println("\n Enter Number : ");
                    String number = sc.hasNext() ? sc.nextLine() : null;
                    Contact obj = new Contact(name, number);
                    mainPhone.searchContact(obj);
                }
                case 2 -> {
                    System.out.println("\n Enter Name : ");
                    String name2 = sc.hasNext() ? sc.nextLine() : null;
                    mainPhone.searchName(name2);
                }
                case 3 -> {
                    System.out.println("\n Enter Number : ");
                    String number2 = sc.hasNext() ? sc.nextLine() : null;
                    mainPhone.searchNumber(number2);
                }
                default -> printInstructions();
            }
        }
        else{
            System.out.println("Invalid Value.");
        }

    }

    private static void add() {
        System.out.println("\n Enter Name : ");
        String name = sc.hasNext() ? sc.nextLine() : null;


        System.out.println("\n Enter Number : ");
        String number = sc.hasNext() ? sc.nextLine() : null ;


        mainPhone.addContact(name, number)  ;

    }

    private static void printList() {
        mainPhone.printContacts();
    }

    public static void printInstructions() {

        System.out.println("\n Press ");
        System.out.println("\t '0' To View Instructions. ");
        System.out.println("\t '1' To Print Contacts List. ");
        System.out.println("\t '2' To Add A Contact. ");
        System.out.println("\t '3' To Remove A Contact. ");
        System.out.println("\t '4' To Search A Contact/Name/Number. ");
        System.out.println("\t '5' To Update A Contact Number Or Name ");
        System.out.println("\t '6' To Exit out of the Program. ");


    }


}
