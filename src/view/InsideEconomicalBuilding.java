package view;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;

import buildings.EconomicBuilding;

public class InsideEconomicalBuilding extends JPanelWithBackground{
	
	public InsideEconomicalBuilding(EconomicBuilding x, String name) throws IOException {
		super(name+"Building.jpg");
		JLabel header = new JLabel();
		header.setText("<html>Building Type: "+name+"<BR>Level: "+x.getLevel()+"<BR>Upgrade Cost: "+
		x.getUpgradeCost()+"<BR>Cooling Down: "+x.isCoolDown()+"</html>");
		header.setFont(new Font("Serif Bold",Font.BOLD,25));
		header.setForeground(Color.white);
		this.add(header);
		this.revalidate();
		this.repaint();
	}

}
