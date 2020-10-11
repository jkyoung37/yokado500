package com.jp.yokado.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 조합 : n 개 중에서 r 개 선택
 */
public class Combination {

    static List<int[]> out = new ArrayList<>();

    // 백트래킹 사용
    // 사용 예시 : combination(arr, visited, 0, n, r)
    public static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        List<Integer> pointLists = new ArrayList<>();

        if (r == 0) {
            pointLists = smallSum(arr, visited, n);
            out.add(Utils.convertIntegers2(pointLists));
        }


        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    // 배열 출력
    static List<Integer> smallSum(int[] arr, boolean[] visited, int n) {
        List<Integer> out = new ArrayList<>();
       
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                out.add(arr[i]);
            }
        }
        return out;
    }

    public static List<int[]> getItem(){
        return out;
    }

    public static void clear(){
        out = new ArrayList<>();
    }
}
