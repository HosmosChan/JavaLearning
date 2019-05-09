package com.hosmos.learning.common.utils;

import java.util.Map;
import java.util.TreeMap;

/**
 * Code is far away from bug with the animal protected
 * 　┏┓　　  ┏┓
 * ┏┻┻━━━┻┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┣┛
 * 　　┗┳┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author chenhuayang
 * @description
 * @date 2019年05月05日
 */
public class Learning {
    private static int midSearching(int[] a, int[] b, int n) {
        int beginInAList = 0, endInAList = n - 1, midInAList, beginInBList = 0, endInBList = n - 1, midInBList;
        while (beginInAList != endInAList || beginInBList != endInBList) {
            midInAList = (beginInAList + endInAList) / 2;
            midInBList = (beginInBList + endInBList) / 2;
            if (a[midInAList] == b[midInBList]) {
                return a[midInAList];
            }
            if (a[midInAList] < b[midInBList]) {
                if ((beginInAList + endInAList) % 2 == 0) {
                    beginInAList = midInAList;
                    endInBList = midInBList;
                } else {
                    beginInAList = midInAList + 1;
                    endInBList = midInBList;
                }
            } else {
                if ((beginInAList + endInAList) % 2 == 0) {
                    endInAList = midInAList;
                    beginInBList = midInBList;
                } else {
                    endInAList = midInAList;
                    beginInBList = midInBList + 1;
                }
            }
        }
        return a[beginInAList] < b[beginInBList] ? a[beginInAList] : b[beginInBList];
    }

    private static int majority(int[] a) {
        int majority = a[0], count = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == majority) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majority = a[i];
                count = 1;
            }
        }
        if (count > 0) {
            count = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] == majority) {
                    count++;
                }
            }
        }
        if (count > a.length / 2) {
            return count;
        } else {
            return -1;
        }
    }

    private static int findMissMin(int[] a) {
        Map<Integer, Integer> b = new TreeMap<>();
        int missMin = a.length;
        for (int i = 0; i < a.length; i++) {
            b.put(i, 0);
        }
        for (int i1 : a) {
            if (0 < i1 && i1 < a.length) {
                b.put(i1, b.get(i1) + 1);
            }
        }
        for (int i = 1; i < a.length; i++) {
            System.err.println(b.get(i));
            if (b.get(i) == 0 && i < missMin) {
                missMin = i;
            }
        }
        return missMin;
    }







    public static void main(String[] args) {
        //project P19-11
        /*int[] a = {11, 13, 15, 17, 19};
        int[] b = {2, 4, 6, 8, 20};
        System.err.println(midSearching(a, b, a.length));*/
        //project P19-12
        /*int[] a = {0, 5, 5, 3, 5, 7, 5, 5};
        System.err.println(majority(a));*/
        //project P19-13
        /*int[] a = {-4, 3, 2, 5, 1, 8, 6, 7, 4, 3, 9, 12, 11};
        System.out.println(findMissMin(a));*/
    }
}
