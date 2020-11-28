package client.view;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import client.controller.Observer;

/**
 * The type Purchase panel.
 */
public class PurchasePanel extends JPanel implements ActionListener{

	private Observer mc;
	private ArrayList<Object> variableList;
	private JTextField toolIDPurchase;
	private JTextField toolNamePurchase;
	private JTextField toolType;
	private JTextField toolPrice;
	private JTextField qtyField;
	private JTextField totalField;
	private JTextField clientIDPurchase;
	private JTextField fNameClient;
	private JButton purchaseBtn;
	private CardLayout c;
	private JPanel panel;
    /**
     * The Mapper.
     */
    ObjectMapper mapper=new ObjectMapper();
    /**
     * The Array node.
     */
    ObjectNode[] arrayNode;

    /**
     * Instantiates a new Purchase panel.
     *
     * @param mc           the mc
     * @param variableList the variable list
     */
    public PurchasePanel(Observer mc, final ArrayList<Object> variableList) {
		this.mc=mc;
		this.variableList=variableList;
		setBackground(SystemColor.inactiveCaption);
		GridBagLayout gbl_purchasePanel = new GridBagLayout();
		gbl_purchasePanel.columnWidths = new int[]{76, 56, 116, 0};
		gbl_purchasePanel.rowHeights = new int[]{29, 16, 22, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_purchasePanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_purchasePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_purchasePanel);
		
		JLabel toolInfo = new JLabel("Tool Info");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		add(toolInfo, gbc_lblNewLabel_1);
		
		JLabel toolIDLbl = new JLabel("ToolID");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(toolIDLbl, gbc_lblNewLabel);
		
		toolIDPurchase = new JTextField();
		toolIDPurchase.setEditable(false);
		toolIDPurchase.setColumns(10);
		GridBagConstraints gbc_toolIDPurchase = new GridBagConstraints();
		gbc_toolIDPurchase.anchor = GridBagConstraints.WEST;
		gbc_toolIDPurchase.insets = new Insets(0, 0, 5, 5);
		gbc_toolIDPurchase.gridx = 1;
		gbc_toolIDPurchase.gridy = 1;
		add(toolIDPurchase, gbc_toolIDPurchase);
		
		JLabel lblToolName = new JLabel("Tool Name");
		GridBagConstraints gbc_lblToolName = new GridBagConstraints();
		gbc_lblToolName.anchor = GridBagConstraints.EAST;
		gbc_lblToolName.insets = new Insets(0, 0, 5, 5);
		gbc_lblToolName.gridx = 0;
		gbc_lblToolName.gridy = 2;
		add(lblToolName, gbc_lblToolName);
		
		toolNamePurchase = new JTextField();
		toolNamePurchase.setEditable(false);
		toolNamePurchase.setColumns(10);
		GridBagConstraints gbc_toolNamePurchase = new GridBagConstraints();
		gbc_toolNamePurchase.anchor = GridBagConstraints.WEST;
		gbc_toolNamePurchase.insets = new Insets(0, 0, 5, 5);
		gbc_toolNamePurchase.gridx = 1;
		gbc_toolNamePurchase.gridy = 2;
		add(toolNamePurchase, gbc_toolNamePurchase);
		
		JLabel lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 3;
		add(lblType, gbc_lblType);
		
		toolType = new JTextField();
		toolType.setEditable(false);
		toolType.setColumns(10);
		GridBagConstraints gbc_toolType = new GridBagConstraints();
		gbc_toolType.anchor = GridBagConstraints.WEST;
		gbc_toolType.insets = new Insets(0, 0, 5, 5);
		gbc_toolType.gridx = 1;
		gbc_toolType.gridy = 3;
		add(toolType, gbc_toolType);
		
		JLabel lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 4;
		add(lblPrice, gbc_lblPrice);
		
		toolPrice = new JTextField();
		toolPrice.setEditable(false);
		toolPrice.setColumns(10);
		GridBagConstraints gbc_toolPrice = new GridBagConstraints();
		gbc_toolPrice.anchor = GridBagConstraints.WEST;
		gbc_toolPrice.insets = new Insets(0, 0, 5, 5);
		gbc_toolPrice.gridx = 1;
		gbc_toolPrice.gridy = 4;
		add(toolPrice, gbc_toolPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.EAST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 5;
		add(lblQuantity, gbc_lblQuantity);
		
		qtyField = new JTextField();
		qtyField.setEditable(true);
		GridBagConstraints gbc_qtyField = new GridBagConstraints();
		gbc_qtyField.anchor = GridBagConstraints.NORTHWEST;
		gbc_qtyField.insets = new Insets(0, 0, 5, 5);
		gbc_qtyField.gridx = 1;
		gbc_qtyField.gridy = 5;
		add(qtyField, gbc_qtyField);
		qtyField.setColumns(10);
	
		
		JLabel lblTotal = new JLabel("Total");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.gridx = 0;
		gbc_lblTotal.gridy = 6;
		add(lblTotal, gbc_lblTotal);
		
		totalField = new JTextField();
		totalField.setEditable(false);
		totalField.setColumns(10);
		GridBagConstraints gbc_totalField = new GridBagConstraints();
		gbc_totalField.anchor = GridBagConstraints.WEST;
		gbc_totalField.insets = new Insets(0, 0, 5, 5);
		gbc_totalField.gridx = 1;
		gbc_totalField.gridy = 6;
		add(totalField, gbc_totalField);
		//To check if the actual quatitiy is > than purchased quantity
		qtyField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
			try{	
				if(Integer.parseInt(qtyField.getText())>(Integer)variableList.get(3)) {
					JOptionPane.showMessageDialog(null, "ERROR enter smaller number");
				}
				else 
				{	
				Double n=Integer.parseInt(qtyField.getText())*(Double)variableList.get(4);
				totalField.setText(n+"");
				}	}catch (Exception e1) {}
				
			}
			
		});
		
		JLabel lblCustomerInfo = new JLabel("Customer Info");
		GridBagConstraints gbc_lblCustomerInfo = new GridBagConstraints();
		gbc_lblCustomerInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerInfo.gridx = 1;
		gbc_lblCustomerInfo.gridy = 7;
		add(lblCustomerInfo, gbc_lblCustomerInfo);
		
		
		
		JLabel labelID = new JLabel("ClientID");
		GridBagConstraints gbc_labelID = new GridBagConstraints();
		gbc_labelID.anchor = GridBagConstraints.EAST;
		gbc_labelID.insets = new Insets(0, 0, 5, 5);
		gbc_labelID.gridx = 0;
		gbc_labelID.gridy = 8;
		add(labelID, gbc_labelID);
		
		clientIDPurchase = new JTextField();
		clientIDPurchase.setEditable(true);
		clientIDPurchase.setColumns(10);
		GridBagConstraints gbc_clientIDPurchase = new GridBagConstraints();
		gbc_clientIDPurchase.anchor = GridBagConstraints.WEST;
		gbc_clientIDPurchase.insets = new Insets(0, 0, 5, 5);
		gbc_clientIDPurchase.gridx = 1;
		gbc_clientIDPurchase.gridy = 8;
		add(clientIDPurchase, gbc_clientIDPurchase);
		
		JLabel fNameClientLbl = new JLabel("First Name");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 9;
		add(fNameClientLbl, gbc_label);
		
		fNameClient = new JTextField();
		fNameClient.setEditable(true);
		fNameClient.setColumns(10);
		GridBagConstraints gbc_fNameClient = new GridBagConstraints();
		gbc_fNameClient.anchor = GridBagConstraints.WEST;
		gbc_fNameClient.insets = new Insets(0, 0, 5, 5);
		gbc_fNameClient.gridx = 1;
		gbc_fNameClient.gridy = 9;
		add(fNameClient, gbc_fNameClient);
		
		purchaseBtn = new JButton("Purchase");
		GridBagConstraints gbc_purchaseBtn = new GridBagConstraints();
		gbc_purchaseBtn.insets = new Insets(0, 0, 0, 5);
		gbc_purchaseBtn.gridx = 1;
		gbc_purchaseBtn.gridy = 11;
		purchaseBtn.addActionListener(this);
		
		add(purchaseBtn, gbc_purchaseBtn);
		populateData();
	}

	private void populateData() {
		toolIDPurchase.setText( variableList.get(0)+"");
		toolNamePurchase.setText(variableList.get(1)+"");
		toolType.setText(variableList.get(2)+"");
		toolPrice.setText(variableList.get(4)+"");
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==purchaseBtn) {
			String request="{ \"type\" : \"GET\", \"table\" : \"CLIENT\" , \"scope\":\"select\",\"field\":\"ClientID\",\"field_value\":\""+clientIDPurchase.getText()+"\"}";
			String response=mc.request(request);
			if (response.length()>3) {
				try {
					arrayNode=mapper.readValue(response, ObjectNode[].class);
					if (arrayNode[0].get("FName").asText().equalsIgnoreCase(fNameClient.getText()))
						{
						request="{ \"type\" : \"PUT\", \"table\" : \"PURCHASE\" ,\"ClientID\":\""+clientIDPurchase.getText()+"\",\"ToolID\":\""+variableList.get(0)+"\",\"Date\":\""+new Timestamp(System.currentTimeMillis())+"\"}";
						 response=mc.request(request);
						 request="{ \"type\" : \"POST\", \"table\" : \"TOOL\" ,\"ToolID\":\""+variableList.get(0)+"\",\"Name\":\""+variableList.get(1)+"\""
						 		+ ",\"Type\":\""+variableList.get(2)+"\",\"Quantity\":\""+((Integer)variableList.get(3)-Integer.parseInt(qtyField.getText()))+"\",\"Price\":\""+variableList.get(4)+"\",\"SupplierID\":\""+variableList.get(5)+"\",\"field\":\"ToolID\",\"field_value\":\""+variableList.get(0)+"\"}";
						 response=mc.request(request);
						 System.err.println("response"+response);
						 JOptionPane.showMessageDialog(null, "Thank you for your purchase "+arrayNode[0].get("FName").asText()+"!");
						 
						 c.show(panel,"table");
						}
					else 
						{System.err.println("WRONG"+arrayNode[0].get("FName").asText());
//					c.show(panel, "table");
						JOptionPane.showMessageDialog(null, "Wrong First Name");
						}
						
				} catch (JsonProcessingException e1) {
					e1.printStackTrace();
				}}
				else 
					JOptionPane.showMessageDialog(null, "Wrong Client ID or UserName");
		}
		
			
	}

    /**
     * Sets card layout.
     *
     * @param c the c
     */
    public void setCardLayout(CardLayout c) {
		this.c=c;
	
		
	}

    /**
     * Sets panel.
     *
     * @param panel the panel
     */
    public void setPanel(JPanel panel) {
		this.panel=panel;
		
	}
}
