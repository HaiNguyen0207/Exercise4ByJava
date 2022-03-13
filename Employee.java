package Exercise;


public class Employee {
    private static int nextId = 1001;
    private String id;  //mã nhân viên
    private String fullName;    //họ tên
    private String address;    //địa chỉ
    private int age;   //tuổi
    private String numberPhone; //số điện thoại
    private float salary;   //mức lươngp
    private float experience;//kinh nghiệm

    public Employee() {
        id = "";
        fullName = "";
        address = "";
        age = 0;
        numberPhone = "";
        salary = 0f;
        experience = 0f;
    }

    public Employee(String id) {
        this.id = id;
    }

    public Employee(String id, String fullName) {
        this(id);
        this.fullName = fullName;
    }

    public Employee(String id, String fullName, String address) {
        this(id, fullName);
        this.address = address;
    }

    public Employee(String id, String fullName, String address, int age) {
        this(id, fullName, address);
        this.age = age;
    }

    public Employee(String id, String fullName, String address, int age, String numberPhone) {
        this(id, fullName, address, age);
        this.numberPhone = numberPhone;
    }

    public Employee(String id, String fullName, String address, int age, String numberPhone, float salary) {
        this(id, fullName, address, age, numberPhone);
        this.salary = salary;
    }

    public Employee(String id, String fullName, String address, int age, String numberPhone,
                    float salary, float experience) {
        this(id, fullName, address, age, numberPhone, salary);
        this.experience = experience;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Employee.nextId = nextId;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        if (id == null) {
            this.id = "EMP" + nextId;
            nextId++;
        } else {
            this.id=id;
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }
}
