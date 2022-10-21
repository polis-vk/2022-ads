package company.vk.polis.ads.workshop;

import company.vk.polis.ads.Sorts;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(value = Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SortsBench {

//    @Param({"100", "1000", "10000", "100000", "300000"})
    @Param({"100", "1000", "10000", "100000", "300000", "2000000"})
//    @Param("2000000")
    private int arraySize;

    @Setup(value = Level.Iteration)
    public void setupIteration() {
        array = IntStream
                .generate(() -> ThreadLocalRandom.current().nextInt())
                .limit(arraySize)
                .boxed()
                .toArray(Integer[]::new);
    }

    private Integer[] array;

//    @Benchmark
//    public void mergeSort(Blackhole bh) {
//        bh.consume(Sorts.mergeSort(array));
//    }
//
//    @Benchmark
//    public void quickSort(Blackhole bh) {
//        bh.consume(Sorts.quickSort(array));
//    }
//
//    @Benchmark
//    public void heapSort(Blackhole bh) {
//        bh.consume(Sorts.heapSort(array));
//    }
//
//    @Benchmark
//    public void insertionSort(Blackhole bh) {
//        bh.consume(Sorts.insertionSort(array));
//    }

    @Benchmark
    public void improvedInsertionSort(Blackhole bh) {
        bh.consume(Sorts.improvedInsertionSort(array));
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(SortsBench.class.getSimpleName())
                .forks(1)
                .jvmArgs("-Xms1G", "-Xmx1G")
                .warmupIterations(3)
                .measurementIterations(3)
                .build();
        new Runner(options).run();
    }
}
