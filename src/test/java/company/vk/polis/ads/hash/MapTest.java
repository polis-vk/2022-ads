package company.vk.polis.ads.hash;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapTest {
    @Test
    void testSeparateChainingBase() {
        baseTest(HashMapMapImpl::new, SeparateChainingMap::new);
    }

    @Test
    void testDoubleHashingBase() {
        baseTest(HashMapMapImpl::new, DoubleHashingMap::new);
    }

    @ParameterizedTest
    @MethodSource("loadFactors")
    void testSeparateChainingLoadFactorAndCapacity(double loadFactor) {
        testLoadFactorAndCapacity(SeparateChainingMap::new, loadFactor, MapTest::separateChainingMapCapacity);
    }

    @ParameterizedTest
    @MethodSource("loadFactors")
    void testDoubleHashingLoadFactorAndCapacity(double loadFactor) {
        testLoadFactorAndCapacity(DoubleHashingMap::new, loadFactor, MapTest::doubleHashingMapCapacity);
    }

    @Test
    void testSeparateChainingCollisions() {
        testCollisions(SeparateChainingMap::new);
    }

    @Test
    void testDoubleHashingCollisions() {
        testCollisions(DoubleHashingMap::new);
    }

    @Disabled
    @Test
    void testHashMapBase() {
        baseTest(HashMapMapImpl::new, (a, b) -> new HashMapMapImpl<>(LinkedHashMap::new, a, b));
        baseTest(HashMapMapImpl::new, (a, b) -> new HashMapMapImpl<>(ConcurrentHashMap::new, a, b));
    }

    @Disabled
    @Test
    void testHashMapLoadFactorAndCapacity() {
        var map = new HashMapMapImpl<>(16, 0.75f);
        map.put(new Object(), new Object());
        assertEquals(16, MapTest.hashMapCapacity(map));
    }

    @Disabled
    @Test
    void testHashMapCollisions() {
        testCollisions(HashMapMapImpl::new);
    }

    private static DoubleStream loadFactors() {
        return DoubleStream.of(0.6f, 0.7f, 0.75f, 0.8f);
    }

    private static void baseTest(BiFunction<Integer, Float, Map<UUID, UUID>> expectedProducer,
                                 BiFunction<Integer, Float, Map<UUID, UUID>> actualProducer) {
        int expectedMaxSize = 100_000;
        float loadFactor = 0.8f;
        var expectedMapCapacity = (int) (expectedMaxSize * loadFactor);
        var expected = expectedProducer.apply(expectedMapCapacity * 2, loadFactor);
        var actual = actualProducer.apply(expectedMaxSize * 2, loadFactor);
        record Kv(UUID k, UUID v) implements Comparable<Kv> {
            @Override
            public int compareTo(@NotNull Kv o) {
                return k.compareTo(o.k);
            }
        }
        var duplicatesByKey = new ArrayList<Kv>();
        IntStream.range(0, expectedMaxSize).forEach(__ -> {
            var key = randomUUID();
            var value = randomUUID();
            assertEquals(expected.put(key, value), actual.put(key, value), "Bug in put");
            assertEquals(expected.size(), actual.size(), "Bug in put");
            if (randomBoolean()) {
                duplicatesByKey.add(new Kv(key, value));
            }
        });
        duplicatesByKey.forEach(kv -> {
            if (randomBoolean()) {
                assertEquals(expected.put(kv.k, kv.v), actual.put(kv.k, kv.v), "Bug in put");
                assertEquals(expected.size(), actual.size(), "Bug in put");
            } else {
                var newValue = randomUUID();
                assertEquals(expected.put(kv.k, newValue), actual.put(kv.k, newValue), "Bug in put");
                assertEquals(expected.size(), actual.size(), "Bug in put");
            }
        });
        duplicatesByKey.forEach(kv -> {
            assertEquals(expected.remove(kv.k), actual.remove(kv.k), "Bug in remove");
            assertEquals(expected.size(), actual.size(), "Bug in remove");
            var key = randomUUID();
            assertEquals(expected.remove(key), actual.remove(key), "Bug in remove");
            assertEquals(expected.size(), actual.size(), "Bug in remove");
        });
        var expectedSet = new HashSet<>();
        expected.forEach((k, v) -> expectedSet.add(new Kv(k, v)));
        var actualSet = new HashSet<>();
        actual.forEach((k, v) -> actualSet.add(new Kv(k, v)));
        assertTrue(expectedSet.containsAll(actualSet), "Bug somewhere in put/delete/forEach");
        assertEquals(expectedSet.size(), actualSet.size(), "Bug somewhere in put/delete/forEach");
    }

    private static void testLoadFactorAndCapacity(BiFunction<Integer, Float, Map<UUID, UUID>> mapProducer,
                                                  double loadFactor,
                                                  Function<Map<UUID, UUID>, Integer> capacityExtractor) {
        var capacity = 100;
        var expectedMaxSize = (int) (capacity * loadFactor);
        var map = mapProducer.apply(expectedMaxSize, (float) loadFactor);
        var expectedCapacity = capacityExtractor.apply(map);
        // Add elements
        IntStream.range(0, expectedMaxSize).forEach(__ -> map.put(randomUUID(), randomUUID()));
        var actualCapacity = capacityExtractor.apply(map);
        // Expecting that capacity has not been changed
        assertEquals(expectedCapacity, actualCapacity, "Capacity has been changed despite the loadFactor and expectedMaxSize");
        // Add one more elem
        map.put(randomUUID(), randomUUID());
        actualCapacity = capacityExtractor.apply(map);
        // Expecting that array has been grown up
        assertNotEquals(expectedCapacity, actualCapacity, "Capacity has not been changed despite the loadFactor and expectedMaxSize");
    }

    private static void testCollisions(BiFunction<Integer, Float, Map<Object, UUID>> mapProducer) {
        var elementCount = 30_000;
        var map = mapProducer.apply(elementCount, 0.75f);
        IntStream.range(0, elementCount / 4).forEach(__ -> map.put(new Object(), randomUUID()));
        var duplicatesByHash = new ArrayList<ConstHashObject>(elementCount / 4 * 3);
        IntStream.range(0, elementCount / 4 * 3).forEach(__ -> {
            final var random = ConstHashObject.random();
            duplicatesByHash.add(random);
            map.put(random, randomUUID());
        });
        duplicatesByHash.forEach(dup -> {
            assertTrue(map.containsKey(dup), "Incorrect collision resolution implementation");
            if (randomBoolean()) {
                assertNotNull(map.remove(dup), "Incorrect collision resolution implementation");
            }
        });
    }

    record ConstHashObject(String s, Long l, int i) {
        static ConstHashObject random() {
            return new ConstHashObject(
                UUID.randomUUID().toString(),
                ThreadLocalRandom.current().nextLong(),
                ThreadLocalRandom.current().nextInt()
            );
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    private static <K, V> int hashMapCapacity(Map<K, V> map) {
        return arrayFieldLength(declaredField(map, "hashMap"), "table");
    }

    private static <K, V> int separateChainingMapCapacity(Map<K, V> map) {
        return arrayFieldLength(map, "array");
    }

    private static <K, V> int doubleHashingMapCapacity(Map<K, V> map) {
        return arrayFieldLength(map, "keys");
    }

    @SuppressWarnings("unchecked")
    private static <T> T declaredField(Object o, String name) {
        final Field field;
        try {
            field = o.getClass().getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Field renaming is not permitted");
        }
        field.setAccessible(true);
        final T value;
        try {
            value = (T) field.get(o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    private static int arrayFieldLength(Object o, String name) {
        return ((Object[]) declaredField(o, name)).length;
    }

    private static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    private static UUID randomUUID() {
        return UUID.randomUUID();
    }
}
