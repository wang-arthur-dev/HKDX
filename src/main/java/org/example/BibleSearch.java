package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

public class BibleSearch {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        Scanner scanner = new Scanner(System.in);

        // 검색할 내용 입력 받기
        System.out.print("성경의 권: ");
        String Bibletitle = scanner.nextLine();
        System.out.print("성경의 장: ");
        String Biblechapter = scanner.nextLine();
        System.out.print("성경의 절: ");
        int Bibledetail = scanner.nextInt(); // nextInt()로 절 번호 입력 받기

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.holybible.or.kr/");
        WebElement searchBox = driver.findElement(By.name("QR")); // 입력창 찾기
        searchBox.sendKeys(Bibletitle + Biblechapter + Bibledetail); // 입력창에 텍스트 입력

        WebElement searchButton = driver.findElement(By.xpath("//input[@type='submit']")); // 검색 버튼 찾기
        searchButton.click();

        List<WebElement> verses = driver.findElements(By.cssSelector("li")); // li 요소 찾기

        // 성경의 절 번호에 맞는 li 요소 찾기
        if (Bibledetail <= verses.size() && Bibledetail > 0) {
            WebElement verseElement = verses.get(Bibledetail - 1); // 절 번호에 해당하는 요소 선택 (인덱스는 0부터 시작)
            System.out.println("찾은 성경 절: " + verseElement.getText()); // 해당 절 내용 출력
        } else {
            System.out.println("유효하지 않은 절 번호입니다.");
        }

        try {
            Thread.sleep(5000);  // 5000ms = 5초
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
