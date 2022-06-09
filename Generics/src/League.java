import java.util.ArrayList;
import java.util.Collections;

public class League <T extends Team>  {

    private String name ;
    private ArrayList<T> league ;

    public League(String name) {

        this.name = name ;
        this.league = new ArrayList<>() ;

    }

    public boolean addTeam( T team ){

        if(league.contains(team))
            return false ;

        league.add(team);
        System.out.println(team.getName() + " added to the league " + this.name);
        return true ;
    }

    public boolean addTeam( T ... teams ){

        for( T team : teams ) {

            if (league.contains(team))
               continue ;

            league.add(team);
            System.out.println(team.getName() + " added to the league " + this.name);
        }

        return true ;
    }

    public String getName() {
        return name;
    }

    public void printTable(){

        System.out.println("\n League table for team " + this.name + " is follows - \n");

        int i = 1 ;

        Collections.sort(league) ;  // Only able to use since we have implemented the COMPARABLE interface in Teams class

        for( T team : league ) {
            System.out.println(i + ". " + team.getName() + " [" + team.rank() + "]");
            i++ ;
        }

        System.out.println();

    }



}
