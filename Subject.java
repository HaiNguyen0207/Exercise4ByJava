package Exercise;

public class Subject {
    private static int nextId = 1001;
    private String id; //id subjetc
    private String name;    // name subject
    private int credit; //credit subject
    private int numOfLesson;    // numer of lesson
    private int numOfExam;    //number of exam

    public Subject() {
        id = "";
        name = "";
        credit = 0;
        numOfLesson = 0;
        numOfExam = 0;
    }
    //consuct

    public Subject(String id) {
        this.id = id;
    }

    public Subject(String id, String name) {
        this(id);
        this.name = name;
    }

    public Subject(String id, String name, int credit) {
        this(id, name);
        this.credit = credit;
    }

    public Subject(String id, String name, int credit, int numOfLesson) {
        this(id, name, credit);
        this.numOfLesson = numOfLesson;
    }

    //getter and setter
    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Subject.nextId = nextId;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        if (id == null) {//id is null, it needs to be created
            this.id = "SUB" + nextId;
            nextId++;
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getNumOfLesson() {
        return numOfLesson;
    }

    public void setNumOfLesson(int numOfLesson) {
        this.numOfLesson = numOfLesson;
    }

    public int getNumOfExam() {
        return numOfExam;
    }

    public void setNumOfExam(int numOfExam) {
        this.numOfExam = numOfExam;
    }

    public Subject(String id, String name, int credit, int numOfLesson, int numOfExam) {
        this(id, name, credit, numOfLesson);
        this.numOfExam = numOfExam;
    }

}
