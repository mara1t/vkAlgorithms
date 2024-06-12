import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.max;

public class ZeroOneArr {
    public static void main(String[] args) {

        // find sequence without triple one's
        for (int i = 1; i <= 10; i++) {
            System.out.println("Count sequence for n " + i + " = " + count_sequences(i));
        }
        /* res =
            Count sequence for n 1 = 1
            Count sequence for n 2 = 2
            Count sequence for n 3 = 4
            Count sequence for n 4 = 7
            Count sequence for n 5 = 13
            Count sequence for n 6 = 24
            Count sequence for n 7 = 44
            Count sequence for n 8 = 81
            Count sequence for n 9 = 149
            Count sequence for n 10 = 274
         */
        System.out.println();

        // Find max sequence
        System.out.println("max sequence for 1,2,3,4,5,1,2,3 = " +  findLIS(new ArrayList<>(Arrays.asList(1,2,3,4,5,1,2,3))));
        /* res =
            max sequence for 1,2,3,4,5,1,2,3 = 5
         */
        System.out.println();

        // Pascal Triangle
        int n = 10;
        List<List<Integer>> tr = PascalTriangle(n);
        for (int i = 0; i < n; i++) {
            System.out.println(tr.get(i));
        }
        /* res =
            [1]
            [1, 1]
            [1, 2, 1]
            [1, 3, 3, 1]
            [1, 4, 6, 4, 1]
            [1, 5, 10, 10, 5, 1]
            [1, 6, 15, 20, 15, 6, 1]
            [1, 7, 21, 35, 35, 21, 7, 1]
            [1, 8, 28, 56, 70, 56, 28, 8, 1]
            [1, 9, 36, 84, 126, 126, 84, 36, 9, 1]
         */

        // Tree max sequence
        Node root = new Node(8);
        root.left = new Node(9);
        root.right = new Node(10);
        root.left.left = new Node(100);
        root.left.right = new Node(200);

        System.out.println(find_max_path_sum(root));
        /* res =
            [100, 9, 200]
         */

        // Max profit
        int[] arr = new int[]{8, 9, 3 ,7 , 4 , 16 ,12};

        System.out.println(maxProfit(arr));
        /* res =
            13
         */

    }

    public static int count_sequences(int n) {
        if (n == 0 || n == 1 || n == 2) {
            return n;
        }
        List<Integer> dp = new ArrayList<>(Arrays.asList(1, 2, 4));
        for (int i = 3; i <= n; i++) {
            dp.add(dp.get(i - 1) + dp.get(i - 2) + dp.get(i - 3));
        }
        return dp.get(n - 1);
    }
    public static int findLIS(List<Integer> nums) {
        if (nums.isEmpty()) {
            return 0;
        }
        if (nums.size() == 1) {
            return 1;
        }
        List<Integer> dp = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            dp.add(1);
        }
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i-1) < nums.get(i)) {
                dp.set(i ,nums.get(i-1) + 1);
            }
        }
        return max(dp);
    }
    public static List<List<Integer>> PascalTriangle(int n) {
        // создаем двумерный массив
        // для хранения треугольника Паскаля
        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                tmp.add(1);
            }
            dp.add(tmp);
        }
        // заполняем массив значениями
        for (int row = 1; row < n; row++) {
            for (int col = 1; col < row; col++) {
                dp.get(row).set(col, dp.get(row - 1).get(col - 1) + dp.get(row - 1).get(col));
            }
        }
        return dp;
    }

    public static List<Integer> find_max_path_sum(Node root) {

        // int max_sum = Integer.MIN_VALUE;
        int max_sum = 0;
        List<Integer> max_path = new ArrayList<>();
        Max_cl max = new Max_cl(max_sum, max_path);
        dfs(root, max);
        return max.max_path;
    }
    public static DFC dfs(Node node, Max_cl max_cl) {
        if (node == null) {
            return new DFC(0, new ArrayList<>());
        }
        DFC left = dfs(node.left, max_cl);
        DFC right = dfs(node.right, max_cl);
        DFC current = new DFC(0, new ArrayList<>());
        if (left.val > right.val) {
            current.list = left.list;
            current.list.add(node.val);
        } else {
            current.list = right.list;
            current.list.add(node.val);
        }
        current.val = Math.max(left.val, right.val) + node.val;
        // обновляем максимальный путь, если сумма
        // его текущих узлов больше
        if ((left.val + node.val + right.val) > max_cl.max) {
            max_cl.max = left.val + node.val + right.val;
            max_cl.max_path = left.list;
            max_cl.max_path.add(node.val);
            max_cl.max_path.addAll(right.list);
        }
        return current;
    }

    public static class Node {
        public Node left;
        public Node right;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }
    public static class Max_cl {
        int max;
        List<Integer> max_path;

        public Max_cl(int max, List<Integer> max_path) {
            this.max = max;
            this.max_path = max_path;
        }
    }
    public static class DFC {
        int val;
        List<Integer> list;

        public DFC(int val, List<Integer> list) {
            this.val = val;
            this.list = list;
        }
    }

    public static int maxProfit(int[] prices) {
        int profit = 0;
        int min_price = prices[0];
        for (int currentPriceIndex = 1; currentPriceIndex < prices.length; currentPriceIndex++) {
            profit = Math.max(profit, prices[currentPriceIndex] - min_price);
            min_price = Math.min(prices[currentPriceIndex], min_price);
        }
        return profit;
    }

}