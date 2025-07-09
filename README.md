# 🚀 Mars Mission

A Java simulation of a Mars habitat, featuring entities such as animals, robots, rocks, and vegetation.  
This project demonstrates object-oriented programming concepts and file-based input/output.

---

## ✨ Features

- Simulates a Mars habitat with various entities
- Reads simulation input from files
- Logs habitability and simulation events
- Modular, extensible codebase

---

## 📁 Project Structure

```text
src/
  entities/                     # Entity classes (animals, robots, rocks)
  util/                         # Utility classes (file handling, menu)
  MarsHabitatApplication.java   # Main entry point
  resources/                    # Input and output files for simulation
```
## 🚀 Getting Started

### 📌 Prerequisites

- Java JDK 8 or higher

---

### ⚙️ Compilation

Open a terminal in the project root and run:

```sh
javac -d out src/**/*.java
```
This compiles all Java files and places the output in the out directory.

## ▶️ Running the Program

To run the main application, open a terminal in your project root and execute:

```sh
java -cp out MarsHabitatApplication
```
If the main class is in a package, use the fully qualified name (e.g., your.package.MarsHabitatApplication).

## 📥 Providing Input

The program may require an input file for the simulation. You can provide it in two ways:

### 1️⃣ Redirect Input from a File

Use input redirection in your terminal:

```sh
java -cp out MarsHabitatApplication < src/resources/test.in
```
### 2️⃣ Redirect Input from a File

Alternatively, run the program normally and follow the on-screen prompts to enter the path to your input file:

```text
Example: src/resources/m1.in
```

## 📤 Output

- Simulation logs and results are automatically written to output files in the `src/resources/` directory.
- Example output includes:
  - `habitability.log`
  - `*.out` files generated for each simulation run

## 📚 Resources

Example input files:
```text
src/resources/*.in
```
Example output files:
```text
src/resources/*.out
habitability.log
```
## 📈 UML

A UML diagram of the Mars Mission project is provided for better understanding of class relationships and architecture.

- **PNG File:**  
  `UML2.0.png`

- **PlantUML File:**  
  `pumlfle.puml`

