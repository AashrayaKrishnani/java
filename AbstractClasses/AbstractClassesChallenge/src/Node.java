public class Node extends ListItem {

    public Node(Object value) {
        super(value);
    }

    @Override
    public ListItem next() {
        return this.nextNode ;
    }

    @Override
    public ListItem back() {
        return this.backNode ;
    }

    @Override
    public boolean setNext(ListItem item) {
        if(item != null) {
            this.nextNode = item;
            return true;
        }
        else
            return false ;
    }

    @Override
    public boolean setBack(ListItem item) {
        if(item != null) {
            this.backNode = item;
            return true;
        }
        else
            return false ;
    }

    @Override
    public int compareTo(ListItem item) {
        if( item != null ) {
            String s = (String) super.getValue() ;
            return s.compareTo((String)item.getValue()) ;
        }
        else
        return -1;
    }
}
