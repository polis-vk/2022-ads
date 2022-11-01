package company.vk.polis.ads.workshop;

import company.vk.polis.ads.workshop.sorting.HeapSort;
import company.vk.polis.ads.workshop.sorting.ImprovedInsertionSort;
import company.vk.polis.ads.workshop.sorting.InsertionSort;
import company.vk.polis.ads.workshop.sorting.MergeSort;
import company.vk.polis.ads.workshop.sorting.QuickSort;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SortBench {
    @Param({"1000000"})
    private int dataLength;
    private Integer[] array;

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt())
                .limit(dataLength)
                .boxed()
                .toArray(Integer[]::new);
    }

    @Benchmark
    public void improvedInsertionSort(Blackhole bh) {
        bh.consume(ImprovedInsertionSort.sort(array));
    }

    @Benchmark
    public void mergeSort(Blackhole bh) {
        bh.consume(MergeSort.sort(array));
    }

    @Benchmark
    public void heapSort(Blackhole bh) {
        bh.consume(HeapSort.sort(array));
    }

    @Benchmark
    public void quickSort(Blackhole bh) {
        bh.consume(QuickSort.sort(array));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortBench.class.getSimpleName())
                .forks(1)
                .jvmArgs("-Xms1G", "-Xmx1G")
                .warmupIterations(2)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}