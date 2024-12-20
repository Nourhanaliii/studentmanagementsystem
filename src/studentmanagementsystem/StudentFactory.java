package studentmanagementsystem;

// Factory: Student
abstract class Student {
    public abstract String getType();
}

class UndergraduateStudent extends Student {
    @Override
    public String getType() {
        return "Undergraduate";
    }
}

class GraduateStudent extends Student {
    @Override
    public String getType() {
        return "Graduate";
    }
}

class PartTimeStudent extends Student {
    @Override
    public String getType() {
        return "Part-time";
    }
}

class StudentFactory {
    public static Student createStudent(String type) {
        return switch (type) {
            case "Undergraduate" -> new UndergraduateStudent();
            case "Graduate" -> new GraduateStudent();
            case "Part-time" -> new PartTimeStudent();
            default -> throw new IllegalArgumentException("Invalid student type");
        };
    }
}