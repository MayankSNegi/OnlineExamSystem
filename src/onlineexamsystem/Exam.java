package onlineexamsystem;

import java.util.ArrayList;
import java.util.List;

public abstract class Exam implements ExamInterface {
    protected List<Question> questions;
    protected List<Question> incorrectQuestions;
    protected int score = 0;
    public static final int TIME_LIMIT = 60;
    protected boolean isExamCompleted = false;

    public Exam() {
        questions = new ArrayList<>();
        incorrectQuestions = new ArrayList<>();
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public void markExamCompleted() {
        this.isExamCompleted = true;
    }

    public boolean isExamCompleted() {
        return isExamCompleted;
    }

    public List<Question> getBookmarkedQuestions() {
        return new ArrayList<>();
    }

    public List<Question> getIncorrectQuestions() {
        return incorrectQuestions;
    }
}
