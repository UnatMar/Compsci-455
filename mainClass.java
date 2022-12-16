import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Scanner;

// anneIMoyR1NUOKmO4qgBEA
public class mainClass
{

	public static void main(String[] args)
	{
		try
		{
			
			String username = "";
			String password = "";
			String filePath = "login.txt";

			Scanner userInputStr = new Scanner(System.in);
			
			System.out.println("Secure Systems TM Login");
			System.out.println("------------------------------------------");
			System.out.print("Enter public key: ");
			String publicKey = userInputStr.nextLine();
			System.out.println("Public Key is: " + publicKey);
			System.out.println();

			System.out.print("Enter private key: ");
			String privateKey = userInputStr.nextLine();
			System.out.println("Private Key is: " + privateKey);
			System.out.println();
			
			System.out.println("Would you like to login (Enter '1') or create a new account(Enter '2')?");
			String response = userInputStr.nextLine();
			System.out.println();
			
			if(response.equals("1"))
			{
				
				System.out.println();
				System.out.print("Enter username: ");
				username = userInputStr.nextLine();
				System.out.print("Enter password: ");
				password = userInputStr.nextLine();
				System.out.println();
				
				boolean verify = verifyLogin(username, password, filePath, privateKey);
				
				if(verify == true)
				{
					System.out.println("Sucessful Login!");
				}
				else
				{
					System.out.println("Login Failed!");
				}
				
			}
			else if(response.equals("2"))
			{
				
				System.out.println();
				System.out.print("Enter new username: ");
				username = userInputStr.nextLine();
				
				while(username.length() < 6)
				{
					
					System.out.println("Username needs to be at least 6 characters!");
					System.out.println();
					System.out.print("Enter new username: ");
					username = userInputStr.nextLine();
					
				}
				
				username = Base64.getEncoder()
						.encodeToString(rsaClass.encrypt(username, publicKey));
				
				System.out.print("Enter new password: ");
				password = userInputStr.nextLine();
				
				while(password.length() < 6)
				{
					
					System.out.println("Password needs to be at least 6 characters!");
					System.out.println();
					System.out.print("Enter new password: ");
					password = userInputStr.nextLine();
					
				}
				
				password = Base64.getEncoder()
						.encodeToString(rsaClass.encrypt(password, publicKey));
				
				String combo = "\n" + username + "," + password;
				
				Files.write(Paths.get(filePath), combo.getBytes(), StandardOpenOption.APPEND);
				
				System.out.println();
				System.out.println("Login Added!");
				
			}
			else
			{
				System.out.println("Invalid Response!");
			}
			
		} 
		catch (Exception e)
		{
		}
	}
	
	public static boolean verifyLogin(String username, String password, String filepath, String privateKey)
	{
		boolean found = false;
		
		String tempUsername = "";
		String tempPassword = "";
		
		String decryptUsername = "";
		String decryptPassword = "";
		
		try 
		{
			Scanner fileScan = new Scanner(new File(filepath));
			fileScan.useDelimiter("[,\n]");
			
			while(fileScan.hasNext() && !found)
			{
				tempUsername = fileScan.next();
				decryptUsername = rsaClass.decrypt(tempUsername, privateKey);
				tempPassword = fileScan.next();
				decryptPassword = rsaClass.decrypt(tempPassword, privateKey);
				
				
				if(decryptUsername.trim().equals(username.trim()) && decryptPassword.trim().equals(password.trim()))
				{
					found = true;
				}
			}
			
		}
		catch(Exception e)
		{
			
		}
		
		return found;
	}

}
