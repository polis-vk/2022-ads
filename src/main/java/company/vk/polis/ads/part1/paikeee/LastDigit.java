package company.vk.polis.ads.part1.paikeee;

import java.util.Scanner;

public class LastDigit {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(num % 10);
    }

}