package test.single;

public class HungrySingle {

    //私有构造函数
    private HungrySingle(){
        System.out.println("加载了HungrySingle这个饿汉单例");
    }

    //类在第一次初始化的时候就会创建这个属性,这就是饿汉的意思,用不用都会先加载.
    private static HungrySingle hungrySingle=new HungrySingle();

    public static HungrySingle getHungrySingle(){
        return  hungrySingle;
    }

}
