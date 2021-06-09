package framework;

import java.util.InputMismatchException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import util.Application;
import util.Browser;
public class Driver extends Application{
	 String winner = null;
		//Creating a driver object referencing WebDriver interface
   static WebDriver driver;
    static String[] board=new String[9];
    static String turn = "X";
    

    
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		driver=Application.GetDriver(Browser.EDGE,System.getProperty("user.dir") + "\\html\\index.html");
	        String winner = null;
	        while (winner == null) {
	        	int numInput;
	            
	            // Exception handling.
	            // numInput will take input from user like from 1 to 9.
	            // If it is not in range from 1 to 9.
	            // then it will show you an error "Invalid input."
	        	try {
	                numInput = Application.getRandomNumber();
	                if (!(numInput >= 0 && numInput <= 8)) {
	                    System.out.println(
	                        "Invalid input; re-enter slot number:");
	                    continue;
	                }
	            }
	            catch (InputMismatchException e) {
	                System.out.println(
	                    "Invalid input; re-enter slot number:");
	                continue;
	            }
	        	//System.out.println("numInput " +numInput);
	        	//System.out.println(driver.getTitle());
	        	driver.findElement(By.xpath("//div[@data-cell-index= '" + numInput + "']")).click();
	        	 winner = Application.checkWinner(driver);
	        }
	        if (winner.equalsIgnoreCase("draw")) {
	            System.out.println(
	                "It's a draw! Thanks for playing.");
	        }
	       
	        // For winner -to display Congratulations! message.
	        else {
	            System.out.println(
	                "Congratulations! " + winner
	                + "'s have won! Thanks for playing.");
	        }
	    
	       
		}

}
