package Exercise;

public class BankAccount {
    private static int nextId = 10001;
    private String id;  //mã nhân viên
    private String number;  //số tài khoản
    private String fullName;    //chủ tài khoản
    private String type;    //loại tài khoản
    private float surplus; //số dư
    private String nameBank;    //tên ngân hàng
    private String start;   //ngày phát hành
    private String end; //ngày hết hạn

    public BankAccount() {
        id = "";
        number = "";
        fullName = "";
        type = "";
        surplus = 0f;
        nameBank = "";
        start = "";
        end = "";
    }

    public BankAccount(String id) {
        this.id = id;
    }

    public BankAccount(String id, String number) {
        this(id);
        this.number = number;
    }

    public BankAccount(String id, String number, String fullName) {
        this(id, number);
        this.fullName = fullName;
    }

    public BankAccount(String id, String number, String fullName, String type) {
        this(id, number, fullName);
        this.type = type;
    }

    public BankAccount(String id, String number, String fullName, String type, float surplus) {
        this(id, number, fullName, type);
        this.surplus = surplus;
    }

    public BankAccount(String id, String number, String fullName, String type, float surplus, String nameBank) {
        this(id, number, fullName, type, surplus);
        this.nameBank = nameBank;
    }

    public BankAccount(String id, String number, String fullName, String type, float surplus,
                       String nameBank, String start) {
        this(id, number, fullName, type, surplus, nameBank);
        this.start = start;
    }

    public BankAccount(String id, String number, String fullName, String type, float surplus, String nameBank,
                       String start, String end) {
        this(id, number, fullName, type, surplus, nameBank, start);
        this.end = end;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        BankAccount.nextId = nextId;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        if (id == null) {
            this.id = "WAB" + nextId;
            nextId++;
        } else {
            getId();
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getSurplus() {
        return surplus;
    }

    public void setSurplus(float surplus) {
        this.surplus = surplus;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    //nạp tiền
    public boolean deposit(float amount) {
        if (amount > 0) {
            surplus += amount;
            return true;    //nạp thành công
        }
        return false;  //nạp thất bại
    }

    //rút tiền
    public boolean withdraw(float amount) {
        if (amount > 0 && amount <= surplus) {
            surplus -= amount;//rút thành công
        }
        return false;   //rút thất bại
    }

    //chuyển khoản
    public boolean transfer(BankAccount other, float amount) {
        if (amount > 0) {
            other.surplus += amount;
            surplus -= amount;
            return true;    //chuyển khoản thành công
        }
        return false;  //nạp thất bại
    }

    //thanh toán điện nước ,....
    public void payment(float amount) {
        System.out.println("Thanh toán tiền điện : " + (surplus - amount));
    }
}
