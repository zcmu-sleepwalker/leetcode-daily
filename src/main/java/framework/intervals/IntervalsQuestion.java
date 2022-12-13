package framework.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 区间问题
 * <p>
 *
 * @Author: LCH
 * @Date: 2021/3/29 10:04 AM
 */
public class IntervalsQuestion {

    /**
     * 移除重叠区间
     * 如何分析区间问题？
     * 1.排序 将区间按照起始位置进行升序排序 我们可以分析出存在的三种情况
     * （1）找到了覆盖区间
     * （2）两个区间合并 成了一个大区间
     * （3）两个区间没有任何交集
     *
     * @param intvs
     * @return
     */
    public int removeCoveredIntervals(int[][] intvs) {
        // 按起点升序排序 起点相同时降序排序
        Arrays.sort(intvs, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        // 记录需要合并区间的起始位置
        int left = intvs[0][0];
        int right = intvs[0][1];

        int res = 0;
        for (int i = 0; i < intvs.length; i++) {
            int[] intv = intvs[i];

            // 情况1 是覆盖区间
            if (left <= intv[0] && right >= intv[1]) {
                // 因为是按起点升序 终点降序排列的
                // 所以不会出现起点相同 终点不同而合成一个大区间的情况（这种情况应该是是属于覆盖的，但是不按照终点降序的就会存在这种bug）
                res++;
            }

            // 情况2 两个区间合并成了一个大区间
            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }

            // 情况3 两个区间没有任何交集
            if (right < intv[0]) {
                left = intv[0];
                right = intv[1];
            }
        }
        return intvs.length - res;
    }


    /**
     * 合并区间
     *
     * @param intvs
     * @return
     */
    public static int[][] mergeIntervals(int[][] intvs) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intvs, (a, b) -> {
            return a[0] - b[0];
        });

        int left = intvs[0][0];
        int right = intvs[0][1];

        int index = 0;
        for (int i = 0; i < intvs.length; i++) {
            int[] intv = intvs[i];

            if (left <= intv[0] && right >= intv[1]) {

            }

            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }

            if (right < intv[0]) {
                int[] tmp = new int[]{left, right};
                result.add(tmp);
                index++;

                left = intv[0];
                right = intv[1];
            }

            if (i == intvs.length - 1) {
                int[] tmp = new int[]{left, right};
                result.add(tmp);
                index++;
            }
        }
        int[][] res = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            int[] tmp = result.get(i);
            res[i][0] = tmp[0];
            res[i][1] = tmp[1];
        }
        return res;
    }


    /**
     * 区间交集问题（自己写的 可以ac）
     *
     * @param intervalA
     * @param intervalB
     * @return
     */
    public static int[][] intervalsIntersection(int[][] intervalA, int[][] intervalB) {
        if (intervalA.length == 0 || intervalB.length == 0){
            return new int[][]{};
        }
        List<int[]> result = new ArrayList<>();
        // 先将两个数组进行排序
        Arrays.sort(intervalA, (a, b) -> {
            return a[0] - b[0];
        });
        Arrays.sort(intervalB, (a, b) -> {
            return a[0] - b[0];
        });

        int left = 0;
        int right = 0;
        int indexA = 0;
        int indexB = 0;
        if (intervalA[0][0] > intervalB[0][0]) {
            left = intervalB[0][0];
            right = intervalB[0][1];
            indexB = 1;
        } else {
            left = intervalA[0][0];
            right = intervalA[0][1];
            indexA = 1;
        }


        int flag = -1;
        while (indexA < intervalA.length || indexB < intervalB.length) {
            int[] current = new int[2];
            if (indexA < intervalA.length && indexB < intervalB.length ){
                if (intervalA[indexA][0] < intervalB[indexB][0]) {
                    current = intervalA[indexA];
                    flag = 0;
                } else {
                    current = intervalB[indexB];
                    flag = 1;
                }
            }else if (indexA < intervalA.length){
                current = intervalA[indexA];
                flag = 0;
            }else if (indexB < intervalB.length ){
                current = intervalB[indexB];
                flag = 1;
            }

            if (left <= current[0] && right >= current[1]) {
                result.add(current);
            }else if (right >= current[0] && right <= current[1]) {
                result.add(new int[]{current[0], right});
                right = current[1];
            }else if (right <= current[0]) {
                if (right == current[0]){
                    result.add(new int[]{current[0], right});
                }
                left = current[0];
                right = current[1];
            }

            if (flag == 0) {
                indexA++;
            } else {
                indexB++;
            }
        }

        int[][] res = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            int[] tmp = result.get(i);
            res[i][0] = tmp[0];
            res[i][1] = tmp[1];
        }
        return res;
    }

    /**
     * 区间交集问题
     * 两个区间存在交集 有以下四种情况：
     * 1. a = [1,6]  b = [2,5]
     * 2. a = [1,6]  b = [2,7]
     * 3. a = [2,5]  b = [1,6]
     * 4. a = [2,7]  b = [1,6]
     *
     * @param intervalA
     * @param intervalB
     * @return
     */
    public static int[][] intervalsIntersection_02(int[][] intervalA, int[][] intervalB) {
        int indexA = 0;
        int indexB = 0;

        List<int[]> result = new ArrayList<>();

        while (indexA < intervalA.length && indexB < intervalB.length){
            int a1 = intervalA[indexA][0];
            int a2 = intervalA[indexA][1];
            int b1 = intervalB[indexB][0];
            int b2 = intervalB[indexB][1];

            if (b1 <= a2 && b2 >= a1){
                result.add(new int[]{Integer.max(a1,b1) , Integer.min(a2,b2)});
            }
            if (b2 > a2){
                indexA++;
            }else {
                indexB++;
            }
        }

        int[][] res = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            int[] tmp = result.get(i);
            res[i][0] = tmp[0];
            res[i][1] = tmp[1];
        }
        return res;
    }

    public static void main(String[] args) {
//        int[][] aa = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] aa = new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] bb = new int[][]{{0, 5}, {8, 12}, {15, 24}, {25, 26}};
        int[][] res = intervalsIntersection_02(aa, bb);
        System.out.println();
    }
}
