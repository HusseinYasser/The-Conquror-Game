package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Stable;
import buildings.Market;
import buildings.Farm;

public class CityView extends JPanelWithBackground implements ActionListener,MouseListener{
	
	private JButton MilitaryBuildings;
	private JLabel ArcheryRange;
	private JLabel Infantry;
	private JLabel Cavalary;
	private JLabel UpgradeArcher;
	private JLabel UpgradeInfantry;
	private JLabel UpgradeCavalary;
	private JLabel RecruitArcher;
	private JLabel RecruitInfantry;
	private JLabel RecruitCavalary;
	private JLabel Build;
	private JComboBox <String> chooseBuild;
	private JButton EconomicalBuildings;
	private JLabel Farm;
	private JLabel Market;
	private JLabel UpgradeFarm;
	private JLabel UpgradeMarket;
	private JLabel food;
	private JLabel gold;
	private Game game;
	private City city;
	private JLabel name;
	private JButton Armies;
	private JLabel ArmiesName;
	private JLabel DefendingArmy;
	private JLabel ControlledArmies;
	private JLabel WorldMap;
	private JButton InitiateArmy;
	private JLabel CurrentCount;
	private JButton relocateUnit;
	
	private ToWorldMapListener World;
	
	public void setToWorldMapListener(ToWorldMapListener l) {
		World = l;
	}
	
