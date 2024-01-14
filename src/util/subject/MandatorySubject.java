package util.subject;

public enum MandatorySubject {
    MANDATORY_SUBJECT_ERROR("Error"),
    JAVA("Java"),
    OOP("OOP"),
    SPRING("Spring"),
    JPA("JPA"),
    MYSQL("MySQL");

    private final String subjectName;
    private static final MandatorySubject[] mandatorySubjects = MandatorySubject.values();

    MandatorySubject(String subjectName) {
        this.subjectName = subjectName;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public static MandatorySubject get(int index) {
        return mandatorySubjects[index];
    }
}
