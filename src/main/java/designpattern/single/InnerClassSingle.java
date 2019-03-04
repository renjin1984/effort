package test.single;

//采用内部类改进了懒汉模式,懒汉模式还是要加锁,对性能有影响
public class InnerClassSingle {

    private  InnerClassSingle(){
      System.out.println("使用内部类加载单例");
  }



  public static InnerClassSingle getInnerClassSingle(){
        return InnerClass.innerClassSingle;
  }

    public static  class InnerClass{
      //内部类的InnerClassSingle属性设置为private,不可能被外部访问
        private  static  InnerClassSingle innerClassSingle=new InnerClassSingle();
    }

}