	public CityView(Game g,City c) throws IOException {
		super(c.getName()+"View.jpg");
		// TODO Auto-generated constructor stub
		game=g;
		city = c;
		this.setLayout(null);
		MilitaryBuildings = new JButton();
		MilitaryBuildings.setText("Military Buildings");
		MilitaryBuildings.setBounds(10,10,150,25);
		MilitaryBuildings.setVisible(true);
		this.add(MilitaryBuildings);
		MilitaryBuildings.addActionListener(this);
		ArcheryRange = new JLabel ();
		ArcheryRange.setIcon(new ImageIcon("Archer.png"));
		ArcheryRange.setText("Archery");
		ArcheryRange.setVerticalTextPosition(JLabel.BOTTOM);
		ArcheryRange.setHorizontalTextPosition(JLabel.CENTER);
		ArcheryRange.setForeground(Color.white);
		ArcheryRange.setBounds(15,45,70,70);
		ArcheryRange.setVisible(false);
		ArcheryRange.addMouseListener(this);
		this.add(ArcheryRange);
		Infantry = new JLabel();
		Infantry.setIcon(new ImageIcon("Infantry.png"));
		Infantry.setText("Infantry");
		Infantry.setBounds(15,130,70,70);
		Infantry.setForeground(Color.white);
		Infantry.setVerticalTextPosition(JLabel.BOTTOM);
		Infantry.setHorizontalTextPosition(JLabel.CENTER);
		Infantry.setVisible(false);
		Infantry.addMouseListener(this);
		this.add(Infantry);
		Cavalary = new JLabel();
		Cavalary.setIcon(new ImageIcon("Cavalary.png"));
		Cavalary.setForeground(Color.white);
	    Cavalary.setText("Stable");
		Cavalary.setBounds(15,210,70,70);
		Cavalary.setVerticalTextPosition(JLabel.BOTTOM);
		Cavalary.setHorizontalTextPosition(JLabel.CENTER);
		Cavalary.setVisible(false);
		Cavalary.addMouseListener(this);
		this.add(Cavalary);
		UpgradeArcher = new JLabel();
		UpgradeArcher.setIcon(new ImageIcon("Upgrade.png"));
		UpgradeArcher.setText("Upgrade");
		UpgradeArcher.setBounds(95,45,70,70);
		UpgradeArcher.setHorizontalTextPosition(JLabel.CENTER);
		UpgradeArcher.setVerticalTextPosition(JLabel.BOTTOM);
		UpgradeArcher.setForeground(Color.white);
		UpgradeArcher.setVisible(false);
		UpgradeArcher.addMouseListener(this);
		this.add(UpgradeArcher);
		RecruitArcher = new JLabel();
		RecruitArcher.setIcon(new ImageIcon("Recruit.png"));
		RecruitArcher.setText("Recruit");
		RecruitArcher.setForeground(Color.white);
		RecruitArcher.setBounds(160,45,70,70);
		RecruitArcher.setHorizontalTextPosition(JLabel.CENTER);
		RecruitArcher.setVerticalTextPosition(JLabel.BOTTOM);
		RecruitArcher.setVisible(false);
		RecruitArcher.addMouseListener(this);
		this.add(RecruitArcher);
		UpgradeInfantry = new JLabel();
		UpgradeInfantry.setIcon(new ImageIcon("Upgrade.png"));
		UpgradeInfantry.setText("Upgrade");
		UpgradeInfantry.setBounds(95,130,70,70);
		UpgradeInfantry.setHorizontalTextPosition(JLabel.CENTER);
		UpgradeInfantry.setVerticalTextPosition(JLabel.BOTTOM);
		UpgradeInfantry.setForeground(Color.white);
		UpgradeInfantry.setVisible(false);
		UpgradeInfantry.addMouseListener(this);
		this.add(UpgradeInfantry);
		RecruitInfantry = new JLabel();
		RecruitInfantry.setIcon(new ImageIcon("Recruit.png"));
		RecruitInfantry.setText("Recruit");
		RecruitInfantry.setForeground(Color.white);
		RecruitInfantry.setBounds(160,130,70,70);
		RecruitInfantry.setHorizontalTextPosition(JLabel.CENTER);
		RecruitInfantry.setVerticalTextPosition(JLabel.BOTTOM);
		RecruitInfantry.setVisible(false);
		RecruitInfantry.addMouseListener(this);
		this.add(RecruitInfantry);
		UpgradeCavalary = new JLabel();
		UpgradeCavalary.setIcon(new ImageIcon("Upgrade.png"));
		UpgradeCavalary.setText("Upgrade");
		UpgradeCavalary.setBounds(95,210,70,70);
		UpgradeCavalary.setHorizontalTextPosition(JLabel.CENTER);
		UpgradeCavalary.setVerticalTextPosition(JLabel.BOTTOM);
		UpgradeCavalary.setForeground(Color.white);
		UpgradeCavalary.setVisible(false);
		UpgradeCavalary.addMouseListener(this);
		this.add(UpgradeCavalary);
		RecruitCavalary = new JLabel();
		RecruitCavalary.setIcon(new ImageIcon("Recruit.png"));
		RecruitCavalary.setText("Recruit");
		RecruitCavalary.setForeground(Color.white);
		RecruitCavalary.setBounds(160,210,70,70);
		RecruitCavalary.setHorizontalTextPosition(JLabel.CENTER);
		RecruitCavalary.setVerticalTextPosition(JLabel.BOTTOM);
		RecruitCavalary.setVisible(false);
		RecruitCavalary.addMouseListener(this);
		this.add(RecruitCavalary);
		Build = new JLabel();
		Build.setBounds(15,350,70,70);
		Build.setForeground(Color.white);
		Build.addMouseListener(this);
		Build.setText("Build");
		Build.setIcon(new ImageIcon("Build.png"));
		Build.setHorizontalTextPosition(JLabel.CENTER);
		Build.setVerticalTextPosition(JLabel.BOTTOM);
		Build.setVisible(true);
		this.add(Build);
		String[]names = {"Select a Building","ArcheryRange","Barracks","Stable","Farm","Market"};
		chooseBuild = new JComboBox<String>(names);
		chooseBuild.setBounds(105, 370, 150, 25);
		chooseBuild.addMouseListener(this);
		this.add(chooseBuild);
		EconomicalBuildings = new JButton();
		EconomicalBuildings.setText("Economical Buildings");
		EconomicalBuildings.setBounds(350,10,200,25);
		EconomicalBuildings.addActionListener(this);
		this.add(EconomicalBuildings);
		Farm = new JLabel();
		Farm.setIcon(new ImageIcon("Farm.png"));
		Farm.setText("Farm");
		Farm.setVerticalTextPosition(JLabel.BOTTOM);
		Farm.setHorizontalTextPosition(JLabel.CENTER);
		Farm.setForeground(Color.white);
		Farm.setBounds(400,45,70,70);
		Farm.setVisible(false);
		Farm.addMouseListener(this);
		this.add(Farm);
		UpgradeFarm = new JLabel();
		UpgradeFarm.setIcon(new ImageIcon("Upgrade.png"));
		UpgradeFarm.setText("Upgrade");
		UpgradeFarm.setHorizontalTextPosition(JLabel.CENTER);
		UpgradeFarm.setVerticalTextPosition(JLabel.BOTTOM);
		UpgradeFarm.setBounds(490,45,70,70);
		UpgradeFarm.setForeground(Color.white);
		UpgradeFarm.setVisible(false);
		UpgradeFarm.addMouseListener(this);
		this.add(UpgradeFarm);
		Market = new JLabel();
		Market.setIcon(new ImageIcon("Market.png"));
		Market.setText("Market");
		Market.setVerticalTextPosition(JLabel.BOTTOM);
		Market.setHorizontalTextPosition(JLabel.CENTER);
		Market.setForeground(Color.white);
		Market.setBounds(400,130,70,70);
		Market.setVisible(false);
		Market.addMouseListener(this);
		this.add(Market);
		UpgradeMarket = new JLabel();
		UpgradeMarket.setIcon(new ImageIcon("Upgrade.png"));
		UpgradeMarket.setText("Upgrade");
		UpgradeMarket.setHorizontalTextPosition(JLabel.CENTER);
		UpgradeMarket.setVerticalTextPosition(JLabel.BOTTOM);
		UpgradeMarket.setBounds(490,130,70,70);
		UpgradeMarket.setForeground(Color.white);
		UpgradeMarket.setVisible(false);
		UpgradeMarket.addMouseListener(this);
		ImageIcon GoldImg = new ImageIcon("GOOOLD.png");
	    ImageIcon FoodImg = new ImageIcon("FOOOD.png");
	    food = new JLabel();
	    food.setIcon(FoodImg);
	    food.setBounds(850,120,70,70);
	    gold = new JLabel();
	    gold.setIcon(GoldImg);
	    gold.setBounds(850,40,70,70);
	    food.setText(game.getPlayer().getFood()+"");
	    food.setForeground(Color.red);
	    food.setHorizontalTextPosition(JLabel.CENTER);
	    food.setVerticalTextPosition(JLabel.NORTH);
	    gold.setText(game.getPlayer().getTreasury()+"");
	    gold.setForeground(Color.yellow);
	    gold.setVerticalTextPosition(JLabel.NORTH);
	    gold.setHorizontalTextPosition(JLabel.CENTER);
	    name = new JLabel();
	    name.setBounds(725,10,300,15);
	    name.setForeground(Color.white);
	    name.setText("Player Name: "+game.getPlayer().getName());
	    name.setFont(new Font("Serif Bold",Font.BOLD,15));
	    this.add(name);
	    this.add(food);
	    this.add(gold);
		this.add(UpgradeMarket);
		Armies = new JButton();
		Armies.addActionListener(this);
		Armies.setIcon(new ImageIcon("CityArmies.jpg"));
		Armies.setBounds(600, 10, 75, 75);
		this.add(Armies);
		ArmiesName = new JLabel();
		ArmiesName.setText("Armies");
		ArmiesName.setBounds(610,90,80,20);
		ArmiesName.setFont(new Font("Serif Bold",Font.BOLD,15));
		ArmiesName.setForeground(Color.white);
		this.add(ArmiesName);
		DefendingArmy = new JLabel();
		DefendingArmy.setVisible(false);
		DefendingArmy.setIcon(new ImageIcon("DefendingArmy.png"));
		DefendingArmy.setText("Defending");
		DefendingArmy.setFont(new Font("Serif Bold",Font.BOLD,12));
		DefendingArmy.setForeground(Color.white);
		DefendingArmy.setHorizontalTextPosition(JLabel.CENTER);
		DefendingArmy.setVerticalTextPosition(JLabel.BOTTOM);;
		DefendingArmy.setBounds(560,120,60,70);
		DefendingArmy.addMouseListener(this);
		this.add(DefendingArmy);
		ControlledArmies = new JLabel();
		ControlledArmies.setVisible(false);
		ControlledArmies.setIcon(new ImageIcon("CityControlledArmies.png"));
		ControlledArmies.setText("Controlled");
		ControlledArmies.setForeground(Color.white);
		ControlledArmies.setHorizontalTextPosition(JLabel.CENTER);
		ControlledArmies.setVerticalTextPosition(JLabel.BOTTOM);
		ControlledArmies.setFont(new Font("Serif Bold",Font.BOLD,12));
		ControlledArmies.setBounds(660,120,60,70);
		ControlledArmies.addMouseListener(this);
		this.add(ControlledArmies);
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
		this.add(WorldMap);
		InitiateArmy = new JButton();
		InitiateArmy.setBounds(750,300,200,25);
		InitiateArmy.setText("Initiate Army");
		InitiateArmy.addActionListener(this);
		this.add(InitiateArmy);
		CurrentCount = new JLabel();
	    CurrentCount.setText("Current Turn: "+g.getCurrentTurnCount());
	    CurrentCount.setFont(new Font("Serif Bold",Font.BOLD,17));
	    CurrentCount.setForeground(Color.white);
	    CurrentCount.setOpaque(false);
	    CurrentCount.setBounds(770,180,200,30);
	    this.add(CurrentCount);
	    relocateUnit = new JButton();
	    relocateUnit.setText("Relocate Unit");
	    relocateUnit.setBounds(600,300,200,25);
	    relocateUnit.setForeground(Color.BLACK);
	    relocateUnit.addActionListener(this);
	    this.add(relocateUnit);
		this.revalidate();
		this.repaint();
	}
	
