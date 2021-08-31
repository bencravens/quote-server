# quote-server
A multithreaded TCP/IP server that prints inspirational quotes to the client

TODO: 
- [x] Multi-threaded server
- [x] Client program

To run:

```Bash
javac QuoteServer.java
javac QuoteClient.java
java QuoteServer &
java QuoteClient
```

Kill the server after you are done (it will be a process running in background) by getting its id with

```Bash
ps aux | grep Quote
kill {id goes here}
```
