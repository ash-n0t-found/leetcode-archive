# Kth Largest Element in a Stream

This is one of those questions that sounds much more confusing than it actually is. Let's break it down.

---

# What is the question asking?

You're given:

- A number `k`
- An initial list of numbers
- Then, numbers keep getting added one by one

**After every new number is added, you must return the kth largest number among all numbers seen so far.**

---

# Example 1

```text
k = 3
nums = [4, 5, 8, 2]
```

First, sort them in descending order:

```text
8, 5, 4, 2
```

The **3rd largest** is:

```text
8 (1st)
5 (2nd)
4 (3rd) ← Answer
```

---

## add(3)

Now the numbers are:

```text
[4, 5, 8, 2, 3]
```

Sort:

```text
8, 5, 4, 3, 2
```

3rd largest is still

```text
4
```

So return

```text
4
```

---

## add(5)

Numbers:

```text
[4, 5, 8, 2, 3, 5]
```

Sort:

```text
8, 5, 5, 4, 3, 2
```

Count:

```text
8 -> 1st
5 -> 2nd
5 -> 3rd
```

Return

```text
5
```

Notice that **duplicates count**.

---

## add(10)

Numbers:

```text
[4, 5, 8, 2, 3, 5, 10]
```

Sort:

```text
10, 8, 5, 5, 4, 3, 2
```

Count:

```text
10 -> 1st
8  -> 2nd
5  -> 3rd
```

Return

```text
5
```

---

## add(9)

Sort:

```text
10, 9, 8, 5, 5, 4, 3, 2
```

Count:

```text
10 -> 1st
9  -> 2nd
8  -> 3rd
```

Return

```text
8
```

---

## add(4)

Sort:

```text
10, 9, 8, 5, 5, 4, 4, 3, 2
```

3rd largest is still

```text
8
```

---

# Why don't we return the largest?

Suppose

```text
k = 4
```

and the numbers are

```text
100, 90, 80, 70, 60
```

Then

```text
Largest      = 100
2nd largest  = 90
3rd largest  = 80
4th largest  = 70
```

So we'd return **70**, not 100.

---

# Why can't we just sort every time?

Imagine

```text
100,000 numbers
```

and another

```text
100,000 add() calls
```

Sorting every time would be extremely slow.

The challenge is to keep track of the kth largest **efficiently** after every insertion.

---

# The Key Observation

Suppose

```text
k = 3
```

Do we actually care about all numbers?

Consider

```text
100, 90, 80, 70, 60, 50, 40, 30
```

The answer is **80**.

Do we need to remember

```text
70, 60, 50, 40, 30
```

to know the 3rd largest?

**No.**

We only need the **largest 3 numbers**:

```text
100, 90, 80
```

Everything smaller can never affect the answer unless a new larger number arrives.

This observation is exactly why the optimal solution uses a **min heap of size `k`**.

The heap always stores the **k largest numbers seen so far**, and the smallest element in that heap is precisely the **kth largest overall**.

Once you understand this idea, the implementation becomes much more intuitive.

---

# Step 1: What Data Structure Should We Use?

Suppose

```text
k = 3

Numbers:
4 5 8 2
```

We only care about the **3 largest** numbers.

Those are

```text
4 5 8
```

Notice something:

The answer is simply the **smallest among these 3**, which is **4**.

So we want a data structure that lets us quickly know the **smallest** element among the largest `k` elements.

That's exactly what a **min heap** does.

In Java:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

By default, `PriorityQueue` is a **min heap**.

---

# Step 2: Constructor

We need to remember:

```java
private PriorityQueue<Integer> pq;
private int k;
```

Now think about the constructor.

For every number in `nums`, what should we do?

Simply insert it into the heap.

```java
pq.offer(num);
```

But...

Should the heap keep growing forever?

No.

We only need **k** numbers.

So after inserting,

```java
if (pq.size() > k)
```

what should happen?

👉 Remove the smallest.

Which method removes the smallest from a min heap?

