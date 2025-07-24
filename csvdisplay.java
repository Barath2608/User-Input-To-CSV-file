import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class UserInputToCSV {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("User Input Form");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        // Create labels and text fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();

        JButton submitButton = new JButton("Submit");

        JLabel statusLabel = new JLabel("");

        // Add components to the frame
        frame.add(nameLabel);
        frame.add(nameField);

        frame.add(ageLabel);
        frame.add(ageField);

        frame.add(submitButton);
        frame.add(statusLabel);

        // Button action
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String ageText = ageField.getText();

                try {
                    int age = Integer.parseInt(ageText); // Validate age is an integer
                    saveToCSV(name, age);
                    statusLabel.setText("Saved!");
                    nameField.setText("");
                    ageField.setText("");
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Invalid age!");
                } catch (IOException ioEx) {
                    statusLabel.setText("Error saving!");
                    ioEx.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }

    // Save data to CSV
    public static void saveToCSV(String name, int age) throws IOException {
        FileWriter csvWriter = new FileWriter("userdata.csv", true); // true = append
        csvWriter.append(name).append(",").append(String.valueOf(age)).append("\n");
        csvWriter.flush();
        csvWriter.close();
    }
}
