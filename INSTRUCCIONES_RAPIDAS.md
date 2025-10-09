# 🚀 Instrucciones Rápidas - Sistema Biblioteca APEC

## ⚡ Inicio Rápido (5 minutos)

### 1️⃣ Verificar Requisitos

```bash
java -version    # Debe mostrar Java 23
mvn -version     # Debe mostrar Maven 3.8+
```

### 2️⃣ Insertar Datos de Ejemplo (OPCIONAL)

Si quieres probar el sistema con datos de ejemplo:

```bash
psql -U postgres -d biblioteca_apec -f datos_ejemplo.sql
```

### 3️⃣ Abrir en IntelliJ IDEA

1. Abre IntelliJ IDEA
2. **File → Open**
3. Selecciona la carpeta `biblioteca_apec`
4. Espera a que Maven descargue las dependencias

### 4️⃣ Configurar Tomcat

1. **Run → Edit Configurations**
2. Click en **+** → **Tomcat Server → Local**
3. Configura:
   - **Application server**: Click en "Configure..." y selecciona tu Tomcat 10.1.46
   - **JRE**: Java 23
4. En pestaña **Deployment**:
   - Click en **+** → **Artifact...**
   - Selecciona: **biblioteca_apec:war exploded**
5. Click en **Apply** → **OK**

### 5️⃣ Ejecutar

1. Click en el botón **Run** (▶️) verde
2. Espera a que Tomcat inicie
3. Abre tu navegador en: **http://localhost:8080/biblioteca_apec/**

---

## 🎯 URLs Principales

| Módulo | URL |
|--------|-----|
| Página Principal | http://localhost:8080/biblioteca_apec/ |
| Tipos de Bibliografía | http://localhost:8080/biblioteca_apec/tipos-bibliografia |
| Editoras | http://localhost:8080/biblioteca_apec/editoras |
| Ciencias | http://localhost:8080/biblioteca_apec/ciencias |
| Idiomas | http://localhost:8080/biblioteca_apec/idiomas |
| Autores | http://localhost:8080/biblioteca_apec/autores |
| Libros | http://localhost:8080/biblioteca_apec/libros |
| Usuarios | http://localhost:8080/biblioteca_apec/usuarios |
| Empleados | http://localhost:8080/biblioteca_apec/empleados |
| Préstamos | http://localhost:8080/biblioteca_apec/prestamos |

---

## 📝 Módulos Implementados

| Módulo | Estado | Descripción |
|--------|--------|-------------|
| Tipos de Bibliografía | ✅ COMPLETO | CRUD completo con interfaz |
| Editoras | ✅ COMPLETO | CRUD completo con interfaz |
| Ciencias | ✅ COMPLETO | CRUD completo (backend + vistas) |
| Idiomas | ✅ COMPLETO | CRUD completo (backend + vistas) |
| Autores | ✅ COMPLETO | CRUD completo (backend + vistas) |
| Libros | ✅ COMPLETO | CRUD completo con relaciones |
| Usuarios | ✅ COMPLETO | CRUD completo (backend + vistas) |
| Empleados | ✅ COMPLETO | CRUD completo (backend + vistas) |
| Préstamos | ✅ COMPLETO | CRUD completo con relaciones |

---

## 🛠️ Compilar Manualmente

Si prefieres compilar desde terminal:

```bash
cd biblioteca_apec
mvn clean package
```

El archivo WAR se generará en: `target/biblioteca_apec.war`

---

## 🔧 Solución Rápida de Problemas

### ❌ Error: "Connection refused"
```bash
# Verifica que PostgreSQL esté corriendo
sudo systemctl status postgresql   # Linux
# O abre pgAdmin para verificar
```

### ❌ Error: "Port 8080 already in use"
- Cambia el puerto en la configuración de Tomcat
- O detén el proceso que usa el puerto 8080

### ❌ Error 404 al acceder
- Verifica que Tomcat esté corriendo
- Verifica la URL: debe incluir `/biblioteca_apec/`

### ❌ Página en blanco
- Abre la consola del navegador (F12)
- Verifica logs de Tomcat en IntelliJ

---

## 📚 Funcionalidades Disponibles

- ✅ Crear, editar, eliminar y listar todos los catálogos
- ✅ Gestión completa de libros con relaciones
- ✅ Registro de usuarios y empleados
- ✅ Sistema de préstamos y devoluciones
- ✅ Interfaz responsive con Bootstrap 5
- ✅ Validación de formularios
- ✅ Estados activo/inactivo para todos los registros

---

## 🎓 Próximos Pasos Sugeridos

1. **Reportes y Consultas**
   - Crear módulo de reportes por fecha
   - Reportes por tipo de bibliografía
   - Reportes por idioma
   - Estadísticas de préstamos

2. **Mejoras de UI**
   - Búsqueda y filtros en las tablas
   - Paginación de resultados
   - Gráficos y estadísticas

3. **Funcionalidades Adicionales**
   - Sistema de multas por retraso
   - Reservas de libros
   - Historial de préstamos por usuario
   - Notificaciones de devolución

---

## 📞 Verificar Instalación

Para verificar que todo está configurado correctamente:

```bash
# 1. Verificar Java
java -version

# 2. Verificar Maven
mvn -version

# 3. Verificar PostgreSQL
psql -U postgres -c "SELECT version();"

# 4. Verificar base de datos
psql -U postgres -d biblioteca_apec -c "\dt"
```

---

## 🎉 ¡Listo!

Si todo está configurado correctamente, deberías poder:

1. Ver la página principal con el dashboard
2. Navegar a cada módulo desde el menú superior
3. Crear, editar y eliminar registros
4. Ver las relaciones entre libros, autores, editoras, etc.

**¡Disfruta tu sistema de biblioteca!** 📚
