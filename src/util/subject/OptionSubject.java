package util.subject;

import java.util.ArrayList;
import java.util.List;

public enum OptionSubject {
    SELECT_SUBJECT_ERROR("Error"),
    DESIGN_PATTERN("Design Pattern"),
    SPRING_SECURITY("Spring Security"),
    REDIS("Redis"),
    MONGODB("MongoDB");

    private final String subjectName;
    private static final OptionSubject[] optionSubjects = OptionSubject.values();
    private static final List<String> optionSubjectStringList = new ArrayList<>();
    static {
        for (OptionSubject optionSubject : optionSubjects) {
            optionSubjectStringList.add(optionSubject.getSubjectName());
        }
    }
    OptionSubject(String subjectName) {
        this.subjectName = subjectName;
    }


    public String getSubjectName() {
        return subjectName;
    }
    public static OptionSubject get(int index) {
        return optionSubjects[index];
    }
    public static List<String> getOptionSubjectStringList() {
        return optionSubjectStringList;
    }
}
