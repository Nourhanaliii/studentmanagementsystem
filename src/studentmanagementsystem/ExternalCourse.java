package studentmanagementsystem;

// Adapter Pattern: External Course Integration
class ExternalCourse {
    private final String courseDetails;

    public ExternalCourse(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    public String getCourseDetails() {
        return courseDetails;
    }
}

class ExternalCourseAdapter extends Course {
    private final ExternalCourse externalCourse;

    public ExternalCourseAdapter(ExternalCourse externalCourse) {
        this.externalCourse = externalCourse;
    }

    @Override
    public String getType() {
        return "External: " + externalCourse.getCourseDetails();
    }
}