package org.example.service;

import org.example.model.Task;
import org.example.util.PrintTask;

import java.io.*;
import java.util.*;

public class TaskServices {

    public List<Task> listTask;
    JsonManager jsonManager = new JsonManager();
    PrintTask printTask = new PrintTask();

    public TaskServices(){
        listTask = jsonManager.loadTasksFromJson();
    }
    //Leer todas las tareas..
    public void listAllTask(){
        listTask = jsonManager.loadTasksFromJson();
        printTask.printTaskList(listTask);
    }

    //Obtener tarea por su id...
    public Task getByIdTask(int id) {
      for (Task task :listTask){
          if (task.getId() == id) return task;
      }
      return null;
    }

    public void showTask(int id){
        printTask.printTask(getByIdTask(id));
    }

    //Agregar nueva tarea...
    public void addTask(String descrip){
        Task task;
        int id = generateId();
        if (!descrip.isEmpty()){
            task = new Task(id, descrip, "TODO");
            listTask.add(task);
            jsonManager.saveTasksToJson(listTask);
            System.out.println("Tarea registrada");
        }else {
            System.out.println("Error al registrar la tarea");
        }

    }

    //Edictar tarea..
    public void updateTask(int id, String descrip){
        Task taskUp = getByIdTask(id);
        if (taskUp != null){
            taskUp.setDescription(descrip);
            taskUp.setUpdateDT(taskUp.updateDataTime());
            jsonManager.saveTasksToJson(listTask);
            System.out.println("Tarea actualizada..");
        }else {
            System.out.println("Error al actualizar tarea");
        }
    }

    //Eliminar tarea.
    public void deleteTask(int id) {
        Task taskUp = getByIdTask(id);
        if (taskUp != null){
            listTask.remove(taskUp);
            jsonManager.saveTasksToJson(listTask);
            System.out.println("Tarea eliminada");
        } else {
        System.out.println("Error al elinimar tarea");
        }
    }

    public void updateStatus(int id, String status) {
        Task taskUp = getByIdTask(id);
        if (taskUp != null){
            taskUp.setStatus(status);
            taskUp.setUpdateDT(taskUp.updateDataTime());
            jsonManager.saveTasksToJson(listTask);
            System.out.println("Tarea estado actualizado");
        }else {
            System.out.println("Error al actualizar tarea");
        }
    }

    public void getByStatus(String status){
        if (status.isEmpty()) {
            System.out.println("Error a encontar las tareas");
            return;
        }
        List<Task> listStatus = new ArrayList<>();
        for (Task task : listTask) {
            if (task.getStatus().equalsIgnoreCase(status)) listStatus.add(task);
        }
        printTask.printTaskList(listStatus);
    }

    public int generateId(){
        Random random = new Random();
        int id;
        do {
            id = 1000 + random.nextInt(9000);
        }while (getByIdTask(id) != null);
        return id;
    }

}
