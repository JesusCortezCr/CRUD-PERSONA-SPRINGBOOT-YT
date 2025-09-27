🚀 CRUD Completo con Spring Boot - REST API
https://img.shields.io/badge/Java-17%252B-orange?style=for-the-badge&logo=java
https://img.shields.io/badge/Spring%2520Boot-3.1.0-brightgreen?style=for-the-badge&logo=springboot
https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql
https://img.shields.io/badge/REST%2520API-Full%2520CRUD-success?style=for-the-badge

Una API REST completa desarrollada con Spring Boot que implementa operaciones CRUD (Create, Read, Update, Delete) con manejo avanzado de excepciones, validaciones robustas y conexión a base de datos MySQL.

📋 Tabla de Contenidos
Características

Tecnologías Utilizadas

Requisitos Previos

Instalación y Configuración

Estructura del Proyecto

Endpoints de la API

Manejo de Excepciones

Validaciones

Ejemplos de Uso

Base de Datos

Contribuir

Licencia

✨ Características
✅ CRUD Completo: Operaciones Create, Read, Update, Delete

🔒 Validaciones Robustas: Validación de datos de entrada

⚡ Manejo de Excepciones: Control centralizado de errores

🗃️ Persistencia MySQL: Conexión con base de datos relacional

📚 Documentación Automática: Endpoints documentados

🧪 Testing: Pruebas unitarias e integrales

🔄 Transacciones: Manejo seguro de operaciones en BD

🛠️ Tecnologías Utilizadas
Java 17+

Spring Boot 3.1.0

Spring Data JPA

Spring Validation

MySQL 8.0

Maven

Lombok

ModelMapper

Springdoc OpenAPI (Documentación)

📋 Requisitos Previos
Antes de ejecutar el proyecto, asegúrate de tener instalado:

Java 17 o superior

MySQL 8.0 o superior

Maven 3.6+

IDE (IntelliJ IDEA, Eclipse, VS Code)

🚀 Instalación y Configuración
1. Clonar el Repositorio
bash
git clone https://github.com/tu-usuario/crud-springboot-rest.git
cd crud-springboot-rest
2. Configurar Base de Datos
Crear una base de datos en MySQL:

sql
CREATE DATABASE crud_springboot;
3. Configurar Variables de Entorno
Editar el archivo src/main/resources/application.properties:

properties
# Configuración de MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/crud_springboot
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Configuration
server.port=8080
4. Compilar y Ejecutar
bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn spring-boot:run
La aplicación estará disponible en: http://localhost:8080

📁 Estructura del Proyecto
text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── ejemplo/
│   │           └── crud/
│   │               ├── controller/     # Controladores REST
│   │               ├── service/        # Lógica de negocio
│   │               ├── repository/     # Acceso a datos
│   │               ├── entity/         # Entidades JPA
│   │               ├── dto/           # Objetos de transferencia
│   │               ├── exception/      # Manejo de excepciones
│   │               └── validation/     # Validaciones personalizadas
│   └── resources/
│       ├── application.properties      # Configuración
│       └── data.sql                   # Datos iniciales (opcional)
└── test/
    └── java/
        └── com/
            └── ejemplo/
                └── crud/
                    ├── controller/     # Tests de controladores
                    ├── service/        # Tests de servicios
                    └── repository/     # Tests de repositorios
🔌 Endpoints de la API
📚 Entidad Principal (Ejemplo: Usuario)
Método	Endpoint	Descripción
GET	/api/usuarios	Obtener todos los usuarios
GET	/api/usuarios/{id}	Obtener usuario por ID
POST	/api/usuarios	Crear nuevo usuario
PUT	/api/usuarios/{id}	Actualizar usuario existente
DELETE	/api/usuarios/{id}	Eliminar usuario
📊 Documentación API
Una vez ejecutada la aplicación, accede a:

Swagger UI: http://localhost:8080/swagger-ui.html

OpenAPI JSON: http://localhost:8080/v3/api-docs

⚡ Manejo de Excepciones
El proyecto incluye un manejo centralizado de excepciones:

java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        // Manejo de recursos no encontrados
    }
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(ValidationException ex) {
        // Manejo de errores de validación
    }
}
Códigos de Error Implementados
400 Bad Request - Validaciones fallidas

