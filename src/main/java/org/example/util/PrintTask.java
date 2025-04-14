package org.example.util;

import org.example.model.Task;

import java.util.List;

public class PrintTask {

    private static final String FORMAT = "| %-5s | %-40s | %-12s | %-16s | %-16s |\n";
    private static final String BORDER = "+-------+------------------------------------------+--------------+------------------+------------------+";
    private static final String HEADER = "| ID    | Description                              | Status       | Created Time     | Updated Time     |";

    // Colores ANSI
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";


    public void printTaskList(List<Task> tasks){

        if (!tasks.isEmpty()){
            HEADER_PRINT("LISTA DE TAREAS");
            tasks.forEach(task -> System.out.printf(
                    FORMAT,
                    task.getId(),
                    truncate(task.getDescription(), 40),
                    colorStatus(task.getStatus().toUpperCase()),
                    formatDateTime(task.getCreateDT()),
                    formatDateTime(task.getUpdateDT()))
            );
            System.out.println(BORDER);
        }else {
            ERROR_PRINT("LISTA DE TAREAS", "LISTAS DE TAREA BASIA : usa add para agregar nueva tarea...");
        }
    }

    public void printTask(Task task){
        if (!(task == null)){
            HEADER_PRINT("TASK");
            System.out.printf(
                    FORMAT,
                    task.getId(),
                    truncate(task.getDescription(), 40),
                    colorStatus(task.getStatus().toUpperCase()),
                    formatDateTime(task.getCreateDT()),
                    formatDateTime(task.getUpdateDT()));
            System.out.println(BORDER);
        }else {
            ERROR_PRINT("TASK", "TAREA NO ENCONTRADA : usa add para agregar nueva tarea...");
        }
    }

    private void HEADER_PRINT(String titel){
        System.out.println(titel);
        System.out.println(BORDER);
        System.out.println(HEADER);
        System.out.println(BORDER);
    }

    private void ERROR_PRINT(String titel, String msg){
        System.out.println(titel);
        System.out.println(BORDER);
        System.out.println(HEADER);
        System.out.println(BORDER);
        System.out.println(msg);
        System.out.println(BORDER);

    }

    private String truncate(String text, int maxLength){
        return text.length() <= maxLength ? text : text.substring(0, maxLength - 3) + "...";
    }

    private String formatDateTime(String datetime) {
        if (datetime == null || datetime.isBlank()) return "N/A";
        return datetime.length() >= 16 ? datetime.substring(0, 16).replace("T", " ") : datetime;
    }

    private String colorStatus(String status) {
        return switch (status.toUpperCase()) {
            case "DONE" -> ANSI_GREEN + status + ANSI_RESET;
            case "IN_PROGRESS" -> ANSI_YELLOW + status + ANSI_RESET;
            case "TODO" -> ANSI_RED + status + ANSI_RESET;
            default -> status;
        };
    }
}
