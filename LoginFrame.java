
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;


public class LoginFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	JPanel panel;
    JLabel user_label, password_label, message;
    JTextField username_text;
    JPasswordField password_text;
    JButton submit;
    User current_user;
    public static String user_name = "";
    
	
	public LoginFrame () {
		// set frame attributes
	    this.setTitle("Login");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 100);
		
		// User Label
	    user_label = new JLabel();
	    user_label.setText("User Name :");
	    username_text = new JTextField();
	    
	    // Password
	    password_label = new JLabel();
	    password_label.setText("Password :");
	    password_text = new JPasswordField();
	    
        submit = new JButton("SUBMIT");

        panel = new JPanel(new GridLayout(3, 1));
        
        panel.add(user_label);
        panel.add(username_text);
        panel.add(password_label);
        panel.add(password_text);

        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        
        //add an action listener for the submit button
        submit.addActionListener(this);
        
        // finally, add the panel to the frame
        this.add(panel, BorderLayout.CENTER);

	}

    @Override
    public void actionPerformed(ActionEvent ae) {
        String username = username_text.getText();
        String password = password_text.getPassword().toString();

        if(isValidUser(username,password))
        {
        	// we need the window for a little bit longer to process the userInfo
        	// but we hide it show the user something has happened
        	this.setVisible(false);
        }
        else
        {
        	final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Incorrect Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
            password_text.setText("");
        } 
    }
    
    private boolean isValidUser(String u, String p)
    {
    	//TODO: check username and password against some store
    	//in the meantime, we'll use this stub
    	if(u.equals("user") || u.equals("admin"))
    	{
    		
    		//save the user's name so it can appear in the main frame as their name.
    		if (u.equals("user")) {
    			user_name = "User";
    		}
    		else if (u.equals("admin")) {
    			user_name = "Admin";
    		}
    		
    		
    		
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public User getUser()
    {
    	
    	return current_user;
    	//TODO: return a user object using ObjectInputStream
    	// User objects are written to files using ObjectOutputStream
    	
    }
}
