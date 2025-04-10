Multiple customers are planning to eat at a
bakery. For each item, the pick a **latest**
ticket and wait for their turn to arrive. When
their turn comes, they place the order, clear
their ticket, goto eating. When they are hungry
again, the process repeats.

Each customer picks a maximum ticket, but since
this maximum can be the same for two people
more preference is given to customer with
smaller id. Also, if picking maximum is not
made atomic, it is possible for two customers
to end up ordering at the same time, so a
**choosing** status is present for each customer.

> **Course**: [Concurrent Data Structures], Monsoon 2020\
> **Taught by**: Prof. Govindarajulu Regeti

[Concurrent Data Structures]: https://github.com/iiithf/concurrent-data-structures

```java
Each customer is here to eat a lot, and there
is only on booth:
1. Places an order (see lock)
2. Eats baked item at table
... Done, but still hungry!
```

```java
To avoid crowding at booth, all customers
follow this process:
1. Pick latest ticket
2. For all other customers:
  a. Wait if they are choosing
  b. Wait if they have early ticket, or
  c. Wait if they came before you
3. Goto the booth and place order
```

```java
When done placing the order:
1. Throw away your ticket
```

```bash
## OUTPUT
2: choosing ticket
0: choosing ticket
3: choosing ticket
1: choosing ticket
3: got ticket=2
1: got ticket=4
0: got ticket=1
3: waiting for turn
0: waiting for turn
0: placing order
0: eating
1: waiting for turn
2: got ticket=3
2: waiting for turn
0: done
0: choosing ticket
0: got ticket=5
0: waiting for turn
3: placing order
3: eating
3: done
3: choosing ticket
3: got ticket=6
3: waiting for turn
2: placing order
2: eating
2: done
2: choosing ticket
2: got ticket=7
2: waiting for turn
1: placing order
1: eating
1: done
1: choosing ticket
1: got ticket=8
1: waiting for turn
0: placing order
0: eating
...
```

See [Main.java] for code, and [repl.it] for output.

[Main.java]: https://repl.it/@wolfram77/bakery-algorithm#Main.java
[repl.it]: https://bakery-algorithm.wolfram77.repl.run


### references

- [W6 L4 Bakery Algorithm :: Introduction to Operating Systems](https://www.youtube.com/watch?v=3pUScfud9Sg)

![](https://ga-beacon.deno.dev/G-G1E8HNDZYY:v51jklKGTLmC3LAZ4rJbIQ/github.com/javaf/bakery-algorithm)
![](https://ga-beacon.deno.dev/G-G1E8HNDZYY:v51jklKGTLmC3LAZ4rJbIQ/github.com/moocf/bakery-algorithm.java)
