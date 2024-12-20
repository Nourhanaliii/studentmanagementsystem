package studentmanagementsystem;


// Factory: Course
abstract class Course {
    public abstract String  getType();
}

class CoreCourse extends Course {
    @Override
    public String getType() {
        return "Core";
    }
}

class ElectiveCourse extends Course {
    @Override
    public String getType() {
        return "Elective";
    }
}

class LabCourse extends Course {
    @Override
    public String getType() {
        return "Lab";
    }
}

class CourseFactory {
    public static Course createCourse(String type) {
        return switch (type) {
            case "Core" -> new CoreCourse();
            case "Elective" -> new ElectiveCourse();
            case "Lab" -> new LabCourse();
            default -> throw new IllegalArgumentException("Invalid course type");
        };
    }
}