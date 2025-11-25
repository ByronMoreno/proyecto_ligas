# ⚽ Proyecto Multimódulo con Gradle (IntelliJ IDEA)

Este documento explica cómo organizar varios microservicios en **IntelliJ IDEA** usando un solo proyecto padre con Gradle.  
En este caso el proyecto padres es: `proyecto_ligas`.
Ideal para arquitecturas de microservicios como: `futbol`, `equipos`, etc.

---

## 🧩 1. Estructura de carpetas

Crea una carpeta raíz para todo tu sistema:

```
proyecto_ligas/
│
├── settings.gradle           # Información del proyecto y módulos
├── build.gradle              # Proyecto raíz - dependencias (padre); aplica solo si es ncesario
│
├── futbol/           # Microservicio 1
│   └── build.gradle
│
├── equipo/          # Microservicio 2
│   └── build.gradle
│
└── jugadores/        # Microservicio 3
    └── build.gradle
```

---

## ⚙️ 2. Archivo `settings.gradle` crear

Define los subproyectos (microservicios) dentro del proyecto padre:

```gradle
rootProject.name = 'proyecto_ligas'
include 'futbol', 'equipo', 'jugadores'
```

---

## 🧱 3. Archivo `build.gradle` del proyecto raíz

Aquí se colocan configuraciones y dependencias comunes para todos los microservicios.

```gradle
plugins {
    id 'java'
}

allprojects {
    group = 'com.byron.futbol'
    version = '1.0.0'
    repositories {
        mavenCentral()
    }
}
```

---

## ⚽ 4. Archivo `build.gradle` de cada microservicio

Cada microservicio tiene su propio `build.gradle` con dependencias específicas.

### Ejemplo: `futbol-service/build.gradle`
```gradle
plugins {
    id 'org.springframework.boot' version '3.3.5'
    id 'io.spring.dependency-management' version '1.1.5'
    id 'java'
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'org.postgresql:postgresql'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

### Ejemplo: `equipos-service/build.gradle`
```gradle
plugins {
    id 'org.springframework.boot' version '3.3.5'
    id 'io.spring.dependency-management' version '1.1.5'
    id 'java'
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'org.postgresql:postgresql'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

*(Puedes repetir el mismo formato para `jugadores-service` o cualquier otro.)*

---

## 🧠 5. Abrir el proyecto en IntelliJ IDEA

1. Abre **IntelliJ IDEA**.  
2. Selecciona **File → Open...**  
3. Elige la carpeta raíz del proyecto (`futbol-app`).  
4. IntelliJ detectará automáticamente todos los subproyectos (`futbol-service`, `equipos-service`, `jugadores-service`).

Cada microservicio aparecerá como **módulo independiente**, pero con una configuración común.

---

## 🚀 6. Ejecutar los microservicios

- Puedes ejecutar cada microservicio desde su clase principal de Spring Boot (`@SpringBootApplication`).  
- Cada uno tendrá su propio `application.properties` o `application.yml`.  
- Ejemplo:
  - `futbol-service` → puerto 8081  
  - `equipos-service` → puerto 8082  
  - `jugadores-service` → puerto 8083  

---

## 💡 7. Ventajas del enfoque multimódulo

✅ Un solo proyecto padre para todos los microservicios.  
✅ Configuración compartida y mantenible.  
✅ Facilita integración con **Eureka**, **Gateway** y **Config Server**.  
✅ Compatible con **Docker** y **CI/CD**.

---

## 📦 8. Ejemplo de estructura final (resumen)

```
futbol-app/
│
├── build.gradle
├── settings.gradle
│
├── futbol-service/
│   ├── src/main/java/com/byron/futbol/FutbolApplication.java
│   ├── src/main/resources/application.properties
│   └── build.gradle
│
├── equipos-service/
│   ├── src/main/java/com/byron/equipos/EquiposApplication.java
│   ├── src/main/resources/application.properties
│   └── build.gradle
│
└── jugadores-service/
    ├── src/main/java/com/byron/jugadores/JugadoresApplication.java
    ├── src/main/resources/application.properties
    └── build.gradle
```

---

## 🏁 Resultado

Cada microservicio:
- Tiene su propio código y configuración.
- Se compila y ejecuta por separado.
- Puede comunicarse con los demás (por ejemplo, vía REST o Eureka).

Y todo el conjunto se gestiona desde un único proyecto raíz en IntelliJ IDEA.

---

✍️ **Autor:** Byron Moreno  
📅 **Proyecto:** Arquitectura de microservicios - Spring Boot + Gradle
