import java.io.*;
import java.util.*;

/**
 * @author (M.G.Gimhani Kavishika)
 * @Student ID (24097251)
 * @Date (18/9/2023)
 * @Description - Assigment 2 for PROG5001-Fundamentals of Programming. Developed a simple program to compute 
 * statistics of 'students' marks in this assignment.
 */

public class Student{
    private static String unitName = "";
    
     public static void main(String[] args) {
        Scanner myReader = new Scanner(System.in);
        List<Student> students = null; // Initialize students to null
        boolean totalCalculated = false;
       
       while (true) {
            System.out.println("\n Student Marks Mangement System Menu:");
            System.out.println("1. Read Student Data from File :");
            System.out.println("2. Calculate Total Marks for Students : )");
            System.out.println("3. Print Students Below a Threshold :");
            System.out.println("4. Print Top 5 Students with Highest and Lowest Total Marks : ");
            System.out.println("5. Exit from the System: ");

            System.out.print("Enter your choice (1-5): ");
            int choice = myReader.nextInt();
            myReader.nextLine(); // Consume newline
            
             if (choice == 1) {
                System.out.print("Enter the filename: ");
                String filename = myReader.nextLine();
                students = readMarks(filename);
                System.out.println("Student data has been loaded.");
                totalCalculated = false;
            } else if (choice == 2) {
                if (students != null) {
                    students = calculateTotalMarksForStudents(students);
                    System.out.println("Total marks have been calculated for students.");
                    totalCalculated = true;
                    printStudents(students);
                    } else {
                    System.out.println("Please load student data first.");
                }
            } else if (choice == 3) {
                if (students != null) {
                    if (totalCalculated) {
                    System.out.print("Enter the threshold for total marks: ");
                    int threshold = myReader.nextInt();
                    printStudentsBelowThreshold(students, threshold);
                } else {
                    System.out.println("Please calculate total marks first.");
                }
            } else {
                    System.out.println("Please load student data first.");
            }
        } else if (choice == 4) {
                if (students != null) {
                    if (totalCalculated) {
                        // in the resultant array first 5 students will have maximum marks and last 5 students will have minimum marks
                        List<Student> sortedStudents = sortStudents(students);
                        System.out.println("Top 5 students with the Top marks:");
                        printTopStudents(sortedStudents);
                        System.out.println("Top 5 students with the Lowest total marks:");
                        printLowestStudents(sortedStudents);
               } else {
                        System.out.println("Please calculate total marks first (F2).");
                    }
                } else {
                    System.out.println("Please load student data first (F1).");
                }
            } else if (choice == 5) {
                System.out.println("Exiting the program. Thank You!");
                System.exit(0);
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
    
    private static void printData(List<Student> students) {//Print the data
       for(Student student :students){
           System.out.println("Student Name : " +student.firstName +""+student.lastName);
           System.out.println("Student ID Number : " +student.studentId);
           System.out.println("Unit Name : " +student.unitName);
           System.out.println("Assigment Marks : " +student.marks);
           System.out.println("Total Marks : " +student.totalMarks);
       }
    }


// Function No 1 read the marks from the given file
public static List<Student>readMarks(String myFile){
    List<Student> students = new ArrayList<>();
    
   try {
            File file = new File(myFile);
            Scanner input = new Scanner(file);

            String unitName = null;
            String studentName = null;
            String studentId = null;
            List<Integer> marks = new ArrayList<>();

            while (input.hasNextLine()) {
                String line = input.nextLine();

                // Check for comments starting with '#' in the code
                if (line.startsWith("#")) {
                    continue; 
                }

                // Using the ',' , divide the lines into sections.
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    // Parse unit name
                    unitName = parts[0].trim();

                    // Parse student name
                    studentName = parts[1].trim();

                    // Parse student ID
                    studentId = parts[2].trim();

                    // Parse marks for three assignments
                    String[] marksArray = parts[3].trim().split(" ");
                   // marks.clear(); // Clear previous marks
                    for (String mark : marksArray) {
                        marks.add(Integer.parseInt(mark));
                    }

                    // Create a new Student object and add it to the list
                    students.add(new Student(unitName, studentName, studentId, new ArrayList<>(marks)));
                }
                input.close();
            }
        }catch(FileNotFoundException e){
                System.out.println("File doesnot found");
            }
           return students;
}



// Function No 3 Below threshold mark
private static List<Student> findStudentsBelowThreshold(List<Student> students, int thresholdMark) {
        // Step 1: Algorithm 3 in pseudocode
        List<Student> belowThresholdStudents = new ArrayList<>();
        for (Student student : students) {
            // Calculate the total marks for the student
            int totalMarks = student.getTotalMarks();

            // Check if the total marks are less than the threshold
            if (totalMarks < thresholdMark) {
                // Add the student to the list of below-threshold students
                belowThresholdStudents.add(student);
            }
        }
        return belowThresholdStudents;
    }
    
    // Function No 4 Highest Marks top 5 students
    // rewrite this with sort method
    //sort assendi and desending
    private static List<Student> highestMarksTopStudents(List<Student> students){
        List<Student> highestMarksStudents = new ArrayList<>();
        for (int i = 0; i < 5 && !students.isEmpty(); i++) {
            Student highestStudent = null;
            for (Student student : students) {
                if (highestStudent == null || student.getTotalMarks() > highestStudent.getTotalMarks()) {
                    highestStudent = student;
                }
            }
            highestMarksStudents.add(highestStudent);
            students.remove(highestStudent);
        }
        return highestMarksStudents;
    }
    
//have to check lowest 5da enne kyla,
    private static List<Student> findLowwestMarksStudents(List<Student> students) {
        List<Student> lowwestMarksStudents = new ArrayList<>();
        for (int i = 0; i < 5 && !students.isEmpty(); i++) {
            Student lowwestStudent = null;
            for (Student student : students) {
                if (lowwestStudent == null || student.getTotalMarks() < lowwestStudent.getTotalMarks()) {
                    lowwestStudent = student;
                }
            }
            lowwestMarksStudents.add(lowwestStudent);
            students.remove(lowwestStudent);
        }
        return lowwestMarksStudents;
    }
}