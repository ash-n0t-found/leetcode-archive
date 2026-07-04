# Letter Combinations of a Phone Number - Understanding Recursion Function Calls

Input:

```text
digits = "23"
```

---

# Before anything

```java
backtrack(0, "", result);
```

Java creates:

## Function Call 1 (ACTIVE)

```text
Fn Call 1
-------------------
index = 0
current = ""

Status: ACTIVE
```

No other function exists.

---

## Fn Call 1

```java
letters = "abc"
```

Loop starts.

First letter

```text
a
```

Append

```text
current = "a"
```

Now execute

```java
backtrack(index + 1,...)
```

Current index is

```text
0
```

so Java calls

```java
backtrack(1,...)
```

---

# Function Call 2 is created

Now the situation is

```text
Fn Call 2
index = 1
current = "a"

Status: ACTIVE
```

```text
Fn Call 1
index = 0
current = "a"

Status: PAUSED
```

⚠️ **This is the important part.**

Fn Call 1 is **not finished**.

It is simply waiting for Fn Call 2 to finish.

---

## Fn Call 2

Current

```text
index = 1
```

Letters

```text
def
```

Loop starts.

Choose

```text
d
```

Append

```text
current = "ad"
```

Execute

```java
backtrack(index+1,...)
```

Current index is

```text
1
```

so Java calls

```java
backtrack(2,...)
```

---

# Function Call 3 is created

State now

```text
Fn Call 3
index = 2
current = "ad"

Status: ACTIVE
```

```text
Fn Call 2
index = 1
current = "ad"

Status: PAUSED
```

```text
Fn Call 1
index = 0
current = "ad"

Status: PAUSED
```

Notice

Only **Fn Call 3** is running.

---

## Fn Call 3

Immediately

```java
if(index == digits.length())
```

becomes

```text
2 == 2
```

True.

Store

```text
ad
```

Result

```text
[ad]
```

Execute

```java
return;
```

---

# Fn Call 3 is FINISHED ❌

It disappears forever.

Now only

```text
Fn Call 2
index = 1

Status: ACTIVE
```

and

```text
Fn Call 1
index = 0

Status: PAUSED
```

remain.

---

## Back to Fn Call 2

This is exactly where Java resumes:

```java
current.deleteCharAt(...)
```

Current

```text
ad
```

Delete

```text
a
```

Now

```text
current = "a"
```

---

### Loop continues.

Next letter

```text
e
```

Notice carefully...

Which function is active?

```text
Fn Call 2
index = 1
```

NOT

```text
Fn Call 3
```

because Fn Call 3 is dead.

So yes,

```text
index = 1
```

This was the confusing part. 😊

---

Choose

```text
e
```

Append

```text
current = "ae"
```

Execute

```java
backtrack(2,...)
```

---

# Function Call 4

Java creates a BRAND NEW function.

```text
Fn Call 4
index = 2
current = "ae"

Status: ACTIVE
```

```text
Fn Call 2
index = 1

Status: PAUSED
```

```text
Fn Call 1
index = 0

Status: PAUSED
```

Notice...

Fn Call 3 is gone forever.

Fn Call 4 is a completely new function.

---

Base case

Store

```text
ae
```

Result

```text
[ad, ae]
```

Return.

---

Fn Call 4

```text
FINISHED ❌
```

Now

```text
Fn Call 2
index = 1

ACTIVE
```

---

Delete

```text
e
```

Current

```text
a
```

Loop continues.

---

Choose

```text
f
```

Append

```text
af
```

Create

---

# Function Call 5

```text
Fn Call 5
index = 2

ACTIVE
```

Store

```text
af
```

Return.

Fn Call 5

```text
FINISHED ❌
```

---

Back to

```text
Fn Call 2

ACTIVE
```

Delete

```text
f
```

Loop is over.

Fn Call 2 executes

```java
return;
```

---

# Fn Call 2

```text
FINISHED ❌
```

Now ONLY

```text
Fn Call 1

index = 0

ACTIVE
```

exists.

---

Java resumes Fn Call 1.

Delete

```text
a
```

Current

```text
""
```

Loop continues.

Next letter

```text
b
```

---

## This is the BIG picture

```text
Fn1(index=0) ACTIVE

↓

creates

↓

Fn2(index=1) ACTIVE

Fn1 PAUSED

↓

creates

↓

Fn3(index=2) ACTIVE

Fn2 PAUSED

Fn1 PAUSED

↓

Fn3 FINISHED ❌

↓

Fn2 ACTIVE again

↓

creates

↓

Fn4(index=2)

↓

Fn4 FINISHED ❌

↓

Fn2 ACTIVE

↓

creates

↓

Fn5(index=2)

↓

Fn5 FINISHED ❌

↓

Fn2 FINISHED ❌

↓

Fn1 ACTIVE again
```

---

# ⭐ The one sentence I want you to remember forever

**Every `backtrack(...)` creates a brand new function call with its own `index`.**

When that function executes `return`, **it is destroyed**, and Java goes back to the function that created it.

---

## Self Check

**At the moment when the loop moves from `d` to `e`, which function call is active?**

- A) Fn Call 3 (`index = 2`)
- **B) Fn Call 2 (`index = 1`) ✅**

Reason:

Fn Call 3 has already reached the base case, executed `return`, and **finished**. Java resumes execution in **Fn Call 2**, right after the recursive call, so the loop continues with the next letter (`e`).
