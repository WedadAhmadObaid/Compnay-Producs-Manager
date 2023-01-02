/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package company_products_manager;  
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Eng. Wedad Obaid
 */
public class Log_in extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Sin in");
    JButton registration = new JButton("Sin up");
    JCheckBox showPassword = new JCheckBox("Show Password");
    Registration_form r = new Registration_form();

    Log_in() {
        //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        r.setVisible(false);
    }
    

    public final void setLayoutManager() {
        container.setLayout(null);
    }

    public final void setLocationAndSize() {
        //Setting location and Size of each components using setBounds() method.

        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        registration.setBounds(200, 300, 100, 30);

    }

    public final void addComponentsToContainer() {
        //Adding each components to the Container
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(registration);
    }

    public final void addActionEvent() {
        //adding Action listener to components
        loginButton.addActionListener(this);
        registration.addActionListener(this);
        showPassword.addActionListener(this);
    }
    private Connection getConnection()
    {
        Connection con;
 
        try {
            con = DriverManager.getConnection(DBInfo.DB_NAME_WITH_ENCODING, DBInfo.USER, DBInfo.PASSWORD);
            return con;
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
     private void CheckValidity()
    {
 
        Connection con = getConnection();
        String query = "SELECT name,password FROM user_info";
 
        Statement st;
        ResultSet rs;
 
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            String n,p ;
            n = userTextField.getText().trim();
            p = passwordField.getText().trim();
            while(rs.next())
            { if(rs.getString(1).trim().equals(n) ){
            if(rs.getString(2).trim().equals(p)){
             User_Page a = new User_Page();
                this.setVisible(false);
            }else {
                JOptionPane.showMessageDialog(this, "Wrong password :(");
            }
          
            }/*else{
                JOptionPane.showMessageDialog(this, " Invalid Username \n"
                        + " You do not have a registered account \n"
                        + " Click on sin up button");
            }*/
            }
 
            con.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
           
            if (userText.equalsIgnoreCase("wedad") && pwdText.equalsIgnoreCase("12345")) {
                this.setVisible(false);
                Main m = new Main();
            } else {
            CheckValidity();
            }

        }
        //Coding Part of Registration button
        if (e.getSource() == registration) {
            this.setVisible(false);
          r.setVisible(true);
        }
        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }

        }
    }

    public static void main(String[] a) {
        Log_in frame = new Log_in();
        frame.setTitle("Login Form");
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
