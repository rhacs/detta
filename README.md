![Contador de Lenguajes](https://img.shields.io/github/languages/count/rhacs/detta?style=flat-square) ![GitHub contributors](https://img.shields.io/github/contributors/rhacs/detta?style=flat-square) ![GitHub last commit](https://img.shields.io/github/last-commit/rhacs/detta?style=flat-square) ![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/rhacs/detta?style=flat-square) ![GitHub top language](https://img.shields.io/github/languages/top/rhacs/detta?style=flat-square) ![GitHub](https://img.shields.io/github/license/rhacs/detta?style=flat-square)

# detta
Este repositorio contiene el desarrollo del ejercicio evolutivo de **Accidentabilidad Laboral** del Módulo 4: Desarrollo de Aplicaciones Web dinámicas con Java de [AwakeLab](https://awakelab.cl)

## Dependencias

### Java
* [JSTL v1.2](https://mvnrepository.com/artifact/jstl/jstl)
* [Oracle Database 19c drivers](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html)

### JavaScript
* [jQuery v3.5.1](https://jquery.com)
* [Popper.js v1.16.1](https://popper.js.org/)
* [Bootstrap v4.5.0](https://getbootstrap.com)
* [FontAwesome v5.13.1](https://fontawesome.com)

## Descripción de los Archivos
Nombre del Archivo | Descripción
------------------ | -----------
src/cl/rhacs/detta/* | Clases Java del proyecto
WebContent/* | Contiene los archivos necesarios para mostrar el contenido en el navegador
WebContent/WEB-INF/web.xml | Descriptor de implementación (Parámetros de configuración del proyecto)
.gitignore | Contiene la información de los archivos/carpetas ignorados por el repositorio
Data Definition Language.sql | Definición de las tablas para la base de datos. **NOTA**: Uso de columna de identidad, por lo que se necesita a lo menos la versión **12c**
LICENSE | Licencia del Proyecto
README.md | Archivo de texto (formato MarkDown) con la descripción del proyecto
pom.xml | Archivo de información y dependencias del proyecto