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

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class HeapSortBenchmark {
    @Param({"100", "1000", "10000", "100000"})
    private int dataLength;
    private Integer[] array;

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt()).limit(dataLength + 1).boxed().toArray(Integer[]::new);
        array[0] = 0; // unused element, it hasn't been sorted
    }

    @Benchmark
    public void measureSort(Blackhole bh) {
        bh.consume(HeapSort.heapSort(array));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(company.vk.polis.ads.workshop.HeapSortBenchmark.class.getSimpleName())
                .forks(1)
                .jvmArgs("-Xms1G", "-Xmx1G")
                .warmupIterations(3)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}
