# Task CLI (Gestor de Tareas en Consola)

## Descripción

Task-CLI es una aplicación de consola escrita en Java que permite administrar tareas mediante comandos.
Permite agregar, listar, actualizar, eliminar y marcar tareas como *done* o *in-progress*.
Todas las tareas se almacenan en un archivo JSON, garantizando persistencia entre ejecuciones.
Proyecto sacado : https://roadmap.sh/projects/task-tracker

Este proyecto es útil para practicar:

* Programación orientada a objetos (POO)
* Manejo y persistencia de datos en JSON
* Diseño de interfaces de línea de comandos
* Validación de entrada y manejo de colecciones

---

## Requisitos previos

* Java 17 o superior
* Apache Maven 3.8 o superior

---

## Estructura del proyecto

```
/src
 └── main
      └── java
           └── cli
                ├── TareaCLI.java
                ├── AdministrarTarea.java
                ├── Tarea.java
```

Archivo JSON generado automáticamente:

```
tareas.json
```

---

## Cómo compilar y ejecutar

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar con Maven

```bash
mvn exec:java -Dexec.mainClass="cli.TareaCLI"
```

---

## Empaquetar como JAR

### Generar el JAR

```bash
mvn clean package
```

Archivo generado:

```
target/TaskTrakerCLI-1.0-SNAPSHOT.jar
```

### Ejecutar el JAR

```bash
java -jar target/TaskTrakerCLI-1.0-SNAPSHOT.jar
```

---

## Uso del CLI

Al iniciar la aplicación se mostrará:

```
task-cli
```

---

## Comandos disponibles

### Agregar una tarea

```
agregar <descripción>
```

### Listar tareas

```
listar
listar todo
listar in-progress
listar done
```

### Actualizar una tarea

```
actualizar <id> <nueva descripción>
```

### Eliminar una tarea

```
eliminar <id>
```

### Marcar tarea como done

```
marcar-done <id>
```

### Marcar tarea como in-progress

```
marcar-in-progress <id>
```

### Mostrar ayuda

```
ayuda
```

### Guardar y salir

```
salir
```

---

## Persistencia en JSON

Al usar el comando `salir`, las tareas se guardan en:

```
tareas.json
```

Ejemplo:

```json
{
  "id": 3,
  "descripcion": "Hacer el informe",
  "estado": "done",
  "creadoEn": "2025-01-10",
  "actualizadoEn": "2025-01-11"
}
```

---

## Ejemplo de sesión

```
task-cli
agregar Estudiar Java
agregar Preparar presentación
listar
marcar-done 1
actualizar 2 Revisar presentación final
eliminar 1
listar done
salir
```

---

## Tecnologías usadas

* Java 17 o superior
* Apache Maven
* JSON para persistencia
* Programación orientada a objetos

---

## Autor

Proyecto desarrollado como práctica de Java CLI y persistencia de datos usando JSON.

---