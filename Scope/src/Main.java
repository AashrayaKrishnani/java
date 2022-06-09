public class Main {

    public static void main(String[] args) {

        ScopeCheck.InnerClass innerClass = new ScopeCheck().new InnerClass() ;

        // Statement below won't work. Var3 is private.
       // System.out.println("Var3 = " + innerClass.var3);

    }
}
