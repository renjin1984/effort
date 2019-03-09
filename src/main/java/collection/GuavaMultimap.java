package collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.concurrent.Executors;

/**
 * 测试guava的mulitmap,有4个常用的实现
 */
public class GuavaMultimap {



    public static void main(String[] args) {

        testArrayListMultiMap();
        testHashMultiMap();
    }


    public static void testArrayListMultiMap(){
        Multimap<String,String> multimap= ArrayListMultimap.create();
        multimap.put("fruit", "bannana");
        multimap.put("fruit", "apple");
        multimap.put("fruit", "apple");

        System.err.println(multimap.size());
        System.err.println(multimap.get("fruit"));
    }

    public static void testHashMultiMap(){
        Multimap<String,String> multimap= HashMultimap.create();
        multimap.put("fruit", "bannana");
        multimap.put("fruit", "apple");
        multimap.put("fruit", "apple");

        System.err.println(multimap.size());
        System.err.println(multimap.get("fruit"));
    }

    //还有一个LinkedHashMultimap 按照put的顺序
    // Multimap<Integer, Integer> map = LinkedHashMultimap.<Integer, Integer>create();

    //还有一个TreeMultimap,会按照key的顺序排序,value也会排序,默认是升序
    //Multimap<Integer, Integer> map = TreeMultimap.<Integer, Integer>create();

}
