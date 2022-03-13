package Exercise;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Test3 {
    public static void main(String[] args) throws IOException {
        var input = new Scanner(System.in);
        var choice = 0;
        ArrayList<Subject> subjects = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        var fileSubject = "SUB.DAT";
        var fileStudent = "STUD.DAT";
        var fileCourse = "COU.DAT";
        var fileTran = "COU-TRAN.DAT";
        readSubFromFile(fileSubject);// read subject from file
        readStuFromFile(fileStudent);//read student from file
        readCouFromFile(fileCourse);//read course from file
        readTranStuFromFile(fileTran);//read Tran-Stu from file
        do {
            System.out.println("===========================MENU===========================");
            System.out.println("= 1. Thêm mới 1 môn học vào danh sách môn học            =");
            System.out.println("= 2. Thêm mới một sinh viên vào danh sách sinh viên      =");
            System.out.println("= 3. Thêm mới một khóa học vào danh sách khóa học        =");
            System.out.println("= 4. Thêm mới một sinh viên vào lớp  học nào đó          =");
            System.out.println("= 5. Hiển thị danh sách môn học                          =");
            System.out.println("= 6. Hiển thị danh sách sinh viên                        =");
            System.out.println("= 7. Hiển thị danh sách khóa học                         =");
            System.out.println("= 8. Nhập và tính điểm trung bình cho từng sv trong lớp  =");
            System.out.println("= 9. Xét học lực cho từng sinh viên trong lớp            =");
            System.out.println("= 10. Hiển thị bảng điểm, học lực các sinh viên trong lớp= ");
            System.out.println("= 11. Tìm xem sinh viên s có trong khóa học nào không    = ");
            System.out.println("= 12. Ghi danh sách sinh viên vào file                   = ");
            System.out.println("= 13. Ghi danh sách môn học vào file                     =");
            System.out.println("= 14. Ghi danh sách lớp học vào file                     =");
            System.out.println("= 15. Ghi danh sách sinh viên - bảng điểm vào file       =");
            System.out.println("= 0. Thoát chương trình                                  = ");
            System.out.println("=================>Xin mời bạn chọn: <===================== ");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("========> Chương trình kết thúc <=========");
                    break;
                case 1:
                    var subject = createNewSubject(input);
                    subjects.add(subject);
                    updateSubjectId(subjects);
                    System.out.println("=========> Thêm mới môn học thành công <=======");
                    break;
                case 2:
                    var student = createNewStudent(input);
                    students.add(student);
                    updateStudentId(students);
                    System.out.println("=======> Thêm mới sinh viên thành công <=======");
                    break;
                case 3:
                    var course = createNewCourse(input, courses, subjects);
                    if (course) {
                        updateCrouseId(courses);
                        System.out.println("=====> Thêm mới môn học thành công <====");
                    } else {
                        System.out.println("=====> Thêm mới môn học thất bại <======");
                    }
                    break;
                case 4:
                    if (students.size() > 0 && courses.size() > 0) {
                        var isSuccess = addStudentInCourse(students, courses, input);
                        if (isSuccess) {
                            System.out.println("====> Thêm sinh viên vào lớp thành công <======");
                        } else {
                            System.out.println("====> Thêm sinh viên vào lớp thất bại  <======");
                        }
                    } else {
                        System.out.println("==> Danh sách lớp học hoặc sinh viên rỗng .Vui lòng xem lại <==");
                    }
                    break;
                case 5:
                    if (subjects.size() > 0) {
                        showSubjects(subjects);
                    } else {
                        System.out.println("========> Danh sách môn học rỗng <=======");
                    }
                    break;
                case 6:
                    if (students.size() > 0) {
                        showStudents(students);
                    } else {
                        System.out.println("======> Danh sách sinh viên rỗng <=====");
                    }
                    break;
                case 7:
                    if (courses.size() > 0) {
                        showCourses(courses);
                    } else {
                        System.out.println("=====> Danh sách lớp học rỗng <========");
                    }
                    break;
                case 8:
                    if (courses.size() > 0) {
                        var isCulcalTranscript = culcalTranscriptOfStudent(courses, input);
                        if (isCulcalTranscript) {
                            System.out.println("==> Tính điểm sinh viên thành công <===");
                        } else {
                            System.out.println("==> Tính điểm sinh viên thất bại <===");
                        }
                    } else {
                        System.out.println("==> Danh sách lớp học rỗng ! Không thể tính điểm sinh viên <==");
                    }
                    break;
                case 9:
                    if (courses.size() > 0) {
                        var isCulcalCapacity = culCalCapacityOfStudent(courses, input);
                        if (isCulcalCapacity) {
                            System.out.println("==> Xét học lực sinh viên thành công <===");
                        } else {
                            System.out.println("==> Xét học lực sinh viên thất bại <===");
                        }
                    } else {
                        System.out.println("==> Danh sách lớp học rỗng ! Không thể xét học lực sinh viên <==");
                    }
                    break;
                case 10:
                    if (courses.size() > 0) {
                        showStudentAndTranscript(courses, input);
                    } else {
                        System.out.println("====> Danh sách lớp học rỗng <===");
                    }
                    break;
                case 11:
                    if (courses.size() > 0 && students.size() > 0) {
                        System.out.println("Nhập mã sinh viên cần tìm kiếm : ");
                        input.nextLine();
                        var studentId = input.nextLine();
                        var student1 = findStudentById(students, studentId);
                        if (student1 != null) {
                            var result = findAStudentById(courses, studentId);
                            if (result) {
                                System.out.println("===> Tìm kiếm ID" + studentId + " thành công <===");
                            } else {
                                System.out.println("==> Tìm kiếm ID" + studentId + " thất bại <===");
                            }
                        } else {
                            System.out.println("==> Mã sinh viên không tồn tại <===");
                        }
                    } else {
                        System.out.println("==> Danh sách sinh viên hoặc lớp học rỗng <==");
                    }
                    break;
                case 12:
                    if (students.size() > 0) {
                        var isValid = writeStudentToFile(students, fileStudent);
                        if (isValid) {
                            System.out.println("===> Ghi file thành công <====");
                        } else {
                            System.out.println("===> Ghi file thất bại <====");
                        }
                    } else {
                        System.out.println("==> Danh sách sinh viên rỗng ! Ghi file thất bại <==");
                    }
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                default:
                    System.out.println("=====> Sai chức năng ! Vui lòng chọn lại <======");
                    break;
            }
        } while (choice != 0);
    }

    /**
     * phương thức ghi danh sách sinh viên vào file
     *
     * @param students    danh sách sinh viên gốc
     * @param fileStudent tên file ghi
     * @return true nếu ghi thành công,false nếu thất bại
     * @throws IOException xử lý ngoại lệ
     */
    private static boolean writeStudentToFile(ArrayList<Student> students, String fileStudent) throws IOException {
        File file = new File(fileStudent);
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter,true);
        for (var item : students) {

            if (!isAppearStudentInFile(file, item.getId())) {
                printWriter.print(item.getId() + "*");
                printWriter.print(item.getFullName() + "*");
                printWriter.print(item.getAddress() + "*");
                printWriter.print(item.getEmail() + "*");
                printWriter.print(item.getGender() + "*");
                printWriter.print(item.getNameClass() + "*");
                printWriter.print(item.getMajor());
                printWriter.println();
            }
        }
        fileWriter.close();
        printWriter.close();
        return true;

    }

    /**
     * phương thức kiểm tra xem trong file đã co sinh viên chưa
     * mỗi sinh viên chỉ xuất hiện 1 lần trong file
     *
     * @param file file ghi
     * @param id   mã sinh viên kiểm tra
     * @return true nếu xuất hiện rồi,false nếu chưa xuất hiện
     * @throws IOException xử lý ngoại lệ
     */
    private static boolean isAppearStudentInFile(File file, String id) throws IOException {
        file.createNewFile();
        var input = new Scanner(file);
        while (input.hasNextLine()) {
            var data = "*".split(input.nextLine());
            if (data[0].compareToIgnoreCase(id) == 0) {
                return true;    //xuất hiện r
            }
        }
        input.close();
        return false;  //chưa xuất hiện
    }

    /**
     * phương thức tìm kiếm sinh viên có trong lớp học không
     * do mỗi sinh viên một mã ID
     * nên việc tìm kiếm chỉ nhận dc 1 sinh viên xuất hiện 1 phòng nào đó
     * hoặc k xuất hiện khi ko có mặt trong phòng
     *
     * @param courses   danh sách lớp học gốc
     * @param studentId mã sinh viên cần tìm
     * @return true nếu tìm thấy ,false nếu k tìm thấy
     */
    private static boolean findAStudentById(ArrayList<Course> courses, String studentId) {
        for (int i = 0; i < courses.size(); i++) {
            var s = courses.get(i).getTranscriptOfStudents();
            for (var item : s) {
                if (item.getStudent().getId().compareToIgnoreCase(studentId) == 0) {
                    System.out.println("==> Mã ID " + studentId + " xuất hiện trong phòng "
                            + courses.get(i).getClassRom() + " <==");
                    System.out.printf("%-15s%-20s%-20s%-20s%-15s%-15s%-20s\n", "Mã ID", "Họ Tên", "Địa chỉ", "Email",
                            "Giới tính", " Tên lớp", "Chuyên ngành");
                    System.out.printf("%-15s%-20s%-20s%-20s%-15s%-15s%-20s\n", item.getStudent().getId(),
                            item.getStudent().getFullName(), item.getStudent().getAddress(), item.getStudent().getEmail(),
                            item.getStudent().getGender(), item.getStudent().getNameClass(), item.getStudent().getMajor());
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * phương thức hiển thị sinh viên cùng bảng điểm và học lực
     * theo dạng cột
     *
     * @param courses danh sách lớp học gốc
     * @param input   đối tượng lớp Scanner
     */
    private static void showStudentAndTranscript(ArrayList<Course> courses, Scanner input) {
        System.out.println("Nhập mã lớp cần hiển thị : ");
        input.nextLine();
        var courseId = input.nextLine();
        var course = findCourseById(courses, courseId);
        if (course != null) {
            System.out.printf("%-20s%-15s%-20s%-15s%-15s%-15s%-15s%-20s\n", "Mã lớp", "Mã SV", "Tên SV", "Điểm HS1",
                    "Điểm HS2", "Điểm HS3", "Điểm TB", "Học lực");
            showCapacity(course.getTranscriptOfStudents(), course);
        } else {
            System.out.println("====> Mã lớp học không tồn tại <===");
        }
    }

    /**
     * phương thức hiển thị từng sinh viên
     * cùng bảng điểm vs học lực
     *
     * @param transcriptOfStudents danh sách sinh viên và bảng điểm gốc
     * @param course               lớp
     */
    private static void showCapacity(ArrayList<Course.TranscriptOfStudent> transcriptOfStudents, Course course) {
        for (var s : transcriptOfStudents) {
            System.out.printf("%-20s%-15s%-20s%-15.2f%-15.2f%-15.2f%-15.2f%-20s\n", course.getId(), s.getStudent().getId(),
                    s.getStudent().getFullName(), s.getTranscript().getGrade1(), s.getTranscript().getGrade2(),
                    s.getTranscript().getGrade3(), s.getTranscript().getGpa(), s.getTranscript().getCapacity());
        }
    }

    /**
     * phương thức xét học lực sinh viên trong lớp
     *
     * @param courses danh sách lớp học gốc
     * @param input   đối tượng lớp Scanner
     * @return true nếu xét thành công ,false thất bại
     */
    private static boolean culCalCapacityOfStudent(ArrayList<Course> courses, Scanner input) {
        System.out.println("Nhập mã lớp xét học lực : ");
        input.nextLine();
        var courseId = input.nextLine();
        var course = findCourseById(courses, courseId);
        if (course != null) {
            var capacity = capacityOfStudent(course.getTranscriptOfStudents());
            if (capacity) {
                return true;
            } else {
                return false;
            }
        }
        System.out.println("===> Mã lớp học không tồn tại <====");
        return false;
    }

    /**
     * phương thức xét học lực cho từng sinh viên
     * yêu cầu đã nhập bảng điểm xong r
     *
     * @param transcriptOfStudents sinh viên và bảng điểm tương ứng
     * @return true nếu cập nhật thành công ,false nếu thất bại
     */
    private static boolean capacityOfStudent(ArrayList<Course.TranscriptOfStudent> transcriptOfStudents) {
        for (var item : transcriptOfStudents) {
            if (item.getTranscript() != null) {
                item.getTranscript().culcalCapacity();
            } else {
                System.out.println("===> Chưa cập nhật bảng điểm hết cho sinh viên <===");
                return false;
            }
        }
        return true;
    }

    /**
     * phương thúc tính điểm cho sinh viên
     *
     * @param courses danh sách lớp học gốc
     * @param input   đối tượng lớp Scanner
     * @return true tính thành công ,false nếu thất bại
     */
    private static boolean culcalTranscriptOfStudent(ArrayList<Course> courses, Scanner input) {
        System.out.println("Nhập mã lớp cần tính điểm : ");
        input.nextLine();
        var courseId = input.nextLine();
        var course = findCourseById(courses, courseId);
        if (course != null) {
            if (course.getTranscriptOfStudents().size() > 0) {
                enterTranscriptOfStudent(course.getTranscriptOfStudents(), input, course);
                //phương thức tính điểm
                for (var item : course.getTranscriptOfStudents()) {
                    item.getTranscript().setGpa();
                }
                return true;
            } else {
                System.out.println("===> Mã lớp " + courseId + " <===chưa có sinh viên nào !");
                return false;
            }
        }
        System.out.println("====> Mã lớp không hợp lệ <====");
        return false;
    }

    /**
     * phương thức nhập điểm cho từng  sinh viên
     *
     * @param transcriptOfStudents sinh viên và bảng điểm tương ứng
     * @param input                đối tượng lớp Scanner
     * @param course               lớp
     */

    private static void enterTranscriptOfStudent(ArrayList<Course.TranscriptOfStudent> transcriptOfStudents,
                                                 Scanner input, Course course) {
        for (var item : transcriptOfStudents) {
            if (item.getTranscript() == null) {
                System.out.println("Nhập điểm của mã sinh viên  " + item.getStudent().getId() + " : ");
                System.out.println("Nhập điểm hệ số 1 : ");
                var garde1 = Float.parseFloat(input.nextLine());
                System.out.println("Nhập điểm hệ số 2 : ");
                var garde2 = Float.parseFloat(input.nextLine());
                System.out.println("Nhập điểm hệ số 3 : ");
                var garde3 = Float.parseFloat(input.nextLine());
                var transcript = new Transcript(garde1, garde2, garde3);
                course.setTranscriptOfStudents(item.getStudent(), transcript);
            }
        }

    }

    /**
     * phương thức thêm sinh viên vào lớp học
     *
     * @param students danh sách sinh viên gốc
     * @param courses  danh sách lớp học gốc
     * @param input    đối tượng lớp Scanner
     * @return true nếu thêm thành công,false nếu thất bại
     */
    private static boolean addStudentInCourse(ArrayList<Student> students, ArrayList<Course> courses, Scanner input) {
        System.out.println("Nhập mã lớp học : ");
        input.nextLine();
        var courseId = input.nextLine();
        var course = findCourseById(courses, courseId);
        if (course != null) {
            System.out.println("===> Danh sách sinh viên đã có trong phòng " + course.getClassRom() + " <====");
            showStudentInCourse(course.getTranscriptOfStudents());
            System.out.println("Nhập mã sinh viên cần thêm : ");
            var studentId = input.nextLine();
            var student = findStudentById(students, studentId);
            if (student != null) {
                //kiểm tra sinh viên đã xuất hiện phòng nào chưa
                //mỗi sinh viên chỉ ở duy nhất 1 lớp
                var isAppear = isAppearStudentInCoure(courses, courseId, studentId);
                if (isAppear) {
                    return false;
                } else {
                    course.addTranscriptOfStudent(student, null);
                    return true;
                }
            } else {
                System.out.println("===> Mã sinh viên không hợp lệ <====");
                return false;
            }
        }
        System.out.println("=====> Mã lớp học không tồn tại <====");
        return false;
    }

    /**
     * phương thức kiểm  tra sinh viên
     * đã có trong lớp nào chưa
     * mồi sinh viên chỉ có mặt trong 1 lớp
     *
     * @param courses   danh sách lớp học gốc
     * @param courseId  mã lớp học
     * @param studentId mã sinh viên
     * @return true nếu đã xuất hiện,flase nếu chưa xuất hiện
     */
    private static boolean isAppearStudentInCoure(ArrayList<Course> courses, String courseId, String studentId) {
        for (var item : courses) {
            var isValid = checkStudentInCourse(item.getTranscriptOfStudents(), studentId);
            if (isValid) {
                System.out.println("====> Mã ID sinh viên " + studentId + " đã xuất hiện ở phòng "
                        + item.getClassRom() + " <====");
                return true;    //xuât hiện rồi
            }
        }
        return false;   //chưa xuất hiện
    }

    /**
     * phương thức kiểm tra sinh viên
     * đã xuất hiện phòng nào chưa
     * sinh viên chỉ ở duy nhất 1 phòng học
     *
     * @param transcriptOfStudents sinh viên -bảng điểm gốc
     * @param studentId            mã sinh viên
     * @return true nếu sinh viên xuất hiện r,false nếu chưa
     */
    private static boolean checkStudentInCourse(ArrayList<Course.TranscriptOfStudent> transcriptOfStudents,
                                                String studentId) {
        for (var item : transcriptOfStudents) {
            if (item.getStudent().getId().compareToIgnoreCase(studentId) == 0) {
                return true; //đã xuất hiện rồi
            }
        }
        return false;  //chưa xuất hiện
    }

    /**
     * phương thức kiểm tra mã sinh viên có tồn tại hay k
     *
     * @param students  danh sách viên gốc
     * @param studentId mã sinh viên cần kiểm tra
     * @return item nếu mã sinh viển tồn tại ,null nếu k
     */
    private static Student findStudentById(ArrayList<Student> students, String studentId) {
        for (var item : students) {
            if (item.getId().compareToIgnoreCase(studentId) == 0) {
                return item;
            }
        }
        return null;
    }

    /**
     * phương thức hiển thị danh sách sinh viên trong lớp học
     * môi sinh viên chỉ xuất hiện trong 1 lớp học
     *
     * @param transcriptOfStudents danh sách sinh viên -bảng điểm gốc
     */
    private static void showStudentInCourse(ArrayList<Course.TranscriptOfStudent> transcriptOfStudents) {
        System.out.printf("%-15s%-20s%-20s%-20s%-15s%-15s%-20s\n", "Mã ID", "Họ Tên", "Địa chỉ", "Email",
                "Giới tính", " Tên lớp", "Chuyên ngành");
        for (var item : transcriptOfStudents) {
            System.out.printf("%-15s%-20s%-20s%-20s%-15s%-15s%-20s\n", item.getStudent().getId(),
                    item.getStudent().getFullName(), item.getStudent().getAddress(), item.getStudent().getEmail(),
                    item.getStudent().getGender(), item.getStudent().getNameClass(), item.getStudent().getMajor());
        }
    }

    /**
     * phương thức kiểm tra mã lớp học có tồn tại hay không
     *
     * @param courses  danh sách lớp học gốc
     * @param courseId mã lớp
     * @return item nếu mã lớp tồn tại,null nếu không có
     */
    private static Course findCourseById(ArrayList<Course> courses, String courseId) {
        for (var item : courses) {
            if (item.getId().compareToIgnoreCase(courseId) == 0) {
                return item;
            }
        }
        return null;
    }

    /**
     * phương thức hiển thị danh sách lớp học
     * theo dạnh cột
     *
     * @param courses danh sách lớp học gốc
     */
    private static void showCourses(ArrayList<Course> courses) {
        System.out.printf("%-15s%-20s%-15s%-15s%-20s\n", "Mã ID", "Tên lớp", "Phòng học", "Giờ học", "Môn học");
        for (var item : courses) {
            showCourse(item);
        }
    }

    /**
     * phương thức hiển thị 1 lớp học
     *
     * @param item lớp học cần hiển thị
     */
    private static void showCourse(Course item) {
        System.out.printf("%-15s%-20s%-15s%-15s%-20s\n", item.getId(), item.getName(), item.getClassRom(),
                item.getTime(), item.getSubject().getName());
    }

    /**
     * phương thức cập nhật id của lớp học
     * mã lớp học tự động tăng
     *
     * @param courses danh sách lớp học gốc
     */
    private static void updateCrouseId(ArrayList<Course> courses) {
        for (var item : courses) {
            item.setId();
        }
    }

    /**
     * phương thức tạo mới 1  lớp học
     * nếu mã môn học không tồn tại ,ko thể tạo
     *
     * @param input    đối tượng lớp Scanner
     * @param courses  danh sách lớp học gốc
     * @param subjects danh sách môn học gốc
     * @return lớp học vừa tạo
     */
    private static boolean createNewCourse(Scanner input, ArrayList<Course> courses, ArrayList<Subject> subjects) {
        System.out.println("Nhập tên lớp : ");
        input.nextLine();
        var name = input.nextLine();
        System.out.println("Nhập phòng học : ");
        var classRom = input.nextLine();
        System.out.println("Nhập giờ học : ");
        var time = input.nextLine();
        System.out.println("Nhập mã môn học : ");
        var subjectId = input.nextLine();
        var subject = findSubjectById(subjects, subjectId);
        if (subject != null) {
            courses.add(new Course(null, name, classRom, time, subject));
            return true;
        } else {
            System.out.println("====> Mã môn học không tồn tại <=====");
            return false;
        }
    }

    /**
     * phương thức tìm kiếm mã môn học có tồn tại ko
     *
     * @param subjects  danh sách môn học gốc
     * @param subjectId mã môn học cần kiểm tra
     * @return item nếu mã môn tồn tại,null nếu không
     */
    private static Subject findSubjectById(ArrayList<Subject> subjects, String subjectId) {
        for (var item : subjects) {
            if (item.getId().compareToIgnoreCase(subjectId) == 0) {
                return item;
            }
        }
        return null;
    }

    /**
     * phương thức thức hiển thị sinh viên
     * theo dạng cột
     *
     * @param students danh sách sinh viên gốc
     */
    private static void showStudents(ArrayList<Student> students) {
        System.out.printf("%-15s%-20s%-20s%-20s%-15s%-15s%-20s\n", "Mã ID", "Họ Tên", "Địa chỉ", "Email", "Giới tính",
                "Tên lớp", "Chuyên ngành");
        for (var item : students) {
            showStudent(item);
        }
    }

    /**
     * phương thức hiển thị 1 sinh viên
     *
     * @param item sinh viên cần hiển thị
     */
    private static void showStudent(Student item) {
        System.out.printf("%-15s%-20s%-20s%-20s%-15s%-15s%-20s\n", item.getId(), item.getFullName(), item.getAddress(),
                item.getEmail(), item.getGender(), item.getNameClass(), item.getMajor());
    }

    /**
     * phương thức cập nhật id sinh viên
     * mỗi sinh viên 1 mã id, và tự động tăng
     *
     * @param students danh sách sinh viên gốc
     */
    private static void updateStudentId(ArrayList<Student> students) {
        for (var student : students) {
            student.setId();
        }
    }

    /**
     * phương thức tạo mới 1 sinh viên
     *
     * @param input đối tượng lớp Scanner
     * @return đối tượng sinh viên vừa tạo
     */
    private static Student createNewStudent(Scanner input) {
        System.out.println("Nhập tên sinh viên : ");
        input.nextLine();
        var fullName = input.nextLine();
        System.out.println("Nhập địa chỉ sinh viên : ");
        var address = input.nextLine();
        System.out.println("Nhập email :");
        var email = input.nextLine();
        System.out.println("Nhập giới tính : ");
        var gender = input.nextLine();
        System.out.println("Nhập tên lớp :");
        var classRom = input.nextLine();
        System.out.println("Nhập chuyên ngành : ");
        var major = input.nextLine();
        return new Student(null, fullName, address, email, gender, classRom, major);
    }

    /**
     * phương thức hiển thị danh sách môn học
     * theo dạng cột
     *
     * @param subjects danh sách môn học gốc
     */
    private static void showSubjects(ArrayList<Subject> subjects) {
        System.out.printf("%-15s%-20s%-15s%-15s%-15s\n", "Mã ID", "Tên môn", "Số Tín", "Số Tiết", "Số BKT");
        for (var subject : subjects) {
            showSubject(subject);
        }
    }

    /**
     * phương thức hiển thị môn học
     *
     * @param subject môn học cần hiển thị
     */
    private static void showSubject(Subject subject) {
        System.out.printf("%-15s%-20s%-15s%-15s%-15s\n", subject.getId(), subject.getName(), subject.getCredit(),
                subject.getNumOfLesson(), subject.getNumOfExam());
    }

    /**
     * phương thức cập nhật mã môn học
     * tự động tăng
     *
     * @param subjects danh sách môn học gốc
     */
    private static void updateSubjectId(ArrayList<Subject> subjects) {
        for (var sub : subjects) {
            sub.setId();
        }
    }

    /**
     * phương thức tạo mới 1 môn học
     *
     * @param input đối tượng lớp Scanner
     * @return đối tượng mới tạo
     */
    private static Subject createNewSubject(Scanner input) {
        System.out.println("Nhập tên môn học : ");
        input.nextLine();
        var name = input.nextLine();
        System.out.println("Nhập số tín  : ");
        var credit = Integer.parseInt(input.nextLine());
        System.out.println("Nhập số tiết : ");
        var numOfLesson = Integer.parseInt(input.nextLine());
        System.out.println("Nhập số bài kiểm tra : ");
        var numOfExam = Integer.parseInt(input.nextLine());
        return new Subject(null, name, credit, numOfLesson, numOfExam);
    }

    /**
     * phương thức đọc dữ liệu từ fileTran
     * load trước khi chạy chương trình
     * file chứa sinh viên và mỗi bảng điểm tương ứng
     *
     * @param fileTran tên file
     * @return read nếu file có dữ liệu ,null nếu file rỗng
     * @throws IOException xử lý ngoại lệ
     */
    private static ArrayList readTranStuFromFile(String fileTran) throws IOException {
        ArrayList<String> read = new ArrayList<>();
        File file = new File(fileTran);
        file.createNewFile();
        var input = new Scanner(file);
        while (input.hasNextLine()) {
            read.add(input.nextLine());
        }
        input.close();
        if (read.size() > 0) {
            return read;
        } else {
            return null;
        }
    }

    /**
     * phương thức load toàn bộ dữ liệu từ file course
     * trước khi chạy  chương trình
     *
     * @param fileCourse tên file
     * @return course nếu file có dữ liệu,null nếu không
     * @throws IOException xử lý ngoại lệ
     */
    private static ArrayList<Course> readCouFromFile(String fileCourse) throws IOException {
        ArrayList<Course> courses = new ArrayList<>();
        File file = new File(fileCourse);
        file.createNewFile();
        var input = new Scanner(file);
        while (input.hasNextLine()) {
            var data = "*".split(input.nextLine());
            var course = readElementCourseFromFile(data);
            courses.add(course);
        }
        input.close();
        if (courses.size() > 0) {
            return courses;
        } else {
            return null;
        }
    }

    /**
     * phương thức đọc từng thuộc tính trong file
     *
     * @param data mảng chứa dữ liệu thuộc tính
     * @return đối tượng vừa đọc từ file
     */
    private static Course readElementCourseFromFile(String[] data) {
        var id = data[0];
        var name = data[1];
        var classRom = data[2];
        var time = data[3];
        Subject subject = new Subject(data[4]);
        return new Course(id, name, classRom, time, subject);
    }

    /**
     * phương thức load toàn bộ dữ liệu fileStudent
     * trước khi chạy chương trình
     *
     * @param fileStudent tên file
     * @return students nếu file có dữ liệu,null nếu file rỗng
     * @throws IOException xử lý ngoại lệ
     */
    private static ArrayList<Student> readStuFromFile(String fileStudent) throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(fileStudent);
        file.createNewFile();
        var input = new Scanner(file);
        while (input.hasNextLine()) {
            var data = "*".split(input.nextLine());
            var student = readElementStuFromFile(data);
            students.add(student);
        }
        input.close();
        if (students.size() > 0) {
            return students;
        } else {
            return null;
        }
    }

    /**
     * phương thức đọc từng thuộc tính của  fileStudent
     *
     * @param data mảng chứa dữ liệu thuộc tính
     * @return đối tượng mới đọc ra từ file
     */
    private static Student readElementStuFromFile(String[] data) {
        var id = data[0];
        var name = data[1];
        var address = data[2];
        var email = data[3];
        var gender = data[4];
        var nameClass = data[5];
        var major = data[6];
        return new Student(id, name, address, email, gender, nameClass, major);
    }

    /**
     * phương thức đọc toàn bộ dữ liệu
     * từ file subject trước khi chạy chương trình
     *
     * @param fileSubject tên file
     * @return subjects nếu file có ,null nếu file rỗng
     * @throws IOException xử lý ngoại lệ
     */
    private static ArrayList<Subject> readSubFromFile(String fileSubject) throws IOException {
        ArrayList<Subject> subjects = new ArrayList<>();
        File file = new File(fileSubject);
        file.createNewFile();
        var input = new Scanner(file);
        while (input.hasNextLine()) {
            var data = "*".split(input.nextLine());
            var subjetc = readElementSubFromFile(data);
            subjects.add(subjetc);
        }
        input.close();
        if (subjects.size() > 0) {
            return subjects;
        } else {
            return null;
        }
    }

    /**
     * phương thức đọc từng thuộc tính file subjetc
     *
     * @param data mảng chứa thuộc tính
     * @return đối tượng mới đọc ra từ file
     */
    private static Subject readElementSubFromFile(String[] data) {
        var id = data[0];
        var name = data[1];
        var credit = Integer.parseInt(data[2]);
        var numOfLesson = Integer.parseInt(data[3]);
        var numOfExam = Integer.parseInt(data[4]);
        return new Subject(id, name, credit, numOfLesson, numOfExam);
    }
}
