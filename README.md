# NonBlockingIncrement
Simplest nonblocking algorithm: a counter that uses the compareAndSet() method of AtomicLong.
The compareAndSet() method says "Update this variable to this new value, but fail if some other thread changed the value since I last looked"
