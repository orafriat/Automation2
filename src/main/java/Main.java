import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Finals finals = new Finals();
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("plz enter a username");
        String username = scanner.nextLine();
        System.out.println("plz enter a password");
        String password = scanner.nextLine();

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\or\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.aac.ac.il/");
        driver.manage().window().maximize();

        List<WebElement> elementList = driver.findElements(By.id("menu-%d7%9b%d7%9c%d7%9c%d7%99"));
        WebElement menu = elementList.get(finals.getGET_FIRST_ELEMENT());
        List<WebElement> menuItems = menu.findElements(By.tagName("li"));
        WebElement personalInfo = menuItems.get(finals.getPERSONAL_INFO_LI_TAG());
        personalInfo.click();

        waitSecond();

        WebElement userNameInput = driver.findElement(By.id("Ecom_User_ID"));
        if (userNameInput != null) {
            userNameInput.sendKeys(username);
        }

        waitSecond();

        WebElement passwordInput = driver.findElement(By.id("Ecom_Password"));
        if (passwordInput != null) {
            passwordInput.sendKeys(password);
        }

        waitSecond();

        WebElement submit = driver.findElement(By.id("wp-submit"));
        submit.click();

        waitSecond();

        List<WebElement> loginMenu = driver.findElements(By.className("row"));
        WebElement moodleRow = loginMenu.get(finals.getLOGIN_MENU_CHOSE());
        List<WebElement> moodleRowOptions = moodleRow.findElements(By.tagName("div"));
        WebElement moodleButton = moodleRowOptions.get(finals.getGET_FIRST_ELEMENT());
        moodleButton.click();

        waitSecond();

        List<WebElement> courseList = driver.findElements(By.className("multiline"));
        printCourseList(courseList);
        System.out.println("plz choose one of the courses");
        int choice = scanner.nextInt();
        WebElement chosenCourse = chosenCourse(courseList,choice);
        chosenCourse.click();

        waitSecond();

        WebElement disconnectCollapse = driver.findElement(By.id("action-menu-toggle-1"));
        disconnectCollapse.click();
        WebElement disconnectButton = driver.findElement(By.id("actionmenuaction-6"));
        disconnectButton.click();

        waitSecond();

        List<WebElement> topHeaderLine = driver.findElements(By.id("menu-top-header"));
        WebElement optionList = topHeaderLine.get(finals.getGET_FIRST_ELEMENT());
        List<WebElement> disconnectMoodle = optionList.findElements(By.tagName("li"));
        WebElement disconnect = disconnectMoodle.get(finals.getDISCONNECT_CHOSE());
        disconnect.click();

    }

    public static WebElement chosenCourse (List<WebElement> courseList, int choice) {
        WebElement chosenCourse =courseList.get(finals.getGET_FIRST_ELEMENT());
        for (int i=0; i<courseList.size();i++) {
            if (choice == i) {
                chosenCourse =  courseList.get(i);
            }
        }
        return chosenCourse;
    }

    public static void printCourseList (List<WebElement> courseList) {
        for (int i=0; i<courseList.size();i++) {
            System.out.println(i + ": " + courseList.get(i).getText());
        }
    }

    public static void waitSecond() {
        try {
            Thread.sleep(finals.getWAIT_SECOND());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}