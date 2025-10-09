@echo off
echo ========================================
echo Sistema de Biblioteca APEC
echo Compilando proyecto con Maven...
echo ========================================
echo.

cd %~dp0

echo Limpiando proyecto anterior...
call mvn clean

echo.
echo Compilando y empaquetando...
call mvn package

echo.
echo ========================================
if %ERRORLEVEL% EQU 0 (
    echo COMPILACION EXITOSA!
    echo.
    echo El archivo WAR se encuentra en:
    echo target\biblioteca_apec.war
    echo.
    echo Ahora puedes:
    echo 1. Copiar el WAR a la carpeta webapps de Tomcat
    echo 2. O ejecutar desde IntelliJ con la configuracion de Tomcat
) else (
    echo ERROR EN LA COMPILACION!
    echo Verifica los mensajes de error arriba.
)
echo ========================================
echo.
pause
