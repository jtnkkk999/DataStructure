package gesture.imisoftware.com.design_mode_lib.factoryMethod;

/**
 * 工厂抽象类，使用了模板方法设计模式
 */
public abstract class Factory {
    public final Product create(String owner){
        Product p = createProduct(owner);
        registerProduct(p);
        return p;
    }
    protected abstract Product createProduct(String owner);
    protected abstract void registerProduct(Product product);
}
