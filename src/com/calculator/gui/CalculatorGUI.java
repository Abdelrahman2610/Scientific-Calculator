package com.calculator.gui;

import com.calculator.core.CalculatorEngine;
import com.calculator.core.Operations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the GUI for the scientific calculator.
 */
public class CalculatorGUI extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 510;
    private static final int FRAME_HEIGHT = 850;

    private final CalculatorEngine engine;
    private final JTextField textField;
    private final JLabel label;
    private final JRadioButton onRadioButton;
    private final JRadioButton offRadioButton;
    private final JTextArea historyArea;
    private final Map<String, JButton> buttons;
    private double number;
    private double memory;
    private Operations currentOperation;
    private final List<String> history;

    public CalculatorGUI() {
        engine = new CalculatorEngine();
        buttons = new HashMap<>();
        textField = new JTextField();
        label = new JLabel();
        onRadioButton = new JRadioButton("ON");
        offRadioButton = new JRadioButton("OFF");
        historyArea = new JTextArea();
        history = new ArrayList<>();
        memory = 0.0;
        prepareGUI();
        addComponents();
        addActionEvents();
    }

    private void prepareGUI() {
        setTitle("Scientific Calculator");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(label, gbc);

        // Text Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        textField.setFont(ButtonConfig.TEXT_FIELD_FONT);
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(textField, gbc);

        // History Area
        gbc.gridy = 2;
        historyArea.setFont(new Font("Arial", Font.PLAIN, 12));
        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        add(scrollPane, gbc);

        // Radio Buttons
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        onRadioButton.setFont(ButtonConfig.SMALL_BUTTON_FONT);
        onRadioButton.setSelected(true);
        add(onRadioButton, gbc);

        gbc.gridx = 1;
        offRadioButton.setFont(ButtonConfig.SMALL_BUTTON_FONT);
        add(offRadioButton, gbc);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(onRadioButton);
        buttonGroup.add(offRadioButton);

        // Button Configurations
        ButtonConfig[] configs = {
            new ButtonConfig("C", 0, 4, ButtonConfig.SMALL_BUTTON_FONT, Color.RED, Color.WHITE),
            new ButtonConfig("DEL", 1, 4, ButtonConfig.SMALL_BUTTON_FONT, Color.RED, Color.WHITE),
            new ButtonConfig("10^x", 2, 4, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("π", 3, 4, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("x^y", 4, 4, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("|x|", 0, 5, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("x²", 1, 5, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("1/x", 2, 5, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("√", 3, 5, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("-", 4, 5, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("sin", 0, 6, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("cos", 1, 6, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("tan", 2, 6, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("x", 3, 6, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("/", 4, 6, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("7", 0, 7, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("8", 1, 7, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("9", 2, 7, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("log", 3, 7, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("+", 4, 7, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("4", 0, 8, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("5", 1, 8, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("6", 2, 8, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("ln", 3, 8, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("x!", 4, 8, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("1", 0, 9, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("2", 1, 9, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("3", 2, 9, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("e", 3, 9, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("mod", 4, 9, ButtonConfig.BUTTON_FONT),
            new ButtonConfig(".", 0, 10, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("0", 1, 10, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("+/-", 2, 10, ButtonConfig.SMALL_BUTTON_FONT),
            new ButtonConfig("M+", 3, 10, ButtonConfig.SMALL_BUTTON_FONT, Color.BLUE, Color.WHITE),
            new ButtonConfig("=", 4, 10, ButtonConfig.BUTTON_FONT),
            new ButtonConfig("M-", 0, 11, ButtonConfig.SMALL_BUTTON_FONT, Color.BLUE, Color.WHITE),
            new ButtonConfig("MR", 1, 11, ButtonConfig.SMALL_BUTTON_FONT, Color.BLUE, Color.WHITE),
            new ButtonConfig("MC", 2, 11, ButtonConfig.SMALL_BUTTON_FONT, Color.BLUE, Color.WHITE)
        };

        for (ButtonConfig config : configs) {
            createButton(config, gbc);
        }
    }

    private void createButton(ButtonConfig config, GridBagConstraints gbc) {
        JButton button = new JButton(config.getText());
        gbc.gridx = config.getX();
        gbc.gridy = config.getY();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        button.setFont(config.getFont());
        if (config.getBgColor() != null) button.setBackground(config.getBgColor());
        if (config.getFgColor() != null) button.setForeground(config.getFgColor());
        buttons.put(config.getText(), button);
        add(button, gbc);
    }

    private void addActionEvents() {
        onRadioButton.addActionListener(this);
        offRadioButton.addActionListener(this);
        buttons.values().forEach(button -> button.addActionListener(this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        try {
            if (source == onRadioButton) {
                onRadioButton.setEnabled(false);
                offRadioButton.setEnabled(true);
                textField.setEnabled(true);
                label.setEnabled(true);
                historyArea.setEnabled(true);
                buttons.values().forEach(button -> button.setEnabled(true));
            } else if (source == offRadioButton) {
                onRadioButton.setEnabled(true);
                offRadioButton.setEnabled(false);
                textField.setText("");
                label.setText("");
                historyArea.setText("");
                buttons.values().forEach(button -> button.setEnabled(false));
            } else if (source == buttons.get("C")) {
                textField.setText("");
                label.setText("");
            } else if (source == buttons.get("DEL")) {
                String text = textField.getText();
                if (!text.isEmpty()) {
                    textField.setText(text.substring(0, text.length() - 1));
                }
                label.setText("");
            } else if (isNumericButton(source)) {
                String buttonText = ((JButton) source).getText();
                textField.setText(textField.getText() + (buttonText.equals("π") ? String.valueOf(Math.PI) : buttonText));
            } else if (source == buttons.get(".")) {
                if (!textField.getText().contains(".")) {
                    textField.setText(textField.getText() + ".");
                }
            } else if (isBinaryOperator(source)) {
                if (!textField.getText().isEmpty()) {
                    number = Double.parseDouble(textField.getText());
                    String symbol = ((JButton) source).getText();
                    textField.setText("");
                    label.setText(number + " " + symbol);
                    currentOperation = getOperation(symbol);
                }
            } else if (source == buttons.get("=")) {
                if (!textField.getText().isEmpty() && currentOperation != null) {
                    double secondNumber = Double.parseDouble(textField.getText());
                    double result = engine.performBinaryOperation(number, secondNumber, currentOperation);
                    String resultStr = engine.formatResult(result);
                    textField.setText(resultStr);
                    history.add(number + " " + currentOperation + " " + secondNumber + " = " + resultStr);
                    updateHistory();
                    label.setText("");
                    currentOperation = null;
                }
            } else if (isUnaryOperator(source)) {
                if (!textField.getText().isEmpty()) {
                    String opText = ((JButton) source).getText();
                    Operations op = getOperation(opText);
                    double number = Double.parseDouble(textField.getText());
                    double result = engine.performUnaryOperation(number, op);
                    String resultStr = engine.formatResult(result);
                    textField.setText(resultStr);
                    String labelText = getOperationLabel(op, number);
                    label.setText(labelText);
                    history.add(labelText + " = " + resultStr);
                    updateHistory();
                }
            } else if (source == buttons.get("M+")) {
                if (!textField.getText().isEmpty()) {
                    memory += Double.parseDouble(textField.getText());
                    label.setText("M+ (" + engine.formatResult(memory) + ")");
                }
            } else if (source == buttons.get("M-")) {
                if (!textField.getText().isEmpty()) {
                    memory -= Double.parseDouble(textField.getText());
                    label.setText("M- (" + engine.formatResult(memory) + ")");
                }
            } else if (source == buttons.get("MR")) {
                textField.setText(engine.formatResult(memory));
                label.setText("MR (" + engine.formatResult(memory) + ")");
            } else if (source == buttons.get("MC")) {
                memory = 0.0;
                label.setText("MC (0)");
            }
        } catch (NumberFormatException ex) {
            textField.setText("Error");
            label.setText("Invalid Input");
        } catch (ArithmeticException ex) {
            textField.setText("Error");
            label.setText(ex.getMessage());
        }
    }

    private void updateHistory() {
        StringBuilder historyText = new StringBuilder();
        for (int i = Math.max(0, history.size() - 5); i < history.size(); i++) {
            historyText.append(history.get(i)).append("\n");
        }
        historyArea.setText(historyText.toString());
        historyArea.setCaretPosition(historyArea.getDocument().getLength());
    }

    private boolean isNumericButton(Object source) {
        String[] numericButtons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "π"};
        for (String btn : numericButtons) {
            if (source == buttons.get(btn)) return true;
        }
        return false;
    }

    private boolean isBinaryOperator(Object source) {
        String[] operators = {"+", "-", "x", "/", "mod", "x^y"};
        for (String op : operators) {
            if (source == buttons.get(op)) return true;
        }
        return false;
    }

    private boolean isUnaryOperator(Object source) {
        String[] unaryOps = {"sin", "cos", "tan", "log", "ln", "e", "x²", "1/x", "√", "+/-", "x!", "10^x", "|x|"};
        for (String op : unaryOps) {
            if (source == buttons.get(op)) return true;
        }
        return false;
    }

    private Operations getOperation(String text) {
        switch (text) {
            case "+": return Operations.ADD;
            case "-": return Operations.SUBTRACT;
            case "x": return Operations.MULTIPLY;
            case "/": return Operations.DIVIDE;
            case "mod": return Operations.MODULUS;
            case "x^y": return Operations.POWER;
            case "sin": return Operations.SIN;
            case "cos": return Operations.COS;
            case "tan": return Operations.TAN;
            case "log": return Operations.LOG;
            case "ln": return Operations.LN;
            case "e": return Operations.EXP;
            case "x²": return Operations.SQUARE;
            case "1/x": return Operations.RECIPROCAL;
            case "√": return Operations.SQRT;
            case "+/-": return Operations.NEGATE;
            case "x!": return Operations.FACTORIAL;
            case "π": return Operations.PI;
            case "10^x": return Operations.TEN_POWER;
            case "|x|": return Operations.ABSOLUTE;
            default: throw new IllegalArgumentException("Unknown operation: " + text);
        }
    }

    private String getOperationLabel(Operations op, double number) {
        switch (op) {
            case SIN: return "sin(" + number + ")";
            case COS: return "cos(" + number + ")";
            case TAN: return "tan(" + number + ")";
            case LOG: return "log(" + number + ")";
            case LN: return "ln(" + number + ")";
            case EXP: return "e^" + number;
            case SQUARE: return "(" + number + ")²";
            case RECIPROCAL: return "1/" + number;
            case SQRT: return "√" + number;
            case NEGATE: return "(-" + number + ")";
            case FACTORIAL: return number + "!";
            case PI: return "π";
            case TEN_POWER: return "10^" + number;
            case ABSOLUTE: return "|" + number + "|";
            default: return "";
        }
    }
}