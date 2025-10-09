# 🎯 Pasos Detallados para Ejecutar el Proyecto

## 📋 Checklist Inicial

Antes de comenzar, verifica que tengas instalado:

- [ ] Java 23 (OpenJDK)
- [ ] Apache Maven 3.8+
- [ ] IntelliJ IDEA
- [ ] Apache Tomcat 10.1.46
- [ ] PostgreSQL (con base de datos biblioteca_apec creada)

---

## 🔧 PASO 1: Verificar Instalaciones

### 1.1 Verificar Java

Abre una terminal y ejecuta:

```bash
java -version
```

**Debe mostrar algo como:**
```
openjdk version "23" ...
```

Si no está instalado:
- Descargar de: https://jdk.java.net/23/

---

### 1.2 Verificar Maven

```bash
mvn -version
```

**Debe mostrar:**
```
Apache Maven 3.x.x
```

Si no está instalado:
- Descargar de: https://maven.apache.org/download.cgi

---

### 1.3 Verificar PostgreSQL

```bash
psql -U postgres -c "SELECT version();"
```

Luego verifica que la base de datos existe:

```bash
psql -U postgres -d biblioteca_apec -c "\dt"
```

**Debe mostrar las 9 tablas:**
- tipo_bibliografia
- editora
- ciencia
- idioma
- autor
- libro
- usuario
- empleado
- prestamo

---

## 📦 PASO 2: Descargar y Preparar el Proyecto

### 2.1 Ubicación del Proyecto

1. Descarga la carpeta completa del proyecto
2. Guárdala en una ubicación fácil de recordar, por ejemplo:
   - Windows: `C:\Users\TuUsuario\Documents\biblioteca_apec`
   - Linux/Mac: `~/Documents/biblioteca_apec`

### 2.2 Verificar Archivos

Dentro de la carpeta debes ver:

```
biblioteca_apec/
├── pom.xml                          ← Archivo Maven
├── README.md                        ← Documentación
├── INSTRUCCIONES_RAPIDAS.md
├── PASOS_DETALLADOS.md             ← Este archivo
├── datos_ejemplo.sql
├── compilar.bat                     ← Script para Windows
├── compilar.sh                      ← Script para Linux/Mac
└── src/                            ← Código fuente
```

---

## 🎲 PASO 3: Cargar Datos de Ejemplo (OPCIONAL)

Si quieres tener datos de prueba en la base de datos:

### Windows:
```bash
cd C:\Users\TuUsuario\Documents\biblioteca_apec
psql -U postgres -d biblioteca_apec -f datos_ejemplo.sql
```

### Linux/Mac:
```bash
cd ~/Documents/biblioteca_apec
psql -U postgres -d biblioteca_apec -f datos_ejemplo.sql
```

**Contraseña:** 123456

---

## 💻 PASO 4: Abrir el Proyecto en IntelliJ IDEA

### 4.1 Iniciar IntelliJ

1. Abre IntelliJ IDEA
2. En la pantalla de bienvenida, haz clic en **"Open"**
3. Navega hasta la carpeta del proyecto `biblioteca_apec`
4. Selecciona la carpeta (NO selecciones archivos individuales)
5. Haz clic en **"OK"**

### 4.2 Importar como Proyecto Maven

IntelliJ detectará automáticamente que es un proyecto Maven.

1. Espera a que aparezca una notificación en la esquina inferior derecha
2. Si aparece "Maven projects need to be imported", haz clic en **"Import Changes"**
3. Espera a que Maven descargue todas las dependencias (puede tardar 2-5 minutos)

**Señal de éxito:**
- En la parte inferior, verás "Build: SUCCESS"
- En la ventana de Maven (lateral derecha), verás el proyecto expandible

---

## 🚀 PASO 5: Configurar Apache Tomcat en IntelliJ

### 5.1 Abrir Configuraciones

1. En la barra superior, busca el dropdown que dice "Add Configuration..." o "Current File"
2. Haz clic en él
3. Selecciona **"Edit Configurations..."**

### 5.2 Agregar Tomcat Server

1. Haz clic en el botón **"+"** (esquina superior izquierda)
2. Selecciona **"Tomcat Server"** → **"Local"**

   Si no aparece "Tomcat Server":
   - Busca en la lista "More items (46 total...)"
   - Encuentra "Tomcat Server" → "Local"

