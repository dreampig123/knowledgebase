package org.pegcode.common.utils;

import java.util.Arrays;

public class SortUtil {

    public static int[] unsort = new int[]{2, 6, 4, 3, 1, 7, 9, 8, 5};

    public static void insertSort(int[] unsort) {
        for (int i = 0; i < unsort.length - 1; i++) {
            int temp = unsort[i + 1];
            while (i >= 0) {
                if (temp < unsort[i]) {
                    unsort[i+1]=unsort[i];
                    i--;
                }else{
                    break;
                }
            }
            unsort[i+1] = temp;
        }
        System.out.println(Arrays.toString(unsort));
    }

    public static void InsertSort(int[] arr)
    {
        for (int i = 0; i < arr.length - 1; ++i)
        {
            //记录有序序列最后一个元素的下标
            int end = i;
            //待插入的元素
            int tem = arr[end + 1];
            //单趟排
            while (end >= 0)
            {
                //比插入的数大就向后移
                if (tem < arr[end])
                {
                    arr[end + 1] = arr[end];
                    end--;
                }
                //比插入的数小，跳出循环
                else
                {
                    break;
                }
            }
            //tem放到比插入的数小的数的后面
            arr[end  + 1] = tem;
            //代码执行到此位置有两种情况:
            //1.待插入元素找到应插入位置（break跳出循环到此）
            //2.待插入元素比当前有序序列中的所有元素都小（while循环结束后到此）
        }
        System.out.println(Arrays.toString(unsort));
    }

    public static void main(String[] args) {
        insertSort(unsort);
        InsertSort(unsort);
    }
}
