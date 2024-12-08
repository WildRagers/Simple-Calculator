import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener{
        private JTextField display; // The display for the calculator, this will display the numbers, operators, etc
        private String operator;
        private double firstNumber;
        private boolean startNewNumber;

        public Calculator()
        {
            setTitle("Calculator");
            setSize(400,600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Initiializing our variables
            operator = "";
            firstNumber = 0;
            startNewNumber = true;

            display = new JTextField();
            display.setFont(new Font("Arial", Font.BOLD, 30));
            display.setHorizontalAlignment(JTextField.RIGHT);
            display.setEditable(false);
            add(display, BorderLayout.NORTH);

            // Adding our buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(4,4,10,10));

            String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for(String label: buttonLabels)
        {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);


        }

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
    
            if (command.matches("\\d")) { // Checking if it's a digit
                if (startNewNumber) {
                    display.setText(command);
                    startNewNumber = false;
                } else {
                    display.setText(display.getText() + command);
                }
            } else if (command.matches("[+\\-*/]")) { // Checking if it's an operator
                operator = command;
                firstNumber = Double.parseDouble(display.getText());
                startNewNumber = true;
            } else if (command.equals("=")) { // If it's equals
                double secondNumber = Double.parseDouble(display.getText());
                double result = 0;
    
                switch (operator) {
                    case "+" -> result = firstNumber + secondNumber;
                    case "-" -> result = firstNumber - secondNumber;
                    case "*" -> result = firstNumber * secondNumber;
                    case "/" -> result = firstNumber / secondNumber;
                }
    
                display.setText(String.valueOf(result));
                startNewNumber = true;
            } else if (command.equals("C")) { // Checking if user presses clear button
                display.setText("");
                firstNumber = 0;
                operator = "";
                startNewNumber = true;
            }
        }
    
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                Calculator calculator = new Calculator();
                calculator.setVisible(true);
            });

        }
}