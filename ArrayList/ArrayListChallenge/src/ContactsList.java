import java.util.ArrayList;
import java.util.List;

public class ContactsList {

    private List<Contact> contacts = new ArrayList<Contact>() ;

    public void search( Contact c ) {

        if( containsName(c.getName()) != -1  && containsNumber(c.getNumber()) != -1){
            System.out.println("Contact with name " + c.getName() + " and number " + c.getNumber() +
                    " Found At Position " + (containsName(c.getName())+1) + ".");
        }
        else{
            System.out.println("Contact Not Found.");
        }

    }
    public void searchName( String name ) {

        if( containsName(name) != - 1){
            System.out.println("Contact with name = " + name +  " and number " + contacts.get(containsName(name)).getNumber() +
                    " found in the list at position " + (containsName(name) + 1));
        }
        else{
            System.out.println("Contact with name = " + name + ", not found in the list.");
        }

    }

    public void searchNumber( String number ) {

        if( containsNumber(number) != - 1){
            System.out.println("Contact with number = " + number +  " and name " +
                    contacts.get(containsNumber(number)).getName()+ " found in the list at position "
                    + (containsNumber(number) + 1));
        }
        else{
            System.out.println("Contact with number = " + number + ", not found in the list.");
        }

    }

    public void updateContact( int index, Contact newContact ) {

        if( index < contacts.size() && index > -1 ){
            System.out.println("Contact Updated Successfully.");
            contacts.get( index ).changeNumber( newContact.getNumber());
            contacts.get( index ).changeName( newContact.getName());
        }
        else{
            System.out.println( "Contact Not Found In The List.");
        }

    }

    public void updateNumber(String oldNumber, String newNumber) {

        if( containsNumber( oldNumber ) != -1 ) {

            contacts.get( containsNumber( oldNumber ) ).changeNumber(newNumber);
            System.out.println("Contact Updated Successfully.");

        }
        else{
            System.out.println("Contact with number " + oldNumber + " not found in the list." ) ;
        }

    }

    public void updateNumber(int index, String newNumber) {

        if( index > -1 && index < contacts.size() ) {

            contacts.get( index).changeNumber(newNumber);
            System.out.println("Contact Updated Successfully.");
        }
        else{
            System.out.println("Invalid Index" ) ;
        }

    }

    public void updateName(String oldName, String newName) {

        if( containsName( oldName ) != -1 ) {

            contacts.get( containsName( oldName ) ).changeName(newName);
            System.out.println("Contact Updated Successfully.");
        }
        else{
            System.out.println("Contact with name " + oldName + " not found in the list." ) ;
        }

    }

    public void updateName(int index, String newName) {

        if( index > -1 && index < contacts.size() ) {

            contacts.get( index).changeName(newName);
            System.out.println("Contact Updated Successfully.");
        }
        else{
            System.out.println("Invalid Index" ) ;
        }

    }
    public void removeContact( Contact c ) {
        removeContact(c.getName(),c.getNumber());
    }

    public void removeContact( String name ) {

        boolean found = false ;
        int index = 0 ;
        Contact obj = new Contact(name, null);

        for(int i = 0 ; i< contacts.size() ; i++) {

            if(contacts.get(i).getName().equals(name)) {
                 obj.changeNumber(contacts.get(i).getNumber() ) ;
                found = true ;
                index = i ;
                break;
            }
        }
          if(found) {
              System.out.println("Contact " + obj.getName() + " with number " + obj.getNumber() + " removed from position "
                      + (index+1));
              contacts.remove(index);
          }
          else{
              System.out.println("No Contact with name = " + name +" found in the list.");
              System.out.println("Please Retry With Correct Name.");
          }

    }

    public void removeContact( int index ) {
        if( index > -1 && index < contacts.size() ) {
            System.out.println("Contact " + contacts.get(index).getName() + " with number " + contacts.get(index).getNumber()
                    + " removed from position " + (index + 1));
            contacts.remove(index);
        }
        else {
            System.out.println("Invalid Index");
        }

    }

    public void removeContact( String name, String number ) {

        if(containsName(name) != -1 ) {
            System.out.println("Contact " + name + " with number " + number + " removed from position " + ( containsName(name) + 1 ) );
            contacts.remove(containsName(name) );
        }
        else {
            System.out.println("Contact Not Present in the list.");
        }

    }

    public void addContact( String name, String number ) {

        Contact obj = new Contact(name, number) ;
        if(containsName(name) == -1  && containsNumber(number) == -1) {
              if( containsName(name) == -1) {
                if(containsNumber(number) == -1){
                    contacts.add(obj);
                    System.out.println("Contact " + name + " with number " + number + " added at position " + (contacts.indexOf(obj)+1));
                }
               else {
                    System.out.println("Contact with Same Number Present.");
                    System.out.println("Please Try Again with Different a Number.");
                }
               }
              else {
                  System.out.println("Contact with Same Name Present.");
                  System.out.println("Please Try Again with Different a name.");
              }
            }
        else {
            System.out.println("Contact Already Present.");
        }
    }

    public void addContact( Contact c ) {
        addContact( c.getName(), c.getNumber());
    }

    public void printContacts() {

        if(contacts.size() > 0 ) {
            System.out.println("\n Current List Of Contacts Is As Follows - ");

            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i).getName() + " --> " + contacts.get(i).getNumber());
            }
        }
        else{
            System.out.println("\n List Is Currently Empty.");
        }
    }



    private int containsName( String name ) {

        int index = -1 ;

        for(int i = 0 ; i< contacts.size() ; i++) {

            if(contacts.get(i).getName().equals(name)) {
                index = i ;
                break;
            }
        }

        return index ;

    }

    private int containsNumber( String number ) {


        int index = -1 ;

        for(int i = 0 ; i< contacts.size() ; i++) {

            if(contacts.get(i).getNumber().equals(number)) {

                index = i ;
                break;
            }
        }

        return index ;

    }


}
