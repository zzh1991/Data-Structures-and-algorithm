package basic;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by dell on 2016/6/30.
 */
class Edge implements Comparable<Edge>{
    public int start;
    public int end;
    public int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return start + "-" + end + " is " + weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (o.weight == weight) return 0;
        else if (weight < o.weight) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
public class Kruskal {

    public static void main(String[] args) {
        int n = 7;
        int[] nums = new int[n];
        Arrays.fill(nums, -1);
        int[] start = new int[]{1,1,2,2,3,4,4,3,4,4,5,7};
        int[] end = new int[]{2,4,4,5,1,3,6,6,7,5,7,6};
        int[] weight = new int[]{2,1,3,10,4,2,8,5,4,7,6,1};
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for (int i = 0; i < start.length; i++) {
            queue.add(new Edge(start[i], end[i], weight[i]));
        }
//        while (!queue.isEmpty()) {
//            Edge edge = queue.poll();
//            System.out.println(edge.toString());
//        }

        int edgeLen = 0;
        int sum = 0;
        while(edgeLen < n - 1) {
            if (!queue.isEmpty()) {
                Edge edge = queue.poll();
                int s = find(nums, edge.start - 1);
                int e = find(nums, edge.end - 1);
                if (s != e) {
                    edgeLen++;
                    union(nums, s, e);
                    sum += edge.weight;
                    System.out.println(edge.start + " to " + edge.end + " distance is " + edge.weight);
                }
            }
            else {
                break;
            }

        }
        if (edgeLen == n - 1) System.out.println("all distance is " + sum);
        else System.out.println("Error");
    }

    // union by size
    private static void union(int[] nums, int root1, int root2) {
        if (nums[root1] <= nums[root2]) {
            nums[root1] += nums[root2];
            nums[root2] = root1;
        }
        else {
            nums[root2] += nums[root1];
            nums[root1] = root2;
        }
    }

    // path compression
    private static int find(int[] nums, int index) {
        if (nums[index] < 0) return index;
        else {
            return nums[index] = find(nums, nums[index]);
        }
    }
}
