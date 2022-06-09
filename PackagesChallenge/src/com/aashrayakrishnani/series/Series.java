package com.aashrayakrishnani.series;

import java.math.BigInteger;

public class Series {


    public static int nSum(int n){

        return (n*(n+1)) / 2 ;

    }

    public static long fact( int n ) {

        long factorial = 1 ;

        for( int i = 1 ; i <= n ; i++ )
            factorial *= i ;

        return factorial ;

    }

    public static String factBigInt(int n ) {

        BigInteger factorial = new BigInteger("1") ;


        for( int i = 1 ; i <= n ; i++ )
            factorial = factorial.multiply(BigInteger.valueOf(i)) ;

        return factorial.toString() ;

    }

    public static long fibonacci(int n){

        if(n == 0 )
            return 0 ;

        if( n == 1 )
            return 1 ;

        return fibonacci(n-1) + fibonacci(n-2) ;

    }

}
