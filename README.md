# Sistema de Gestión Médica

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.4-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-v8.0-blue)
![HTML](https://img.shields.io/badge/HTML-5-red)
![CSS](https://img.shields.io/badge/CSS-3-blue)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6-yellow)

## Introducción

Este proyecto es un sistema de gestión médica que permite registrar, visualizar y administrar información de pacientes,
doctores y atenciones médicas. Desarrollado en dos partes, el backend maneja la lógica del servidor y la base de datos
usando Java y Spring Boot, mientras que el frontend ofrece una interfaz de usuario en HTML, CSS y JavaScript para
facilitar la interacción con el sistema.

## Tecnologías Utilizadas

- **Backend:**
    - Java 17
    - Spring Boot
    - MySQL
    - Flyway para migraciones de base de datos

- **Frontend:**
    - HTML5
    - CSS3
    - JavaScript ES6

## Dependencias Principales

- **Backend:**
    - Spring Web
    - Spring Data JPA
    - Lombok
    - Flyway
    - MySQL Connector
    - Spring Validation

- **Frontend:**
    - Librerías de estilos (Bootstrap y CSS puro)
    - Fetch API para llamadas a la API REST

## Funcionalidades Principales

- **Registro de pacientes y doctores:** Crear, actualizar y visualizar información de pacientes y doctores.
- **Administración de atenciones médicas:** Registrar las atenciones médicas realizadas, asociándolas a pacientes y
  doctores.
- **API RESTful:** Backend con endpoints para realizar operaciones CRUD sobre las entidades del sistema.
- **Interfaz de usuario:** Frontend que permite realizar las operaciones del sistema de manera amigable.

## Guía de Instalación y Ejecución

### Prerrequisitos

- **Java 17**
- **Maven**
- **MySQL** instalado y corriendo

### Pasos para la Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/tu-usuario/sistema-gestion-medica.git
2. **Navegar al directorio del proyecto:**
   ```bash
   cd sistema-gestion-medica
3. **Configurar la base de datos:**

- Crear una base de datos en MySQL.
- Configurar las credenciales de acceso en el archivo application.properties del backend:
    ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/nombre_base_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña

4. **Ejecutar el backend:**

- Desde la carpeta del backend, compilar y ejecutar:
    ```bash
    mvn spring-boot:run

5. Abrir el frontend:

- Navegar a la carpeta del frontend y abrir el archivo index.html en el navegador.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue para discutir los cambios propuestos antes de realizar un
pull request.

## Contacto

LinkedIn: [Elias Celis](https://www.linkedin.com/in/ecelis/)

Correo electrónico: zelys.dev@gmail.com