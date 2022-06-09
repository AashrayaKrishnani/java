import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    private static Map<HeavenlyBody.Key, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();
    private static Set<HeavenlyBody> moons = new HashSet<>();

    public static void main(String[] args) {

        // Initializing Planets and Respective Moons, Manually!
        {
            HeavenlyBody temp = new Planet("Mercury", 88);
            solarSystem.put(temp.getKey(), temp);
            planets.add(temp);

            temp = new Planet("Venus", 225);
            solarSystem.put(temp.getKey(), temp);
            planets.add(temp);

            temp = new Planet("Earth", 365);
            solarSystem.put(temp.getKey(), temp);
            planets.add(temp);
            {
                HeavenlyBody tempMoon = new Moon("Moon", 27);
                solarSystem.put(tempMoon.getKey(), tempMoon);
                temp.addSatellite(tempMoon);
            }
            temp = new Planet("Mars", 687);
            solarSystem.put(temp.getKey(), temp);
            planets.add(temp);
            {
                HeavenlyBody tempMoon = new Moon("Deimos", 1.3);
                solarSystem.put(tempMoon.getKey(), tempMoon);
                temp.addSatellite(tempMoon);

                tempMoon = new Moon("Phobos", 0.3);
                solarSystem.put(tempMoon.getKey(), tempMoon);
                temp.addSatellite(tempMoon);
            }
            temp = new Planet("Jupiter", 4332);
            solarSystem.put(temp.getKey(), temp);
            planets.add(temp);
            {
                HeavenlyBody tempMoon = new Moon("Io", 1.8);
                solarSystem.put(tempMoon.getKey(), tempMoon);
                temp.addSatellite(tempMoon);

                tempMoon = new Moon("Europa", 3.5);
                solarSystem.put(tempMoon.getKey(), tempMoon);
                temp.addSatellite(tempMoon);

                tempMoon = new Moon("Ganymede", 7.1);
                solarSystem.put(tempMoon.getKey(), tempMoon);
                temp.addSatellite(tempMoon);

                tempMoon = new Moon("Callisto", 16.7);
                solarSystem.put(tempMoon.getKey(), tempMoon);
                temp.addSatellite(tempMoon);
            }
            temp = new Planet("Saturn", 10759);
            solarSystem.put(temp.getKey(), temp);
            planets.add(temp);

            temp = new Planet("Uranus", 30660);
            solarSystem.put(temp.getKey(), temp);
            planets.add(temp);

            temp = new Planet("Neptune", 60225);
            solarSystem.put(temp.getKey(), temp);
            planets.add(temp);

            temp = new Planet("Pluto", 90520);
            solarSystem.put(temp.getKey(), temp);
            planets.add(temp);
        }

        //OutPut Checking Code.
        {

            // Problem Due to NOT overriding equals() and hashCode(), from Object class, in HeavenlyBody class.
            // Overriding equals() is necessary as default equals() only checks for equal References.
            // Overriding hashCode() is necessary as Objects are stored in Batches under SAME HASH CODE.
            // So even if two objects are equal but at different places due to different hashCodes, then
            // The set will keep them because it only checks for equality in the same batch, i.e, same hashCode.

            {
                // Pluto as DWARFPLANET will be added, since the other pluto already present is
                // a PLANET not a DWARFPLANET, hence not eqaul ;D

                HeavenlyBody pluto = new DwarfPlanet("Pluto", 90520);
                // Even tho Orbital Period is diff from already added 'Pluto'

                planets.add(pluto);
                // This will work if HeavenlyBody doesn't over ride the equals() and hashCode()
                // methods to actually Compare the names in the Right Place.
                // And Pluto will be printed twice by the code below

                Object o = new Object();
                // The super Basic equals() function that only does a Reference check.
                System.out.println( "\nDoes object o equals object o = " + o.equals(o) + "\n"  );

                // The String equals(), however is different!
                System.out.println( "Does String 'Java' equals String ' ' = " + "Java".equals(" ") + "\n"  ) ;
            }
            System.out.println("Planets :-");
            for (HeavenlyBody planet : planets) {
                System.out.println("\t" + planet);  // Overridden toString() in HeavenlyBody allows to print in such a way ;D
            }

            HeavenlyBody body = solarSystem.get( HeavenlyBody.makeKey("Jupiter", HeavenlyBody.BodyTypes.PLANET));
            System.out.println("\nMoons of " + body.getName() + " :-\n");
            Set<HeavenlyBody> moons = body.getSatellites();
            System.out.println();  // As body.getSatellites() calls a bunch of hashCode() methods, so... ;p
            for (HeavenlyBody moon : moons) {
                System.out.println("\t" + moon);
            }
            System.out.println();
        }

        // Initializing set of all moons using Sets.addAll() function. Printing output then.
        {
            System.out.println("Moons :-\n");

            moons = new HashSet<>();
            for (HeavenlyBody planet : planets) {
                moons.addAll(planet.getSatellites()); // Like how we perform union operation in two Sets to add them! :D
            }

            System.out.println(); // As planet.getSatellites() will call a bunch of hashCode() methods ;p

            for (HeavenlyBody moon : moons) {
                System.out.println("\t" + moon);
            }
        }


        // Testing after updating Code as per SetChallenge Guidelines :-
        {
            System.out.println("\n---------------------------------------------\n" +
                    "Testing after updating Code as per SetChallenge Guidelines :-\n");

            // Checking if adding a copy of a planet replaces original present or doesn't :-
            {
                System.out.println("1. To check if adding a copy in solar system replaces the original or doesn't:-\n");

                HeavenlyBody.Key keyForPluto = HeavenlyBody.makeKey("Pluto", HeavenlyBody.BodyTypes.PLANET);  // Key For Pluto

                // We have a pluto as PLANET and one as DWARFPLANET
                System.out.println("[+] Original Planet Pluto --> " +
                        solarSystem.get( keyForPluto ));
                System.out.println("[+] Adding another PLANET Pluto but with orbital period as '1'..");
                Planet pluto = new Planet("Pluto", 1);
                solarSystem.put(pluto.getKey(), pluto);
                System.out.println("[+] Printing current pluto in 'solarSystem' --> "
                                    + solarSystem.get( keyForPluto ) + "\n");
            }
        }

    }

}
