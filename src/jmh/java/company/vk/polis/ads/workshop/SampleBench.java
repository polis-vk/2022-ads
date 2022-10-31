package company.vk.polis.ads.workshop;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SampleBench {
    @Param({"100", "1000", "10000", "100000", "300000"})
    private int dataLength;
    private int[] array;

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        // Generate input data
//        array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt()).limit(dataLength).boxed().toArray(Integer[]::new);
        array = new Random().ints(dataLength).toArray();
    }

    @Benchmark
    public void measureInsertionSort(Blackhole bh) {
        bh.consume(Sorts.InsertionSort.sort(array));
    }

    @Benchmark
    public void measureImprovedInsertionSort(Blackhole bh) {
        bh.consume(Sorts.ImprovedInsSort.sort(array));
    }

    @Benchmark
    public void measureMergeSort(Blackhole bh) {
        bh.consume(Sorts.MergeSort.sort(array));
    }

    @Benchmark
    public void measureQuickSort(Blackhole bh) {
        bh.consume(Sorts.QuickSort.sort(array));
    }

    @Benchmark
    public void measureHeapSort(Blackhole bh) {
        bh.consume(Sorts.HeapSort.sort(array));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SampleBench.class.getSimpleName())
                .forks(1)
                .jvmArgs("-Xms1G", "-Xmx1G")
                .warmupIterations(3)
                .measurementIterations(3)
                .build();
        new Runner(opt).run();
    }
}
