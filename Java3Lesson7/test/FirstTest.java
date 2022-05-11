package Java3Lesson7.test;

import Java3Lesson7.annotations.AfterSuite;
import Java3Lesson7.annotations.BeforeSuite;
import Java3Lesson7.annotations.Test;

public class FirstTest {
    @BeforeSuite
    public void Inception(){
        System.out.println(" -> FirstTest.methodBeforeSuite");
    }
    @Test(priority = 1)
    public void test3() {
        System.out.println("-> FirstTest.methodTest - priority 1");
    }
    @Test(priority = 1)
    public void simpleMethodTest1First() {
        System.out.println("-> simpleMethodTest1First - priority 1");
    }
    @Test(priority = 3)
    public void test1() {
        System.out.println("-> FirstTest.methodTest - priority 3");
    }
    @Test(priority = 3)
    public void simpleMethodTest1Sec() {
        System.out.println("-> simpleMethodTest1Second - priority 3");
    }
    @Test(priority = 8)
    public void test2() {
        System.out.println("-> FirstTest.methodTest - priority 8");
    }
    @AfterSuite
    public void end(){
        System.out.println(" -> FirstTest.methodAfterSuite");
    }
}
