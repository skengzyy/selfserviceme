# Pizza Service Multithreading (EK)

Simulation eines Pizza-Services mit Java Threads.

## Features
- **Kein Busy Waiting**: Threads nutzen `wait()` und `notifyAll()`.
- **Thread-Sicherheit**: Einsatz von `AtomicInteger` und `BlockingQueue`.
- **Kapselung**: Saubere Trennung von Logik und Synchronisation.

## Ausführung
```bash
javac -d bin src/*.java
java -cp bin Main
