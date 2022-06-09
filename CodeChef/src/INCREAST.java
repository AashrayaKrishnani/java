import java.io.BufferedInputStream;
import java.util.Scanner;

public class INCREAST {

    public static void main(String[] args) {

        Scanner sc = new Scanner(new BufferedInputStream(System.in));

        int t = sc.nextInt();
        sc.nextLine();

        while(t-- > 0)
        {
            String input[] = sc.nextLine().trim().split(" ");

            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            System.out.println(n + " " + m);

            int length = 0;
            String output = "";

            if(n==m)
            {
                length = 2*(n+1);
                for(int i = 0; i<length; i++)
                    output += i%2 == 0 ? "1" : "0";
            }
            else
            {
                char c1 = n>m ? '0' : '1';
                char c2 = c1=='0' ? '1' : '0';

                length = 2 * (Math.max(n, m)) + 1 ;

                for(int i = 0; i < length; i++)
                {
                    output += i%2 == 0 ? "" + c1 : "" + c2 ;
                }

                int excess = Math.abs(m-n) - 1;

                System.out.println("excess = " + excess);

                if(excess>0)
                {
                    for(int i = 0 ; i < excess*3; i+=3)
                    {
                        output = output.substring(0, length -(i+2)) + "" + c1 + output.substring(length-(i+2), length);
                        length++;
                    }
                }

            }

            System.out.println("Result of check = " + check(output, n, m));

            System.out.println(length);
            System.out.println(output);

        }

    }

    static boolean check(String s, int n, int m){
        boolean result = true;

        int x = 0;
        int y = 0;

        for(int i = 1; i<s.length()-1; i++)
        {
            if(s.charAt(i) == '0' && s.charAt(i+1) == '1' && s.charAt(i-1) == '1')
            {
                y++;
            }
            else if (s.charAt(i) == '1' && s.charAt(i+1) == '0' && s.charAt(i-1) == '0')
            {
                x++;
            }
        }

        if(m!=y || n!=x){
            result = false;
        }

        return result;
    }

}
