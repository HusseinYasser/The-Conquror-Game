package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import engine.Game;
import units.Archer;
import units.Army;
import units.Infantry;
import units.Status;

public class Info extends JFrame implements ActionListener {
	
	private SetTargetListener listener;
	JButton set;
	public void setListener(SetTargetListener listener) {
		this.listener = listener;
	}

	JComboBox<String> cities;
	Army z;
	public Info(Game g,Army x) {
		z=x;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		//this.setSize(400,300);
		this.setTitle("Army Information");
		this.setIconImage(new ImageIcon("game11.png").getImage());
		String status;
		String currLoc = x.getCurrentLocation();
		String target;
		String distance;
		String cityUnderSiege="";
		String turnsUnderSiege="";
		if(x.getCurrentStatus() == Status.IDLE)
			status = "Idle";
		else if(x.getCurrentStatus() == Status.MARCHING)
			status = "Marching";
		else {
			status = "Besiegeing";
			cityUnderSiege = x.getCurrentLocation();
			for(int i = 0;i<g.getAvailableCities().size();i++) {
				if(g.getAvailableCities().get(i).equals(x.getCurrentLocation())) {
					turnsUnderSiege = g.getAvailableCities().get(i).getTurnsUnderSiege()+"";
					break;
				}
			}
		}
		if(x.getTarget() == "") {
			target = "No Target";
			distance = "No Target";
		}
		else {
			target = x.getTarget();
			distance = x.getDistancetoTarget()+"";
		}
		String Text;
		if(status!="Besiegeing") {
			 Text = "<html>Currennt Status: "+status+"<BR>Current Location: "+currLoc+"<BR>Target: "+target+"<BR>"
					+"Distance To Target: "+distance+"<BR>";
		}
		else
		     Text = "<html>Currennt Status: "+status+"<BR>Current Location: "+currLoc+"<BR>Target: "+target+"<BR>"
					+"Distance To Target: "+distance+"<BR>City Under Siege: "+cityUnderSiege+"<BR>Turns Under Siege: "+turnsUnderSiege+"<BR>";
		for(int k = 0;k<x.getUnits().size();k++) {
			String type;
			String level = x.getUnits().get(k).getLevel()+"";
			String maxCount = x.getUnits().get(k).getMaxSoldierCount()+"";
			String currentCount = x.getUnits().get(k).getCurrentSoldierCount()+"";
			if(x.getUnits().get(k) instanceof Archer)
				type = "Archer";
			else if(x.getUnits().get(k) instanceof Infantry)
				type = "Infantry";
			else
				type = "Cavalry";
			String UnitInfo = "Unit Type: "+type+"  Level: "+level+"  CurrentSoliderCount: "+currentCount+"  MaxSoliderCount: "+maxCount+"<BR>";
			Text+=UnitInfo;
	   }
	   Text+="</html>";
	   JLabel label = new JLabel();
	   label.setText(Text);
	   String[]arr = new String[g.getAvailableCities().size()];
	   arr[0] = "Select Target City";
	   int k=1;
	   label.setFont(new Font("MV Boli",Font.PLAIN,22));
	   for(int i=0;i<g.getAvailableCities().size();i++) {
		   if(!g.getAvailableCities().get(i).getName().equals(x.getCurrentLocation()) && !x.getCurrentLocation().equals("onRoad") && x.getTarget()=="") {
			   arr[k] = g.getAvailableCities().get(i).getName();
			   k++;
		   }
	   }
	   cities = new JComboBox<String>(arr);
	   set = new JButton();
	   set.setText("Set Target City");
	   set.addActionListener(this);
	   this.setLayout(new FlowLayout(FlowLayout.LEADING));
	   this.add(label);
	   this.add(cities);
	   this.add(set);
	   this.pack();
	   this.revalidate();
	   this.repaint();
  }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == set) {
			if(cities.getSelectedItem() == "Select Target City") {
			}
			else {
				listener.onSetTarget(z,(String)cities.getSelectedItem());
			}
		}
	}
}
