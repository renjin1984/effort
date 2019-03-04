package test.single;

public class LazySingle {
    //私有构造函数
    private LazySingle(){
        System.out.println("加载了LazySingle这个懒汉单例");
    }

    //懒汉的意思在这里,第一次加载类的时候,这个属性是不创建对象的,只有在使用的时候才创建对象
    private static  LazySingle lazySingle=null;

    //加了同步保证多线程情况下返回一个对象,不然就不叫单例了
    public static synchronized LazySingle getLazySingle(){
        if (lazySingle==null)
            lazySingle = new LazySingle();
        return lazySingle;
    }

}
