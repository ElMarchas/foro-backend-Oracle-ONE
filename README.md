# Foro Backend reto ORACLE ONE 6

Este repositorio es un intento de resolver el [desafío foro backend](https://www.aluracursos.com/challenges/back-end/foro-alura) propuesto en alura. 

Tenemos un periodo de tiempo de dos semanas para desarrollar el proyecto **(del 22 de Junio 2024 al 16 de Julio 2024)** 

Desarrollaremos el crud para los topicos solamente.

## Requisitos 
### Base de datos

La aplicación contará con una base de datos en mysql local con 4 tablas diferentes.

- Topico [id, titulo, mensaje, fechaCreación, estatus, autor, curso, respuestas]

- Respuesta [id, mensaje, topico, fechaCreación, autor, solución]

- Curso [id, nombre, categoria]

- Usuario [id, nombre, correoElectronico, contraseña, perfil]

### CRUD Topicos 

- Crear topicos nuevos, verificamos que todos los campos tengan datos validos.

- Leer todos los topicos en lista.

- Leer un topico en concreto mediante su id.

- Actualizar un topico mediante su id, solo se podra modificar el titulo y el mensaje. Los demas datos no deben ser modificados.

- Eliminar logicamente un topico mediante su id, el topico se mantendrá en la base de datos pero ya no será indexado con los demas topicos.

### Seguridad

- Autentificacion con Spring Security sin sesion.

- Generacion y autentificacion con token JWT