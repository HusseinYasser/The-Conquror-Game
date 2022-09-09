package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.Utilities;

import engine.City;
import units.Archer;
import units.Infantry;

public class CityDefendingArmy extends JPanel {
	
	public CityDefendingArmy(City c)  {
		JLabel units = new JLabel();
		String res ="<html>";
		String type;
	    for(int i =0;i<c.getDefendingArmy().getUnits().size();i++) {
			if(c.getDefendingArmy().getUnits().get(i) instanceof Archer)
				type = "Archer";
			else if(c.getDefendingArmy().getUnits().get(i) instanceof Infantry)
				type = "Infantry";
			else
				type = "Cavalry";
			res+="Unit"+(i+1)+" Type: "+type+"<BR>Level: "+c.getDefendingArmy().getUnits().get(i).getLevel()
					+"  CurrentSoliderCount: "+c.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
					+"  MaxSoliderCount: "+c.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()+"<BR>IdleUpKeep: "+c.getDefendingArmy().getUnits().get(i).getIdleUpkeep()
					+"  MarchingUpKeep: "+c.getDefendingArmy().getUnits().get(i).getMarchingUpkeep()+"  BesiegingUpKeep: "+c.getDefendingArmy().getUnits().get(i).getSiegeUpkeep()+"<BR>";
		}
		res+="</html>";
		units.setText(res);
		units.setFont(new Font("Serif Bold",Font.BOLD,15));
		units.setIcon(new ImageIcon(c.getName()+"Army.jpg"));
		units.setHorizontalTextPosition(JLabel.LEFT);
		units.setVerticalTextPosition(JLabel.CENTER);
		if(units.getText().equals("<html></html>")) {
			units.setText("No Units in Defending Army");
		}
		JScrollPane sc = new JScrollPane(units);
		sc.setPreferredSize(new Dimension(910,523));
		
		this.add(sc);
		this.revalidate();
		this.repaint();	
		
	}
	
	public static void main(String[]args) {
		JFrame jf = new JFrame();
	    jf.setSize(910,523);
	    jf.setVisible(true);
	    CityDefendingArmy c = new CityDefendingArmy(new City("Sparta"));
		jf.add(c);
		jf.revalidate();
		jf.repaint();
		
	}

}
