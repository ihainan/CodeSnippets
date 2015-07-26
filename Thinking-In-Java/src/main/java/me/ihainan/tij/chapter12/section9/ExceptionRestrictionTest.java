package me.ihainan.tij.chapter12.section9;

/**
 * 异常限制测试
 */

class CustomExceptionOne extends Exception{}
class CustomExceptionTwo extends Exception{}

abstract class MyAbstractClass{
    public void f() throws CustomExceptionOne{}
}

interface MyInterface{
    public void f() throws CustomExceptionTwo;
}

public class ExceptionRestrictionTest extends MyAbstractClass implements MyInterface{
    public void f(){

    }
    // public void f() throws CustomExceptionTwo{
    // public void f() throws CustomExceptionOne{}
}
