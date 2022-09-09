package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import engine.Game;
import units.Archer;
import units.Infantry;
import units.Status;

public class IdleArmy extends JFrame implements ActionListener,MouseListener{
	
	ArrayList <JLabel> armies;
	
	ArmyClickListener listener;
	public void setListener(ArmyClickListener listener) {
		this.listener = listener;
	}

	public IdleArmy(Game g) {
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Idle Armie");
		this.setIconImage((new ImageIcon("game11.png")).getImage());
		this.setSize(new Dimension(500,600));
		this.setLayout(new FlowLayout());
		armies = new ArrayList<JLabel>();
		int counter =1;
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		for(int i=0;i<g.getPlayer().getControlledArmies().size();i++) {
			JLabel x = new JLabel();
			x.addMouseListener(this);
			ImageIcon img = new ImageIcon("tesss.png");
			x.setIcon(img);
			x.setText("Army"+counter);
			x.setVerticalTextPosition(JLabel.NORTH);
			x.setHorizontalTextPosition(JLabel.CENTER);
			x.setFont(new Font("Serif Bold",Font.PLAIN,17));
			x.setIconTextGap(15);
			armies.add(x);
			this.add(x);
			counter++;
		}
		
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<armies.size();i++) {
			if(e.getSource() == armies.get(i)) {
				listener.onArmy(i);
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
