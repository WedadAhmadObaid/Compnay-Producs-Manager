
package company_products_manager;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;  
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
 
public class Main extends JFrame implements DBInfo, ActionListener {
 
    JPanel panel;
    JLabel image, idLabel, nameLabel, priceLabel, dateLabel, searchLabel, moveFastLabel;
    JTextField idField, nameField, priceField, searchField;
    JDateChooser dateField;
    JButton updateImageButton, insertButton, updateButton, deleteButton ,exitButton,
            selectFirstButton, selectNextButton, selectPreviousButton, selectLastButton;
    JTable table;
    JScrollPane tableScroller;
    DefaultTableModel model;
 
    String currentImagePath = null;
 
    AddNewProductDialog addProductDialog;
 
    public Main() {
        createAndShowGUI();
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
 
    private void createAndShowGUI() {
 
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) { }
 
        panel = new JPanel(null);
        model = new DefaultTableModel();
        table = new JTable(model);
        tableScroller = new JScrollPane(table);
        image = new JLabel();
        idLabel = new JLabel("ID");
        nameLabel = new JLabel("Name");
        priceLabel = new JLabel("Price");
        dateLabel = new JLabel("Date");
        searchLabel = new JLabel();
        moveFastLabel = new JLabel("Move Fast", JLabel.CENTER);
        idField = new JTextField();
        nameField = new JTextField();
        priceField = new JTextField();
        dateField = new JDateChooser();
        searchField = new JTextField();
        updateImageButton = new JButton("Update Image");
        insertButton = new JButton("Add New", new ImageIcon(this.getClass().getResource("/images/insert.png")));
        updateButton = new JButton("Update", new ImageIcon(this.getClass().getResource("/images/update.png")));
        deleteButton = new JButton("Delete", new ImageIcon(this.getClass().getResource("/images/delete.png")));
        searchLabel = new JLabel("Search");
        selectFirstButton = new JButton("First", new ImageIcon(this.getClass().getResource("/images/first.png")));
        selectLastButton = new JButton("Last", new ImageIcon(this.getClass().getResource("/images/last.png")));
        selectNextButton = new JButton("Next", new ImageIcon(this.getClass().getResource("/images/next.png")));
        selectPreviousButton = new JButton("Previous", new ImageIcon(this.getClass().getResource("/images/previous.png")));
        exitButton = new JButton("Exit", new ImageIcon(this.getClass().getResource("/images/exit.png")));
        addProductDialog = new AddNewProductDialog(this, model);
 
        image.setBounds(80, 41, 270, 250);
        updateImageButton.setBounds(150, 300, 130, 34);
        idLabel.setBounds(20, 355, 50, 40);
        idField.setBounds(80, 355, 270, 40);
        nameLabel.setBounds(20, 405, 50, 40);
        nameField.setBounds(80, 405, 270, 40);
        priceLabel.setBounds(20, 455, 50, 40);
        priceField.setBounds(80, 455, 270, 40);
        dateLabel.setBounds(20, 505, 50, 40);
        dateField.setBounds(80, 505, 270, 40);
        deleteButton.setBounds(80, 575, 130, 40);
        updateButton.setBounds(220, 575, 130, 40);
        tableScroller.setBounds(377, 40, 520, 505);
        searchField.setBounds(530, 577, 255, 36);
        searchLabel.setBounds(460, 575, 115, 40);
        insertButton.setBounds(920, 40, 130, 60);
        moveFastLabel.setBounds(890, 150, 190, 30);
        selectFirstButton.setBounds(920, 200, 130, 40);
        selectLastButton.setBounds(920, 250, 130, 40);
        selectNextButton.setBounds(920, 300, 130, 40);
        selectPreviousButton.setBounds(920, 350, 130, 40);
        exitButton.setBounds(920, 575, 130, 40);
 
        updateImageButton.setFont(new Font("Arial", Font.BOLD, 14));
        idLabel.setFont(new Font("Arial", Font.BOLD, 16));
        idField.setFont(new Font("Arial", Font.BOLD, 15));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameField.setFont(new Font("Arial", Font.BOLD, 15));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceField.setFont(new Font("Arial", Font.BOLD, 15));
        dateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dateField.setFont(new Font("Arial", Font.BOLD, 13));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
        updateButton.setFont(new Font("Arial", Font.BOLD, 16));
        insertButton.setFont(new Font("Arial", Font.BOLD, 16));
        searchField.setFont(new Font("Arial", Font.BOLD, 15));
        searchLabel.setFont(new Font("Arial", Font.BOLD, 17));
        moveFastLabel.setFont(new Font("Arial", Font.BOLD, 22));
        selectFirstButton.setFont(new Font("Arial", Font.BOLD, 16));
        selectLastButton.setFont(new Font("Arial", Font.BOLD, 16));
        selectNextButton.setFont(new Font("Arial", Font.BOLD, 16));
        selectPreviousButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
 
        image.setBorder(BorderFactory.createLineBorder(Color.gray, 1, true));
        idField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        nameField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        priceField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        searchField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
 
        idField.setEditable(false);
        idField.setBackground(new Color(240, 240, 240));
 
        dateField.setDateFormatString("yyyy-MM-dd");
        dateField.setBackground(Color.gray);
        dateField.getCalendarButton().setIcon(new ImageIcon(this.getClass().getResource("/images/calendar.png")));
        dateField.getCalendarButton().setBackground(Color.gray);
 
        table.setColumnSelectionAllowed(false);
        table.getParent().setBackground(Color.white);
        tableScroller.setViewportView(table);
 
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Price ($)");
        model.addColumn("Date Of Add");
 
        try {
            viewProductsInTheTable();
        }
        catch(Exception e) { }
 
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int index = table.getSelectedRow();
                showProduct(index);
                currentImagePath = null;
            }
        });
 
        table.addKeyListener(new KeyListener() {
 
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN )
                    showProduct(table.getSelectedRow());
            }
 
            @Override
            public void keyTyped(KeyEvent e) { }
 
            @Override
            public void keyPressed(KeyEvent e) { }
 
        });
 
        searchField.addKeyListener(new KeyListener() {
 
            @Override
            public void keyReleased(KeyEvent e) {
                search();
            }
 
            @Override
            public void keyTyped(KeyEvent e) { }
 
            @Override
            public void keyPressed(KeyEvent e) { }
 
        });
 
        updateImageButton.addActionListener(this);
        insertButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        selectFirstButton.addActionListener(this);
        selectLastButton.addActionListener(this);
        selectNextButton.addActionListener(this);
        selectPreviousButton.addActionListener(this);
        exitButton.addActionListener(this);
 
        panel.add(image);
        panel.add(updateImageButton);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(insertButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(tableScroller);
        panel.add(searchField);
        panel.add(searchLabel);
        panel.add(moveFastLabel);
        panel.add(selectFirstButton);
        panel.add(selectLastButton);
        panel.add(selectNextButton);
        panel.add(selectPreviousButton);
        panel.add(exitButton);
 
        showFirstProduct();
 
        panel.setPreferredSize(new Dimension(1070, 640));
        panel.setMinimumSize(new Dimension(1070, 640));
 
        setContentPane(new JPanel(new GridBagLayout()));
 
        add(panel);
 
        setTitle("Company Products Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        if( nameField.getText().equals("") || priceField.getText().equals("") || dateField.getDate() == null )
        {
            JOptionPane.showMessageDialog(this,
                "Product information are not updated because one or more fields are empty",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else {
            try {
                Float.parseFloat(priceField.getText());
                return true;
            }
            catch(NumberFormatException ex) {
                return false;
            }
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
 
 
    private void updateImage()
    {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
 
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Select a .JPG .PNG .GIF image", "jpg", "png", "gif");
        file.setFileFilter(filter);
 
        int result = file.showOpenDialog(this);
 
        if(result == JFileChooser.APPROVE_OPTION)
        {
            try{
                byte[] selectedImage = Files.readAllBytes(file.getSelectedFile().toPath());
                image.setIcon(resizeImage(selectedImage));
                currentImagePath = file.getSelectedFile().toPath().toString();
            }
            catch(IOException ex) {
                image.setIcon(resizeImage(null));
            }
        }
    }
 
 
    private void addNewProduct()
    {
        addProductDialog.show();
    }
 
 
    private void updateProduct()
    {
        if(checkInputs() && idField.getText() != null)
        {
            if(currentImagePath != null)
            {
                try {
                    InputStream img = new FileInputStream(new File(currentImagePath));
 
                    String query = "UPDATE products SET name = ?, price = ?, add_date = ?, image = ? WHERE id = ?";
               
                    Connection con = getConnection();
 
                    PreparedStatement ps = con.prepareStatement(query);
 
                    ps.setString(1, nameField.getText());
                    ps.setString(2, priceField.getText());
 
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(dateField.getDate());
 
                    ps.setString(3, addDate);
                    ps.setBlob(4, img);
                    ps.setInt(5, Integer.parseInt(idField.getText()));
 
                    ps.executeUpdate();
 
                    con.close();
 
                    viewProductsInTheTable();
                    JOptionPane.showMessageDialog(this, "Product information has been successfully updated");
                }
                catch(HeadlessException | FileNotFoundException | NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                try {
                    String query = "UPDATE products SET name = ?, price = ?, add_date = ? WHERE id = ?";
 
                    Connection con = getConnection();
 
                    PreparedStatement ps = con.prepareStatement(query);
 
                    ps.setString(1, nameField.getText());
                    ps.setString(2, priceField.getText());
 
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(dateField.getDate());
 
                    ps.setString(3, addDate);
                    ps.setInt(4, Integer.parseInt(idField.getText()));
 
                    ps.executeUpdate();
 
                    con.close();
 
                    viewProductsInTheTable();
                    JOptionPane.showMessageDialog(this, "Product information are successfuly updated");
                }
                catch(HeadlessException | NumberFormatException | SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
 
 
    private ArrayList<Product> getProductList()
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
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
 
        return productList;
    }
 
 
    private void showProduct(int index)
    {
        idField.setText(Integer.toString(getProductList().get(index).getId()));
        nameField.setText(getProductList().get(index).getName());
        priceField.setText(Float.toString(getProductList().get(index).getPrice()));
 
        try {
            Date addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProductList().get(index).getAddDate());
            dateField.setDate(addDate);
        }
        catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
 
        byte[] theImage = getProductList().get(index).getImage();
 
        image.setIcon(resizeImage(theImage));
    }
 
 
    private void deleteProduct()
    {
        if(table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select the product that you want to delete from the table and try again");
        }
        else {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id = ?");
 
                int id = Integer.parseInt(idField.getText());
 
                ps.setInt(1, id);
                ps.executeUpdate();
 
                con.close();
 
                int nextSelectedRowIndex = table.getSelectedRow();
                viewProductsInTheTable();
 
                if(table.getRowCount() == 1) {
                    table.setRowSelectionInterval(0, 0);
                    showProduct(0);
                }
 
                else if(table.getRowCount() > 1 && nextSelectedRowIndex < table.getRowCount()) {
                    table.setRowSelectionInterval(nextSelectedRowIndex, nextSelectedRowIndex);
                    showProduct(nextSelectedRowIndex);
                }
 
                else if(table.getRowCount() > 1 && nextSelectedRowIndex == table.getRowCount()) {
                    nextSelectedRowIndex--;
                    table.setRowSelectionInterval(nextSelectedRowIndex, nextSelectedRowIndex);
                    showProduct(nextSelectedRowIndex);
                }
 
                if(table.getRowCount() == 0) {
                    image.setIcon(null);
                    idField.setText("");
                    nameField.setText("");
                    priceField.setText("");
                    dateField.setDate(null);
                }
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
 
 
    private void showFirstProduct()
    {
        if(table.getRowCount() != 0) {
            table.setRowSelectionInterval(0, 0);
            showProduct(0);
        }
    }
 
 
    private void showLastProduct()
    {
        if(table.getRowCount() != 0) {
            table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
            showProduct(table.getRowCount()-1);
        }
    }
 
 
    private void showNextProdut()
    {
        if(table.getSelectedRow() < table.getRowCount()-1) {
            int currentSelectedRow = table.getSelectedRow()+1;
            table.setRowSelectionInterval(currentSelectedRow, currentSelectedRow);
            showProduct(currentSelectedRow);
        }
    }
 
 
    private void showPreviousProduct()
    {
        if(table.getSelectedRow() > 0) {
            int currentSelectedRow = table.getSelectedRow()-1;
            table.setRowSelectionInterval(currentSelectedRow, currentSelectedRow);
            showProduct(currentSelectedRow);
        }
    }
 
 
    private void search()
    {
        String keyword = searchField.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        table.setRowSorter(tr);
 
        tr.setRowFilter(RowFilter.regexFilter(keyword));
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
 
        if (e.getSource() == updateImageButton){
            updateImage();
        }
 
        else if (e.getSource() == insertButton){
            addNewProduct();
        }
 
        else if (e.getSource() == updateButton){
            updateProduct();
        }
 
        else if (e.getSource() == deleteButton){
            deleteProduct();
        }
 
        else if (e.getSource() == selectFirstButton){
            showFirstProduct();
        }
 
        else if (e.getSource() == selectLastButton){
            showLastProduct();
        }
 
        else if (e.getSource() == selectNextButton){
            showNextProdut();
        }
 
        else if (e.getSource() == selectPreviousButton){
            showPreviousProduct();
        }
 
        else if (e.getSource() == exitButton){
            System.exit(0);
        }
 
    }
 
}