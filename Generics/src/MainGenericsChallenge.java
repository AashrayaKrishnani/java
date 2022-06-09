import com.sun.security.jgss.GSSUtil;

public class MainGenericsChallenge {

    public static void main(String[] args) {

        League<Team<FootballPlayer>> Laliga = new League<>("La Liga") ;

        Team<FootballPlayer> fcb = new Team<>("FC Barcelona") ;
        Team<FootballPlayer> rma = new Team<>("Real Madrid FC") ;
        Team<FootballPlayer> atm = new Team<>("Athletico Madrid") ;
        Team<FootballPlayer> sevilla = new Team<>("Sevilla FC") ;

        // Team<CricketPlayer> csk = new Team<>("Chennai Super Kings" ) ;

        Laliga.addTeam(fcb, atm,rma, sevilla ) ;

        // Laliga.addTeam(csk) ;        Compile Time error since csk isn't a Football Team ! :D

        System.out.println();

        fcb.result(rma, 2, 3) ;
        sevilla.result(atm, 4, 2);
        fcb.result(sevilla, 2, 0);
        rma.result(atm, 2 , 2 );
        fcb.result(atm, 2, 3) ;

        System.out.println();

        Laliga.printTable();

    }

}
