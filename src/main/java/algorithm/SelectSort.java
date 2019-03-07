package algorithm;

import java.util.Arrays;

/**
 * 选择排序 遍历数组,从中选出最小的放在数组的第一位,循环剩下的每次选最小的依次交换
 * Created by renjin on 2019/3/5.
 */
public class SelectSort extends BaseSortClass{

    public static void main(String[] args) {
        int[] arras={9,1,5,8,3,7,4,6,2};
        selectSort(arras);
    }

    public static void selectSort(int[] arrays){
        for (int i=0;i<arrays.length;i++){
            int min=i;
            for (int j=i+1;j<arrays.length;j++){
                if (arrays[j]<arrays[min]){
                    min=j;
                }
            }
            swap(arrays,i,min);
            System.out.println(Arrays.toString(arrays));
        }

    }
}
