package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Scanner;

import java.time.Duration;

public class SeleniumTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");


        Scanner scanner = new Scanner(System.in);

        // 검색할 내용 입력 받기
        System.out.print("교독문 00번으로 검색하세요: ");
        String searchQuery = scanner.nextLine();
        WebDriver driver = new ChromeDriver();
        driver.get("https://cppt.tistory.com/");
        WebElement searchButton = driver.findElement(By.className("btn_search"));
        // 버튼 클릭
        // 검색창에 텍스트 입력
        searchButton.sendKeys(" "+searchQuery);

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.RETURN).perform();

        WebElement searchButton2 = driver.findElement(By.id("mArticle"));
        searchButton2.click();
        WebElement downloadbutton = driver.findElement(By.className("imageblock"));
        downloadbutton.click();

        try {
            Thread.sleep(5000);  // 5000ms = 5초
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        driver.quit();


        /**
         * pull request 테스트입니다.
         */

    }
}
