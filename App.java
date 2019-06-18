import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class App{

	static LoginFrame login;
	static MainFrame main;

	public static void main(String[] args)
	{
		openLogin();
	}

	private static void openLogin()
	{
		login = new LoginFrame();
		login.setLocationRelativeTo(null);

		// add a component listener
		login.addComponentListener(new ComponentAdapter() {
			//setVisible(false) triggered when the user validated
			public void componentHidden(ComponentEvent e) {
				try {
					openMain(login.getUser());
					login.dispose();
				}
				catch(Exception err)
				{
					System.out.println(err);
				}
			}
		});

		login.setVisible(true);
	}

	private static void openMain(User user)
	{
		main = new MainFrame(user);
		main.setLocationRelativeTo(null);

		// add a component listener
		main.addComponentListener(new ComponentAdapter() {
			//setVisible(false) triggered when the user validated
			public void componentHidden(ComponentEvent e) {
				login = new LoginFrame();
				main.dispose();
			}
		});

		main.setVisible(true);
	}
}