	public static void main(String[]args) {
		JFrame x = new JFrame();
		x.setVisible(true);
		x.setSize(910,523);
		CityView y = null;
		try {
			 y = new CityView(new Game("Hussein","Cairo"),new City("Cairo"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x.add(y);
		x.revalidate();
		x.repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean appearMilitary;
		if(ArcheryRange.isVisible() || Infantry.isVisible() || Cavalary.isVisible()) {
			appearMilitary = true;
		}
		else
			appearMilitary = false;
		boolean appearEco ;
		if(Farm.isVisible() || Market.isVisible())
			appearEco = true;
		else
			appearEco = false;
		
		if(e.getSource().equals(MilitaryBuildings) && !appearMilitary) {
			boolean anything = false;
			for(int i = 0;i<city.getMilitaryBuildings().size();i++) {
				if(city.getMilitaryBuildings().get(i) instanceof ArcheryRange) {
					ArcheryRange.setVisible(true);
					UpgradeArcher.setVisible(true);
					RecruitArcher.setVisible(true);
					anything = true;
				}
				else if(city.getMilitaryBuildings().get(i) instanceof Barracks) {
				    Infantry.setVisible(true);
					UpgradeInfantry.setVisible(true);
					RecruitInfantry.setVisible(true);
					anything = true;
				}
				else if(city.getMilitaryBuildings().get(i) instanceof Stable) {
					Cavalary.setVisible(true);
					UpgradeCavalary.setVisible(true);
					RecruitCavalary.setVisible(true);
					anything = true;
				}
			}
			if(!anything)
				JOptionPane.showMessageDialog(this, "You Dont Have Military Buildings Yet!",
			               "Military Alert", JOptionPane.ERROR_MESSAGE);
		}
		else if(e.getSource() == MilitaryBuildings && appearMilitary) {
			if(ArcheryRange.isVisible()) {
				ArcheryRange.setVisible(false);
				UpgradeArcher.setVisible(false);
				RecruitArcher.setVisible(false);
			}
			if(Infantry.isVisible()) {
				Infantry.setVisible(false);
				UpgradeInfantry.setVisible(false);
				RecruitInfantry.setVisible(false);
			}
			if(Cavalary.isVisible()) {
				Cavalary.setVisible(false);
				UpgradeCavalary.setVisible(false);
				RecruitCavalary.setVisible(false);
			}
		}
		else if(e.getSource().equals(EconomicalBuildings) && !appearEco) {
			boolean anything = false;
			for(int i=0;i<city.getEconomicalBuildings().size();i++) {
				if(city.getEconomicalBuildings().get(i) instanceof Market) {
					Market.setVisible(true);
					UpgradeMarket.setVisible(true);
					anything = true;
				}
				else if(city.getEconomicalBuildings().get(i) instanceof Farm) {
					Farm.setVisible(true);
					UpgradeFarm.setVisible(true);
					anything = true;
				}
			}
			if(!anything) {
				JOptionPane.showMessageDialog(this, "You Dont Have Economical Buildings Yet!",
			               "Economical Alert", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == EconomicalBuildings && appearEco) {
			if(Farm.isVisible()) {
				Farm.setVisible(false);
				UpgradeFarm.setVisible(false);
			}
			if(Market.isVisible()) {
				Market.setVisible(false);
				UpgradeMarket.setVisible(false);
			}
		}
		if(e.getSource() == Armies && DefendingArmy.isVisible() && ControlledArmies.isVisible()) {
			DefendingArmy.setVisible(false);
			ControlledArmies.setVisible(false);
		}
		else if(e.getSource() == Armies && !DefendingArmy.isVisible() && !ControlledArmies.isVisible()) {
			DefendingArmy.setVisible(true);
			ControlledArmies.setVisible(true);
		}
	
		if(e.getSource() == InitiateArmy) {
			JFrame jf = new JFrame();
			jf.setVisible(true);
			jf.setSize(910, 523);
			jf.setTitle("Initiate Army");
			jf.setResizable(false);
			BattleUnits x = null;
			try {
				x = new BattleUnits(city.getDefendingArmy(),jf,"Initiate","",game);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jf.add(x);
		}
		
		if(e.getSource() == relocateUnit) {
			JFrame n = new JFrame();
			n.setSize(910, 523);
			n.setResizable(false);
			n.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			n.setVisible(true);
			n.setTitle("Relocate a Unit");
			RelocateArmies x = null;
			try {
				x = new RelocateArmies(game,n,"start",null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			n.add(x);
			n.revalidate();
			n.repaint();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == Build && chooseBuild.getSelectedItem()!="Select a Building") {
			try {
				boolean found = false;
				String curr = (String) chooseBuild.getSelectedItem();
				for(int i = 0;i<city.getMilitaryBuildings().size();i++) {
					if(city.getMilitaryBuildings().get(i) instanceof ArcheryRange && curr.equals("ArcheryRange")) {
						found=true;
						break;
					}
					else if(city.getMilitaryBuildings().get(i) instanceof Barracks && curr.equals("Barracks") ){
						found = true;
						break;
					}
					else if(city.getMilitaryBuildings().get(i) instanceof Stable && curr.equals("Stable") ){
						found = true;
						break;
					}
				}
				for(int i = 0;i<city.getEconomicalBuildings().size();i++) {
					if(city.getEconomicalBuildings().get(i) instanceof Farm && curr.equals("Farm")) {
						found=true;
						break;
					}
					else if(city.getEconomicalBuildings().get(i) instanceof Market && curr.equals("Maret") ){
						found = true;
						break;
					}
				}
				if(!found) {
					game.getPlayer().build((String)chooseBuild.getSelectedItem(), city.getName());
					if((String)chooseBuild.getSelectedItem() == "ArcheryRange") {
						ArcheryRange.setVisible(true);
						UpgradeArcher.setVisible(true);
						RecruitArcher.setVisible(true);
					}
					else if((String)chooseBuild.getSelectedItem() == "Barracks") {
					Infantry.setVisible(true);
					UpgradeInfantry.setVisible(true);
					RecruitInfantry.setVisible(true);
				    }
					else if((String)chooseBuild.getSelectedItem() == "Stable") {
						Cavalary.setVisible(true);
						UpgradeCavalary.setVisible(true);
						RecruitCavalary.setVisible(true);
					}
					else if((String)chooseBuild.getSelectedItem() == "Stable") {
						Cavalary.setVisible(true);
						UpgradeCavalary.setVisible(true);
						RecruitCavalary.setVisible(true);
					}
					else if((String)chooseBuild.getSelectedItem() == "Farm") {
						Farm.setVisible(true);
						UpgradeFarm.setVisible(true);
					}
					else if((String)chooseBuild.getSelectedItem() == "Market") {
						Market.setVisible(true);
						UpgradeMarket.setVisible(true);
					}
					gold.setText(game.getPlayer().getTreasury()+"");
					this.revalidate();
					this.repaint();
				}
				else {
					JOptionPane.showMessageDialog(this, "You Can't have two Buildings of the Same type!!",
				               "Build Alert", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NotEnoughGoldException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "You Dont Have Enough Gold!",
			               "Gold Alert", JOptionPane.ERROR_MESSAGE);
			}	
		}
		if(e.getSource() == ArcheryRange && ArcheryRange.isVisible()) {
			ArcheryRange x = null;
			for(int i=0;i<city.getMilitaryBuildings().size();i++) {
				if(city.getMilitaryBuildings().get(i) instanceof ArcheryRange) {
					x = (ArcheryRange) city.getMilitaryBuildings().get(i);
				}
			}
			InsideMilitaryBuilding m = null;
			try {
				 m = new InsideMilitaryBuilding(x,"ArcheryRange");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JFrame temp = new JFrame();
			m.setBounds(0, 0, 910, 523);
			temp.setLayout(null);
			temp.setSize(910, 523);
			temp.setVisible(true);
			temp.setResizable(false);
			temp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			temp.setTitle("ArcheryRange Building");
			temp.add(m);
			temp.revalidate();
			temp.repaint();
		}
		else if(e.getSource() == Infantry && Infantry.isVisible()) {
			Barracks x = null;
			for(int i=0;i<city.getMilitaryBuildings().size();i++) {
				if(city.getMilitaryBuildings().get(i) instanceof Barracks) {
					x = (Barracks) city.getMilitaryBuildings().get(i);
				}
			}
			InsideMilitaryBuilding m = null;
			try {
				 m = new InsideMilitaryBuilding(x,"Infantry");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JFrame temp = new JFrame();
			m.setBounds(0, 0, 910, 523);
			temp.setLayout(null);
			temp.setSize(910, 523);
			temp.setVisible(true);
			temp.setResizable(false);
			temp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			temp.setTitle("Barracks Building");
			temp.add(m);
			temp.revalidate();
			temp.repaint();
		}
		else if(e.getSource() == Cavalary && Cavalary.isVisible()) {
			Stable x = null;
			for(int i=0;i<city.getMilitaryBuildings().size();i++) {
				if(city.getMilitaryBuildings().get(i) instanceof Stable) {
					x = (Stable) city.getMilitaryBuildings().get(i);
				}
			}
			InsideMilitaryBuilding m = null;
			try {
				 m = new InsideMilitaryBuilding(x,"Cavalary");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JFrame temp = new JFrame();
			m.setBounds(0, 0, 910, 523);
			temp.setLayout(null);
			temp.setSize(910, 523);
			temp.setVisible(true);
			temp.setResizable(false);
			temp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			temp.setTitle("Stable Building");
			temp.add(m);
			temp.revalidate();
			temp.repaint();
		}
		else if(e.getSource() == Market && Market.isVisible()) {
			Market x = null;
			for(int i=0;i<city.getEconomicalBuildings().size();i++) {
				if(city.getEconomicalBuildings().get(i) instanceof Market) {
					x = (Market) city.getEconomicalBuildings().get(i);
				}
			}
			InsideEconomicalBuilding m = null;
			try {
				 m = new InsideEconomicalBuilding(x,"Market");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JFrame temp = new JFrame();
			m.setBounds(0, 0, 910, 523);
			temp.setLayout(null);
			temp.setSize(910, 523);
			temp.setVisible(true);
			temp.setResizable(false);
			temp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			temp.setTitle("Market Building");
			temp.add(m);
			temp.revalidate();
			temp.repaint();
		}
		else if(e.getSource() == Farm && Farm.isVisible()) {
			Farm x = null;
			for(int i=0;i<city.getEconomicalBuildings().size();i++) {
				if(city.getEconomicalBuildings().get(i) instanceof Farm) {
					x = (Farm) city.getEconomicalBuildings().get(i);
				}
			}
			InsideEconomicalBuilding m = null;
			try {
				 m = new InsideEconomicalBuilding(x,"Farm");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JFrame temp = new JFrame();
			m.setBounds(0, 0, 910, 523);
			temp.setLayout(null);
			temp.setSize(910, 523);
			temp.setVisible(true);
			temp.setResizable(false);
			temp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			temp.setTitle("Farm Building");
			temp.add(m);
			temp.revalidate();
			temp.repaint();
		}
		if(e.getSource() == UpgradeArcher && UpgradeArcher.isVisible()) {
			for(int i = 0 ;i<city.getMilitaryBuildings().size();i++) {
				if(city.getMilitaryBuildings().get(i) instanceof ArcheryRange) {
					try {
						game.getPlayer().upgradeBuilding(city.getMilitaryBuildings().get(i));
						gold.setText(game.getPlayer().getTreasury()+"");
						JOptionPane.showMessageDialog(this, "Upgrade Done!",
					               "Upgrade Success", JOptionPane.INFORMATION_MESSAGE);
						this.revalidate();
						this.repaint();
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, "You Don't Have enough Gold!",
					               "Treasury Alert", JOptionPane.ERROR_MESSAGE);
					} catch(BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, "Building is Cooling Down!",
					               "Upgrade Alert", JOptionPane.ERROR_MESSAGE);
					} catch(MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, "Building has reached Maximum Level!",
					               "Upgrade Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		else if(e.getSource() == UpgradeInfantry && UpgradeInfantry.isVisible()) {
			for(int i = 0 ;i<city.getMilitaryBuildings().size();i++) {
				if(city.getMilitaryBuildings().get(i) instanceof Barracks) {
					try {
						game.getPlayer().upgradeBuilding(city.getMilitaryBuildings().get(i));
						JOptionPane.showMessageDialog(this, "Upgrade Done!",
					               "Upgrade Success", JOptionPane.INFORMATION_MESSAGE);
						gold.setText(game.getPlayer().getTreasury()+"");
						this.revalidate();
						this.repaint();
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, "You Don't Have enough Gold!",
					               "Treasury Alert", JOptionPane.ERROR_MESSAGE);
					} catch(BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, "Building is Cooling Down!",
					               "Upgrade Alert", JOptionPane.ERROR_MESSAGE);
					} catch(MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, "Building has reached Maximum Level!",
					               "Upgrade Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		else if(e.getSource() == UpgradeCavalary && UpgradeCavalary.isVisible()) {
			for(int i = 0 ;i<city.getMilitaryBuildings().size();i++) {
				if(city.getMilitaryBuildings().get(i) instanceof Stable) {
					try {
						game.getPlayer().upgradeBuilding(city.getMilitaryBuildings().get(i));
						JOptionPane.showMessageDialog(this, "Upgrade Done!",
					               "Upgrade Success", JOptionPane.INFORMATION_MESSAGE);
						gold.setText(game.getPlayer().getTreasury()+"");
						this.revalidate();
						this.repaint();
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, "You Don't Have enough Gold!",
					               "Treasury Alert", JOptionPane.ERROR_MESSAGE);
					} catch(BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, "Building is Cooling Down!",
					               "Upgrade Alert", JOptionPane.ERROR_MESSAGE);
					} catch(MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, "Building has reached Maximum Level!",
					               "Upgrade Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		else if(e.getSource() == UpgradeFarm && UpgradeFarm.isVisible()) {
			for(int i = 0 ;i<city.getEconomicalBuildings().size();i++) {
				if(city.getEconomicalBuildings().get(i) instanceof Farm) {
					try {
						game.getPlayer().upgradeBuilding(city.getEconomicalBuildings().get(i));
						JOptionPane.showMessageDialog(this, "Upgrade Done!",
					               "Upgrade Success", JOptionPane.INFORMATION_MESSAGE);
						gold.setText(game.getPlayer().getTreasury()+"");
						this.revalidate();
						this.repaint();
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, "You Don't Have enough Gold!",
					               "Treasury Alert", JOptionPane.ERROR_MESSAGE);
					} catch(BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, "Building is Cooling Down!",
					               "Upgrade Alert", JOptionPane.ERROR_MESSAGE);
					} catch(MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, "Building has reached Maximum Level!",
					               "Upgrade Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		else if(e.getSource() == UpgradeMarket && UpgradeMarket.isVisible()) {
			for(int i = 0 ;i<city.getEconomicalBuildings().size();i++) {
				if(city.getEconomicalBuildings().get(i) instanceof Market) {
					try {
						game.getPlayer().upgradeBuilding(city.getEconomicalBuildings().get(i));
						JOptionPane.showMessageDialog(this, "Upgrade Done!",
					               "Upgrade Success", JOptionPane.INFORMATION_MESSAGE);
						gold.setText(game.getPlayer().getTreasury()+"");
						this.revalidate();
						this.repaint();
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, "You Don't Have enough Gold!",
					               "Treasury Alert", JOptionPane.ERROR_MESSAGE);
					} catch(BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, "Building is Cooling Down!",
					               "Upgrade Alert", JOptionPane.ERROR_MESSAGE);
					} catch(MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, "Building has reached Maximum Level!",
					               "Upgrade Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		if(e.getSource() == RecruitArcher && RecruitArcher.isVisible() ) {
			for(int i=0;i<city.getMilitaryBuildings().size();i++) {
				if(city.getMilitaryBuildings().get(i) instanceof ArcheryRange) {
					try {
						game.getPlayer().recruitUnit("Archer", city.getName());
						JOptionPane.showMessageDialog(this, "Recruitment Done!",
					               "Upgrade Success", JOptionPane.INFORMATION_MESSAGE);
						gold.setText(game.getPlayer().getTreasury()+"");
						this.revalidate();
						this.repaint();
					} catch (BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, "Building is Cooling Down!",
					               "Recruit Alert", JOptionPane.ERROR_MESSAGE);
					} catch (MaxRecruitedException e1) {
						JOptionPane.showMessageDialog(this, "The Building have reached Maximum recruits in this turn!",
					               "Recruit Alert", JOptionPane.ERROR_MESSAGE);
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, "You Don't Have enough Gold!",
					               "Treasury Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		else if(e.getSource() == RecruitInfantry && RecruitInfantry.isVisible() ) {
			for(int i=0;i<city.getMilitaryBuildings().size();i++) {
				if(city.getMilitaryBuildings().get(i) instanceof Barracks) {
					try {
						game.getPlayer().recruitUnit("Infantry", city.getName());
						JOptionPane.showMessageDialog(this, "Recruitment Done!",
					               "Upgrade Success", JOptionPane.INFORMATION_MESSAGE);
						gold.setText(game.getPlayer().getTreasury()+"");
						this.revalidate();
						this.repaint();
					} catch (BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, "Building is Cooling Down!",
					               "Recruit Alert", JOptionPane.ERROR_MESSAGE);
					} catch (MaxRecruitedException e1) {
						JOptionPane.showMessageDialog(this, "The Building have reached Maximum recruits in this turn!",
					               "Recruit Alert", JOptionPane.ERROR_MESSAGE);
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, "You Don't Have enough Gold!",
					               "Treasury Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		else if(e.getSource() == RecruitCavalary && RecruitCavalary.isVisible() ) {
			for(int i=0;i<city.getMilitaryBuildings().size();i++) {
				if(city.getMilitaryBuildings().get(i) instanceof Stable) {
					try {
						game.getPlayer().recruitUnit("Cavalry", city.getName());
						JOptionPane.showMessageDialog(this, "Recruitment Done!",
					               "Upgrade Success", JOptionPane.INFORMATION_MESSAGE);
						gold.setText(game.getPlayer().getTreasury()+"");
						this.revalidate();
						this.repaint();
					} catch (BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, "Building is Cooling Down!",
					               "Recruit Alert", JOptionPane.ERROR_MESSAGE);
					} catch (MaxRecruitedException e1) {
						JOptionPane.showMessageDialog(this, "The Building have reached Maximum recruits in this turn!",
					               "Recruit Alert", JOptionPane.ERROR_MESSAGE);
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, "You Don't Have enough Gold!",
					               "Treasury Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		if(e.getSource() == DefendingArmy) {
			JFrame jf = new JFrame();
			//jf.setSize(910, 523);
			jf.setResizable(true);
			jf.setVisible(true);
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jf.setTitle(city.getName()+" Defending Army");
			CityDefendingArmy h = new CityDefendingArmy(city);
			jf.add(h);
			jf.revalidate();
			jf.repaint();
			jf.pack();
		}
		if(e.getSource() == WorldMap) {
			World.onWorld();
		}
		if(e.getSource() == ControlledArmies && ControlledArmies.isVisible()) {
			JFrame jf = new JFrame();
			jf.setVisible(true);
			jf.setSize(910, 523);
			jf.setResizable(false);
			jf.setTitle("ControlledArmies in "+city.getName());
			CityControlledArmies t = null;
			try {
				 t = new CityControlledArmies(game,city);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jf.add(t);
			jf.revalidate();
			jf.repaint();
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