### 5.3 Configurar el Servidor

En la pestaña **"Server"**:

1. **Name:** Escribe "Tomcat 10.1.46" (o el nombre que prefieras)

2. **Application server:**
   - Haz clic en **"Configure..."**
   - Haz clic en el botón **"+"**
   - Navega hasta donde descargaste/instalaste Tomcat
   - Selecciona la carpeta raíz de Tomcat (por ejemplo: `C:\apache-tomcat-10.1.46`)
   - Haz clic en **"OK"**

3. **JRE:** Asegúrate de que esté seleccionado **"Java 23"**
   - Si no aparece, haz clic en "Add JDK..." y busca la instalación de Java 23

4. **HTTP port:** Déjalo en **8080** (o cámbialo si el puerto está ocupado)

5. **URL:** Déjalo como está (`http://localhost:8080`)

### 5.4 Configurar el Deployment

1. Haz clic en la pestaña **"Deployment"**

2. Haz clic en el botón **"+"** debajo de "Deploy at the server startup"

3. Selecciona **"Artifact..."**

4. En la lista que aparece, selecciona:
   ```
   biblioteca_apec:war exploded
   ```

5. **Application context:**
   - Verás algo como `/biblioteca_apec`
   - Puedes dejarlo así o cambiarlo a `/` si prefieres

6. Haz clic en **"Apply"**

7. Haz clic en **"OK"**

---

## ▶️ PASO 6: Ejecutar la Aplicación

### 6.1 Compilar el Proyecto

Antes de ejecutar por primera vez, compila el proyecto:

**Opción A - Desde IntelliJ:**
1. Ve al menú **"Build"**
2. Selecciona **"Build Project"**
3. Espera a que termine (verás "Build completed successfully")

**Opción B - Desde Terminal:**
```bash
mvn clean package
```

### 6.2 Ejecutar Tomcat

1. En la barra superior, selecciona la configuración de Tomcat que creaste
2. Haz clic en el botón verde **"Run"** (▶️)
   - O presiona **Shift + F10**

### 6.3 Esperar a que Inicie

En la consola de IntelliJ verás:

```
Connected to server
[fecha] org.apache.catalina.startup.Catalina.start Server startup in [X] milliseconds
```

### 6.4 Abrir en el Navegador

IntelliJ debería abrir automáticamente el navegador.

Si no lo hace, abre tu navegador manualmente y ve a:

```
http://localhost:8080/biblioteca_apec/
```

---

## ✅ PASO 7: Verificar que Funciona

### 7.1 Página Principal

Deberías ver:
- Un título: "Sistema de Gestión de Biblioteca Universitaria APEC"
- Un menú de navegación superior azul
- 6 tarjetas con diferentes módulos

### 7.2 Probar un Módulo

1. Haz clic en el menú **"Catálogos"** → **"Tipos de Bibliografía"**
2. Deberías ver una tabla (vacía si no cargaste datos de ejemplo)
3. Haz clic en **"Nuevo Tipo"**
4. Completa el formulario:
   - Descripción: "Libro"
   - Estado: "Activo"
5. Haz clic en **"Guardar"**
6. Deberías ver el registro en la tabla

### 7.3 Probar Todos los Módulos

Desde el menú, prueba acceder a:
- Catálogos → Editoras
- Catálogos → Ciencias
- Catálogos → Idiomas
- Catálogos → Autores
- Libros
- Usuarios
- Empleados
- Préstamos

Todos deberían cargar sin errores.

---

## 🔧 SOLUCIÓN DE PROBLEMAS COMUNES

### ❌ Error: "Cannot resolve symbol 'jakarta'"

**Solución:**
1. Cierra IntelliJ
2. Borra la carpeta `.idea` del proyecto
3. Abre el proyecto nuevamente
4. Espera a que Maven reimporte todo

### ❌ Error: "Port 8080 already in use"

**Solución:**
1. Cambia el puerto en la configuración de Tomcat a 8081
2. O encuentra y detén el proceso que usa el puerto 8080

### ❌ Error: "Connection refused" al acceder a la base de datos

**Solución:**
1. Verifica que PostgreSQL esté corriendo:
   ```bash
   # Windows
   services.msc
   # Busca "PostgreSQL" y verifica que esté "Running"

   # Linux/Mac
   sudo systemctl status postgresql
   ```

