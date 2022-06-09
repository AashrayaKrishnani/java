/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class LISTOFLISTS
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));

        int t = sc.nextInt();

        while(t-- > 0)
        {
            Map<Integer, Integer> map = new HashMap<>();

            int n = sc.nextInt();
            boolean allSame = true;
            boolean allDiff = true;

            List<Integer> temp = new ArrayList<>();

            for(int i = n; i>0; i-- )
            {
                List<Integer> list = new ArrayList<>();
                list.add(sc.nextInt());

                int reps = 1;

                if(i != n)
                {
                    if (allSame && list.get(0) != temp.get(temp.size() - 1 ))
                    {
                        allSame = false;
                    }

                    if(allDiff && temp.contains(list.get(0)))
                        allDiff = false;

                    if(map.containsKey(list.get(0))){
                        reps = (map.get(list.get(0))) + 1;
                    }
                }

                temp.add(list.get(0));
                map.put(list.get(0), reps);
            }

            if(allSame)
            {
                System.out.println("0");
            }
            else if(allDiff)
            {
                System.out.println("-1");
            }
            else {

                int maxReps = 0;

                for(int i: map.values()){
                    if(i > maxReps)
                        maxReps = i;
                }

                int listsPossible = maxReps - 1 ;

                System.out.println(n - listsPossible);

            }

        }
    }
}
