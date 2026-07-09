# Combination Sum II (LeetCode 40)

## Problem Statement

Given an array `candidates` (which **may contain duplicates**) and a target, return all **unique combinations** where:

- Each number may be **used only once**.
- The sum of the chosen numbers equals `target`.
- The result should **not contain duplicate combinations**.

Example:

```text
Input:
candidates = [10,1,2,7,6,1,5]
target = 8

Output:
[
 [1,1,6],
 [1,2,5],
 [1,7],
 [2,6]
]
```

---

# Difference Between Combination Sum I and II

| Feature | Combination Sum I | Combination Sum II |
|----------|-------------------|--------------------|
| Input has duplicates? | No | Yes |
| Can reuse same element? | ✅ Yes | ❌ No |
| Sort required? | ❌ No | ✅ Yes |
| Duplicate handling | ❌ No | ✅ Yes |
| Recursive call after choosing | `index` | `i + 1` |

---

# Why Sort the Array?

We sort because:

1. Duplicate numbers become adjacent.
2. We can skip duplicate branches.
3. We can stop early using `break`.

Example:

```text
Before

[10,1,2,7,6,1,5]

↓

After sorting

[1,1,2,5,6,7,10]
```

---

# The Code

```java
class Solution {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);

        backtrack(candidates, target, 0, new ArrayList<>());

        return result;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> current) {

        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            if (i > start && candidates[i] == candidates[i - 1])
                continue;

            if (candidates[i] > target)
                break;

            current.add(candidates[i]);

            backtrack(candidates, target - candidates[i], i + 1, current);

            current.remove(current.size() - 1);
        }
    }
}
```

---

# Understanding the Algorithm

Suppose

```text
candidates = [1,1,2]
target = 3
```

After sorting:

```text
Index : 0 1 2
Value : 1 1 2
```

---

## Root Call

```text
backtrack(target=3, start=0, current=[])
```

Loop begins.

```
i = 0
```

Choose first 1.

```
current = [1]
```

Recursive call:

```text
backtrack(target=2, start=1, current=[1])
```

---

## Second Recursive Call

Notice:

```text
start = 1
current = [1]
```

The loop starts from

```text
i = 1
```

Choose second 1.

```
current = [1,1]
```

Recursive call.

Nothing works.

Return.

Backtrack.

Current becomes

```text
[1]
```

Loop continues.

```
i = 2
```

Choose 2.

```
current = [1,2]
```

Remaining target becomes

```
0
```

Valid answer!

```
[1,2]
```

Return.

Second recursive call ends.

---

Back to the root call.

Current becomes

```text
[]
```

The root loop now continues.

```
i = 1
```

Now the duplicate condition is checked.

```
i > start

1 > 0

true
```

```
candidates[1] == candidates[0]

1 == 1

true
```

Therefore

```java
continue;
```

So we skip this iteration.

The loop moves directly to

```
i = 2
```

No duplicate branch is created.

---

# Why is continue needed?

Without this condition, the root loop would create two branches.

```
[]
├── first 1
└── second 1
```

Both eventually generate

```
[1,2]
```

Result becomes

```
[
 [1,2],
 [1,2]
]
```

Duplicate!

So we skip the second branch.

---

# Then How Do We Still Use the Second 1?

This confuses almost everyone.

Suppose we already picked the first 1.

Now we are inside

```text
backtrack(start=1, current=[1])
```

The loop begins from

```
i = 1
```

Check

```
i > start

1 > 1

false
```

Since the first condition is false,

the duplicate check fails.

So we are allowed to choose the second 1.

That's exactly how

```
[1,1,6]
```

is formed.

---

# Why Do We Need `i > start`?

Suppose we only wrote

```java
if(candidates[i] == candidates[i-1])
    continue;
```

Now consider

```text
[1,1,6]
```

After choosing the first 1,

we enter

```text
start = 1
```

The loop starts from

```
i = 1
```

Now

```
candidates[1] == candidates[0]

1 == 1
```

would be true.

So we'd skip the second 1.

Then

```
[1,1,6]
```

could never be formed.

Wrong answer.

That's why we need

```java
i > start
```

It means:

> Skip duplicates **only after the first occurrence at the current recursion level.**

---

# What Does `i > start` Mean?

Think of `start` as the beginning of the current recursion level.

Example:

```
start = 0

Loop

i = 0
i = 1
i = 2
```

The first occurrence

```
i = start
```

is always allowed.

Only later duplicates are skipped.

---

# The Meaning of the Duplicate Condition

```java
if(i > start && candidates[i] == candidates[i-1])
    continue;
```

Read it in English:

> "If this is **not the first candidate** at this recursion level, and its value is the same as the previous candidate, skip it."

It does **NOT** mean

> Never use duplicate numbers.

It only means

> Never start two branches with the same value at the same recursion level.

---

# Why Does continue Work?

Remember:

```java
for(...)
```

creates the branches.

Every iteration of the loop starts a new branch.

Without continue:

```
[]
├── first 1
├── second 1
└── 2
```

With continue:

```
[]
├── first 1
└── 2
```

Notice we only removed the duplicate sibling branch.

The recursive child

```
[]
|
1
|
1
```

still exists.

---

# Why i + 1?

Combination Sum II says

> Every element can only be used once.

Therefore after choosing

```text
candidates[i]
```

we recurse using

```java
i + 1
```

instead of

```java
i
```

This prevents reusing the same element.

---

# Why break?

```java
if(candidates[i] > target)
    break;
```

Suppose

```
target = 4
```

Current candidate

```
5
```

Array is sorted.

Everything after 5 is

```
6
7
10
```

They are all larger.

So no need to continue.

This saves time.

---

# Complexity Analysis

## Sorting

Sorting takes

```
O(n log n)
```

---

## Backtracking

Each element has two possibilities:

- Choose it
- Don't choose it

Therefore the recursion explores subsets.

Worst-case complexity:

```
O(2^n)
```

where

```
n = number of candidates
```

Duplicate skipping and pruning reduce the practical runtime, but the worst case remains exponential.

---

## Space Complexity

Ignoring the output,

the recursion depth is at most

```
O(n)
```

because each recursive call moves to the next index.

The current combination also stores at most

```
n
```

elements.

Therefore

```
Auxiliary Space = O(n)
```

---

# Interview Summary

Remember these five points:

1. Sort the array.

```java
Arrays.sort(candidates);
```

---

2. Loop from `start`.

```java
for(int i = start; i < candidates.length; i++)
```

---

3. Skip duplicate branches.

```java
if(i > start && candidates[i] == candidates[i-1])
    continue;
```

---

4. Since the array is sorted,

```java
if(candidates[i] > target)
    break;
```

---

5. Choose → Recurse → Backtrack

```java
current.add(candidates[i]);

backtrack(candidates,
          target - candidates[i],
          i + 1,
          current);

current.remove(current.size()-1);
```

---

# One Sentence to Remember Forever

> **The duplicate condition does NOT stop us from using duplicate numbers. It only stops us from starting duplicate branches at the same recursion level.**
