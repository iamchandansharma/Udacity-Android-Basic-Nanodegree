import java.util.ArrayList;
import java.util.Scanner;

class StudentData {

    @Override
    public String toString() {
        return "StudentData{" +
                "schoolName='" + schoolName + '\'' +
                ", yearOfPassing=" + yearOfPassing +
                ", passingMarks=" + passingMarks +
                ", totalMarks=" + totalMarks +
                ", standard=" + standard +
                ", rollNumber=" + rollNumber +
                ", totalMarksObtain=" + totalMarksObtain +
                ", nameOfStudent='" + nameOfStudent + '\'' +
                ", percentageMarks=" + percentageMarks +
                ", fatherNameOfStudent='" + fatherNameOfStudent + '\'' +
                ", subjectName=" + subjectName +
                ", subjectMarks=" + subjectMarks +
                '}';
    }

    //constants whose valude never modified
    final private String schoolName = "K.V. International School";
    final private int yearOfPassing = 2016;
    final private int passingMarks = 35;
    final private int totalMarks = 100;
    final private char standard = 'X';

    //Private data member for class which can be accessed outside the class through getter method

    private int rollNumber;
    private int totalMarksObtain;
    private String nameOfStudent;
    private double percentageMarks;
    private String fatherNameOfStudent;
    private ArrayList<String> subjectName = new ArrayList<>();
    private ArrayList<Integer> subjectMarks = new ArrayList<>();

    //Constructor to instantiates each variable in the class
    public StudentData(int rollNumber, int totalMarksObtain, String nameOfStudent, double percentageMarks, String fatherNameOfStudent, ArrayList<String> subjectName, ArrayList<Integer> subjectMarks) {
        this.rollNumber = rollNumber;
        this.totalMarksObtain = totalMarksObtain;
        this.nameOfStudent = nameOfStudent;
        this.percentageMarks = percentageMarks;
        this.fatherNameOfStudent = fatherNameOfStudent;
        this.subjectName = subjectName;
        this.subjectMarks = subjectMarks;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public int getYearOfPassing() {
        return yearOfPassing;
    }

    public int getPassingMarks() {
        return passingMarks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public char getStandard() {
        return standard;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public int getTotalMarksObtain() {
        return totalMarksObtain;
    }

    public String getNameOfStudent() {
        return nameOfStudent;
    }

    public double getPercentageMarks() {
        return percentageMarks;
    }

    public String getFatherNameOfStudent() {
        return fatherNameOfStudent;
    }

    public ArrayList<String> getSubjectName() {
        return subjectName;
    }

    public ArrayList<Integer> getSubjectMarks() {
        return subjectMarks;
    }
}

// Main Class
public class ReportCard {
    //Main Method
    public static void main(String[] args) {

        int rollNumber;
        int totalMarksObtain = 0;
        String nameOfStudent;
        double percentageMarks;
        String fatherNameOfStudent;
        ArrayList<String> subjectName = new ArrayList<>();
        ArrayList<Integer> subjectMarks = new ArrayList<>();
        boolean resultStatus = false;

        Scanner inputDataFromConsole = new Scanner(System.in);

        System.out.println("Enter Student Information For ReportCrd:");
        System.out.println("Enter Student Name: ");
        nameOfStudent = inputDataFromConsole.nextLine();

        System.out.println("Enter Student's Father Name: ");
        fatherNameOfStudent = inputDataFromConsole.nextLine();

        System.out.println("Enter Student's RollNumbr: ");
        rollNumber = inputDataFromConsole.nextInt();

        System.out.println("How many Subject have in student syallbus");
        int subjectCounter = inputDataFromConsole.nextInt();

        for (int index = 0; index < subjectCounter; index++) {
            System.out.println("Enter " + (index + 1) + " Subjects Details : ");

            System.out.println("Enter " + (index + 1) + " Subject Name : ");
            String subject = inputDataFromConsole.next();

            System.out.println("Enter Marks Obtained in " + subject + " Subject : ");
            int marksObtain = inputDataFromConsole.nextInt();
            totalMarksObtain += marksObtain;

            subjectName.add(subject);
            subjectMarks.add(marksObtain);
        }

        percentageMarks = (totalMarksObtain * 100) / (subjectName.size() * 100);
        StudentData studentOne = new StudentData(rollNumber,totalMarksObtain,nameOfStudent,percentageMarks,fatherNameOfStudent,subjectName,subjectMarks);

        /**Report Card Generator on Display Code
            For better human readable form without using to string method
         */

        System.out.println("\n\n\n\t\t\t\t..........Report Card.........");
        System.out.println("\n\n\t\t\t\t\t\t " + studentOne.getSchoolName());
        System.out.print("\n\n Roll No : " + studentOne.getRollNumber());
        System.out.println("\t\t\t\t\t\t\t\t Year Of Passing : " + studentOne.getYearOfPassing());
        System.out.print("\n Student Name : " + studentOne.getNameOfStudent());
        System.out.println("\t\t\t\t Class : " + studentOne.getStandard());
        System.out.println("\n Father Name : " + studentOne.getFatherNameOfStudent());

        System.out.println("\n\n Subjects\t \t\t PassingMarks \t\t\t MarksObtained");
        for (int index = 0; index < studentOne.getSubjectName().size(); index++)
            System.out.println("\n\n " + studentOne.getSubjectName().get(index) + "\t\t\t\t\t" + studentOne.getPassingMarks() + "\t\t\t\t\t" + studentOne.getSubjectMarks().get(index));

        System.out.println("\n\n Total Marks : " + studentOne.getTotalMarksObtain() + "\t\t\t\t\t\t Percentage Marks : " + studentOne.getPercentageMarks());

        for (int index = 0; index < studentOne.getSubjectMarks().size(); index++) {
            if (studentOne.getSubjectMarks().get(index) < studentOne.getPassingMarks()) {
                resultStatus = false;
                break;
            }
            else
                resultStatus = true;
        }
        if (resultStatus)
            System.out.println("\n\n\t\t\t\t Congratulation You Pass This Exam !!!!!!!");
        else
            System.out.println("\n\n\t\t\t\t Sorry You can't Passed This Exam  !!!!!!!");

        /**Report Card Generator on Display Code
         For better human readable form with using to string method
         */

        System.out.print(studentOne.toString());
    }
}

