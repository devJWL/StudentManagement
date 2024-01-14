package resources;


public class SubjectScore {
    private int score;
    private char grade;

    public SubjectScore(int score, boolean isMandatory) {
        this.score = score;
        this.grade = calcGrade(score, isMandatory);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) { this.grade = grade; }

    private char calcGrade(int score, boolean isMandatory) {
        char grade = 'a';
        if (isMandatory) {
            if (score >= 95) {
                grade = 'A';
            }
            else if (score >= 90){
                grade = 'B';
            }
            else if (score >= 80) {
                grade = 'C';
            }
            else if (score >= 70) {
                grade = 'D';
            }
            else if (score >= 60) {
                grade = 'F';
            }
            else {
                grade = 'N';
            }
        }
        else {
            if (score >= 90) {
                grade = 'A';
            }
            else if (score >= 80){
                grade = 'B';
            }
            else if (score >= 70) {
                grade = 'C';
            }
            else if (score >= 60) {
                grade = 'D';
            }
            else if (score >= 50) {
                grade = 'F';
            }
            else {
                grade = 'N';
            }
        }
        return grade;
    }
}
