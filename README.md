# Task CLI (Gestor de Tareas en Consola)

## Descripción

Task-CLI es una aplicación de consola escrita en Java que permite administrar tareas mediante comandos.
Permite agregar, listar, actualizar, eliminar y marcar tareas como *done* o *in-progress*.
Todas las tareas se almacenan en un archivo JSON, garantizando persistencia entre ejecuciones.

Este proyecto es ideal para practicar:

* Programación orientada a objetos (POO)
* Manejo de archivos JSON
* Diseño de interfaces de línea de comandos
* Validación de entrada y manejo de listas

---

## Características principales

* Agregar tareas con descripción
* Listar tareas con o sin filtro
* Actualizar descripción por ID
* Marcar tareas como done
* Marcar tareas como in-progress
* Eliminar tareas por ID
* Persistencia automática en JSON
* CLI interactiva basada en comandos

---

## Estructura del proyecto

```
/src
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

## Cómo ejecutar el programa

Compilar:

```bash
javac -d out src/cli/*.java
```

Ejecutar:

```bash
java -cp out cli.TareaCLI
```

---

## Uso del CLI

Al iniciar la aplicación verás:

```
task-cli
```

A partir de ahí puedes usar los comandos que se describen a continuación.

---

## Comandos disponibles

### Agregar una tarea

```
agregar <descripción>
```

Ejemplo:

```
agregar Terminar proyecto de Java
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

Ejemplo:

```
actualizar 3 Hacer tarea de cálculo
```

### Eliminar una tarea

```
eliminar <id>
```

Ejemplo:

```
eliminar 5
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

Ejemplo de contenido:

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
* Manejo de archivos JSON
* Programación orientada a objetos

---

## Autor

Proyecto desarrollado como práctica de Java CLI y manipulación de datos en archivos.

---