public class Spread {
    int count;
    int range;
    int[] array;

    Spread(int range, int[] array){
        this.range = range;
        this.array = array;
        this.count = array.length;
    }

    private int findMin(){
        int min = array[0];
        for (int i = 1; i < count; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public int[] sort(){
        if(count == 0){
            return null;
        }
        int min = findMin();
        int[] numbers = new int[range];
        int[] countNumbers = new int[range];
        for (int i = 0; i < count; i++) {
            countNumbers[array[i] - min]++;
        }
        int[] result = new int[count];
        int k = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < countNumbers[i]; j++) {
                result[k++] = min + i;
            }
        }
        return result;
    }
}
