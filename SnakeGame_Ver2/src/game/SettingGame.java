package game;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import User.ListUser;
import object.Graphicx;

import java.awt.Color;
import java.awt.Container;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingGame extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton btnPlay;
	private JButton btnHighScore;
	private JButton btnOption;
	private JButton btnExit;
	private JButton btnBackOption;
	private JButton buttonBackHighScore;
	private JRadioButton rdbtnBorder;
	private JRadioButton rdbtnNoBorder;
	private JSlider slider;
	private JTextField lblValueSlider;

	private CardLayout cardPanel;
	
	/*
	 * Static variable
	 */
	private static int valueSlider;					// valueSlider is used to get value from slider
	private static int typeGame = 1;				// typeGame is used to get value when rdbtnBorder or rdbtnNoBorder is selected [Border-1], [No Border-0]
	private static int checkFirstCreate = 0;		// checkFirstCreate is used to confirm that [Graphicx] Panel is added only once time
	
	private Graphicx g;
	private ListUser listuser;
	private JPanel panel_HighScore;
	
	/**
	 * Create the panel.
	 */
	public SettingGame(Container pane, CardLayout card) {
		
		setBackground(Color.WHITE);
		this.setLayout(null);
		this.setSize(640,640);
		
		//Label Introduction 
		JLabel lblNewLabel = new JLabel("Snake 2D");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 99));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(70, 50, 500, 180);
		this.add(lblNewLabel);

		
		// Panel contain panel_main, panel_highscore, panel_option
		JPanel panel = new JPanel();
		cardPanel = new CardLayout(0, 0);
		panel.setBounds(0, 276, 640, 364);
		this.add(panel);
		panel.setLayout(cardPanel);
		
		/*
		 *  Panel Main
		 */
		JPanel panel_Main = new JPanel();
		panel_Main.setBackground(Color.WHITE);
		panel_Main.setFocusTraversalKeysEnabled(false);
		panel.add(panel_Main, "panelMain");
		panel_Main.setLayout(null);
		
		// Button Play Game
		btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPlay.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPlay.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkFirstCreate==0) {
					pane.add(g, "graphic");
					checkFirstCreate=1;
				}
				card.show(pane, "graphic");
				g.setOptionGame(valueSlider, typeGame);
				g.setStartTimer();
				/*
				 * Every 50 millisecond, We'll check Life of Snake
				 * If life is equal 0
				 * Game will return Setting Panel
				 */
				ActionListener action = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(g.getCheckPlayAgain()==1) {
							card.show(pane, "setting");
							g.setCheckPlayAgain(0);
						}
					}
				};
				Timer time = new Timer(50, action);
				time.start();
			}
		});
		btnPlay.setFocusTraversalKeysEnabled(false);
		btnPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlay.setFocusPainted(false);
		btnPlay.setBackground(Color.WHITE);
		btnPlay.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		btnPlay.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnPlay.setBounds(240, 50, 160, 40);
		panel_Main.add(btnPlay);
		
		// Button Show High Score
		btnHighScore = new JButton("High Score");
		btnHighScore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHighScore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHighScore.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHighScore.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				listuser.displayHighScore(panel_HighScore);
				cardPanel.show(panel, "panelHighScore");
			}
		});
		btnHighScore.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		btnHighScore.setFocusTraversalKeysEnabled(false);
		btnHighScore.setFocusPainted(false);
		btnHighScore.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnHighScore.setBackground(Color.WHITE);
		btnHighScore.setBounds(240, 105, 160, 40);
		panel_Main.add(btnHighScore);
		
		// Button Show Option To Custom Game
		btnOption = new JButton("Option");
		btnOption.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOption.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnOption.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnOption.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cardPanel.show(panel, "panelOption");
			}
		});
		btnOption.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		btnOption.setFocusTraversalKeysEnabled(false);
		btnOption.setFocusPainted(false);
		btnOption.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnOption.setBackground(Color.WHITE);
		btnOption.setBounds(240, 160, 160, 40);
		panel_Main.add(btnOption);
		
		//Button Exit Game (Close Frame)
		btnExit = new JButton("Exit");
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		btnExit.setFocusTraversalKeysEnabled(false);
		btnExit.setFocusPainted(false);
		btnExit.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(240, 215, 160, 40);
		panel_Main.add(btnExit);
		
		/*
		 * Panel High Score
		 */
		panel_HighScore = new JPanel();
		panel_HighScore.setBackground(Color.WHITE);
		panel.add(panel_HighScore, "panelHighScore");
		panel_HighScore.setLayout(null);
		
		JLabel lblHighScore = new JLabel("High Score");
		lblHighScore.setFont(new Font("Bahnschrift", Font.BOLD, 35));
		lblHighScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScore.setBounds(220, 10, 200, 60);
		panel_HighScore.add(lblHighScore);
		
		buttonBackHighScore = new JButton("Back");
		buttonBackHighScore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonBackHighScore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonBackHighScore.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonBackHighScore.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				listuser.resetPanelHighScore(panel_HighScore);
				cardPanel.show(panel, "panelMain");
			}
		});
		buttonBackHighScore.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		buttonBackHighScore.setFocusTraversalKeysEnabled(false);
		buttonBackHighScore.setFocusPainted(false);
		buttonBackHighScore.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		buttonBackHighScore.setBackground(Color.WHITE);
		buttonBackHighScore.setBounds(240, 260, 160, 40);
		panel_HighScore.add(buttonBackHighScore);
		
		/*
		 * Panel Option
		 */
		JPanel panel_Option = new JPanel();
		panel_Option.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_Option.setBackground(Color.WHITE);
		panel.add(panel_Option, "panelOption");
		panel_Option.setLayout(null);
		
		JLabel lblGame = new JLabel("Game:");
		lblGame.setBackground(Color.WHITE);
		lblGame.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		lblGame.setBounds(100, 60, 100, 30);
		panel_Option.add(lblGame);
		
		// Radio Button Border To Set Border For Game
		rdbtnBorder = new JRadioButton("Border");
		rdbtnBorder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnBorder.setSelected(true);
				rdbtnNoBorder.setSelected(false);
				typeGame = 1;
			}
		});
		rdbtnBorder.setSelected(true);
		rdbtnBorder.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		rdbtnBorder.setBackground(Color.WHITE);
		rdbtnBorder.setBounds(240, 60, 100, 30);
		panel_Option.add(rdbtnBorder);
		
		// Radio Button No Border To Set None Border For Game
		rdbtnNoBorder = new JRadioButton("No Border");
		rdbtnNoBorder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnBorder.setSelected(false);
				rdbtnNoBorder.setSelected(true);
				typeGame=0;
			}
		});
		rdbtnNoBorder.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		rdbtnNoBorder.setBackground(Color.WHITE);
		rdbtnNoBorder.setBounds(400, 60, 130, 30);
		panel_Option.add(rdbtnNoBorder);
		
		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		lblLevel.setBackground(Color.WHITE);
		lblLevel.setAlignmentX(0.5f);
		lblLevel.setBounds(100, 130, 101, 30);
		panel_Option.add(lblLevel);
		
		// Slider To Set Level Fast Or Slow For Game
		slider = new JSlider();
		valueSlider = slider.getValue();
		slider.setBounds(240, 130, 220, 30);
		panel_Option.add(slider);
		
		lblValueSlider = new JTextField(String.valueOf(valueSlider));
		lblValueSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblValueSlider.setBorder(new LineBorder(Color.BLACK,1));
				lblValueSlider.setEditable(true);
			}
		});
		lblValueSlider.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					slider.setValue(Integer.parseInt(lblValueSlider.getText()));
					lblValueSlider.setBorder(null);
					lblValueSlider.setEditable(false);
				}
			}
		});
		panel_Option.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Reset Value Of Slider When Input From Keyboard
				slider.setValue(Integer.parseInt(lblValueSlider.getText()));
				lblValueSlider.setBorder(null);
				lblValueSlider.setEditable(false);
			}
		});
		lblValueSlider.setBorder(null);
		lblValueSlider.setBackground(Color.WHITE);
		lblValueSlider.setEditable(false);
		lblValueSlider.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		lblValueSlider.setBounds(480, 130, 40, 30);
		panel_Option.add(lblValueSlider);
		
		// Reset Text Of lblValueSlider When Slider Change Value
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				valueSlider = slider.getValue();
				lblValueSlider.setText(String.valueOf(valueSlider));
			}
		});
		
		// Button To Come Back To Panel Main
		btnBackOption = new JButton("Back");
		btnBackOption.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBackOption.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBackOption.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cardPanel.show(panel, "panelMain");
			}
		});
		btnBackOption.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBackOption.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		btnBackOption.setFocusTraversalKeysEnabled(false);
		btnBackOption.setFocusPainted(false);
		btnBackOption.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnBackOption.setBackground(Color.WHITE);
		btnBackOption.setBounds(240, 220, 160, 40);
		panel_Option.add(btnBackOption);
		
		listuser = new ListUser();
		g = new Graphicx(listuser);
	}

}
