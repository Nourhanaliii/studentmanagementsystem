package studentmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


// GUI Application
public class StudentManagementSystem {
    static CourseRegistrationLogger logger;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(8, 2, 10, 10));

        // Initialize Singleton and Observer
        CourseRegistrationSystem registrationSystem = CourseRegistrationSystem.getInstance();
        logger = new CourseRegistrationLogger();
        registrationSystem.attach(logger);

        // GUI Components
        JLabel studentNameLabel = new JLabel("Student Name:");
        JTextField studentNameField = new JTextField();
        JLabel courseNameLabel = new JLabel("Course Name:");
        JTextField courseNameField = new JTextField();
        JLabel courseTypeLabel = new JLabel("Course Type:");
        JComboBox<String> courseTypeCombo = new JComboBox<>(new String[]{"Core", "Elective", "Lab", "External"});
        JLabel studentTypeLabel = new JLabel("Student Type:");
        JComboBox<String> studentTypeCombo = new JComboBox<>(new String[]{"Undergraduate", "Graduate", "Part-time"});
        JButton registerButton = new JButton("Register");
        JButton gradeButton = new JButton("Assign Grade");
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        JTextArea logsArea = new JTextArea(20, 40);
        logsArea.setEditable(false);
        JScrollPane logsScrollPane = new JScrollPane(logsArea);

        // Add components to frame
        frame.add(studentNameLabel);
        frame.add(studentNameField);
        frame.add(courseNameLabel);
        frame.add(courseNameField);
        frame.add(courseTypeLabel);
        frame.add(courseTypeCombo);
        frame.add(studentTypeLabel);
        frame.add(studentTypeCombo);
        frame.add(registerButton);
        frame.add(gradeButton);
        frame.add(resultLabel);
        frame.add(logsScrollPane);

        // Action Listeners
        registerButton.addActionListener(_ -> {
            String studentName = studentNameField.getText();
            String courseName = courseNameField.getText();
            String courseType = (String) courseTypeCombo.getSelectedItem();
            String studentType = (String) studentTypeCombo.getSelectedItem();

            Course course;
            if ("External".equals(courseType)) {
                ExternalCourse externalCourse = new ExternalCourse(courseName);
                course = new ExternalCourseAdapter(externalCourse);
            } else {
                assert courseType != null;
                course = CourseFactory.createCourse(courseType);
            }

            StudentProfile studentProfile = new StudentProfile.Builder()
                    .setName(studentName)
                    .setType(studentType)
                    .setCourseName(courseName)
                    .setCourseType(course.getType())
                    .build();

            String result = registrationSystem.registerCourse(studentName, courseName, course);
            resultLabel.setText("<html>" + studentProfile.toString().replace("\n", "<br>") + "</html>");

            // Update the logs
            StringBuilder records = new StringBuilder();
            updateLogger(records, logsArea);
        });

        gradeButton.addActionListener(_ -> {
            String studentName = studentNameField.getText();
            String grade = JOptionPane.showInputDialog(frame, "Enter grade for " + studentName + ":");

            GradeProcessingSystem gradeSystem = GradeProcessingSystem.getInstance();
            gradeSystem.assignGrade(studentName, grade);
            resultLabel.setText("Grade " + grade + " assigned to " + studentName);

        });

        frame.setVisible(true);
    }

    static void updateLogger(StringBuilder records, JTextArea logsArea) {
        GradeProcessingSystem gradeSystem = GradeProcessingSystem.getInstance();
        CourseRegistrationSystem registrationSystem = CourseRegistrationSystem.getInstance();

        int index = 1;
        records.setLength(0); // Clear the records buffer
        records.append("Student Records:\n");

        // Fetch registered courses and generate detailed messages
        Map<String, String> registeredStudents = registrationSystem.getRegisteredCourses();
        for (Map.Entry<String, String> entry : registeredStudents.entrySet()) {
            String studentName = entry.getKey();
            String courseDetails = entry.getValue();
            String grade = gradeSystem.getGrade(studentName);
            records.append(index++).append(" - ").append(studentName).append(" - ").append(courseDetails).append(" - Grade: ").append(grade).append("\n");
        }

        logsArea.setText(records.toString()); // Update the logs area
    }

}