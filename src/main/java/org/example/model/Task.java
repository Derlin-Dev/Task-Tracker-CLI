package org.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private int id;
    private String description;
    private String status;
    private String createDT;
    private String updateDT;

    public Task(){}

    public Task(int id, String description, String status ) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createDT = LocalDateTime.now().format(formatter());
        this.updateDT = LocalDateTime.now().format(formatter());
    }

    public String toJson() {
        return String.format(
                "{ \"id\": %d, \"description\": \"%s\", \"status\": \"%s\", \"createDT\": \"%s\", \"updateDT\": \"%s\" }",
                id, escape(description), status, createDT, updateDT
        );
    }

    private String escape(String str) {
        return str.replace("\"", "\\\""); // Escapar comillas dobles
    }

    public static Task fromJson(String jsonLine) {
        Task task = new Task();
        jsonLine = jsonLine.trim();

        // Elimina la coma al final si existe
        if (jsonLine.endsWith(",")) {
            jsonLine = jsonLine.substring(0, jsonLine.length() - 1);
        }

        // Elimina las llaves {}
        if (jsonLine.startsWith("{") && jsonLine.endsWith("}")) {
            jsonLine = jsonLine.substring(1, jsonLine.length() - 1);
        }

        // Divide por comas que separan atributos
        String[] fields = jsonLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // evita dividir dentro de comillas

        for (String field : fields) {
            String[] keyValue = field.split(":", 2);
            if (keyValue.length < 2) continue;

            String key = keyValue[0].trim().replace("\"", "");
            String value = keyValue[1].trim().replace("\"", "");

            switch (key) {
                case "id" -> task.setId(Integer.parseInt(value));
                case "description" -> task.setDescription(value);
                case "status" -> task.setStatus(value);
                case "createDT" -> task.setCreateDT(value);
                case "updateDT" -> task.setUpdateDT(value);
            }
        }

        return task;
    }


    public DateTimeFormatter formatter(){
        return  DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    public String updateDataTime(){
        return LocalDateTime.now().format(formatter());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getUpdateDT() {
        return updateDT;
    }

    public void setUpdateDT(String updateDT) {
        this.updateDT = updateDT;
    }

    public String getCreateDT() {
        return createDT;
    }

    public void setCreateDT(String createDT) {
        this.createDT = createDT;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Tareas{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createDT=" + createDT +
                ", updateDT=" + updateDT +
                '}';
    }
}
