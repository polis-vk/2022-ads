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

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import company.vk.polis.ads.workshop.sorts.HeapSort;
import company.vk.polis.ads.workshop.sorts.ImproveInsSort;
import company.vk.polis.ads.workshop.sorts.InsertionSort;
import company.vk.polis.ads.workshop.sorts.MergeSort;
import company.vk.polis.ads.workshop.sorts.QuickSort;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SampleBench {
    @Param({"100", "1000", "10000", "100000"})
    private int dataLength;

    private Integer[] array;

    @Setup(value = Level.Iteration)
    public void setUpIteration() {
        array = IntStream.generate(() ->
                ThreadLocalRandom.current().nextInt()).limit(dataLength).boxed().toArray(Integer[]::new);
    }

    @Benchmark
    public void heapSort(Blackhole bh) {
        bh.consume(HeapSort.heapSort(array));
    }

    @Benchmark
    public void improvedInsertionSort(Blackhole bh) {
        bh.consume(ImproveInsSort.improveInsSort(array));
    }

    @Benchmark
    public void insertionSort(Blackhole bh) {
        bh.consume(InsertionSort.insertionSort(array));
    }

    @Benchmark
    public void mergeSort(Blackhole bh) {
        bh.consume(MergeSort.mergeSort(array));
    }

    @Benchmark
    public void quickSort(Blackhole bh) {
        bh.consume(QuickSort.quickSort(array));
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
