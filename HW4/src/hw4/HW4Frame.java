package hw4;


import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Random;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;


class PasswordException extends Exception {
	public PasswordException(String error) {
		super(error);
	}
}

class Minimum8CharactersRequired extends PasswordException {
	public Minimum8CharactersRequired() {
		super("Password Needs To Be 8 or More Characters");
	}
}

class UpperCaseCharacterMissing extends PasswordException {
	public UpperCaseCharacterMissing() {
		super("Password Needs a UpperCase Character");
	}
}

class LowerCaseCharacterMissing extends PasswordException {
	public LowerCaseCharacterMissing() {
		super("Password Needs a Lowercase Character");
	}
}

class SpecialCharacterMissing extends PasswordException {
	public SpecialCharacterMissing() {
		super("Password Needs a Special Character");
	}
}

class NumberCharacterMissing extends PasswordException {
	public NumberCharacterMissing() {
		super("Password Needs a Number");
	}
}

public class HW4Frame extends JFrame {
	public HW4Frame() {
	}

	private JPanel contentPane;
	private JButton jButton;

	public static void main(String[] args) {

		// Creating Main Page
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.getContentPane().setLayout(null);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.red);
		titlePanel.setBounds(0, 0, 500, 50);

		frame.getContentPane().add(titlePanel);

		// Header Creation
		JLabel header = new JLabel();
		header.setText("Welcome To Ben's HW 4 Project. What would You Like To Test");
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setVerticalAlignment(JLabel.CENTER);

		titlePanel.add(header);

