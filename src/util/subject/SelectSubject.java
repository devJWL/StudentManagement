package util.subject;

import java.util.ArrayList;
import java.util.List;

public enum SelectSubject {
    SELECT_SUBJECT_ERROR("ERROR"),
    DESIGN_PATTERN("Design Pattern"),
    SPRING_SECURITY("Spring Security"),
    REDIS("Redis"),
    MONGODB("MongoDB");

    private final String subjectName;
    private static final SelectSubject[] selectSubjects = SelectSubject.values();
    private static final List<String> selectSubjectStringList = new ArrayList<>();
    static {
        for (SelectSubject selectSubject : selectSubjects) {
            selectSubjectStringList.add(selectSubject.getSubjectName());
        }
    }
    SelectSubject(String subjectName) {
        this.subjectName = subjectName;
    }


    public String getSubjectName() {
        return subjectName;
    }
    public static SelectSubject get(int index) {
        return selectSubjects[index];
    }
    public static List<String> getSelectSubjectStringList() {
        return selectSubjectStringList;
    }
}
