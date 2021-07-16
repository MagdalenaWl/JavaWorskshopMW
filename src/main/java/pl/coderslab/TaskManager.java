package pl.coderslab;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        String fileName = "tasks.csv";
        manager(fileName);


    }

    // Zarządzanie programem -------------------------------------------------

    public static void manager(String fileName) {
        String[] tasks = tasks("tasks.csv");
        String option = new String();
        Scanner scan = new Scanner(System.in);


        while (true) {
            selectOptions();
            option = scan.next();
            if (option.equals("add")) {
                tasks = add(tasks);
            } else if (option.equals("remove")) {
                tasks = remove(tasks);
            } else if (option.equals("list")) {
                list(tasks);
            } else if (option.equals("exit")) {
                exit(tasks, fileName);
                break;
            } else {
                System.out.println(ConsoleColors.YELLOW + "Wrong option" + ConsoleColors.RESET);
            }


        }


    }

    // Koniec programu -------------------------------------------------------

    public static void exit(String[] tasks, String fileName) {
        System.out.println(ConsoleColors.PURPLE_BRIGHT + "Bye, bye");
        saveFile(tasks, fileName);
    }

    // Zapisywanie danych do pliku -------------------------------------------

    public static void saveFile(String tasks[], String fileName) {
        Path path = Paths.get(fileName);
        try {
            cleanFile(fileName);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            for (String task : tasks) {
                Files.writeString(path, task + "\n", StandardOpenOption.APPEND);

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void cleanFile(String fileName) throws FileNotFoundException {
        PrintWriter cleaner = new PrintWriter(fileName);
        cleaner.print("");
        cleaner.close();

    }
    // Usuwanie danych z listy -----------------------------------------------

    public static String[] remove(String[] tasks) {
        int remove = pickNumber(tasks);
        tasks = deleteCell(tasks, remove);

        return tasks;
    }

    public static String[] deleteCell(String[] tasks, int remove) {

        String[] delete = new String[tasks.length - 1];
        for (int i = 0; i < remove; i++) {
            delete[i] = tasks[i];
        }
        for (int i = remove; i < delete.length; i++) {
            delete[i] = tasks[i + 1];
        }

        return delete;
    }

    public static int pickNumber(String[] tasks) {
        int remove = -1;
        Scanner scan = new Scanner(System.in);

        System.out.println(ConsoleColors.RED_BOLD + "Please select number to remove (0-" + (tasks.length - 1) + ")");


        while (true) {
            if (!scan.hasNextInt()) {
                scan.next();
                System.out.println("Incorrect data. Please select number to remove (0-" + (tasks.length - 1) + ")");

            } else {
                remove = scan.nextInt();
                if (remove < 0 || remove >= tasks.length) {
                    System.out.println("Wrong number. Please select number to remove (0-" + (tasks.length - 1) + ")");

                } else {
                    break;
                }
            }
        }

        System.out.println(ConsoleColors.RESET);


        return remove;
    }

    // Dodawanie danych do listy ---------------------------------------------

    public static String[] add(String[] tasks) {
        String task = newTask();
        tasks = addToArray(tasks, task);

        return tasks;
    }

    public static String newTask() {
        StringBuilder task = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Please add task description:");
        task.append(scan.nextLine());
        System.out.println("Please add task due date:");
        task.append(", " + scan.nextLine());
        System.out.println("Is your task important? (true/false): ");
        task.append(", " + scan.nextLine());
        System.out.println(ConsoleColors.RESET); //@TODO kontrola wpisywanych danych

        return task.toString();
    }

    public static String[] addToArray(String[] tasks, String task) {
        String[] copy = Arrays.copyOf(tasks, tasks.length + 1);
        copy[copy.length - 1] = task;
        return copy;
    }

    // Wyświetlanie danych z pliku -------------------------------------------

    public static void list(String[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(i + " : " + tasks[i]);
        }
    }

    // Wczytywanie danych z pliku --------------------------------------------

    public static String[] tasks(String fileName) {
        String[] strings = new String[lines(fileName)];
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            for (int i = 0; i < strings.length; i++) {
                strings[i] = scan.nextLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return strings;
    }

    public static int lines(String fileName) {
        int counter = 0;
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {

                counter++;
                scan.nextLine();
            }
        } catch (IOException ex) {
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
