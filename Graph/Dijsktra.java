package basic;

import java.util.ArrayList;

/**
 * Created by dell on 2016/6/30.
 */
public class Dijsktra {
    public static void main(String[] args) {
        ArrayList<Vertex> vertices = new ArrayList<>();
        int n = 7;
        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex(i + 1));
        }
        int[] start = new int[]{1,1,2,2,3,4,4,3,4,4,5,7};
        int[] end = new int[]{2,4,4,5,1,3,6,6,7,5,7,6};
//        int[] weight = new int[]{2,1,3,10,4,2,8,5,4,2,6,1};
        int[] weight = new int[]{2,1,3,10,4,2,8,5,4,7,6,1};
        for (int i = 0; i < start.length; i++) {
            vertices.get(start[i] - 1).adj.add(end[i] - 1);
            vertices.get(start[i] - 1).weight.add(weight[i]);
            vertices.get(end[i] - 1).adj.add(start[i] - 1);
            vertices.get(end[i] - 1).weight.add(weight[i]);
        }
//        dijkstraSP(0, vertices);
//        for (Vertex v: vertices) {
//            System.out.println(v.dist);
//            printPath(v.val - 1, vertices);
//            System.out.println();
//        }

        prim(0, vertices);
        int sum = 0;
        for (Vertex v: vertices) {
            if (v.path > -1) {
                System.out.println(v.path + " to " + v.val + " distance is " + v.dist );
            }
            sum += v.dist;
        }
        System.out.println("all distance is " + sum);
    }

    private static void printPath(int index, ArrayList<Vertex> vertices) {
        if (index >= 0 && vertices.get(index).path > -1) {
            printPath(vertices.get(index).path, vertices);
            System.out.print(" to ");
        }
        System.out.print(vertices.get(index).val);
    }

    private static void dijkstraSP(int start, ArrayList<Vertex> vertices) {
        int smallestDist;
        int smallestVertex;
        Vertex v, s;
        int n = vertices.size();

        for (int i =0; i< n; i++) {
            vertices.get(i).dist = Integer.MAX_VALUE;
            vertices.get(i).known= false;
            vertices.get(i).path = -1;
        }
        vertices.get(start).dist = 0;
        for (;;) {
            smallestDist = Integer.MAX_VALUE;
            smallestVertex = -1;
            for (int i = 0; i< n; i++) {
                if (!vertices.get(i).known && vertices.get(i).dist < smallestDist) {
                    smallestDist = vertices.get(i).dist;
                    smallestVertex = i;
                }
            }
            if (smallestVertex == -1) break;
            vertices.get(smallestVertex).known = true;
            v = vertices.get(smallestVertex);

            for (int j = 0; j < v.adj.size(); j++) {
                if (!vertices.get(v.adj.get(j)).known) {
                    if (v.dist + v.weight.get(j) < vertices.get(v.adj.get(j)).dist) {
                        vertices.get(v.adj.get(j)).dist = v.dist + v.weight.get(j);
                        vertices.get(v.adj.get(j)).path = smallestVertex;
                    }
                }
            }
        }
    }

    private static void prim(int start, ArrayList<Vertex> vertices) {
        int smallestDist;
        int smallestVertex;
        Vertex v, s;
        int n = vertices.size();

        for (int i =0; i< n; i++) {
            vertices.get(i).dist = Integer.MAX_VALUE;
            vertices.get(i).known= false;
            vertices.get(i).path = -1;
        }
        vertices.get(start).dist = 0;
        for (;;) {
            smallestDist = Integer.MAX_VALUE;
            smallestVertex = -1;
            for (int i = 0; i< n; i++) {
                if (!vertices.get(i).known && vertices.get(i).dist < smallestDist) {
                    smallestDist = vertices.get(i).dist;
                    smallestVertex = i;
                }
            }
            if (smallestVertex == -1) break;
            vertices.get(smallestVertex).known = true;
            v = vertices.get(smallestVertex);

            for (int j = 0; j < v.adj.size(); j++) {
                if (!vertices.get(v.adj.get(j)).known) {
                    if (v.weight.get(j) < vertices.get(v.adj.get(j)).dist) {
                        vertices.get(v.adj.get(j)).dist = v.weight.get(j);
                        vertices.get(v.adj.get(j)).path = smallestVertex + 1;
                    }
                }
            }
        }
    }
}
