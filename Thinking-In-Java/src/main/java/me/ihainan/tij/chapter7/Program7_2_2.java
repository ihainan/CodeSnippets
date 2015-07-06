package me.ihainan.tij.chapter7;

/**
 * Created by ihainan on 7/6/15.
 */

class People{
    String name;
    public People(String name){
        this.name = name;
    }
}

class Man extends People{
    public Man(String name) {
        super(name);
    }
}

public class Program7_2_2 {
    public static void main(String[] args){
        Man man = new Man("ihainan");
        System.out.println(man.name);
    }
}
