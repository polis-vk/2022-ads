import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Median {
    private Median() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/11705819
    private static void solve(final Scanner in, final PrintWriter out) {
        AbstractHeap lowers = new MaxHeap();
        AbstractHeap highers = new MinHeap();
        int number;
        while (in.hasNext()) {
            number = in.nextInt();
            addNumber(number, lowers, highers);
            balance(lowers, highers);
            out.println(getMedian(lowers, highers));
        }
    }

    private static int getMedian(AbstractHeap lowers, AbstractHeap highers) {
        AbstractHeap biggerHeap = lowers.size > highers.size ? lowers : highers;
        AbstractHeap smallerHeap = lowers.size > highers.size ? highers : lowers;

        if (biggerHeap.size == smallerHeap.size) {
            return (int) (smallerHeap.peek() + (biggerHeap.peek() - smallerHeap.peek()) / 2);
        } else {
            return (int) biggerHeap.peek();
        }
    }

    private static void balance(AbstractHeap lowers, AbstractHeap highers) {
        AbstractHeap biggerHeap = lowers.size > highers.size ? lowers : highers;
        AbstractHeap smallerHeap = lowers.size > highers.size ? highers : lowers;

        if (biggerHeap.size - smallerHeap.size >= 2) {
            smallerHeap.add(biggerHeap.remove());
        }
    }

    private static void addNumber(int number, AbstractHeap lowers, AbstractHeap highers) {
        if (lowers.size == 0 || number < lowers.peek()) {
            lowers.add(number);
        } else {
            highers.add(number);
        }
    }

    static abstract class AbstractHeap {
        protected static final int ROOT_INDEX = 1;
        protected static final int DEFAULT_CAPACITY = 16;
        protected double[] values = new double[DEFAULT_CAPACITY];
        protected int size;

        public void add(double element) {
            if (size == values.length - 1) {
                values = Arrays.copyOf(values, values.length * 3 / 2);
            }
            values[++size] = element;
            swim(size);
        }

        public double peek() {
            return values[ROOT_INDEX];
        }

        public double remove() {
            double root = values[ROOT_INDEX];
            swap(ROOT_INDEX, size--);
            sink(ROOT_INDEX);
            return root;
        }

        protected void swap(int firstIndex, int secondIndex) {
            double temp = values[firstIndex];
            values[firstIndex] = values[secondIndex];
            values[secondIndex] = temp;
        }

        protected abstract void swim(int index);

        protected abstract void sink(int index);
    }

    static class MaxHeap extends AbstractHeap {
        @Override
        protected void swim(int index) {
            int currIndex = index;
            while (currIndex > 1 && values[currIndex] > values[currIndex / 2]) {
                swap(currIndex, currIndex / 2);
                currIndex /= 2;
            }
        }

        @Override
        protected void sink(int index) {
            int currIndex = index;
            int childIndex;
            while (2 * currIndex <= size) {
                childIndex = 2 * currIndex;
                if (childIndex < size && values[childIndex] < values[childIndex + 1]) {
                    childIndex++;
                }
                if (values[currIndex] >= values[childIndex]) {
                    break;
                }
                swap(childIndex, currIndex);
                currIndex = childIndex;
            }
        }
    }

    static class MinHeap extends AbstractHeap {
        @Override
        protected void swim(int index) {
            int currIndex = index;
            while (currIndex > 1 && values[currIndex] < values[currIndex / 2]) {
                swap(currIndex, currIndex / 2);
                currIndex /= 2;
            }
        }

        @Override
        protected void sink(int index) {
            int currIndex = index;
            int childIndex;
            while (2 * currIndex <= size) {
                childIndex = 2 * currIndex;
                if (childIndex < size && values[childIndex] > values[childIndex + 1]) {
                    childIndex++;
                }
                if (values[currIndex] <= values[childIndex]) {
                    break;
                }
                swap(childIndex, currIndex);
                currIndex = childIndex;
            }
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
