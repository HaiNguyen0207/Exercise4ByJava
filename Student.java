package Exercise;

public class Student {
    private static int nextId = 1001;
    private String id;  //id student
    private FullName fullName;//fullname student
    private String address;    //address student
    private String email;// email student
    private String gender;  //gender student
    private String nameClass;   //name class
    private String major;   //major

    //consuctor
    public Student(Student student) {
        id = "";
        fullName = new FullName();
        address = "";
        email = "";
        gender = "";
        nameClass = "";
        major = "";
    }

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String fullName) {
        this.fullName = new FullName();
        this.id = id;
        setFullName(fullName);
    }

    public Student(String id, String fullName, String address) {
        this(id, fullName);
        this.address = address;
    }

    public Student(String id, String fullName, String address, String email) {
        this(id, fullName, address);
        this.email = email;
    }

    public Student(String id, String fullName, String address, String email, String gender) {
        this(id, fullName, address, email);
        this.gender = gender;
    }

    public Student(String id, String fullName, String address, String email, String gender, String nameClass) {
        this(id, fullName, address, email, gender);
        this.nameClass = nameClass;
    }

    public Student(String id, String fullName, String address, String email, String gender, String nameClass,
                   String major) {
        this(id, fullName, address, email, gender, nameClass);
        this.major = major;
    }


    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        if (id == null) {//id is null ,it needs create
            this.id = "STU" + nextId;
            nextId++;
        } else {
            getId();
        }
    }

    public String getFullName() {
        return fullName.last + " " + fullName.mid + fullName.first;
    }

    public void setFullName(String fullName) {
        var word = fullName.split(" ");
        this.fullName.last = word[0];
        this.fullName.first = word[word.length - 1];
        this.fullName.mid = "";
        for (int i = 1; i < word.length - 1; i++) {
            this.fullName.mid += word[i] + " ";
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    //class fullName
    class FullName {
        private String last;
        private String mid;
        private String first;

        public FullName() {
            last = "";
            mid = "";
            first = "";
        }

        public FullName(String last) {
            this.last = last;
        }

        public FullName(String last, String mid) {
            this(last);
            this.mid = mid;
        }

        public FullName(String last, String mid, String first) {
            this(last, mid);
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }
    }
}
