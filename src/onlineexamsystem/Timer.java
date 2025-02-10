package onlineexamsystem;

public class Timer extends Thread {
    private Exam exam;

    public Timer(Exam exam) {
        this.exam = exam;
    }

    @Override
    public void run() {
        try {
            System.out.println("Timer started. You have " + Exam.TIME_LIMIT + " seconds to complete the exam.");
            Thread.sleep(Exam.TIME_LIMIT * 1000);
            if (!exam.isExamCompleted()) {
                System.out.println("\nTime's up! The exam has ended.");
            }
        } catch (InterruptedException e) {
            if (exam.isExamCompleted()) {
                System.out.println("Timer interrupted: Exam completed early.");
            } else {
                System.out.println("Timer interrupted: sleep interrupted");
            }
        }
    }
}
