package studentmanagementsystem;

import java.util.HashMap;
import java.util.Map;

// Singleton: Grade Processing System
class GradeProcessingSystem {
    private static GradeProcessingSystem instance;
    private final Map<String, String> studentGrades = new HashMap<>();

    private GradeProcessingSystem() {}

    public static synchronized GradeProcessingSystem getInstance() {
        if (instance == null) {
            instance = new GradeProcessingSystem();
        }
        return instance;
    }

    public void assignGrade(String studentName, String grade) {
        studentGrades.put(studentName, grade);
    }

    public String getGrade(String studentName) {
        return studentGrades.getOrDefault(studentName, "No grade assigned");
    }
}