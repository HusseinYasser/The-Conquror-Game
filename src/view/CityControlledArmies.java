package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import engine.City;
import engine.Game;
import units.Archer;
import units.Army;
import units.Infantry;

public class CityControlledArmies extends JPanelWithBackground implements MouseListener {
	
	ArrayList<JLabel> ArmiesLabel;
	ArrayList<Army> Armies;
	
	public CityControlledArmies(Game game,City city) throws IOException {
		super("UnitsBackGround.jpg");
		ArmiesLabel = new ArrayList<JLabel>();
		Armies = new ArrayList<Army>();
		this.setLayout(new GridLayout(0,3));
		int k=1;
		for(int i=0;i<game.getPlayer().getControlledArmies().size();i++) {
			if(game.getPlayer().getControlledArmies().get(i).getCurrentLocation().equals(city.getName())) {
				JLabel a = new JLabel();
				a.setIcon(new ImageIcon("tesss.png"));
				a.setText("Army"+k);
				a.setHorizontalTextPosition(JLabel.CENTER);
				a.setVerticalTextPosition(JLabel.NORTH);
				a.setForeground(Color.white);
				a.setFont(new Font("Serif Bold",Font.BOLD,15));
				a.addMouseListener(this);
				ArmiesLabel.add(a);
				Armies.add(game.getPlayer().getControlledArmies().get(i));
				this.add(a);
				k++;
				this.revalidate();
				this.repaint();
			}
		}
		if(ArmiesLabel.size() == 0) {
			JLabel noCurrent = new JLabel();
			noCurrent.setText("None of Your Controlled Armies are in "+city.getName());
			noCurrent.setFont(new Font("Serif Bold",Font.BOLD,15));
			noCurrent.setForeground(Color.white);
			noCurrent.setFont(new Font("Serif Bold",Font.BOLD,25));
			this.add(noCurrent,BorderLayout.WEST);
			this.revalidate();
			this.repaint();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<ArmiesLabel.size();i++) {
			if(e.getSource() == ArmiesLabel.get(i)) {
				JFrame x = new JFrame();
				x.setVisible(true);
				x.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				x.setSize(new Dimension(910,523));
				x.setResizable(false);
				JLabel info = new JLabel();
				String res="<html>";
				String type;
				for(int j = 0;j<Armies.get(i).getUnits().size();j++) {
					if(Armies.get(i).getUnits().get(j) instanceof Archer)
						type = "Archer";
					else if(Armies.get(i).getUnits().get(j) instanceof Infantry)
						type = "Infantry";
					else
						type = "Cavalry";
					res+="Unit"+(j+1)+" Type: "+type+"<BR>Level: "+Armies.get(i).getUnits().get(j).getLevel()
							+"  CurrentSoliderCount: "+Armies.get(i).getUnits().get(j).getCurrentSoldierCount()
							+"  MaxSoliderCount: "+Armies.get(i).getUnits().get(j).getMaxSoldierCount()+"<BR>IdleUpKeep: "+Armies.get(i).getUnits().get(j).getIdleUpkeep()
							+"  MarchingUpKeep: "+Armies.get(i).getUnits().get(j).getMarchingUpkeep()+"  BesiegingUpKeep: "+Armies.get(i).getUnits().get(j).getSiegeUpkeep()+"<BR>";
				}
				res+="</html>";
				info.setText(res);
				info.setFont(new Font("Serif Bold",Font.BOLD,15));
				info.setIcon(new ImageIcon(Armies.get(i).getCurrentLocation()+"Army.jpg"));
				info.setHorizontalTextPosition(JLabel.LEFT);
				info.setVerticalTextPosition(JLabel.CENTER);
				if(info.getText().equals("<html></html>")) {
					info.setText("No Units in Defending Army");
					info.setFont(new Font("Serif Bold",Font.BOLD,25));
				}
				JScrollPane sc = new JScrollPane(info);
				sc.setPreferredSize(new Dimension(910,523));
				x.add(sc);
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
