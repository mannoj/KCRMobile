import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import org.junit.runners.Parameterized;
//import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import sun.nio.cs.StandardCharsets;

//import com.beust.jcommander.Parameterized;

//@RunWith(Parameterized.class)
public class KPMobileAutomationTest  {

	private String baseUrl;
	private AppiumDriver wd;
	private StringBuilder urlBuilder = new StringBuilder();
	private String asinString;
	List<String> lines ;
	
	public class Element{ 
		String name;
		String xpath;
	}
	
	
	static String[] asins = new String[] {
			"B00A9HIH5O", 
			"B00ITEWYKS", 
//			"B009POEWK6", 
			"B00QPHE3UU", 
//			"B00SECZHPE", Blank Page after tapping preview button 
//			"B00T6U4CV8", Less than 10 pages in sample (7 pages in 6+)
			"B00KMGO544", 
			"B00JDMPOK2", 
//			"B00BP83RMO", 
			"B00831TRPC", 
			"B004I6DD8I", 
			"B00L9B7IKE", 
//			"B00RTY0VSG", Duplicate
			"B00VZ2YYFY", 
			"B006KY2VZ2", 
			"B00VVNU5I2", 
			"B007FG9LIE", 
			"B00W6WIZC0", 
			"B00L2GPRLI", 
			"B00J4XLILY", 
//			"B00L9B7IKE",  Duplicate
//			"B00O4FK8D6", 
			"B00X1AQ5ZA", 
//			"B007FG9LIE",  Duplicate 
			"B00RTY0VSG", 
//			"B00Q74VXMC", 
			"B00N7TDP8K", 
//			"B00U0NAMB4", 
//			"B00PG8UCH2", 
//			"B00OM9OKEC", 
			"B00N58XLLE", 
			"B00WYI2A5E", 
			"B00DPM7TIG", 
			"B00O2BKKUS", 
			"B00JO8PEN2", 
			"B00WLD27N2", 
			"B00O2BKKZS", 
			"B00FJ314YE", 
			"B00X63OPAE", 
			"B00NU69YD0"};
	
	
//	@Parameters
//	public static List<String> asinString(){
//		return Arrays.asList(asins);		
//	}
	
	private String asin;
	
//	public KPMobileAutomationTest(String Asin){
//		asin = Asin;
//	}
	
	public KPMobileAutomationTest() {
	}

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "8.3");
		capabilities.setCapability("deviceName", "iPhone 6");
		capabilities.setCapability("app", "io.appium.SafariLauncher");
		wd = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(1000);
		
//		7e39357c5a76ce472bb0e178cb1a6a317ef325c3     KCR iPhone
//		wd = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//		baseUrl = "http://amzn.to/1ONdvip";
//        baseUrl = "https://read.amazon.com/kp/kshare?beta=ipmmpm7ygpr1jszc&asin={asin}&id=XD3ezAVVQ3KP0bWxSFvosg";
        String prod = "https://read.amazon.com/kp/kshare?beta=ipmmpm7ygpr1jszc&asin={asin}";
        String official = "https://k4w-dev.aka.amazon.com/kshare/bb/official/index.jsp?asin={asin}&bb=official";
        String mainLine = "https://k4w-dev.aka.amazon.com/kshare/bb/mainline/index.jsp?asin={asin}&bb=mainline";
        String charltonBB = "https://k4w-dev.aka.amazon.com/kp/kshare?asin={asin}&mobile=true&bb=charltow";
        baseUrl = prod;
        
//        String fileName = "/Users/mtahil/Documents/workspace/KPMobileBrowserTest/top_10000.csv";
//        String fileName = "/Users/mtahil/Documents/workspace/KPMobileBrowserTest/Top10K.csv";
        String fileName = "/Users/mtahil/Documents/workspace/KPMobileBrowserTest/BlankPageASIN.csv";
//        String fileName = "/Users/mtahil/Documents/workspace/KPMobileBrowserTest/CheckImage.csv";
    	File file = new File(fileName);
//    	try{
    		lines = Files.readAllLines(file.toPath(), java.nio.charset.StandardCharsets.UTF_8);
    		System.out.println("***********************************************");
//    		List<String> lines = Files.
//    	}catch (InterruptedException e) {
//    		
//    	}
        
	}

	@After
	public void tearDown() throws Exception {
		wd.quit();
	}

