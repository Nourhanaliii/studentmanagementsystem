package studentmanagementsystem;

// Builder Pattern: Student Profile
class StudentProfile {
    private final String name;
    private final String courseName;
    private final String courseType;
    private final Student student;

    private StudentProfile(Builder builder) {
        this.name = builder.name;
        this.courseName = builder.courseName;
        this.courseType = builder.courseType;
        this.student = builder.student;
    }

    @Override
    public String toString() {
        return "Registered: \n" +
               "Student Name: " + name + "\n" +
               "Type: " + student.getType() + "\n" +
               "Course: " + courseName + " (" + courseType + ")";
    }

    public static class Builder {
        private String name;
        private String courseName;
        private String courseType;
        private Student student;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public Builder setCourseType(String courseType) {
            this.courseType = courseType;
            return this;
        }

        public Builder setType(String type) {
            this.student = StudentFactory.createStudent(type);
            return this;
        }

        public StudentProfile build() {
            return new StudentProfile(this);
        }
    }
}