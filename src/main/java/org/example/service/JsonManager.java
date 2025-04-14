package org.example.service;

import org.example.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {

    private static final String FILE_NAME = "tasks.json";

    //Metodos oara guardar y cargar e Json.
    public List<Task> loadTasksFromJson() {
        List<Task> listTask = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }

            String json = jsonBuilder.toString();
            // quitar los corchetes [ ]
            if (json.startsWith("[") && json.endsWith("]")) {
                json = json.substring(1, json.length() - 1);
            }

            // separar los objetos por "}, {", luego agregamos las llaves de nuevo
            String[] taskJsons = json.split("},\\s*\\{");

            for (String taskJson : taskJsons) {
                taskJson = taskJson.trim();
                if (!taskJson.startsWith("{")) taskJson = "{" + taskJson;
                if (!taskJson.endsWith("}")) taskJson = taskJson + "}";

                Task task = Task.fromJson(taskJson);
                listTask.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar tareas: " + e.getMessage());
        }
        return listTask;
    }


    public void saveTasksToJson(List<Task> listTask) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("[\n");

            for (int i = 0; i < listTask.size(); i++) {
                Task task = listTask.get(i);
                writer.write("  " + task.toJson());

                if (i < listTask.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("Error al guardar las tareas: " + e.getMessage());
        }
    }

}
