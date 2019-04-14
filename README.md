# Object-oriented programming

Classes, objects, interfaces, polymorphism, inheritance, encapsulation. All ```this``` and much more in tasks from academic course on OO-programming taught in Java.

*Unfortunately* all code and documentation is in Polish. Thankfully since then learned to code in English.

## Planista (*The Planner*)

Simulation of a single-processor prioritizing module: given a number of processes, their entry time, and required resources (in seconds), the program prints, for every prioritizing strategy entry and exit time for each process, mean time of waiting, and mean time in processor.

Implemented strategies:
 - First Come First Served
 - Shortest Job First
 - Shortest Remaining Time - self explanatory
 - Processor Sharing - all the processes are given the processor at entry time, however real effectiveness drops proportionally to number of connected processes.
 - Round Robin - Processor gives access to every process in a cyclic queue for a given amount of time ```q```. Particularly, RR strategy with infinite ```q``` is equivalent to FCFS strategy.

Example input:
```
  5       // number of processes
  0 10    // process of id 1: 0 - entry time, 10 - required time of access
  0 29    // process of id 2: 0 - entry time, 29 - required time of access
  0 3     // ...
  0 7
  0 12
  10      // Time
```  
 Produces the output:
```
  Strategia: FCFS                   // strategy name
  [1 0 10.00][2 0 39.00][3 0 42.00][4 0 49.00][5 0 61.00] // [<id> <entry time> <exit time>]
  Średni czas obrotu: 40.20         // mean time in processor
  Średni czas oczekiwania: 28.00    // mean wait time

  Strategia: SJF
  [3 0 3.00][4 0 10.00][1 0 20.00][5 0 32.00][2 0 61.00]
  Średni czas obrotu: 25.20
  Średni czas oczekiwania: 13.00

  Strategia: SRT
  [3 0 3.00][4 0 10.00][1 0 20.00][5 0 32.00][2 0 61.00]
  Średni czas obrotu: 25.20
  Średni czas oczekiwania: 13.00

  Strategia: PS
  [3 0 15.00][4 0 31.00][1 0 40.00][5 0 44.00][2 0 61.00]
  Średni czas obrotu: 38.20
  Średni czas oczekiwania: 0.00

  Strategia: RR-1
  [3 0 13.00][4 0 30.00][1 0 38.00][5 0 44.00][2 0 61.00]
  Średni czas obrotu: 37.20
  Średni czas oczekiwania: 25.00

  Strategia: RR-10
  [1 0 10.00][3 0 23.00][4 0 30.00][5 0 52.00][2 0 61.00]
  Średni czas obrotu: 35.20
  Średni czas oczekiwania: 23.00
```

## Epidemia (*Epidemic*)

[Click to see the assignment. (In Polish)](Epidemia/treść.md)
