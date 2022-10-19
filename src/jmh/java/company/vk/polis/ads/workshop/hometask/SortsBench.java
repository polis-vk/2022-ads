package company.vk.polis.ads.workshop.hometask;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

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

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SortsBench {

    @Param({"100", "1_000", "10_000", "100_000", "1_000_000"})
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
    public void measureInsertionSort(Blackhole bh) {
        // TODO.
    }

    @Benchmark
    public void measureImprovedInsertionSort(Blackhole bh) {
        // TODO.
    }

    @Benchmark
    public void measureMergeSort(Blackhole bh) {
        // TODO.
    }

    @Benchmark
    public void measureQuickSort(Blackhole bh) {
        // TODO.
    }

    @Benchmark
    public void measureHeapSort(Blackhole bh) {
        // TODO.
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortsBench.class.getSimpleName())
                .forks(1)
                .jvmArgs("-Xms1G", "-Xmx1G")
                .warmupIterations(3)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}
