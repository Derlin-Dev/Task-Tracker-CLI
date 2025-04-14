package org.example;

import org.example.model.Task;
import org.example.service.TaskServices;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Main {

    public static TaskServices taskService = new TaskServices();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        out.println("TASK TRACKER CLI >");

        while (true) {
            String input = scanner.nextLine().trim();
            Integer id;

            if (input.isEmpty()) continue;

            String[] partes = input.split(" ", 2);
            String comando = partes[0].toLowerCase();
            String argumentos = partes.length > 1 ? partes[1].trim() : "";
            id = parseId(argumentos);
            switch (comando){
                case "add" :
                    taskService.addTask(argumentos);
                    break;
                case "update" :
                    handleUpdate(argumentos);
                    break;
                case "delete" :
                    if (id != null) {
                        taskService.deleteTask(id);
                    }else {
                        out.println("Ingrese el comando correctamente / ejemplo : delete {id}");
                    }
                    break;
                case "mark-in-progress" :
                    if (id != null){
                        taskService.updateStatus(id, "IN-PROGRESS");
                    }else {
                        out.println("Ingrese el comando correctamente / ejemplo : mark-in-progress {id}");
                    }
                    break;
                case "mark-done" :
                    if (id != null){
                        taskService.updateStatus(id, "DONE");
                    }else {
                        out.println("Ingrese el comando correctamente / ejemplo : mark-done {id}");
                    }
                    break;
                case "list" :
                    taskService.listAllTask();
                    break;
                case "list-id" :
                    if (id != null){
                        taskService.showTask(id);
                    }else {
                        out.println("Ingrese el comando correctamente / ejemplo : list-id {id}");
                    }
                    break;
                case "list-in-progress" :
                    taskService.getByStatus("IN-PROGRESS");
                    break;
                case "list-done" :
                    taskService.getByStatus("DONE");
                    break;
                case "exit" :
                    System.out.println("----Saliendo----");
                    return;
                case "help" :
                    helpMenu();
                    break;
                default:
                    System.out.println("❌ Error al procesar el comando ingrese el id" );
            }
        }
    }

    public static void helpMenu(){

        String help = """
                - add [description] : Add a new task
                - update [id] [description] : Update a task
                - delete [id] : Delete a task
                - mark-in-progress [id] : Mark a task as In-Progress
                - mark-done [id] : Mark a task as Done
                - list : List all tasks
                - list-id : List by id
                - list-in-progress : List all In-Progress tasks
                - list-done : List all Done tasks
                - exit : Exit the program
                """;
        out.println(help);

    }

    public static Integer parseId(String argumentos){
        try {
            return Integer.parseInt(argumentos);
        } catch (NumberFormatException e) {
            System.out.println("El ID debe ser un número.");
            return null;
        }
    }

    public static void handleUpdate(String args){

        Pattern pattern = Pattern.compile("^(\\d+)\\s+\"(.+)\"$");
        Matcher matcher = pattern.matcher(args);

        if (matcher.matches()){
            int id = Integer.parseInt(matcher.group(1));
            String newDesc = matcher.group(2);

            taskService.updateTask(id, newDesc);
        }else {
            System.out.println("Uso incorrecto. Ejemplo válido: update {id} \\\"nuevo contenido\\\"");
        }
    }
}