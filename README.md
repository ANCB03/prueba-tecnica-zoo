# Prueba Técnica ZOOLOGICO - Nelumbo

En este README se va a explicar como ejecutar el proyecto de manera local.




## Prerrequisitos

- Se debe tener una version de java 17 o superior.

- Se debe tener maven en el computador o utilizarlo por medio del entorno de desarrollo como por ejemplo IntelliJIDEA.

- Tener el puerto 8080 disponible ya que el proyecto se ejecuta en este por defecto.
## Ejecutar en local

**1** - Clonar el proyecto:

```bash
  https://github.com/ANCB03/prueba-tecnica-zoo.git
```

**2** - Abrir el proyecto en el entorno de desarrollo que uses.

**3** - Descargar las depenedencias que usa el proyecto en el pom(En IntelliJIDEA está el reload project de maven).


**4** - Ejecutar el siguiente comando de maven que permite que se generen los mappers y demás cosas necesarias:
```bash
  mvn clean package -DskipTests
```
**IMPORTANTE** - Por alguna razon en IntelliJIDEA no dejaba ejecutar el comando y la solución fue ir a:
```bash
  src/main/resources/application.properties
```
y comentar o cortar estas lineas de codigo:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/zoo
spring.datasource.username=postgres
spring.datasource.password=Androlo123
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuración adicional opcional
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```
Luego ejecutar el comando de maven:
```bash
  mvn clean package -DskipTests
```
una vez se complete la ejecución del comando anterior volver a pegar las lineas que se quitaron antes en el
application.properties.

**5** - Ingresar su usuario, contraseña y nombre de base de datos de su postgresql en el archivo:
```bash
  src/main/resources/application.properties
```

**6** - Ejecutar el proyecto.

**7** - Una vez ejecutado el proyecto este estará en el puerto 8080 y se podrán hacer peticiones desde esta base url:

```bash
  http://localhost:8080
```

## Documentación Postman

Para acceder a la Documentación de postman debes ingresar a este link:
- [https://documenter.getpostman.com/view/24144423/2sAYQdjq9q](https://documenter.getpostman.com/view/24144423/2sAYQdjq9q)
  
También puedes consultar la documentación de la aplicación por SWAGGER con este link:

**Local:**
- [http://localhost:8080/swagger-ui/#/](http://localhost:8080/swagger-ui/#/)




## Autor

- [@ANCB03](https://www.github.com/ancb03)



