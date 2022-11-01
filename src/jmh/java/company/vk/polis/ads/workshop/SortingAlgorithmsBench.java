package company.vk.polis.ads.workshop;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import company.vk.polis.ads.workshop.Sorts.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SortingAlgorithmsBench {
    @Param({ "100", "1000" })
    private int dataLength;
    private Integer[] array;

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt())
                .limit(dataLength).boxed().toArray(Integer[]::new);
    }

    @Benchmark
    public void measureImprovedInsertionSort(Blackhole bh) {
        bh.consume(ImprovedInsertionSort.sortReturn(array));
    }

    @Benchmark
    public void measureInsertionSort(Blackhole bh) {
        bh.consume(InsertionSort.sort(array));
    }

    @Benchmark
    public void measureMergeSort(Blackhole bh) {
        bh.consume(MergeSort.sort(array));
    }

    @Benchmark
    public void measureQuickSort(Blackhole bh) {
        bh.consume(ImprovedInsertionSort.sortReturn(array));
    }

    @Benchmark
    public void measureHeapSort(Blackhole bh) {
        bh.consume(ImprovedInsertionSort.sortReturn(array));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortingAlgorithmsBench.class.getSimpleName())
                .forks(1)
                .jvmArgs("-Xms1G", "-Xmx1G")
                .warmupIterations(3)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}
