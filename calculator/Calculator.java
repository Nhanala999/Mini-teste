/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author user88
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea historyTextArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator window = new Calculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Calculator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNumber1 = new JLabel("Number 1:");
        lblNumber1.setBounds(20, 20, 80, 25);
        frame.getContentPane().add(lblNumber1);

        textField1 = new JTextField();
        textField1.setBounds(100, 20, 100, 25);
        frame.getContentPane().add(textField1);

        JLabel lblNumber2 = new JLabel("Number 2:");
        lblNumber2.setBounds(20, 50, 80, 25);
        frame.getContentPane().add(lblNumber2);

        textField2 = new JTextField();
        textField2.setBounds(100, 50, 100, 25);
        frame.getContentPane().add(textField2);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(20, 80, 80, 25);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performOperation(Operation.ADD);
            }
        });
        frame.getContentPane().add(btnAdd);

        JButton btnSubtract = new JButton("Subtract");
        btnSubtract.setBounds(110, 80, 100, 25);
        btnSubtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performOperation(Operation.SUBTRACT);
            }
        });
        frame.getContentPane().add(btnSubtract);

        JButton btnMultiply = new JButton("Multiply");
        btnMultiply.setBounds(220, 80, 100, 25);
        btnMultiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performOperation(Operation.MULTIPLY);
            }
        });
        frame.getContentPane().add(btnMultiply);

        JButton btnDivide = new JButton("Divide");
        btnDivide.setBounds(20, 110, 80, 25);
        btnDivide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performOperation(Operation.DIVIDE);
            }
        });
        frame.getContentPane().add(btnDivide);

        JButton btnExponentiation = new JButton("Exponentiation");
        btnExponentiation.setBounds(110, 110, 140, 25);
        btnExponentiation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performOperation(Operation.EXPONENTIATION);
            }
        });
        frame.getContentPane().add(btnExponentiation);

        JButton btnFactorial = new JButton("Factorial");
        btnFactorial.setBounds(260, 110, 80, 25);
        btnFactorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performOperation(Operation.FACTORIAL);
            }
        });
        frame.getContentPane().add(btnFactorial);

                JButton btnClear = new JButton("Clear");
        btnClear.setBounds(20, 140, 80, 25);
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        frame.getContentPane().add(btnClear);

        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        scrollPane.setBounds(20, 170, 320, 80);
        frame.getContentPane().add(scrollPane);

        JLabel lblHistory = new JLabel("History:");
        lblHistory.setBounds(20, 150, 80, 25);
        frame.getContentPane().add(lblHistory);
    }

    private void performOperation(Operation operation) {
        try {
            double num1 = Double.parseDouble(textField1.getText());
            double num2 = Double.parseDouble(textField2.getText());
            double result = 0;

            switch (operation) {
                case ADD:
                    result = num1 + num2;
                    break;
                case SUBTRACT:
                    result = num1 - num2;
                    break;
                case MULTIPLY:
                    result = num1 * num2;
                    break;
                case DIVIDE:
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(frame, "Denominator cannot be zero.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    result = num1 / num2;
                    break;
                case EXPONENTIATION:
                    result = Math.pow(num1, num2);
                    break;
                case FACTORIAL:
                    if (num1 < 0 || num1 != Math.floor(num1)) {
                        JOptionPane.showMessageDialog(frame, "Factorial operation is only supported for non-negative integers.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    result = factorial((int) num1);
                    break;
            }

            historyTextArea.append(num1 + " " + operation.getSymbol() + " " + num2 + " = " + result + "\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
    }

    private int factorial(int n) {
        if (n == 0)
            return 1;
        else
            return n * factorial(n - 1);
    }

    private enum Operation {
        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        EXPONENTIATION("^"),
        FACTORIAL("!");

        private final String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }
}

