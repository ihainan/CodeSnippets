package me.ihainan.tij.chapter1;

/**
 * 验证 1.7 小节中 Java 的动态绑定特性。
 */
public class Program1_7 {
    /**
     * 形状类
     */
    static class Shape{
        /**
         * 绘制自身
         */
        public void draw(){
            System.out.println("Draw a shape.");
        }
    }

    /**
     * 圆类，继承自 Shape 类
     */
    static class Circle extends Shape{
        /**
         * 绘制自身
         */
        @Override
        public void draw() {
            System.out.println("Draw a circle.");
        }
    }

    /**
     * 绘制一个图形
     * @param shape 将被绘制的图形
     */
    public static void drawAShape(Shape shape){
        shape.draw();
    }

    /**
     * 绘制一个圆
     * @param circle 奖杯绘制的圆形
     */
    public static void drawACircle(Circle circle){
        circle.draw();
    }

    public static void main(String[] agrs){
        Shape shape = new Shape();
        Circle circle = new Circle();
        drawAShape(shape);
        // drawACircle(shape);  /* 错误 */
        drawAShape(circle);
    }
}
