import java.util.*;
import java.io.*;
import javax.swing.JTextArea;
public class User {
	private HashMap <String, LinkedList<Song>> playlists;
	private String uID;

	public User(String uID) {
		this.uID = uID;
		playlists = new HashMap <String, LinkedList<Song>> ();
	}

	public User(String uID, HashMap <String, LinkedList<Song>> playlists) {
		this.uID = uID;
		this.playlists = playlists;
	}

	public void addToPlaylist(String p, Song song) {
		playlists.get(p).add(song);
	}

	public void addPlaylist(String name, LinkedList<Song> list) {
		playlists.put(name, list);
	}

	public LinkedList<Song> getPlaylist(String name) {
		return playlists.get(name);
	}

	public void display(JTextArea area) {
		LinkedList<Song> l = playlists.get("test_playlist_1.txt");
		for (int i = 0; i < l.size(); i++) area.append(l.get(i).print() +"\n");
	}
	
	public void saveUser()
	{
		//TODO: write to file with ObjectOutputStream
	}
}
