-- Script para insertar datos de ejemplo en la base de datos biblioteca_apec
-- Ejecutar después de crear las tablas

-- Tipos de Bibliografía
INSERT INTO tipo_bibliografia (descripcion, estado) VALUES
('Libro', true),
('Revista', true),
('Periódico', true),
('Tesis', true),
('Manual', true);

-- Editoras
INSERT INTO editora (descripcion, estado) VALUES
('Pearson', true),
('McGraw-Hill', true),
('Santillana', true),
('Planeta', true),
('Alfaguara', true);

-- Ciencias
INSERT INTO ciencia (descripcion, estado) VALUES
('Historia', true),
('Biología', true),
('Física', true),
('Química', true),
('Matemáticas', true),
('Literatura', true),
('Ficción', true),
('Tecnología', true);

-- Idiomas
INSERT INTO idioma (descripcion, estado) VALUES
('Español', true),
('Inglés', true),
('Francés', true),
('Alemán', true),
('Italiano', true),
('Portugués', true);

-- Autores
INSERT INTO autor (nombre, pais_origen, idioma_nativo, estado) VALUES
('Gabriel García Márquez', 'Colombia', 1, true),
('William Shakespeare', 'Inglaterra', 2, true),
('Pablo Neruda', 'Chile', 1, true),
('Stephen King', 'Estados Unidos', 2, true),
('Isabel Allende', 'Chile', 1, true);

-- Libros
INSERT INTO libro (descripcion, signatura_topografica, isbn, tipo_bibliografia, autor, editora, anio_publicacion, ciencia, idioma, estado) VALUES
('Cien Años de Soledad', 'COL-FIC-001', '978-0307474728', 1, 1, 4, 1967, 7, 1, true),
('Romeo y Julieta', 'ENG-LIT-001', '978-0743477116', 1, 2, 1, 1597, 6, 2, true),
('Veinte Poemas de Amor', 'CHI-POE-001', '978-0811216876', 1, 3, 3, 1924, 6, 1, true),
('El Resplandor', 'USA-HOR-001', '978-0385121675', 1, 4, 2, 1977, 7, 2, true),
('La Casa de los Espíritus', 'CHI-FIC-001', '978-1501117015', 1, 5, 3, 1982, 7, 1, true);

-- Usuarios
INSERT INTO usuario (nombre, cedula, no_carnet, tipo_persona, estado) VALUES
('Juan Pérez', '001-1234567-8', 'EST-2024-001', 'FISICA', true),
('María López', '001-2345678-9', 'EST-2024-002', 'FISICA', true),
('Carlos Martínez', '001-3456789-0', 'EST-2024-003', 'FISICA', true),
('Universidad APEC', '101-0123456-7', 'ORG-2024-001', 'JURIDICA', true);

-- Empleados
INSERT INTO empleado (nombre, cedula, tanda_labor, porciento_comision, fecha_ingreso, estado) VALUES
('Ana García', '001-4567890-1', 'MATUTINA', 5.00, '2023-01-15', true),
('Pedro Rodríguez', '001-5678901-2', 'VESPERTINA', 5.00, '2023-03-20', true),
('Laura Sánchez', '001-6789012-3', 'NOCTURNA', 5.50, '2023-06-10', true);

-- Préstamos
INSERT INTO prestamo (empleado_id, libro_id, usuario_id, fecha_prestamo, fecha_devolucion, monto_dia, dias_excedidos, comentario, estado) VALUES
(1, 1, 1, '2024-01-10', '2024-01-20', 50.00, 0, 'Préstamo normal', true),
(2, 2, 2, '2024-01-15', '2024-01-25', 50.00, 0, 'Préstamo para investigación', true),
(1, 3, 3, '2024-02-01', NULL, 50.00, 0, 'Préstamo activo', true);

-- Verificar datos insertados
SELECT 'Tipos de Bibliografía' as tabla, COUNT(*) as registros FROM tipo_bibliografia
UNION ALL
SELECT 'Editoras', COUNT(*) FROM editora
UNION ALL
SELECT 'Ciencias', COUNT(*) FROM ciencia
UNION ALL
SELECT 'Idiomas', COUNT(*) FROM idioma
UNION ALL
SELECT 'Autores', COUNT(*) FROM autor
UNION ALL
SELECT 'Libros', COUNT(*) FROM libro
UNION ALL
SELECT 'Usuarios', COUNT(*) FROM usuario
UNION ALL
SELECT 'Empleados', COUNT(*) FROM empleado
UNION ALL
SELECT 'Préstamos', COUNT(*) FROM prestamo;
