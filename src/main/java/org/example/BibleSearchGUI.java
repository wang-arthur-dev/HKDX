package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BibleSearchGUI {
    private JFrame frame;
    private JTextField titleField, chapterField, detailField;
    private JTextArea resultArea;

    public BibleSearchGUI() {
        frame = new JFrame("성경 검색");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1));

        // 성경의 권 입력
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("📖 성경의 권 (예: 창세기)");
        titleField = new JTextField();
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(titleField, BorderLayout.CENTER);

        // 장 입력
        JPanel chapterPanel = new JPanel(new BorderLayout());
        JLabel chapterLabel = new JLabel("📜 장 (예: 1장)");
        chapterField = new JTextField();
        chapterPanel.add(chapterLabel, BorderLayout.NORTH);
        chapterPanel.add(chapterField, BorderLayout.CENTER);

        // 절 입력
        JPanel detailPanel = new JPanel(new BorderLayout());
        JLabel detailLabel = new JLabel("📌 절 번호 (여러 개 입력 시 공백으로 구분)");
        detailField = new JTextField();
        detailPanel.add(detailLabel, BorderLayout.NORTH);
        detailPanel.add(detailField, BorderLayout.CENTER);

        // 검색 버튼
        JButton searchButton = new JButton("🔍 검색");
        searchButton.addActionListener(e -> searchBible());

        // 결과 표시 영역
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // 프레임에 추가
        frame.add(titlePanel);
        frame.add(chapterPanel);
        frame.add(detailPanel);
        frame.add(searchButton);
        frame.add(new JScrollPane(resultArea));

        frame.setVisible(true);
    }

    private void searchBible() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.holybible.or.kr/");

        try {
            WebElement searchBox = driver.findElement(By.name("QR"));
            searchBox.sendKeys(titleField.getText() + chapterField.getText());

            WebElement searchButton = driver.findElement(By.xpath("//input[@type='submit']"));
            searchButton.click();

            List<WebElement> verses = driver.findElements(By.cssSelector("li"));
            String[] detailInputs = detailField.getText().split(" ");
            StringBuilder results = new StringBuilder("\n🔍 검색 결과:\n");

            for (String detail : detailInputs) {
                int verseNum = Integer.parseInt(detail);
                if (verseNum <= verses.size() && verseNum > 0) {
                    results.append("[").append(titleField.getText()).append(" ")
                            .append(chapterField.getText()).append(":").append(verseNum)
                            .append("] ").append(verses.get(verseNum - 1).getText()).append("\n");
                } else {
                    results.append("유효하지 않은 절 번호: ").append(verseNum).append("\n");
                }
            }
            resultArea.setText(results.toString());
        } catch (Exception ex) {
            resultArea.setText("오류 발생: " + ex.getMessage());
        } finally {
            driver.quit();
        }
    }
}
