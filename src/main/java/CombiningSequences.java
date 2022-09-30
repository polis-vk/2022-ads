public class CombiningSequences {
    public long getNumberFromSequence(int index) {
        long ai = 1;
        int curIndexA = 1;
        long bi = 1;
        int curIndexB = 1;
        long current = 1;
        for (int i = 1; i <= index; i++) {
            if (ai < bi) {
                current = ai;
                curIndexA++;
                ai = (long) curIndexA * curIndexA;
            } else if (ai > bi) {
                current = bi;
                curIndexB++;
                bi = (long) Math.pow(curIndexB, 3);
            } else {
                current = ai;
                curIndexA++;
                ai = (long) curIndexA * curIndexA;
                curIndexB++;
                bi = (long) Math.pow(curIndexB, 3);
            }
        }
        return current;
    }
}
