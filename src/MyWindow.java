import com.sun.jdi.IntegerType;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.function.IntBinaryOperator;
import javax.swing.*;
import org.w3c.dom.events.EventException;

public class MyWindow extends JFrame implements ActionListener {

    private JButton buttonMinus;
    private JButton buttonPlus;
    private JButton buttonMultiply;
    private JButton buttonDivision;
    private JButton buttonEnter;
    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JLabel windowOut;
    private int nowPos = 0;

    public MyWindow() {
        super(" Че зыришь? ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null); 

        button0 = new JButton("0");
        button0.setBounds(150, 425, 100, 75); 
        button0.setFont(new Font("Arial", Font.BOLD, 36));

        button1 = new JButton("1");
        button1.setBounds(50, 200, 100, 75); 
        button1.setFont(new Font("Arial", Font.BOLD, 36));

        button2 = new JButton("2");
        button2.setBounds(150, 200, 100, 75); 
        button2.setFont(new Font("Arial", Font.BOLD, 36));

        button3 = new JButton("3");
        button3.setBounds(250, 200, 100, 75); 
        button3.setFont(new Font("Arial", Font.BOLD, 36));

        button4 = new JButton("4");
        button4.setBounds(50, 275, 100, 75); 
        button4.setFont(new Font("Arial", Font.BOLD, 36));

        button5 = new JButton("5");
        button5.setBounds(150, 275, 100, 75); 
        button5.setFont(new Font("Arial", Font.BOLD, 36));

        button6 = new JButton("6");
        button6.setBounds(250, 275, 100, 75); 
        button6.setFont(new Font("Arial", Font.BOLD, 36));

        button7 = new JButton("7");
        button7.setBounds(50, 350, 100, 75); 
        button7.setFont(new Font("Arial", Font.BOLD, 36));

        button8 = new JButton("8");
        button8.setBounds(150, 350, 100, 75); 
        button8.setFont(new Font("Arial", Font.BOLD, 36));

        button9 = new JButton("9");
        button9.setBounds(250, 350, 100, 75); 
        button9.setFont(new Font("Arial", Font.BOLD, 36));

        buttonMinus = new JButton("-");
        buttonMinus.setBounds(350, 200, 100, 75); 
        buttonMinus.setFont(new Font("Arial", Font.BOLD, 36));

        buttonPlus = new JButton("+");
        buttonPlus.setBounds(350, 275, 100, 75); 
        buttonPlus.setFont(new Font("Arial", Font.BOLD, 36));

        buttonMultiply = new JButton("*");
        buttonMultiply.setBounds(350, 350, 100, 75); 
        buttonMultiply.setFont(new Font("Arial", Font.BOLD, 36));

        buttonDivision = new JButton(":");
        buttonDivision.setBounds(350, 425, 100, 75); 
        buttonDivision.setFont(new Font("Arial", Font.BOLD, 36));

        buttonEnter = new JButton("enter");
        buttonEnter.setBounds(350, 520, 100, 75); 
        buttonEnter.setFont(new Font("Arial", Font.BOLD, 16));
        
        windowOut = new JLabel("", windowOut.CENTER);
        windowOut.setBounds(100, 30, 300, 100); 
        windowOut.setOpaque(true);
        windowOut.setBackground(Color.LIGHT_GRAY); 
        windowOut.setForeground(Color.BLACK); 
        windowOut.setFont(new Font("Arial", Font.BOLD, 16)); 

        setLayout(null);
        add(button0);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(button7);
        add(button8);
        add(button9);
        add(buttonMinus);
        add(buttonPlus);
        add(buttonMultiply);
        add(buttonDivision);
        add(buttonEnter);
        add(windowOut);
        
        button0.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        buttonMinus.addActionListener(this);
        buttonPlus.addActionListener(this);
        buttonMultiply.addActionListener(this);
        buttonDivision.addActionListener(this);
        buttonEnter.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String buttonText = ((JButton) e.getSource()).getText(); 
        switch (buttonText) 
        {
            case "enter": 
            try
            {
                double getres = LogicCalculator(windowOut.getText());
                String result = String.valueOf(getres) ;
                if (getres % 1 == 0)
                {
                    windowOut.setText(" " + String.format("%.0f",getres));
                }
                else{
                    windowOut.setText(" " + result);
                }
                
            }
            catch (EventException ex)
            {
                JOptionPane.showMessageDialog(this, "придурок? подумай головой, а не бред всякий пиши!");
                windowOut.setText(" ");
            }
            break;        
            default:
                String nowText = windowOut.getText();
                windowOut.setText(nowText+" " +buttonText);
                break;
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
      }

    private double LogicCalculator (String str)
    {
        double result = 0;
        String[] splitStr = str.split(" ");

        ArrayList<String> digit = new ArrayList<String>();
        ArrayList<String> operand = new ArrayList<String>();

        String nowStr = "";

        for (int i = 1; i< splitStr.length;i++)
        {
            System.out.println(splitStr[i]);
            if (isNumeric(splitStr[i]))
            {
                nowStr +=splitStr[i];
            }
            else
            {
                
                digit.add(nowStr);
                nowStr="";
                operand.add(splitStr[i]);
            }
        }
        digit.add(nowStr);

        String nowStep = "";
        System.out.println(operand);
        System.out.println(digit);
        int pos = 0;
        while (pos<operand.size()) 
        {
            if (operand.get(pos).equals(":") || operand.get(pos).equals("*"))
                {
                    nowStep = Operation(digit.get(pos),digit.get(pos+1),operand.get(pos));
                    digit.remove(pos);
                    digit.remove(pos);
                    digit.add(pos,nowStep);
                    operand.remove(pos);
                    nowStep = null;
                }
            pos++;
        }
        while (!operand.isEmpty()) 
        {
                    nowStep = Operation(digit.get(0),digit.get(1),operand.get(0));
                    digit.remove(0);
                    digit.remove(0);
                    digit.add(0,nowStep);
                    operand.remove(0);
                    nowStep = null;
        }
        System.out.println(operand);
        System.out.println(digit);
        return Double.parseDouble(digit.get(0));
    }

    private String Operation (String first, String second, String operator)
    {
        double result  = 0;
        switch (operator) {
            case "+":
                result = Double.parseDouble(first) + Double.parseDouble(second);
            break;
            case "-":
                result = Double.parseDouble(first) - Double.parseDouble(second);
            break;
            case "*":
                result = Double.parseDouble(first) * Double.parseDouble(second);
            break;
            case ":":
                result = Double.parseDouble(first) / Double.parseDouble(second);
            break;
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyWindow());
    }
}