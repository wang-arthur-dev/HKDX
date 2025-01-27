package org.example;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public static void main(String[] args) {
        // 프레임 생성
        JFrame frame = new JFrame("교회 ppt 제작 어시");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        // 타이틀 라벨
        JLabel titleLabel = new JLabel("원하는 기능을 선택하세요", SwingConstants.CENTER);
        frame.add(titleLabel);

        // 성경 검색 버튼
        JButton bibleSearchButton = new JButton("성경 구절 크롤링");
        bibleSearchButton.addActionListener(e -> new BibleSearchGUI());
        frame.add(bibleSearchButton);

        // Selenium 테스트 버튼
        JButton RRButton = new JButton("교독문 ppt 다운로드");
        RRButton.addActionListener(e -> new ResponsiveReading());
        frame.add(RRButton);

        // 프레임 표시
        frame.setVisible(true);
    }
}