```java
pq.poll();
```

After processing all numbers, the heap contains exactly the **k largest elements**.

---

# Step 3: add(val)

Exactly the same idea.

Insert it.

```java
pq.offer(val);
```

If the size becomes larger than `k`

```java
pq.poll();
```

Finally, what should we return?

The smallest element inside the heap.

Which method gives it?

```java
pq.peek();
```

That's the kth largest.

---

# Full Code

```java
class KthLargest {

    private PriorityQueue<Integer> pq;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();

        for (int num : nums) {
            pq.offer(num);

            if (pq.size() > k) {
                pq.poll();
            }
        }
    }

    public int add(int val) {
        pq.offer(val);

        if (pq.size() > k) {
            pq.poll();
        }

        return pq.peek();
    }
}
```

---

# Dry Run

Suppose

```text
k = 3

nums = [4, 5, 8, 2]
```

Heap after each insertion:

### Add 4

```text
[4]
```

---

### Add 5

```text
[4, 5]
```

---

### Add 8

```text
[4, 5, 8]
```

Size = 3

Good.

---

### Add 2

Heap becomes

```text
[2, 4, 8, 5]
```

Size = 4

Too many.

Remove smallest.

```text
poll() → 2
```

Heap becomes

```text
[4, 5, 8]
```

Notice how **2 disappeared forever** because it'll never be among the top 3 largest.

---

Now call

```java
add(10)
```

Insert

```text
[4, 5, 8, 10]
```

Too many.

Remove smallest.

```text
poll() → 4
```

Heap becomes

```text
[5, 8, 10]
```

Now

```text
peek() = 5
```

which is exactly the **3rd largest**.

---

# Complexity

Let `k` be the heap size.

- **Constructor:** `O(n log k)` (each insertion/removal costs `log k`)
- **add():** `O(log k)`
- **peek():** `O(1)`
- **Space:** `O(k)`

---

# Why Do We Use a Min Heap Instead of a Max Heap?

We only care about the **top `k` largest elements**, not all elements.

A min heap of size `k` lets us:

- Keep only the largest `k` elements.
- Instantly know the smallest among them using `peek()`.
- That smallest element is exactly the **kth largest**.

If we used a max heap, we'd have to store **every element** and remove the largest `k - 1` elements every time we wanted the answer.

That would make each `add()` operation much slower.

Using a **min heap of size `k`** keeps every operation efficient.


There are two complexities to consider: the **constructor** and the **`add()`** method.

| Operation                       | Time Complexity | Why?                                                                                                         |
| ------------------------------- | --------------- | ------------------------------------------------------------------------------------------------------------ |
| `KthLargest(int k, int[] nums)` | **O(n log k)**  | We insert each of the `n` numbers into a heap of size at most `k`. Each `offer()`/`poll()` costs `O(log k)`. |
| `add(int val)`                  | **O(log k)**    | We insert one element and possibly remove one element from the heap.                                         |
| `peek()`                        | **O(1)**        | The smallest element (the kth largest overall) is always at the root.                                        |
| Space                           | **O(k)**        | The heap never stores more than `k` elements.                                                                |

### Why is the constructor `O(n log k)` and not `O(n log n)`?

This is a common interview question.

Notice this part of the code:

```java
pq.offer(num);

if (pq.size() > k) {
    pq.poll();
}
```

The heap **never grows beyond `k` elements**.

A heap operation takes **`O(log(size of heap))`**.

Since the heap size is always at most `k`, each insertion/removal costs:

```text
O(log k)
```

Doing this for `n` numbers gives:

```text
n × O(log k) = O(n log k)
```

### Example

Suppose:

```text
n = 1,000,000
k = 10
```

Then:

* `log₂(10) ≈ 3.3`
* `log₂(1,000,000) ≈ 20`

Using a heap of size `10` is much cheaper than using a heap of size `1,000,000`.

That's why this solution is much more efficient than storing every number in a max heap or sorting after each insertion.
