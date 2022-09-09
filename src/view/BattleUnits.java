package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import engine.City;
import engine.Game;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class BattleUnits extends JPanelWithBackground implements MouseListener {
	
	private ArrayList<JLabel> unitsLabel;
	private ArrayList<Unit> unitss;
	private JFrame jf;
	private onChooseUnitListener listener;
	private String type;
	private String War;
	private Game g;
	private Army attacker;
	
	public BattleUnits(Army attacker,JFrame jf,String type,String War,Game g) throws IOException {
		super("UnitsBackGround.jpg");
		this.type = type;
		this.attacker = attacker;
		this.g = g;
		this.War = War;
		this.setLayout(new GridLayout(0,10));
		unitsLabel = new ArrayList<JLabel>();
		unitss = new ArrayList<Unit>();
		this.jf=jf;
		for(int i=0;i<attacker.getUnits().size();i++) {
			String type1;
			if(attacker.getUnits().get(i) instanceof Archer)
				type1 = "Archery";
			else if(attacker.getUnits().get(i) instanceof Infantry)
				type1 = "Infantry";
			else
				type1 = "Cavalary";
			JLabel unit = new JLabel();
			unit.setSize(new Dimension(100,150));
			unit.setIcon(new ImageIcon(type1+"Unit2.png"));
			unit.setText("<html>Level "+attacker.getUnits().get(i).getLevel() +"<BR>CurrSolider "
					+attacker.getUnits().get(i).getCurrentSoldierCount()+"</html>");
			unit.setHorizontalTextPosition(JLabel.CENTER);
			unit.setVerticalTextPosition(JLabel.BOTTOM);
			unit.setForeground(Color.black);
			if(War == "Manual" || type=="Initiate" || type.equals("relocate"))
				unit.addMouseListener(this);
			this.add(unit);
			unitsLabel.add(unit);
			unitss.add(attacker.getUnits().get(i));
			this.revalidate();
			this.repaint();
		}
		
		
	}
	
	public void setOnChooseUnitListener(onChooseUnitListener l) {
		listener = l;
	}
	
   public static void main(String[]args) {
    	Archer u1 = new Archer(1,500,123,4,5);
    	Infantry u2 = new Infantry(2,312,515,15,6);
    	Cavalry u3 = new Cavalry(1,436,234,523,6);
    	Archer u4 = new Archer(1,500,123,4,5);
   
    	Army x = new Army("Cairo");
    	x.getUnits().add(u1);
    	x.getUnits().add(u2);
    	x.getUnits().add(u3);
    	x.getUnits().add(u4);
    	JFrame jf = new JFrame();
    	jf.setVisible(true);
    	jf.setSize(910, 523);
    	BattleUnits y = null;
    	try {
			y = new BattleUnits(x,jf,"attack","Maula",null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	jf.add(y);
    	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	jf.revalidate();
    	jf.repaint();
    } 

	//@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0;i<unitsLabel.size();i++) {
			if(e.getSource() == unitsLabel.get(i) && !type.equals("defend") && !type.equals("Initiate") && !type.equals("relocate")) {
				listener.onChooseUnit(unitss.get(i),jf);
			}
			else if(e.getSource() == unitsLabel.get(i) && type.equals("Initiate")) {
				jf.dispose();
				City x = null;
				for(int j=0;j<g.getPlayer().getControlledCities().size();j++) {
					if(g.getPlayer().getControlledCities().get(j).getDefendingArmy().equals(attacker)) {
						x = g.getPlayer().getControlledCities().get(j);
						break;
					}
				}
				g.getPlayer().initiateArmy(x, unitss.get(i));
				JOptionPane.showMessageDialog(this, "You Have initiated an army and added to your Controlled Armies",
				         "Initiate Success", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(e.getSource() == unitsLabel.get(i) && type.equals("relocate")) {
				RelocateArmies x = null;
				try {
					x = new RelocateArmies(g,jf,"end",unitss.get(i));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jf.getContentPane().removeAll();
				jf.add(x);
				jf.revalidate();
				jf.repaint();
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
