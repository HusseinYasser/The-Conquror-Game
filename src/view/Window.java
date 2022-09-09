package view;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame implements KeyListener {
	
	private StartListener listener;
	
	public Window(){
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    ImageIcon img = new ImageIcon("game11.png");
		this.setTitle("The Conqurer");
		this.setIconImage(img.getImage());
		JLabel panel = new JLabel();
		img = new ImageIcon("ConqLast.png");
		panel.setPreferredSize(new Dimension(img.getIconWidth(),img.getIconHeight()));
		this.setBounds(200,100,img.getIconWidth(),img.getIconHeight());
		panel.setIcon(img);
		this.addKeyListener(this);
		this.add(panel);
		this.revalidate();
		this.repaint();
	}
	
	public void setStartListener(StartListener x) {
		listener = x;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			listener.onEnter(this);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
