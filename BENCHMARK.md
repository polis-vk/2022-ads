# Final data by each algorithm

| Sorts / Data Size       | 100     | 1000  | 10_000 | 100_000   | 1_000_000 |
|-------------------------|---------|-------|--------|-----------|-----------|
| Improved Insertion Sort | 0.001   | 0.013 | 0.217  | 3.374     | 25721.061 |
| Default Insertion Sort  | < 0.001 | 0.003 | 0.034  | 19599.363 | \> 30000  |
| Merge Sort              | 0.005   | 0.067 | 1.059  | 11.380    | 550.842   |
| Quick Sort              | 0.004   | 0.058 | 0.917  | 12.383    | 415.052   |
| Heap Sort               | 0.004   | 0.093 | 1.264  | 15.019    | 448.118   |

# Summary

| Data size      | 100                    | 1000                   | 10_000                 | 100_000                 | 1_000_000  |
|----------------|------------------------|------------------------|------------------------|-------------------------|------------|
| Best algorithm | Default Insertion Sort | Default Insertion Sort | Default Insertion Sort | Improved Insertion Sort | Quick Sort |

### I made some graphs, and now I can come to a conclusion that Merge Sort graph is the closest to n*log(n).<br><br>That's why I can proclaim it the most stable sorting algorithm, even though Quick Sort nailed everything.
### In cases, when there are less than 10_000 elements in array, it's reasonable to use Insertion Sort.
