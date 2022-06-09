import java.util.Objects;

public class Dog {
    private final String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object o) {  // 'final' here stops sub-classes from over riding equals() method.

        if (this == o) return true;

        if (!(o instanceof Dog)) return false;

        Dog dog = (Dog) o;
        return dog.getName().equals(this.name);
    }

    @Override
    public int hashCode() { // 'final' here stops sub-classes from over riding hashCode() method.
        return name.hashCode();
    }
}
