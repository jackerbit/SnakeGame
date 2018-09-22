package object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import User.ListUser;
import User.User;

public class Graphicx extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	/*
	 * Static Variable 
	 */
	private static int direction = 3;			// Direction Of Snake, Default is 3,   [UP-1] [DOWN-2] [RIGHT-3] [LEFT-4]
	private static int level;					// Level is a time delay of Timer
	private static int value;					// Value is a value of Slider 
	private static int type;					// type is type of game [Border-1] or [No Border-0]
	private static int checkstop = 0;			// checkstop is used to check number of type [ENTER] from keyboard
	
	private int checkPlayAgain;					// checkPlayAgain is used to check btnCheckPlayAgain is clicked
	
	private ListUser listuser;
	private Objects obj;
	private Food food;
	private Timer time;							// Timer of time
	
	public Graphicx(ListUser listuser) {
		
		this.checkPlayAgain = 0;
		
		// listuser is just a reference variable from SettingGame Class
		this.listuser = listuser;
		
		obj = new Objects();
		food = new Food();
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_UP: if(direction!=2)	 direction = 1; break;
					case KeyEvent.VK_DOWN: if(direction!=1)	 direction = 2; break;
					case KeyEvent.VK_RIGHT: if(direction!=4) direction = 3; break;
					case KeyEvent.VK_LEFT: if(direction!=3)	 direction = 4; break;
					case KeyEvent.VK_W: if(direction!=2)	 direction = 1; break;
					case KeyEvent.VK_S: if(direction!=1)	 direction = 2; break;
					case KeyEvent.VK_D: if(direction!=4)	 direction = 3; break;
					case KeyEvent.VK_A: if(direction!=3)	 direction = 4; break;
					case KeyEvent.VK_ENTER:{
						if(checkstop==0) {
							time.stop();
							checkstop=1;
						}
						else {
							time.start();
							checkstop=0;
						}
						break;
					}
				}
			}
		});
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
	}
	
	/*
	 * Graphics
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		obj.paintSnake(g);
		obj.paintLife(g);
		food.paintFood(g);
		
		initGame(g);
	}
	
	public void initGame(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		float[] dash1 = {1f};
		BasicStroke dashed = new 
				BasicStroke(3f, 
						 	BasicStroke.CAP_SQUARE, 
						 	BasicStroke.CAP_SQUARE, 
						 	10f, dash1, 0f);
		g2d.setStroke(dashed);
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Bahnschrift",Font.BOLD,20));
		
		// board
		g2d.drawRect(20, 20, 580, 500);
		
		// score
		g2d.drawString("Score: "+food.getScore(), 20, 560);
		
		// level
		g2d.drawString("Level: " + value, 500, 560);
	}
	
	/*
	 * Set Property For Game
	 */
	
	// Start timer when player click btnPlay
	public void setStartTimer() {
		time.start();
	}
	
	// Stop timer when life's snake is equal 0
	public void setStopTimer() {
		time.stop();
	}
	
	// Set Option For Game
	public void setOptionGame(int valueSlider, int typeGame) {
		
		/*
		 * Minimum and maximum of delay of timer is between [180-40] -> Distance = 140
		 * Minimum and maximum of slider is between [0-100]	 -> Distance = 100
		 * => level = valueSlider*(-140/100)+180;
		 * => level[Min] = 180 when slider[Min] = 0
		 *	  level[Max] = 40  when slider[Max] = 100
		 */
		level = (int)(valueSlider*(-1.4)+180);
		value = valueSlider;
		type = typeGame;
		
		// Create new Timer to Run Game
		time = new Timer(level, this);
		
		// Must Set requestFocusInWindow is true to Focus KeyListener From KeyBoard [Important !]
		this.requestFocusInWindow(true);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}
	
	
	/*
	 * Game Over
	 */
	public void gameover(JPanel panel_Gphx) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		panel.setBounds(115, 130, 400 , 250);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setBounds(50, 30, 450, 60);
		lblGameOver.setFont(new Font("Bahnschrift", Font.BOLD, 60));
		lblGameOver.setForeground(Color.WHITE);
		panel.add(lblGameOver);
		
		JLabel lblPlayerName = new JLabel("Your Name: ");
		lblPlayerName.setBounds(50, 120, 200, 40);
		lblPlayerName.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		lblPlayerName.setForeground(Color.WHITE);
		panel.add(lblPlayerName);
		
		JTextField tfNamePlayer = new JTextField("Unkown Player");
		tfNamePlayer.setBorder(new LineBorder(Color.WHITE, 1));
		tfNamePlayer.setBackground(Color.BLACK);
		tfNamePlayer.setForeground(Color.WHITE);
		tfNamePlayer.setEditable(true);
		tfNamePlayer.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		tfNamePlayer.setBounds(180, 125, 180, 30);
		panel.add(tfNamePlayer);
		
		JButton btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlayAgain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPlayAgain.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPlayAgain.setBackground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 *  When btnPlayAgain is clicked
				 *  +) addUser into ArrayList[list] 
				 *  +) write user into database
				 *  +) set Score = 0, Set score in this moment because we have to getScore to store into database then we set score by 0
				 *  +) setCheckPlayAgain is equal 1 to confirm that Player is done with game 
				 *  +) Remove this panel to do not show it again when player replay game
				 */
				listuser.addUser(new User(tfNamePlayer.getText(), food.getScore()));
				listuser.writeUser();
				food.setScore(0);
				setCheckPlayAgain(1);
				panel_Gphx.remove(panel);
			}
		});
		btnPlayAgain.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		btnPlayAgain.setFocusTraversalKeysEnabled(false);
		btnPlayAgain.setFocusPainted(false);
		btnPlayAgain.setBorder(new LineBorder(Color.WHITE, 3, true));
		btnPlayAgain.setBackground(Color.BLACK);
		btnPlayAgain.setForeground(Color.WHITE);
		btnPlayAgain.setBounds(130, 180, 160, 40);
		panel.add(btnPlayAgain);
		
		panel_Gphx.add(panel);
	}
	
	/*
	 * Action Game
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		obj.moveObjects(direction);
		
		obj.checkCollision();
		food.checkCollision(obj);
		
		if(type==1) {
			obj.resetCoord();
			obj.border();
		}
		else {
			obj.noborder();
			obj.resetCoord();
		}
		
		if(obj.getCheckResetGame()==1) {
			direction=3;
			obj.setCheckResetGame(0);
		}
		if(obj.getLife()==0) {
			setStopTimer();
			obj.reset();
			this.gameover(this);
		}
		
		repaint();
	}
	
	/*
	 * Getter, Setter
	 */
	public int getCheckPlayAgain() {
		return this.checkPlayAgain;
	}
	public void setCheckPlayAgain(int checkPlayAgain) {
		this.checkPlayAgain = checkPlayAgain;
	}
	
}
