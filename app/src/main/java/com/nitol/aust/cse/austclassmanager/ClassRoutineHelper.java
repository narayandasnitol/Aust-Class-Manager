package com.nitol.aust.cse.austclassmanager;


public class ClassRoutineHelper {

    String s;

    public String getRoutine(String department, String year, String semester, String section){


        if(department.equals("CSE") && year.equals("1st Year") &&
                semester.equals("1st Semester") && section.equals("Section A")){

            s = "cse_1_1_a";
        }
        else if(department.equals("CSE") && year.equals("1st Year") &&
                semester.equals("1st Semester") && section.equals("Section B")){

            s = "cse_1_1_b";
        }
        else if(department.equals("CSE") && year.equals("1st Year") &&
                semester.equals("1st Semester") && section.equals("Section C")){

            s = "cse_1_1_c";
        }
        else if(department.equals("CSE") && year.equals("1st Year") &&
                semester.equals("2nd Semester") && section.equals("Section A")){

            s = "cse_1_2_a";
        }
        else if(department.equals("CSE") && year.equals("1st Year") &&
                semester.equals("2nd Semester") && section.equals("Section B")){

            s = "cse_1_2_b";
        }
        else if(department.equals("CSE") && year.equals("2nd Year") &&
                semester.equals("1st Semester") && section.equals("Section A")){

            s = "cse_2_1_a";
        }
        else if(department.equals("CSE") && year.equals("2nd Year") &&
                semester.equals("1st Semester") && section.equals("Section B")){

            s = "cse_2_1_b";
        }
        else if(department.equals("CSE") && year.equals("2nd Year") &&
                semester.equals("1st Semester") && section.equals("Section C")){

            s = "cse_2_1_c";
        }
        else if(department.equals("CSE") && year.equals("2nd Year") &&
                semester.equals("2nd Semester") && section.equals("Section A")){

            s = "cse_2_2_a";
        }
        else if(department.equals("CSE") && year.equals("2nd Year") &&
                semester.equals("2nd Semester") && section.equals("Section B")){

            s = "cse_2_2_b";
        }
        else if(department.equals("CSE") && year.equals("3rd Year") &&
                semester.equals("1st Semester") && section.equals("Section A")){

            s = "cse_3_1_a";
        }
        else if(department.equals("CSE") && year.equals("3rd Year") &&
                semester.equals("1st Semester") && section.equals("Section B")){

            s = "cse_3_1_b";
        }
        else if(department.equals("CSE") && year.equals("3rd Year") &&
                semester.equals("1st Semester") && section.equals("Section C")){

            s = "cse_3_1_c";
        }
        else if(department.equals("CSE") && year.equals("3rd Year") &&
                semester.equals("2nd Semester") && section.equals("Section A")){

            s = "cse_3_2_a";
        }
        else if(department.equals("CSE") && year.equals("3rd Year") &&
                semester.equals("2nd Semester") && section.equals("Section B")){

            s = "cse_3_2_b";
        }
        else if(department.equals("CSE") && year.equals("4th Year") &&
                semester.equals("1st Semester") && section.equals("Section A")){

            s = "cse_4_1_a";
        }
        else if(department.equals("CSE") && year.equals("4th Year") &&
                semester.equals("1st Semester") && section.equals("Section B")){

            s = "cse_4_1_b";
        }
        else if(department.equals("CSE") && year.equals("4th Year") &&
                semester.equals("1st Semester") && section.equals("Section C")){

            s = "cse_4_1_c";
        }
        else if(department.equals("CSE") && year.equals("4th Year") &&
                semester.equals("2nd Semester") && section.equals("Section A")){

            s = "cse_4_2_a";
        }
        else if(department.equals("CSE") && year.equals("4th Year") &&
                semester.equals("2nd Semester") && section.equals("Section B")){

            s = "cse_4_2_b";
        }
        else{
            s = "nothing";
        }





        return s;
    }


}
