package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import engine.City;
import engine.Game;
import engine.Player;
import exceptions.FriendlyCityException;
import exceptions.TargetNotReachedException;
import units.Army;

public class ReachedTarget extends JPanelWithBackground implements ActionListener {
	
	private JButton LaySiege;
	private JButton Manual;
	private JButton AutoResolve;
	private Army army;
	private Player player;
	private City city;
	private JFrame jf;
	private Game g;
	private JLabel know;
	private boolean check;
	
	public ReachedTarget(JFrame jf,Player player,Army army,City city,Game g,boolean check) throws IOException {
		super("UnitsBackGround.jpg");
		this.army = army;
		this.city = city;
		this.jf = jf;
		this.g=g;
		this.check = check;
		this.player = player;
		this.setLayout(null);
		LaySiege = new JButton();
		Manual = new JButton();
		AutoResolve = new JButton();
		LaySiege.setText("Lay Siege");
		LaySiege.setBounds(50,200,200,50);
		LaySiege.setForeground(Color.WHITE);
		LaySiege.setBackground(Color.BLACK);
		LaySiege.setFont(new Font("Serif Bold",Font.BOLD,17));
		LaySiege.addActionListener(this);
		Manual.setText("Manual Attack");
		Manual.setBounds(300,200,200,50);
		Manual.setForeground(Color.WHITE);
		Manual.setBackground(Color.BLACK);
		Manual.setFont(new Font("Serif Bold",Font.BOLD,17));
		Manual.addActionListener(this);
		AutoResolve.setText("Auto Resolve");
		AutoResolve.setBounds(550,200,200,50);
		AutoResolve.setForeground(Color.WHITE);
		AutoResolve.setBackground(Color.BLACK);
		AutoResolve.setFont(new Font("Serif Bold",Font.BOLD,17));
		AutoResolve.addActionListener(this);
		know = new JLabel();
		if(!check)
			know.setText("One Of Your Army Has Reached It's Target "+city.getName());
		else
			know.setText(city.getName()+" has reached 3 Turns UnderSiege");
		know.setFont(new Font("Serif Bold",Font.BOLD,25));
		know.setForeground(Color.black);
		know.setBounds(100, 100, 1000, 100);
		know.setOpaque(false);
		this.add(know);
		this.add(LaySiege);
		this.add(Manual);
		this.add(AutoResolve);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == LaySiege && !check) {
			try {
				player.laySiege(army,city);
				JOptionPane.showMessageDialog(this, "You Have Layed Siege On "+city.getName(),
				         "Laying Siege", JOptionPane.INFORMATION_MESSAGE);
				jf.dispose();
			} catch (TargetNotReachedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Your Army Haven't Reached The Target Yet",
				         "Target Failure", JOptionPane.ERROR_MESSAGE);
			} catch (FriendlyCityException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "You Can't LaySiege On one of Your Controlled Cities!",
				         "Siege Failure", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(e.getSource() == LaySiege && check) {
			JOptionPane.showMessageDialog(this, "You Can't LaySiege On already Sieged City!",
			         "Siege Failure", JOptionPane.ERROR_MESSAGE);
		}
		else if(e.getSource() == Manual) {
			JFrame x = new JFrame();
			x.setVisible(true);
			x.setSize(910, 523);
			x.setTitle("Manual Attack");
			x.setResizable(false);
			x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			BattleView b = null;
			try {
				b = new BattleView(army,city.getDefendingArmy(),"Manual",g,x);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			x.add(b);
			jf.dispose();
		}
		else if(e.getSource() == AutoResolve) {
			JFrame x = new JFrame();
			x.setVisible(true);
			x.setSize(910, 523);
			x.setTitle("Manual Attack");
			x.setResizable(false);
			x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			BattleView b = null;
			try {
				b = new BattleView(army,city.getDefendingArmy(),"AutoResolve",g,x);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			x.add(b);
			jf.dispose();
		}
	}

}
