# Sistema de Gestión de Biblioteca Universitaria APEC

Sistema completo de gestión de biblioteca desarrollado con Java Servlets, JSP y PostgreSQL.

## 📋 Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

1. **JDK 23** (OpenJDK)
   - Descargar de: https://jdk.java.net/23/
   - Verificar instalación: `java -version`

2. **Apache Maven 3.8+**
   - Descargar de: https://maven.apache.org/download.cgi
   - Verificar instalación: `mvn -version`

3. **IntelliJ IDEA** (Community o Ultimate)
   - Descargar de: https://www.jetbrains.com/idea/download/

4. **Apache Tomcat 10.1.46**
   - Descargar de: https://tomcat.apache.org/download-10.cgi
   - Descargar la versión "Core" (zip o tar.gz)

5. **PostgreSQL 12+**
   - Ya instalado en tu laptop
   - Base de datos: `biblioteca_apec`
   - Usuario: `postgres`
   - Contraseña: `123456`
   - Puerto: `5432`

## 🗄️ Configuración de la Base de Datos

La base de datos ya está creada con el script que proporcionaste. Verifica que:

1. PostgreSQL esté ejecutándose
2. La base de datos `biblioteca_apec` exista
3. Todas las tablas estén creadas

Para verificar, puedes ejecutar:

```sql
\c biblioteca_apec
\dt
```

Deberías ver todas las tablas: tipo_bibliografia, editora, ciencia, idioma, autor, libro, usuario, empleado, prestamo.

## 🚀 Pasos para Ejecutar la Aplicación

### Opción 1: Ejecutar desde IntelliJ IDEA (RECOMENDADO)

#### 1. Abrir el Proyecto en IntelliJ IDEA

1. Abre IntelliJ IDEA
2. File → Open
3. Navega hasta la carpeta `biblioteca_apec`
4. Selecciona la carpeta y haz clic en "OK"
5. IntelliJ detectará automáticamente que es un proyecto Maven

#### 2. Configurar Tomcat en IntelliJ

1. Ve a: **Run → Edit Configurations**
2. Haz clic en el botón **+** (Add New Configuration)
3. Selecciona **Tomcat Server → Local**
4. En la pestaña **Server**:
   - **Name**: Tomcat 10.1.46
   - Haz clic en **Configure...** junto a "Application server"
   - Haz clic en **+** y navega hasta donde descargaste Tomcat
   - Selecciona la carpeta de Tomcat y haz clic en "OK"
   - **HTTP port**: 8080 (por defecto)
   - **JRE**: Selecciona Java 23

5. En la pestaña **Deployment**:
   - Haz clic en el botón **+** (Add)
   - Selecciona **Artifact...**
   - Selecciona **biblioteca_apec:war exploded**
   - En **Application context**, déjalo como `/biblioteca_apec` o cámbialo a `/`

6. Haz clic en **Apply** y luego en **OK**

#### 3. Compilar el Proyecto

Opción A - Usando Maven en IntelliJ:
1. Abre la ventana "Maven" (View → Tool Windows → Maven)
2. Expande "biblioteca_apec → Lifecycle"
3. Haz doble clic en **clean**
4. Luego haz doble clic en **package**

Opción B - Usando terminal:
```bash
cd biblioteca_apec
mvn clean package
```

#### 4. Ejecutar la Aplicación

1. En IntelliJ, selecciona la configuración de Tomcat que creaste
2. Haz clic en el botón **Run** (▶️) o presiona **Shift + F10**
3. Espera a que Tomcat inicie
4. IntelliJ abrirá automáticamente el navegador

Si no se abre automáticamente, abre tu navegador y ve a:
```
http://localhost:8080/biblioteca_apec/
```

### Opción 2: Ejecutar Manualmente con Maven y Tomcat

#### 1. Compilar el Proyecto

```bash
cd biblioteca_apec
mvn clean package
```

Esto generará el archivo `biblioteca_apec.war` en la carpeta `target/`.

#### 2. Desplegar en Tomcat

1. Copia el archivo WAR:
   ```bash
   cp target/biblioteca_apec.war /ruta/a/tomcat/webapps/
   ```

2. Inicia Tomcat:
   - **Windows**:
     ```bash
     C:\ruta\a\tomcat\bin\startup.bat
     ```
   - **Linux/Mac**:
     ```bash
     /ruta/a/tomcat/bin/startup.sh
     ```

3. Abre tu navegador y ve a:
   ```
   http://localhost:8080/biblioteca_apec/
   ```

## 📁 Estructura del Proyecto

