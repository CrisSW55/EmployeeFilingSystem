import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;


public class Main{
    public static void main(String[] args) throws SQLException {
        //Obtains the monitors dimensions in pixels
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Setup the frame
        JFrame frame = new JFrame("Employee Filing System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640,480);
        //Centers the frame or window on the current monitor
        frame.setLocation((windowSize.width/2)-frame.getWidth()/2,(windowSize.height/2)-frame.getHeight()/2);
        frame.setLayout(new BorderLayout());
        //Create J panel and it's labels!!!
        JPanel panel0 = new JPanel();
        //Added break lines
        JLabel headerLabel = new JLabel("<html>Welcome new employee,<br/> we are excited you joining our team!</html>");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel0.add(headerLabel);


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Please fill out employee form below: ");
        label.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label,gbc);

        JLabel label2 = new JLabel("first name: ");
        label2.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(label2,gbc);

        JTextField text = new JTextField("",15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(text,gbc);

        JLabel label3 = new JLabel("last name: ");
        label3.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(label3,gbc);

        JTextField text2 = new JTextField("",15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(text2,gbc);

        JLabel label4 = new JLabel("email address: ");
        label4.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(label4,gbc);

        JTextField text3 = new JTextField("",15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(text3,gbc);

        JLabel label5 = new JLabel("hourly pay: ");
        label5.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(label5,gbc);

        JTextField text4 = new JTextField("",15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(text4,gbc);

        JLabel label6 = new JLabel("job title: ");
        label6.setFont(new Font("Serif ", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(label6,gbc);

        JTextField text5 = new JTextField("",15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(text5,gbc);

        JButton button = new JButton("Submit");
        button.addActionListener(e -> {
            if(!text.getText().isEmpty() && !text2.getText().isEmpty()
                    && !text3.getText().isEmpty() &&
            !text4.getText().isEmpty() && !text5.getText().isEmpty() ){
                System.out.println("first name: " + text.getText());
                System.out.println("last name: " + text2.getText());
                System.out.println("email address: " + text3.getText());
                System.out.println("hourly pay: " + text4.getText());
                System.out.println("job title: " + text5.getText());
                text.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");
                text5.setText("");
            }

        });
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(button,gbc);

        frame.add(panel0,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);

//        Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/mydb",
//                "user",
//                "");
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
//            String space = " | ";
//            System.out.println("employee_id" + space +
//                    "first_name"  + space +
//                    "last_name"  + space +
//                    "hourly_pay"  + space +
//                    "hire_date" );
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("employee_id") + space +
//                                   resultSet.getString("first_name")  + space +
//                                    resultSet.getString("last_name")  + space +
//                                    resultSet.getString("hourly_pay")  + space +
//                                    resultSet.getString("hire_date") );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }



}