package gesture.imisoftware.com.design_mode_lib.factoryMethod;

/**
 * IDCard
 */
public class IDCard extends Product{
    private String owner;
    IDCard(String owner){
        System.out.println("制作"+owner+"的IDCard");
        this.owner  = owner;
    }
    @Override
    public void use() {
        System.out.println("使用"+owner+"的IDCard");
    }

    public String getOwner(){
        return owner;
    }
}
