package client.controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.Box;
import javax.swing.ButtonGroup;

import java.awt.Insets;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import client.view.CMS;
import client.view.InventoryPanel;
import client.view.LoginPanel;

import javax.swing.AbstractListModel;
/**
 * This class is the main controller for all of the GUI controlleres , it is responsible of crearitn all the necessary panel nedde for the 
 * gui
 * @author zchem
 *
 */
public class ViewController {

	private JFrame frame;
	private Observer mc;
	private InventoryPanel inventoryPanel;
	private LoginPanel loginPanel;

	/**
	 * Ctor
	 * @param mc
	 */

    /**
     * Create the application.
     *
     * @param mc the mc
     */
    public ViewController(Observer mc) {
		this.mc=mc;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1362, 692);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu inventory = new JMenu("Inventory GUI");
		menuBar.add(inventory);
		
		JMenu clientGuiMenu = new JMenu("Client GUI");
		menuBar.add(clientGuiMenu);
		
		final JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		final CardLayout c=new CardLayout(0,0);
		panel.setLayout(c);
		
		 inventoryPanel = new InventoryPanel(mc);
		inventoryPanel.setBackground(SystemColor.inactiveCaption);
		panel.add(inventoryPanel, "inventory");
		
	
		
		
		
		
		CMS clientMgmntPanel = new CMS(mc);
		panel.add(clientMgmntPanel, "CMS");
		
		loginPanel=new LoginPanel(mc,c,panel);
		loginPanel.setBackground(SystemColor.inactiveCaption);
		panel.add(loginPanel,"login");
		
		
	
		frame.setVisible(true);
		inventory.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				c.show(panel, "inventory");
			}
		});
			
			
		
		clientGuiMenu.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				if(loginPanel.isLoginStatus()==false)
				c.show(panel,"login");
				else
					c.show(panel, "CMS");
				
			}
		});
	}
}
