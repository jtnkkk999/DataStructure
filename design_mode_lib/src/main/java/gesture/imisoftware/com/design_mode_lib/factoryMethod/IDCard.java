package gesture.imisoftware.com.design_mode_lib.factoryMethod;

/**
 * IDCard
 */
public class IDCard extends Product{
    private String owner;
    private String cardNumber;
    IDCard(String owner,String cardNumber){
        System.out.println("制作"+owner+"的IDCard");
        this.owner  = owner;
        this.cardNumber = cardNumber;
    }
    @Override
    public void use() {
        System.out.println("使用"+owner+"的IDCard:"+cardNumber);
    }

    public String getOwner(){
        return owner;
    }
    public String getCardNumber(){
        return cardNumber;
    }
}
