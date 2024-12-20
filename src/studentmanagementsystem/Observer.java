package studentmanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Observer Interface
interface Observer {
    void update(String message);
}

// Subject Abstract Class
abstract class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// Observer Implementation: Logs course registrations
class CourseRegistrationLogger implements Observer {
    private final Map<String, String> studentRecords = new HashMap<>();

    @Override
    public void update(String message) {
        // Extract the student name and course details from the message
        String[] parts = message.split(" -> ");
        if (parts.length == 2) {
            String studentName = parts[0].replace("New Registration: ", "").trim();
            String courseDetails = parts[1].trim();
            studentRecords.put(studentName, courseDetails);
        }
    }

    public Map<String, String> getStudentRecords() {
        return new HashMap<>(studentRecords);
    }
}

