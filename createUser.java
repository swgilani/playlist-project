import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.LinkedList;

//class for creating test data users
public class createUser{
	
	static Song[] s;
	
	public static void main(String[] args) {
		// create User object, fill it with data, then write it to a file using ObjectOutputStream
		//String name, String genre, String artist, String album, int length, int year
		s = new Song[5];
		s[0] = new Song("Yesterday","Baroque Pop","The Beatles","Help!",123,1965);
		s[1] = new Song("I've Just Seen a Face","Folk Rock","The Beatles","Help!",127,1965);
		s[2] = new Song("Forget Not","Metal","Ne Obliviscaris","The Aurora Veil",720,2007);
		s[3] = new Song("Gethsemane","Rock","Ted Neely","Jesus Christ Superstar",356,1973);
		s[4] = new Song("1 Hour Test","Rock","TestArtist","TestAlbum",3600,2019);
		
		File dir = new File("./Users");
		if(!dir.exists()){
			dir.mkdir();
		}
		
		newUser();
		newAdmin();
		testRead();
	}
	
	public static void newUser()
	{
		
		LinkedList<Song> list = new LinkedList<Song>();
		list.add(s[0]);
		list.add(s[1]);
		list.add(s[2]);
		list.add(s[4]);
		
		HashMap<String,LinkedList<Song>> map = new HashMap<String,LinkedList<Song>>();
		map.put("playlist1",list);
		User user = new User("user",map);
		
		user.saveUser();
		System.out.println("User:\n"+user);
		
	}
	
	public static void newAdmin()
	{
		LinkedList<Song> list = new LinkedList<Song>();
		list.add(s[0]);
		list.add(s[2]);
		list.add(s[4]);
		
		HashMap<String,LinkedList<Song>> map = new HashMap<String,LinkedList<Song>>();
		map.put("myFirstPlaylist", list);
		
		list.add(s[1]);
		map.put("Playlist2",list);
		User admin = new User("admin",map);
		admin.setAdmin(true);
		
		admin.saveUser();
	}
	
	public static void testRead()
	{
    	try {
    	FileInputStream fis = new FileInputStream("./Users/user");
    	ObjectInputStream ois = new ObjectInputStream(fis);
    	
    	User u = (User) ois.readObject();
    	ois.close();
    	
    	System.out.println("Read:\n"+u);
    	
    	}catch(Exception e) {
    		System.out.println(e);
    	};
	}

}
