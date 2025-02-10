package onlineexamsystem;

import java.util.List;

public class ScoreCard {
    private ExamInterface exam;

    public ScoreCard(ExamInterface exam) {
        this.exam = exam;
    }

    public void displayScore() {
        int totalQuestions = exam.getTotalQuestions();
        int correctAnswers = exam.getScore();
        int incorrectAnswers = totalQuestions - correctAnswers;
        double percentage = (double) correctAnswers / totalQuestions * 100;

        System.out.println("\n--- Scorecard ---");
        System.out.printf("Score: %.2f%%\n", percentage);
        System.out.println("Total Questions: " + totalQuestions);
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Incorrect Answers: " + incorrectAnswers);

        List<Question> incorrectQuestions = exam.getIncorrectQuestions();
        List<Question> skippedQuestions = ((OnlineExam) exam).getSkippedQuestions();

        if (!incorrectQuestions.isEmpty() || !skippedQuestions.isEmpty()) {
            System.out.println("\n--- Incorrect or Skipped Questions ---");
            for (Question question : incorrectQuestions) {
                displayCorrectAnswer(question);
            }
            for (Question question : skippedQuestions) {
                displayCorrectAnswer(question);
            }
        }

        List<Question> bookmarkedQuestions = exam.getBookmarkedQuestions();
        if (!bookmarkedQuestions.isEmpty()) {
            System.out.println("\n--- Bookmarked Questions ---");
            for (Question question : bookmarkedQuestions) {
                System.out.println(question.getQuestionText());
            }
        }
    }

    private void displayCorrectAnswer(Question question) {
        System.out.println(question.getQuestionText());
        String correctAnswer = question.getCorrectAnswer();
        String correctOptionText = getOptionText(question, correctAnswer);
        System.out.println("Correct Answer: " + correctAnswer + " , " + correctOptionText);
    }

    private String getOptionText(Question question, String correctAnswer) {
        String[] options = question.getOptions();
        char correctOption = correctAnswer.charAt(0);
        
        int optionIndex = correctOption - 'A';
        if (optionIndex >= 0 && optionIndex < options.length) {
            return options[optionIndex].substring(3); 
        }
        
        return "Option text not found"; // In case of an unexpected issue
    }
}
