import java.util.*;

abstract class Question {
    protected String text;
    protected int points;

    public Question(String text, int points) {
        this.text = text;
        this.points = points;
    }

    public abstract boolean evaluate(String answer);

    public int getPoints() {
        return points;
    }
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(
            String text,
            int points,
            String correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(
            String text,
            int points,
            boolean correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    public ShortAnswerQuestion(
            String text,
            int points,
            String correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String name;
    private ArrayList<Question> questions;

    public Examination(String name) {
        this.name = name;
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }
}

class Attempt {
    private Student student;
    private Examination exam;
    private ArrayList<String> answers;
    private boolean submitted;

    public Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
        answers = new ArrayList<>();
        submitted = false;
    }

    public void start() {
        System.out.println(
            exam.getName() +
            " started by " +
            student.getName() + ".");
    }

    public void answer(int questionNumber, String answer) {
        if (submitted) {
            System.out.println(
                "Cannot change answers for a submitted examination.");
            return;
        }

        while (answers.size() < questionNumber) {
            answers.add("");
        }

        answers.set(questionNumber - 1, answer);

        System.out.println(
            "Answer recorded for Question " +
            questionNumber + ".");
    }

    public void submit() {
        if (submitted) {
            return;
        }

        submitted = true;

        System.out.println(
            exam.getName() +
            " submitted by " +
            student.getName() + ".");

        int total = 0;
        int maximum = 0;

        for (int i = 0; i < exam.getQuestions().size(); i++) {
            Question q = exam.getQuestions().get(i);

            maximum += q.getPoints();

            boolean correct = q.evaluate(answers.get(i));

            if (correct) {
                total += q.getPoints();

                System.out.println(
                    "Result: Question " +
                    (i + 1) +
                    ": Correct (" +
                    q.getPoints() +
                    " points)");
            } else {
                System.out.println(
                    "Result: Question " +
                    (i + 1) +
                    ": Incorrect (0 points)");
            }
        }

        System.out.println(
            "Total score: " + total +
            "/" + maximum + ".");
    }
}

public class OnlineExam {
    public static void main(String[] args) {
        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(
            new MultipleChoiceQuestion(
                "Which is a programming language?",
                5,
                "C"
            )
        );

        exam.addQuestion(
            new TrueFalseQuestion(
                "Java is a scripting language.",
                5,
                false
            )
        );

        Attempt attempt = new Attempt(student, exam);

        attempt.start();

        attempt.answer(1, "C");
        attempt.answer(2, "true");

        attempt.submit();

        attempt.answer(1, "A");
    }
}