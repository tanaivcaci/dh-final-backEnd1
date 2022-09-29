# dh-final-backEnd1
Microservicios


Proyecto Integrador final

Especialización en Backend I

API Restful con Spring Boot
Arquitectura de MicroServicios.

    Central configuration (Spring Cloud Config)
    Service Registry/Discovery (Spring Cloud Netflix Eureka)
    Edge server (Spring Cloud Gateway)
    Distributed tracing (Spring Cloud: Sleuth y Zipkin)
    Circuit Breaker (Spring Cloud Circuit Breaker - Resilience4j)
    Comunicación asíncrona (Spring Cloud Stream: RabbitMQ)



*Contextualización*

El proyecto consiste de tres microservicios: 
Movie, Series y Catalog. 

Catalog es un microservicio que lee información de Movie y Series con el objetivo de enviar un catálogo al cliente. 

Catalog recibe un mensaje cada vez que una película o una serie es dada de alta y las persiste en una base de datos no relacional de MongoDB. Cuando le llega una petición del cliente, busca en la base de datos y responde.
