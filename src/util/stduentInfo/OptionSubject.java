package util.stduentInfo;

import java.util.ArrayList;
import java.util.List;

public enum OptionSubject {
    OPTION_SUBJECT_ERROR("Error"),
    OPTION_SUBJECT_DESIGN_PATTERN("Design Pattern"),
    OPTION_SUBJECT_SPRING_SECURITY("Spring Security"),
    OPTION_SUBJECT_REDIS("Redis"),
    OPTION_SUBJECT_MONGODB("MongoDB");

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
