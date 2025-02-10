package onlineexamsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class OnlineExam extends Exam {
    private List<Question> bookmarkedQuestions;
    private List<Question> skippedQuestions;

    public OnlineExam() {
        super();
        bookmarkedQuestions = new ArrayList<>();
        skippedQuestions = new ArrayList<>();
        // Java and OOP-related questions
        questions.add(new Question("What is the default value of a boolean variable in Java?", new String[]{"A) true", "B) false", "C) 0", "D) null"}, "B"));
        questions.add(new Question("Which keyword is used to inherit a class in Java?", new String[]{"A) inherit", "B) extends", "C) super", "D) class"}, "B"));
        questions.add(new Question("Which access modifier makes a variable accessible only within its own class?", new String[]{"A) public", "B) protected", "C) private", "D) default"}, "C"));
        questions.add(new Question("What is the root class of all classes in Java?", new String[]{"A) Root", "B) Object", "C) Class", "D) Super"}, "B"));
        questions.add(new Question("What keyword is used to prevent inheritance of a class in Java?", new String[]{"A) static", "B) abstract", "C) final", "D) void"}, "C"));
        questions.add(new Question("Which keyword is used to refer to the current instance of a class?", new String[]{"A) self", "B) super", "C) this", "D) current"}, "C"));
        questions.add(new Question("Which operator is used to allocate memory for an object?", new String[]{"A) malloc", "B) new", "C) alloc", "D) create"}, "B"));
        questions.add(new Question("What keyword is used in Java for creating an abstract method?", new String[]{"A) interface", "B) abstract", "C) void", "D) implements"}, "B"));
        questions.add(new Question("Which Java feature allows multiple methods with the same name but different parameters?", new String[]{"A) Overloading", "B) Overriding", "C) Encapsulation", "D) Inheritance"}, "A"));
        questions.add(new Question("What is the process of wrapping code and data together into a single unit?", new String[]{"A) Inheritance", "B) Polymorphism", "C) Encapsulation", "D) Abstraction"}, "C"));

    }

    @Override
    public void startExam() {
        Scanner scanner = new Scanner(System.in);
        
        Thread shuffleThread = new Thread(new ShuffleThread());
        shuffleThread.start();
        try {
            shuffleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            for (String option : question.getOptions()) {
                System.out.println(option);
            }
            System.out.print("Enter your answer (A/B/C/D) or type 'bookmark' to bookmark: ");
            String answer = scanner.nextLine().toUpperCase();
            if (answer.equals("BOOKMARK")) {
                bookmarkedQuestions.add(question);
                System.out.println("Question bookmarked!");
            } else if (answer.equals(question.getCorrectAnswer())) {
                score++;
            } else {
                incorrectQuestions.add(question);
            }
        }
        reviewBookmarkedQuestions(scanner);
        markExamCompleted();
        System.out.println("Exam completed.");
        scanner.close();
    }

    private void reviewBookmarkedQuestions(Scanner scanner) {
        if (bookmarkedQuestions.isEmpty()) {
            return;
        }

        System.out.println("\n--- Reviewing Bookmarked Questions ---");
        List<Question> reviewedQuestions = new ArrayList<>();

        for (Question question : bookmarkedQuestions) {
            System.out.println(question.getQuestionText());
            for (String option : question.getOptions()) {
                System.out.println(option);
            }

            System.out.print("Enter your answer (A/B/C/D) or type 'skip' to leave unanswered: ");
            String answer = scanner.nextLine().toUpperCase();

            if (answer.equals("SKIP")) {
                System.out.println("Skipping this question.");
                skippedQuestions.add(question);
            } else if (answer.equals(question.getCorrectAnswer())) {
                score++;
                reviewedQuestions.add(question);
            } else {
                incorrectQuestions.add(question);
                reviewedQuestions.add(question);
            }
        }
        bookmarkedQuestions.removeAll(reviewedQuestions);
    }

    @Override
    public List<Question> getBookmarkedQuestions() {
        return bookmarkedQuestions;
    }

    public List<Question> getSkippedQuestions() {
        return skippedQuestions;
    }
    private class ShuffleThread implements Runnable {
        @Override
        public void run() {
            // Shuffle the questions using Random
            Collections.shuffle(questions, new Random());
        }
    }
}
