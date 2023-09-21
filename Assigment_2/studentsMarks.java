import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    
    public studentsMarks(String studnetName,String studentId,String unitName,List<Integer>marks){
        this.studentName = studentName;
        this.studentId = studentId;
        this.unitName = unitName;
        this.marks = marks;
    }
    
    
    
    public static void main(String[]args){
       Scanner myReader = new Scanner(System.in);
       System.out.println("Please enter your file name :");
       String myFile = myReader.nextLine();
       
       List<studentsMarks>students = readMarks(myFile);
       
       //Print the data
       for(studentsMarks student :students){
           
       }
    }


//Function No 1 read the marks from the given file
public static List<studentsMarks>readMarks(String myFile){
    List<studentsMarks> students = new ArrayList<>();
    try{
        File file = new File(myFile);
        Scanner input = new Scanner(myFile);
        
        String studentName = null;
        String studentId = null;
        String unitName = null;
        List<Integer> marks = new ArrayList<>();
        
        while(input.hasNextLine()){
            String lines = input.nextLine();
            
            //check comment lines 
            if(lines.startsWith("#")){
                continue;
            }
            
           String[] sections = lines.split(",");
                if (sections.length == 4) {
                    unitName = sections[0].trim();
                    studentName = sections[1].trim();
                    studentId = sections[2].trim();
                    marks = new ArrayList<>();
                    String[] marksArray = sections[3].trim().split(" ");
                    for (String mark : marksArray) {
                        marks.add(Integer.parseInt(mark));
                    } 
                     students.add(new studentsMarks(unitName, studentName, studentId, marks));
        }
    }
        input.close();
    } //catch(FileNotFoundException e){
                //System.out.println("File doesnot found");
                //System.exit(1);
           // }
    return students;
}


        


}