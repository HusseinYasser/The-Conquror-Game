package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Name extends JPanelWithBackground implements ActionListener  {
	
	private JButton confirm;
	private JComboBox <String> names;
	private JLabel label;
	private JTextArea text;
	private NameListener listener;
	
	public JTextArea getText() {
		return text;
	}
	
	public JComboBox getNames() {
		return names;
	}
	

	public Name() throws IOException {
		super("NameW.jpg");
		this.setLayout(null);
		//ImageIcon img = new ImageIcon(new ImageIcon("NameW.jpg").getImage().getScaledInstance(900, 523, Image.SCALE_DEFAULT));
		confirm = new JButton();
		confirm.setBounds(350, 450, 100, 25);
		confirm.setText("Confirm");
		confirm.addActionListener(this);
	    String[]cities = {"Select your City","Cairo","Rome","Sparta"};
	    names = new JComboBox<String>(cities);
	    names.addActionListener(this);
	    names.setBounds(500,350,200,25);
	    label = new JLabel();
	    label.setBounds(100,350,100,25);
	    label.setForeground(Color.white);
	    label.setText("Player Name:");
	    text=new JTextArea();
	    text.setBounds(200,350,100,25);
	    text.setFont(new Font("MV Boli",Font.PLAIN,17));
	    this.add(text);
	    this.add(label);
	    this.add(names);
		this.add(confirm);
		this.revalidate();
		this.repaint();
	}
	
	public void setNameListener(NameListener x) {
		listener = x;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean allSet = true;
		if(e.getSource() == confirm) {
			if(text.getText().equals("")) {
				JLabel error = new JLabel();
				error.setText("** Required");
				error.setForeground(Color.RED);
				error.setBounds(100,400,200,25);
				this.add(error);
				this.revalidate();
				this.repaint();
				allSet = false;
			}
			if(names.getSelectedItem() == "Select your City") {
				JLabel error = new JLabel();
				error.setText("** Required");
				error.setForeground(Color.RED);
				error.setBounds(715,350,200,25);
				this.add(error);
				this.revalidate();
				this.repaint();
				allSet = false;
			}
			else if(allSet){
				listener.onConfirm();
			}
		
	     }
	}
	
	

}
