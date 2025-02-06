import javax.swing.*;
import java.awt.event.ActionEvent; import java.awt.event.ActionListener; import java.util.ArrayList;
import java.util.List;


public class HealthAppGUI { private JFrame frame;
private JComboBox<String> userComboBox;
private JTextField nameField, ageField, genderField, conditionsField; private JComboBox<String> surveyComboBox;
private JTextArea resultTextArea;


private List<User> users = new ArrayList<>();


private Connection;
private static final String DB_URL = "jdbc:mysql://your_database_host:port/database_name"; private static final String DB_USER = "username";
private static final String DB_PASSWORD = "password";




public HealthAppGUI() { initialize(); initializeDatabaseConnection();
}
 
private void initializeDatabaseConnection() { try {
connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
} catch (SQLException e) { e.printStackTrace();
}
}


private void createUser() {
String name = nameField.getText();
int age = Integer.parseInt(ageField.getText()); String gender = genderField.getText();
String conditions = conditionsField.getText();


User = new User(name, age, gender, conditions); users.add(user); userComboBox.addItem(user.getName()); clearFields();

// Insert user data into the database try {
String query = "INSERT INTO users (name, age, gender, health_conditions) VALUES (?,
?, ?, ?)";
PreparedStatement = connection.prepareStatement(query); preparedStatement.setString(1, name); preparedStatement.setInt(2, age); preparedStatement.setString(3, gender); preparedStatement.setString(4, conditions); preparedStatement.executeUpdate();
 
} catch (SQLException e) { e.printStackTrace();
}
}




private void initialize() {
frame = new JFrame("Health Axis"); frame.setBounds(100, 100, 600, 400);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.getContentPane().setLayout(null);

JLabel lblSelectUser = new JLabel("Select User:"); lblSelectUser.setBounds(10, 10, 80, 20); frame.getContentPane().add(lblSelectUser);

userComboBox = new JComboBox<>(); userComboBox.setBounds(100, 10, 150, 20); frame.getContentPane().add(userComboBox);

JButton btnNewUser = new JButton("New User"); btnNewUser.setBounds(260, 10, 100, 25); frame.getContentPane().add(btnNewUser);

btnNewUser.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
createUser();
}
});
 

JLabel lblName = new JLabel("Name:"); lblName.setBounds(10, 40, 80, 20); frame.getContentPane().add(lblName);

nameField = new JTextField(); nameField.setBounds(100, 40, 150, 20); frame.getContentPane().add(nameField); nameField.setColumns(10);

JLabel lblAge = new JLabel("Age:"); lblAge.setBounds(10, 70, 80, 20); frame.getContentPane().add(lblAge);

ageField = new JTextField(); ageField.setBounds(100, 70, 150, 20); frame.getContentPane().add(ageField); ageField.setColumns(10);

JLabel lblGender = new JLabel("Gender:"); lblGender.setBounds(10, 100, 80, 20); frame.getContentPane().add(lblGender);

genderField = new JTextField(); genderField.setBounds(100, 100, 150, 20); frame.getContentPane().add(genderField); genderField.setColumns(10);

JLabel lblConditions = new JLabel("Health Conditions:");
 
lblConditions.setBounds(10, 130, 120, 20); frame.getContentPane().add(lblConditions);

conditionsField = new JTextField(); conditionsField.setBounds(140, 130, 150, 20); frame.getContentPane().add(conditionsField); conditionsField.setColumns(10);

JLabel lblSurvey = new JLabel("Select Survey:"); lblSurvey.setBounds(10, 160, 100, 20); frame.getContentPane().add(lblSurvey);

String[] surveyOptions = { "Mild", "Critical" }; surveyComboBox = new JComboBox<>(surveyOptions); surveyComboBox.setBounds(140, 160, 150, 20); frame.getContentPane().add(surveyComboBox);

JButton btnSubmitSurvey = new JButton("Submit Survey"); btnSubmitSurvey.setBounds(10, 190, 150, 25); frame.getContentPane().add(btnSubmitSurvey);

btnSubmitSurvey.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
conductSurvey();
}
});


resultTextArea = new JTextArea(); resultTextArea.setEditable(false);
 
resultTextArea.setBounds(300, 40, 280, 275); frame.getContentPane().add(resultTextArea);

frame.setVisible(true);
}


private void createUser() {
String name = nameField.getText();
int age = Integer.parseInt(ageField.getText()); String gender = genderField.getText();
String conditions = conditionsField.getText();


User = new User(name, age, gender, conditions); users.add(user); userComboBox.addItem(user.getName()); clearFields();
}


private void clearFields() { nameField.setText(""); ageField.setText(""); genderField.setText(""); conditionsField.setText("");
}


private void conductSurvey() {
int selectedUserIndex = userComboBox.getSelectedIndex(); if (selectedUserIndex == -1) {
JOptionPane.showMessageDialog(frame, "Please select a user.");
 
return;
}


User selectedUser = users.get(selectedUserIndex);
String selectedSurvey = (String) surveyComboBox.getSelectedItem();
StringBuilder result = new StringBuilder("Survey Result for " + selectedUser.getName() + ":\n");


if (selectedSurvey.equals("Mild")) {
result.append("Your symptoms are mild. Contact our helpline for assistance.\n"); result.append("Company Helpline: 123-456-7890\n");
} else if (selectedSurvey.equals("Critical")) {
result.append("Your symptoms are critical. Here are affiliated hospitals and their numbers:\n");
result.append("Hospital A: 111-111-1111\n"); result.append("Hospital B: 222-222-2222\n"); result.append("Hospital C: 333-333-3333\n");
}


resultTextArea.setText(result.toString());
}




public static void main(String[] args) { SwingUtilities.invokeLater(new Runnable() {
public void run() {
new HealthAppGUI();
}
});
 
}
}


class User {
private String name;


public User(String name, int age, String gender, String healthConditions) { this.name = name;
}


public String getName() { return name;
}
}
