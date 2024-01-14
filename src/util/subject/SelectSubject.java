package util.subject;

public enum SelectSubject {
    SELECT_SUBJECT_ERROR("ERROR"),
    DESIGN_PATTERN("Design Pattern"),
    SPRING_SECURITY("Spring Security"),
    REDIS("Redis"),
    MONGODB("MongoDB");

    private final String subjectName;
    private static final SelectSubject[] selectSubjects = SelectSubject.values();

    SelectSubject(String subjectName) {
        this.subjectName = subjectName;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public static SelectSubject get(int index) {
        return selectSubjects[index];
    }
}
