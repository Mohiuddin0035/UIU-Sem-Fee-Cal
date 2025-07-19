import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UIUPaymentCalculator implements ActionListener {
    JFrame frame;
    JLabel heading, label;
    JTextField input;
    JButton waiver25, waiver50, waiver100, plain;
    JTextArea resultArea;
    JPanel topPanel, inputPanel, buttonPanel, outputPanel;

    UIUPaymentCalculator() {
        frame = new JFrame("UIU Semester Payment");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel
        topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        heading = new JLabel("UIU Payment Calculation");
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(heading);
        frame.add(topPanel, BorderLayout.NORTH);

        // Input Panel
        inputPanel = new JPanel();
        inputPanel.setBackground(Color.GRAY);
        label = new JLabel("Enter Total Amount: ");
        label.setForeground(Color.WHITE);
        input = new JTextField(15);
        inputPanel.add(label);
        inputPanel.add(input);
        frame.add(inputPanel, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GRAY);
        waiver25 = new JButton("25%");
        waiver50 = new JButton("50%");
        waiver100 = new JButton("100%");
        plain = new JButton("Plane");

        buttonPanel.add(waiver25);
        buttonPanel.add(waiver50);
        buttonPanel.add(waiver100);
        buttonPanel.add(plain);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Output Panel
        outputPanel = new JPanel();
        outputPanel.setBackground(Color.BLACK);
        resultArea = new JTextArea(7, 35);
        resultArea.setEditable(false);
        resultArea.setBackground(Color.BLACK);
        resultArea.setForeground(Color.GREEN);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputPanel.add(resultArea);
        frame.add(outputPanel, BorderLayout.EAST);

        // Action Listeners
        waiver25.addActionListener(this);
        waiver50.addActionListener(this);
        waiver100.addActionListener(this);
        plain.addActionListener(this);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(input.getText());
            double netAmount = amount;

            if (e.getSource() == waiver25) netAmount = amount * 0.75;
            else if (e.getSource() == waiver50) netAmount = amount * 0.5;
            else if (e.getSource() == waiver100) netAmount = 0;
            // plain means no change to amount

            double first = netAmount * 0.40;
            double second = netAmount * 0.30;
            double third = netAmount * 0.30;

            resultArea.setText(""); // clear previous
            resultArea.append("Your respective installment amounts\nfor the next trimester:\n");
            resultArea.append("-----------------------------------\n");
            resultArea.append(String.format("1st Installment (40%%): %.0f/-\n", first));
            resultArea.append(String.format("2nd Installment (30%%): %.0f/-\n", second));
            resultArea.append(String.format("3rd Installment (30%%): %.0f/-\n", third));
            resultArea.append("\nThank You for using our code!");
        } catch (Exception ex) {
            resultArea.setText("Invalid Input! Please enter a valid amount.");
        }
    }
}
