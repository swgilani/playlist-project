
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javafx.scene.text.Font;

public class MainFrame extends JFrame{
	
	public ArrayList<String> songs;
	public ArrayList<String> playlist_songs = new ArrayList<String>();
	public ArrayList<String> queued_songs = new ArrayList<String>();
	JTextArea current_songs = new JTextArea(35,35);
	JTextArea play_songs = new JTextArea(50,30);
	
	JLabel amount_of_playlists = new JLabel();
	
	JLabel amount_of_songs = new JLabel();
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;

	private final User user;
	JPanel userPanel;
	JPanel currentPanel;
	JPanel playlistPanel;
	JPanel songlistPanel;
	
	public MainFrame(User u)
	{
		this.user = u;
	    this.setTitle("CPS420 DJ APP");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(1100, 600);
    
	    initializeComponents();
	    
	   // this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);
	    this.setVisible(true);
	}
	
	
	//Adding components to the playlist panel
	void playlistPanel_components(JPanel playlistPanel) {

		//Adding a title to the panel
		//initializing another panel to make space for title
		JPanel top_panel = new JPanel(new BorderLayout());
		playlistPanel.add(top_panel, BorderLayout.NORTH);
		//making the title itself
		JLabel title = new JLabel("PLAYLIST");
		title.setForeground(Color.white);
		JPanel title_panel = new JPanel();
		top_panel.add(title_panel);
		title_panel.add(title);
	
		
		//Adding the add and next buttons to the panel
		//initializing another panel to make space for the title
		JPanel bottom_panel = new JPanel (new BorderLayout());
		playlistPanel.add(bottom_panel, BorderLayout.SOUTH);
		JButton add_button = new JButton("ADD");
		 add_button.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	
	            	if (playlist_songs.size() == 0) {
	            	JOptionPane.showMessageDialog(null, 
                            "THERE ARE NO SONGS IN YOUR PLAYLIST. PLEASE ADD SONGS TO YOUR PLAYLIST FROM THE DATABASE BY CLICKING ON THEM", 
                            "NO SONGS IN PLAYLIST", 
                            JOptionPane.WARNING_MESSAGE);
	            
	            	}
	            	else {
	            	queued_songs.add(playlist_songs.get(0));
	            	current_songs.append(playlist_songs.get(0)+"\n");
	            	playlist_songs.remove(0);
	            	
	            	
	            	play_songs.setText(null);
	            	for (int i = 0; i < playlist_songs.size();i++) {
	            		play_songs.append(playlist_songs.get(i) + "\n");
	            	}
	            	
	            	
	            	
	            	
	            	amount_of_songs.setText("You have "+ queued_songs.size()+ " songs currentlyin queue");
	            	amount_of_playlists.setText("You have " + playlist_songs.size() +" songs in your playlists");
	            	
	            	}
	            }
	            
	        });
		
		
		
		
		JButton back_button = new JButton("BACK");
		back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (queued_songs.size() == 0) {
	            	JOptionPane.showMessageDialog(null, 
                            "THERE ARE NO SONGS IN QUEUE. PLEASE ADD SONGS TO QUEUE FROM YOUR PLAYLIST", 
                            "NO SONGS IN QUEUE", 
                            JOptionPane.WARNING_MESSAGE);
	            
	            	}
            	else {
            		playlist_songs.add(0, queued_songs.get(0));
            		current_songs.setText(null);
            		queued_songs.remove(0);
            		
            		play_songs.setText(null);
	            	for (int i = 0; i < playlist_songs.size();i++) {
	            		play_songs.append(playlist_songs.get(i) + "\n");
	            	}
	            	
	            	for (int i = 0; i < queued_songs.size();i++) {
	            		current_songs.append(queued_songs.get(i) + "\n");
	            	}
	            	
	            	
	            	amount_of_songs.setText("You have "+ queued_songs.size()+ " songs currentlyin queue");
	            	amount_of_playlists.setText("You have " + playlist_songs.size() +" songs in your playlists");
	            		
            	}
            
            }
            
        });
		
		
		
		
		
		
		
		JPanel buttons_panel = new JPanel();
		bottom_panel.add(buttons_panel);
		buttons_panel.add(back_button);
		buttons_panel.add(add_button);
		
		//Adding the text area in the middle for the current songs in the playlist
		//initializing another panel to make space for the text area
		JPanel center_panel = new JPanel (new BorderLayout());
		playlistPanel.add(center_panel, BorderLayout.CENTER);
		
		//this is the text area where the songs will be displayed in the playlist
		
		play_songs.append("");
		play_songs.setEditable(false);
		
		//making a panel so the songlist will be on the right side and the playlist buttons on the left
		JPanel playlist_and_song_list_panel = new JPanel(new BorderLayout());
		JPanel song_list_panel = new JPanel();
		JPanel playlists_panel = new JPanel();
		playlists_panel.setLayout(new BoxLayout(playlists_panel,BoxLayout.PAGE_AXIS));
		center_panel.add(playlist_and_song_list_panel);
		playlist_and_song_list_panel.add(song_list_panel,BorderLayout.EAST);
		playlist_and_song_list_panel.add(playlists_panel,BorderLayout.WEST);
		song_list_panel.add(play_songs);
		
		//These are the playlists of the current user. TODO: switch between them by clicking on the different ones
		
		
		JButton playlist1 = new JButton("PLAYLIST 1");
		JButton playlist2 = new JButton("PLAYLIST 2");
		JButton playlist3 = new JButton("PLAYLIST 3");
		JButton playlist4 = new JButton("PLAYLIST 4");
		JButton playlist5 = new JButton("PLAYLIST 5");
		playlists_panel.add(playlist1);
		playlists_panel.add(Box.createRigidArea(new Dimension(0,5)));
		playlists_panel.add(playlist2);
		playlists_panel.add(Box.createRigidArea(new Dimension(0,5)));
		playlists_panel.add(playlist3);
		playlists_panel.add(Box.createRigidArea(new Dimension(0,5)));
		playlists_panel.add(playlist4);
		playlists_panel.add(Box.createRigidArea(new Dimension(0,5)));
		playlists_panel.add(playlist5);
		
		//setting background color of the playlist panel to grey bc grey is sick
		title_panel.setBackground(Color.darkGray);
		playlist_and_song_list_panel.setBackground(Color.DARK_GRAY);
		buttons_panel.setBackground(Color.darkGray);
		song_list_panel.setBackground(Color.darkGray);
		playlists_panel.setBackground(Color.DARK_GRAY);
	}
	
	
	
	
	void songlistPanel_components(JPanel playlistPanel) {

		//Adding a title to the panel
		//initializing another panel to make space for title
		JPanel top_panel = new JPanel(new BorderLayout());
		playlistPanel.add(top_panel, BorderLayout.NORTH);
		//making the title for the song info
		JLabel song_info = new JLabel("WAITING TO BE PLAYED (QUEUE)            ");
		JLabel q_info = new JLabel("SONG LIST (CLICK TO ADD TO PLAYLIST)");
		q_info.setForeground(Color.white);
		song_info.setForeground(Color.white);
		JPanel title_panel = new JPanel();
		JPanel q_panel = new JPanel();
		top_panel.add(title_panel,BorderLayout.EAST);
		top_panel.add(q_panel,BorderLayout.WEST);
		title_panel.add(song_info);
		q_panel.add(q_info);
	
		
		//Adding the sorting buttons on the bottom
		JPanel bottom_panel = new JPanel (new BorderLayout());
		playlistPanel.add(bottom_panel, BorderLayout.SOUTH);
		JButton name_butt = new JButton("SORT BY NAME");
		JButton release_butt = new JButton("SORT BY YEAR OF RELEASE");
		JButton genre_butt = new JButton("SORT BY GENRE");
		JPanel buttons_panel = new JPanel();
		bottom_panel.add(buttons_panel);
		buttons_panel.add(name_butt);
		buttons_panel.add(release_butt);
		buttons_panel.add(genre_butt);
		
		//Adding the text area in the middle for the current songs in the playlist
		//initializing another panel to make space for the text area
		JPanel center_panel = new JPanel (new BorderLayout());
		playlistPanel.add(center_panel, BorderLayout.CENTER);
		
		//this is the text area where the songs will be displayed in the playlist
		
		
		
		
		current_songs.append("");
		current_songs.setEditable(false);
		
		//making a panel so the songlist will be on the right side and the playlist buttons on the left
		JPanel playlist_and_song_list_panel = new JPanel(new BorderLayout());
		JPanel song_list_panel = new JPanel();
		JPanel playlists_panel = new JPanel(new BorderLayout());
		playlists_panel.setLayout(new BoxLayout(playlists_panel,BoxLayout.PAGE_AXIS));
		center_panel.add(playlist_and_song_list_panel);
		playlist_and_song_list_panel.add(song_list_panel,BorderLayout.EAST);
		playlist_and_song_list_panel.add(playlists_panel,BorderLayout.CENTER);
		song_list_panel.add(current_songs);
		
		//These are the playlists of the current user. TODO: switch between them by clicking on the different ones
		
		

	
		songs = new ArrayList<String>();
	//	playlist_songs = new ArrayList<String>();
		addSongs(songs);
		
		
		DefaultListModel<String> list = new DefaultListModel<String>();
		
		for (int i = 0; i < songs.size(); i ++) {
			list.addElement(songs.get(i));
		}
		
		JList<String> queue_list = new JList<String>(list);
		
		
		
		queue_list.setVisibleRowCount(2);
		queue_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		 queue_list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (e.getValueIsAdjusting()) return;
				
				
				int selectedIndex = queue_list.getSelectedIndex();
				//System.out.println(selectedIndex);
				if (selectedIndex != -1 && songs.size() >= 1 ) {
					// System.out.println(list.getElementAt(selectedIndex));
					 
				
					 playlist_songs.add(list.getElementAt(selectedIndex));
					 play_songs.append(list.getElementAt(selectedIndex)+"\n");  
					 
					 for (int i = 0; i< playlist_songs.size(); i++) {
						 System.out.println(playlist_songs.get(i));
					 }
				}
				DefaultListModel model = (DefaultListModel) queue_list.getModel();
				if (selectedIndex != -1) {
				    model.remove(selectedIndex);
				    amount_of_songs.setText("You have "+ queued_songs.size()+ " songs currentlyin queue");
					amount_of_playlists.setText("You have " + playlist_songs.size() +" songs in your playlists");
					
				}
			}
			 
		 }); 
		
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane(queue_list);
		playlists_panel.add(scrollPane, BorderLayout.WEST);
	
		
		
		
		

		//setting background color of the playlist panel to grey bc grey is sick
		title_panel.setBackground(Color.darkGray);
		playlist_and_song_list_panel.setBackground(Color.DARK_GRAY);
		buttons_panel.setBackground(Color.darkGray);
		song_list_panel.setBackground(Color.darkGray);
		playlists_panel.setBackground(Color.DARK_GRAY);
		q_panel.setBackground(Color.DARK_GRAY);
		top_panel.setBackground(Color.DARK_GRAY);
	}
	
	
	
	void addSongs(ArrayList<String> songs) {
		songs.add("Old Town Road");
		songs.add("Sunflower");
		songs.add("7 Rings");
		songs.add("Wow.");
		songs.add("Without Me");
		songs.add("Please Me");
		songs.add("Bad Guy");
		songs.add("Sucker");
		songs.add("Happier");
		songs.add("Middle Child");
		songs.add("Shallow");
		songs.add("Eastside");
		songs.add("Thotiana");
		songs.add("Going Bad");
		songs.add("Dancing with a Stranger");
		songs.add("Dancing with a Stranger");
		songs.add("High Hopes");
		songs.add("Better");
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//adding components to the user panel
	void userPanel_components(JPanel userPanel) {
		//getting the users name from LoginFrame.java
		String hello = LoginFrame.user_name;
		
		//separating the panel into two panels -> name panel(on the left) and user info panel(on the right)
		JPanel name_panel = new JPanel();
		JPanel user_info_panel = new JPanel();
		userPanel.add(name_panel, BorderLayout.WEST);
		userPanel.add(user_info_panel, BorderLayout.EAST);
	
		//making a greeting for the user inside the name_panel so its like "yo sup wasiq in the top left"
		JLabel greeting = new JLabel("Hello "+ hello);
		greeting.setForeground(Color.white);
		name_panel.add(greeting);
		
		
		//creating a different panel so it can list the number of songs and number of playlists on the top right
		JPanel user_playlist_info = new JPanel();
		user_playlist_info.setLayout(new BoxLayout(user_playlist_info,BoxLayout.PAGE_AXIS));
		user_info_panel.add(user_playlist_info);
		
		
		
		amount_of_songs.setText("You have "+ queued_songs.size()+ " songs currentlyin queue");
		amount_of_playlists.setText("You have " + playlist_songs.size() +" songs in your playlists");
		
		
		amount_of_playlists.setForeground(Color.white);
		amount_of_songs.setForeground(Color.white);
		
		user_playlist_info.add(amount_of_playlists);
		user_playlist_info.add(amount_of_songs);
		
		
		
		
		
		
	//changing color to dark grey bc its a sick color	
	name_panel.setBackground(Color.DARK_GRAY);
	user_playlist_info.setBackground(Color.DARK_GRAY);
	user_info_panel.setBackground(Color.DARK_GRAY);
		
		
	}
	
	
	
	void initializeComponents()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		userPanel = new JPanel(new BorderLayout());
		userPanel_components(userPanel);
		userPanel.setBackground(Color.DARK_GRAY);
		//top-left is x=0, y=0
		gbc.gridx = 0;
		gbc.gridy = 0;
		//weight determines how much of free space it fills up
		gbc.weightx = 1.0;
		gbc.weighty = .25;
		this.add(userPanel,gbc);
		
		currentPanel = new JPanel();
		currentPanel.setBackground(Color.red);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weighty = .25;
		this.add(currentPanel,gbc);
		
		
		
		playlistPanel = new JPanel(new BorderLayout());
		playlistPanel_components(playlistPanel);
		playlistPanel.setBackground(Color.green);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1.0;
		this.add(playlistPanel,gbc);
		
		
		
		
		songlistPanel = new JPanel(new BorderLayout());
		songlistPanel_components(songlistPanel);
		
		//songlistPanel.setBackground(Color.magenta);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weighty = 1.0;
		this.add(songlistPanel,gbc);
	}
	
	
	class LogoutListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
		
	}

}