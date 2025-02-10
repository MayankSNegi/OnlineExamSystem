package onlineexamsystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Online Exam System");

        // User Authentication
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (AuthenticationService.authenticate(username, password)) {
            System.out.println("Login successful! Starting the exam...");
            // Exam setup and timer
            Exam exam = new OnlineExam();
            Timer timer = new Timer(exam);
            timer.start();
            // Start the exam
            exam.startExam();
            timer.interrupt();
            // Display ScoreCard
            ScoreCard scoreCard = new ScoreCard(exam);
            scoreCard.displayScore();
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }

        scanner.close();
    }
}
