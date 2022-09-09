package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import engine.Game;
import exceptions.MaxCapacityException;
import units.Army;
import units.Unit;

public class RelocateArmies extends JPanelWithBackground implements MouseListener{
	
	private JLabel choose;
	private ArrayList<Army> Armies;
	private ArrayList<JLabel> ArmyLabel;
	private Game game;
	private JFrame jf;
	private String type;
	private Unit u;
	
	public RelocateArmies(Game game,JFrame jf,String type, Unit u) throws IOException{
		super("UnitsBackGround.jpg");
		this.game = game;
		this.jf = jf;
		this.type = type;
		this.u = u;
		Armies = new ArrayList<Army>();
		ArmyLabel = new ArrayList<JLabel>();
		choose = new JLabel();
		choose.setOpaque(false);
        if(type.equals("start"))
    		choose.setText("Choose An Army to Relocate unit from: ");
        else if(type.equals("end"))
    		choose.setText("Choose An Army to Relocate unit to: ");
		choose.setForeground(Color.black);
		choose.setFont(new Font("Serif Bold",Font.PLAIN,20));
		this.add(choose);
		int k=1;
		for(int i=0;i<game.getPlayer().getControlledArmies().size();i++) {
			JLabel x = new JLabel();
			x.addMouseListener(this);
			ImageIcon img = new ImageIcon("tesss.png");
			x.setIcon(img);
			x.setText("Army"+k);
			x.setVerticalTextPosition(JLabel.NORTH);
			x.setHorizontalTextPosition(JLabel.CENTER);
			x.setFont(new Font("Serif Bold",Font.PLAIN,17));
			x.setIconTextGap(15);
			ArmyLabel.add(x);
			Armies.add(game.getPlayer().getControlledArmies().get(i));
			this.add(x);
			this.revalidate();
			this.repaint();
			k++;
		}
		for(int i=0;i<game.getPlayer().getControlledCities().size();i++) {
			JLabel x = new JLabel();
			x.addMouseListener(this);
			ImageIcon img = new ImageIcon("tesss.png");
			x.setIcon(img);
			x.setText(game.getPlayer().getControlledCities().get(i).getName()+" Army");
			x.setVerticalTextPosition(JLabel.NORTH);
			x.setHorizontalTextPosition(JLabel.CENTER);
			x.setFont(new Font("Serif Bold",Font.PLAIN,17));
			x.setIconTextGap(15);
			ArmyLabel.add(x);
			Armies.add(game.getPlayer().getControlledCities().get(i).getDefendingArmy());
			this.add(x);
			this.revalidate();
			this.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<ArmyLabel.size();i++) {
			if(e.getSource() == ArmyLabel.get(i) && type.equals("start")) {
				BattleUnits u = null;
				try {
					u = new BattleUnits(Armies.get(i),jf,"relocate","",game);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jf.getContentPane().removeAll();
				jf.add(u);
			}
			else if(e.getSource() == ArmyLabel.get(i) && type.equals("end")) {
				JOptionPane.showMessageDialog(this, "You Have Relocated The Unit!",
			               "Relocate Success", JOptionPane.INFORMATION_MESSAGE);
				jf.dispose();
				try {
					Armies.get(i).relocateUnit(u);
				} catch (MaxCapacityException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "You Don't Have Enough Capacity!",
				               "Relocate Failure", JOptionPane.INFORMATION_MESSAGE);
				}
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
