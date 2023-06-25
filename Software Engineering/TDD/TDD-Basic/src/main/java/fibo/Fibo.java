package main.java.fibo;// 예제 1 피보나치 수열 TDD로 작성하기

public class Fibo {
    //Step 01 -> 0을 대입햇을 때 0을 반환하도록 해야한다.
    public static int fib_step01(int n){
        return 0;
    }

    public static int fib_step02(int n){
        if(n==0) return 0;
        return 1;
    }

    public static int fib_step03(int n){
        if(n==0) return 0;
        if(n <= 2) return 1;
        return 2;
    }

    public static int fib_step04(int n){
        if(n==0) return 0;
        if(n <= 2) return 1;
        return fib_step05(n-1) + 1;
    }

    public static int fib_step05(int n){
        if(n==0) return 0;
        if(n <= 2) return 1;
        return fib_step05(n-1) + fib_step05(n-2);
    }
}
