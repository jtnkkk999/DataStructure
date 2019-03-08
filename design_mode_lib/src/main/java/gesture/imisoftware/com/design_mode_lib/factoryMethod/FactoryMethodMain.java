package gesture.imisoftware.com.design_mode_lib.factoryMethod;

/**
 * 只要是工厂模式，在生成实例时，一定会用到模板方法设计模式
 */
public class FactoryMethodMain {
    public static void main(String[] args){
        Factory factory = new IDCardFactory();
        Product p1 = factory.create("雪胜");
        Product p2 = factory.create("星星");
        p1.use();
        p2.use();
    }
}
