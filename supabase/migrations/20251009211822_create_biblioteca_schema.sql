/*
  # Sistema de Gestión de Biblioteca Universitaria APEC
  
  1. Nuevas Tablas
    - `tipos_bibliografia` - Catálogo de tipos (Libro, Revista, Periódico, etc.)
    - `editoras` - Catálogo de editoriales
    - `ciencias` - Catálogo de áreas del conocimiento (Historia, Biología, etc.)
    - `idiomas` - Catálogo de idiomas
    - `autores` - Catálogo de autores
    - `usuarios` - Usuarios de la biblioteca (estudiantes, profesores)
    - `empleados` - Personal que atiende la biblioteca
    - `libros` - Catálogo de libros con toda su información
    - `prestamos` - Registro de préstamos y devoluciones
    
  2. Seguridad
    - RLS habilitado en todas las tablas
    - Políticas para usuarios autenticados
    
  3. Relaciones
    - libros -> tipos_bibliografia
    - libros -> editoras
    - libros -> ciencias
    - libros -> idiomas
    - libros -> autores
    - prestamos -> libros
    - prestamos -> usuarios
    - prestamos -> empleados (quien asiste)
*/

-- Tabla: Tipos de Bibliografía
CREATE TABLE IF NOT EXISTS tipos_bibliografia (
  id_tipo_bibliografia SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL UNIQUE,
  descripcion TEXT,
  activo BOOLEAN DEFAULT true,
  fecha_creacion TIMESTAMPTZ DEFAULT now(),
  fecha_modificacion TIMESTAMPTZ DEFAULT now()
);

-- Tabla: Editoras
CREATE TABLE IF NOT EXISTS editoras (
  id_editora SERIAL PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  pais VARCHAR(100),
  sitio_web VARCHAR(255),
  activo BOOLEAN DEFAULT true,
  fecha_creacion TIMESTAMPTZ DEFAULT now(),
  fecha_modificacion TIMESTAMPTZ DEFAULT now()
);

-- Tabla: Ciencias (Áreas del conocimiento)
CREATE TABLE IF NOT EXISTS ciencias (
  id_ciencia SERIAL PRIMARY KEY,
  nombre VARCHAR(150) NOT NULL UNIQUE,
  descripcion TEXT,
  activo BOOLEAN DEFAULT true,
  fecha_creacion TIMESTAMPTZ DEFAULT now(),
  fecha_modificacion TIMESTAMPTZ DEFAULT now()
);

-- Tabla: Idiomas
CREATE TABLE IF NOT EXISTS idiomas (
  id_idioma SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL UNIQUE,
  codigo_iso VARCHAR(3),
  activo BOOLEAN DEFAULT true,
  fecha_creacion TIMESTAMPTZ DEFAULT now(),
  fecha_modificacion TIMESTAMPTZ DEFAULT now()
);

-- Tabla: Autores
CREATE TABLE IF NOT EXISTS autores (
  id_autor SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  nacionalidad VARCHAR(100),
  biografia TEXT,
  fecha_nacimiento DATE,
  activo BOOLEAN DEFAULT true,
  fecha_creacion TIMESTAMPTZ DEFAULT now(),
  fecha_modificacion TIMESTAMPTZ DEFAULT now()
);

-- Tabla: Usuarios (Estudiantes, Profesores, etc.)
CREATE TABLE IF NOT EXISTS usuarios (
  id_usuario SERIAL PRIMARY KEY,
  cedula VARCHAR(20) NOT NULL UNIQUE,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE,
  telefono VARCHAR(20),
  direccion TEXT,
  tipo_usuario VARCHAR(50) DEFAULT 'Estudiante',
  activo BOOLEAN DEFAULT true,
  fecha_creacion TIMESTAMPTZ DEFAULT now(),
  fecha_modificacion TIMESTAMPTZ DEFAULT now()
);

-- Tabla: Empleados
CREATE TABLE IF NOT EXISTS empleados (
  id_empleado SERIAL PRIMARY KEY,
  cedula VARCHAR(20) NOT NULL UNIQUE,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE,
  telefono VARCHAR(20),
  cargo VARCHAR(100) DEFAULT 'Bibliotecario',
  activo BOOLEAN DEFAULT true,
  fecha_creacion TIMESTAMPTZ DEFAULT now(),
  fecha_modificacion TIMESTAMPTZ DEFAULT now()
);

