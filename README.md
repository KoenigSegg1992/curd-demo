# Redis Methods
Lettuce 和 Jedis 都是Redis的client，所以他们都可以连接 Redis Server。
Jedis在实现上是`直接连接的Redis Server`，如果在多线程环境下是`非线程安全`的。
每个线程都去拿自己的 Jedis 实例，当连接数量增多时，资源消耗阶梯式增大，连接成本就较高了。

Lettuce的连接是基于Netty的，Netty 是一个多线程、事件驱动的 I/O 框架。连接实例可以在多个线程间共享，当多线程使用同一连接实例时，是`线程安全`的。
所以，一个多线程的应用可以使用同一个连接实例，而不用担心并发线程的数量。
