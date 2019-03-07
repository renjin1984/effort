package algorithm;

import java.util.Arrays;

/**
 * 快速排序,采用的前后指针法
 * @author renjin
 */
public class FastSort extends BaseSortClass{

    private static  int[] arrays={6,1,2,7,9,3,4,5,10,8};

    public static void main(String[] args) {
        start(0,arrays.length-1);
    }

    //这里递归,返回的结果集继续
    public static void start(int left,int right){
        if (left<right)
        {
            //进行第一次单趟排序
            int div = fastSort(left, right);
            //递归子问题，划分区间
            start( left, div - 1);
            //递归子问题
            start( div + 1, right);
        }

    }

    public static int fastSort(int left,int right){

        System.out.println(left+","+right);

        int tem=left;
        while (left!=right){

            while (arrays[right]>arrays[tem]&&left<right){
                right--;
            }

            while (arrays[left]<=arrays[tem]&&left<right){
                left++;
            }
            swap(arrays,left,right);

        }
        //把基数和中间的数换位置
        swap(arrays,tem,left);

        System.out.println(Arrays.toString(arrays));

        return right;
    }
}
