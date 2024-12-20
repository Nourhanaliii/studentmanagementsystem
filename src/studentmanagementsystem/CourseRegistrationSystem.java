package studentmanagementsystem;

import java.util.HashMap;
import java.util.Map;

// Singleton: Course Registration System with Observer
class CourseRegistrationSystem extends Subject {
    private static CourseRegistrationSystem instance;
    private final Map<String, String> registeredCourses = new HashMap<>();

    private CourseRegistrationSystem() {}

    public static synchronized CourseRegistrationSystem getInstance() {
        if (instance == null) {
            instance = new CourseRegistrationSystem();
        }
        return instance;
    }

    public String registerCourse(String studentName, String courseName, Course course) {
        String courseDetails = courseName + " (" + course.getType() + ")";
        registeredCourses.put(studentName, courseDetails);

        String registrationMessage = studentName + " registered for " + courseDetails;
        notifyObservers(registrationMessage);

        return registrationMessage;
    }


    public Map<String, String> getRegisteredCourses() {
        return registeredCourses;
    }
}