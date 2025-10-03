package com.mrdinner.gui;

import com.mrdinner.service.*;
import com.mrdinner.domain.customer.Customer;
import com.mrdinner.domain.order.Order;
import com.mrdinner.domain.common.Address;
import com.mrdinner.domain.common.Money;
import com.mrdinner.domain.menu.*;
import com.mrdinner.domain.inventory.StockItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Main GUI for Mr. Dinner Service
 */
public class MainGUI extends JFrame {
    private OrderService orderService;
    private InventoryService inventoryService;
    private DeliveryService deliveryService;
    private PricingService pricingService;
    
    private JTabbedPane tabbedPane;
    private JTextArea logArea;
    
    public MainGUI() {
        initializeServices();
        initializeGUI();
        setupEventHandlers();
    }
    
    private void initializeServices() {
        // Initialize all services
        inventoryService = new InventoryService();
        deliveryService = new DeliveryService();
        pricingService = new PricingService();
        orderService = new OrderService(pricingService, deliveryService, inventoryService);
        
        // Add sample data
        createSampleData();
        
        logMessage("Services initialized successfully!");
    }
    
    private void initializeGUI() {
        setTitle("Mr. Dinner Service - Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Create main layout
        setLayout(new BorderLayout());
        
        // Create header
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Create tabbed pane for different sections
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Add tabs
        tabbedPane.addTab("Dashboard", createDashboardPanel());
        tabbedPane.addTab("Orders", createOrdersPanel());
        tabbedPane.addTab("Menu", createMenuPanel());
        tabbedPane.addTab("Customers", createCustomersPanel());
        tabbedPane.addTab("Inventory", createInventoryPanel());
        tabbedPane.addTab("Delivery", createDeliveryPanel());
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Create log panel
        JPanel logPanel = createLogPanel();
        add(logPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(41, 128, 185));
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("Mr. Dinner Service");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel("Premium Dinner Delivery Management System");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(200, 200, 200));
        
        panel.setLayout(new BorderLayout());
        panel.add(titleLabel, BorderLayout.WEST);
        panel.add(subtitleLabel, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Create stats panel
        JPanel statsPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        statsPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        // Add stat cards
        statsPanel.add(createStatCard("Total Orders", "12", "Orders processed today"));
        statsPanel.add(createStatCard("Revenue", "$2,450", "Today's earnings"));
        statsPanel.add(createStatCard("Deliveries", "8", "Completed deliveries"));
        statsPanel.add(createStatCard("Customers", "156", "Active customers"));
        statsPanel.add(createStatCard("Menu Items", "24", "Available items"));
        statsPanel.add(createStatCard("Inventory", "95%", "Stock level"));
        
        panel.add(statsPanel, BorderLayout.NORTH);
        
        // Create quick actions panel
        JPanel actionsPanel = createQuickActionsPanel();
        panel.add(actionsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatCard(String title, String value, String description) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(new Color(100, 100, 100));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 20));
        valueLabel.setForeground(new Color(41, 128, 185));
        
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        descLabel.setForeground(new Color(150, 150, 150));
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        card.add(descLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JPanel createQuickActionsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 15, 15));
        panel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        // Quick action buttons
        JButton newOrderBtn = createActionButton("New Order", "Create a new customer order", new Color(46, 204, 113));
        JButton viewMenuBtn = createActionButton("View Menu", "Browse available menu items", new Color(155, 89, 182));
        JButton manageInventoryBtn = createActionButton("Manage Inventory", "Update stock levels", new Color(230, 126, 34));
        
        panel.add(newOrderBtn);
        panel.add(viewMenuBtn);
        panel.add(manageInventoryBtn);
        
