This is one of the most common points of confusion. Let's visualize it.

Suppose

```text
nums1 = [1, 3, 3]
m = 3

nums2 = [2, 5, 6]
n = 3
```

Initially:

```text
i = 2
j = 2
```

Every iteration, **exactly one pointer moves**.

| Iteration | i | j  | Pointer moved |
| --------- | - | -- | ------------- |
| Start     | 2 | 2  | -             |
| 1         | 2 | 1  | j--           |
| 2         | 2 | 0  | j--           |
| 3         | 1 | 0  | i--           |
| 4         | 0 | 0  | i--           |
| 5         | 0 | -1 | j--           |

How many times did `i` move?

```text
2 → 1 → 0
```

That's **2 moves** (or at most `m` moves in general).

How many times did `j` move?

```text
2 → 1 → 0 → -1
```

That's **3 moves** (equal to `n` here).

Total pointer movements:

```text
2 + 3 = 5
```

Notice the loop stopped before `i` reached `-1`.

---

Now look at another example.

```text
nums1 = [4,5,6]
nums2 = [1,2,3]
```

| Iteration | i  | j | Pointer moved |
| --------- | -- | - | ------------- |
| Start     | 2  | 2 | -             |
| 1         | 1  | 2 | i--           |
| 2         | 0  | 2 | i--           |
| 3         | -1 | 2 | i--           |

Here:

* `i` moved **3** times (`m`).
* `j` moved **0** times.

Then the second loop copies the remaining `nums2` elements:

```text
j: 2 → 1 → 0 → -1
```

That's **3** more moves (`n`).

Total work:

```text
3 + 3 = 6 = m + n
```

---

### The key idea

Think of each pointer as having a limited number of "lives."

```
i has m lives.
j has n lives.
```

Every iteration consumes **one life** from either `i` or `j`.

So the algorithm can perform at most:

```text
m lives + n lives = m + n iterations
```

It **cannot** do more because once both pointers have exhausted all their lives, there's nothing left to process.

---

### A simpler intuition

There are exactly:

* `m` real elements in `nums1`
* `n` elements in `nums2`

Your algorithm eventually places **all `m + n` elements** into their correct positions.

Since each iteration places **one element**, the total number of iterations is proportional to:

```text
m + n
```

That's why the time complexity is **O(m + n)**. This is usually the easiest way to remember it.
