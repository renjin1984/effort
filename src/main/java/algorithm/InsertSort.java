package algorithm;


import java.util.Arrays;

/**
 * 插入排序
 * 从数组中的第i元素开始(i>1),依次去已经排好序的数组中找到应该的位置j(拿第i个元素循在已经排好序的数组中循环比较),交换i和j里面的元素.
 * @author renjin
 */
public class InsertSort extends BaseSortClass{

    public static void main(String[] args) {
        int[] arras={9,1,5,8,3,7,4,6,2};
        insertSort(arras);
    }

    public static  void insertSort(int[] arrays){
        int k=0;
        for (int i=1;i<arrays.length;i++){
            for (int j=0;j<i+1;j++){
                if (arrays[i]<arrays[j]){
                    swap(arrays,i,j);
                }
                k++;
            }
            System.out.println(Arrays.toString(arrays));
        }
        System.out.println(k);
    }
}
