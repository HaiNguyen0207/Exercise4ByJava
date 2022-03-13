package Exercise;

import java.util.ArrayList;

public class Course {
    private static int nextID = 1001;
    private String id;
    private String name;
    private String classRom;
    private String time;
    private Subject subject;
    //update student and transcript
    private ArrayList<TranscriptOfStudent> transcriptOfStudents = new ArrayList<>();

    public Course() {
        id = "";
        name = "";
        classRom = "";
        time = "";
        transcriptOfStudents = new ArrayList<>();

    }

    public Course(String id) {
        transcriptOfStudents = new ArrayList<>();
        this.id = id;
    }

    public Course(String id, String name) {
        this(id);
        this.name = name;
    }

    public Course(String id, String name, String classRom) {
        this(id, name);
        this.classRom = classRom;
    }

    public Course(String id, String name, String classRom, String time) {
        this(id, name, classRom);
        this.time = time;
    }

    public Course(String id, String name, String classRom, String time, Subject subject) {
        this(id, name, classRom, time);
        this.subject = subject;

    }

    //getter and setter
    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Course.nextID = nextID;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        if (id == null) {
            this.id = "COU" + nextID;
            nextID++;
        } else {
            getId();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassRom() {
        return classRom;
    }

    public void setClassRom(String classRom) {
        this.classRom = classRom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public ArrayList<TranscriptOfStudent> getTranscriptOfStudents() {
        return transcriptOfStudents;
    }

    public void setTranscriptOfStudents(Student student, Transcript transcript) {
        for (int i = 0; i < transcriptOfStudents.size(); i++) {
            if (transcriptOfStudents.get(i).getStudent().getId().compareToIgnoreCase(student.getId()) == 0) {
                transcriptOfStudents.get(i).transcript = transcript;
            }
        }
    }

    //Thêm sinh viên vào lớp
    public void addTranscriptOfStudent(Student student, Transcript transcript) {
        transcriptOfStudents.add(new TranscriptOfStudent(student, null));
    }

    class TranscriptOfStudent {
        private Student student;
        private Transcript transcript;

        public TranscriptOfStudent() {
            student = null;
            transcript = null;
        }

        public TranscriptOfStudent(Student student) {
            this.student = student;
        }

        public TranscriptOfStudent(Student student, Transcript transcript) {
            this(student);
            this.transcript = transcript;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public Transcript getTranscript() {
            return transcript;
        }

        public void setTranscript(Transcript transcript) {
            this.transcript = transcript;
        }
    }
}
