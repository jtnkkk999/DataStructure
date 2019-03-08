package gesture.imisoftware.com.design_mode_lib.factoryMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * IDCardFactory IDCard工厂
 */
public class IDCardFactory extends Factory{
    private List owners = new ArrayList();
    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    /**
     * 注册功能，就是存储一下
     * @param product
     */
    @Override
    protected void registerProduct(Product product) {
        owners.add(((IDCard)product).getOwner());
    }

    public List getOwners(){
        return owners;
    }
}
