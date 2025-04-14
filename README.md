#url del proyecto: Roadmap.com
- https://roadmap.sh/projects/task-tracker

# Task Tracker CLI

Este es un proyecto de seguimiento de tareas implementado en Java. Permite a los usuarios agregar, actualizar, eliminar y listar tareas a través de la consola. La aplicación maneja las tareas almacenándolas en un archivo JSON, lo que permite persistencia entre sesiones.

## ✨ Características

- **Agregar tareas**: Permite agregar tareas con una descripción y un estado inicial.
- **Actualizar tareas**: Se pueden actualizar las descripciones de las tareas existentes.
- **Eliminar tareas**: Se puede eliminar tareas por su ID.
- **Cambiar el estado de las tareas**: Puedes marcar las tareas como "In-Progress" o "Done".
- **Listar tareas**: Puedes listar todas las tareas, tareas por estado o tareas específicas por su ID.
- **Interfaz de línea de comandos (CLI)**: Interactúa con el sistema mediante comandos escritos en la consola.

## 📋 Requisitos

- JDK 8 o superior.
- Un editor de texto o IDE compatible con Java (Ej: IntelliJ IDEA, Eclipse).

## ⚙️ Instalación

1. Clona este repositorio o descarga los archivos.
   ```bash
   git clone https://github.com/tu-usuario/task-tracker-cli.git

Abre el proyecto en tu IDE o editor de texto preferido.

Asegúrate de tener el JDK 8 o superior instalado en tu máquina.

Ejecuta el archivo Main.java para iniciar la aplicación.

## 🧑‍💻 Uso
Para interactuar con el sistema, simplemente ejecuta el programa y usa los siguientes comandos:

## 📖 Comandos Disponibles
- add [descripción]: Agrega una nueva tarea con la descripción proporcionada.
   Ejemplo : add "Comprar pan"

- update [id] [descripción]: Actualiza la descripción de una tarea existente identificada por su ID.
- Ejemplo : update 1 "Comprar pan y leche"

- delete [id]: Elimina la tarea con el ID proporcionado.
- Ejmplo : delete 1

- mark-in-progress [id]: Marca una tarea como In-Progress.
- Ejemplo : mark-in-progress 2

- mark-done [id]: Marca una tarea como Done.
- Ejemplo : mark-done 3

- list: Muestra todas las tareas.
- list-id [id]: Muestra una tarea específica por su ID.
- list-in-progress: Muestra todas las tareas con el estado In-Progress.
- list-done: Muestra todas las tareas con el estado Done.
- exit: Salir de la aplicación.
- help: Muestra la lista de todos los comandos disponibles.

## Persistencia de Datos
Las tareas se guardan en un archivo tasks.json en el mismo directorio del proyecto. Este archivo es cargado y actualizado cada vez que se agrega, modifica o elimina una tarea.

## Contribuciones
Las contribuciones son bienvenidas. Si deseas mejorar este proyecto o agregar nuevas características, puedes hacer un fork y enviar un pull request.
