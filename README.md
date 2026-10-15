# SortVisual

SortVisual is a JavaFX-based sorting algorithm visualizer. It lets you choose a sorting algorithm, generate a list of numbers, shuffle it, and watch how the algorithm works step by step with animated bar-chart updates.

The application includes several common sorting algorithms, including:

- Bubble Sort
- Selection Sort
- Insertion Sort
- Heap Sort
- Quick Sort
- Merge Sort

It is designed as a simple educational tool for understanding how sorting algorithms compare, swap, and partition data.

## Features
- Select a sorting algorithm from a menu
- Set the list size (from 5 to 200 values)
- Shuffle the list before sorting
- Start or replay the sort with playback controls
- Highlight active comparisons and sorted elements
- View algorithm complexity information

## Requirements

- Java 21 or newer
- Maven

## Running the project

From the project root, run:

```bash
mvn javafx:run
```

This starts the JavaFX application and opens the sorting visualizer window.

If you want to build the project without launching the GUI, you can use:

```bash
mvn clean compile
```

## Project structure

- `src/main/java` — Java source files
- `src/main/java/cz/cvut/fel/sortvisual/App.java` — application entry point
- `src/main/java/cz/cvut/fel/sortvisual/view` — UI and rendering logic
- `src/main/java/cz/cvut/fel/sortvisual/model` — sorting state and data model
- `src/main/java/cz/cvut/fel/sortvisual/model/sort` — algorithm implementations
- `pom.xml` — Maven configuration
