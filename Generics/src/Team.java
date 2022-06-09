import java.util.ArrayList;

public class Team<T extends Player> implements Comparable<Team<T>> {

    private String name ;

    private int won = 0,
               lost =  0,
              tied  = 0,
             played = 0 ;

    private ArrayList<T> members = new ArrayList<>() ;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addPlayer( T player ){



        if(members.contains(player)){
            System.out.println(((Player)player).getName() + " is already in the team.") ;
            return false ;
        }

        members.add(player) ;
        System.out.println(((Player)player).getName() + " added to the team - " + this.name);
        return true ;

    }

    public int getTotalLost() {
        return lost;
    }

    public int getTotalTied() {
        return tied;
    }

    public int getTotalPlayed() {
        return played;
    }

    public int numberOfPlayers() {
       return this.members.size() ;
    }

    public void result( Team<T> team, int theirScore, int ourScore ){

        if( team != null ) {

        if( ourScore > theirScore ) {
            System.out.println( "Team - " +  this.name  + " [" + ourScore + "]" + " won against " + team.getName() +" [" + theirScore + "]"  ) ;

            won++ ;
            played++ ;
            team.lost++ ;
            team.played++;

        }
        else if( theirScore == ourScore ){
            System.out.println( "Team" + " - " +  this.name + " [" + ourScore + "]" + " drew against " + team.getName() +" [" + theirScore + "]" );
            tied++ ;
            played++ ;
            team.tied++ ;
            team.played++;
        }
        else{
            System.out.println( team.getName() +" [" + theirScore + "]" + " won against Team " + this.name + " [" + ourScore + "]");

            lost++ ;
            played++ ;
            team.won++ ;
            team.played++;
        }

        }
        else{
            System.out.println("Error - Invalid input.");
        }


    }


    public void ranking(){

        int ranking = this.won*2 + tied ;

        System.out.println(this.name + ": " + ranking );
    }

    public int rank() {
        return this.won * 2  + tied ;
    }


    @Override
    public int compareTo(Team<T> o) {

        int rankingOurs = this.won * 2 + tied ;
        int rankingTheirs = o.won *2 + o.tied ;

        if( rankingOurs == rankingTheirs  )
            return 0 ;
        if(rankingOurs > rankingTheirs)
            return -1 ;
        else
            return 1 ;

    }

    public void compareToAndPrint(Team<T> o){
        int result = this.compareTo(o) ;

        if(result == 0 )
            System.out.println(this.name + "("+ this.rank() +")" + " Equals in ranking with " + o.name + "("+o.rank()+")");
        else if(result == 1)
            System.out.println(this.name + "("+ this.rank() +")" + " has Lesser ranking than " + o.name+ "("+o.rank()+")");
        else
            System.out.println(this.name + "("+ this.rank() +")" + " has Greater ranking than " + o.name+ "("+o.rank()+")");
    }
}
