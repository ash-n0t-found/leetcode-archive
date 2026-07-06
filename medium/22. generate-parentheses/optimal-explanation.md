# Generate Parentheses - Backtracking Notes

## 4. Deriving the Conditions

We are given `n` pairs of parentheses and need to generate **all valid combinations**.

At every step, we have only **two choices**:

- Add `'('`
- Add `')'`

However, both choices have constraints.

### Rule 1: When can we add `'('`?

We cannot use more than `n` opening brackets.

```java
if (open < n)
```

Example (`n = 3`):

```text
(((
```

Valid.

```text
((((
```

Invalid (already used 3 opening brackets).

---

### Rule 2: When can we add `')'`?

A closing bracket can only be added if there is an unmatched opening bracket.

```java
if (close < open)
```

Example:

```text
(
```

Can add `)`:

```text
()
```

Valid.

But

```text
()
```

cannot become

```text
())
```

because now we have used more closing brackets than opening brackets.

---

### Base Case

Every valid string contains exactly:

- `n` opening brackets
- `n` closing brackets

Total length:

```java
2 * n
```

So,

```java
if(current.length() == 2 * n)
```

Store the answer and return.

---

# 5. Complete Java Code

```java
class Solution {

    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack(0, 0, n, new StringBuilder(), result);

        return result;
    }

    private void backtrack(int open,
                           int close,
                           int n,
                           StringBuilder current,
                           List<String> result) {

        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        if (open < n) {

            current.append('(');

            backtrack(open + 1, close, n, current, result);

            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {

            current.append(')');

            backtrack(open, close + 1, n, current, result);

            current.deleteCharAt(current.length() - 1);
        }
    }
}
```

---

# 6. Line-by-Line Explanation

Variables:

| Variable | Meaning |
|----------|---------|
| `open` | Number of `'('` used |
| `close` | Number of `')'` used |
| `current` | Current string being built |
| `result` | Stores all valid answers |

The recursive function follows exactly three steps.

```java
current.append(...);
```

**Choose**

↓

```java
backtrack(...);
```

**Explore**

↓

```java
current.deleteCharAt(...);
```

**Undo (Backtrack)**

This same template appears in almost every backtracking problem.

---

# 7. Dry Run (n = 3)

## Initial Call

```java
backtrack(0,0,"")
```

---

## Function Call 1

```text
Fn1

open = 0
close = 0
current = ""

ACTIVE
```

Can add `'('`

Create

```text
Fn2
```

Fn1 becomes

```text
PAUSED
```

---

## Function Call 2

```text
Fn2

open = 1
close = 0
current = "("

ACTIVE
```

Can add `'('`

Create

```text
Fn3
```

Fn2 pauses.

---

## Function Call 3

```text
Fn3

open = 2
close = 0
current = "(("

ACTIVE
```

Can add `'('`

Create

```text
Fn4
```

---

## Function Call 4

```text
Fn4

open = 3
close = 0
current = "((("

ACTIVE
```

Cannot add `'('`

Can add `')'`

Create

```text
Fn5
```

---

## Function Call 5

```text
Fn5

open = 3
close = 1
current = "((()"

ACTIVE
```

Can only add `')'`

Create

```text
Fn6
```

---

## Function Call 6

```text
Fn6

open = 3
close = 2
current = "((())"

ACTIVE
```

Can only add `')'`

Create

```text
Fn7
```

---

## Function Call 7

```text
Fn7

open = 3
close = 3
current = "((()))"

ACTIVE
```

Length = 6

Base Case reached.

Store

```text
((()))
```

Return.

Fn7 is finished.

---

Now execution starts moving **back up**.

Fn6 becomes active.

Executes

```java
current.deleteCharAt(...)
```

Current changes

```text
((()))

↓

((())
```

Fn6 has no more code.

Fn6 finishes.

---

Fn5 becomes active.

Deletes last `)`.

```text
((())

↓

((()
```

Fn5 finishes.

---

Fn4 becomes active.

Deletes last `)`.

```text
((()

↓

(((
```

Fn4 finishes.

---

Fn3 becomes active again.

Current:

```text
((
```

Now Fn3 has finished exploring the `'('` branch.

It now checks the second choice.

```java
if(close < open)
```

Current values

```text
open = 2
close = 0
```

Condition is true.

Append

```text
)
```

Current becomes

```text
(()
```

Create a NEW function call.

This branch eventually produces

```text
(()())
(())()
```

---

After those finish,

Fn2 resumes.

It explores

```text
()
```

which produces

```text
()(())
()()()
```

Final Answer

```text
((()))
(()())
(())()
()(())
()()()
```

---

# 8. Function Call Stack

During recursion:

```text
Fn1 ACTIVE

↓

Fn2 ACTIVE

Fn1 PAUSED

↓

Fn3 ACTIVE

Fn2 PAUSED

Fn1 PAUSED

↓

Fn4 ACTIVE

↓

Fn5 ACTIVE

↓

Fn6 ACTIVE

↓

Fn7 ACTIVE
```

Base Case

```text
Fn7 FINISHED
```

Backtrack

```text
Fn6 ACTIVE

↓

Fn6 FINISHED

↓

Fn5 ACTIVE

↓

Fn5 FINISHED

↓

Fn4 ACTIVE

↓

Fn4 FINISHED

↓

Fn3 ACTIVE
```

The recursion continues until every branch has been explored.

---

# 9. Why Backtracking Works

Each function explores **one possible choice**.

Before exploring, it **adds** a character.

After exploration, it **removes** the character.

This allows the same `StringBuilder` to be reused.

Pattern:

```java
current.append(...);

backtrack(...);

current.deleteCharAt(...);
```

Think of it as:

```text
Choose

↓

Explore

↓

Undo
```

---

# 10. Complexity Analysis

Let `Cₙ` be the number of valid parenthesis strings (the nth Catalan number).

Time Complexity:

```text
O(Cₙ × n)
```

Reason:

- There are `Cₙ` valid answers.
- Copying each answer into the result takes `O(n)` time.

Space Complexity:

```text
O(n)
```

Reason:

- Maximum recursion depth is `2n`.
- `StringBuilder` also stores at most `2n` characters.

(Result storage is not counted as auxiliary space.)

---

# 11. Common Mistakes

❌ Forgetting

```java
current.deleteCharAt(...)
```

This causes every recursive branch to share incorrect data.

---

❌ Using

```java
close < n
```

Correct condition:

```java
close < open
```

Otherwise invalid strings like

```text
())
```

can be generated.

---

❌ Incorrect base case

Wrong

```java
open == n && close == n
```

Preferred

```java
current.length() == 2 * n
```

Simple and clean.

---

# 14. Pattern Recognition

When you see interview questions like

- Generate Parentheses
- Letter Combinations of a Phone Number
- Subsets
- Permutations
- Combination Sum
- N Queens
- Sudoku Solver
- Word Search

Immediately think

```text
Backtracking
```

Most backtracking problems follow exactly the same structure.

```java
Choose

↓

Recurse

↓

Undo
```

Only the choices and constraints change.
