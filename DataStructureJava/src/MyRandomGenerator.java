import java.util.Random;

/**
 * Created by x0241589 on 5/19/2017.
 */
public class MyRandomGenerator extends Random {
    private int a;
    private int b;
    private int n;
    private int seed;
    private int cur;
    private int index;

    public MyRandomGenerator(int ai, int bi, int ni, int seedi) {
        a = ai;
        b = bi;
        n = ni;
        seed = seedi;
        cur = seed;
        index = 0;
    }

    public void setSeed(int seedi){
        seed = seedi;
        index = 0;
    }

    public int nextInt() {
        cur = (a * cur + b) % n;
        index++;
        return cur;
    }
}
