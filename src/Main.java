import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args)  throws  SQLException{
        int new_employee_id = 0;
        int last_EmployeeId = 0;
        //Initialize keyHandler class, so to check the keystrokes
        KeyHandler kH = new KeyHandler();
        //Initialize the current monitor's or screen's size in pixels
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Initialize frame
        JFrame frame = new JFrame("Employee Filing System");
        //Initialize components inside the frame
        JLabel headerLabel = new JLabel("<html>Welcome new employee,<br/> we are excited you joining our team!</html>",SwingConstants.CENTER);
        JPanel headerPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        //Initialize GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        //Initialize all components within the formPanel
        JLabel formLabel = new JLabel("Please fill out employee form below: ");

        JLabel fNLabel = new JLabel("first name: ");
        JTextField fNText = new JTextField(15);
        //JLabel fNLabel = new JLabel("");

        JLabel lNLabel = new JLabel("last name: ");
        JTextField lNText = new JTextField("",15);

        JLabel emailLabel = new JLabel("email address: ");
        JTextField emailText = new JTextField("",15);

        JLabel hPayLabel = new JLabel("hourly pay: ");
        JTextField hPayText = new JTextField("",15);

        JLabel jTitleLabel = new JLabel("job title: ");
        JTextField jTitleText = new JTextField("",15);

        JButton submitBtn = new JButton("Submit");

        //Setup headerPanel's layout and it's components
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel, BorderLayout.NORTH);

        //Setup formPanel's layout and it's components, such as Textfield,JButton,and JLabel
        formLabel.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(formLabel,gbc);

        fNLabel.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(fNLabel,gbc);
        //Using Keyhandler and this textfield to properly update the textfield's text
        fNText.addKeyListener(kH);
        //fNText.add(textLabel);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(fNText,gbc);

        lNLabel.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lNLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(lNText,gbc);

        emailLabel.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(emailLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(emailText,gbc);

        hPayLabel.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(hPayLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(hPayText,gbc);

        jTitleLabel.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(jTitleLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(jTitleText,gbc);

        //Added an actionListener for submitBtn,
        //and resets the all Text field components within the formPanel to empty strings
        submitBtn.addActionListener(e -> {
            if(!fNText.getText().isEmpty() && !lNText.getText().isEmpty()
                    && !emailText.getText().isEmpty() &&
            !hPayText.getText().isEmpty() && !jTitleText.getText().isEmpty() ){
                System.out.println("New Employee Record:");
                System.out.println("first name: " + fNText.getText());
                System.out.println("last name: " + lNText.getText());
                System.out.println("email address: " + emailText.getText());
                System.out.println("hourly pay: " + hPayText.getText());
                System.out.println("job title: " + jTitleText.getText());
                submit_NewEmployeeRecord(fNText.getText(),lNText.getText(),emailText.getText(), Integer.parseInt(hPayText.getText()),jTitleText.getText());
                fNText.setText("");
                lNText.setText("");
                emailText.setText("");
                hPayText.setText("");
                jTitleText.setText("");
            }

        });
        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(submitBtn,gbc);

        //Applied all frame requirements, such as initial startup size and position,
        // with respect to the user's monitor's screen, seen at the center at startup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640,480);
        //Centers the frame or window on the current monitor
        frame.setLocation((windowSize.width/2)-frame.getWidth()/2,(windowSize.height/2)-frame.getHeight()/2);
        frame.setLayout(new BorderLayout());
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(formPanel,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setFocusable(true);

        //Below using the JDBC, a Java API to connect to MySQL database!
        retrieveTableEmployee();

    }

    public static void retrieveTableEmployee(){
        try {
            String url = "jdbc:mysql://localhost:3306/mydb";
            Connection connection = DriverManager.getConnection(
                    url, "yourUser", "yourPassword");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            String space = " | ";

            while (resultSet.next()) {
                System.out.println(resultSet.getString("employee_id") + space+
                        resultSet.getString("first_name")  + space +
                        resultSet.getString("last_name")  + space +
                        resultSet.getString("email_address")  + space +
                        resultSet.getString("hourly_pay")  + space +
                        resultSet.getString("job_title")  + space);


            }
            //Closing
            resultSet.close();
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void submit_NewEmployeeRecord(String first_name, String last_name,String email_address,int hourly_pay,String job_title){
        int last_EmployeeId = 0;
        int new_employee_id = 0;
        try {
            //Start by connecting the java code with mydb
            String url = "jdbc:mysql://localhost:3306/mydb";
            Connection connection = DriverManager.getConnection(url, "yourUser", "yourPassword");

            //This first statement is used to get the lastEmployeeId
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT employee_id FROM employees ORDER BY employee_id DESC LIMIT 1");

            while (resultSet.next() ) {last_EmployeeId = resultSet.getInt("employee_id");}

            //Close statement and resultStatement
            statement.close();
            resultSet.close();

            //Displays the last and new employee id
            //System.out.println("Last employee id: " + last_EmployeeId);
            new_employee_id = new_employee_id + last_EmployeeId + 1;
            //System.out.println("New employee id: " + new_employee_id);

            // Create a PreparedStatement
            String sql = "INSERT INTO employees VALUES ('  "+new_employee_id+" ',' "+first_name+" ',' "+last_name+" ',' "+email_address+" ',' "+hourly_pay+" ',' "+job_title+" ') ";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.executeUpdate(sql);

            System.out.println("Inserting new record into the table...");
            // Close the resources
            pStatement.close();
            connection.close();




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}