package WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;



public class JenkinsTesting {
	private final static String base_url = "http://localhost:8081";
	private final static String Manage_Jenkins = "/manage";
	private final static String Manage_User = "/securityRealm";
	private final static String Creat_User = "/addUser";

	private final static String LOGIN = "someuser";
	private final static String PASSWORD = "somepassword";

	WebDriver driver = null;
	PageObject page = null;

	@BeforeClass
	public void beforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
		driver = new ChromeDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(base_url);
		if(!driver.getTitle().equals("Jenkins")){
			Assert.fail("Page ["+base_url+"] is wrong.");
		}
		page = PageFactory.initElements(driver, PageObject.class);
		page.login(LOGIN,PASSWORD);
	}

	@AfterClass
	public void afterClass() {
		driver.get(base_url);
		page.logout();
		if(!driver.getTitle().equals("Jenkins")){
			Assert.fail("Can`t logOut ["+base_url+"].");
		}
		driver.close();
		driver.quit();
	}

	@Test
	public void manageJenkinsTest(){
		driver.get(base_url);
		Assert.assertTrue(page.clickManageJenkins(),"Can`t click button with text [Manage Jenkins]:");
		Assert.assertTrue(page.isThereManageUserdt(),"Can`t find dt element with text [Manage User]:");
		Assert.assertTrue(page.isThereCreateJenkinsdd(),"Can`t find dd element with text [Create/delete/modify users that can log in to this Jenkins]:");
	}

	@Test
	public void manageUserTest(){
		driver.get(base_url+Manage_Jenkins);
		Assert.assertTrue(page.clickManageUser(),"Can`t click link with text [Manage User]:");
		Assert.assertTrue(page.isThereCreateUserlink(),"Can`t find link element with text [Create User]:");
	}

	@Test
	public void checkFieldCreateUserTest(){
		driver.get(base_url + Manage_User);
		Assert.assertTrue(page.clickCreateUser(),"Can`t click link with text [Create User]:");
		Assert.assertTrue(page.isThereFieldCreateUser(),"Can`t find input elements to create user:");
		Assert.assertTrue(page.isFieldCreateUserEmpty(),"The input elements to create user isn`t empty:");
	}

	@Test
	public void createUserTest(){
		driver.get(base_url+Manage_User+Create_User);
		page.inputDataToCreateUser();
		Assert.assertTrue(page.clickCreateNewUser(),"Can`t find button with text [Create User]:");
		Assert.assertTrue(page.isThereNewUser(),"New [Someuser] isn`t create:");
		Assert.assertTrue(page.clickLinkDelete(),"Can`t click link:");
		Assert.assertTrue(page.clickButtonDeleteUser(),"Can`t find button with text [Yes]:");
	}

	@Test
	public void checkDeleteUserTest(){
		driver.get(base_url+Manage_User+Create_User);
		page.inputDataToCreateUser();
		Assert.assertTrue(page.clickCreateNewUser(),"Can`t find button with text [Create User]:");
		Assert.assertTrue(page.clickLinkDelete(),"Can`t click link:");
		Assert.assertTrue(page.isThereDeleteUserText(),"Can`t find form with text [re you sure about deleting the user from Jenkins?]:");
		Assert.assertTrue(page.clickLinkDelete(),"Can`t click link:");
		Assert.assertTrue(page.clickButtonDeleteUser(),"Can`t find button with text [Yes]:");
	}

	@Test()
	public void isDeleteUserTest(){
		driver.get(base_url+Manage_User+Create_User);
		page.inputDataToCreateUser();
		Assert.assertTrue(page.clickCreateNewUser(),"Can`t find button with text [Create User]:");
		Assert.assertTrue(page.clickLinkDelete(),"Can`t click link:");
		Assert.assertTrue(page.clickButtonDeleteUser(),"Can`t find button with text [Yes]:");
		Assert.assertFalse(page.isThereNewUser(),"Find New [Someuser]:");
		Assert.assertFalse(page.clickLinkDelete(),"Find click link:");
		Assert.assertFalse(page.isThereLinkDeleteAdmin(),"Find link with text [user/admin/delete]");
	}



	@Test
	public void checkButtonsColorTest(){
		driver.get(base_url+Manage_User+Create_User);
		page.inputDataToCreateUser();
		Assert.assertTrue(page.checkCreateUsereButtonColor(),"Button [Create User] have not [#4b758b] color:");
		Assert.assertTrue(page.clickCreateNewUser(),"Can`t find button with text [Create User]:");
		Assert.assertTrue(page.clickLinkDelete(),"Can`t click link:");
		Assert.assertTrue(page.checkDeleteUsereButtonColor(),"Button [Delete User] have not [#4b758b] color:");
		Assert.assertTrue(page.clickButtonDeleteUser(),"Can`t find button with text [Yes]:");
	}

	@Test
	public void EmptyUserTest(){
		driver.get(base_url+Manage_User+Create_User);
		Assert.assertTrue(page.clickCreateNewUser(),"Can`t find button with text [Create User]:");
		Assert.assertTrue(page.isThereDivError(),"Can`t find div element with text [\"\" is prohibited as a full name for security reasons.]:");
	}

	@Test
	public void autoRefreshTest(){
		driver.get(base_url);
		Assert.assertTrue(page.enableToDisable(),"Can`t change refresh [from Enable to Disable]");
		Assert.assertTrue(page.disableToEnable(),"Can`t change refresh [from Disable to Enable]");
	}

}
