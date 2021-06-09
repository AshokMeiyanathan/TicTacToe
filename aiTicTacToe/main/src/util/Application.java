package util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Application {
	
	static String[] board=new String[9];
	//protected static WebDriver driver;
	static String turn = "X";

	public static WebDriver GetDriver(Browser chrome,String url) throws Exception {

		WebDriver driver = null;
		try {
			switch (chrome) {

			case FIREFOX:

				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions fireFoxOptions = new FirefoxOptions().setProfile(new FirefoxProfile());
				// fireFoxOptions.setBinary(configData.get("FirefoxBinary"));
				driver = new FirefoxDriver(fireFoxOptions);

				break;

			case CHROME:

				String ssysP = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", ssysP);

				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("disable-infobars");
				chromeOptions.addArguments("disable-extensions");
				chromeOptions.setExperimentalOption("useAutomationExtension", false);

				driver = new ChromeDriver(chromeOptions);

				break;

			case EDGE:
				
				// Set the driver path
				ssysP = System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe";
				System.setProperty("webdriver.edge.driver", ssysP);
				/*// Start Edge Session
				driver = new EdgeDriver();*/

				//WebDriverManager.edgedriver().setup();

				EdgeOptions Edgeoptions = new EdgeOptions();

				driver = new EdgeDriver(Edgeoptions);

				break;

			default:
				break;

			}
		} catch (Exception e) {
			throw e;
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		return driver;


	}
	
	public static int getRandomNumber(){
		Random r = new Random();
		int num=r.nextInt((9 - 0) + 1) + 0;
        System.out.println("Entered Number : "+num);
        return num;
	}
	
	public static String checkWinner(WebDriver driver)
    { for (int a = 0; a < 8; a++) {
        String line = null;

        switch (a) {
        case 0:
        	board[0]=driver.findElement(By.xpath("//div[@data-cell-index= '0']")).getText();
        	board[1]=driver.findElement(By.xpath("//div[@data-cell-index= '1']")).getText();
        	board[2]=driver.findElement(By.xpath("//div[@data-cell-index= '2']")).getText();
        	line = board[0] + board[1] + board[2];
            break;
        case 1:
        	board[3]=driver.findElement(By.xpath("//div[@data-cell-index= '3']")).getText();
        	board[4]=driver.findElement(By.xpath("//div[@data-cell-index= '4']")).getText();
        	board[5]=driver.findElement(By.xpath("//div[@data-cell-index= '5']")).getText();
            line = board[3] + board[4] + board[5];
            break;
        case 2:
        	board[6]=driver.findElement(By.xpath("//div[@data-cell-index= '6']")).getText();
        	board[7]=driver.findElement(By.xpath("//div[@data-cell-index= '7']")).getText();
        	board[8]=driver.findElement(By.xpath("//div[@data-cell-index= '8']")).getText();
            line = board[6] + board[7] + board[8];
            break;
        case 3:
        	board[0]=driver.findElement(By.xpath("//div[@data-cell-index= '0']")).getText();
        	board[3]=driver.findElement(By.xpath("//div[@data-cell-index= '3']")).getText();
        	board[6]=driver.findElement(By.xpath("//div[@data-cell-index= '6']")).getText();
            line = board[0] + board[3] + board[6];
            break;
        case 4:
        	board[1]=driver.findElement(By.xpath("//div[@data-cell-index= '1']")).getText();
        	board[4]=driver.findElement(By.xpath("//div[@data-cell-index= '4']")).getText();
        	board[7]=driver.findElement(By.xpath("//div[@data-cell-index= '7']")).getText();
            line = board[1] + board[4] + board[7];
            break;
        case 5:
        	board[2]=driver.findElement(By.xpath("//div[@data-cell-index= '2']")).getText();
        	board[5]=driver.findElement(By.xpath("//div[@data-cell-index= '5']")).getText();
        	board[8]=driver.findElement(By.xpath("//div[@data-cell-index= '8']")).getText();
            line = board[2] + board[5] + board[8];
            break;
        case 6:
        	board[0]=driver.findElement(By.xpath("//div[@data-cell-index= '0']")).getText();
        	board[4]=driver.findElement(By.xpath("//div[@data-cell-index= '4']")).getText();
        	board[8]=driver.findElement(By.xpath("//div[@data-cell-index= '8']")).getText();
            line = board[0] + board[4] + board[8];
            break;
        case 7:
        	board[2]=driver.findElement(By.xpath("//div[@data-cell-index= '2']")).getText();
        	board[4]=driver.findElement(By.xpath("//div[@data-cell-index= '4']")).getText();
        	board[6]=driver.findElement(By.xpath("//div[@data-cell-index= '6']")).getText();
            line = board[2] + board[4] + board[6];
            break;
        }
        
        System.out.println("a is : "+a);
        //For X winner
        if (line.equals("XXX")) {
            return "X";
        }
         
        // For O winner
        else if (line.equals("OOO")) {
            return "O";
        }
        else if (a==8) {
            return "draw";
        }
    }
    System.out.println(
        turn + "'s turn; enter a slot number to place "
        + turn + " in:");
    return null;
    }
}
