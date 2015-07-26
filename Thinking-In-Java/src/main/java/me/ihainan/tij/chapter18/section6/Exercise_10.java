package me.ihainan.tij.chapter18.section6;

/****************** Exercise 10 ******************
 * Create a class with two methods, f() and g().
 * In g(), throw an exception of a new type that
 * you define. In f(), call g(), catch its
 * exception and, in the catch clause, throw a
 * different exception (of a second type that you
 * define). Test your code in main().
 ***********************************************/

class CustomExceptionOne extends Exception{}
class CustomExceptionTwo extends Exception{}

public class Exercise_10 {
    static void f() throws CustomExceptionOne {
        throw new CustomExceptionOne();
    }

    static void g() throws CustomExceptionTwo {
        try {
            f();
        } catch (CustomExceptionOne customExceptionOne) {
            CustomExceptionTwo exceptionTwo = new CustomExceptionTwo();
            exceptionTwo.initCause(customExceptionOne);
            throw exceptionTwo;
        }
    }

    public static void main(String[] args){
        try {
            g();
        } catch (CustomExceptionTwo customExceptionTwo) {
            customExceptionTwo.printStackTrace();
        }
    }

    /* Output:
    me.ihainan.tij.chapter18.section6.CustomExceptionTwo
	at me.ihainan.tij.chapter18.section6.Exercise_10.g(Exercise_10.java:24)
	at me.ihainan.tij.chapter18.section6.Exercise_10.main(Exercise_10.java:32)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:134)
Caused by: me.ihainan.tij.chapter18.section6.CustomExceptionOne
	at me.ihainan.tij.chapter18.section6.Exercise_10.f(Exercise_10.java:17)
	at me.ihainan.tij.chapter18.section6.Exercise_10.g(Exercise_10.java:22)
	... 6 more
     */
}
