package org.example;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public static void main(String[] args) {
        // 프레임 생성
        JFrame frame = new JFrame("성경 & Selenium 검색기");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        // 타이틀 라벨
        JLabel titleLabel = new JLabel("원하는 기능을 선택하세요", SwingConstants.CENTER);
        frame.add(titleLabel);

        // 성경 검색 버튼
        JButton bibleSearchButton = new JButton("성경 검색");
        bibleSearchButton.addActionListener(e -> BibleSearch.main(new String[]{}));
        frame.add(bibleSearchButton);

        // Selenium 테스트 버튼
        JButton seleniumTestButton = new JButton("Selenium 테스트");
        seleniumTestButton.addActionListener(e -> ResponsiveReading.main(new String[]{}));
        frame.add(seleniumTestButton);

        // 프레임 표시
        frame.setVisible(true);
    }
}
