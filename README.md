# Genetic-Algorithm
Proyect developed in Java 1.8 to solve optimization problems minimizing cost/fitness function associated by user.

## Introduction
This proyect was originally create for optimizing several metrics in a maritime environment. The parameters of this tool was coded tp represent all of metrics in a binary genome of size 14.

## Execution
At the top of Main.class, the next parameters must be modified depending of your problem:
- **populationSize**: number of individuals in population.
- **tourSize**: number of inidivuals that compete in tournaments.
- **n_iter**: number of iterations.
- **geneLength**: lenght of gen. It is fix by the problem.
- **mutationRate**: probability of one gen within a genome is changed by mutation method. 
- **uniformRate**: probability of one gen within a genome is inherited by its "father" or its "mother".

Futhermore, it is necessary to define cost/fitness function depending on the problem that you want to solve.

