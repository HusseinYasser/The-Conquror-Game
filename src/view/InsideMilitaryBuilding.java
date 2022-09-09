package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.MilitaryBuilding;

public class InsideMilitaryBuilding extends JPanelWithBackground{
	
	public InsideMilitaryBuilding(MilitaryBuilding x,String name) throws IOException {
		super(name+"Building.jpg");
		JLabel header = new JLabel();
		header.setText("<html>Building Type: "+name+"<BR>Level: "+x.getLevel()+"<BR>Upgrade Cost: "+x.getUpgradeCost()
		+"<BR>Recruitment Cost: "+x.getRecruitmentCost()+"<BR>Current Recruit: "+x.getCurrentRecruit()+"<BR>Max Recruit: "+x.getMaxRecruit()+
		"<BR>Cooling Down: "+x.isCoolDown()+"</html>");
		header.setFont(new Font("Serif Bold",Font.BOLD,25));
		header.setForeground(Color.white);
		this.add(header);
		this.revalidate();
		this.repaint();
	}
	
}
