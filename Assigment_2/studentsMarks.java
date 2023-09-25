import java.io.*;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * @author (M.G.Gimhani Kavishika)
 * @Student ID (24097251)
 * @Date (18/9/2023)
 * @Description - Assigment 2 for PROG5001-Fundamentals of Programming. Developed a simple program to compute 
 * statistics of 'students' marks in this assignment.
 */
public class studentsMarks{
    //studentMarks properties declared as private
    private String studentName;
    private String studentId;
    private String unitName;
    private List<Integer>marks;
    int totalMarks;
    
    public studentsMarks(String studnetName,String studentId,String unitName,List<Integer>marks){
        this.studentName = studentName;
        this.studentId = studentId;
        this.unitName = unitName;
        this.marks = marks;
        this.totalMarks = cacluateTotalMarks();
    }
    
    
    
    public static void main(String[]args){
       Scanner myReader = new Scanner(System.in);
       System.out.println("Please enter your file name :");
       String myFile = myReader.nextLine();
       
       List<studentsMarks>students = readMarks(myFile);
       
       //Get threshold mark from user
       System.out.println("Enter the threshold for total marks:");
       int thresholdMark = myReader.nextInt();
       
       List<studentsMarks>belowThresholdStudents = findStudentsBelowThreshold(students,thresholdMark);
       
       // Print students with total marks below threshold mark
       System.out.println("Students with total marks below  : " + thresholdMark + ": "); 
       //Print the data
       for(studentsMarks student :students){
           System.out.println("Student Name : " +student.studentName);
           System.out.println("Student ID Number : " +student.studentId);
           System.out.println("Unit Name : " +student.unitName);
           System.out.println("Assigment Marks : " +student.marks);
           System.out.println("Total Marks : " +student.totalMarks);
       }
    }


// Function No 1 read the marks from the given file
public static List<studentsMarks>readMarks(String myFile){
    List<studentsMarks> students = new ArrayList<>();
    
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
                    students.add(new studentsMarks(unitName, studentName, studentId, new ArrayList<>(marks)));
                }
                input.close();
            }
        }catch(FileNotFoundException e){
                System.out.println("File doesnot found");
            }
           return students;
}

// Function No 2 calculate Total Marks 
private int cacluateTotalMarks(){
    int total =0;
    for (int mark : marks){
        total += mark;
    }
    return total; 
}

public int getTotalMarks(){
    return totalMarks;
}


// Function No 3 Below threshold mark
private static List<studentsMarks> findStudentsBelowThreshold(List<studentsMarks> students, int thresholdMark) {
        // Step 1: Algorithm 3 in pseudocode
        List<studentsMarks> belowThresholdStudents = new ArrayList<>();
        for (studentsMarks student : students) {
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
}