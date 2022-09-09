package control;


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import engine.Game;
import units.Army;
import view.ArmyClickListener;
import view.CityListener;
import view.CityView;
import view.EndTurnListener;
import view.IdleArmy;
import view.Info;
import view.MyArmyListener;
import view.Name;
import view.NameListener;
import view.SetTargetListener;
import view.StartListener;
import view.ToWorldMapListener;
import view.Window;
import view.WorldMap;

public class Controller implements StartListener , NameListener,MyArmyListener,ArmyClickListener, SetTargetListener
,CityListener, ToWorldMapListener, EndTurnListener{
	
	private Game game;
	private Window view;
	private Name name;
	private WorldMap map;
	private IdleArmy idleArmies;
	private Info x;
	private CityView cityView;
	
	public Game getGame() {
		return game;
	}
	
	public Window getView() {
		return view;
	}

	public Controller() {
		view = new Window();
		try {
			name = new Name();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		view.setStartListener(this);
		name.setNameListener(this);
		
	}

	@Override
	public void onEnter(Window x) {
		// TODO Auto-generated method stub
		view.getContentPane().removeAll();
		view.add(name);
		view.revalidate();
		view.repaint();
	}
	
	public static void main(String[]args) {
		Controller x = new Controller();
		
		
	}

	@Override
	public void onConfirm() {
		// TODO Auto-generated method stub
		try {
			game = new Game(name.getText().getText(),(String)name.getNames().getSelectedItem());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.map = new WorldMap(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.setMyArmyListener(this);
		map.setCityListener(this);
		map.setEndTurnListener(this);
		view.getContentPane().removeAll();
		view.add(map);
		view.revalidate();
		view.repaint();
		this.PlaySound("");
	}

	@Override
	public void onMyArmy() {
		// TODO Auto-generated method stub
		idleArmies = new IdleArmy(game);
		idleArmies.setListener(this);
		
	}

	@Override
	public void onArmy(int i) {
		// TODO Auto-generated method stub
		x = new Info(this.game,game.getPlayer().getControlledArmies().get(i));
		x.setListener(this);
		x.revalidate();
		x.repaint();
	}

	@Override
	public void onSetTarget(Army a,String s) {
		// TODO Auto-generated method stub
		game.targetCity(a, s);
		this.x.dispose();
		
	}

	@Override
	public void onCity(String city) {
		// TODO Auto-generated method stub
		for(int i=0;i<game.getPlayer().getControlledCities().size();i++) {
			if(game.getPlayer().getControlledCities().get(i).getName().equals(city)) {
				try {
					cityView = new CityView(game,game.getPlayer().getControlledCities().get(i));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.view.getContentPane().removeAll();
			this.view.add(cityView);
			cityView.setToWorldMapListener(this);
			this.view.revalidate();
			this.view.repaint();
		}
	}

	@Override
	public void onWorld() {
		// TODO Auto-generated method stub
		this.view.getContentPane().removeAll();
		try {
			map = new WorldMap(this);
			map.setCityListener(this);
			map.setMyArmyListener(this);
			map.setEndTurnListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.view.add(map);
		this.view.revalidate();
		this.view.repaint();
	}

	@Override
	public void onEndTurn() {
		// TODO Auto-generated method stub
		try {
			this.map = new WorldMap(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.setMyArmyListener(this);
		map.setCityListener(this);
		map.setEndTurnListener(this);
		view.getContentPane().removeAll();
		view.add(map);
		view.revalidate();
		view.repaint();
	}
	
	public void PlaySound(String filepath){
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Hearthstone Main Theme Cover.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(1000000);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
