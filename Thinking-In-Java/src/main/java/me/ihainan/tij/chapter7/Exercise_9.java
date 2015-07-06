package me.ihainan.tij.chapter7;

/**
 * Chapter 7 - Exercise 9
 */

class Root{
    ComponentA componentA = new ComponentA();
    ComponentB componentB = new ComponentB();
    public Root(){
        System.out.println("Root");
    }
}

class ComponentA{
    public ComponentA(){
        System.out.println("ComponentA");
    }
}

class ComponentB{
    public ComponentB(){
        System.out.println("ComponentB");
    }
}

class Stem extends Root{
    public static int getInt(){
        System.out.println("Invoke getInt method");
        return 1;
    }

    public static int number = getInt();
    public Stem(){
        System.out.println("Stem");
    }
}

public class Exercise_9 {
    public static void main(String[] args){
        Stem stem = new Stem();
    }
}
