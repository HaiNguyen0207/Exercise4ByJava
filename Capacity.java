package Exercise;

public enum Capacity {
    F("Trượt môn"), D("Yếu"), C("Trung bình"), B("Khá"), A("Giỏi"), A_Plus("Xuất Xắc");

    private  String value;

    public void setValue(String value) {
        this.value = value;
    }

    Capacity(String value) {
        this.value = value;

    }

    public String getValue() {
        return value;
    }

}
