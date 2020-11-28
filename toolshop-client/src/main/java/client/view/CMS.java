package client.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import client.controller.Observer;

/**
 * The type Cms.
 */
public class CMS extends JPanel implements ActionListener, ListSelectionListener {
	private Observer mc;
	private JTextField search;
	private JButton searchClient;
	private JList list;
	private JButton clearSearch;
	private JTextField clientIDCMS;
	private JTextField fnameCMS;
	private JTextField lNameCMS;
	private JTextField addressCMS;
	private JTextField postalCodeCMS;
	private JTextField phoneCMS;
	private JButton saveBtn;
	private JButton deleteBtn;
	private JButton clearBtn;
    /**
     * The Option.
     */
    int option;
	private JComboBox comboBoxClientMgmnt;
	private JRadioButton clientType;
	private JRadioButton lName;
	private JRadioButton clientID;
    /**
     * The Mapper.
     */
    ObjectMapper mapper = new ObjectMapper();
    /**
     * The Array.
     */
    ObjectNode[] array;
    /**
     * The Empty.
     */
    boolean empty=true;
	private ArrayList<String> values;
	private JButton addCustomer;

    /**
     * Instantiates a new Cms.
     *
     * @param mc the mc
     */
    public CMS(Observer mc) {
		this.mc = mc;
		setLayout(new BorderLayout(0, 0));
		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Tahoma", Font.PLAIN, 89));
		panel_1.setBackground(SystemColor.controlDkShadow);
		panel_1.setPreferredSize(new Dimension(10, 100));
		add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel_3 = new JLabel("Client Management Screen");
		lblNewLabel_3.setForeground(SystemColor.window);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 29));
		panel_1.add(lblNewLabel_3);

		JSplitPane splitPane_1 = new JSplitPane();
		add(splitPane_1, BorderLayout.CENTER);
		JPanel searchClientPanel = new JPanel();
		searchClientPanel.setMinimumSize(new Dimension(900, 10));
		searchClientPanel.setPreferredSize(new Dimension(500, 10));
		splitPane_1.setLeftComponent(searchClientPanel);
		ButtonGroup bg1 =new ButtonGroup();
		searchClientPanel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Select type of search to be performed");
		lblNewLabel_4.setBounds(116, 0, 217, 16);
		searchClientPanel.add(lblNewLabel_4);
		clientID = new JRadioButton("Client ID");
		clientID.setBounds(27, 90, 217, 25);
		searchClientPanel.add(clientID);
		bg1.add(clientID);
		clientID.addActionListener(this);
		lName = new JRadioButton("Last Name");
		lName.setBounds(27, 136, 89, 25);
		searchClientPanel.add(lName);
		bg1.add(lName);
		lName.addActionListener(this);
		clientType = new JRadioButton("Client Type");
		clientType.setBounds(27, 175, 93, 25);
		searchClientPanel.add(clientType);
		bg1.add(clientType);
		clientType.addActionListener(this);
		
		search = new JTextField();
		search.setBounds(27, 252, 217, 22);
		searchClientPanel.add(search);
		search.setColumns(10);
		
		 searchClient = new JButton("Search");
		searchClient.setBounds(334, 218, 107, 25);
		searchClientPanel.add(searchClient);
		searchClient.addActionListener(this);
		
		clearSearch = new JButton("Clear Search");
		clearSearch.setBounds(334, 251, 107, 25);
		searchClientPanel.add(clearSearch);
		clearSearch.addActionListener(this);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(27, 280, 414, 156);
		searchClientPanel.add(scrollPane_1);
		
		list = new JList();
		scrollPane_1.setViewportView(list);
		list.addListSelectionListener(this) ;
			
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		JPanel clientInfoPanel = new JPanel();
		clientInfoPanel.setBackground(SystemColor.inactiveCaption);
		clientInfoPanel.setMinimumSize(new Dimension(900, 10));
		clientInfoPanel.setPreferredSize(new Dimension(500, 10));
		splitPane_1.setRightComponent(clientInfoPanel);
		GridBagLayout gbl_clientInfoPanel = new GridBagLayout();
		gbl_clientInfoPanel.columnWidths = new int[] { 97, 97, 38, 274, 0 };
		gbl_clientInfoPanel.rowHeights = new int[] { 16, 67, 22, 22, 22, 22, 22, 22, 37, 22, 61, 25, 0 };
		gbl_clientInfoPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_clientInfoPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		clientInfoPanel.setLayout(gbl_clientInfoPanel);

		JLabel lblClientInformation = new JLabel("Client Information");
		GridBagConstraints gbc_lblClientInformation = new GridBagConstraints();
		gbc_lblClientInformation.anchor = GridBagConstraints.NORTH;
		gbc_lblClientInformation.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblClientInformation.insets = new Insets(0, 0, 5, 0);
		gbc_lblClientInformation.gridx = 3;
		gbc_lblClientInformation.gridy = 0;
		clientInfoPanel.add(lblClientInformation, gbc_lblClientInformation);

		JLabel lblNewLabel_5 = new JLabel("Clinet ID");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 2;
		clientInfoPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		clientIDCMS = new JTextField();
		clientIDCMS.setEditable(false);
		GridBagConstraints gbc_clientIDCMS = new GridBagConstraints();
		gbc_clientIDCMS.anchor = GridBagConstraints.NORTHWEST;
		gbc_clientIDCMS.insets = new Insets(0, 0, 5, 0);
		gbc_clientIDCMS.gridwidth = 2;
		gbc_clientIDCMS.gridx = 2;
		gbc_clientIDCMS.gridy = 2;
		clientInfoPanel.add(clientIDCMS, gbc_clientIDCMS);
		clientIDCMS.setColumns(10);

		JLabel lblFirstName = new JLabel("First Name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 3;
		clientInfoPanel.add(lblFirstName, gbc_lblFirstName);

		fnameCMS = new JTextField();
		fnameCMS.setColumns(10);
		GridBagConstraints gbc_fnameCMS = new GridBagConstraints();
		gbc_fnameCMS.anchor = GridBagConstraints.NORTHWEST;
		gbc_fnameCMS.insets = new Insets(0, 0, 5, 0);
		gbc_fnameCMS.gridwidth = 2;
		gbc_fnameCMS.gridx = 2;
		gbc_fnameCMS.gridy = 3;
		clientInfoPanel.add(fnameCMS, gbc_fnameCMS);

		JLabel lblLastName_1 = new JLabel("Last Name");
		GridBagConstraints gbc_lblLastName_1 = new GridBagConstraints();
		gbc_lblLastName_1.anchor = GridBagConstraints.WEST;
		gbc_lblLastName_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName_1.gridx = 1;
		gbc_lblLastName_1.gridy = 4;
		clientInfoPanel.add(lblLastName_1, gbc_lblLastName_1);

		lNameCMS = new JTextField();
		lNameCMS.setColumns(10);
		GridBagConstraints gbc_lNameCMS = new GridBagConstraints();
		gbc_lNameCMS.anchor = GridBagConstraints.NORTHWEST;
		gbc_lNameCMS.insets = new Insets(0, 0, 5, 0);
		gbc_lNameCMS.gridwidth = 2;
		gbc_lNameCMS.gridx = 2;
		gbc_lNameCMS.gridy = 4;
		clientInfoPanel.add(lNameCMS, gbc_lNameCMS);

		JLabel lblAddress_1 = new JLabel("Address");
		GridBagConstraints gbc_lblAddress_1 = new GridBagConstraints();
		gbc_lblAddress_1.anchor = GridBagConstraints.WEST;
		gbc_lblAddress_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress_1.gridx = 1;
		gbc_lblAddress_1.gridy = 5;
		clientInfoPanel.add(lblAddress_1, gbc_lblAddress_1);

		addressCMS = new JTextField();
		addressCMS.setColumns(10);
		GridBagConstraints gbc_addressCMS = new GridBagConstraints();
		gbc_addressCMS.anchor = GridBagConstraints.NORTHWEST;
		gbc_addressCMS.insets = new Insets(0, 0, 5, 0);
		gbc_addressCMS.gridwidth = 2;
		gbc_addressCMS.gridx = 2;
		gbc_addressCMS.gridy = 5;
		clientInfoPanel.add(addressCMS, gbc_addressCMS);

		JLabel lblPostalCode = new JLabel("Postal Code");
		GridBagConstraints gbc_lblPostalCode = new GridBagConstraints();
		gbc_lblPostalCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPostalCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostalCode.gridx = 1;
		gbc_lblPostalCode.gridy = 6;
		clientInfoPanel.add(lblPostalCode, gbc_lblPostalCode);

		postalCodeCMS = new JTextField();
		postalCodeCMS.setColumns(10);
		GridBagConstraints gbc_postalCodeCMS = new GridBagConstraints();
		gbc_postalCodeCMS.anchor = GridBagConstraints.NORTHWEST;
		gbc_postalCodeCMS.insets = new Insets(0, 0, 5, 0);
		gbc_postalCodeCMS.gridwidth = 2;
		gbc_postalCodeCMS.gridx = 2;
		gbc_postalCodeCMS.gridy = 6;
		clientInfoPanel.add(postalCodeCMS, gbc_postalCodeCMS);

		JLabel lblPhoneNumber_1 = new JLabel("Phone Number");
		GridBagConstraints gbc_lblPhoneNumber_1 = new GridBagConstraints();
		gbc_lblPhoneNumber_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPhoneNumber_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNumber_1.gridx = 1;
		gbc_lblPhoneNumber_1.gridy = 7;
		clientInfoPanel.add(lblPhoneNumber_1, gbc_lblPhoneNumber_1);

		phoneCMS = new JTextField();
		phoneCMS.setColumns(10);
		GridBagConstraints gbc_phoneCMS = new GridBagConstraints();
		gbc_phoneCMS.anchor = GridBagConstraints.NORTHWEST;
		gbc_phoneCMS.insets = new Insets(0, 0, 5, 0);
		gbc_phoneCMS.gridwidth = 2;
		gbc_phoneCMS.gridx = 2;
		gbc_phoneCMS.gridy = 7;
		clientInfoPanel.add(phoneCMS, gbc_phoneCMS);

		JLabel lblClientType = new JLabel("Client Type");
		GridBagConstraints gbc_lblClientType = new GridBagConstraints();
		gbc_lblClientType.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblClientType.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientType.gridx = 1;
		gbc_lblClientType.gridy = 9;
		clientInfoPanel.add(lblClientType, gbc_lblClientType);

		comboBoxClientMgmnt = new JComboBox();
		comboBoxClientMgmnt.setModel(new DefaultComboBoxModel(new String[] { "R", "C" }));
		GridBagConstraints gbc_comboBoxClientMgmnt = new GridBagConstraints();
		gbc_comboBoxClientMgmnt.anchor = GridBagConstraints.NORTHEAST;
		gbc_comboBoxClientMgmnt.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxClientMgmnt.gridx = 2;
		gbc_comboBoxClientMgmnt.gridy = 9;
		clientInfoPanel.add(comboBoxClientMgmnt, gbc_comboBoxClientMgmnt);

		saveBtn = new JButton("Save");
		GridBagConstraints gbc_saveBtn = new GridBagConstraints();
		gbc_saveBtn.anchor = GridBagConstraints.NORTHEAST;
		gbc_saveBtn.insets = new Insets(0, 0, 0, 5);
		gbc_saveBtn.gridx = 0;
		gbc_saveBtn.gridy = 11;
		clientInfoPanel.add(saveBtn, gbc_saveBtn);
		saveBtn.addActionListener(this);

		deleteBtn = new JButton("Delete");
		GridBagConstraints gbc_deleteBtn = new GridBagConstraints();
		gbc_deleteBtn.anchor = GridBagConstraints.NORTH;
		gbc_deleteBtn.insets = new Insets(0, 0, 0, 5);
		gbc_deleteBtn.gridwidth = 2;
		gbc_deleteBtn.gridx = 1;
		gbc_deleteBtn.gridy = 11;
		clientInfoPanel.add(deleteBtn, gbc_deleteBtn);
		deleteBtn.addActionListener(this);

		clearBtn = new JButton("Clear");
		GridBagConstraints gbc_clearBtn = new GridBagConstraints();
		gbc_clearBtn.anchor = GridBagConstraints.NORTHWEST;
		gbc_clearBtn.gridx = 3;
		gbc_clearBtn.gridy = 11;
		clientInfoPanel.add(clearBtn, gbc_clearBtn);
		clearBtn.addActionListener(this);
		list.addListSelectionListener(this);
		addCustomer = new JButton("Add New Customer");
		GridBagConstraints gbc_addCustomer = new GridBagConstraints();
		gbc_addCustomer.gridx = 4;
		gbc_addCustomer.gridy = 11;
		clientInfoPanel.add(addCustomer, gbc_addCustomer);
		addCustomer.addActionListener(this);
	}

    /**
     * Populate list.
     *
     * @param json the json
     * @throws JsonMappingException    the json mapping exception
     * @throws JsonProcessingException the json processing exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public void populateList(String json)  {
		try {
			array = mapper.readValue(json, ObjectNode[].class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
		}
		values = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			values.add(array[i].get("FName").asText() + " " + array[i].get("LName").asText() + " "
					+ array[i].get("Type").asText());
		}
		try {
		list.setModel(new AbstractListModel() {
			
			public int getSize() {
				return values.size();
			}

			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
		}catch(Exception e) {
			
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchClient) {
			populateList("[]");
			if (option == 1) {
				// using ID
				String request = "{\"type\":\"GET\",\"table\":\"CLIENT\",\"scope\":\"select\",\"field\":\"ClientID\",\"field_value\":\""
						+ search.getText() + "\"}";
				String response = mc.request(request);
				if (response.length() > 3) {
					populateList(response);
				} else
					JOptionPane.showMessageDialog(null, "Client Not Found");
			} else if (option == 2) {
				// using lname
				String request = "{\"type\":\"GET\",\"table\":\"CLIENT\",\"scope\":\"select\",\"field\":\"LName\",\"field_value\":\""
						+ search.getText() + "\"}";
				String response = mc.request(request);
				if (response.length() > 3) {
					populateList(response);
				} else
					JOptionPane.showMessageDialog(null, "Client Not Found");
			} else if (option == 3) {
				// using type
				String request = "{\"type\":\"GET\",\"table\":\"CLIENT\",\"scope\":\"select\",\"field\":\"Type\",\"field_value\":\""
						+ search.getText() + "\"}";
				String response = mc.request(request);
				if (response.length() > 3) {
					populateList(response);
				} else
					JOptionPane.showMessageDialog(null, "Client Not Found!!");
			} else {
				JOptionPane.showMessageDialog(null, "Select a option");
			}
		} else if (e.getSource() == saveBtn) {
			if (!empty) {
				String type = "";
				if (comboBoxClientMgmnt.getSelectedIndex() == 0)
					type = "Residential";
				else if (comboBoxClientMgmnt.getSelectedIndex() == 1)
					type = "Commercial";
				String request = "{\"type\":\"POST\",\"table\":\"CLIENT\",\"ClientID\":\"" + clientIDCMS.getText()
						+ "\",\"LName\":\"" + lNameCMS.getText() + "\",\"FName\":\"" + fnameCMS.getText()
						+ "\",\"Type\":\"" + type + "\",\"PhoneNum\":\"" + phoneCMS.getText() + "\",\"Address\":\""
						+ addressCMS.getText() + "\",\"PostalCode\":\"" + postalCodeCMS.getText() + "\"}";
				mc.request(request);
				// clearing fields/list
				clientIDCMS.setText("");
				fnameCMS.setText("");
				lNameCMS.setText("");
				addressCMS.setText("");
				postalCodeCMS.setText("");
				phoneCMS.setText("");
				comboBoxClientMgmnt.setSelectedIndex(-1);
				JOptionPane.showMessageDialog(null, "Customer Updated");
				populateList("[]");
			} else
				JOptionPane.showMessageDialog(null, "Search for client first");
		} else if (e.getSource() == deleteBtn) {
			if (!empty) {
				String request = "{\"type\":\"DELETE\",\"table\":\"CLIENT\",\"field\":\"ClientID\",\"field_value\":\""
						+ clientIDCMS.getText() + "\"}";
				mc.request(request);
				// clearing fields/list
				empty = true;
				clientIDCMS.setText("");
				fnameCMS.setText("");
				lNameCMS.setText("");
				addressCMS.setText("");
				postalCodeCMS.setText("");
				phoneCMS.setText("");
				comboBoxClientMgmnt.setSelectedIndex(-1);
				JOptionPane.showMessageDialog(null, "Customer Deleted");
				populateList("[]");

			} else
				JOptionPane.showMessageDialog(null, "Search for client first");

		} else if (e.getSource() == clearBtn) {
			empty = true;
			clientIDCMS.setText("");
			fnameCMS.setText("");
			lNameCMS.setText("");
			addressCMS.setText("");
			postalCodeCMS.setText("");
			phoneCMS.setText("");
			comboBoxClientMgmnt.setSelectedIndex(-1);

		} else if (e.getSource() == clientID) {
			this.option = 1;
		} else if (e.getSource() == lName) {
			this.option = 2;
		} else if (e.getSource() == clientType) {
			this.option = 3;
		} else if (e.getSource() == clearSearch) {
			empty = true;
			search.setText("");
			populateList("[]");
		} else if (e.getSource() == addCustomer) {
			if (lNameCMS.getText().isEmpty() || fnameCMS.getText().isEmpty()
					|| comboBoxClientMgmnt.getSelectedIndex() == -1 || phoneCMS.getText().isEmpty()
					|| addressCMS.getText().isEmpty() || postalCodeCMS.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Please fill all fields prior to creating new customer");
			else {
				String type = "";
				if (comboBoxClientMgmnt.getSelectedIndex() == 0)
					type = "Residential";
				else if (comboBoxClientMgmnt.getSelectedIndex() == 1)
					type = "Commercial";
				int id = generateCustomerID();
				clientIDCMS.setText(id + "");
				String request = "{\"type\":\"PUT\",\"table\":\"CLIENT\",\"ClientID\":\"" + clientIDCMS.getText()
						+ "\",\"LName\":\"" + lNameCMS.getText() + "\",\"FName\":\"" + fnameCMS.getText()
						+ "\",\"Type\":\"" + type + "\",\"PhoneNum\":\"" + phoneCMS.getText() + "\",\"Address\":\""
						+ addressCMS.getText() + "\",\"PostalCode\":\"" + postalCodeCMS.getText() + "\"}";
				mc.request(request);
				// clearing fields/list
				empty = true;
				clientIDCMS.setText("");
				fnameCMS.setText("");
				lNameCMS.setText("");
				addressCMS.setText("");
				postalCodeCMS.setText("");
				phoneCMS.setText("");
				comboBoxClientMgmnt.setSelectedIndex(-1);
				JOptionPane.showMessageDialog(null, "Customer Added");
			}

		}
	}

	/**
	 * This method if for the JList , it will populate the fields in Client info
	 * panel
	 */
	public void valueChanged(ListSelectionEvent e) {

		empty = false;
		ObjectNode dummy = array[list.getSelectedIndex()];
		clientIDCMS.setText(dummy.get("ClientID").asText());
		fnameCMS.setText(dummy.get("FName").asText());
		lNameCMS.setText(dummy.get("LName").asText());
		addressCMS.setText(dummy.get("Address").asText());
		postalCodeCMS.setText(dummy.get("PostalCode").asText());
		phoneCMS.setText(dummy.get("PhoneNum").asText());
		if (dummy.get("Type").asText().equals("Residential"))
			comboBoxClientMgmnt.setSelectedIndex(0);
		else
			comboBoxClientMgmnt.setSelectedIndex(1);
	}

    /**
     * Generate customer id int.
     *
     * @return the int
     */
    public int generateCustomerID(){
	        Random r = new Random( System.currentTimeMillis() );
	        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	    }

}
