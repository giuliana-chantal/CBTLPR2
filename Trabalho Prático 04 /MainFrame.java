//Giuliana Ferreira Chantal - CB3013171

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    private JTextField searchField;
    private JTextArea resultArea;
    private JButton searchButton, nextButton, previousButton;
    private ResultSet rs;
    private int currentIndex = -1;

    public MainFrame() {
        setTitle("Pesquisa de Alunos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        searchField = new JTextField(20);
        searchButton = new JButton("Pesquisar");
        nextButton = new JButton("Próximo");
        previousButton = new JButton("Anterior");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        add(new JLabel("Nome:"));
        add(searchField);
        add(searchButton);
        add(previousButton);
        add(nextButton);
        add(new JScrollPane(resultArea));

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudents();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigateNext();
            }
        });

        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigatePrevious();
            }
        });
    }

    private void searchStudents() {
        String searchText = searchField.getText();
        String query = "SELECT * FROM Alunos WHERE nome LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + searchText + "%");
            rs = pstmt.executeQuery();
            resultArea.setText(""); // Limpa a área de resultados
            currentIndex = -1; // Reseta o índice

            if (rs.next()) {
                currentIndex++;
                displayCurrentRecord();
            } else {
                resultArea.setText("Nenhum registro encontrado.");
            }
        } catch (SQLException ex) {
            resultArea.setText("Erro na pesquisa: " + ex.getMessage());
        }
    }

    private void navigateNext() {
        try {
            if (rs != null && rs.next()) {
                currentIndex++;
                displayCurrentRecord();
            }
        } catch (SQLException ex) {
            resultArea.setText("Erro ao navegar: " + ex.getMessage());
        }
    }

    private void navigatePrevious() {
        try {
            if (rs != null && rs.previous()) {
                currentIndex--;
                displayCurrentRecord();
            }
        } catch (SQLException ex) {
            resultArea.setText("Erro ao navegar: " + ex.getMessage());
        }
    }

    private void displayCurrentRecord() throws SQLException {
        if (rs != null) {
            String nome = rs.getString("nome");
            int idade = rs.getInt("idade");
            String curso = rs.getString("curso");
            resultArea.setText("Nome: " + nome + ", Idade: " + idade + ", Curso: " + curso);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
