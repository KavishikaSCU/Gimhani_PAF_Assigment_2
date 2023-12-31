import java.io.*;
import java.util.*;

/**
 * @author (M.G.Gimhani Kavishika)
 * @Student ID (24097251)
 * @Date (18 / 9 / 2023)
 * @Description - Assigment 2 for PROG5001-Fundamentals of Programming. Developed a simple program to compute
 * statistics of 'students' marks in this assignment.
 */

// Define the Student class
    class Student {
        // Declaring Variables for given details
        private String firstName;
        private String lastName;
        private int studentID;
        private Float a1Marks;
        private Float a2Marks;
        private Float a3Marks;
        private Float totalMarks = 0.0F;

        // Constructor for the Student class
        public Student(String firstName, String lastName, int studentID, Float a1Marks, Float a2Marks, Float a3Marks) {
            // Initializing name as two parts,student ID and marks for tree parts
            this.firstName = firstName;
            this.lastName = lastName;
            this.studentID = studentID;
            this.a1Marks = a1Marks;
            this.a2Marks = a2Marks;
            this.a3Marks = a3Marks;
        }
        
        // Getter method for Total Marks
        public Float getTotalMarks() {
            return totalMarks;
        }
        // Setter method for Total Marks
        public void setTotalMarks(Float totalMarks) {
            this.totalMarks = totalMarks;
        }
        // Getter method for First Name
        public String getFirstName() {
            return firstName;
        }
        // Setter method for Frist Name
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        // Getter methods for Last Name
        public String getLastName() {
            return lastName;
        }
        // Setter method for Last Name
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        // Getter methods for Student ID
        public int getStudentID() {
            return studentID;
        }
        // Setter method for Student ID
        public void setStudentID(int studentID) {
            this.studentID = studentID;
        }
        // Getter methods for Aissigment 1 Marks
        public Float getA1Marks() {
            return a1Marks;
        }
        // Setter method for Assigment 1 Marks
        public void setA1Marks(Float a1Marks) {
            this.a1Marks = a1Marks;
        }
        // Getter methods for Aissigment 2 Marks
        public Float getA2Marks() {
            return a2Marks;
        }
        // Setter method for Assigment 2 Marks
        public void setA2Marks(Float a2Marks) {
            this.a2Marks = a2Marks;
        }
        // Getter methods for Aissigment 3 Marks
        public Float getA3Marks() {
            return a3Marks;
        }
        // Setter method for Assigment 3 Marks
        public void setA3Marks(Float a3Marks) {
            this.a3Marks = a3Marks;
        }
    }
    // Define the studentMarks class
public class StudentMarks {
    
    private static String unitName = "";

    public static void main(String[] args) {
        Scanner myReader = new Scanner(System.in);
        List<Student> students = null; // Initialize students to null
        boolean totalCalculated = false;
        
        // Print Menu for all implemented functions 
        while (true) {
            System.out.println("Student Marks Mangement System Menu:");
            System.out.println("1. Read Student Data from File ");
            System.out.println("2. Calculate Total Marks for Students ");
            System.out.println("3. Print Students Below a Threshold ");
            System.out.println("4. Print Top 5 Students with Highest and lowest Total Marks");
            System.out.println("5. Exit ");

            System.out.print("Please enter your selection (1-5): ");
            int selection = myReader.nextInt();
            myReader.nextLine(); // Consume newline
            // Depending on the user's selection, execute the appropriate step.
            if (selection == 1) {
                 // Read a file
                System.out.print("Enter the filename: ");
                String filename = myReader.nextLine();
                students = readStudentData(filename);
                System.out.println("Student data has been loaded.");
                totalCalculated = false;
            // Display all students with total marks
            } else if (selection == 2) {
                if (students != null) {
                    students = calculateTotalMarks(students);
                    System.out.println("Total marks have been calculated for students.");
                    totalCalculated = true;
                    printStudentsData(students);
                } else {
                    // If not user read file frist display message for read file first. 
                    System.out.println("Please load student data first !!");
                }
            // Display students below a given threshold
            } else if (selection == 3) {
                if (students != null) {
                    if (totalCalculated) {
                        System.out.print("Enter the threshold for total marks (0-100): ");
                        float threshold = myReader.nextFloat();
                        printStudentsBelowThreshold(students, threshold);
                        // If user not calculate total marks first display message to caculate total marks frist
                    } else {
                        System.out.println("Please calculate total marks first !!");
                    }
                    // If not user read file frist display message for read file first. 
                } else {
                    System.out.println("Please load student data first !!");
                }
            // Display top 5 students with the highest total marks and  lowest total marks 
            } else if (selection == 4) {
                if (students != null) {
                    if (totalCalculated) {
                        // in the resultant array first 5 students will have maximum marks and last 5 students will have minimum marks
                        List<Student> sortedStudents = sortStudents(students);
                        System.out.println("Top 5 students with the Top marks:");
                        printTopFiveStudents(sortedStudents);
                        System.out.println("Top 5 students with the Lowest total marks:");
                        printLowestFiveStudents(sortedStudents);
                    } else {
                        // If user not calculate total marks first display message to caculate total marks 
                        System.out.println("Please calculate total marks first !!");
                    }
                    // If not user read file frist display message for read file first. 
                } else {
                    System.out.println("Please load student data first !!.");
                }
                 // If the user chooses to exit, return 
            } else if (selection == 5) {
                System.out.println("Exiting from the program. Thank You!");
                System.exit(0);
                 // If the user chooses to wrong option not within 1- 5, Display message to enter valid option return immediately
            } else {
                System.out.println("Invalid selection. Please enter a valid option.");
            }
        }
    }

