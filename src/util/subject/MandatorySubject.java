package util.subject;

import util.Util;
import util.options.StudentStatus;

import java.util.ArrayList;
import java.util.List;

public enum MandatorySubject {
    MANDATORY_SUBJECT_ERROR("Error"),
    JAVA("Java"),
    OOP("OOP"),
    SPRING("Spring"),
    JPA("JPA"),
    MYSQL("MySQL");

    private final String subjectName;
    private static final MandatorySubject[] mandatorySubjects = MandatorySubject.values();
    private static final List<String> mandatorySubjectStringList = new ArrayList<>();
    static {
        for (MandatorySubject mandatorySubject: mandatorySubjects) {
            mandatorySubjectStringList.add(mandatorySubject.getSubjectName());
        }
    }
    MandatorySubject(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }
    public static MandatorySubject get(int index) {
        return mandatorySubjects[index];
    }
    public static List<String> getMandatorySubjectStringList() {
        return mandatorySubjectStringList;
    }
}
