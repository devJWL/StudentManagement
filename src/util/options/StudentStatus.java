package util.options;

import java.util.ArrayList;
import java.util.List;

public enum StudentStatus {
    STUDENT_STATUS_ERROR("Error"),
    STUDENT_STATUS_GREEN("Green"),
    STUDENT_STATUS_YELLOW("Yellow"),
    STUDENT_STATUS_RED("Red");


    private final String status;
    private static final StudentStatus[] studentStatuses = StudentStatus.values();
    private static final List<String> statusStringList = new ArrayList<>();

    static {
        for (StudentStatus studentStatus : studentStatuses) {
            statusStringList.add(studentStatus.getStatus());
        }
    }
    StudentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public static StudentStatus get(int index) {
        return studentStatuses[index];
    }
    public static List<String> getStatusStringList() {
        return statusStringList;
    }


}