//	@Test
	public void test() {
		try {
			System.out.print(new Date());
			System.out.println("***********************************************");
			for (int k = 0 ; k < 1; k ++){
				for (int j = 0 ; j <asins.length; j++){
					System.out.println(baseUrl.replace("{asin}", asins[j]));
					wd.navigate().to(baseUrl.replace("{asin}", asins[j]));
					Thread.sleep(5000);
					wd.findElementById("kp-landing-read-sample-button").click();
					Thread.sleep(2000);
//					String percent = wd.findElementById("kp-reader-status-left").getText();
//					String percent = wd.findElementById("kp-reader-status-left").getText();
//					System.out.println("*********************************" + percent);
//					Thread.sleep(5000);
					int count = 10;
					int interation = 20;
//					Timer  timer;
					for (int i = 0; i < count; i++){
//						if (i == 5)
						Thread.sleep(500);
						System.out.print(i + " ,");
//						System.out.println(new Date());
						wd.findElementById("kp-next-page").click();
					}
					Thread.sleep(1000);
					for (int i = 0; i < count; i++){
//						if (i == 5)
						Thread.sleep(250);
						System.out.print(i + ", ");
						wd.findElementById("kp-prev-page").click();			
					}
//					if (j%2 == 0){
						WebElement button = wd.findElementById("kp-endAction-buy-button"); 
						while(!button.isDisplayed()){
							System.out.print("|");
							wd.findElementById("kp-next-page").click();
							Thread.sleep(500);
						}
						Thread.sleep(500);
						System.out.println("|");
						System.out.println("--------------Tapping Buy button from End Action Page--------------Count : " + j/2);
						wd.findElementById("kp-endAction-buy-button").click();
						System.out.println("--------------Done tapping Buy from End Action--------------");
						Thread.sleep(2000);
//					}
//					else {
//						Thread.sleep(300);
//						System.out.println("-----------Tapping Buy button from Reader Page----------- Count : " + j);
//						wd.findElementById("kp-toolbar-buy-button").click();						
//						System.out.println("--------------Done tapping Buy from Reader Page--------------");
//						Thread.sleep(2000);
//					}
					Thread.sleep(2000);
					System.out.println("*");
//					Thread.sleep(1000);
					System.out.println("************************************************************K : "+k+", J:"+j);
					System.out.println(j);
				}
				System.out.println("DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE");
				System.out.println("*****************************## K ******" + k);
				System.out.println(k);
				System.out.print(new Date());
			}
//			String percent = wd.findElementById("kp-reader-status").getText();
//			System.out.println("*********************************" + percent);
			wd.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			wd.close();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			wd.quit();
		}
	}
	
