package gesture.imisoftware.com.design_mode_lib.factoryMethod;

/**
 * 只要是工厂模式，在生成实例时，一定会用到模板方法设计模式
 * frameworks是不用修改的，idcard是具体的某个
 *
 * 不同工厂要写不同的工厂类。不同的工厂生成不同的对象，但是生成的是相同的抽象类。
 *
 * *不能定义abstract 的构造函数，因为构造函数是不能被继承的。
 */
public class FactoryMethodMain {
    public static void main(String[] args){
        Factory factory = new IDCardFactory();
        Product p1 = factory.create("雪胜","0001");
        Product p2 = factory.create("星星","0002");
        p1.use();
        p2.use();
    }
}
