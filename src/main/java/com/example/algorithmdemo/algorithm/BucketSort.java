package com.example.algorithmdemo.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @description:
 * 人为设置一个BucketSize，作为每个桶所能放置多少个不同数值
 * （例如当BucketSize==5时，该桶可以存放｛1,2,3,4,5｝这几种数字，但是容量不限，即可以存放100个3）；
 * 遍历输入数据，并且把数据一个一个放到对应的桶里去；
 * 对每个不是空的桶进行排序，可以使用其它排序方法，也可以递归使用桶排序；
 * 从不是空的桶里把排好序的数据拼接起来。
 * @Author: wzp
 * @Date: 2020/2/20 14:53
 * @Version: 1.0
 */
public class BucketSort {

    private static final Logger log = LoggerFactory.getLogger(SelectionSort.class);

    public static void main(String[] args){
        System.out.println("请输入数组大小");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int[] array = new int[number];
        System.out.println("请输入排序数组");
        for (int i = 0;i < array.length;i++){
            array[i] = scanner.nextInt();
        }
        System.out.println("==================");
        display(array);
        System.out.println();
        System.out.println("==================");
       /* int[] resultArray = bucketSort(array,9);

        System.out.println("------------------");
        display(resultArray);*/
    }
    /**
     * 桶排序
     * @param array
     * @param bucketSize
     * @return
     */
    public static ArrayList<Integer> bucketSort(ArrayList<Integer> array, int bucketSize){
        if (array == null || array.size() < 2)
            return array;
        int max = array.get(0),min =array.get(0);
        //找到最大值和最小值
        for (int i = 0; i < array.size();i++){
            if (array.get(i) > max){
                max = array.get(i);
            }
            if (array.get(i) < min){
                min = array.get(i);
            }
        }
        int bucketCount = (max - min)/bucketSize +1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0;i < bucketCount;i++){
            bucketArr.add(new ArrayList<Integer>());
        }
        for (int i = 0;i < array.size();i++){
            bucketArr.get((array.get(i) - min)/ bucketSize).add(array.get(i));
        }
        for (int i = 0;i < bucketCount;i++){
            if (bucketSize == 1){
                for (int j = 0;j < bucketArr.get(i).size();j++){
                    resultArr.add(bucketArr.get(i).get(j));
                }
            }else{
                if (bucketCount == 1) bucketSize--;
                ArrayList<Integer> temp = bucketSort(bucketArr.get(i),bucketSize);
                for (int j = 0;j < temp.size();j++){
                    resultArr.add(temp.get(j));
                }
            }
        }
        return resultArr;
    }
    /**
     * 展示
     * @param array
     */
    public static void display(int[] array){
        for (int i = 0; i < array.length;i++){
            System.out.print(array[i]+"\t");
        }
    }
}
