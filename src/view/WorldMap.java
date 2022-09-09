package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import control.Controller;
import engine.City;
import engine.Game;
import units.Army;
import units.Status;


public class WorldMap extends JPanelWithBackground implements ActionListener,MouseListener{
	private JLabel foodValue;
	private JLabel goldValue;
	private JLabel name;
	private JLabel food;
	private JLabel gold;
	private JButton cairo;
	private JButton rome;
	private JButton sparta;
	private JButton myArmies;
	private MyArmyListener myArmyListener;
	private Game g;
	private CityListener cityListener;
	private JLabel CurrentCount;
	private JLabel EndTurn;
	private EndTurnListener endTurnListener;
	private Controller x;

	public WorldMap(Controller x) throws IOException   {
		super("Map2New.jpg");
		// TODO Auto-generated constructor stub
		g = x.getGame();
		this.x = x;
		cairo = new JButton();
		rome = new JButton();
		sparta = new JButton();
		cairo.addActionListener(this);
		rome.addActionListener(this);
		sparta.addActionListener(this);
		cairo.setText("Cairo");
		sparta.setText("Sparta");
		rome.setText("Rome");
		cairo.setBounds(600,300,100,25);
		rome.setBounds(500,100,100,25);
		sparta.setBounds(150,300,100,25);
		cairo.setOpaque(true);
		rome.setOpaque(true);
		sparta.setOpaque(true);
		name = new JLabel();
	    name.setBounds(725,10,300,15);
	    name.setFont(new Font("Serif Bold",Font.BOLD,15));
	    name.setForeground(Color.white);
	    name.setText("Player Name: "+x.getGame().getPlayer().getName());
	    ImageIcon GoldImg = new ImageIcon("GOOOLD.png");
	    ImageIcon FoodImg = new ImageIcon("FOOOD.png");
	    food = new JLabel();
	    food.setIcon(FoodImg);
	    food.setBounds(850,110,50,70);
	    gold = new JLabel();
	    gold.setIcon(GoldImg);
	    gold.setBounds(850,40,50,70);
	    foodValue = new JLabel();
	    foodValue.setText(x.getGame().getPlayer().getFood()+"");
	    foodValue.setForeground(Color.red);
	    foodValue.setBounds(850,100,50,10);
	    goldValue = new JLabel();
	    goldValue.setText(x.getGame().getPlayer().getTreasury()+"");
	    goldValue.setForeground(Color.yellow);
	    goldValue.setBounds(850,30,50,10);
	    myArmies = new JButton();
	    myArmies.setText("My Armies");
	    myArmies.setBounds(5,10,200,20);
	    myArmies.addActionListener(this);
	    CurrentCount = new JLabel();
	    CurrentCount.setText("Current Turn: "+g.getCurrentTurnCount());
	    CurrentCount.setFont(new Font("Serif Bold",Font.BOLD,17));
	    CurrentCount.setForeground(Color.white);
	    CurrentCount.setOpaque(false);
	    CurrentCount.setBounds(770,180,200,30);
	    EndTurn = new JLabel();
	    EndTurn.setIcon(new ImageIcon("EndTurn2.jpg"));
	    EndTurn.setText("End Turn");
	    EndTurn.setHorizontalTextPosition(JLabel.CENTER);
	    EndTurn.setVerticalTextPosition(JLabel.CENTER);
	    EndTurn.setBounds(800,400,100,50);
	    EndTurn.addMouseListener(this);
	    this.add(EndTurn);
	    this.add(CurrentCount);
        this.add(myArmies);
		this.setLayout(null);
		this.add(goldValue);
		this.add(foodValue);
		this.add(food);
		this.add(gold);
		this.add(name);
		this.add(cairo);
        this.add(rome);
        this.add(sparta);
        this.revalidate();
        this.repaint();
	}
	
	public void setCityListener(CityListener cityListener) {
		this.cityListener = cityListener;
	}

	public void setMyArmyListener(MyArmyListener x) {
		myArmyListener = x;
	}
	
