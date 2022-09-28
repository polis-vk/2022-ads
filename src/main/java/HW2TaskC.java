import java.util.Scanner;

public class HW2TaskC {
    static class Student implements Comparable<Student> {
        private final int id;
        private final int scores;

        public Student(int id, int scores){
            this.id = id;
            this.scores = scores;
        }

        int getId(){
            return id;
        }

        int getScores(){
            return scores;
        }

        @Override
        public int compareTo(Student o) {
            return (scores > o.getScores()) ? 1: (scores == o.getScores() && id < o.getId()) ? 1 : -1;
        }
    }

    private static void merge(Student[] data, int startIndex, int midIndex ,int endIndex){
        int sizeLeft = midIndex - startIndex;
        int sizeRight = endIndex - midIndex;
        Student[] leftData = new Student[sizeLeft];
        Student[] rightData = new Student[sizeRight];
        System.arraycopy(data, startIndex, leftData, 0, sizeLeft);
        System.arraycopy(data, midIndex, rightData, 0, sizeRight);

        int i = 0;
        int j = 0;
        int k = startIndex;
        while (i < sizeLeft && j < sizeRight) {
            if (leftData[i].compareTo(rightData[j]) > 0) {
                data[k] = leftData[i];
                i++;
            }
            else {
                data[k] = rightData[j];
                j++;
            }
            k++;
        }
        while (i < sizeLeft) {
            data[k] = leftData[i];
            i++;
            k++;
        }
        while (j < sizeRight) {
            data[k] = rightData[j];
            j++;
            k++;
        }
    }

    private static int getMidIndex(int startIndex, int endIndex){
        return startIndex + ((endIndex - startIndex) >> 1);
    }

    private static void mergeSort(Student[] data, int startIndex, int endIndex){
        if (startIndex >= endIndex - 1){
            return;
        }
        int mid = getMidIndex(startIndex, endIndex);
        mergeSort(data, startIndex, mid);
        mergeSort(data, mid, endIndex);
        merge(data, startIndex, mid, endIndex);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        Student[] data = new Student[size];
        for (int i = 0; i < size; i++){
            data[i] = new Student(in.nextInt(), in.nextInt());
        }
        mergeSort(data, 0 , size);
        for (Student student : data) {
            System.out.println(student.getId() + " " + student.getScores());
        }
    }
}
