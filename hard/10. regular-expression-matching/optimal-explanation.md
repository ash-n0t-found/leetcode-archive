REFER NEETCODE YT VID TO UNDERSTAND THE PROBLEM 

# Regular Expression Matching (LeetCode 10) – Complete Notes

> These notes are based on the recursive + memoized (top-down DP) solution.

## 1. What regex knowledge do I need?

Only two operators:

- `.` → matches **exactly one** character.
- `*` → matches **zero or more of the previous character**.

Examples:

- `a*` matches: `""`, `a`, `aa`, `aaa`, ...
- `.` matches: `a`, `b`, `x`, ...
- `.*` matches any string (including the empty string).

---

## 2. The Recursive Question

Every recursive call answers exactly one question:

```
Does s[i...] match p[j...] ?
```

State:

```java
dfs(i, j)
```

---

## 3. Three Recursive Transitions

### Normal match

```
dfs(i+1, j+1)
```

Both pointers move.

---

### Ignore `x*`

```
dfs(i, j+2)
```

Skip both `x` and `*`.

---

### Use one character from `x*`

```
dfs(i+1, j)
```

Consume one character from the string.

The pattern pointer **does not move** because `x*` can still match more characters.

---

## 4. Decision Tree Example

Example:

```
s = "aa"
p = "a*"
```

```
                    dfs(0,0)

             Does "aa" match "a*" ?

                    /           \
                   /             \
      Ignore a*                 Use one 'a'
      dfs(0,2)                 dfs(1,0)
         False

                               /          \
                          Ignore       Use one

                        dfs(1,2)      dfs(2,0)
                           False          |
                                          |
                                     Ignore a*
                                          |
                                      dfs(2,2)
                                          |
                                         True
```

Returning upward:

```
dfs(2,2) = true

↓

dfs(2,0) = true

↓

dfs(1,0) = true

↓

dfs(0,0) = true
```

---

## 5. Dry Run – Example 1

```
s = "aa"
p = "a"
```

```
dfs(0,0)

a == a

↓

dfs(1,1)

Pattern finished

String not finished

↓

false
```

Answer:

```
false
```

---

## 6. Dry Run – Example 2

```
s = "aa"
p = "a*"
```

Decision tree shown above.

Answer:

```
true
```

---

## 7. Dry Run – Example 3

```
s = "ab"
p = ".*"
```

```
dfs(0,0)

. matches a

↓

Use one character

↓

dfs(1,0)

. matches b

↓

Use one character

↓

dfs(2,0)

String finished

↓

Ignore .*

↓

dfs(2,2)

Both finished

↓

true
```

Answer:

```
true
```

---

## 8. Why does `dfs(i+1, j)` keep `j` the same?

Think of `a*` as a machine.

```
a*

↓

a
a
a
a
...
```

Using one `a` does **not** finish the machine.

So:

```
dfs(i+1, j)
```

means:

- consume one character from the string
- stay on the same `a*`

---

## 9. Why `dfs(i, j+2)`?

Ignoring `a*` means skipping the entire unit.

```
a *
^ ^

↓

j + 2
```

---

## 10. Why check

```java
j + 1 < p.length()
```

before

```java
p.charAt(j+1)
```

Because Java would otherwise evaluate

```java
p.charAt(j+1)
```

even when `j+1` is outside the pattern.

Example:

```
Pattern = "abc"

j = 2

j+1 = 3
```

```
p.charAt(3)
```

throws

```
StringIndexOutOfBoundsException
```

The first condition protects the second using short-circuit `&&`.

---

## 11. Why don't we check `j < p.length()` inside `match`?

Earlier we already do:

```java
if (j >= p.length())
    return false;
```

So by the time execution reaches

```java
p.charAt(j)
```

we already know `j` is valid.

---

## 12. Why use `Boolean[][]` instead of `boolean[][]`?

Primitive `boolean` has only

```
true
false
```

But memoization needs three states:

| Value | Meaning |
|------|---------|
| null | Not computed |
| true | Computed → true |
| false | Computed → false |

That is why we use

```java
Boolean[][]
```

---

## 13. Why check

```java
if(dp[i][j] != null)
```

Think of `dp` as a notebook.

Initially:

```
dp[0][0] = null
```

means

```
I have never solved this state.
```

After solving:

```
dp[0][0] = true
```

Next time the same state appears:

```
Immediately return it.
```

No recursion.

Without `null`, Java cannot distinguish:

- "not solved"
- "solved and answer is false"

---

## 14. Complexity

### Without Memoization

Every `*` creates two branches.

Time:

```
O(2^(m+n))
```

Space:

```
O(m+n)
```

---

### With Memoization

There are only

```
(m+1) × (n+1)
```

states.

Each state is solved once.

Time:

```
O(m × n)
```

Space:

```
O(m × n)
```

---

## 15. Mental Model

Whenever you see:

```
x*
```

Always think:

```
            x*

         /        \

      Ignore      Use one

   dfs(i,j+2)   dfs(i+1,j)
```

When there is no `*`:

```
Characters match?

       |

      Yes

       |

dfs(i+1,j+1)
```

Those three recursive transitions are the entire algorithm.