//	@Test
	public void test2() {
		try {
			System.out.print(new Date());
			System.out.println("***********************************************");
			for (int j = 0 ; j <asins.length; j++){
				System.out.println(baseUrl.replace("{asin}", asins[j]));
				wd.navigate().to(baseUrl.replace("{asin}", asins[j]));
				Thread.sleep(5000);
				wd.findElementById("kp-landing-read-sample-button").click();
				Thread.sleep(2000);
				int count = 10;
				int interation = 20;
				for (int i = 0; i < count; i++){
					Thread.sleep(500);
					System.out.print(i + " ,");
					wd.findElementById("kp-next-page").click();
				}
				Thread.sleep(1000);
				for (int i = 0; i < count; i++){
					Thread.sleep(250);
					System.out.print(i + ", ");
					wd.findElementById("kp-prev-page").click();			
				}
				WebElement button = wd.findElementById("kp-endAction-buy-button"); 
				while(!button.isDisplayed()){
					System.out.print("|");
					wd.findElementById("kp-next-page").click();
					Thread.sleep(500);
				}
				Thread.sleep(500);
				System.out.println("|");
				System.out.println("--------------Tapping Buy button from End Action Page--------------Count : " + j/2);
				wd.findElementById("kp-endAction-buy-button").click();
				System.out.println("--------------Done tapping Buy from End Action--------------");
				Thread.sleep(2000);
//				System.out.println("************************************************************K : "+k+", J:"+j);
				System.out.println(j);
			}
			System.out.println("DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE-------DONE");
			System.out.print(new Date());
			wd.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			wd.close();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			wd.quit();
		}
}

	@Test
	public void testASINs() {
		try {
			System.out.print(new Date());
			
			Boolean isMobi7 = false;
			int asinCount = lines.size();
			String fileName = "ASINReport"+(new Date().toString())+".csv";
			
			FileWriter writer = new FileWriter(fileName);
			
			writer.append((new Date()).toString());
			writer.append("\n");
			
			String bookTitleXpath = "//*[@id='kp-landing-share-section']/div[2]/p[1]";
			String bookAuthorXpath = "//*[@id='kp-landing-share-section']/div[2]/p[2]";
			String book5thStarXpath = "//*[@id='kp-landing-book-rating']/span[5]";
			String bookDescriptionXpath = "//*[@id='kp-landing-share-section']/div[2]/div[1]/p";
			String bookImageXpath = "//*[@id='kp-landing-share-section']/div[1]/img";
			String bookReviewsXpath = "//*[@id='kp-landing-book-total-rating-count']";
			String readerViewXpath = "//*[@id='ReaderView']";
			
			Element title = new Element();
			Element author = new Element();
			Element star = new Element();
			Element description = new Element();
			Element image = new Element();
			Element reviews = new Element();
			Element reader = new Element();
			title.name = "bookTitle";
			title.xpath = bookTitleXpath;
			author.name = "bookAuthor";
			author.xpath = bookAuthorXpath;
			star.name = "bookStar";
			star.xpath = book5thStarXpath;
			description.name = "bookDescription";
			description.xpath = bookDescriptionXpath;
			image.name = "bookName";
			image.xpath = bookImageXpath;
			reviews.name = "reviews";
			reviews.xpath = bookReviewsXpath;
			reader.name = "readerView";
			reader.xpath = readerViewXpath;
			System.out.println("Total ASIN in sheet : " + (asinCount-1));
			writer.append("Total ASIN in input sheet : " + (asinCount-1));
			writer.append("\n");
			writer.append("Serial #");
			writer.append(',');
			writer.append("ASIN");
			writer.append(',');
			writer.append("Book Format");
			writer.append(',');
			writer.append("Title");
			writer.append(',');
			writer.append("Author");
			writer.append(',');
			writer.append("Reviews");
			writer.append(',');
			writer.append("Star Rating");
			writer.append(',');
			writer.append("Description");
			writer.append(',');
			writer.append("Image Size W X H");
			writer.append(',');
			writer.append("Blank Page");
			writer.append('\n');
//			for (int j = 1 ; j <2; j++){
			for (int j = 1 ; j <asinCount; j++){
				String bookASIN = lines.get(j);
//				String bookASIN = "B00VZ7ZDYK"; // No Stars
//				String bookASIN = "B00DPM7TIG"; // Blank Page
//				String bookASIN = "B00BP83RMO"; // Blank description
//				String bookASIN = "B00W6NWKCA"; // Too many Authors
//				String bookASIN = "B0042FZWZ2" // without Cover Image
//				String bookASIN = "B00MW2U9SW";
				String urlWithAsin = baseUrl.replace("{asin}", bookASIN);
	//			System.out.println(urlWithAsin);
				wd.navigate().to(urlWithAsin);
				// Check if book is Mobi7
				System.out.print(j+ "\t");
				writer.append(new Integer(j).toString());
				writer.append(',');
				writer.append(bookASIN);
				writer.append(',');
				String isMobie7 =checkFormat(bookASIN);
				System.out.print(isMobie7+ '\t');
				writer.append(isMobie7);
				writer.append(',');
				
				// Title
				String titleOp =checkElement(title); 
//				System.out.print(titleOp);
				System.out.print("\t");
				writer.append(titleOp);
				writer.append(',');
				
				// Check Author
				String authorOp =checkElement(author);
//				System.out.print(authorOp);
				writer.append(authorOp);
				writer.append(',');
				
				// Check Reviews
				String reviewsOp =checkElement(reviews);
				writer.append(reviewsOp);
				writer.append(',');
				
				Boolean starOp =checkElementPresence(star);
//				if(starOp == true)
//					writer.append("Start Present");
//				else
//					writer.append("Start NOT Present");
				writer.append(starOp.toString());
				System.out.print(starOp);
				
				writer.append(',');
				
				String descriptionOp =checkElement(description);
				if(descriptionOp.contains("Book Description"))
					descriptionOp = "Present";
				else
					descriptionOp = "Not Present";
				System.out.print(descriptionOp);
				writer.append(descriptionOp);
				writer.append(',');
				
				// Check Image
				String size =checkElement(image, "Size");
				writer.append(size);
				writer.append(',');
				
				
//				Dimension size = wd.findElement(By.xpath(image.xpath)).getSize();
//				System.out.println(size.toString().replace(",", " X"));
//				if (size.toString().contains("1, 1")){
//					writer.append("No Image");
//				}
//				else{
//					writer.append(size.toString().replace(",", " X"));
//				}
//				writer.append(',');
				
				// Check Blank Page
				Boolean blankPage =checkBlankPage(); 
				if(blankPage){
					if(checkElementPresence(reader)){
						writer.append(blankPage.toString());
					}
					else{
						writer.append("N/A");
					}
				}
				writer.append(',');
				

				writer.append("\n");
				System.out.println("");
			}
			writer.append("Run Finished at : ");
			writer.append(",");
			writer.append((new Date()).toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("************************ Test Complete ************************");
		System.out.print(new Date());
		wd.close();
	}
	
	public String checkElement(Element elmnt, String... str){
		try{
	
			
			WebElement element = wd.findElement(By.xpath(elmnt.xpath));
			if(element!=null ){
				if (str.length>0 && str[0] == "Size"){
					String imageSize = element.getSize().toString().replace(",", " X");
					if(imageSize.contains("(1 X 1)")){
						System.out.print("Blank Image"+ '\t');
						return "Blank Image";
					}
					else{
						System.out.print(imageSize+ '\t');
						return imageSize;
					}
				}
				else {
					System.out.print(element.getText()+ '\t');
					return element.getText().replace(",", "");
				}
			}
			else{
				System.out.print(elmnt.name + " N/A "+ '\t');
				return " N/A ";
			}
		}
		catch(NoSuchElementException e){
			System.out.print(elmnt.name + " Not found "+ '\t');
			return "Not Found";
		}
		catch(ElementNotVisibleException e){
			System.out.print(elmnt.name + " Not found "+ '\t');
			return "Not Found";
		}
		catch(Exception e){
			System.out.print(elmnt.name + " Not found "+ '\t');
			return "Not Found";
		}
	}
	
	public Boolean checkElementPresence(Element elmnt){
		try{
			WebElement element = wd.findElement(By.xpath(elmnt.xpath));
			if(element!=null){
				System.out.print(elmnt.name + " present ");
				return true;
			}
			else{
				System.out.print(elmnt.name + " not present ");
				return false;
			}
		}
		catch(NoSuchElementException e){
			System.out.print(elmnt.name + " not present ");
			return false;
		}
		catch(ElementNotVisibleException e){
			System.out.print(elmnt.name + " not present ");
			return false;
		}
		catch(Exception e){
			System.out.print(elmnt.name + " not present ");
			return false;
		}
	}
	
	public String checkFormat(String asin){

		try {
			String uri = "https://read.amazon.com/service/web/content/startReading?asin={asin}&clientVersion=10805999";
			uri = uri.replace("{asin}", asin);
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
			String inputLine;
			
            
			StringBuffer response = new StringBuffer();
			while((inputLine = in.readLine()) != null){
				response.append(inputLine);
				
			}
			
//			JSONParser parser = new JSONParser();
//			  Object obj = null;
//			try {
//				obj = parser.parse(response.toString());
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	 
//			
//			JSONObject jsonObject =  (JSONObject) obj;
//
//            String format = (String) jsonObject.get("format");
//            String srl = (String) jsonObject.get("srl").toString();
//            System.out.println("Format : "+format);
//            System.out.println("SRL : "+ srl);
//            
//            return format;
			System.out.print(asin + '\t');
			if(response.toString().contains("Mobi7"))
				return "Mobi7";
			else{
				return "Not Mobi7";
			}
		} 
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Not Mobi7";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Not Mobi7";
		}
	}

	public Boolean checkBlankPage(){
		try{			
			wd.findElementById("kp-landing-read-sample-button").click();
				Thread.sleep(1000);
				WebElement nextPageArrow = wd.findElementById("kp-next-page");
				Thread.sleep(100);
			
			if(nextPageArrow.isDisplayed()){
				System.out.print("Blank Page : NO");
//				wd.findElementById("kp-next-page").click();
				return false;
			}
			else{
				System.out.print("Blank Page : YES");
				return true;
			}
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		catch(NoSuchElementException e){
			System.out.println("Blank Page : YES");
			return true;
		}
		catch(ElementNotVisibleException e){
			System.out.println("ElementNotVisibleException : YES");
			return true;
		}
	}
}

	