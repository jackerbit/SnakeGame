package game;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SnakeGame_Ver2 extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private CardLayout cardJFrame;
	private SettingGame setting;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				SnakeGame_Ver2 game = new SnakeGame_Ver2();
				game.setVisible(true);
			}
		});

	}
	
	/*
	 * Create JFrame
	 */
	public SnakeGame_Ver2() {
		
		this.setTitle("Snake 2D");
		cardJFrame = new CardLayout(0, 0);
		
		ImageIcon icon = new ImageIcon("iconGame.png");
		this.setIconImage(icon.getImage());
		
		this.setBounds(500,150,640,640);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(cardJFrame);
		
		setting = new SettingGame(getContentPane(), cardJFrame);
		getContentPane().add(setting, "setting");
		
	}

}
