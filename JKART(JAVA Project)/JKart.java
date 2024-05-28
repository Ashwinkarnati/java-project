import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

import javax.swing.*;

public class JKart extends JFrame {
    String username;

    JKart() {
        // Adding Login Page
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridy++;
        String wish;
        LocalTime currentTime = LocalTime.now();
        // Check if it's morning or evening
        if (currentTime.isBefore(LocalTime.NOON)) {
            wish = "Good Morning";
        } else if (currentTime.isBefore(LocalTime.of(16, 0))) {
            wish = "Good Afternoon";
        } else {
            wish = "Good Evening!";
        }
        JLabel wishLabel = new JLabel(wish);
        wishLabel.setFont(new Font("Algerian", Font.BOLD, 35));
        wishLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(wishLabel, gbc);

        gbc.gridy++;
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Brisk Script", Font.BOLD, 20));
        JTextField usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        loginPanel.add(usernamePanel, gbc);

        gbc.gridy++;
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Brisk Script", Font.BOLD, 20));
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        loginPanel.add(passwordPanel, gbc);

        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginPanel.add(loginButton, gbc);

        // JLabel to display error message
        JLabel errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(Color.RED);
        gbc.gridy++;
        loginPanel.add(errorMessageLabel, gbc);

        // Add ActionListener to the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                char[] password = passwordField.getPassword();

                if (isValidLogin(username, password)) {
                    errorMessageLabel.setText(""); // Clear any previous error message
                    new JKartProducts();
                } else {
                    errorMessageLabel.setText("Invalid Username or Password. Please try again.");
                }
            }