	public void setEndTurnListener(EndTurnListener l) {
		endTurnListener = l;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == myArmies) {
			myArmyListener.onMyArmy();
		}
		else if(e.getSource() == cairo) {
			boolean found = false;
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if(g.getPlayer().getControlledCities().get(i).getName().equals(cairo.getText())) {
					found = true;
					cityListener.onCity("Cairo");
			        break;
				}
			}
			if(!found) {
				JOptionPane.showMessageDialog(this, "You Can Choose Controlled City Only!",
			               "City Error", JOptionPane.ERROR_MESSAGE);
				this.revalidate();
				this.repaint();
			}
		}
		else if(e.getSource() == rome) {
			boolean found = false;
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if(g.getPlayer().getControlledCities().get(i).getName().equals(rome.getText())) {
					found = true;
					cityListener.onCity("Rome");
			        break;
				}
			}
			if(!found) {
				JOptionPane.showMessageDialog(this, "You Can Choose Controlled City Only!",
			               "City Error", JOptionPane.ERROR_MESSAGE);
				this.revalidate();
				this.repaint();
			}
		}
		else if(e.getSource() == sparta) {
			boolean found = false;
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if(g.getPlayer().getControlledCities().get(i).getName().equals(sparta.getText())) {
					found = true;
					cityListener.onCity("Sparta");
			        break;
				}
			}
			System.out.println(found);
			if(!found) {
				JOptionPane.showMessageDialog(this, "You Can Choose Controlled City Only!",
			               "City Error", JOptionPane.ERROR_MESSAGE);
				this.revalidate();
				this.repaint();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == EndTurn) {
			g.endTurn();
			endTurnListener.onEndTurn();
			for(int i=0;i<g.getPlayer().getControlledArmies().size();i++) {
				if(g.getPlayer().getControlledArmies().get(i).getDistancetoTarget()==0) {
					JFrame x = new JFrame();
					g.getPlayer().getControlledArmies().get(i).setCurrentStatus(Status.BESIEGING);
					g.getPlayer().getControlledArmies().get(i).setDistancetoTarget(-1);
					g.getPlayer().getControlledArmies().get(i).setTarget("");
					x.setVisible(true);
					x.setSize(910, 523);
					x.setResizable(false);
					x.setTitle("Target Reached");
					x.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					ReachedTarget r = null;
					City c = null;
					for(int j=0;j<g.getAvailableCities().size();j++) {
						if(g.getAvailableCities().get(j) .getName().equals(g.getPlayer().getControlledArmies().get(i).getCurrentLocation())) {
							c = g.getAvailableCities().get(j);
							break;
						}
					}
					g.getPlayer().getControlledArmies().get(i).setCurrentLocation(c.getName());
					try {
						r = new ReachedTarget(x,g.getPlayer(),g.getPlayer().getControlledArmies().get(i),c,g,false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					x.add(r);
					x.revalidate();
					x.repaint();
					
				}
			}
			for(int i=0;i<g.getAvailableCities().size();i++) {
				if(g.getAvailableCities().get(i).getTurnsUnderSiege()>=3) {
					JFrame x = new JFrame();
					x.setVisible(true);
					x.setSize(910, 523);
					x.setTitle("Maximum Turns UnderSiege");
					x.setResizable(false);
					x.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					Army h = null;
					for(int j=0;j<g.getPlayer().getControlledArmies().size();j++) {
						if(g.getPlayer().getControlledArmies().get(j).getCurrentStatus().equals(Status.BESIEGING)
								&& g.getPlayer().getControlledArmies().get(j).getCurrentLocation().equals(g.getAvailableCities().get(i).getName())) {
							h=g.getPlayer().getControlledArmies().get(j);
						}
					}
					ReachedTarget r = null;
					try {
						r = new ReachedTarget(x,g.getPlayer(),h,g.getAvailableCities().get(i),g,true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					x.add(r);
					x.revalidate();
					x.repaint();
				}
			}
			if(g.getCurrentTurnCount()>50) {
				if(g.getPlayer().getControlledCities().size() != g.getAvailableCities().size()) {
					EndGame end = null;
					try {
						end = new EndGame("GameOver");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					x.getView().getContentPane().removeAll();
					x.getView().add(end);
					x.getView().revalidate();
					x.getView().repaint();
				}
			}
			if(g.getPlayer().getControlledCities().size() == g.getAvailableCities().size()) {
				EndGame end = null;
				try {
					end = new EndGame("GameVictory");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				x.getView().getContentPane().removeAll();
				x.getView().add(end);
				x.getView().revalidate();
				x.getView().repaint();
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
