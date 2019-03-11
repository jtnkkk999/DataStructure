package gesture.imisoftware.com.design_mode_lib.prototype;

/**
 * 接口可以继承类，然后再被其他类实现。
 */
public interface Product extends Cloneable{
    public abstract void use(String s);
    public abstract Product createClone();
}
