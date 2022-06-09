import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public abstract class HeavenlyBody {

    private final Key key;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public enum BodyTypes {
        STAR,
        PLANET,
        DWARF_PLANET,
        MOON,
        COMET,
        ASTEROID
    }

    public HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodyType) {
        this.key = new Key(name, bodyType);
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }

    @Override
    public final boolean equals(@NotNull Object anObject) {

        if (this == anObject) {
            System.out.println("\nSame Reference passed as Argument. \n" + this.getName() + ".equals() called\n");
            return true;
        }
        if (anObject instanceof HeavenlyBody) {
            HeavenlyBody aHeavenlyBody = (HeavenlyBody) anObject;
            System.out.println("\n" + this.getName() + " a (" + this.getBodyType() + ") .equals() called");
            System.out.println("To check equality against " + aHeavenlyBody.getBodyType() + " named - " + aHeavenlyBody.getName());
            boolean result = this.key.equals(aHeavenlyBody.getKey());
            System.out.println("Result = " + result + "\n");

            return result;
        }
        return false;
    }

    @Override
    public final int hashCode() {
        System.out.println("hashCode() for " + this + ", called.");
        return this.key.hashCode();  // Adding a num so as to avoid zero error if name = null ;D
    }

    public Key getKey() {
        return key;
    }

    public String getName() {
        return this.key.getName();
    }

    public BodyTypes getBodyType() {
        return this.key.getBodyType();
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public boolean addSatellite(HeavenlyBody satellite) {

        if (this.getBodyType() == BodyTypes.PLANET) {
            if (satellite.getBodyType() == BodyTypes.MOON)
                this.satellites.add(satellite);
            return false;
        }

        if (satellite.getBodyType() == BodyTypes.ASTEROID)
            return false;

        if (satellite.getBodyType() == BodyTypes.COMET)
            return false;

        return this.satellites.add(satellite);
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(satellites);
    }

    @Override
    public String toString() {
        return this.getBodyType() + ": " + this.getName() + " (Name)" + ", " + this.orbitalPeriod + " (Orbital Period)";
    }

    public static Key makeKey(String name, BodyTypes bodyType) {
        return new Key(name, bodyType);
    }

    public static final class Key {
        private String name;
        private BodyTypes bodyType;

        public Key(String name, BodyTypes bodyType) {
            this.name = name;
            this.bodyType = bodyType;
        }

        public String getName() {
            return name;
        }

        public BodyTypes getBodyType() {
            return bodyType;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() + 192 + this.bodyType.hashCode();
        }

        @Override
        public boolean equals(@NotNull Object obj) {
            if (this == obj)
                return true;
            if (obj instanceof Key) {
                Key key = (Key) obj;
                if (this.name.equals(key.getName())) {
                    return this.getBodyType() == key.getBodyType();
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return this.getBodyType() + ": " + this.name;
        }
    }
}
