package me.ihainan.tij.chapter7.Exercise15.package1;

/**
 * Created by ihainan on 7/6/15.
 */
public class ClassB {
    public static void main(String[] args){
        ClassA classA = new ClassA();
        classA.f(); // 同一个包的类调用 Protected 权限的成员
    }
}
