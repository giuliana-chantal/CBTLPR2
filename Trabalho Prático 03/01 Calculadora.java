//Giuliana Ferreira Chantal CB 3013171
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora {
    private JFrame frame;
    private JTextField textField;
    private double num1, num2, resultado;
    private String operador;

    public Calculadora() {
        frame = new JFrame("Calculadora");
        textField = new JTextField();
        textField.setBounds(30, 40, 280, 30);
        
        JButton btnAdd = new JButton("+");
        JButton btnSub = new JButton("-");
        JButton btnMul = new JButton("*");
        JButton btnDiv = new JButton("/");
        JButton btnClear = new JButton("C");
        JButton btnEqual = new JButton("=");

        btnAdd.setBounds(30, 80, 50, 40);
        btnSub.setBounds(90, 80, 50, 40);
        btnMul.setBounds(150, 80, 50, 40);
        btnDiv.setBounds(210, 80, 50, 40);
        btnClear.setBounds(30, 130, 50, 40);
        btnEqual.setBounds(90, 130, 50, 40);

        frame.add(textField);
        frame.add(btnAdd);
        frame.add(btnSub);
        frame.add(btnMul);
        frame.add(btnDiv);
        frame.add(btnClear);
        frame.add(btnEqual);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(textField.getText());
                operador = "+";
                textField.setText("");
            }
        });

        btnSub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(textField.getText());
                operador = "-";
                textField.setText("");
            }
        });

        btnMul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(textField.getText());
                operador = "*";
                textField.setText("");
            }
        });

        btnDiv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(textField.getText());
                operador = "/";
                textField.setText("");
            }
        });

        btnEqual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    num2 = Double.parseDouble(textField.getText());
                    switch (operador) {
                        case "+":
                            resultado = num1 + num2;
                            break;
                        case "-":
                            resultado = num1 - num2;
                            break;
                        case "*":
                            resultado = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                throw new ArithmeticException("Divisão por zero não é permitida.");
                            }
                            resultado = num1 / num2;
                            break;
                    }
                    textField.setText(String.valueOf(resultado));
                } catch (NumberFormatException ex) {
                    textField.setText("Entrada inválida");
                } catch (ArithmeticException ex) {
                    textField.setText(ex.getMessage());
                } finally {
                    // Código que sempre será executado, se necessário
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                num1 = num2 = resultado = 0;
                operador = "";
            }
        });

        frame.setSize(350, 250);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
