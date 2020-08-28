package v2.di;

public class Renminbi implements Money {
    
    @Override
    public void pay() {
        System.out.println("使用人民币成功进行了支付");
    }
}
