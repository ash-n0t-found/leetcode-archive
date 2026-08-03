Ahh, the **space complexity** is actually much easier than the time complexity. Let's ignore time completely.

## What extra memory are we using?

Look at your code:

```java
Set<Integer> seen = new HashSet<>();
```

That's the **only** extra data structure.

So ask yourself:

> **How many numbers can `seen` store?**

---

### Example 1: Happy number

```text
19
82
68
100
1
```

The HashSet contains:

```text
{19, 82, 68, 100}
```

Size = **4**

---

### Example 2: Unhappy number

```text
2
4
16
37
58
89
145
42
20
4
```

Before we see `4` again, the HashSet contains

```text
{2, 4, 16, 37, 58, 89, 145, 42, 20}
```

Size = **9**

---

## So what's the worst case?

Suppose the sequence visits **k** different numbers before stopping.

Then the HashSet stores

```text
k numbers
```

So the space complexity is

```text
O(k)
```

Now the question becomes:

> **How big can `k` get?**

---

## For Java `int`

The largest `int` is

```text
2,147,483,647
```

which has **10 digits**.

The next number can never exceed

```text
10 × 81 = 810
```

So after the **first iteration**, every number is between

```text
1 and 810
```

That means the HashSet can never hold more than **810 different values**.

Worst case:

```text
seen = {810 different numbers}
```

810 is just a constant.

So:

```text
Space = O(810)
```

and Big-O ignores constants:

```text
Space = O(1)
```

---

## Why do some websites say `O(log n)`?

They're analyzing the algorithm **without assuming a fixed-size integer**.

Imagine numbers with **1,000 digits** (like Java's `BigInteger`).

Then:

* `nextNumber()` could initially produce a much larger value.
* The sequence could go through more distinct states before collapsing.

In that more general mathematical model, it's common to express the space as `O(log n)`.

---

### For interviews (LeetCode with Java `int`)

I would answer:

* **Time:** `O(log n)`
* **Space:** `O(1)`

because the HashSet can store at most a constant number of values (bounded by about 810).

---

### One question for you

If I **remove** this line:

```java
Set<Integer> seen = new HashSet<>();
```

and instead use Floyd's slow and fast pointers, what would the space complexity be?

Think about it before answering.
