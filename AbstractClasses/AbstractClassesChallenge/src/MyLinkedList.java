import java.util.List;

public class MyLinkedList implements NodeList {

    private ListItem root = null ;


    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root ;
    }

    @Override
    public boolean addItem(ListItem item) {

        if(item != null ) {
            System.out.println("\n Adding " + item.getValue() + " to the list...");
        }

        if( this.root != null ) {
            root = item ;
            return true ;
        }
        else{
            ListItem current = this.getRoot() ;
            int comparison ;
            boolean loop = true ;

            while(loop){

                comparison = current.compareTo(item);

                if( comparison == 0 ) {
                    System.out.println((String) item.getValue() + " already exists in the list.");
                    return false ;
                }

                if(comparison > 0 ){
                    if( current.back() != null) {
                        ListItem temp = current.back();
                        current.setBack(item);
                        item.setNext(current);
                        item.setBack(temp);
                        temp.setNext(item);
                        return true;
                    }
                    else{
                        item.setNext(this.root) ;
                        root.setBack(item) ;
                        this.root = item ;
                        return true ;
                    }
                }

                if( comparison < 0 ) {

                    if(current.next() == null ) {

                        current.setNext(item) ;
                        item.setBack(current) ;
                        return true ;
                    }

                }

                current = current.next() ;

            }
            return false ;
        }

    }

    @Override
    public boolean removeItem(ListItem item) {

        if( root != null) {

            ListItem temp = root ;

            if(item != null ) {
                System.out.println("\n Deleting " + item.getValue() + " from the list...");
            }

            while( temp != null ) {

                if(temp.compareTo(item) == 0 ) {

                    if( temp == root ) {

                        this.root = temp.next() ;

                    }
                    else {
                        ListItem next = temp.next();
                        ListItem back = temp.back();

                        next.setBack(back);
                        back.setNext(next);
                    }
                    return true ;

                }

                temp = temp.next() ;
            }

            System.out.println( item.getValue() + " not found.");
            return false ;
        }
        else{
            System.out.println("List is empty.") ;
            return false ;
        }

    }

    @Override
    public void traverse(ListItem root) {

        if(root == null) {
            System.out.println("List is currently Empty.");
        }
        else {

            ListItem temp = root ;
            int num = 1 ;

            System.out.println(" \n List is as follows :- \n ");

            while( temp != null ) {

                System.out.println( num +". " + temp.getValue() );
                temp = temp.next() ;
                num++ ;
            }

        }

    }
}