```
biblioteca_apec/
├── src/
│   ├── main/
│   │   ├── java/org/example/biblioteca_apec/
│   │   │   ├── model/           # Clases de entidad (POJO)
│   │   │   ├── dao/              # Data Access Objects (JDBC)
│   │   │   ├── controller/       # Servlets (controladores)
│   │   │   └── utils/            # Utilidades (DBConnection)
│   │   └── webapp/
│   │       ├── views/            # Páginas JSP
│   │       │   ├── tipos-bibliografia/
│   │       │   ├── editoras/
│   │       │   ├── libros/
│   │       │   ├── prestamos/
│   │       │   ├── header.jsp
│   │       │   ├── footer.jsp
│   │       │   └── home.jsp
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       └── index.jsp
└── pom.xml                       # Configuración Maven
```

## 🔧 Configuración de Base de Datos

Si necesitas cambiar la configuración de la base de datos, edita el archivo:

```
src/main/java/org/example/biblioteca_apec/utils/DBConnection.java
```

Modifica las siguientes líneas:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca_apec";
private static final String USER = "postgres";
private static final String PASSWORD = "123456";
```

## 📚 Funcionalidades Implementadas

1. ✅ **Gestión de Tipos de Bibliografía** - CRUD completo
2. ✅ **Gestión de Editoras** - CRUD completo
3. ⚠️ **Gestión de Ciencias** - Modelo y DAO listos
4. ⚠️ **Gestión de Idiomas** - Modelo y DAO listos
5. ⚠️ **Gestión de Autores** - Modelo y DAO listos
6. ⚠️ **Gestión de Usuarios** - Modelo y DAO listos
7. ✅ **Gestión de Libros** - CRUD completo con relaciones
8. ⚠️ **Gestión de Empleados** - Modelo y DAO listos
9. ✅ **Gestión de Préstamos** - CRUD completo con relaciones
10. ⚠️ **Consultas y Reportes** - Por implementar

**Nota**: Los módulos marcados con ⚠️ tienen la estructura backend completa (Modelo + DAO) pero necesitan crear el Servlet y las vistas JSP siguiendo el mismo patrón de los módulos completos.

## 🔍 Solución de Problemas

### Error: "No suitable driver found"
- Verifica que el driver de PostgreSQL esté en el `pom.xml`
- Ejecuta `mvn clean package` nuevamente

### Error: "Connection refused"
- Verifica que PostgreSQL esté ejecutándose
- Verifica el puerto (5432)
- Verifica que la base de datos `biblioteca_apec` exista

### Error: "Could not find or load main class"
- Verifica que estés usando JDK 23
- Verifica la configuración del JDK en IntelliJ

### Error 404 al acceder a la aplicación
- Verifica que Tomcat esté ejecutándose
- Verifica la URL: `http://localhost:8080/biblioteca_apec/`
- Verifica que el WAR se haya desplegado correctamente en `tomcat/webapps/`

## 📖 Uso de la Aplicación

1. **Página Principal**: Muestra un dashboard con acceso a todos los módulos
2. **Catálogos**: Gestiona tipos de bibliografía, editoras, ciencias, idiomas y autores
3. **Libros**: Agrega, edita y elimina libros del catálogo
4. **Usuarios**: Registra usuarios de la biblioteca
5. **Empleados**: Gestiona empleados que asisten a los usuarios
6. **Préstamos**: Registra préstamos y devoluciones de libros

## 🛠️ Tecnologías Utilizadas

- **Backend**: Java 23, Jakarta Servlets 6.0, JSP
- **Frontend**: HTML5, Bootstrap 5.3, Font Awesome 6.4
- **Base de Datos**: PostgreSQL 12+
- **Servidor**: Apache Tomcat 10.1.46
- **Build Tool**: Maven 3.8+
- **IDE**: IntelliJ IDEA

## 📝 Notas Adicionales

- El proyecto usa Jakarta EE (no javax) compatible con Tomcat 10.x
- La conexión a la base de datos se hace con JDBC puro (sin frameworks)
- El proyecto sigue el patrón MVC básico
- Todas las páginas incluyen header y footer comunes
- Bootstrap se carga desde CDN

## 👨‍💻 Desarrollo Futuro

Para completar los módulos faltantes, sigue estos pasos:

1. Crea el Servlet correspondiente en `src/main/java/.../controller/`
2. Crea las vistas JSP en `src/main/webapp/views/nombre-modulo/`
3. Sigue el patrón de TipoBibliografiaServlet y sus vistas

## 📧 Soporte

Si encuentras algún problema, verifica:
1. Logs de Tomcat en `tomcat/logs/catalina.out`
2. Logs en la consola de IntelliJ
3. Configuración de la base de datos
