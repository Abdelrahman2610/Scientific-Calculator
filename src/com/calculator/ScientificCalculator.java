package com.calculator;

import com.calculator.gui.CalculatorGUI;
import javax.swing.*;

/**
 * Entry point for the Scientific Calculator application.
 */
public class ScientificCalculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI().setVisible(true));
    }
}