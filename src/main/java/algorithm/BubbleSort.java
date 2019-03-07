package algorithm;

import algorithm.BaseSortClass;

import java.util.Arrays;

/**
 * 冒泡排序,有两种优化方案
 * @author renjin
 */
public class BubbleSort extends BaseSortClass{

    public static void main(String[] args) {
        int[] arras={9,1,5,8,3,7,4,6,2};
        buddleSort1(arras);
        buddleSort2(arras);

    }

    //优化方案1,有序的话,少一次循环
    public static void buddleSort1(int[] arras){
        int b=0;
        for (int x=0;x<arras.length-1;x++){
            boolean flag=true;
            for (int i=0;i<arras.length-1-x;i++){
                int j=i+1;
                if (arras[i]>arras[j]){
                    int temp=arras[i];
                    arras[i]=arras[j];
                    arras[j]=temp;
                    flag=false;
                }
                b++;
            }
            System.out.println(Arrays.toString(arras));
            if (flag){////如果没有交换过元素，则已经有序,优化点
                break;
            }
        }
        System.out.println(b);
    }

    //优化方案2,我们可以记下最后一次交换的位置，后边没有交换，必然是有序的，然后下一次排序从第一个比较到上次记录的位置结束即可。
    public static void buddleSort2(int[] arras){
        int b=0;
        for (int x=0;x<arras.length-1;x++){
            boolean flag=true;
            int pos=0;
            int k=arras.length-1-x;
            for (int i=0;i<k;i++){
                int j=i+1;
                if (arras[i]>arras[j]){
                    int temp=arras[i];
                    arras[i]=arras[j];
                    arras[j]=temp;
                    flag=false;
                    pos=j;
                }
                b++;
            }
            System.out.println(Arrays.toString(arras));
            if (flag){////如果没有交换过元素，则已经有序,优化点
                break;
            }
            k=pos;
        }
        System.out.println(b);
    }
}
