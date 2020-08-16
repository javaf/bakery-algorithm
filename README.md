Multiple customers are planning to eat at a bakery. For each item, the pick a "latest" ticket and wait for their turn to arrive. When their turn comes, they place the order, clear their ticket, goto eating. When they are hungry again, the process repeats.

Each customer picks a maximum ticket, but since this maximum can be the same for two people more preference is given to customer with smaller id. Also, if picking maximum is not made atomic, it is possible for two customers to end up ordering at the same time, so a "choosing" status is present for each customer.

See [Main.java] for code, and [repl.it] for output.

[Main.java]: https://repl.it/@wolfram77/bakery-algorithm#Main.java
[repl.it]: https://bakery-algorithm.wolfram77.repl.run


### references

- [W6 L4 Bakery Algorithm :: Introduction to Operating Systems](https://www.youtube.com/watch?v=3pUScfud9Sg)
