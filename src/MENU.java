import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MENU {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel główny");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JButton createAccountButton = new JButton("Stwórz konto");
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton personButton = new JButton("Osoba");
                personButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, "Tworzenie konta osoby...");
                        // Tutaj można dodać kod obsługujący tworzenie konta osoby
                    }
                });

                JButton companyButton = new JButton("Firma");
                companyButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, "Tworzenie konta firmy...");
                        // Tutaj można dodać kod obsługujący tworzenie konta firmy
                    }
                });

                createAccountButton.setVisible(false); // Ukrycie przycisku "Stwórz konto"
                mainPanel.remove(createAccountButton); // Usunięcie przycisku z panelu

                constraints.gridx = 0;
                constraints.gridy = 0;
                mainPanel.add(personButton, constraints);

                constraints.gridx = 1;
                constraints.gridy = 0;
                mainPanel.add(companyButton, constraints);

                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        JButton adminPanelButton = new JButton("Panel admina");
        adminPanelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Otwieranie panelu administratora...");
                // Tutaj można dodać kod obsługujący panel administratora
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(createAccountButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(adminPanelButton, constraints);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