		// Panel for Login Button
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.blue);
		loginPanel.setBounds(0, 50, 250, 50);
		frame.getContentPane().add(loginPanel);

		JButton loginButton = new JButton();
		loginButton.setBounds(10, 30, 50, 20);
		loginButton.setText("Login");
		loginButton.addActionListener(new ActionListener() { // referenced from
																// https://stackoverflow.com/questions/21879243/how-to-create-on-click-event-for-buttons-in-swing

			@Override
			public void actionPerformed(ActionEvent e) {
				goToLoginUI();
			}
		});
		loginPanel.add(loginButton);

		
		// Panel for Signup Button
		JPanel signupPanel = new JPanel();
		signupPanel.setBackground(Color.GREEN);
		signupPanel.setBounds(250, 50, 250, 50);
		frame.getContentPane().add(signupPanel);

		JButton signupButton = new JButton();
		signupButton.setBounds(270, 30, 50, 20);
		signupButton.setText("Sign Up");
		signupButton.addActionListener(new ActionListener() { // referenced from
																// https://stackoverflow.com/questions/21879243/how-to-create-on-click-event-for-buttons-in-swing

			@Override
			public void actionPerformed(ActionEvent e) {
				goToSignupUI();
			}
		});
		signupPanel.add(signupButton);

	}

	// Runs when the user clicks on the login button
	public static void goToLoginUI() {
		JFrame loginFrame = new JFrame("Login");
		final JLabel label = new JLabel();
		label.setBounds(20, 150, 500, 50);

		final JTextField loginUsername = new JTextField();
		loginUsername.setBounds(100, 20, 100, 30);

		final JPasswordField loginPassword = new JPasswordField();
		loginPassword.setBounds(100, 75, 100, 30);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(20, 20, 80, 30);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(20, 75, 80, 30);

		final JLabel errorLabel = new JLabel();
		errorLabel.setBounds(20, 150, 500, 50);

		JButton loginButton = new JButton("Login");
		loginButton.setBounds(100, 300, 80, 30);

		loginFrame.getContentPane().add(loginPassword);
		loginFrame.getContentPane().add(usernameLabel);
		loginFrame.getContentPane().add(errorLabel);
		loginFrame.getContentPane().add(label);
		loginFrame.getContentPane().add(passwordLabel);
		loginFrame.getContentPane().add(loginButton);
		loginFrame.getContentPane().add(loginUsername);
		loginFrame.setSize(500, 500);
		loginFrame.getContentPane().setLayout(null);
		loginFrame.setVisible(true);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean hasLoggedIn = false;
				
				try {
					// Reads a line from the accounts file to see if the details of an account have been created. 
					// I used a local data storage of using files because I couldn't figure out how to include a cloud based data system into
					// this project. 
					String loginAttempt = loginUsername.getText() + " " + loginPassword.getText();
					
					BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"));
					
					String line = reader.readLine(); //referenced from https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
					
					while (line != null) {
						
						String accountDetails = line.substring(line.indexOf('|') + 1, line.length());
						System.out.println(accountDetails);
						
						if (loginAttempt.equals(accountDetails)) { // If "database" has account details, navigates the user to their home page, displaying information.
							goToUserPage(line);
							hasLoggedIn = true;
							errorLabel.setText("Login is a Success");
							
						}
						// read next line
						line = reader.readLine();
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				if (hasLoggedIn) {
					errorLabel.setText("Login is a Success");
				} else {
					errorLabel.setText("Incorrect login or password info");
				}

			}
		});

	}
	// Runs when the user successfully logs into their account, displaying all their information.
	public static void goToUserPage(String accountDetails) { 
		
		
		JFrame frame = new JFrame("Userpage");
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.getContentPane().setLayout(null);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.red);
		titlePanel.setBounds(0, 0, 500, 50);

		frame.getContentPane().add(titlePanel);

		JLabel header = new JLabel();
		header.setText("Welcome to your userpage");
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setVerticalAlignment(JLabel.CENTER);
		
		JPanel informationPanel = new JPanel();
		informationPanel.setBackground(Color.blue);
		informationPanel.setBounds(0, 50, 500, 50);
		frame.getContentPane().add(informationPanel);
		
		
		JLabel allInformation = new JLabel();
		
		allInformation.setBounds(20, 80, 500, 50);
		
		String fullnameText = accountDetails.substring(0, accountDetails.indexOf('|'));
		
		allInformation.setBounds(20, 80, 250, 50);
		String usernameAndPassword = accountDetails.substring(fullnameText.length() + 1, accountDetails.length());
		
		String usernameText = usernameAndPassword.substring(0, 8);
		String passwordText = usernameAndPassword.substring(usernameText.length(), usernameAndPassword.length());
		
		allInformation.setText("Username: " + usernameText + " Password: " + passwordText + " Full Name: " + fullnameText);
		informationPanel.add(allInformation);

		
		titlePanel.add(header);
		

	}
	
	// Runs when the user clicks on the signup button.
	public static void goToSignupUI() {
		JFrame signupFrame = new JFrame("Signup");

		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(20, 20, 80, 30);

		final JTextField firstNameInput = new JTextField();
		firstNameInput.setBounds(100, 20, 100, 30);

		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(20, 60, 80, 30);

		final JTextField lastNameInput = new JTextField();
		lastNameInput.setBounds(100, 60, 100, 30);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(20, 100, 80, 30);

		final JPasswordField signupPassword = new JPasswordField();
		signupPassword.setBounds(100, 100, 100, 30);

		JLabel resultLabel = new JLabel();
		resultLabel.setBounds(20, 150, 500, 50);

		JButton signupButton = new JButton("Sign Up");
		signupButton.setBounds(100, 300, 80, 30);

		signupFrame.getContentPane().add(signupPassword);
		signupFrame.getContentPane().add(firstNameLabel);
		signupFrame.getContentPane().add(lastNameLabel);
		signupFrame.getContentPane().add(passwordLabel);
		signupFrame.getContentPane().add(resultLabel);
		signupFrame.getContentPane().add(signupButton);
		signupFrame.getContentPane().add(firstNameInput);
		signupFrame.getContentPane().add(lastNameInput);
		signupFrame.setSize(500, 500);
		signupFrame.getContentPane().setLayout(null);
		signupFrame.setVisible(true);
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Random generator = new Random();
				// Random 4 digit integer referenced from
				// https://stackoverflow.com/questions/14844477/generate-random-number-between-0000-and-9999

				try {
					String username = firstNameInput.getText().substring(0, 1).toUpperCase()
							+ lastNameInput.getText().substring(0, 1).toUpperCase() + "-"
							+ String.format("%04d", generator.nextInt(9999));

					String password = signupPassword.getText();

					checkPassword(password);
					String accountInfo = firstNameInput.getText() + " " + lastNameInput.getText() + "|" + username + " " + password;
					
					FileWriter fstream = new FileWriter("accounts.txt", true); // referenced from https://www.roseindia.net/java/javafile/How-to-Write-to-a-File-in-Java-without-overwriting.shtml
					BufferedWriter out = new BufferedWriter(fstream);
					
					BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"));
					String line = reader.readLine();
					if (line == null) {
						out.write(accountInfo);
						out.close();
					} else {
						out.write("\n" + accountInfo);
						out.close();
					}
					
					resultLabel.setText("Success. Your Username is: " + username + " Close UI or Create A New Account!");
					
				} catch (UpperCaseCharacterMissing e1) {
					e1.printStackTrace();
					resultLabel.setText("UpperCase character is missing in password");
				} catch (LowerCaseCharacterMissing e1) {
					e1.printStackTrace();
					resultLabel.setText("LowerCase character is missing in password");
				} catch (SpecialCharacterMissing e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					resultLabel.setText("Special character is missing in password");
				} catch (NumberCharacterMissing e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					resultLabel.setText("Number character is missing in password");
				} catch (Minimum8CharactersRequired e1) {
					resultLabel.setText("Password must be at least 8 characters long");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	// Method to check if the password the user inputs, meets the requirements.
	private static void checkPassword(String password) throws UpperCaseCharacterMissing, LowerCaseCharacterMissing,
			SpecialCharacterMissing, NumberCharacterMissing, Minimum8CharactersRequired {

		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		boolean hasSpecialCharacter = false;
		boolean hasNumber = false;

		if (password.length() < 8) {
			throw new Minimum8CharactersRequired();
		}

		// Checking letter conditions.
		for (int i = 0; i < password.length(); i++) {
			char letter = password.charAt(i);

			if (Character.isUpperCase(letter)) {
				hasUpperCase = true;
			} else if (Character.isLowerCase(letter)) {
				hasLowerCase = true;
			} else if (!Character.isDigit(password.charAt(i)) && !Character.isLetter(password.charAt(i))
					&& !Character.isWhitespace(password.charAt(i))) { // referenced from
																		// https://www.geeksforgeeks.org/java-program-to-check-whether-the-string-consists-of-special-characters/#
				hasSpecialCharacter = true;
			} else if (Character.isDigit(letter)) {
				hasNumber = true;
			}

		}

		if (!hasUpperCase) {
			throw new UpperCaseCharacterMissing();
		}

		if (!hasLowerCase) {
			throw new LowerCaseCharacterMissing();
		}

		if (!hasSpecialCharacter) {
			throw new SpecialCharacterMissing();
		}

		if (!hasNumber) {
			throw new NumberCharacterMissing();
		}

	}

}
