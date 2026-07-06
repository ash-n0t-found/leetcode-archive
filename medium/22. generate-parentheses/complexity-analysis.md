# Generate Parentheses - Complexity Analysis

---

# 1. Brute Force Complexity

## Step 1: How many strings are generated?

Each position has **2 choices**:

```text
(
)
```

For `n` pairs of parentheses, every string has length:

```text
2n
```

So the recursion tree looks like this:

```text
Level 0 : 1

Level 1 : 2

Level 2 : 4

Level 3 : 8

...

Level 2n : 2^(2n)
```

Every level doubles because each position has two choices.

Therefore, the total number of leaf nodes (complete strings) is

```text
2^(2n)
```

Using exponent rules,

```text
2^(2n)

=

(2²)^n

=

4^n
```

So the recursion generates

```text
O(4^n)
```

possible strings.

---

## Step 2: Cost of validating one string

Whenever a string reaches length

```text
2n
```

we call

```java
isValid(current)
```

The validation function scans every character.

```java
for(each character)
```

The string length is

```text
2n
```

Ignoring constants,

```text
Validation Cost = O(n)
```

---

## Total Time Complexity

Total work is

```text
(Number of Strings)

×

(Validation Cost)
```

↓

```text
O(4^n)

×

O(n)
```

↓

```text
O(n × 4^n)
```

---

## Space Complexity

Maximum recursion depth

```text
2n
```

↓

```text
O(n)
```

The `StringBuilder` also stores at most

```text
2n
```

characters.

Therefore,

```text
Auxiliary Space = O(n)
```

(Result list is not counted.)

---

## Final Complexity (Brute Force)

| Complexity | Value |
|------------|-------|
| Time | **O(n × 4ⁿ)** |
| Space | **O(n)** |

---

# 2. Optimized Backtracking Complexity

Unlike brute force, the optimized solution **never generates invalid strings**.

Example:

Brute Force generates

```text
))))))
```

Optimized solution:

```text
First character = ')'

↓

close < open ?

↓

0 < 0

↓

False

↓

Stop immediately.
```

That branch is pruned.

Similarly,

```text
())())
```

is never fully generated because the moment

```text
close > open
```

the recursion stops.

---

## How many valid answers exist?

For different values of `n`:

| n | Valid Strings |
|---|---------------|
| 1 | 1 |
| 2 | 2 |
| 3 | 5 |
| 4 | 14 |
| 5 | 42 |
| 6 | 132 |

These values are called the **Catalan Numbers**.

Instead of exploring all

```text
4^n
```

strings,

the optimized algorithm explores only the valid ones.

---

## Cost per Answer

Every valid string has length

```text
2n
```

When we execute

```java
result.add(current.toString());
```

Java copies the string.

Copying takes

```text
O(n)
```

time.

---

## Total Time Complexity

Total work is

```text
(Number of Valid Answers)

×

(Cost to Copy One Answer)
```

↓

```text
Catalan(n)

×

O(n)
```

↓

```text
O(Catalan(n) × n)
```

---

### Approximation

The nth Catalan Number is approximately

```text
4^n / n^(3/2)
```

Therefore,

```text
Catalan(n) × n

≈

(4^n / n^(3/2))

×

n

=

4^n / √n
```

So another accepted complexity is

```text
O(4^n / √n)
```

---

## Space Complexity

Maximum recursion depth

```text
2n
```

↓

```text
O(n)
```

The `StringBuilder` stores at most

```text
2n
```

characters.

Therefore,

```text
Auxiliary Space = O(n)
```

---

## Final Complexity (Optimized)

| Complexity | Value |
|------------|-------|
| Time | **O(Catalan(n) × n)** ≈ **O(4ⁿ / √n)** |
| Space | **O(n)** |

---

# Interview Explanation

### Brute Force

> Every position has two choices (`(` or `)`), and the string length is `2n`. Therefore, we generate `2^(2n) = 4^n` strings. Each string is validated in `O(n)` time, giving an overall complexity of **O(n × 4ⁿ)**.

---

### Optimized Backtracking

> Instead of generating every possible string, we prune invalid branches early using the conditions `open < n` and `close < open`. Therefore, only valid parenthesis strings are generated. The number of valid strings is the nth Catalan Number. Copying each answer takes `O(n)`, giving a total complexity of **O(Catalan(n) × n)**.

---

# Intuition to Remember

### Brute Force

```text
Generate every possible string

↓

Validate each string

↓

Discard invalid ones
```

---

### Optimized Backtracking

```text
Generate only valid prefixes

↓

Prune invalid branches immediately

↓

Never create invalid strings
```

This pruning is what makes the optimized solution significantly faster.
