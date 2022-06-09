public abstract class ListItem {

    protected ListItem nextNode ;
    protected ListItem backNode ;

    public ListItem(Object value) {
        this.value = value;
    }

    protected Object value ;

    public abstract ListItem next() ;
    public abstract ListItem back() ;
    public abstract boolean setNext(ListItem item) ;
    public abstract boolean setBack(ListItem item) ;

    public abstract int compareTo( ListItem item ) ;

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
