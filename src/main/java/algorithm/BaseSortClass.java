package algorithm;

import java.util.Arrays;

public  class BaseSortClass {

    public static void swap(int[] arrys,int i,int j ){
        int temp=arrys[j];
        arrys[j]=arrys[i];
        arrys[i]=temp;
    }

    public static void main(String[] args) {
        int[] arr={1,3,4};
        swap(arr,0,2);
        System.out.println(Arrays.toString(arr));
    }
}
