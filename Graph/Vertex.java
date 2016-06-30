package basic;

import java.util.ArrayList;

/**
 * Created by dell on 2016/6/30.
 */
public class Vertex {
    public boolean known;
    public int dist;
    public int path;
    int val;
    public ArrayList<Integer> adj;
    public ArrayList<Integer> weight;

    Vertex(int val) {
        this.val = val;
        adj = new ArrayList<>();
        weight =new ArrayList<>();
    }
}
