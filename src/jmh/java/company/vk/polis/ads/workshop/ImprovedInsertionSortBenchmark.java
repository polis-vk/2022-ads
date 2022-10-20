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

import company.vk.polis.ads.workshop.sorts.ImprovedInsertionSort;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class ImprovedInsertionSortBenchmark {
    @Param({"100", "1000", "10000", "100000", "1000000"})
    private int dataLength;

    private Integer[] data;

    @Setup(value = Level.Iteration)
    public void setUpInvocation() {
        data = IntStream.generate(() -> ThreadLocalRandom.current().nextInt())
                .limit(dataLength)
                .boxed()
                .toArray(Integer[]::new);
    }

    @Benchmark
    public void improvedInsertionSort(Blackhole bh) {
        bh.consume(ImprovedInsertionSort.sort(data));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ImprovedInsertionSortBenchmark.class.getSimpleName())
                .forks(1)
                .jvmArgs("-Xms1G", "-Xmx1G")
                .warmupIterations(2)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}
