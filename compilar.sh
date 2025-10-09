#!/bin/bash

echo "========================================"
echo "Sistema de Biblioteca APEC"
echo "Compilando proyecto con Maven..."
echo "========================================"
echo ""

cd "$(dirname "$0")"

echo "Limpiando proyecto anterior..."
mvn clean

echo ""
echo "Compilando y empaquetando..."
mvn package

echo ""
echo "========================================"
if [ $? -eq 0 ]; then
    echo "COMPILACION EXITOSA!"
    echo ""
    echo "El archivo WAR se encuentra en:"
    echo "target/biblioteca_apec.war"
    echo ""
    echo "Ahora puedes:"
    echo "1. Copiar el WAR a la carpeta webapps de Tomcat"
    echo "2. O ejecutar desde IntelliJ con la configuracion de Tomcat"
else
    echo "ERROR EN LA COMPILACION!"
    echo "Verifica los mensajes de error arriba."
fi
echo "========================================"
echo ""
