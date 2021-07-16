package pl.coderslab;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
//        selectOptions();
//        list(tasks("tasks.csv"));
//
//
//        list(add(tasks("tasks.csv")));


    }
    // Usuwanie danych z listy -----------------------------------------------

    // Dodawanie danych do listy ---------------------------------------------

    public static String[] add(String[] tasks){
        String task=newTask();
        tasks=addToArray(tasks,task);

        return tasks;
    }

    public static String newTask(){
        StringBuilder task=new StringBuilder();
        Scanner scan = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Please add task description:");
        task.append(scan.nextLine());
        System.out.println("Please add task due date:");
        task.append(", "+ scan.nextLine());
        System.out.println("Is your task important? (true/false): ");
        task.append(", "+ scan.nextLine());
        System.out.println(ConsoleColors.RESET); //@TODO kontrola wpisywanych danych

        return task.toString();
    }

    public static String[] addToArray(String[] tasks,String task){
        String[] copy=Arrays.copyOf(tasks,tasks.length+1);
        copy[copy.length-1]=task;
        return copy;
    }

    // Wyświetlanie danych z pliku -------------------------------------------
    
    public static void list(String[] tasks){
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(i + " : " + tasks[i]);
        }
    }
    
    // Wczytywanie danych z pliku --------------------------------------------

    public static String[] tasks(String fileName){
        String[] strings = new String[lines(fileName)];
        try {
            File file=new File(fileName);
            Scanner scan=new Scanner(file);
            for (int i = 0; i < strings.length; i++) {
                strings[i]=scan.nextLine();
            }
            }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return strings;
    }
    public static int lines(String fileName){
        int counter=0;
        try {
            File file=new File(fileName);
            Scanner scan=new Scanner(file);

            while(scan.hasNextLine()){

                counter++;
                scan.nextLine();
            }}
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return counter;
    }

    // Wyświetlanie opcji  ---------------------------------------------------
    public static void selectOptions() {
        String[] options = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        for (String option : options) {
            System.out.println(ConsoleColors.RESET + option);

        }

    }
}
