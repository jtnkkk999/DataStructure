package gesture.imisoftware.com.design_mode_lib.prototype;

import java.util.HashMap;

public class Manager {
    HashMap<String,Product> data = new HashMap<>();
    public void registr(String name,Product proto){
        data.put(name,proto);
    }
    public Product create(String name){
        Product p = data.get(name);
        return p;
    }
}
