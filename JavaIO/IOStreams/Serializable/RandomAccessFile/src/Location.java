import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

// We must implement the 'Serializable' interface to have a class object used in the Object*Output/Input*Stream :D
// It doesn't have any ABSTRACT METHODS to be implemented, but is just a marker for the JVM to be alert
// about objects that may be serialized :D

public class Location implements Serializable {


    // Java Documentation for 'Serializable' mentions to define this 'long serialVersionUID' to identify the class.
    //
    // If we don't do it, the compiler will do it and there will be no issue as such.
    // However, since there are different compilers, so it may be done DIFFERENTLY each time, hence leading to
    // possible issues in near future, so better to do it :D
    //
    // The JVM uses it to match the serialized class data with the current class data. If we update a classe's data
    // say added more methods/variables, then JVM won't match previously written serialized object data with this unless
    // we ourselves enter the serialVersionUID manually, because if it is implemented by the compiler then it'd be
    // changed as we modified the class data and hence won't match with previously saved files! ;D

    private long serialVersionUID = 1L;

    // The data is currently saved and marked with this UID, so for fun you can uncomment above line and see,
    // that it will throw ClassNotFound exception as the automatic UID generated by the compiler will not match with the
    // UID already marked in the saved file as '1' (in long).

    private final int locationID ;
    private final String description ;
    private final Map<String, Integer> exits ;

    public Location(int locationID, String description, LinkedHashMap<String, Integer> exits)  {
        this.locationID = locationID;
        this.description = description;

        if(exits == null)
            this.exits = new LinkedHashMap<>();
        else
            this.exits = new LinkedHashMap<>(exits) ;

        this.exits.put("Q",0);
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new LinkedHashMap<>(exits);
    }

    protected void addExit(String direction, int locationID  ){
        this.exits.put( direction, locationID);
    }



}
