package me.ihainan.tij.chapter5;

/**
 * 验证 This 构造器
 */
public class Exercise_9 {
    public static class Person{
        String name;
        int age;

        public void print(){
            System.out.println("name = " + name + ", age = " + age);
        }

        public Person(String name, int age){
            this(name);
            // this(age); /* This 构造函数只能被调用一次 */
            this.name = name;
        }

        public Person(String name){
            this.name = name;
        }

        public Person(int age){
            this.age = age;
        }
    }

    public static void main(String[] args){
        Person p = new Person("ihainan", 23);
        p.print();
    }
}
