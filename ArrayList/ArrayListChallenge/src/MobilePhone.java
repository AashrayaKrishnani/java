public class MobilePhone {

    private ContactsList contactsList = new ContactsList() ;

     public void printContacts() {
         contactsList.printContacts();
     }

     public void addContact( String name, String number ) {
         contactsList.addContact(name, number);
     }

       public void removeContact(String name, String number) {
           contactsList.removeContact(name, number) ;
     }

     public void removeContact( Contact c ) {
         removeContact(c.getName(),c.getNumber());
     }

     public void removeContact( int index ) {
         contactsList.removeContact(index);
     }

     public void removeContact( String name ) {
         contactsList.removeContact(name);
     }

     public void modifyContact( int index, Contact newContact ){

         contactsList.updateContact(index, newContact );

     }

     public void modifyContactName( String oldName, String newName) {

         contactsList.updateName(oldName, newName);

     }

    public void modifyContactName( int index, String newName) {

        contactsList.updateName(index, newName);

    }

    public void modifyContactNumber( String oldNumber, String newNumber) {

        contactsList.updateNumber(oldNumber, newNumber);

    }
    public void modifyContactNumber( int index, String newNumber) {

        contactsList.updateNumber(index, newNumber);

    }

    public void searchContact( Contact c ) {
         contactsList.search(c);
    }
    public void searchName(String name ) {
        contactsList.searchName(name);
    }
    public void searchNumber( String number ) {
        contactsList.searchNumber(number);
    }
}
