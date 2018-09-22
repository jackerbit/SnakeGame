package User;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ListUser {
	/*
	 * list is used to store Object User
	 * arJLabels is used to store JLabel 
	 * 				displaying information high score of player
	 */
	private ArrayList<User> list;
	private ArrayList<JLabel> arJLabels;
	
	public ListUser() {
		list = new ArrayList<User>();
		arJLabels = new ArrayList<JLabel>();
		
		this.readUser();
	}
	
	// Add new user when Player is done with game
	public void addUser(User user) {
		list.add(user);
	}
	
	// Read all information of user is stored in database into ArrayList[list]
	public void readUser() {
		File f = new File("highscore.txt");
		FileReader fr;
		BufferedReader br;
		if(f.exists()) {
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				while(br.ready()) {
					String tmp = br.readLine();
					String[] arUser = tmp.split("-");
					addUser(new User(arUser[0], Integer.parseInt(arUser[1])));
				}
				fr.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Write all user in ArrayList[list] into database
	public void writeUser() {
		sortScore();
		PrintWriter pw;
		try {
			pw = new PrintWriter("highscore.txt");
			for(int i=0;i<list.size();i++) {
				pw.write(list.get(i).toString());
				pw.write("\n");;
			}
			pw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Sort Descending Score's Player
	public void sortScore() {
		for(int i=0;i<list.size()-1;i++) {
			for(int k=i+1;k<list.size();k++) {
				if(list.get(i).getScore()<list.get(k).getScore()) {
					String tmpName = list.get(i).getName();
					int tmpScore = list.get(i).getScore();
					list.get(i).setName(list.get(k).getName());
					list.get(i).setScore(list.get(k).getScore());
					list.get(k).setName(tmpName);
					list.get(k).setScore(tmpScore);
				}
			}
		}
	}
	
	// Display high score when player want to see
	public void displayHighScore(JPanel panel) {
		if(list.size()>0) {
			int tmpx = 160;
			int tmpy = 80;
			int size = (list.size()<=4) ? list.size() : 4;
			for(int i=0;i<size;i++) {
				JLabel name =new JLabel(list.get(i).getName());
				name.setHorizontalAlignment(SwingConstants.CENTER);
				name.setBounds(tmpx, tmpy, 180, 30);
				name.setFont(new Font("Bahnschrift", Font.BOLD, 20));
				panel.add(name);
				arJLabels.add(name);
				
				tmpx+=230;
				
				JLabel score = new JLabel(String.valueOf(list.get(i).getScore()));
				score.setHorizontalAlignment(SwingConstants.CENTER);
				score.setBounds(tmpx, tmpy, 50, 30);
				score.setFont(new Font("Bahnschrift", Font.BOLD, 20));
				panel.add(score);
				arJLabels.add(score);
				
				tmpx = 160;
				tmpy+=40;
			}
		}
	}
	
	// Remove all JLabel in ArrayList[arJLabels] when player exit
	// That means to reDisplay again
	public void resetPanelHighScore(JPanel panel) {
		for(int i=0;i<arJLabels.size();i++)
			panel.remove(arJLabels.get(i));
		arJLabels.removeAll(arJLabels);
	}
}
