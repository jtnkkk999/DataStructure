package gesture.imisoftware.com.design_mode_lib.factoryMethod;

/**
 * 工厂抽象类，使用了模板方法设计模式
 *
 * framework
 */
public abstract class Factory {
    public final Product create(String owner,String cardNumber){
        Product p = createProduct(owner,cardNumber);
        registerProduct(p);
        return p;
    }
    protected abstract Product createProduct(String owner,String cardNumber);
    protected abstract void registerProduct(Product product);
}
