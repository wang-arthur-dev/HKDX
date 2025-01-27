public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { // 올바른 입력이 들어올 때까지 반복
            System.out.println("원하는 기능을 선택하세요:");
            System.out.println("1. 성경 검색");
            System.out.println("2. Selenium 테스트");
            System.out.print("번호 입력: ");

            String choice = scanner.nextLine();

            switch (choice) {
