package onlineexamsystem;

import java.util.List;

public interface ExamInterface {
    void startExam();
    void markExamCompleted();
    boolean isExamCompleted();
    int getScore();
    List<Question> getBookmarkedQuestions();
    List<Question> getIncorrectQuestions();
    int getTotalQuestions();
}