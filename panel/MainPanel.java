package panel;

import javax.swing.*;

import model.*;

import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.*;

public class MainPanel extends JPanel {
    private JScrollPane spFood, spDrink, spList;
    private JTabbedPane tp;
    private JList<String> list;
    private JPanel pnlFood, pnlDrink, pnlList;
    private JButton btnBill, btnReset;
    private JTextField tfCoupon;
    private static DefaultListModel<String> model;

    private static Bill bill;
    private ArrayList<Item> menuItems;
    private ArrayList<Coupon> coupons;
    private ArrayList<ItemPanel> itemPanels;

    public MainPanel() {
        init();
    }

    public static void updateBill(Item selectedItem) {
        bill.addItem(selectedItem);
    }

    public static void updateList(Item newItem) {
        String line = String.format(" %14s %5d    RM %6.2f", 
            newItem.getName(), newItem.getQuantity(), newItem.getPrice()
        );
        model.addElement(line);
    }

    private void init() {
        bill = new Bill();
        menuItems = new ArrayList<>();
        coupons = new ArrayList<>();
        itemPanels = new ArrayList<>();

        setBackground(new Color(255, 247, 222));

        initMenu();
        initCoupon();
        initButtons();
        initList();
        initTabbedPaneComponents();
        initLayout();

        populateTabbedPane();
    }

    private void initList() {
        pnlList = new JPanel();
        spList = new JScrollPane();
        list = new JList<>();
        model = new DefaultListModel<>();

        initTfCoupon();

        pnlList.setOpaque(false);

        list.setBackground(new Color(255, 247, 222));
        list.setFont(new Font("Menlo", 1, 18)); // NOI18N
        list.setModel(model);
        
        spList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spList.setViewportView(list);

        GroupLayout pnlListLayout = new GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfCoupon)
                .addContainerGap())
            .addComponent(spList, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addGap(30, 30, 90)
                .addComponent(btnReset)
                .addGap(30, 30, 60)
                .addComponent(btnBill)
                .addGap(46, 46, 46))
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spList)
                .addGap(26, 26, 26)
                .addComponent(tfCoupon, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlListLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnBill))
                .addGap(16, 16, 16))    
        );

        model.addElement(String.format("%10s %11s %9s", "Item", "Qty", "Price"));
        model.addElement(String.format(" %40s", " ").replace(' ', '-'));
    }

    private void initTabbedPaneComponents() {
        tp = new JTabbedPane();
        tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        initFoodPane();
        initDrinkPane();
        
        tp.addTab("Food", spFood);
        tp.addTab("Drink", spDrink);
    }

    private void initFoodPane() {
        spFood = new JScrollPane();
        pnlFood = new JPanel();

        spFood.setViewportView(pnlFood);

        pnlFood.setLayout(new GridLayout(0, 2, 90, 20));
        pnlFood.setBackground(new Color(255, 247, 222));
    }

    private void initDrinkPane() {
        spDrink = new JScrollPane();
        pnlDrink = new JPanel();

        spDrink.setViewportView(pnlDrink);

        pnlDrink.setLayout(new GridLayout(0, 2, 90, 20));
        pnlDrink.setBackground(new Color(255, 247, 222));
    }

    private void initButtons() {
        btnBill = new JButton();
        btnBill.setFont(new Font("Lucida Grande", 1, 18)); // NOI18N
        btnBill.setText("Bill");
        btnBill.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for (Coupon c : coupons)
                    if (c.matches(tfCoupon.getText())) {
                        bill.setCoupon(c);
                        break;
                    }

                for (int i = 0; i < 3; i++)
                    model.addElement("\n");
                model.addElement(String.format(" %40s", " ").replace(' ', '-'));
                model.addElement(String.format(" Total Price: RM %6.2f", bill.getPrice()));
                model.addElement(String.format("    Discount: RM %6.2f", bill.getDiscount()));
                model.addElement(String.format(" Net Payable: RM %6.2f", bill.getNetPayable()));
            }
        });

        btnReset = new JButton();
        btnReset.setFont(new Font("Lucida Grande", 1, 18)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for (ItemPanel ip : itemPanels)
                    ip.resetQty();
                    
                bill.getItems().clear();
                bill.setCoupon(null);
                model.removeRange(2, model.size()-1);
                tfCoupon.setText("Enter Coupon Code");;
            }
        });
    }

    private void initTfCoupon() {
        tfCoupon = new JTextField();
        tfCoupon.setFont(new Font("Lucida Grande", 0, 18));
        tfCoupon.setHorizontalAlignment(JTextField.CENTER);
        tfCoupon.setText("Enter Coupon Code");
        
        tfCoupon.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if (tfCoupon.getText().equals("Enter Coupon Code"))
                    tfCoupon.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfCoupon.getText().equals(""))
                    tfCoupon.setText("Enter Coupon Code");
            }
        });
    }

    private void initLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tp, GroupLayout.PREFERRED_SIZE, 743, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(tp, GroupLayout.Alignment.TRAILING)
            .addComponent(pnlList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void initMenu() {
        File f = new File("menu.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        String line = s.nextLine();
        String[] datas = line.split(",");
        String dataType = datas[0];
        if (dataType.equals("f")) {
            line = s.nextLine();
            while (line.length() > 1) {
                datas = line.split(",");
                menuItems.add(new Food(datas[0], Double.parseDouble(datas[1])));
                line = s.nextLine();
                if (line.length() <= 1)
                    dataType = line;
            }
        }
        if (dataType.equals("d")) {
            while (s.hasNextLine()) {
                line = s.nextLine();
                datas = line.split(",");
                menuItems.add(new Drink(datas[0], Double.parseDouble(datas[1])));
            }
        }
        s.close();
    }

    private void initCoupon() {
        File f = new File("coupon.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (s.hasNextLine()) 
            coupons.add(new Coupon(s.next(), s.nextDouble()));
        s.close();
    }

    private void populateTabbedPane() {
        for (Item i : menuItems) {
            ItemPanel ip = new ItemPanel(i);
            itemPanels.add(ip);
            if (i.getItemType().equals("FOOD"))
                pnlFood.add(ip);
            else if (i.getItemType().equals("DRINK"))
                pnlDrink.add(ip);
        }
    }
}