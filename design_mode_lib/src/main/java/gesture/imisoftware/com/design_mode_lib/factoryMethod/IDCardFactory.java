package gesture.imisoftware.com.design_mode_lib.factoryMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * IDCardFactory IDCard工厂
 * IDCard
 */
public class IDCardFactory extends Factory{
    private HashMap<String,String> owners = new HashMap<>();
    @Override
    protected Product createProduct(String owner,String cardNumber) {
        return new IDCard(owner,cardNumber);
    }

    /**
     * 注册功能，就是存储一下
     * @param product
     */
    @Override
    protected void registerProduct(Product product) {
        owners.put(((IDCard)product).getOwner(),((IDCard)product).getCardNumber());
    }

    public HashMap<String, String> getOwners(){
        return owners;
    }
}
