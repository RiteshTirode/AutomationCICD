package riteshtirodeacademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import riteshtirodeacademy.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public 	LandingPage landingPage;

	public WebDriver initilizeDriver() throws IOException {
	Properties prop=new Properties();

	FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\riteshtirodeacademy\\resources\\globalData.properties");
	prop.load(fis);

	//It will first look for parameter passed from terminal/command prompt (using System.getProperty("browser")),  and if not found then will look for Propertie.file (using prop.getProperty("browser"))
	//mvn test -PGroups -Dbrowser=firefox    (D-> runtime parameter)
	String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
	
	//String browserName=prop.getProperty("browser");

	if(browserName.contains("chrome"))
	{
			ChromeOptions chromeOp=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) 
			{   //for running test/job in headless mode in backend without invoking browser 
				chromeOp.addArguments("headless");
			}
	        driver=new ChromeDriver(chromeOp);
	        driver.manage().window().setSize(new Dimension(1440, 900));
	}
	else if(browserName.equalsIgnoreCase("firefox")){
		
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
	
	}else {
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
	}
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver=initilizeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goToUrl();
		return landingPage;
	}
	
  public List<HashMap<String,String>> getJsonDataToMap(String fileName) throws IOException {
		
		//Read Json to String
		String jsonData=FileUtils.readFileToString(new File(fileName),StandardCharsets.UTF_8);
    
		//Convert String to List of HashMap
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>() {
		});
	   
		return data;
	
	}

    public String getScreenShot(String testCaseName) throws IOException 
    {
	  	TakesScreenshot ts=(TakesScreenshot) driver;
	  	File source=ts.getScreenshotAs(OutputType.FILE);
	  	File file=new File(System.getProperty("user.dir")+ "//reports//"+testCaseName+".png" );
	  	FileUtils.copyFile(source, file);
	  	return System.getProperty("user.dir")+ "//reports//"+testCaseName+".png";
	}
}
