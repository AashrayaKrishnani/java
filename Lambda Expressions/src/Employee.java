public class Employee {
    private  String name;
    private int age;

    public Employee(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name;
    }    

    public int getAge(){return this.age;}
}
