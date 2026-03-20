# Tareas-Reservas-Listo

Sistema de Gestion Deportiva - Microservicio de Reservas
Este modulo es el encargado de la gestion y persistencia de reservaciones del club. Su arquitectura se basa en el desacoplamiento de servicios, utilizando comunicacion sincronica para garantizar la integridad de los datos entre modulos independientes.

Stack Tecnologico
Lenguaje: Java 21

Framework Base: Spring Boot 4.0.1

Comunicacion Inter-servicios: Spring Cloud OpenFeign 2025.1.1

Persistencia: Spring Data JPA / MySQL

Gestor de Dependencias: Maven

Implementacion de Comunicacion Inter-servicios
El sistema implementa un patron de comunicacion entre el Servicio de Reservas (Puerto 8082) y el Servicio de Canchas (Puerto 8081). Los componentes clave de esta integracion son:

1. Gestion de Dependencias y Versiones
Se configuro el bloque dependencyManagement en el archivo pom.xml para integrar el Bill of Materials (BOM) de Spring Cloud. Esto asegura que la libreria de OpenFeign sea totalmente compatible con la version 4.0.1 de Spring Boot, evitando conflictos de dependencias en tiempo de ejecucion.

2. Cliente Declarativo (OpenFeign)
Se desarrollo la interfaz CanchaClient en el paquete de clientes. Esta interfaz actua como un proxy dinamico que:

Define la URL base del servicio externo (http://localhost:8081).

Mapea el endpoint de consulta por identificador (/api/canchas/{id}).

Realiza la deserializacion automatica del JSON de respuesta hacia el objeto CanchaDTO.

3. Logica de Validacion en Capa de Servicio
En la implementacion de ReservaServicesImpl, se integro una validacion obligatoria antes de la persistencia:

El servicio solicita la informacion de la cancha al microservicio externo.

Si el recurso existe, se procede con el guardado de la reserva.

En caso de que el recurso no exista o el servicio destino no este disponible, se captura la excepcion y se interrumpe el proceso para evitar inconsistencias en la base de datos.
