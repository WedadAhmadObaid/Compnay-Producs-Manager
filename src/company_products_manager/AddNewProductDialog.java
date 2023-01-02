
package company_products_manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
 
public class AddNewProductDialog implements DBInfo, ActionListener {
 
    JDialog dialog;
    JLabel image, nameLabel, priceLabel;
    JTextField nameField, priceField;
    JButton chooseImageButton, addButton;
    String selectedImagePath = null;
    DefaultTableModel model;
    JFrame frame;
 
    public AddNewProductDialog(JFrame frame, DefaultTableModel model)
    {
        dialog = new JDialog(frame);
        image = new JLabel("", JLabel.CENTER);
        chooseImageButton = new JButton("choose Image", new ImageIcon(this.getClass().getResource("/images/add-image.png")));
        nameLabel = new JLabel("Name");
        priceLabel = new JLabel("Price ( $ )");
        nameField = new JTextField();
        priceField = new JTextField();
        addButton = new JButton("Add Product", new ImageIcon(this.getClass().getResource("/images/add-product.png")));
        this.model = model;
        this.frame = frame;
 
        image.setBounds(36, 40, 224, 224);
        nameLabel.setBounds(300, 30, 80, 40);
        nameField.setBounds(300, 70, 270, 40);
        priceLabel.setBounds(300, 120, 80, 40);
        priceField.setBounds(300, 160, 270, 40);
        chooseImageButton.setBounds(298, 220, 274, 45);
        addButton.setBounds(34, 310, 538, 60);
 
        chooseImageButton.setFont(new Font("Arial", Font.BOLD, 15));
        addButton.setForeground(Color.white);
        addButton.setBackground(Color.black);
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        image.setBackground(Color.gray);
        image.setForeground(Color.white);
        image.setOpaque(true);
        image.setBorder(BorderFactory.createLineBorder(Color.gray, 1, true));
        image.setFont(new Font("Arial", Font.BOLD, 22));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameField.setFont(new Font("Arial", Font.BOLD, 15));
        nameField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceField.setFont(new Font("Arial", Font.BOLD, 15));
        priceField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
 
        dialog.add(image);
        dialog.add(chooseImageButton);
        dialog.add(nameLabel);
        dialog.add(priceLabel);
        dialog.add(nameField);
        dialog.add(priceField);
        dialog.add(addButton);
 
        chooseImageButton.addActionListener(this);
        addButton.addActionListener(this);
 
        dialog.setLayout(null);
        dialog.setSize(630, 420);
        dialog.setTitle("Add New Product");
        dialog.setModal(false);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(frame);
    }
 
 
    public void show() {
        nameField.setText("");
        priceField.setText("");
        image.setText("No image selected");
        image.setIcon(null);
        dialog.setVisible(true);
    }
 
 
    public void hide() {
        dialog.setVisible(false);
    }
 
 
    private Connection getConnection()
    {
        Connection con;
 
        try {
            con = DriverManager.getConnection(DBInfo.DB_NAME_WITH_ENCODING, DBInfo.USER, DBInfo.PASSWORD);
            return con;
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
 
 
    private void viewProductsInTheTable()
    {
        ArrayList<Product> productList = new ArrayList();
 
        Connection con = getConnection();
        String query = "SELECT * FROM products";
 
        Statement st;
        ResultSet rs;
 
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
 
            Product product;
 
            while(rs.next())
            {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        Float.parseFloat(rs.getString("price")),
                        rs.getString("add_date"),
                        rs.getBytes("image")
                );
                productList.add(product);
            }
 
            con.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
 
        model.setRowCount(0);
 
        Object[] row = new Object[4];
 
        for(int i = 0; i<productList.size(); i++)
        {
            row[0] = productList.get(i).getId();
            row[1] = productList.get(i).getName();
            row[2] = productList.get(i).getPrice();
            row[3] = productList.get(i).getAddDate();
 
            model.addRow(row);
        }
 
    }
 
 
    private boolean checkInputs()
    {
        if( nameField.getText().equals("") && priceField.getText().equals("") ) {
            JOptionPane.showMessageDialog(dialog, "Name and Price fields cannot be empty !", "", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
 
        else if(nameField.getText().equals("")) {
            JOptionPane.showMessageDialog(dialog, "Please enter product name", "", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
 
        else if(priceField.getText().equals("")) {
            JOptionPane.showMessageDialog(dialog, "Please enter product price", "", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
 
        try {
            Float.parseFloat(priceField.getText());
            return true;
        }
        catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog,
                                          "<html>"
                                          + "<b>Price should be a decimal number.<br><br>"
                                          + "Examples:<br>"
                                          + "• 40<br>"
                                          + "• 10.5</b>"
                                          + "</html>",
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
            return false;
        }
 
    }
 
 
    private ImageIcon resizeImage(byte[] pic)
    {
        ImageIcon myImage;
 
        if(pic == null)
            myImage = new ImageIcon(this.getClass().getResource("/images/no-image.jpg"));
 
        else
            myImage = new ImageIcon(pic);
 
        Image tempImage = myImage.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
 
        return new ImageIcon(tempImage);
    }
 
 
    private void chooseImage()
    {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
 
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Select a .JPG .PNG .GIF image", "jpg", "png", "gif");
        file.setFileFilter(filter);
 
        int result = file.showOpenDialog(dialog);
 
        if(result == JFileChooser.APPROVE_OPTION) {
            try {
                byte[] selectedImage = Files.readAllBytes(file.getSelectedFile().toPath());
                image.setIcon(resizeImage(selectedImage));
                image.setText("");
                selectedImagePath = file.getSelectedFile().toPath().toString();
            }
            catch(IOException ex) {
                image.setIcon(resizeImage(null));
            }
        }
    }
 
 
    private void insertProduct()
    {
        if(checkInputs()) {
            try {
                Connection con = getConnection();
                PreparedStatement ps;
 
                if (selectedImagePath == null) {
                    ps = con.prepareStatement("INSERT INTO products(name, price, add_date) values(?,?,?)");
                }
 
                else {
                    ps = con.prepareStatement("INSERT INTO products(name, price, add_date, image) values(?,?,?,?)");
 
                    InputStream img = new FileInputStream(new File(selectedImagePath));
                    ps.setBlob(4, img);
                }
 
                ps.setString(1, nameField.getText());
                ps.setString(2, priceField.getText());
 
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(new Date());
                ps.setString(3, addDate);
 
                ps.executeUpdate();
                con.close();
 
                viewProductsInTheTable();
 
                nameField.setText("");
                priceField.setText("");
                image.setText("No image selected");
                image.setIcon(null);
                selectedImagePath = null;
            }
            catch (FileNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
 
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource() == chooseImageButton)
            chooseImage();
 
        else if(e.getSource() == addButton)
            insertProduct();
    }
}