        return panel;
    }
    
    private JButton createActionButton(String text, String tooltip, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(new EmptyBorder(20, 20, 20, 20));
        button.setToolTipText(tooltip);
        button.setFocusPainted(false);
        
        button.addActionListener(e -> {
            if (text.contains("New Order")) {
                tabbedPane.setSelectedIndex(1); // Switch to Orders tab
            } else if (text.contains("View Menu")) {
                tabbedPane.setSelectedIndex(2); // Switch to Menu tab
            } else if (text.contains("Manage Inventory")) {
                tabbedPane.setSelectedIndex(4); // Switch to Inventory tab
            }
        });
        
        return button;
    }
    
    private JPanel createOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Create order form
        JPanel formPanel = createOrderForm();
        panel.add(formPanel, BorderLayout.NORTH);
        
        // Create orders list
        JPanel listPanel = createOrdersList();
        panel.add(listPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createOrderForm() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Create New Order"));
        panel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Customer info
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Customer Name:"), gbc);
        gbc.gridx = 1;
        JTextField customerNameField = new JTextField(20);
        panel.add(customerNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        JTextField phoneField = new JTextField(20);
        panel.add(phoneField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        JTextField addressField = new JTextField(20);
        panel.add(addressField, gbc);
        
        // Menu selection
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Menu Item:"), gbc);
        gbc.gridx = 1;
        String[] menuItems = {"Valentine Dinner", "French Dinner", "English Dinner", "Champagne Feast", "House Wine", "Artisan Bread"};
        JComboBox<String> menuCombo = new JComboBox<>(menuItems);
        panel.add(menuCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        panel.add(quantitySpinner, gbc);
        
        // Create order button
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JButton createOrderBtn = new JButton("Create Order");
        createOrderBtn.setBackground(new Color(46, 204, 113));
        createOrderBtn.setForeground(Color.WHITE);
        createOrderBtn.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(createOrderBtn, gbc);
        
        // Add action listener
        createOrderBtn.addActionListener(e -> createNewOrder(customerNameField.getText(), 
            phoneField.getText(), addressField.getText(), 
            (String) menuCombo.getSelectedItem(), 
            (Integer) quantitySpinner.getValue()));
        
        return panel;
    }
    
    private JPanel createOrdersList() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Recent Orders"));
        panel.setBackground(Color.WHITE);
        
        String[] columns = {"Order ID", "Customer", "Items", "Total", "Status"};
        String[][] data = {
            {"ORD-001", "Alice Johnson", "Valentine Dinner", "$45.99", "Confirmed"},
            {"ORD-002", "Bob Smith", "French Dinner", "$52.50", "Ready"},
            {"ORD-003", "Carol Davis", "English Dinner", "$38.75", "Delivered"}
        };
        
        JTable ordersTable = new JTable(data, columns);
        ordersTable.setBackground(Color.WHITE);
        ordersTable.setGridColor(new Color(200, 200, 200));
        ordersTable.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Our Premium Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Create menu items grid
        JPanel menuGrid = new JPanel(new GridLayout(2, 3, 15, 15));
        
        menuGrid.add(createMenuItemCard("Valentine Dinner", "$45.99", "Romantic dinner for two with wine"));
        menuGrid.add(createMenuItemCard("French Dinner", "$52.50", "Authentic French cuisine"));
        menuGrid.add(createMenuItemCard("English Dinner", "$38.75", "Traditional English tea service"));
        menuGrid.add(createMenuItemCard("Champagne Feast", "$99.99", "Luxury dinner with champagne"));
        menuGrid.add(createMenuItemCard("House Wine", "$15.99", "Premium wine selection"));
        menuGrid.add(createMenuItemCard("Artisan Bread", "$8.50", "Fresh baked artisan bread"));
        
        panel.add(menuGrid, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createMenuItemCard(String name, String price, String description) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(new Color(46, 204, 113));
        
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        descLabel.setForeground(new Color(100, 100, 100));
        
        card.add(nameLabel, BorderLayout.NORTH);
        card.add(priceLabel, BorderLayout.CENTER);
        card.add(descLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JPanel createCustomersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Customer Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Customer table
        String[] columns = {"Name", "Email", "Phone", "Address", "Orders"};
        String[][] data = {
            {"Alice Johnson", "alice@email.com", "555-1234", "123 Main St", "3"},
            {"Bob Smith", "bob@email.com", "555-5678", "456 Oak Ave", "2"},
            {"Carol Davis", "carol@email.com", "555-9012", "789 Pine St", "1"}
        };
        
        JTable customersTable = new JTable(data, columns);
        customersTable.setBackground(Color.WHITE);
        customersTable.setGridColor(new Color(200, 200, 200));
        
        JScrollPane scrollPane = new JScrollPane(customersTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createInventoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Inventory Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Inventory table
        String[] columns = {"Item", "Current Stock", "Min Level", "Status"};
        String[][] data = {
            {"Chicken Breast", "25", "10", "Good"},
            {"Tomatoes", "15", "5", "Good"},
            {"Olive Oil", "8", "3", "Low"},
            {"Champagne", "12", "5", "Good"}
        };
        
        JTable inventoryTable = new JTable(data, columns);
        inventoryTable.setBackground(Color.WHITE);
        inventoryTable.setGridColor(new Color(200, 200, 200));
        
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createDeliveryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Delivery Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Delivery table
        String[] columns = {"Order ID", "Customer", "Address", "Status", "Courier"};
        String[][] data = {
            {"ORD-001", "Alice Johnson", "123 Main St", "In Transit", "Mike Driver"},
            {"ORD-002", "Bob Smith", "456 Oak Ave", "Ready", "Sarah Cyclist"},
            {"ORD-003", "Carol Davis", "789 Pine St", "Delivered", "Mike Driver"}
        };
        
        JTable deliveryTable = new JTable(data, columns);
        deliveryTable.setBackground(Color.WHITE);
        deliveryTable.setGridColor(new Color(200, 200, 200));
        
        JScrollPane scrollPane = new JScrollPane(deliveryTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createLogPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("System Log"));
        panel.setPreferredSize(new Dimension(0, 150));
        
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBackground(new Color(240, 240, 240));
        logArea.setFont(new Font("Consolas", Font.PLAIN, 11));
        
        JScrollPane scrollPane = new JScrollPane(logArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void setupEventHandlers() {
        // Add any additional event handlers here
    }
    
    private void createSampleData() {
        // Create sample customers, staff, and menu items
        // This would normally populate the services with initial data
    }
    
    private void createNewOrder(String customerName, String phone, String address, 
                               String menuItem, int quantity) {
        try {
            // Create customer
            Address customerAddress = new Address(address, "Downtown", "CC", "12345", "USA");
            Customer customer = new Customer(customerName, "customer@email.com", phone, customerAddress);
            
            // Create order
            Order order = orderService.createOrder(customer, customerAddress);
            
            // Add menu item to order
            // This is simplified - in real implementation, you'd get the actual menu item
            logMessage("New order created for " + customerName + " - " + quantity + "x " + menuItem);
            
            // Clear form
            // You would clear the form fields here
            
        } catch (Exception e) {
            logMessage("Error creating order: " + e.getMessage());
        }
    }
    
    private void logMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append("[" + java.time.LocalTime.now().toString().substring(0, 8) + "] " + message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }
    
    public static void main(String[] args) {
        // Use default look and feel
        
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}
