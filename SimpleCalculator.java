package Javaproject.github;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleCalculator extends JFrame implements ActionListener{

    private JLabel header;
    private JTextField resulttxt;
    private JButton[] numbers = new JButton[10];
    private JButton plus, minus, multi, divide, equals, clear;
    private String operator = "";
    private String current = "";
    private double num1, num2;

    public SimpleCalculator(){
        setTitle("Simple Calculator 101");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        header = new JLabel("Simple Calculator 101");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        resulttxt = new JTextField(20);
        resulttxt.setEditable(false);

       for(int i = 0; i < 10; i++){
        numbers[i] = new JButton(String.valueOf(i));
        numbers[i].setFont(new Font("Arial", Font.BOLD, 15));
        numbers[i].addActionListener(this);
       }

       plus = new JButton("+");
       minus = new JButton("-");
       multi = new JButton("x");
       divide = new JButton("/");
       clear = new JButton("C");
       equals = new JButton("=");

       JButton[] operators = {plus, minus, multi, divide, clear, equals};
       for(JButton btn : operators){
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.addActionListener(this);
       }

       add(header, 0,0,7,0.0);
       add(resulttxt, 0, 1, 0, 0.0);
       //add(numbers[8], 0, 2, 1, 0.0);
       for(int i = 1; i < numbers.length; i++){
            int value = Integer.parseInt(numbers[i].getText());

            int row = value % 3;
            int col = 2;

            if(value >= 7 && value <= 9){
                add(numbers[i], row, col, 1, 0.0);
            } 
            col += 1;
            if(value >= 4 && value <= 6){
                add(numbers[i], row, col, 1, 0.0);
            } 
            col += 1;
            if(value >= 1 && value <= 3){
                add(numbers[i], row, col, 1, 0.0);
            } 
       }
       add(numbers[0], 1,5,1,0.0);
       add(multi, 3, 2, 1, 0.0);
       add(minus, 3, 3, 1, 0.0);
       add(plus, 3, 4, 1, 0.0);
       add(clear, 0, 5, 1, 0.0);
       add(divide, 2, 5,1,0.0);
       add(equals, 3, 5,1,0.0);  
  }

        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();

            if("0123456789".contains(command)){
                current += command;
                resulttxt.setText(current);
            } else if("/-x+".contains(command)){
                num1 = Double.parseDouble(current);
                operator += command;
                current = "";
            } else if(command.equals("=")){
                num2 = Double.parseDouble(current);
                double result = switch (operator){
                    case "+" -> num1 + num2;
                    case "-" -> num1 + num2;
                    case "x" -> num1 * num2;
                    case "/" -> (num2 != 0) ? num1/num2 :0;
                    default -> 0;
                };
                resulttxt.setText(String.valueOf(result));
                current = String.valueOf(result);
            } else if (command.equals("C")){
                current = "";
                resulttxt.setText("");
            }
        }

    private void add(Component comp, int x, int y, int width, double weightx){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.weightx = weightx;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(comp, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            SimpleCalculator frame = new SimpleCalculator();
            frame.setVisible(true);
        });
    }
}