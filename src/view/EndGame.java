package view;

import java.io.IOException;

import javax.swing.JOptionPane;

public class EndGame extends JPanelWithBackground {
	
	public EndGame(String type) throws IOException {
		super(type+".jpg");
		String message;
		if(type.equals("GameOver"))
			message = "You Have Not Conqured The World, You LOST";
		else
			message = "You Have Conqured The World, You WON";
		JOptionPane.showMessageDialog(this, message,
		         "End Game", JOptionPane.INFORMATION_MESSAGE);
	}

}