    // ReadStudentData method for read the data from the file
    private static List<Student> readStudentData(String filename) {
        List<Student> students = null;
        
        try {
            File file = new File(filename);
            //creating fileScanner to read file line by line
            Scanner fileScanner = new Scanner(file);

            int studentID;
            students = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                // Split the line into parts using ',' as the delimiter
                String[] parts = line.split(",", 6);
                if (parts.length == 6) {

                    // trying to parse student id (if it is not valid the line is skipped (can be a comment line)
                    try {
                        studentID = Integer.parseInt(parts[2].trim());
                    } catch (NumberFormatException e) {
                        continue;
                    }

                    // Parse marks for three assignments
                    Float a1Marks = null;
                    Float a2Marks = null;
                    Float a3Marks = null;

                    if (!parts[3].isEmpty()) {
                        a1Marks = Float.valueOf(parts[3]);
                    }
                    if (!parts[4].isEmpty()) {
                        a2Marks = Float.valueOf(parts[4]);
                    }
                    if (!parts[5].isEmpty()) {
                        a3Marks = Float.valueOf(parts[5]);
                    }
                    // Create a new Student object and add it to the list
                    students.add(new Student(parts[1].trim(), parts[0].trim(), studentID, a1Marks, a2Marks, a3Marks));
                } else if (line.contains("Unit:")) {
                    unitName = line.split(":")[1].trim(); // setting unit name
                }
            }
            // closing fileScanner after reading
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return students;
    }

    //F2 returns students list with calculated total values
    private static List<Student> calculateTotalMarks(List<Student> students) {
        for (Student student : students) {
            float total = 0.0F;
            if (student.getA1Marks() != null) { // checking if marks are available
                total += student.getA1Marks();
            }
            if (student.getA2Marks() != null) {
                total += student.getA2Marks();
            }
            if (student.getA3Marks() != null) {
                total += student.getA3Marks();
            }
            student.setTotalMarks(total);
        }
        return students;
    }
    
    // Method for print students data
    private static void printStudentsData(List<Student> students) {
        System.out.println();
        System.out.println("Unit Name: " + unitName);
        System.out.println();
        // Call printToColomns method here to print table
        printToColumns("Last Name", "First Name", "Student ID", "A1", "A2", "A3", "Total Marks");
        for (Student student : students) {
            // getting each assignment marks
            Float a1Marks = student.getA1Marks();
            Float a2Marks = student.getA2Marks();
            Float a3Marks = student.getA3Marks();

            //initializing marks String values as empty strings
            String a1St = "";
            String a2St = "";
            String a3St = "";

            // checking if the values are null before converting it to string
            if (a1Marks != null) {
                a1St = String.valueOf(a1Marks);
            }
            if (a2Marks != null) {
                a2St = String.valueOf(a2Marks);
            }
            if (a3Marks != null) {
                a3St = String.valueOf(a3Marks);
            }
            printToColumns(student.getLastName(), student.getFirstName(), String.valueOf(student.getStudentID()), a1St, a2St, a3St, String.valueOf(student.getTotalMarks()));
        }
    }

    // this method will print passed values on the table columns
    private static void printToColumns(String col1, String col2, String col3, String col4, String col5, String col6, String col7) {
        Formatter formatter = new Formatter();
        formatter.format("%30s %15s %15s %15s %15s %15s %15s\n", col1, col2, col3, col4, col5, col6, col7);
        System.out.println(formatter);
    }
    // This method for print students with total marks below a given threshold
    private static void printStudentsBelowThreshold(List<Student> students, float threshold) {
        // creating new list with students below threshold marks
        List<Student> studentsBelowThreshold = new ArrayList<>();
        // Loop through every student within the list
        for (Student student : students) {
            // checking student's total mark is below the threshold
            if (student.getTotalMarks() < threshold) {
                studentsBelowThreshold.add(student);
            }
        }
        // Print the students data using printStudentData method
        printStudentsData(studentsBelowThreshold);
    }

    private static List<Student> sortStudents(List<Student> students) {
        boolean swapped = false; // variable to check if any values are swapped
        do {
            swapped = false; // setting swapped false for each cycle
            for (int i = 1; i < students.size(); i++) { // going over all students
                Student prevStudent = students.get(i - 1); // getting previous student
                Student student = students.get(i); // getting current student
                if (prevStudent.getTotalMarks() < student.getTotalMarks()) { // if previous student marks are lower than current switch them
                    students.set(i - 1, student);
                    students.set(i, prevStudent);
                    swapped = true; // setting swapped to true this will check all record for another time
                }
            }
        } while (swapped);
        return students;
    }

    // the students array must be sorted to total marks in descending order
    private static void printTopFiveStudents(List<Student> students) {
        List<Student> studentsWithTopMarks = new ArrayList<>();
        int startIndex = 5;
        // setting startIndex to the size of the list if students size is less than 5
        if (students.size() < startIndex) {
            startIndex = students.size();
        }
        for (int i = 0; i < startIndex; i++) {
            studentsWithTopMarks.add(students.get(i));
        }
        printStudentsData(studentsWithTopMarks);
    }


    // the students array must be sorted to total marks in descending order
    private static void printLowestFiveStudents(List<Student> students) {
        List<Student> studentsWithLowestMarks = new ArrayList<>();
        // setting value to get last 5 values
        int startIndex = students.size() - 5;
        // setting startIndex to the size of the list if students size is less than 5
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < students.size(); i++) {
            studentsWithLowestMarks.add(students.get(i));
        }
        printStudentsData(studentsWithLowestMarks);
    }


}
