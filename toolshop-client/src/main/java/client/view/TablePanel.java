package client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import client.controller.Observer;

/**
 * The type Table panel.
 */
public class TablePanel extends JPanel implements ListSelectionListener{

	private JTable table;
	private CardLayout c;
	private JPanel panel;
	private DefaultTableModel tableModel;
	private Observer mc;

    /**
     * Instantiates a new Table panel.
     *
     * @param mc the mc
     */
    public TablePanel(Observer mc) {
		this.mc=mc;
		setBackground(SystemColor.inactiveCaption);
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableModel.addColumn("ToolID");
		tableModel.addColumn("Name");
		tableModel.addColumn("Type");
		tableModel.addColumn("Quantity");
		tableModel.addColumn("Price");
		tableModel.addColumn("SupplierID");
		tableModel.addColumn("PowerType");
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(this);
	}


		

    /**
     * Populate table.
     *
     * @param arrayNode the array node
     */
    public void populateTable(ObjectNode[] arrayNode) {
	tableModel.setRowCount(0);
	for (int i=0;i<arrayNode.length;i++) {
		String powerType="";//to display empty instead of null
		if(arrayNode[i].hasNonNull("PowerType")){
			powerType=arrayNode[i].get("PowerType").asText();
		}
		tableModel.insertRow(0, new Object [] {  arrayNode[i].get("ToolID").asInt(),arrayNode[i].get("Name").asText(),arrayNode[i].get("Type").asText()
				,arrayNode[i].get("Quantity").asInt(),arrayNode[i].get("Price").asDouble(),arrayNode[i].get("SupplierID").asInt(),powerType});
}}

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
public void valueChanged(ListSelectionEvent e) {
	 Integer toolID=  (Integer) table.getValueAt(table.getSelectedRow(), 0);
	 String toolName=  (String) table.getValueAt(table.getSelectedRow(), 1);
	 String type=  (String) table.getValueAt(table.getSelectedRow(), 2);
	 Integer  quantity =(Integer) table.getValueAt(table.getSelectedRow(), 3);
	 Double price=  (Double) table.getValueAt(table.getSelectedRow(), 4);
	 Integer supplierID=  (Integer) table.getValueAt(table.getSelectedRow(), 5);
	 Object powerType=   table.getValueAt(table.getSelectedRow(), 6);
	 ArrayList<Object>temp =new ArrayList<Object>();
	 temp.add(toolID);
	 temp.add(toolName);
	 temp.add(type);
	 temp.add(quantity);
	 temp.add(price);
	 temp.add(supplierID);
	 temp.add(powerType);
	 
	
	 PurchasePanel p= new PurchasePanel(mc,temp);
	 panel.add(p,"purchase");
	 p.setCardLayout(c);
	 p.setPanel(panel);
	 c.show(panel, "purchase");
	
}
}