404 Not Found - Recurso no encontrado

500 Internal Server Error - Error del servidor

✅ Validaciones
Validaciones de Entidad (Ejemplo)
java
public class UsuarioDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;
    
    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 100, message = "La edad máxima es 100 años")
    private Integer edad;
}
💡 Ejemplos de Uso
Crear un Usuario (POST)
Request:

bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan@example.com",
    "edad": 25
  }'
Response (201 Created):

json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "edad": 25,
  "fechaCreacion": "2024-01-15T10:30:00Z"
}
Obtener Usuario por ID (GET)
Request:

bash
curl -X GET http://localhost:8080/api/usuarios/1
Response (200 OK):

json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "edad": 25,
  "fechaCreacion": "2024-01-15T10:30:00Z"
}
🗃️ Base de Datos
Esquema Principal (Ejemplo)
sql
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    edad INT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
Configuración JPA
Las entidades están mapeadas con JPA y Hibernate:

java
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Getters y Setters
}
🧪 Testing
Ejecutar Tests
bash
# Ejecutar todos los tests
mvn test

# Ejecutar tests con cobertura
mvn jacoco:report
Tipos de Tests Implementados
Unit Tests: Servicios y componentes individuales

Integration Tests: Pruebas de integración con base de datos

Controller Tests: Pruebas de endpoints REST

🤝 Contribuir
¡Las contribuciones son bienvenidas! Para contribuir:

Haz un Fork del proyecto

Crea una rama para tu feature (git checkout -b feature/AmazingFeature)

Commit tus cambios (git commit -m 'Add some AmazingFeature')

Push a la rama (git push origin feature/AmazingFeature)

Abre un Pull Request

📝 Licencia
Este proyecto está bajo la Licencia MIT. Ver el archivo LICENSE para más detalles.

👨‍💻 Autor
Tu Nombre

GitHub: @tu-usuario

LinkedIn: Tu Perfil

🙏 Agradecimientos
Spring Boot

MySQL

La comunidad de desarrolladores Java

⭐ Si este proyecto te fue útil, no olvides darle una estrella en GitHub!

¿Tienes preguntas? Abre un issue o contáctame directamente. ¡Happy coding! 🎉

muy grande , hazme mas chico por favor
🚀 CRUD Spring Boot - REST API
https://img.shields.io/badge/Spring%2520Boot-3.1.0-brightgreen
https://img.shields.io/badge/MySQL-8.0-blue

API REST completa con Spring Boot que implementa operaciones CRUD con manejo de excepciones, validaciones y conexión MySQL.

✨ Características
✅ CRUD Completo (Create, Read, Update, Delete)

🔒 Validaciones robustas de datos

⚡ Manejo centralizado de excepciones

🗃️ Conexión con MySQL

📚 Documentación Swagger/OpenAPI

🛠️ Tecnologías
Java 17+

Spring Boot 3.1.0

Spring Data JPA

MySQL 8.0

Maven

Lombok

🚀 Instalación Rápida
Clonar repositorio:

bash
git clone https://github.com/tu-usuario/crud-springboot.git
cd crud-springboot
Configurar base de datos:

sql
CREATE DATABASE crud_springboot;
Configurar application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/crud_springboot
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
Ejecutar:

bash
mvn spring-boot:run
🔌 Endpoints Principales
Método	Endpoint	Descripción
GET	/api/usuarios	Listar todos
GET	/api/usuarios/{id}	Buscar por ID
POST	/api/usuarios	Crear nuevo
PUT	/api/usuarios/{id}	Actualizar
DELETE	/api/usuarios/{id}	Eliminar
💡 Ejemplo de Uso
Crear usuario:

bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Juan", "email": "juan@email.com", "edad": 25}'
📊 Documentación
Swagger UI: http://localhost:8080/swagger-ui.html

API Docs: http://localhost:8080/v3/api-docs

🐛 Manejo de Errores
Códigos de respuesta implementados:

200 OK - Éxito

400 Bad Request - Validación fallida

404 Not Found - Recurso no existe

500 Internal Error - Error del servidor

⭐ ¿Te gustó el proyecto? Dale una estrella en GitHub!

¿Preguntas? Abre un issue o contáctame. ¡Happy coding! 🎉
