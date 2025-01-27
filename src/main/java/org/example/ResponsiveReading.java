package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.awt.*;

public class ResponsiveReading {
    private JFrame frame;
    private JTextField searchField;
    private JTextArea resultArea;

    public ResponsiveReading() {
        frame = new JFrame("🔍 교독문 ppt 다운로드");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        // 검색어 입력 필드
        JPanel searchPanel = new JPanel(new BorderLayout());
        JLabel searchLabel = new JLabel("🔎 다운로드 받을 교독문을 입력하세요 예) 교독문 3장:");
        searchField = new JTextField();
        searchPanel.add(searchLabel, BorderLayout.NORTH);
        searchPanel.add(searchField, BorderLayout.CENTER);

        // 검색 버튼
        JButton searchButton = new JButton("🔍 검색 실행");
        searchButton.addActionListener(e -> search());

        // 결과 출력
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // 프레임에 추가
        frame.add(searchPanel);
        frame.add(searchButton);
        frame.add(new JScrollPane(resultArea));

        frame.setVisible(true);
    }

    private void search() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://cppt.tistory.com/");

        try {
            WebElement searchButton = driver.findElement(By.className("btn_search"));
            searchButton.sendKeys(" " + searchField.getText());

            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.RETURN).perform();

            WebElement searchResult = driver.findElement(By.id("mArticle"));
            searchResult.click();

            WebElement downloadButton = driver.findElement(By.className("imageblock"));
            downloadButton.click();

            // 5초 동안 대기 (다운로드가 실행될 시간을 확보)
            Thread.sleep(5000);

            resultArea.setText("✅ 검색 및 다운로드 버튼 클릭 완료!");
        } catch (Exception ex) {
            resultArea.setText("❌ 오류 발생: " + ex.getMessage());
        } finally {
            driver.quit();
        }
    }
}
