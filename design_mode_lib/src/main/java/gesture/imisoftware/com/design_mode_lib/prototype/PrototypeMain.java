package gesture.imisoftware.com.design_mode_lib.prototype;

/**
 * 通过复制生成实例，原型模式。
 * cloneable接口，标记接口，空实现。
 * clone()方法的复制是浅复制，复制的不是元素，当复制数组的时候，其实复制的是引用。
 *          值进行复制，不会调用被复制对象的构造函数。
 */
public class PrototypeMain {
    public static void main(String[] args){
        //创建管理类
        Manager m = new Manager();
        //创建该实例通过clone方法创建了Product实例。此时的实例是不通用的
        LineProduct lp = new LineProduct('*');
        //注册，其实就是存入HashMap
        m.registr("1",lp);
        //重新获取实例，
        Product product = m.create("1");
        product.use("hahahaha");
        Product product1 = product.createClone();
        System.out.println("-------------"+(product==product1));
    }
    //历史天量
}
