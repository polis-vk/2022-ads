import java.util.Scanner;

public class TASK2HM2 {
    static long Aseq(int i){
        return (long) i * i;
    }
    static long Bseq(int i){
        return (long) i * i * i;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int A = 1;
        int B = 1;
        long C = 0;
        while(x-->0){
            long elementA = Aseq(A);
            long elementB = Bseq(B);
            if(elementA > elementB){
                C = elementB;
                B++;
            }else if(elementA < elementB){
                C = elementA;
                A++;
            }else{
                C = elementA;
                A++; B++;
            }
        }
        System.out.println(C);
    }
}
