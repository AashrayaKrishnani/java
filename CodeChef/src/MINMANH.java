import java.io.BufferedInputStream;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

class Point implements Comparable<Point>{
    int x;
    int y;

    @Override
    public int compareTo(Point o) {
        if()
    }

    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class MINMANH {

    public static void main(String[] args) {

        Scanner sc = new Scanner(new BufferedInputStream(System.in));

        String input[] = sc.nextLine().split(" ");

        Set<Point> S = new LinkedHashSet<>();
        Set<Point> T = new LinkedHashSet<>();

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        for(int i = 0; i<N; i++){
            input =  sc.nextLine().split(" ");

            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            S.add(new Point(x, y));
        }


        for(int i = 0; i<M; i++){
            input =  sc.nextLine().split(" ");

            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            T.add(new Point(x, y));
        }

        for(Point p1: T){

            int minDist = Integer.MAX_VALUE;

            for(Point p2: S)
            {
                if(calcManhDist(p1, p2) < minDist)
                    minDist = calcManhDist(p1, p2);
            }
            System.out.println(minDist);
        }

    }

    static int calcManhDist(Point p1, Point p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

}
