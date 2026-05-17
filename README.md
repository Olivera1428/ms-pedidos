# ms-pedidos

## Descripción

Microservicio desarrollado con Spring Boot para la gestión de pedidos.  
Permite registrar pedidos, listar pedidos, buscar pedidos y actualizar estados.

Este proyecto fue desarrollado como parte del examen final de microservicios utilizando:

- Spring Boot
- PostgreSQL (Neon)
- Docker
- Render

---

# Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- PostgreSQL
- Neon Database
- Docker
- Render
- Maven
- Lombok
- Validation

---

# Arquitectura del proyecto

El proyecto está organizado por capas:

```text
src/main/java/com/codigo/ms_pedidos
│
├── controller
├── service
├── repository
├── entity
├── enums
├── dto
└── exception
```

---

# Variables de entorno

El proyecto utiliza variables de entorno para proteger las credenciales de la base de datos.

## Configuración

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=${PORT:8080}
```

## Variables necesarias

| Variable | Descripción |
|---|---|
| DB_URL | URL de conexión PostgreSQL Neon |
| DB_USERNAME | Usuario de la base de datos |
| DB_PASSWORD | Contraseña de la base de datos |
| PORT | Puerto del servicio |

---

# Entidad Pedido

| Campo | Tipo |
|---|---|
| id | Long |
| cliente | String |
| correoCliente | String |
| productoId | Long |
| nombreProducto | String |
| cantidad | Integer |
| precioUnitario | BigDecimal |
| total | BigDecimal |
| estado | String |
| fechaPedido | LocalDateTime |

---

# Lógica implementada

El total del pedido es calculado automáticamente en el backend:

```text
total = cantidad * precioUnitario
```

---

# Endpoints disponibles

## Crear pedido

```http
POST /api/pedidos
```

### Ejemplo JSON

```json
{
  "cliente": "Juan Pérez",
  "correoCliente": "juan@email.com",
  "productoId": 1,
  "nombreProducto": "Laptop Lenovo",
  "cantidad": 2,
  "precioUnitario": 3500.00
}
```

---

## Listar pedidos

```http
GET /api/pedidos
```

---

## Buscar pedido por ID

```http
GET /api/pedidos/{id}
```

---

## Actualizar estado del pedido

```http
PATCH /api/pedidos/{id}/estado
```

### Ejemplo JSON

```json
{
  "estado": "PAGADO"
}
```

## Estados utilizados

- REGISTRADO
- PAGADO
- ENVIADO
- CANCELADO

---

## Eliminar pedido

```http
DELETE /api/pedidos/{id}
```

---

# Validaciones implementadas

- Cliente obligatorio
- Correo válido
- Cantidad mayor a cero
- Precio unitario mayor a cero

## Anotaciones utilizadas

```java
@NotBlank
@Email
@Positive
@NotNull
```

---

# Manejo de errores

El proyecto implementa manejo global de excepciones utilizando:

- `@RestControllerAdvice`
- Excepciones personalizadas

## Ejemplo de respuesta de error

```json
{
  "mensaje": "Pedido no encontrado",
  "detalle": "No existe un pedido con el ID 5",
  "fecha": "2026-05-09T10:30:00"
}
```

---

# Docker

El proyecto incluye un `Dockerfile` para el despliegue.

```dockerfile
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---


# Despliegue en Render

El microservicio fue desplegado en Render utilizando Docker.

## URL pública

```text
https://ms-pedidos-qx09.onrender.com
```

## Endpoint principal

```text
https://ms-pedidos-qx09.onrender.com/api/pedidos
```

---

# Base de datos

Base de datos PostgreSQL alojada en Neon.

Las tablas son generadas automáticamente mediante Hibernate utilizando:

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

# Evidencias requeridas

El proyecto fue probado utilizando Postman y Thunder Client.

## Endpoints probados

```http
POST   /api/pedidos
GET    /api/pedidos
GET    /api/pedidos/{id}
PATCH  /api/pedidos/{id}/estado
DELETE /api/pedidos/{id}
```

---

# Autor

Proyecto desarrollado por:

- Paulocésar Donovan Olivera Bautista
