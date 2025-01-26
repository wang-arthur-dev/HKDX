package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class BibleSearch {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        Scanner scanner = new Scanner(System.in);

        // 검색할 내용 입력 받기
        System.out.print("성경의 권: ");
        String Bibletitle = scanner.nextLine();
        System.out.print("성경의 장: ");
        String Biblechapter = scanner.nextLine();

        // 절 번호 여러 개 입력 받기
        System.out.print("성경의 절 (여러 개 입력 시 공백으로 구분): ");
        String[] detailInputs = scanner.nextLine().split(" "); // 공백을 기준으로 나누기
        int[] Bibledetail = new int[detailInputs.length];

        for (int i = 0; i < detailInputs.length; i++) {
            Bibledetail[i] = Integer.parseInt(detailInputs[i]); // 문자열을 숫자로 변환
        }

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.holybible.or.kr/");
        WebElement searchBox = driver.findElement(By.name("QR")); // 검색창 찾기
        searchBox.sendKeys(Bibletitle + Biblechapter); // 장(chapter)까지 입력

        WebElement searchButton = driver.findElement(By.xpath("//input[@type='submit']")); // 검색 버튼 찾기
        searchButton.click();

        List<WebElement> verses = driver.findElements(By.cssSelector("li")); // 성경 절 목록 가져오기

        System.out.println("\n🔍 검색 결과:");
        for (int detail : Bibledetail) {
            if (detail <= verses.size() && detail > 0) {
                WebElement verseElement = verses.get(detail - 1); // 절 번호에 해당하는 요소 선택 (인덱스는 0부터 시작)
                System.out.println("[" + Bibletitle + " " + Biblechapter + ":" + detail + "] " + verseElement.getText()); // 결과 출력
            } else {
                System.out.println("유효하지 않은 절 번호: " + detail);
            }
        }

        try {
            Thread.sleep(5000);  // 5초 대기 후 종료
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
