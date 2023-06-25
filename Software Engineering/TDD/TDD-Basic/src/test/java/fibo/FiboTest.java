package test.java.fibo;

import main.java.fibo.Fibo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiboTest {

    //Step 01 TDD
    @Test
    public void test1(){
        //기댓값과 출력결과가 같은지 비교하는 것
        assertEquals(0, Fibo.fib_step01(0));
        assertEquals(1, Fibo.fib_step01(0));
    }

    @Test
    public void test2(){
        assertEquals(0, Fibo.fib_step01(0));
        assertEquals(1, Fibo.fib_step01(1));
    }

    @Test
    public void test22(){
        //기댓값과 결과값을 일일이 적어주는게 귀찮다
        int testCases[][] = {{0,0}, {1,1}};
        for(int i = 0 ; i < testCases.length ; ++i){
            assertEquals(testCases[i][1], Fibo.fib_step01(testCases[i][0]));
        }
    }

    @Test
    public void test3(){
        int testCases[][] = {{0,0}, {1,1}, {2,1}, {3,2}};
        for(int i = 0 ; i < testCases.length ; ++i){
            assertEquals(testCases[i][1], Fibo.fib_step03(testCases[i][0]));
        }
    }

    @Test
    public void test4(){
        int testCases[][] = {{0,0}, {1,1}, {2,1}, {3,2}, {4,3}, {5,5}};
        for(int i = 0 ; i < testCases.length ; ++i){
            assertEquals(testCases[i][1], Fibo.fib_step04(testCases[i][0]));
        }
    }

    @Test
    public void test5(){
        int testCases[][] = {{0,0}, {1,1}, {2,1}, {3,2}, {4,3}, {5,5}};
        for(int i = 0 ; i < testCases.length ; ++i){
            assertEquals(testCases[i][1], Fibo.fib_step05(testCases[i][0]));
        }
    }

}
