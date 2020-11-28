package client.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import client.controller.Observer;

/**
 * The type Login panel.
 */
public class LoginPanel extends JPanel implements ActionListener {
 private Observer mc;
private JTextField userNameField;
private JTextField passwordField;
private JButton loginBtn;
    /**
     * The Login status.
     */
    boolean loginStatus=false;
private JsonParser respose;
private CardLayout c;
private JPanel panel;

    /**
     * Instantiates a new Login panel.
     *
     * @param mc    the mc
     * @param c     the c
     * @param panel the panel
     */
    public LoginPanel(Observer mc, CardLayout c, JPanel panel) {
	 this.mc=mc;
	 this.c=c;
	 this.panel=panel;
	 
	setLayout(null);
		
		JLabel userNamelbl = new JLabel("User Name");
		userNamelbl.setBounds(222, 96, 85, 16);
		add(userNamelbl);
		
		userNameField = new JTextField();
		userNameField.setBounds(319, 93, 116, 22);
		add(userNameField);
		userNameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(222, 141, 85, 16);
		add(lblPassword);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(319, 138, 116, 22);
		add(passwordField);
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(267, 209, 97, 25);
		add(loginBtn);
		loginBtn.addActionListener(this);
 }

public void actionPerformed(ActionEvent e) {
		String request="{\"type\":\"GET\",\"table\":\"USER\",\"scope\":\"select\",\"field\":\"username\",\"field_value\":\""+userNameField.getText()+"\"}";
		String response=mc.request(request);
		if(response.length()>3) {
			try {
				JsonNode node =new ObjectMapper().readTree(response);
				
				if(node.get("password").asText().equals(passwordField.getText()))
					{c.show(panel, "CMS");
					loginStatus=true;
					}
					else 
						JOptionPane.showMessageDialog(null,"Wrong login !!");
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else
			JOptionPane.showMessageDialog(null,"Wrong login !!");
	}


    /**
     * Is login status boolean.
     *
     * @return the boolean
     */
    public boolean isLoginStatus() {
	return loginStatus;
}

    /**
     * Sets login status.
     *
     * @param loginStatus the login status
     */
    public void setLoginStatus(boolean loginStatus) {
	this.loginStatus = loginStatus;
}
}
