package client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import client.controller.Observer;

/**
 * The type Inventory panel.
 */
public class InventoryPanel extends JPanel implements ActionListener{
private JButton search;
private JButton listTools;
private TablePanel tablePanel;
private CardLayout c;
private JPanel displayPanel;
private Observer mc;
private JTextField searchField;
private JComboBox comboBox;
    /**
     * The Mapper.
     */
    ObjectMapper mapper=new ObjectMapper();
    /**
     * The Array node.
     */
    ObjectNode[] arrayNode;

    /**
     * Instantiates a new Inventory panel.
     *
     * @param mc the mc
     */
    public InventoryPanel(Observer mc) {
	this.mc=mc;
	setBackground(SystemColor.inactiveCaption);
	setLayout(new BorderLayout(0, 0));
	//splits the panel into two section, header contains all the buttons search,listtool
	JSplitPane splitPane = new JSplitPane();
	splitPane.setDoubleBuffered(true);
	splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	add(splitPane);
	splitPane.setDividerLocation(100);
	JPanel headerPanel = new JPanel();
	headerPanel.setBackground(SystemColor.menu);
	headerPanel.setAutoscrolls(true);
	headerPanel.setMaximumSize(new Dimension(32767, 60));
	headerPanel.setMinimumSize(new Dimension(30, 400));
	splitPane.setLeftComponent(headerPanel);
	headerPanel.setLayout(null);
	
	searchField = new JTextField();
	searchField.setBounds(168, 10, 116, 22);
	headerPanel.add(searchField);
	searchField.setColumns(10);
	

	
	comboBox = new JComboBox();
	comboBox.setModel(new DefaultComboBoxModel(new String[] {"Search by ToolID", "Search by ToolName"}));
	comboBox.setBounds(31, 10, 125, 22);
	headerPanel.add(comboBox);
	
	
	
	search = new JButton("Search");
	search.setBounds(168, 74, 97, 25);
	headerPanel.add(search);
	search.addActionListener(this);
	
	listTools = new JButton("List All Tools");
	listTools.setBounds(485, 61, 134, 25);
	headerPanel.add(listTools);
	listTools.addActionListener(this);
	//Bottom half of the panel contains multiple panels such as table and purchase panels
	displayPanel = new JPanel();
	displayPanel.setBackground(SystemColor.scrollbar);
	displayPanel.setMinimumSize(new Dimension(10, 600));
	splitPane.setRightComponent(displayPanel);
	c= new CardLayout(0,0);
	displayPanel.setLayout(c);
	tablePanel = new TablePanel(mc);
	displayPanel.add(tablePanel,"table");
	tablePanel.setCardLayout(c);
	tablePanel.setPanel(displayPanel);
	
}

public void actionPerformed(ActionEvent e) {
	if(e.getSource()==listTools)
	{	
		tablePanel = new TablePanel(mc);
		displayPanel.add(tablePanel,"table");
		tablePanel.setCardLayout(c);
		tablePanel.setPanel(displayPanel);
		
		c.show(displayPanel, "table"); 
		//String json;
		  String json="{ \"type\" : \"GET\", \"table\" : \"TOOL\" , \"scope\":\"all\"}";
		String response=mc.request(json);
		
		if (response!=null) {
		try {
			arrayNode=mapper.readValue(response, ObjectNode[].class);
			tablePanel.populateTable(arrayNode);//fills the table with response
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}}
		else 
			JOptionPane.showMessageDialog(null, "Error reading tool list");

	}
	else if (e.getSource()==search) {
		if(comboBox.getSelectedItem().equals("Search by ToolID"))
		{
			try{
				tablePanel = new TablePanel(mc);
			displayPanel.add(tablePanel,"table");
			tablePanel.setCardLayout(c);
			tablePanel.setPanel(displayPanel);
			
			c.show(displayPanel, "table"); 
			String request="{ \"type\" : \"GET\", \"table\" : \"TOOL\" , \"scope\":\"select\",\"field\":\"ToolID\",\"field_value\":\""+Integer.parseInt(searchField.getText())+"\"}";
		
			String response=mc.request(request);
			if (response.length()>3) {//for somereason len of response is 3 for tools not found
				try {
					arrayNode=mapper.readValue(response, ObjectNode[].class);
					tablePanel.populateTable(arrayNode);
				} catch (JsonProcessingException e1) {
					e1.printStackTrace();
				}}
				else 
					JOptionPane.showMessageDialog(null, "Tool Not Found");
		
		
		
		}catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Please enter an Integer");
		}
			}
		else  {
			tablePanel = new TablePanel(mc);
			displayPanel.add(tablePanel,"table");
			tablePanel.setCardLayout(c);
			tablePanel.setPanel(displayPanel);
			
			c.show(displayPanel, "table"); 
			String request="{ \"type\" : \"GET\", \"table\" : \"TOOL\" , \"scope\":\"select\",\"field\":\"Name\",\"field_value\":\""+searchField.getText()+"\"}";
		
			String response=mc.request(request);
			if (response.length()>3) {
				try {
					arrayNode=mapper.readValue(response, ObjectNode[].class);
					tablePanel.populateTable(arrayNode);
				} catch (JsonProcessingException e1) {
					e1.printStackTrace();
				}}
				else 
					JOptionPane.showMessageDialog(null, "Tool Not Found");
		}
		}
	}
	
}

