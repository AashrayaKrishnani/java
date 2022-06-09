public class Main {
    public static void main(String[] args) {

        FootballPlayer messi = new FootballPlayer("Lionel Messi") ;
        CricketPlayer dhoni = new CricketPlayer("M.S. Dhoni") ;
        TennisPlayer nadal = new TennisPlayer("Rafael Nadal") ;

        Team<FootballPlayer> myTeam = new Team<>("FC Barcelona") ;
        Team<FootballPlayer> rma = new Team<>("Real Madrid FC") ;
        Team<FootballPlayer> atm = new Team<>("Athletico Madrid") ;

        myTeam.addPlayer(messi);
//        myTeam.addPlayer(dhoni);
//        myTeam.addPlayer(nadal);

        System.out.println("Number of Players in FCB = " + myTeam.numberOfPlayers());
        
        System.out.println();

       // Team<String> brokenTeam = new Team<String>("This Shouldn't work!");
      //  String is not a player, hence casting will give error
        // That's why we use <T extends Player>.

        Team<CricketPlayer> csk = new Team<>("Chennai Super Kings" ) ;
        Team<CricketPlayer> rcb = new Team<>("Royal Challengers Bangalore" ) ;

        csk.addPlayer(dhoni) ;

        Team<TennisPlayer> nye = new Team<>("Ney York Empire") ;
        Team<TennisPlayer> chicagoSmash = new Team<>("Chicago Smash") ;

        nye.addPlayer(nadal) ;

        System.out.println();

        myTeam.result(rma, 2, 3) ;
        rcb.result(csk, 256,255);
        nye.result(chicagoSmash, 21, 21);
        myTeam.result(atm, 2, 2) ;

        System.out.println();

        myTeam.ranking();
        rma.ranking();
        csk.ranking();
        nye.ranking();
        chicagoSmash.ranking();

        System.out.println();

        myTeam.compareToAndPrint(rma);
        atm.compareToAndPrint(myTeam);
        myTeam.compareToAndPrint(atm);
        nye.compareToAndPrint(chicagoSmash);
        chicagoSmash.compareToAndPrint(nye);

    }
}
