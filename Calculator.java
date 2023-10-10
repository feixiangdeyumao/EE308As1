package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    /**
     * Serialization version UID
     */
    private static final long serialVersionUID = 1L;

    // North components
    private JPanel jp_north = new JPanel(); // Declare a panel
    private JTextField input_text = new JTextField(); // Input field
    private JButton c_Btn = new JButton("Empty"); // Clear button

    // Center components
    private JPanel jp_center = new JPanel(); // Declare a panel

    // Main panel
    public Calculator() throws HeadlessException {
        this.init(); // Call the init method
        this.addNorthComponent(); // Load and call the north components
        this.addCenterButton(); // Load and call the center components
    }

    // Initialization method
    public void init() {
        this.setTitle("Calculator"); // Title
        this.setSize(400, 400); // Size
        this.setLayout(new BorderLayout()); // Border layout
        this.setResizable(false); // Window cannot be resized
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the window, exit the program
    }

    // Add north components
    public void addNorthComponent() {
        this.input_text.setPreferredSize(new Dimension(230, 30));
        jp_north.add(input_text);

        this.c_Btn.setForeground(Color.blue);
        jp_north.add(c_Btn);

        c_Btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the input field when the button is clicked
                input_text.setText("");
            }
        });

        this.add(jp_north, BorderLayout.NORTH); // Place components to the north of the window
    }

    // Add center components
    public void addCenterButton() {
        String btn_text = "123+456-789*.0=/";
//      String regex = "[\\+\\-*/.+]";  \\ Escape character
        this.jp_center.setLayout(new GridLayout(4, 4)); // Declare a 4x4 grid
        for (int i = 0; i < 16; i++) {
            String temp = btn_text.substring(i, i + 1);
            JButton btn = new JButton();
            btn.setText(temp);
//          if(temp.matches(regex)) {
//              btn.setFont(new Font("Bold", Font.BOLD, 20));
//              btn.setForeground(Color.red);
//          }
            if (temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/") || temp.equals(".")
                    || temp.equals("=")) {
                btn.setFont(new Font("Bold", Font.BOLD, 20));
                btn.setForeground(Color.red);
            }
            btn.addActionListener(this);
            jp_center.add(btn);
        }
        this.add(jp_center, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true); // Display GUI components
    }

    private String firstInput = null; // Record the first entered number
    private String symbol = null; // Record the operator
    @Override
    public void actionPerformed(ActionEvent e) {
        String click = e.getActionCommand(); // Get the clicked value
        if (".0123456789".indexOf(click) != -1) {
            // Append the value from the button to the text field
            // The number clicked first is added to the back, and the number clicked later is added to the front
            this.input_text.setText(input_text.getText() + click);
            // Numbers are displayed from right to left
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);
            // JOptionPane.showMessageDialog(this, click); Display the clicked value in a window
            // click.matches("[\\+\\-*/]{1}") If it is one of the operators (+, -, *, /)
        } else if (click.matches("[\\+\\-*/]{1}")) {
            symbol = click; // Record the operator
            firstInput = this.input_text.getText(); // Record the number
            this.input_text.setText(""); // Clear the text field content
        } else if (click.equals("=")) {
            // Values before and after the operator
            Double a = Double.valueOf(firstInput); // Value before the operator
            Double b = Double.valueOf(this.input_text.getText()); // Value after the operator, the current value
            Double result = null;
            switch (symbol) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b != 0) {
                    result = a / b;
                }
                break;
            }
            this.input_text.setText(result.toString());
        }
    }
}
