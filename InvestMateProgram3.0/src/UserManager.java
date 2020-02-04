import java.util.ArrayList;

/**
 * This class helps the ui connect to the database in a non invasive way
 * 
 * @author davidcasente
 *
 */
public class UserManager {

	/**
     * 
     * @param name
     * @param password
     * @param age
     * @param port
     * @return the success of adding a user to the database
     */
	public static boolean addUser(String name, String pw, int age, Portfolio port) {
		UserDatabase data = new UserDatabase();
		boolean add = data.addUser(name, pw, age, port);
		return add;
	}

	 
	/**
     * 
     * @param name
     * @return a particular user
     */
	public static User getUser(String name) {
		UserDatabase data = new UserDatabase();
		User output = data.getAUser(name);
		return output;
	}

	/**
     * 
     * @return all the users int the applicaiton
     */
	public static ArrayList<User> getUsers() {
		UserDatabase data = new UserDatabase();
		ArrayList<User> output = data.getUsers();
		return output;
	}

	/**
	 * 
	 * @param name
	 * @param Ps
	 * @return whether a user exists or not
	 */
	public static boolean checkUser(String name, String Ps) {
		UserDatabase data = new UserDatabase();
		ArrayList<User> users = data.getUsers();
		for (int i = 0; i < users.size(); i++) {
			if (name.equals(users.get(i).getName()) || Ps.equals(users.get(i).getPassword()))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @param name
	 * @param Ps
	 * @return the user class of the user logging in
	 */
	public static User Login(String name, String Ps) {
		UserDatabase data = new UserDatabase();
		return data.getAUser(name);
	}

	/**
	 * 
	 * @param oldName
	 * @param newName
	 * @return success of updating a particular user's name
	 */
	public static boolean updateName(String old, String newName) {
		UserDatabase data = new UserDatabase();

		boolean update = data.updateName(old, newName);
		return update;

	}

	 /**
     * 
     * @param name
     * @param newAge
     * @return Update age of a user specified by the name
     */
	public static boolean updateAge(String name, int newAge) {
		UserDatabase data = new UserDatabase();

		boolean update = data.updateAge(name, newAge);

		return update;
	}

	/**
     * 
     * @param name
     * @param newPassword
     * @return Update password of a user specified by the name
     */
	public static boolean updatePassword(String name, String newPassword) {
		UserDatabase data = new UserDatabase();

		boolean update = data.updatePassword(name, newPassword);

		return update;

	}

	/**
     * 
     * @param name
     * @param port
     * @return Update portfolio of a user specified by the name
     */
	public static boolean updatePortfolio(String name, Portfolio port) {
		UserDatabase data = new UserDatabase();

		boolean update = data.updatePortfolio(name, port);

		return update;
	}

}