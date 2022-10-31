package company.vk.polis.ads.workshop;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SampleBench {
    @Param({"100", "1000", "10000", "100000", "300000"})
    private int arraySize;

    private int[] array;

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        array = new Random().ints(arraySize).toArray();
    }

    @Benchmark
    public void measureInsertionSort(Blackhole bh) {
        bh.consume(Sorts.insertionSort(array));
    }

    @Benchmark
    public void measureImprovedInsSort(Blackhole bh) {
        bh.consume(Sorts.improvedInsSort(array));
    }

    @Benchmark
    public void measureMergeSort(Blackhole bh) {
        bh.consume(Sorts.mergeSort(array));
    }

    @Benchmark
    public void measureQuickSort(Blackhole bh) {
        bh.consume(Sorts.quickSort(array));
    }

    @Benchmark
    public void measureHeapSort(Blackhole bh) {
        bh.consume(Sorts.heapSort(array));
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
