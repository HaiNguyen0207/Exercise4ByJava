package Exercise;


import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) throws IOException {
        var input = new Scanner(System.in);
        var choice = 0;
        var fileName = "ACC.DAT";
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        readACCFromFile(fileName);//load file khi chạy
        do {
            System.out.println("=========================MENU==========================");
            System.out.println("= 1. Thêm mới một tài khoản vào danh sách             =");
            System.out.println("= 2. Hiển thị danh sách tài khoản                     =");
            System.out.println("= 3. Nạp tiền vào tài khoản với mã cho trước          =");
            System.out.println("= 4. Rút tiền khỏi tài khoản với mã cho trước         =");
            System.out.println("= 5. Chuyển tiền STK A- STK B với số tài khoản        =");
            System.out.println("= 6. Tìm tài khoản theo tên tài khoản                 =");
            System.out.println("= 7. Tìm tài khoản theo mã tài khoản                  =");
            System.out.println("= 8. Tìm tài khoản có số dư > X                       =");
            System.out.println("= 9. Xóa tài khoản theo mã cho trước                  =");
            System.out.println("= 10.Ghi danh sách tài khoản vào file                 =");
            System.out.println("= 0. Kết thúc chương trình                            =");
            System.out.println("=======================================================");
            System.out.println("================> Lựa chọn của bạn ? <=================");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("=====> Chương trình kết thúc .Tạm biệt <=======");
                    break;
                case 1:
                    var acc = createNewBankAcount(input);
                    bankAccounts.add(acc);
                    updateBankAccId(bankAccounts);
                    System.out.println("=====> Thêm mới tài khoản thành công <=====");
                    break;
                case 2:
                    if (bankAccounts.size() > 0) {
                        showBankAccounts(bankAccounts);
                    } else {
                        System.out.println("====> Danh sách tài khoản rỗng <=====");
                    }
                    break;
                case 3:
                    if (bankAccounts.size() > 0) {
                        System.out.println("Nhập mã tài khoản nạp tiền : ");
                        input.nextLine();
                        var id = input.nextLine();
                        System.out.println("Nhập số tiền nạp : ");
                        float amount = Float.parseFloat(input.nextLine());
                        var isSuccess = deposit(bankAccounts, id, amount);
                        if (isSuccess) {
                            System.out.println("======> Nạp tiền thành công <=====");
                        } else {
                            System.out.println("======> Nạp tiền thất bại <======");
                        }
                    } else {
                        System.out.println("====> Danh sách tài khoản rỗng <=====");
                    }
                    break;
                case 4:
                    if (bankAccounts.size() > 0) {
                        System.out.println("Nhập mã tài khoản rút tiền : ");
                        input.nextLine();
                        var id = input.nextLine();
                        System.out.println("Nhập số tiền rút : ");
                        float amount = Float.parseFloat(input.nextLine());
                        var isSuccess = withdraw(bankAccounts, id, amount);
                        if (isSuccess) {
                            System.out.println("====> Rút tiền thành công <====");
                        } else {
                            System.out.println("=====> Rút tiền thất bại <====");
                        }
                    } else {
                        System.out.println("====> Danh sách tài khoản rỗng <=====");
                    }
                    break;
                case 5:
                    if (bankAccounts.size() > 0) {
                        System.out.println("Nhập STK chuyển A : ");
                        input.nextLine();
                        var numberA = input.nextLine();
                        System.out.println("Nhập STK nhận B : ");
                        var numberB = input.nextLine();
                        System.out.println("Nhập số tiền chuyển : ");
                        var amount = Float.parseFloat(input.nextLine());
                        var isSuccess = transfer(bankAccounts, numberA, numberB, amount);
                        if (isSuccess) {
                            System.out.println("====> Chuyển khoản thành công <=====");
                        } else {
                            System.out.println("====> Chuyển khoản thất bại <=====");
                        }
                    } else {
                        System.out.println("====> Danh sách tài khoản rỗng <=====");
                    }
                    break;
                case 6:
                    if (bankAccounts.size() > 0) {
                        System.out.println("Nhập tên cần tìm kiếm : ");
                        input.nextLine();
                        var name = input.nextLine();
                        var result = findAccByName(bankAccounts, name);
                        if (result.size() > 0) {
                            System.out.println("====> Tìm thấy " + result.size() + " kết quả  <====");
                            showBankAccounts(result);
                        } else {
                            System.out.println("====> Tên chủ tài khoản " + name + " không  có <====");
                        }
                    } else {
                        System.out.println("====> Danh sách tài khoản rỗng <=====");
                    }
                    break;
                case 7:
                    if (bankAccounts.size() > 0) {
                        System.out.println("Nhập mã ID cần tìm kiếm : ");
                        input.nextLine();
                        var id = input.nextLine();
                        var result = findBankAccountById(bankAccounts, id);
                        if (result.size() > 0) {
                            System.out.println("====> Tìm thấy " + result.size() + " kết quả  <====");
                            showBankAccounts(result);
                        } else {
                            System.out.println("====> Tên chủ tài khoản " + id + " không  có <====");
                        }
                    } else {
                        System.out.println("====> Danh sách tài khoản rỗng <=====");
                    }
                    break;
                case 8:
                    if (bankAccounts.size() > 0) {
                        System.out.println("Nhập số dư cần tìm kiếm : ");
                        input.nextLine();
                        var surplus = Float.parseFloat(input.nextLine());
                        var result = findBankAccountBySurplus(bankAccounts, surplus);
                        if (result.size() > 0) {
                            System.out.println("====> Tìm thấy " + result.size() + " kết quả  <====");
                            showBankAccounts(result);
                        } else {
                            System.out.println("====> Không có tài khoản có số dư > " + surplus + " <=====");
                        }
                    } else {
                        System.out.println("====> Danh sách tài khoản rỗng <=====");
                    }
                    break;
                case 9:
                    if (bankAccounts.size() > 0) {
                        System.out.println("Nhập mã tài khoản cần xóa : ");
                        input.nextLine();
                        var id = input.nextLine();
                        var isSuccess = removeBankAccountById(bankAccounts, id);
                        if (isSuccess) {
                            System.out.println("=======>Xóa ID : " + id + " thành công <======");
                        } else {
                            System.out.println("=======>Xóa ID : " + id + " thất bại  <======");
                        }
                    } else {
                        System.out.println("====> Danh sách tài khoản rỗng <=====");
                    }
                    break;
                case 10:
                    if (bankAccounts.size() > 0) {
                        var isSuccess = witerBankAccountToFile(bankAccounts, fileName);
                        if(isSuccess) {
                            System.out.println("====> Ghi file thành công <=====");
                        }else {
                            System.out.println("====> Ghi file thất bại <=====");
                        }
                    } else {
                        System.out.println("====> Danh sách rỗng .Không thể ghi file <=====");
                    }
                    break;
                default:
                    System.out.println("=====> Sai chức năng ! Vui lòng chọn lại <=====");
                    break;
            }

        } while (choice != 0);
    }

    /**
     * phương thức ghi danh  sách tài khoản vào file
     *
     * @param bankAccounts danh sách tài khoản gốc
     * @param fileName     tên file
     * @return true nếu ghi thành công,false nếu ghi thất bại
     * @throws IOException xử lí ngoại lệ
     */
    private static boolean witerBankAccountToFile(ArrayList<BankAccount> bankAccounts, String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter, true);
        for (var item : bankAccounts) {
            if (!isAppear(item.getId(), fileName)) {
                printWriter.print(item.getId() + "-");
                printWriter.print(item.getNumber() + "-");
                printWriter.print(item.getFullName() + "-");
                printWriter.print(item.getType() + "-");
                printWriter.print(item.getSurplus() + "-");
                printWriter.print(item.getNameBank() + "-");
                printWriter.print(item.getStart() + "-");
                printWriter.print(item.getEnd());
                printWriter.println();
            }
        }
        fileWriter.close();
        printWriter.close();
        return true;
    }

    /**
     * phương thức kiểm tra xem file đã tồn tại id tk chưa
     * mỗi tài khoản chỉ có 1 id
     * mỗi id chỉ ghi 1 lần trong file
     *
     * @param id       tài khoản
     * @param fileName tên fie
     * @return true nếu id xuất hiện,false nếu chưa
     * @throws IOException xử lí ngoại lệ
     */
    private static boolean isAppear(String id, String fileName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        var input = new Scanner(file);
        while (input.hasNextLine()) {
            var data = input.nextLine().split("-");
            if (data[0].compareToIgnoreCase(id) == 0) {
                return true;    //đã xuất hiện rồi
            }
        }
        return false;//chưa xuất hiện
    }


    /**
     * phương thức xóa tài khoản theo mã
     *
     * @param bankAccounts danh sách tài khoản gốc
     * @param id           mã cần xóa
     * @return true nếu xóa thành công,false nếu xóa thất bại
     */
    private static boolean removeBankAccountById(ArrayList<BankAccount> bankAccounts, String id) {
        for (var item : bankAccounts) {
            if (item.getId().compareToIgnoreCase(id) == 0) {
                bankAccounts.remove(item);
                return true;
            }
        }
        System.out.println("====> Mã tài khoản không tồn tại <=====");
        return false;
    }

    /**
     * phương thức tìm kiếm số dư >x
     *
     * @param bankAccounts danh sách tài khoản gốc
     * @param surplus      số dư cần tìm kiếm
     * @return mảng chứa kết quả tìm kiếm
     */
    private static ArrayList<BankAccount> findBankAccountBySurplus(ArrayList<BankAccount> bankAccounts, float surplus) {
        ArrayList<BankAccount> bankAcc = new ArrayList<>();
        for (var item : bankAccounts) {
            if (item.getSurplus() > surplus) {
                bankAcc.add(item);
            }
        }
        return bankAcc;
    }

    /**
     * phương thức tìm kiếm tài khoản theo mã id
     *
     * @param bankAccounts danh sách tài khoản gốc
     * @param id           mã tài khoản cần tìm
     * @return mảng chứa kết quả tìm
     */
    private static ArrayList<BankAccount> findBankAccountById(ArrayList<BankAccount> bankAccounts, String id) {
        ArrayList<BankAccount> bankAcc = new ArrayList<>();
        for (var item : bankAccounts) {
            if (item.getId().compareToIgnoreCase(id) == 0) {
                bankAcc.add(item);
            }
        }
        return bankAcc;
    }

    /**
     * phương thức tìm kiếm tài khoản theo tên
     *
     * @param bankAccounts danh sách tài khoản gốc
     * @param name         tên tài khoản cần tìm
     * @return mảng chứa tìm kiếm
     */
    private static ArrayList<BankAccount> findAccByName(ArrayList<BankAccount> bankAccounts, String name) {
        ArrayList<BankAccount> bankAcc = new ArrayList<>();
        for (var item : bankAccounts) {
            var firstName = getFirstName(item.getFullName());
            if (firstName.compareToIgnoreCase(name) == 0) {
                bankAcc.add(item);
            }
        }
        return bankAcc;
    }

    /**
     * phương thức tách lấy tên tên
     *
     * @param fullName họ tên đầy đủ
     * @return mỗi tên
     */
    private static String getFirstName(String fullName) {
        var index = fullName.lastIndexOf(" ");
        return fullName.substring(index + 1);
    }

    /**
     * phương thức chuyển khoản theo số tk
     *
     * @param bankAccounts danh sách tài khoản gốc
     * @param numberA      số tài khoản chuyển a
     * @param numberB      số tài khoản nhận b
     * @param amount       số tiền chuyển
     * @return true nếu chuyển khoản thành công ,false nếu thất bại
     */
    private static boolean transfer(ArrayList<BankAccount> bankAccounts, String numberA, String numberB, float amount) {
        var accA = findAccById(bankAccounts, numberA);
        var accB = findAccById(bankAccounts, numberB);
        if (accA != null && accB != null) {
            accA.transfer(accB, amount);
            return true;
        } else {
            System.out.println("=====> Số tài khoản không hợp lệ  <====");
            return false;
        }
    }

    /**
     * phương thức rút tiền theo mã
     *
     * @param bankAccounts danh sách tài khoản gốc
     * @param id           mã tài khoản rút tiền
     * @param amount       số tiền rút
     * @return true nếu rút thành công ,false nếu thất bại
     */
    private static boolean withdraw(ArrayList<BankAccount> bankAccounts, String id, float amount) {
        var acc = findAccById(bankAccounts, id);
        if (acc != null) {
            acc.withdraw(amount);
            return true;
        } else {
            System.out.println("=====> Mã tài khoản không tồn tại <====");
            return false;
        }
    }

    /**
     * phương thức nạp tiền theo mã
     *
     * @param bankAccounts danh sách tài khoản gốc
     * @param id           mã tài khoản cần nạp tiền
     * @param amount       số tiền nạp
     * @return true nếu nạp thành công ,false  thất bại
     */
    private static boolean deposit(ArrayList<BankAccount> bankAccounts, String id, float amount) {
        var acc = findAccById(bankAccounts, id);
        if (acc != null) {
            acc.deposit(amount);
            return true;
        } else {
            System.out.println("=====> Mã tài khoản không tồn tại <====");
            return false;
        }
    }

    /**
     * phương thức tìm tài khoản theo id
     *
     * @param bankAccounts danh sách tài khoản gốc
     * @param id           mã tài khoản cần duyệt
     * @return item nếu tồn tại, null nếu không
     */
    private static BankAccount findAccById(ArrayList<BankAccount> bankAccounts, String id) {
        for (var item : bankAccounts) {
            if (item.getId().compareToIgnoreCase(id) == 0) {
                return item;
            }
        }
        return null;
    }

    /**
     * phương thức hiển thị danh sách tài khoản
     * theo dạng cột
     *
     * @param bankAccounts danh sách tài khoản gốc
     */
    private static void showBankAccounts(ArrayList<BankAccount> bankAccounts) {
        System.out.printf("%-20s%-20s%-20s%-20s%-15s%-20s%-15s%-15s\n", "Mã ID", "Số TK", "Họ Tên", "Loại TK",
                "Số Dư", "Tên NH", "Ngày PH", "Ngày HH");
        for (var item : bankAccounts) {
            showItem(item);
        }
    }

    /**
     * phương thức hiển thị 1 tài khoản
     *
     * @param item tài khoản
     */
    private static void showItem(BankAccount item) {
        System.out.printf("%-20s%-20s%-20s%-20s%-15.2f%-20s%-15s%-15s\n", item.getId(), item.getNumber(),
                item.getFullName(), item.getType(), item.getSurplus(), item.getNameBank(), item.getStart(), item.getEnd());
    }

    /**
     * phương thức cập nhật ID Tài khoản
     * id tự động tăng  từ WAB10001........
     *
     * @param bankAccounts tài khoản gốc
     */
    private static void updateBankAccId(ArrayList<BankAccount> bankAccounts) {
        for (var item : bankAccounts) {
            item.setId();
        }
    }

    /**
     * phương thức thêm mới 1 tài khoản
     *
     * @param input đối tượng lớp Scanner
     * @return đối tượng vừa tạo
     */
    private static BankAccount createNewBankAcount(Scanner input) {
        System.out.println("Nhập số tài khoản : ");
        input.nextLine();
        var number = input.nextLine();
        System.out.println("Nhập họ tên : ");
        var name = input.nextLine();
        System.out.println("Nhập loại tài khoản : ");
        var type = input.nextLine();
        System.out.println("Nhập số dư : ");
        float surplus = Float.parseFloat(input.nextLine());
        System.out.println("Nhập tên ngân hàng : ");
        var nameBank = input.nextLine();
        System.out.println("Nhập ngày phát hành : ");
        var start = input.nextLine();
        System.out.println("Nhập ngày hết hạn : ");
        var end = input.nextLine();
        return new BankAccount(null, number, name, type, surplus, nameBank, start, end);
    }

    /**
     * phương thức load toàn bộ dữ liệu ra
     * trước khi chạy chương trình
     *
     * @param fileName tên File
     * @return ArrayList chứ dữ liệu file
     * null nếu file rỗng
     * @throws FileNotFoundException xử lí ngoại lệ
     */
    private static ArrayList<BankAccount> readACCFromFile(String fileName) throws FileNotFoundException {
        ArrayList<BankAccount> bankAcc = new ArrayList<>();
        File file = new File(fileName);
        var input = new Scanner(file);
        while (input.hasNextLine()) {
            var data = input.nextLine().split("-");
            var acc = readElementFromFile(bankAcc, data);
            bankAcc.add(acc);
        }
        input.close();
        if (bankAcc.size() > 0) {
            return bankAcc;
        } else {
            return null;
        }
    }

    /**
     * phương thức đọc từng phần tử trên 1 dòng
     *
     * @param bankAccounts mảng load toàn bộ dũ liệu ra file
     * @param data         mảng chưa dữ liệu
     * @return new BankAccount
     */
    private static BankAccount readElementFromFile(ArrayList<BankAccount> bankAccounts, String[] data) {
        var id = data[0];
        var number = data[1];
        var fullName = data[2];
        var type = data[3];
        var surplus = Float.parseFloat(data[4]);
        var nameBank = data[5];
        var start = data[6];
        var end = data[7];
        return new BankAccount(id, number, fullName, type, surplus, nameBank, start, end);

    }
}
