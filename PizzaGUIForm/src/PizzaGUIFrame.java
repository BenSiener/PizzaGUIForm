import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class PizzaGUIFrame extends JFrame {
    private JRadioButton thinCrustRadioButton, regularCrustRadioButton, deepDishCrustRadioButton;
    private JComboBox<String> sizeComboBox;
    private JCheckBox pepperoniCheckBox, mushroomsCheckBox, onionsCheckBox, sausageCheckBox, baconCheckBox, pineappleCheckBox;
    private JTextArea orderTextArea;

    public PizzaGUIFrame() {
        setTitle("Pizza Order");
        setSize(800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel crustPanel = createCrustPanel();
        JPanel sizePanel = createSizePanel();
        JPanel toppingsPanel = createToppingsPanel();
        JPanel orderPanel = createOrderPanel();
        JPanel buttonPanel = createButtonPanel();

        add(crustPanel, BorderLayout.NORTH);
        add(sizePanel, BorderLayout.WEST);
        add(toppingsPanel, BorderLayout.EAST);
        add(orderPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createCrustPanel() {
        JPanel crustPanel = new JPanel();
        crustPanel.setBorder(BorderFactory.createTitledBorder("Crust"));
        ButtonGroup crustGroup = new ButtonGroup();

        thinCrustRadioButton = new JRadioButton("Thin");
        regularCrustRadioButton = new JRadioButton("Regular");
        deepDishCrustRadioButton = new JRadioButton("Deep-dish");

        crustGroup.add(thinCrustRadioButton);
        crustGroup.add(regularCrustRadioButton);
        crustGroup.add(deepDishCrustRadioButton);

        crustPanel.add(thinCrustRadioButton);
        crustPanel.add(regularCrustRadioButton);
        crustPanel.add(deepDishCrustRadioButton);

        return crustPanel;
    }

    private JPanel createSizePanel() {
        JPanel sizePanel = new JPanel();
        sizePanel.setBorder(BorderFactory.createTitledBorder("Size"));

        String[] sizes = {"Small", "Medium", "Large", "Super"};
        sizeComboBox = new JComboBox<>(sizes);

        sizePanel.add(sizeComboBox);

        return sizePanel;
    }

    private JPanel createToppingsPanel() {
        JPanel toppingsPanel = new JPanel(new BorderLayout());
        JPanel checkboxesPanel = new JPanel(new GridLayout(0, 1));

        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));


        pepperoniCheckBox = new JCheckBox("Pepperoni");
        mushroomsCheckBox = new JCheckBox("Mushrooms");
        onionsCheckBox = new JCheckBox("Onions");
        sausageCheckBox = new JCheckBox("Sausage");
        baconCheckBox = new JCheckBox("Bacon");
        pineappleCheckBox = new JCheckBox("Pineapple");

        checkboxesPanel.add(pepperoniCheckBox);
        checkboxesPanel.add(mushroomsCheckBox);
        checkboxesPanel.add(onionsCheckBox);
        checkboxesPanel.add(sausageCheckBox);
        checkboxesPanel.add(baconCheckBox);
        checkboxesPanel.add(pineappleCheckBox);

        toppingsPanel.add(checkboxesPanel, BorderLayout.WEST);

        return toppingsPanel;
    }


    private JPanel createOrderPanel() {
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBorder(BorderFactory.createTitledBorder("Order"));

        orderTextArea = new JTextArea(10, 30);
        orderTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderTextArea);
        orderPanel.add(scrollPane);

        return orderPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton orderButton = new JButton("Order");
        JButton clearButton = new JButton("Clear");
        JButton quitButton = new JButton("Quit");

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });

        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        return buttonPanel;
    }

    private void placeOrder() {
        StringBuilder order = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00");

        if (thinCrustRadioButton.isSelected())
            order.append("Type of Crust: Thin\n");
        else if (regularCrustRadioButton.isSelected())
            order.append("Type of Crust: Regular\n");
        else if (deepDishCrustRadioButton.isSelected())
            order.append("Type of Crust: Deep-dish\n");

        String selectedSize = (String) sizeComboBox.getSelectedItem();
        order.append("Size: ").append(selectedSize).append("\n");

        double baseCost = getBaseCost(selectedSize);
        double toppingsCost = getToppingsCost();
        double subTotal = baseCost + toppingsCost;
        double tax = subTotal * 0.07;
        double total = subTotal + tax;

        order.append("==============================\n");
        order.append("Type of Crust & Size     ").append(df.format(baseCost)).append("\n");
        order.append("==============================\n");
        order.append("Ingredient               ").append(df.format(toppingsCost)).append("\n");
        order.append("==============================\n");

        order.append("Sub-total:               $").append(df.format(subTotal)).append("\n");
        order.append("Tax:                     $").append(df.format(tax)).append("\n");
        order.append("------------------------------\n");
        order.append("Total:                   $").append(df.format(total)).append("\n");

        orderTextArea.setText(order.toString());
    }

    private double getBaseCost(String size) {
        switch (size) {
            case "Small":
                return 8.00;
            case "Medium":
                return 12.00;
            case "Large":
                return 16.00;
            case "Super":
                return 20.00;
            default:
                return 0.00;
        }
    }

    private double getToppingsCost() {
        double cost = 0.00;
        if (pepperoniCheckBox.isSelected()) cost += 1.00;
        if (mushroomsCheckBox.isSelected()) cost += 1.00;
        if (onionsCheckBox.isSelected()) cost += 1.00;
        if (sausageCheckBox.isSelected()) cost += 1.00;
        if (baconCheckBox.isSelected()) cost += 1.00;
        if (pineappleCheckBox.isSelected()) cost += 1.00;
        return cost;
    }

    private void clearForm() {
        thinCrustRadioButton.setSelected(false);
        regularCrustRadioButton.setSelected(false);
        deepDishCrustRadioButton.setSelected(false);
        sizeComboBox.setSelectedIndex(0);
        pepperoniCheckBox.setSelected(false);
        mushroomsCheckBox.setSelected(false);
        onionsCheckBox.setSelected(false);
        sausageCheckBox.setSelected(false);
        baconCheckBox.setSelected(false);
        pineappleCheckBox.setSelected(false);
        orderTextArea.setText("");
    }

    private void quit() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PizzaGUIFrame().setVisible(true);
            }
        });
    }
}

