import java.util.ArrayList;
import java.util.List;

/**
 * Created by x0241589 on 5/24/2017.
 */
public class MyUtils {

    public int[] IntegerListToArray(List<Integer> list){
        ArrayList<Integer> array = new ArrayList<>();
        int[] out = new int[list.size()];

        array.addAll(list);
        int i = 0;
        for(Integer e : array){
            out[i++] = e.intValue();
        }
        return out;
    }
}
