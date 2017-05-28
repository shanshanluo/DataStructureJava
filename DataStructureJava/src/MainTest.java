import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by x0241589 on 5/19/2017.
 */
public class MainTest {

    /**** Random number generator tests */
    static private void randomNumberGenerator(){
        MyRandomGenerator rd = new MyRandomGenerator(12, 5, 100, 92);
        ExerciseArrays eArrays = new ExerciseArrays(rd);

        int[] numbers = eArrays.getNextNRandomNumbers(0);
        if(numbers != null) {
            System.out.println("Next " + numbers.length + " generated random numbers are :");
            for (int i : numbers)
                System.out.print(i + ",");
        }
    }

    /* remove elements from a array randomly until array has no more element */
    private void removeArrayElementRandom(){
        ExerciseArrays deleteArrayElements = new ExerciseArrays();
        Random rd = deleteArrayElements.getRandomGenerator();
        int[] array = new int[32];
        for(int i=0; i<array.length; i++)
            array[i] = rd.nextInt();

        int size = array.length;
        while(size != 0){
            for(int i : array)
                System.out.print(i + ", ");
            System.out.println("\n");
            int[] newArray = deleteArrayElements.deleteEntry(array, deleteArrayElements.selectRandomIndex(size));
            size--;
            array = newArray;
        }
    }

    /* Shuffle given array in a recursive way - split array into halves recursively*/
    static private List<Integer> shuffleArray(List<Integer> arrayIn, int size){
        if(size == 0) return null;
        else if(size == 1) return arrayIn;
        else {
            int halfSize = size>>1;
            List<Integer> leftHalf = shuffleArray(arrayIn.subList(0, halfSize), halfSize);
            List<Integer> rightHalf = shuffleArray(arrayIn.subList((size+1)>>1, size), halfSize);
            List<Integer> shuffledArray = new ArrayList();
            shuffledArray.addAll(rightHalf);
            if(size%2 == 1) shuffledArray.add(arrayIn.get(halfSize));
            shuffledArray.addAll(leftHalf);
            return shuffledArray;
        }
    }

    /* Generate an array with random elements in a range with some duplicates */
    static private List<Integer> generateArrayInRange(int size, int range){
        if(range > size) {
            System.out.println("Not supported !\n");
            return null;
        }
        ArrayList<Integer> origin = new ArrayList();
        int i = 0;
        for(i=0; i<range; i++) {
            origin.add(i+1);
        }
        if(size > range){
            Random rd = new Random();
            for(; i<size; i++){
                origin.add(rd.nextInt(range));
            }
        }

        for(i = 0; i<size; i++)
            System.out.print(origin.get(i)+", ");
        System.out.println(" ");

        List<Integer> randomArrayInRange = shuffleArray(origin, origin.size());
        if(randomArrayInRange != null)
            return randomArrayInRange;
        else
            return null;
    }

    /* find duplicate elements in an randomly generated array */
    static private void findDuplicateElements() {
        ExerciseArrays findDuplicate = new ExerciseArrays();
        MyUtils util= new MyUtils();

        List<Integer> arrayIn = generateArrayInRange(25, 20);
        for(int i: arrayIn)
            System.out.print(i + ", ");
        System.out.println('\n');

        ArrayList<Integer> duplicate = findDuplicate.findDuplicatesInArray(util.IntegerListToArray(arrayIn), arrayIn.size());
        if(duplicate.isEmpty() != true) {
            System.out.println("Duplicates are found : ");
            int i = 0;
            while (i<duplicate.size()) {
                System.out.print(duplicate.get(i) + ", ");
                i++;
            }
        }
    }

	private static void testMeetGame() {
        int players = 10;

        ExerciseArrays arrayForGame = new ExerciseArrays(players);
        ExerciseArrays.MeetGame game = arrayForGame.getGameObject();
        Random rd = new Random();
        ArrayList<Integer> winners;

        while(true) {
            int player1 = rd.nextInt(players);
            int player2;
            while ((player2 = rd.nextInt(players)) == player1) ;

            Boolean status = game.meetNewPlayer(player1, player2);
            if (status) {
                System.out.println("Player1: " + player1 + ", player2: " + player2);
                winners = game.checkWinner(player1, player2);
                if (!winners.isEmpty()) {
                    System.out.println("Game over !");
                    System.out.println("Winner(s) : ");
                    for (Integer w : winners)
                        System.out.println("Player_" + w);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {


//      randomNumberGenerator();

//      removeArrayElementRandom();

//        generateArrayInRange(25, 20);
//        findDuplicateElements();

		testMeetGame();
    }
}
