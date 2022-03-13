package Exercise;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        ArrayList<Employee> employees = new ArrayList<>();
        var input = new Scanner(System.in);
        var choice = 0;
        var fileName = "EMP.DAT";
        readEmpFromFile(fileName);//đọc dữ liệu từ file ra
        do {
            System.out.println("==========================MENU==========================");
            System.out.println("= 1. Thêm mới nhân viên vào danh sách                  =");
            System.out.println("= 2. Hiển thị danh sách nhân viên                      =");
            System.out.println("= 3. Tìm nhân viên theo tên                            =");
            System.out.println("= 4. Xóa nhân viên theo mã ID                          =");
            System.out.println("= 5. Ghi danh sách nhân viên vào file                  =");
            System.out.println("= 0. Kết thúc chương trình                             =");
            System.out.println("========================================================");
            System.out.println("==================> Lựa chọn của bạn ? <================");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("========> Chương trình kết thúc <==========");
                    break;
                case 1:
                    var emp = createNewEmloyees(input);
                    employees.add(emp);
                    updateEmployeeID(employees);
                    System.out.println("=======> Thêm mới nhân viên thành công <======");
                    break;
                case 2:
                    if (employees.size() > 0) {
                        showEmployees(employees);
                    } else {
                        System.out.println("========> Danh sách nhân viên rỗng <=======");
                    }
                    break;
                case 3:
                    if (employees.size() > 0) {
                        System.out.println("Nhập tên nhân viên cần tìm : ");
                        input.nextLine();
                        var name = input.nextLine();
                        var result = findEmpById(employees, name);
                        if (result.size() > 0) {
                            System.out.println("====> Đã tìm thấy " + result.size() + " kết quả : ");
                            showEmployees(result);
                        } else {
                            System.out.println("====> Nhân viên tên :" + name + " không tồn tại <====");
                        }
                    } else {
                        System.out.println("=====> Danh sách nhân viên rỗng <=====");
                    }
                    break;
                case 4:
                    if (employees.size() > 0) {
                        System.out.println("Nhập mã nhân viên cần xóa : ");
                        input.nextLine();
                        var id = input.nextLine();
                        var isSuccess = removeEmployeeById(employees, id);
                        if (isSuccess) {
                            System.out.println("========> Xóa mã ID : " + id + " thành công <=========");
                        } else {
                            System.out.println("========> Xóa mã ID : " + id + " thất bại  <=========");
                        }
                    }
                    break;
                case 5:
                    var isSuccess = writerEmpToFile(employees, fileName);
                    if (isSuccess) {
                        System.out.println("==========> Ghi file thành công <=======");
                    } else {
                        System.out.println("==========> Ghi file thất bại <========");
                    }
                    break;
                default:
                    System.out.println("====> Sao chức năng .Vui lòng chọn lại <=====");
                    break;
            }
        } while (choice != 0);
    }

    /**
     * phương thức ghi danh sách nhân viên vào file
     * @param employees
     * @param fileName
     * @return
     * @throws IOException
     */
    private static boolean writerEmpToFile(ArrayList<Employee> employees, String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter, true);
        if (employees.size() > 0) {
            for (var item : employees) {
                if (!isAppear(fileName, item.getId())) {
                    printWriter.println(item.getId());
                    printWriter.println(item.getFullName());
                    printWriter.println(item.getAddress());
                    printWriter.println(item.getAge());
                    printWriter.println(item.getNumberPhone());
                    printWriter.println(item.getSalary());
                    printWriter.println(item.getExperience());
                    printWriter.println("===============");
                }
            }
            fileWriter.close();
            printWriter.close();
            return true;
        } else {
            System.out.println("=====> Danh sách nhân viên rỗng <=====");
            return false;
        }
    }

    private static boolean removeEmployeeById(ArrayList<Employee> employees, String id) {
        for (var item : employees) {
            if (item.getId().compareToIgnoreCase(id) == 0) {
                employees.remove(item);
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Employee> findEmpById(ArrayList<Employee> employees, String name) {
        ArrayList<Employee> emp = new ArrayList<>();
        for (var item : employees) {
            var firstName = FirstNameEmployes(item.getFullName());
            if (firstName.compareToIgnoreCase(name) == 0) {
                emp.add(item);
            }
        }
        return emp;
    }

    private static String FirstNameEmployes(String fullName) {
        var index = fullName.lastIndexOf(" ");
        return fullName.substring(index + 1);
    }

    private static void showEmployees(ArrayList<Employee> employees) {
        System.out.printf("%-15s%-20s%-20s%-15s%-20s%-15s%-15s\n", "Mã ID", "Họ Tên", "Địa Chỉ", "Tuổi", "Số ĐT", "Lương",
                "Kinh nghiệm");
        for (var item : employees) {
            showItem(item);
        }
    }

    private static void showItem(Employee item) {
        System.out.printf("%-15s%-20s%-20s%-15d%-20s%-15.2f%-15.2f\n", item.getId(), item.getFullName(), item.getAddress(),
                item.getAge(), item.getNumberPhone(), item.getSalary(), item.getExperience());
    }

    /**
     * phương thức tạo mới nhân viên
     *
     * @param input đối tượng lớp Scanner
     * @return đối tượng nhân viên vừa tạo
     */
    private static Employee createNewEmloyees(Scanner input) {
        System.out.println("Nhập họ tên nhân viên : ");
        input.nextLine();
        var name = input.nextLine();
        System.out.println("Nhập địa chỉ nhân viên : ");
        var address = input.nextLine();
        System.out.println("Nhập tuổi nhân viên : ");
        var age = Integer.parseInt(input.nextLine());
        System.out.println("Nhập số điện thoại : ");
        var num = input.nextLine();
        System.out.println("Nhập mức lương : ");
        var salary = Float.parseFloat(input.nextLine());
        System.out.println("Nhập năm kinh nghiệm : ");
        var exp = Float.parseFloat(input.nextLine());
        return new Employee(null, name, address, age, num, salary, exp);
    }

    /**
     * phương thức cập nhật mã nhân viên tự động  tăng
     *
     * @param employees danh sách nhân viên rỗng
     */
    private static void updateEmployeeID(ArrayList<Employee> employees) {
        for (var emp : employees) {
            emp.setId();
        }
    }

    /**
     * phương thức load toàn bộ dữ liệu từ file ra ngoài
     *
     * @param fileName tên file cần load
     * @throws IOException xử lí ngoại lệ
     */
    private static void readEmpFromFile(String fileName) throws IOException {
        ArrayList<String> emp = new ArrayList<>();
        File file = new File(fileName);
        file.createNewFile();
        var input = new Scanner(file);
        while (input.hasNextLine()) {
            emp.add(input.nextLine());
        }
        if (emp.size() > 0) {
            System.out.println("==> Đã load toàn bộ dữ liệu từ file ! <=====");
        } else {
            System.out.println("=====> Đọc File .File rỗng!<=====");
        }
        input.close();
    }

    /**
     * phương thức kiểm tra xem
     * mã nhân viên đã được ghi vô file chưa
     * mỗi id chỉ ghi 1 lần
     *
     * @param fileName tên file
     * @param id       nhân viên
     * @return true nếu id đã ghi,false ngược lại
     * @throws IOException xử lí ngoại lệ
     */
    private static boolean isAppear(String fileName, String id) throws IOException {
        File file = new File(fileName);
        var input = new Scanner(file);
        file.createNewFile();
        while (input.hasNextLine()) {
            if (input.nextLine().compareToIgnoreCase(id) == 0) {
                return true; //mã id đã được ghi rồi
            }
        }
        return false;//mặc định chưa xuất hiện
    }
}
