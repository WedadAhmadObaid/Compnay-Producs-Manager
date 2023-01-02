/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company_products_manager;        
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
/**
 *
 * @author Eng. Wedad Obaid
 */

public class Registration_form extends JFrame implements ActionListener , DBInfo {
    private final Container c;
    private final JLabel title;
    private final JLabel name;
    private final JTextField tname;
    private final JLabel pass;
    private final JTextField tpass;
    private final JLabel gender;
    private final JRadioButton male;
    private final JRadioButton female;
    private final ButtonGroup gengp;
    private final JLabel dob;
    private final JComboBox date;
    private final JComboBox month;
    private final JComboBox year;
    private final JLabel add;
    private final JTextArea tadd;
    private final JCheckBox term;
    private final JButton sub;
    private final JButton log;
    private final JTextArea tout;
    private final JLabel res;
    private final JTextArea resadd;

    
    private final String dates[]
            = {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30",
                "31"};
    private final String months[]
            = {"Jan", "feb", "Mar", "Apr",
                "May", "Jun", "July", "Aug",
                "Sup", "Oct", "Nov", "Dec"};
    private final String years[]
            = {"1995", "1996", "1997", "1998",
                "1999", "2000", "2001", "2002",
                "2003", "2004", "2005", "2006",
                "2007", "2008", "2009", "2010",
                "2011", "2012", "2013", "2014",
                "2015", "2016", "2017", "2018",
                "2019"};

    // constructor, to initialize the components
    // with default values.
    public Registration_form() {
        setTitle("Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);

        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setSize(100, 20);
        pass.setLocation(100, 150);
        c.add(pass);

        tpass = new JTextField();
        tpass.setFont(new Font("Arial", Font.PLAIN, 15));
        tpass.setSize(150, 20);
        tpass.setLocation(200, 150);
        c.add(tpass);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(100, 20);
        gender.setLocation(100, 200);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setSelected(true);
        male.setSize(75, 20);
        male.setLocation(200, 200);
        c.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setSelected(false);
        female.setSize(80, 20);
        female.setLocation(275, 200);
        c.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dob = new JLabel("DOB");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(100, 20);
        dob.setLocation(100, 250);
        c.add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(200, 250);
        c.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(250, 250);
        c.add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(320, 250);
        c.add(year);

        add = new JLabel("Address");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(100, 20);
        add.setLocation(100, 300);
        c.add(add);

        tadd = new JTextArea();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(200, 75);
        tadd.setLocation(200, 300);
        tadd.setLineWrap(true);
        c.add(tadd);

        term = new JCheckBox("Accept Terms And Conditions.");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(150, 400);
        c.add(term);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);

        log = new JButton("Log in");
        log.setFont(new Font("Arial", Font.PLAIN, 15));
        log.setSize(100, 20);
        log.setLocation(270, 450);
        log.addActionListener(this);
        c.add(log);

        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);
    

        setVisible(true);
        
    }
     private Connection getConnection() {
        Connection con;

        try {
            con = DriverManager.getConnection(DBInfo.DB_NAME_WITH_ENCODING, DBInfo.USER, DBInfo.PASSWORD);
            return con;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(c, ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
      private boolean checkInfo() {
        if (tname.getText().equals("") && tpass.getText().equals("")) {
            JOptionPane.showMessageDialog(c, "Name and Password fields cannot be empty !", "", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else if (tname.getText().equals("")) {
            JOptionPane.showMessageDialog(c, "Please enter your name", "", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else if (tpass.getText().equals("")) {
            JOptionPane.showMessageDialog(c, "Please enter password", "", JOptionPane.PLAIN_MESSAGE);
            return false;
        } 
 try {
            Float.parseFloat(tpass.getText());
            return true;
        }
        catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(c,
                                          "<html>"
                                          + "<b>Password should be a decimal number.<br><br>"
                                          + "Examples:<br>"
                                          + "• 40<br>"
                                          + "• 10.5</b>"
                                          + "</html>",
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
            return false;
        }                }
       private void Reg()  {
        if (checkInfo()) {
              try {
                Connection con = getConnection();
                PreparedStatement ps;

                  if (tadd == null) {
                    ps = con.prepareStatement("INSERT INTO user_info(name, password, add_date) values(?,?,?)");
                } else {
                    ps = con.prepareStatement("INSERT INTO user_info(name, password, adress, add_date) values(?,?,?,?)");

                }

                ps.setString(1, tname.getText());
                ps.setString(2, tpass.getText()); 
                ps.setString(3, tadd.getText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(new java.util.Date());
                ps.setString(4, addDate);
               

                ps.executeUpdate();
                con.close();
                tname.setText("");
                tpass.setText("");
                tadd.setText("");
                
            }catch ( SQLException ex) {
                JOptionPane.showMessageDialog(c, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }}
       
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            if (term.isSelected()) {
           
                String data1;

                String data = "Name : "
                        + tname.getText() + "\n"
                        + "Password : "
                        + tpass.getText() + "\n";
                if (male.isSelected()) {
                    data1 = "Gender : Male"
                            + "\n";
                } else {
                    data1 = "Gender : Female"
                            + "\n";
                }
                String data2
                        = "DOB : "
                        + (String) date.getSelectedItem()
                        + "/" + (String) month.getSelectedItem()
                        + "/" + (String) year.getSelectedItem()
                        + "\n";

                String data3 = "Address : " + tadd.getText();
                tout.setText(data + data1 + data2 + data3);
                tout.setEditable(false);
                res.setText("Registration Successfully..");
               
                    Reg();
                
            } else {
                tout.setText("");
                resadd.setText("");
                res.setText("Please accept the"
                        + " terms & conditions..");
            }
        } else if (e.getSource() == log) {
            Log_in w = new Log_in();
            w.setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) throws Exception {
        Registration_form f = new Registration_form();

    }

    
}
