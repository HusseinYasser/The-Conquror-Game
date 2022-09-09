package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import engine.Game;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;



public class BattleView extends JPanelWithBackground implements ActionListener ,MouseListener, onChooseUnitListener {

	private Army attack;
	private Army defend;
	private JLabel label;
	private JLabel label2;
	private JLabel jTA;
	private String type;
	private JLabel unitLeft;
	private JLabel unitRight;
	private JLabel underAttack;
	private JLabel warning;
	private JButton Continue;
	private JLabel WorldMap;
	private JLabel AutoResolve;
	private Game g;
	private JFrame jf;
	
	public BattleView (Army attack , Army defend,String type,Game g,JFrame jf ) throws IOException {
	super("BAAAAtle.jpg");
	this.jf = jf;
	this.attack = attack;
	this.defend = defend;
	this.type = type;
	this.g = g;
	this.setLayout(null);
	jTA = new JLabel();
	jTA.setOpaque(true);
	label = new JLabel() ;
	label.setIcon(new ImageIcon("CityArmies.jpg"));
	label.setVisible(true);
	label.setBounds(30, 30, 150, 100);;
	label.setText("Choose Unit");
	label.setForeground(Color.white);
	label.setVerticalTextPosition(JLabel.BOTTOM);
	label.setHorizontalTextPosition(JLabel.CENTER);
	label.addMouseListener(this);
	label2 = new JLabel();
	label2.setIcon(new ImageIcon("CityArmies.jpg"));
	label2.setVisible(true);
	label2.setBounds(780, 30, 150, 100);
	label2.setText("Show Enemy");
	label2.setForeground(Color.white);
	label2.setVerticalTextPosition(JLabel.BOTTOM);
	label2.setHorizontalTextPosition(JLabel.CENTER);
	label2.addMouseListener(this);
	this.add(label2);
	unitLeft = new JLabel();
	unitLeft.setVisible(false);
	unitRight = new JLabel();
	unitRight.setVisible(false);
	underAttack = new JLabel();
	underAttack.setVisible(false);
	warning = new JLabel();
	warning.setVisible(false);
	Continue = new JButton();
	Continue.setVisible(false);
	Continue.setText("Continue");
	Continue.setForeground(Color.ORANGE);
	Continue.setBounds(350, 30, 150, 25);
	Continue.addActionListener(this);
	WorldMap = new JLabel();
	WorldMap.setBounds(775, 350, 100, 120);
	WorldMap.setText("World Map");
	WorldMap.setHorizontalTextPosition(JLabel.CENTER);
	WorldMap.setVerticalTextPosition(JLabel.BOTTOM);
	WorldMap.setForeground(Color.black);
	WorldMap.setFont(new Font("Serif Bold",Font.BOLD,15));
	WorldMap.setIcon(new ImageIcon("WorldMapButton.png"));
	WorldMap.setIconTextGap(-10);
	WorldMap.addMouseListener(this);
	WorldMap.setVisible(false);
	AutoResolve = new JLabel();
	AutoResolve.setText("Start");
	AutoResolve.setBounds(405,75,150,150);
	AutoResolve.setFont(new Font("Serif Bold",Font.BOLD,25));
	AutoResolve.setForeground(Color.white);
	AutoResolve.setHorizontalTextPosition(JLabel.CENTER);
	AutoResolve.setVerticalTextPosition(JLabel.TOP);
	if(type == "AutoResolve")
		AutoResolve.setVisible(true);
	else
		AutoResolve.setVisible(false);
	AutoResolve.setIcon(new ImageIcon("CityControlledArmies2.png"));
	AutoResolve.setText("Fight");
	AutoResolve.addMouseListener(this);
	this.add(AutoResolve);
	this.add(WorldMap);
	this.add(Continue);
	this.add(label);
	this.revalidate();
	this.repaint();
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == label && !Continue.isVisible()) {
			JFrame jf = new JFrame();
			jf.setTitle("Attacking Unit");
			jf.setVisible(true);
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jf.setSize(910, 523);
			jf.setResizable(false);
			BattleUnits u = null;
			try {
				 if(type.equals("Manual"))
					 u = new BattleUnits(attack,jf,"attack","Manual",g);
				 else
					 u = new BattleUnits(attack,jf,"attack","AutoResolve",g);
			     u.setOnChooseUnitListener(this);
				 jf.add(u);
				 jf.revalidate();
				 jf.repaint();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getSource() == label2  && !Continue.isVisible()) {
			JFrame jf = new JFrame();
			jf.setTitle("Enemy Units");
			jf.setVisible(true);
			jf.setResizable(false);
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jf.setSize(910, 523);
			BattleUnits u = null;
			try {
			     if(type.equals("Manual"))
			    	 u = new BattleUnits(defend,jf,"defend","Manual",g);
			     else
			    	 u = new BattleUnits(defend,jf,"defend","AutoResolve",g);
				 jf.add(u);
				 jf.revalidate();
				 jf.repaint();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == AutoResolve && AutoResolve.isVisible()) {
			try {
				g.autoResolve(attack, defend);
				if(attack.getUnits().size() == 0) {
					JOptionPane.showMessageDialog(this, "Your Army has been Taken Down! And This Army is Removed From Your Controlled Armies",
					         "DAMAGE Alert", JOptionPane.INFORMATION_MESSAGE);
					g.getPlayer().getControlledArmies().remove(attack);
				}
				else if(defend.getUnits().size() == 0) {
					JOptionPane.showMessageDialog(this, "<html>You Have Beaten The Enemy!+<BR>"
							+ "You Have Occupied "+attack.getCurrentLocation()+"</html>",
					         "Attack Alert", JOptionPane.ERROR_MESSAGE);
				}
				WorldMap.setVisible(true);
			} catch (FriendlyFireException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "You Are Attacking a Friend Army!",
				         "Attack Alert", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == WorldMap && WorldMap.isVisible()) {
			jf.dispose();
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
	
	@Override
	public void onChooseUnit(Unit u,JFrame jf) {
		// TODO Auto-generated method stub
		jf.dispose();
		if(defend.getUnits().size() == 0) {
		        JOptionPane.showMessageDialog(this, "You Have Won The Battle And Occupied "+defend.getCurrentLocation(),
				         "WON", JOptionPane.INFORMATION_MESSAGE);
				WorldMap.setVisible(true);
				g.occupy(attack, defend.getCurrentLocation());
				return;
		}
		warning.setText("");
		if(defend.getUnits().size() == 0) {
			JOptionPane.showMessageDialog(this, "You Won The Battle!",
			         "Attack Alert", JOptionPane.INFORMATION_MESSAGE);
			WorldMap.setVisible(true);
			return;
		}
		Random Rind = new Random();
		int r = Rind.nextInt(defend.getUnits().size());
		Unit defender = defend.getUnits().get(r);
		int old = 0;
		int neww = 0;
	    try {
	    	old = defender.getCurrentSoldierCount();
			u.attack(defender);
			neww = defender.getCurrentSoldierCount();
			String type1 ;
		    if(u instanceof Archer)
		    	type1 = "Archery";
		    else if(u instanceof Infantry)
		    	type1 = "Infantry";
		    else
		    	type1 = "Cavalary";
		    String type2;
		    if(defender instanceof Archer)
		    	type2 = "Archery";
		    else if(defender instanceof Infantry)
		    	type2 = "Infantry";
		    else
		    	type2 = "Cavalary";
		    unitLeft.setIcon(new ImageIcon(type1+"UnitAttack.png"));
		    unitRight.setIcon(new ImageIcon(type2+"UnitDefend.png"));
		    unitLeft.setBounds(0,150,250,200);
		    unitRight.setBounds(700,150,200,200);
		    unitLeft.setVisible(true);
		    unitRight.setVisible(true);
			this.add(unitLeft);
			this.add(unitRight);
		    jTA.setText("Enemy's Unit Decreased by "+(old-neww));
			jTA.setForeground(Color.ORANGE);
			jTA.setBounds(250,425,1000,50);
			jTA.setFont(new Font("Serif Bold",Font.BOLD,25));
			jTA.setOpaque(false);
			this.add(jTA);
			Continue.setVisible(true);
			
			this.revalidate();
			this.repaint();
		} catch (FriendlyFireException e) {
				// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "You are Attacking a FriendlyUnit!",
			         "Attack Alert", JOptionPane.ERROR_MESSAGE);
		}
		if(defend.getUnits().size() == 0) {
	        JOptionPane.showMessageDialog(this, "You Have Won The Battle And Occupied "+defend.getCurrentLocation(),
			         "WON", JOptionPane.INFORMATION_MESSAGE);
			WorldMap.setVisible(true);
			g.occupy(attack, defend.getCurrentLocation());
			return;
	    }
	}
	
	public void EnemyAttack()  {
		if(attack.getUnits().size() == 0) {
			JOptionPane.showMessageDialog(this, "Your Army has been Taken Down! And This Army is Removed From Your Controlled Armies",
			         "DAMAGE Alert", JOptionPane.INFORMATION_MESSAGE);
			WorldMap.setVisible(true);
			g.getPlayer().getControlledArmies().remove(attack);
			return;
		}
		Random rand = new Random();
		int r = rand.nextInt(defend.getUnits().size());
		int r2 = rand.nextInt(attack.getUnits().size());
		Unit defender = defend.getUnits().get(r);
		Unit attacker = attack.getUnits().get(r2);
		try {
			int old = attacker.getCurrentSoldierCount();
			defender.attack(attacker);
			int neww = attacker.getCurrentSoldierCount();
			warning.setText("You are Being Attacked NOW!");
			warning.setForeground(Color.red);
			warning.setFont(new Font("Serif Bold",Font.BOLD,25));
			warning.setBounds(250, 100, 400, 50);
			warning.setVisible(true);
			this.add(warning);
			String type1 ;
		    if(attacker instanceof Archer)
		    	type1 = "Archery";
		    else if(attacker instanceof Infantry)
		    	type1 = "Infantry";
		    else
		    	type1 = "Cavalary";
		    String type2;
		    if(defender instanceof Archer)
		    	type2 = "Archery";
		    else if(defender instanceof Infantry)
		    	type2 = "Infantry";
		    else
		    	type2 = "Cavalary";
		    unitLeft.setIcon(new ImageIcon(type1+"UnitAttack.png"));
		    unitRight.setIcon(new ImageIcon(type2+"UnitDefend.png"));
		    unitLeft.setBounds(0,150,250,200);
		    unitRight.setBounds(700,150,200,200);
		    unitLeft.setVisible(true);
		    unitRight.setVisible(true);
			this.add(unitLeft);
			this.add(unitRight);
			jTA.setText("Your Unit Decreased by "+(old-neww));
			jTA.setForeground(Color.ORANGE);
			jTA.setBounds(275,425,1000,50);
			jTA.setFont(new Font("Serif Bold",Font.BOLD,25));
			jTA.setOpaque(false);
			this.add(jTA);
			this.revalidate();
			this.repaint();
		} catch (FriendlyFireException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(attack.getUnits().size() == 0) {
			JOptionPane.showMessageDialog(this, "Your Army has been Taken Down! And This Army is Removed From Your Controlled Armies",
			         "DAMAGE Alert", JOptionPane.INFORMATION_MESSAGE);
			WorldMap.setVisible(true);
			g.getPlayer().getControlledArmies().remove(attack);
			return;
		}
	}
	
	public void AutoResolveAttack() {
		if(defend.getUnits().size() == 0) {
			JOptionPane.showMessageDialog(this, "You Won The Battle!",
			         "Attack Alert", JOptionPane.INFORMATION_MESSAGE);
			WorldMap.setVisible(true);
			return;
		}
		Random Rind = new Random();
		int r = Rind.nextInt(defend.getUnits().size());
		Unit defender = defend.getUnits().get(r);
		r = Rind.nextInt(attack.getUnits().size());
		Unit u = attack.getUnits().get(r);
		int old = 0;
		int neww = 0;
	    try {
	    	old = defender.getCurrentSoldierCount();
			u.attack(defender);
			neww = defender.getCurrentSoldierCount();
			String type1 ;
		    if(u instanceof Archer)
		    	type1 = "Archery";
		    else if(u instanceof Infantry)
		    	type1 = "Infantry";
		    else
		    	type1 = "Cavalary";
		    String type2;
		    if(defender instanceof Archer)
		    	type2 = "Archery";
		    else if(defender instanceof Infantry)
		    	type2 = "Infantry";
		    else
		    	type2 = "Cavalary";
		    unitLeft.setIcon(new ImageIcon(type1+"UnitAttack.png"));
		    unitRight.setIcon(new ImageIcon(type2+"UnitDefend.png"));
		    unitLeft.setBounds(0,150,250,200);
		    unitRight.setBounds(700,150,200,200);
		    unitLeft.setVisible(true);
		    unitRight.setVisible(true);
			this.add(unitLeft);
			this.add(unitRight);
		    jTA.setText("Enemy's Unit Decreased by "+(old-neww));
			jTA.setForeground(Color.ORANGE);
			jTA.setBounds(250,425,1000,50);
			jTA.setFont(new Font("Serif Bold",Font.BOLD,25));
			jTA.setOpaque(false);
			this.add(jTA);
			Continue.setVisible(true);
			
			this.revalidate();
			this.repaint();
		} catch (FriendlyFireException e) {
				// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "You are Attacking a FriendlyUnit!",
			         "Attack Alert", JOptionPane.ERROR_MESSAGE);
		}
		if(defend.getUnits().size() == 0) {
			JOptionPane.showMessageDialog(this, "You Won The Battle!",
			         "Attack Alert", JOptionPane.INFORMATION_MESSAGE);
			WorldMap.setVisible(true);
			return;
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == Continue && Continue.isVisible() && !WorldMap.isVisible()) {
			this.EnemyAttack();
			Continue.setVisible(false);
			this.revalidate();
			this.repaint();
		}
		
	}
}
