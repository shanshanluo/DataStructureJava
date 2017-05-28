
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * Created by x0241589 on 5/19/2017.
 */
public class ExerciseArrays {
    private Random randomGenerator = null;
	private MeetGame game;

    public ExerciseArrays(){
        randomGenerator = new Random();
    }
	
	public ExerciseArrays(int n){
        game = new MeetGame(n);
    }

    public ExerciseArrays(Random rd){
        randomGenerator = rd;
    }

    public Random getRandomGenerator(){
        return randomGenerator;
    }

    public int[] getNextNRandomNumbers(int n) {
        if((randomGenerator == null)||(n==0)) {
            System.out.println("Random number generator created ? How many numbers you need ? ");
            return null;
        }
        int[] out = new int[n];
        for(int i: out){
            out[i] = randomGenerator.nextInt();
        }
        return out;
    }

    /* randomly select index in an Array */
    public int selectRandomIndex(int size){
        return randomGenerator.nextInt(size);
    }

    /* delete selected entry in Array */
    int[] deleteEntry(int[] in, int idx){
        int size = in.length;
        if(idx == 0) return(Arrays.copyOfRange(in, 1, size));
        else if(idx == (size-1)) return (Arrays.copyOfRange(in, 0, idx));
        else{
            int[] out = new int[in.length - 1];
            for(int i=0; i<idx; i++)
                out[i] = in[i];
            for(int i=idx+1; i<in.length; i++){
                out[i-1] = in[i];
            }
            return out;
        }
    }

    /* find the duplicate element in an Array with values in[1, n-1] */
    int findDuplicateElement(int[] ai, int size) {
        Arrays.sort(ai);
        for(int i=0; i<size; i++){
            if((ai[i+1] - ai[i] == 0)){
                return ai[i];
            }
        }
        System.out.println("No duplicate elements in given array.");
        return 0;
    }

    ArrayList<Integer> findDuplicatesInArray(int[] inArray, int size){
        int[] hash = new int[size];
        ArrayList<Integer> out = new ArrayList<Integer>();

        for(int i=0; i<size; i++){
            if(hash[inArray[i]] != 0)
                hash[inArray[i]] = -1; //mark this entry having duplicate
            else
                hash[inArray[i]] = inArray[i];
        }

        for(int i=0; i<hash.length; i++) {
            if(hash[i] == -1) {
                out.add(i);
            }
        }
        return out;
    }
	
	    MeetGame getGameObject(){
        return game;
    }

    public class MeetGame {
        private int players = 0;
        ArrayList<Boolean[]> recorders = new ArrayList<Boolean[]>();

        public MeetGame(int n) {
            players = n;
            for (int row = 0; row < n; row++) {
                Boolean[] ar = new Boolean[n];
                Arrays.fill(ar, false);
                recorders.add(ar);
            }
        }

        public Boolean meetNewPlayer(int player1, int player2) {
            if ((player1 >= players) || (player2 >= players)) {
                throw new ArrayIndexOutOfBoundsException("--------- player ID is incorrect !\n");
            } else if ((player1 < 0) || (player2 < 0)) {
                throw new ArrayIndexOutOfBoundsException("--------- player ID is incorrect !\n");
            }

            if (recorders.get(player1)[player2]) {
                return false;
            }
            recorders.get(player1)[player2] = true;
            recorders.get(player2)[player1] = true;
            return true;
        }

        public ArrayList<Integer> checkWinner(int player1, int player2) {
            ArrayList<Integer> winners = new ArrayList<>();
            Boolean[] candidate = recorders.get(player1);
            int i = 0;
            for (; i < candidate.length; i++) {
                if ((!candidate[i]) && (i != player1))
                    break;
            }
            if (i == candidate.length) {
                winners.add(player1);
            }
            candidate = recorders.get(player2);
            for (i = 0; i < candidate.length; i++) {
                if ((!candidate[i]) && (i != player2))
                    break;
            }
            if (i == candidate.length) {
                winners.add(player2);
            }
            return winners;
        }
    }
}
