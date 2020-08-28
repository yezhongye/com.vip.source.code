public class BoyFactoryBean {
    
    public Boy buildBoy() {
        return new Boy() {
            @Override
            public void sayLove() {
                System.out.println("I love you, my gril!" +hashCode());
            }
        };
    }
}
