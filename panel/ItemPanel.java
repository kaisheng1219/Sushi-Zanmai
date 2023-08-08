package panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import model.*;

public class ItemPanel extends JPanel{
    private JButton btnAdd;
    private JSpinner spnQty;
    private JLabel lblImg, lblName, lblPrice, lblQty;
    private Item item;

    public ItemPanel(Item item) {
        this.item = item;
        init();
    }

    public void resetQty() {
        spnQty.setValue( ((SpinnerNumberModel)spnQty.getModel()).getMinimum());
    }

    private void init() {
        setBackground(new Color(255, 247, 244));
        setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        initLabels();
        initButton();
        initSpinner();
        initLayout();        
    }

    private void initLabels() {
        lblImg = new JLabel();
        lblName = new JLabel();
        lblPrice = new JLabel();
        lblQty = new JLabel();

        lblImg.setIcon(new ImageIcon("image/" + item.getName() + ".png")); 

        lblName.setFont(new Font("Lucida Grande", 1, 18)); 
        lblName.setText(item.getName());

        lblPrice.setFont(new Font("Menlo", 1, 18)); 
        lblPrice.setText("RM " +  String.valueOf(item.getPrice()));

        lblQty.setFont(new Font("Menlo", 1, 18)); 
        lblQty.setText("Qty:");
    }

    private void initButton() {
        btnAdd = new JButton();
        btnAdd.setFont(new Font("Menlo", 0, 14)); 
        btnAdd.setText("Add");
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                ((model.MenuItem)item).setQuantity((int)spnQty.getValue());
                
                if (item.getQuantity() > 0) {
                    MainPanel.updateBill(item);
                    MainPanel.updateList(item);
                }
            }
        });
    }

    private void initSpinner() {
        spnQty = new JSpinner();
        spnQty.setFont(new Font("Menlo", 1, 18));
        spnQty.setModel(new SpinnerNumberModel(0, 0, 50, 1));
        spnQty.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    private void initLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrice)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(lblQty)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(spnQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 50)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrice)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lblQty)
                            .addComponent(spnQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 50))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
    }
}