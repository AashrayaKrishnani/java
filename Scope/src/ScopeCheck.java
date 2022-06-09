public class ScopeCheck {

    public void getVar3(){

        InnerClass innerClass = new InnerClass();

        //Parent class can access child class throughout. Just instantiation is required!
        System.out.println("Var3 = " + innerClass.var3);

    }

    class InnerClass{

        private int var3 = 3 ;

    }

}