-- Tabla: Libros
CREATE TABLE IF NOT EXISTS libros (
  id_libro SERIAL PRIMARY KEY,
  isbn VARCHAR(20) UNIQUE,
  titulo VARCHAR(300) NOT NULL,
  subtitulo VARCHAR(300),
  id_tipo_bibliografia INTEGER REFERENCES tipos_bibliografia(id_tipo_bibliografia),
  id_editora INTEGER REFERENCES editoras(id_editora),
  id_ciencia INTEGER REFERENCES ciencias(id_ciencia),
  id_idioma INTEGER REFERENCES idiomas(id_idioma),
  id_autor INTEGER REFERENCES autores(id_autor),
  año_publicacion INTEGER,
  edicion VARCHAR(50),
  numero_paginas INTEGER,
  ubicacion_fisica VARCHAR(100),
  cantidad_disponible INTEGER DEFAULT 1,
  descripcion TEXT,
  activo BOOLEAN DEFAULT true,
  fecha_creacion TIMESTAMPTZ DEFAULT now(),
  fecha_modificacion TIMESTAMPTZ DEFAULT now()
);

-- Tabla: Préstamos
CREATE TABLE IF NOT EXISTS prestamos (
  id_prestamo SERIAL PRIMARY KEY,
  id_libro INTEGER NOT NULL REFERENCES libros(id_libro),
  id_usuario INTEGER NOT NULL REFERENCES usuarios(id_usuario),
  id_empleado INTEGER NOT NULL REFERENCES empleados(id_empleado),
  fecha_prestamo TIMESTAMPTZ DEFAULT now(),
  fecha_devolucion_esperada DATE NOT NULL,
  fecha_devolucion_real TIMESTAMPTZ,
  estado VARCHAR(50) DEFAULT 'Prestado',
  observaciones TEXT,
  fecha_creacion TIMESTAMPTZ DEFAULT now(),
  fecha_modificacion TIMESTAMPTZ DEFAULT now()
);

-- Habilitar RLS en todas las tablas
ALTER TABLE tipos_bibliografia ENABLE ROW LEVEL SECURITY;
ALTER TABLE editoras ENABLE ROW LEVEL SECURITY;
ALTER TABLE ciencias ENABLE ROW LEVEL SECURITY;
ALTER TABLE idiomas ENABLE ROW LEVEL SECURITY;
ALTER TABLE autores ENABLE ROW LEVEL SECURITY;
ALTER TABLE usuarios ENABLE ROW LEVEL SECURITY;
ALTER TABLE empleados ENABLE ROW LEVEL SECURITY;
ALTER TABLE libros ENABLE ROW LEVEL SECURITY;
ALTER TABLE prestamos ENABLE ROW LEVEL SECURITY;

-- Políticas para tipos_bibliografia
CREATE POLICY "Permitir lectura pública de tipos_bibliografia"
  ON tipos_bibliografia FOR SELECT
  TO authenticated
  USING (true);

CREATE POLICY "Permitir inserción de tipos_bibliografia"
  ON tipos_bibliografia FOR INSERT
  TO authenticated
  WITH CHECK (true);

CREATE POLICY "Permitir actualización de tipos_bibliografia"
  ON tipos_bibliografia FOR UPDATE
  TO authenticated
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Permitir eliminación de tipos_bibliografia"
  ON tipos_bibliografia FOR DELETE
  TO authenticated
  USING (true);

-- Políticas para editoras
CREATE POLICY "Permitir lectura pública de editoras"
  ON editoras FOR SELECT
  TO authenticated
  USING (true);

CREATE POLICY "Permitir inserción de editoras"
  ON editoras FOR INSERT
  TO authenticated
  WITH CHECK (true);

CREATE POLICY "Permitir actualización de editoras"
  ON editoras FOR UPDATE
  TO authenticated
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Permitir eliminación de editoras"
  ON editoras FOR DELETE
  TO authenticated
  USING (true);

-- Políticas para ciencias
CREATE POLICY "Permitir lectura pública de ciencias"
  ON ciencias FOR SELECT
  TO authenticated
  USING (true);

CREATE POLICY "Permitir inserción de ciencias"
  ON ciencias FOR INSERT
  TO authenticated
  WITH CHECK (true);

CREATE POLICY "Permitir actualización de ciencias"
  ON ciencias FOR UPDATE
  TO authenticated
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Permitir eliminación de ciencias"
  ON ciencias FOR DELETE
  TO authenticated
  USING (true);