2. Verifica la configuración en `DBConnection.java`:
   ```java
   URL: jdbc:postgresql://localhost:5432/biblioteca_apec
   Usuario: postgres
   Password: 123456
   ```

### ❌ Error 404: "The origin server did not find a current representation for the target resource"

**Solución:**
1. Verifica que la URL sea correcta: `http://localhost:8080/biblioteca_apec/`
2. Verifica en la consola de Tomcat que la aplicación se haya desplegado correctamente
3. Busca mensajes como: `Deployment of web application archive ... has finished`

### ❌ Error: "Class not found" o "ClassNotFoundException"

**Solución:**
1. Limpia y recompila:
   ```bash
   mvn clean package
   ```
2. En IntelliJ: **Build → Rebuild Project**

### ❌ Páginas JSP sin estilos

**Solución:**
1. Verifica tu conexión a internet (Bootstrap y Font Awesome se cargan desde CDN)
2. Abre la consola del navegador (F12) y busca errores de red

---

## 📱 PASO 8: Probar Funcionalidades

### Crear un Libro Completo

1. **Primero, crea los catálogos:**
   - Tipo de Bibliografía: "Libro"
   - Editora: "Santillana"
   - Ciencia: "Literatura"
   - Idioma: "Español"
   - Autor: "Gabriel García Márquez" (con idioma nativo: Español)

2. **Luego, crea el libro:**
   - Descripción: "Cien Años de Soledad"
   - ISBN: "978-0307474728"
   - Signatura: "COL-FIC-001"
   - Año: 1967
   - Selecciona todos los datos creados anteriormente
   - Estado: Disponible

3. **Crea un usuario:**
   - Nombre: "Juan Pérez"
   - Cédula: "001-1234567-8"
   - No. Carnet: "EST-2024-001"
   - Tipo: Física
   - Estado: Activo

4. **Crea un empleado:**
   - Nombre: "Ana García"
   - Cédula: "001-9876543-2"
   - Tanda: Matutina
   - Comisión: 5.00
   - Fecha de ingreso: Hoy
   - Estado: Activo

5. **Registra un préstamo:**
   - Empleado: Ana García
   - Usuario: Juan Pérez
   - Libro: Cien Años de Soledad
   - Fecha préstamo: Hoy
   - Monto por día: 50.00
   - Días excedidos: 0
   - Estado: Activo

---

## 🎉 ¡Felicidades!

Si llegaste hasta aquí y todo funciona, **¡tu sistema de biblioteca está completamente operativo!**

### Próximos Pasos:

1. 📊 Explora todos los módulos
2. 🗄️ Carga más datos de ejemplo
3. 🎨 Personaliza los estilos si lo deseas
4. 📈 Considera agregar reportes (ver README.md para ideas)

---

## 📞 Información de Ayuda

### Logs Importantes

Para diagnosticar problemas, revisa:

1. **Console en IntelliJ** - Mensajes de Tomcat
2. **Logs de Tomcat:**
   - Windows: `C:\apache-tomcat-10.1.46\logs\catalina.out`
   - Linux/Mac: `/opt/tomcat/logs/catalina.out`

### Comandos Útiles

```bash
# Verificar que PostgreSQL escucha en el puerto
netstat -an | grep 5432

# Ver logs de PostgreSQL
# Linux: /var/log/postgresql/
# Windows: C:\Program Files\PostgreSQL\XX\data\log\

# Compilar manualmente
mvn clean package

# Ver estructura del WAR generado
jar tf target/biblioteca_apec.war | head -20
```

---

## 📚 Archivos de Referencia

- **README.md** - Documentación completa
- **INSTRUCCIONES_RAPIDAS.md** - Guía de 5 minutos
- **RESUMEN_PROYECTO.md** - Visión general del proyecto
- **datos_ejemplo.sql** - Datos de prueba

---

## ✨ ¡Éxito!

Tu sistema de biblioteca está listo para usarse. Puedes crear, editar, listar y eliminar:

- ✅ Tipos de bibliografía
- ✅ Editoras
- ✅ Ciencias
- ✅ Idiomas
- ✅ Autores
- ✅ Libros (con todas sus relaciones)
- ✅ Usuarios
- ✅ Empleados
- ✅ Préstamos y devoluciones

**¡Disfruta tu aplicación!** 🚀📚
