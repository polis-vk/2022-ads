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
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SortBench {


    @Param({"1000"})
    private long dataLength;

    private Integer[] inpArray;

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        var inputData = IntStream.generate(() -> new Random().nextInt()).limit(dataLength).boxed().toList();
        inpArray = inputData.toArray(Integer[]::new);
    }

    @Benchmark
    public void improvedInsertionSort(Blackhole bh) {
        ImprovedInsertionSort.sort(inpArray);
        bh.consume(inpArray);
        bh.consume(inpArray[0]);
        bh.consume(inpArray[inpArray.length - 1]);
    }

    @Benchmark
    public void insertionSort(Blackhole bh) {
        ImprovedInsertionSort.sort(inpArray);
        bh.consume(inpArray);
        bh.consume(inpArray[0]);
        bh.consume(inpArray[inpArray.length - 1]);
    }

    @Benchmark
    public void heapSort(Blackhole bh) {
        HeapSort.sort(inpArray);
        bh.consume(inpArray);
        bh.consume(inpArray[0]);
        bh.consume(inpArray[inpArray.length - 1]);
    }
    @Benchmark
    public void mergeSort(Blackhole bh) {
        var res = MergeSort.sort(inpArray);
        bh.consume(res);
        bh.consume(res[0]);
        bh.consume(res[res.length - 1]);
    }

    @Benchmark
    public void quickSort(Blackhole bh) {
        QuickSort.sort(inpArray);
        bh.consume(inpArray);
        bh.consume(inpArray[0]);
        bh.consume(inpArray[inpArray.length - 1]);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortBench.class.getSimpleName())
                .forks(1)
                .jvmArgs("-Xms1G", "-Xmx1G")
                .warmupIterations(3)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}
