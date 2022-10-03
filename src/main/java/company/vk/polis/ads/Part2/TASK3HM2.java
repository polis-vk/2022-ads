import java.util.Scanner;

public class TASK3HM2 {
    public static void merge(Attendee[] a, int left, int middle, int right){
        int size1 = middle - left + 1;
        int size2 = right - middle;

        Attendee[] leftSubArray = new Attendee[size1];
        Attendee[] rightSubArray = new Attendee[size2];

        for (int i = 0; i < size1; i++) {
            leftSubArray[i] = a[left + i];
        }
        for (int i = 0; i < size2; i++) {
            rightSubArray[i] = a[middle + 1 + i];
        }

        int i = 0, j = 0;
        int k = left;
        while(i < size1 && j < size2){
            if(leftSubArray[i].compareTo(rightSubArray[j]) > 0){
                a[k] = leftSubArray[i];
                i++;
            }else {
                a[k] = rightSubArray[j];
                j++;
            }
            k++;
        }
        while(i < size1){
            a[k] = leftSubArray[i];
            i++; k++;
        }
        while(j < size2){
            a[k] = rightSubArray[j];
            j++; k++;
        }
    }

    public static void sort(Attendee[] a, int left, int right){
        if(left < right){
            int middle = left + (right - left) / 2;
            sort(a, left, middle);
            sort(a, middle + 1, right);
            merge(a, left, middle, right);
        }
    }

    public static void printArrayOfAttendee(Attendee[] attendees){
        for (Attendee attendee : attendees) {
            System.out.println(attendee);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int i = 0;
        Attendee[] attendees = new Attendee[n];
        while(n-->0){
            int id = in.nextInt();
            int score = in.nextInt();
            attendees[i] = new Attendee(id, score);
            i++;
        }
        sort(attendees, 0, attendees.length - 1);
        printArrayOfAttendee(attendees);
    }
}

class Attendee implements Comparable<Attendee>{

    private final int id;
    private final int score;

    public Attendee(int id, int score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public int compareTo(Attendee attendee) {
        if(this.score == attendee.score) {
            return attendee.id - this.id;
        }else{
            return this.score - attendee.score;
        }
    }

    @Override
    public String toString() {
        return id + " " + score;
    }
}