            private boolean isValidLogin(String username, char[] password) {
               String Pass=new String(password);
               if(Pass.equals(username+"@jkart")) return true;
               else return false;
            }
        });

        add(loginPanel);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("JKart");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // JKart Selection panel
    class JKartProducts extends JFrame implements ActionListener {
        HashMap<String, Integer> kartItems = new HashMap<>();
        JPanel welcomePanel;
        JPanel buttonsPanel;
        JPanel choicePanel;

        JButton next;

        JMenuBar mobileMenuBar;
        JMenuBar laptopMenuBar;
        JMenuBar electronicMenuBar;

        JKartProducts() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);

            welcomePanel = new JPanel(new FlowLayout());
            JLabel jl = new JLabel("Welcome to JKart");
            jl.setFont(new Font("Verdana", Font.BOLD, 50));
            jl.setForeground(new Color(155, 120, 70));

            next = new JButton("Next");
            next.addActionListener(this);

            welcomePanel.add(jl);
            add(welcomePanel, BorderLayout.NORTH);

            buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

            JButton mobile = createStyledButton("mobiles.png", "mobile");
            JButton laptop = createStyledButton("laptops.png", "laptop");
            JButton electronics = createStyledButton("electronics.png", "electronics");

            mobile.addActionListener(this);
            laptop.addActionListener(this);
            electronics.addActionListener(this);

            buttonsPanel.add(mobile);
            buttonsPanel.add(laptop);
            buttonsPanel.add(electronics);

            add(buttonsPanel, BorderLayout.CENTER);

            choicePanel = new JPanel(new GridLayout(1, 1));
            add(choicePanel, BorderLayout.SOUTH);
            setTitle("JKart Products...");
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent ae) {
            String cmd = ae.getActionCommand();
            choicePanel.removeAll();

            switch (cmd) {
                case "mobile":
                    mobileMenuBar();
                    break;
                case "laptop":
                    laptopMenuBar();
                    break;
                case "electronics":
                    electronicsMenu();
                    break;
                case "Next":
                    new JKartBill(kartItems);
                    break;
                default:
                    kartItems.put(cmd, kartItems.getOrDefault(cmd, 0) + 1);
            }

            choicePanel.add(next);

            revalidate();
            repaint();
        }

        private void mobileMenuBar() {
            mobileMenuBar = new JMenuBar();
            String[] menu = { "Apple", "Vivo", "OnePlus", "Redmi" };
            String[][] menuItems = { { "IPhone 14", "IPhone 14 Pro max", "IPhone 15" },
                    { "Vivo V29", "Vivo V29 Pro", "Vivo T2 Pro" },
                    { "OnePlus 11", "OnePlus 11R", "OnePlus 10T" },
                    { "Redmi 12", "Redmi Note 12 Pro", "RedNote 13 Pro+" } };
            for (int i = 0; i < menu.length; i++) {
                JMenu menuCategory = new JMenu(menu[i]);
                for (int j = 0; j < 3; j++) {
                    JMenuItem menuItem = new JMenuItem(menuItems[i][j]);
                    menuCategory.add(menuItem);
                    menuItem.addActionListener(this);
                }
                mobileMenuBar.add(menuCategory);
            }
            choicePanel.add(mobileMenuBar);
        }

        private void laptopMenuBar() {
            laptopMenuBar = new JMenuBar();
            String[] menu = { "HP", "DELL", "Apple MacBook", "Lenovo" };
            String[][] menuItems = { { "HP-ProBook 440 G8 NoteBook PC", "HP-Laptop 15", },
                    { "Dell 15 Laptop", "Dell Inspiron 15 Laptop" },
                    { "Apple MacBook Pro", "Apple MacBook Air - M2 Chip" },
                    { "Lenovo Yoga Pro 7i", "Lenovo Ideapad Gaming 3i Laptop" } };
            for (int i = 0; i < menu.length; i++) {
                JMenu menuCategory = new JMenu(menu[i]);
                for (int j = 0; j < 2; j++) {
                    JMenuItem menuItem = new JMenuItem(menuItems[i][j]);
                    menuCategory.add(menuItem);
                    menuItem.addActionListener(this);
                }
                laptopMenuBar.add(menuCategory);
            }
            choicePanel.add(laptopMenuBar);
        }

        private void electronicsMenu() {
            electronicMenuBar = new JMenuBar();
            String[] menu = { "Cooler", "TV", "Fridge", "Bulb" };
            String[][] menuItems = { { "Symphony", "Bajaj", "Crompton" },
                    { "Samsung", "Sony", "LG" },
                    { "Whirlpool", "Haier", "Godrej" },
                    { "Philips", "Crompton", "Syska" } };
            for (int i = 0; i < menu.length; i++) {
                JMenu menuCategory = new JMenu(menu[i]);
                for (int j = 0; j < 3; j++) {
                    JMenuItem menuItem = new JMenuItem(menuItems[i][j]);
                    menuCategory.add(menuItem);
                    menuItem.addActionListener(this);
                }
                electronicMenuBar.add(menuCategory);
            }
            choicePanel.add(electronicMenuBar);
        }

        private JButton createStyledButton(String imagePath, String actionCommand) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage();
            img = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(400, 400));
            button.setActionCommand(actionCommand);
            return button;
        }
    }

    // Final bill for the items generated
    class JKartBill extends JFrame {
        JKartBill(HashMap<String, Integer> billMap) {
            JPanel billPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(2, 0, 2, 0);
            gbc.anchor = GridBagConstraints.CENTER;

            JLabel thankYou = new JLabel("Thank You " + username);
            JLabel visitAgain = new JLabel("Visit Again");
            Font f = new Font("TimesRoman", Font.ITALIC + 1, 35);

            thankYou.setFont(f);
            visitAgain.setFont(f);

            billPanel.add(thankYou, gbc);
            gbc.gridy++;
            billPanel.add(visitAgain, gbc);

            // Place the date and order number labels at the northeast corner
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.NORTHEAST;

            JLabel date = new JLabel("Date:");
            JLabel dt = new JLabel(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            Font font = new Font("CooperBlack", Font.ITALIC, 15);
            date.setFont(font);
            dt.setFont(font);

            billPanel.add(date, gbc);
            gbc.gridx++;
            billPanel.add(dt, gbc);

            gbc.gridx = 0;
            gbc.gridy++;

            JLabel orderid = new JLabel("OrderID: ");
            Random rand = new Random();
            String randno = Integer.toString(rand.nextInt(1000000000));
            JLabel orderno = new JLabel(randno);
            Font k = new Font("CooperBlack", Font.ITALIC, 15);
            orderid.setFont(k);
            orderno.setFont(k);

            billPanel.add(orderid, gbc);
            gbc.gridx++;
            billPanel.add(orderno, gbc);

            // Set the column headings for the JTable
            gbc.gridx = 0;
            gbc.gridy++;

            String colnames[] = { "S.No", "Product", "Qty", "Price(Rs.)", "Total(Rs.)" };

            // Add data and create the JTable with the DefaultTableModel
            String[] itemList = { "IPhone 14", "IPhone 14 Pro max", "IPhone 15",
                    "Vivo V29", "Vivo V29 Pro", "Vivo T2 Pro",
                    "OnePlus 11", "OnePlus 11R", "OnePlus 10T",
                    "Redmi 12", "Redmi Note 12 Pro", "Redmi Note 13 Pro+",
                    "HP-ProBook 440 G8 NoteBook PC", "HP-Laptop 15",
                    "Dell 15 Laptop", "Dell Inspiron 15 Laptop",
                    "Apple MacBook Pro", "Apple MacBook Air - M2 Chip",
                    "Lenovo Yoga Pro 7i", "Lenovo Ideapad Gaming 3i Laptop",
                    "Symphony", "Bajaj", "Crompton",
                    "Samsung", "Sony", "LG",
                    "Whirlpool", "Haier", "Godrej",
                    "Philips", "Crompton", "Syska" };
            Integer[] itemPrice = { 66999, 127999, 156900,
                    36999, 39999, 23999,
                    61999, 44999, 49999,
                    15999, 21999, 39599,
                    69999, 27990,
                    47990, 53990,
                    169900, 125900,
                    99990, 71790,
                    21999, 19999, 17999,
                    199999, 249999, 179999,
                    109999, 139999, 99998,
                    799, 699, 749 };
            HashMap<String, Integer> itemsData = new HashMap<>();
            for (int i = 0; i < itemList.length; i++) {
                itemsData.put(itemList[i], itemPrice[i]);
            }

            Object[][] data = new Object[billMap.size()][5];
            ArrayList<String> keys = new ArrayList<>(billMap.keySet());
            int sum = 0;

            for (int i = 0; i < keys.size(); i++) {
                String item = keys.get(i);
                data[i][0] = i + 1;
                data[i][1] = item;
                data[i][2] = billMap.get(item);
                data[i][3] = itemsData.get(item);
                data[i][4] = (int) data[i][2] * (int) data[i][3];
                sum += (int) data[i][4];
            }

            JTable table = new JTable(data, colnames);
            table.getColumnModel().getColumn(1).setPreferredWidth(200);

            // Center the JTable
            gbc.anchor = GridBagConstraints.CENTER;

            gbc.gridy++;
            billPanel.add(table.getTableHeader(), gbc);
            gbc.gridy++;
            billPanel.add(table, gbc);
            table.setEnabled(false);

            gbc.gridy++;
            JLabel totalamount = new JLabel("Total Amount to be paid(Inclusive of delivery charge(Rs.100/-))");
            JLabel totalcost;
            if (sum > 0) {
                totalcost = new JLabel(Integer.toString(sum + 100));
            } else {
                totalcost = new JLabel(Integer.toString(sum));
            }
            Font j = new Font("CooperBlack", Font.ITALIC, 20);
            Font l = new Font("CooperBlack", Font.ITALIC + 1, 20);
            totalamount.setFont(j);
            totalcost.setFont(l);

            billPanel.add(totalamount, gbc);
            gbc.gridx++;
            billPanel.add(totalcost, gbc);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 3);
            Date increasedDate = calendar.getTime();
            JLabel deliveryDate = new JLabel(
                    "Your Order Will Be Delivered by " + new SimpleDateFormat("dd-MM-yyyy").format(increasedDate));
            deliveryDate.setFont(new Font("Sanserif", Font.ITALIC + 1, 20));
            gbc.gridx = 0;
            gbc.gridy++;

            gbc.anchor = GridBagConstraints.CENTER;

            billPanel.add(deliveryDate, gbc);

            add(billPanel);
            setSize(500, 500);
            setTitle("JKartBill");
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public static void main(String[] args) {
        new JKart();
    }
}