-- Políticas para idiomas
CREATE POLICY "Permitir lectura pública de idiomas"
  ON idiomas FOR SELECT
  TO authenticated
  USING (true);

CREATE POLICY "Permitir inserción de idiomas"
  ON idiomas FOR INSERT
  TO authenticated
  WITH CHECK (true);

CREATE POLICY "Permitir actualización de idiomas"
  ON idiomas FOR UPDATE
  TO authenticated
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Permitir eliminación de idiomas"
  ON idiomas FOR DELETE
  TO authenticated
  USING (true);

-- Políticas para autores
CREATE POLICY "Permitir lectura pública de autores"
  ON autores FOR SELECT
  TO authenticated
  USING (true);

CREATE POLICY "Permitir inserción de autores"
  ON autores FOR INSERT
  TO authenticated
  WITH CHECK (true);

CREATE POLICY "Permitir actualización de autores"
  ON autores FOR UPDATE
  TO authenticated
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Permitir eliminación de autores"
  ON autores FOR DELETE
  TO authenticated
  USING (true);

-- Políticas para usuarios
CREATE POLICY "Permitir lectura pública de usuarios"
  ON usuarios FOR SELECT
  TO authenticated
  USING (true);

CREATE POLICY "Permitir inserción de usuarios"
  ON usuarios FOR INSERT
  TO authenticated
  WITH CHECK (true);

CREATE POLICY "Permitir actualización de usuarios"
  ON usuarios FOR UPDATE
  TO authenticated
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Permitir eliminación de usuarios"
  ON usuarios FOR DELETE
  TO authenticated
  USING (true);

-- Políticas para empleados
CREATE POLICY "Permitir lectura pública de empleados"
  ON empleados FOR SELECT
  TO authenticated
  USING (true);

CREATE POLICY "Permitir inserción de empleados"
  ON empleados FOR INSERT
  TO authenticated
  WITH CHECK (true);

CREATE POLICY "Permitir actualización de empleados"
  ON empleados FOR UPDATE
  TO authenticated
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Permitir eliminación de empleados"
  ON empleados FOR DELETE
  TO authenticated
  USING (true);

-- Políticas para libros
CREATE POLICY "Permitir lectura pública de libros"
  ON libros FOR SELECT
  TO authenticated
  USING (true);

CREATE POLICY "Permitir inserción de libros"
  ON libros FOR INSERT
  TO authenticated
  WITH CHECK (true);

CREATE POLICY "Permitir actualización de libros"
  ON libros FOR UPDATE
  TO authenticated
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Permitir eliminación de libros"
  ON libros FOR DELETE
  TO authenticated
  USING (true);

-- Políticas para prestamos
CREATE POLICY "Permitir lectura pública de prestamos"
  ON prestamos FOR SELECT
  TO authenticated
  USING (true);

CREATE POLICY "Permitir inserción de prestamos"
  ON prestamos FOR INSERT
  TO authenticated
  WITH CHECK (true);

CREATE POLICY "Permitir actualización de prestamos"
  ON prestamos FOR UPDATE
  TO authenticated
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Permitir eliminación de prestamos"
  ON prestamos FOR DELETE
  TO authenticated
  USING (true);

-- Insertar datos iniciales
INSERT INTO tipos_bibliografia (nombre, descripcion) VALUES
('Libro', 'Publicación impresa o digital en formato de libro'),
('Revista', 'Publicación periódica con artículos variados'),
('Periódico', 'Publicación diaria o semanal de noticias'),
('Tesis', 'Trabajo de investigación académica'),
('Manual', 'Guía práctica o instructivo')
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO ciencias (nombre, descripcion) VALUES
('Historia', 'Estudio de eventos del pasado'),
('Biología', 'Ciencia de la vida y organismos vivos'),
('Física', 'Estudio de la materia y energía'),
('Matemáticas', 'Ciencia de los números y formas'),
('Literatura', 'Arte de la expresión escrita'),
('Ficción', 'Narrativa imaginaria'),
('Ingeniería', 'Aplicación de ciencias para diseño y construcción'),
('Medicina', 'Ciencia de la salud humana')
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO idiomas (nombre, codigo_iso) VALUES
('Español', 'ESP'),
('Inglés', 'ENG'),
('Francés', 'FRA'),
('Alemán', 'DEU'),
('Italiano', 'ITA')
ON CONFLICT (nombre) DO NOTHING;
