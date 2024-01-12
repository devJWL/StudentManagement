package Resources;

public class Subject {
    private String subjectName;
    private boolean isMandatory;

    public Subject(String subjectName, boolean isMandatory) {
        this.subjectName = subjectName;
        this.isMandatory = isMandatory;
    }

    public String getSubjectName() { return subjectName; }

    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public boolean isMandatory() { return isMandatory; }

    public void setMandatory(boolean mandatory) { isMandatory = mandatory; }
}
