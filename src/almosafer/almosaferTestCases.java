package almosafer;
import static org.testng.Assert.assertEquals;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class almosaferTestCases {
	
	
	WebDriver myDriver = new ChromeDriver();
	 String  Url = "https://www.almosafer.com/en"; 
	 String  ExpectedEngleshLanguage = "en" ;
	 String  ExpectedArabicLanguage = "ar" ;
	 String  ExpectedCrruency = "SAR";
	 String  ExpectedContactNumber="+966554400000" ;
	 Boolean ExpectedFoundQetafLogo = true;
	 String  ExpectedSelectedHotelTap = "false";
	 Random rand = new Random();
	@BeforeTest
	public void setUp() {
		myDriver.get(Url);
		myDriver.manage().window().maximize();
		myDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		WebElement website = myDriver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
			website.click();
	}
	@Test (priority = 1 , enabled = false )
	public void verificationLanguageDefault ()
	{
	           
	      WebElement tagHtml = myDriver.findElement(By.tagName("html"));
	      String ActualLanguage = tagHtml.getAttribute("lang");
	      Assert.assertEquals(ActualLanguage,ExpectedEngleshLanguage);
	      			
	}
	
	@Test(priority = 2 , enabled = false)
	public void verificationCurrencyDefault ()
	{
	           
		String ActualCrruency = myDriver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
	      Assert.assertEquals(ActualCrruency, ExpectedCrruency);
	      			
	}
	
	@Test (priority = 3 , enabled = false)
	public void verificationContactNumber ()
	{
		String ActualContactNumber = myDriver.findElement(By.tagName("strong")).getText();
		
		Assert.assertEquals(ActualContactNumber,ExpectedContactNumber);
		
	}
	
	@Test (priority = 4 , enabled = false)
	public void checkLogoQetafIsDeispled ()
	{
	    WebElement Footer = myDriver.findElement(By.tagName("footer")); 
		Boolean ActualFoundQetafLogo =Footer.findElement(By.cssSelector(".sc-fihHvN.eYrDjb")).findElement(By.tagName("svg")).isDisplayed();
		Assert.assertEquals(ActualFoundQetafLogo,ActualFoundQetafLogo);
		
	}
	
	@Test (priority = 5 , enabled = false)
	public void checkFromNotChoesesHotelTap ()
	{
		String ActualSelectedHotelTap = myDriver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
		Assert.assertEquals(ActualSelectedHotelTap,ExpectedSelectedHotelTap);
		
	}
	
	@Test (priority = 6 , enabled = false)
	public void checkFlightDeparturAndReturn ()
	{
		LocalDate today = LocalDate.now(); 
		int ExpectedDeparturDate =  today.plusDays(1).getDayOfMonth();
		int ExpectedReturnDate =  today.plusDays(2).getDayOfMonth();
		
		 String actualDeparturDate = myDriver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']" )).getText(); 		
		 String actualReturnDate = myDriver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']" )).getText(); 
		
		 int actualDeparturDateIsInt = Integer.parseInt(actualDeparturDate);
		 int actualReturnDateIsInt = Integer.parseInt(actualReturnDate);
		 Assert.assertEquals(actualDeparturDateIsInt, ExpectedDeparturDate);
		 Assert.assertEquals(actualReturnDateIsInt, ExpectedReturnDate);
		
	}
		
	@Test (priority = 7)
	public void checkChangeTheLanguageTheWebSiteRandomly ()
	{
		String Website [] = {"https://www.almosafer.com/en","https://www.almosafer.com/ar"}; 
		int Index = rand.nextInt(Website.length);
		myDriver.get(Website[Index]);
				
				if(myDriver.getCurrentUrl().contains("en")){
					
					  WebElement tagHtml = myDriver.findElement(By.tagName("html"));
				      String ActualLanguage = tagHtml.getAttribute("lang");
				      Assert.assertEquals(ActualLanguage,ExpectedEngleshLanguage);
				      
				}else if (myDriver.getCurrentUrl().contains("ar")) {
					
					  WebElement tagHtml = myDriver.findElement(By.tagName("html"));
				      String ActualLanguage = tagHtml.getAttribute("lang");
				      Assert.assertEquals(ActualLanguage,ExpectedArabicLanguage);
				}
				
				
	}
	
	@Test (priority =8)
	public void checkChangeTheSwitchHotelTapRandomly ()
	{ 
		myDriver.findElement(By.xpath("(//a[@id='uncontrolled-tab-example-tab-hotels'])[1]")).click();
		
		WebElement Locationhotel = myDriver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
				if(myDriver.getCurrentUrl().contains("en")){
					
					String EnglashCities [] = {"Riyadh","Dubai","Jeddah"}; 
					int Index= rand.nextInt(EnglashCities.length);	
				    Locationhotel.sendKeys(EnglashCities[Index]);
				      
				}else if (myDriver.getCurrentUrl().contains("ar")) {	
					
					String ArabicCities [] = {"الرياض","دبي","جده"}; 
					int Index= rand.nextInt(ArabicCities.length);
			        Locationhotel.sendKeys(ArabicCities[Index]);
				}
				
				
	}
	
	@Test (priority = 9    ) 
	public void checkChangeRandomlyForPerson ()
	{ 
		 
		
		
		myDriver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi")).click();
		
		int index = rand.nextInt(2);
		WebElement ElemntPerson = myDriver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select MySelect = new Select(ElemntPerson);
		MySelect.selectByIndex(index);
		
		myDriver.findElement(By.cssSelector(".sc-1vkdpp9-5.btwWVk")).click();
	}
	

	@Test (priority = 10   ) 
	public void checkWaitToFullyParAPI ()
	{
		
		WebDriverWait  Wait =new  WebDriverWait(myDriver,Duration.ofSeconds(30));
		
		By Resalt = By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']");
		
		WebElement Results = Wait.until(ExpectedConditions.presenceOfElementLocated(Resalt));
		
		assertEquals(Results.getText().contains("found") || Results.getText().contains("وجدنا"),true);
				
	}
	
	@Test (priority = 11  ) 
	public void checkSortLowstPrices () throws InterruptedException
	{
		WebElement ChlickLowPrices = myDriver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		ChlickLowPrices.click();
		
		Thread.sleep(2000);
		
		List <WebElement> listPrices = myDriver.findElements(By.className("Price__Value")); 
		
		String FirstPrices = listPrices.get(0).getText();
		String LastPrices = listPrices.get(listPrices.size()-1).getText();
		
		Integer   FirstPricesIsInt = Integer.parseInt(FirstPrices);
		Integer   LastPricesIsInt = Integer.parseInt(LastPrices);
		Assert.assertEquals(FirstPricesIsInt < LastPricesIsInt, true);
		
				
	}
	
	
	
	
	
}
