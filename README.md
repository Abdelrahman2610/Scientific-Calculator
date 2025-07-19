# Scientific Calculator

A robust Java-based scientific calculator with a graphical user interface (GUI) built using Swing. This application supports a wide range of mathematical operations, including basic arithmetic, trigonometric functions, logarithms, factorials, and memory management, with a history feature to track calculations.

## Features

- **Basic Operations**: Addition, subtraction, multiplication, division, modulus
- **Scientific Functions**: Sine, cosine, tangent, logarithm (base 10), natural logarithm, exponentiation, square, reciprocal, square root, factorial, absolute value
- **Special Constants**: π (pi)
- **Memory Functions**: Add to memory (M+), subtract from memory (M-), recall memory (MR), clear memory (MC)
- **Calculation History**: Displays the last five calculations in a scrollable text area
- **Toggle ON/OFF**: Enable or disable the calculator via radio buttons
- **Error Handling**: Manages invalid inputs (e.g., division by zero) with clear error messages
- **User-Friendly Interface**: Customizable button sizes, colors, and fonts

## Screenshots

![Calculator Interface](docs/screenshots/Scientific Calculator Preview.png)

## Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher
- **Integrated Development Environment (IDE)**: Recommended (e.g., IntelliJ IDEA, Eclipse, or VS Code with Java Extension Pack)
- **Operating System**: Windows, macOS, or Linux

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Abdelrahman2610/scientific-calculator.git
   ```

2. **Navigate to the Project Directory**:
   ```bash
   cd scientific-calculator
   ```

3. **Compile the Project**:
   ```bash
   javac -d bin src/com/calculator/ScientificCalculator.java src/com/calculator/core/CalculatorEngine.java src/com/calculator/core/Operations.java src/com/calculator/gui/ButtonConfig.java src/com/calculator/gui/CalculatorGUI.java
   ```

4. **Run the Application**:
   ```bash
   java -cp bin com.calculator.ScientificCalculator
   ```

## Usage

- **Launch the Calculator**: Execute the compiled program to display the GUI.
- **Input Operations**: Click number buttons (0-9, π) and operation buttons (+, -, x, /, etc.) to build expressions.
- **Perform Calculations**:
  - Binary operations: Enter first number, select operation (e.g., +), enter second number, press =.
  - Unary operations: Enter a number, press a function button (e.g., sin, √).
- **Memory Functions**:
  - `M+`: Add the current value to memory.
  - `M-`: Subtract the current value from memory.
  - `MR`: Recall the memory value to the display.
  - `MC`: Clear the memory.
- **History**: View recent calculations in the history area below the input field.
- **Toggle ON/OFF**: Use the radio buttons to enable or disable the calculator.
- **Clearing**: Press `C` to reset the input or `DEL` to remove the last character.
- **Error Handling**: Invalid operations (e.g., 1/0) display "Error" with a message.

## Project Structure

```
scientific-calculator/
├── src/
│   └── com/calculator/
│       ├── core/
│       │   ├── CalculatorEngine.java  # Core calculation logic
│       │   └── Operation.java        # Enum for supported operations
│       ├── gui/
│       │   ├── ButtonConfig.java     # Button configuration settings
│       │   └── CalculatorGUI.java    # GUI implementation
│       └── ScientificCalculator.java # Main entry point
├── bin/                              # Compiled .class files
├── docs/
│   └── screenshots/                  # Screenshots (e.g., Scientific Calculator Preview.png)
├── .gitignore                        # Git ignore file
└── README.md                         # This file
```

## Contributing

Contributions are welcome to enhance this project! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes and commit (`git commit -m 'Add your feature'`).
4. Push to the branch (`git push origin feature/your-feature`).


## Acknowledgments

- Inspired by the need for a customizable scientific calculator.
- Built with Java Swing and GridBagLayout for a responsive UI.
- Thanks to the Java community for robust libraries and documentation.
```
