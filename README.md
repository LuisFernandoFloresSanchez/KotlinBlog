# CRUD-Retrofit-Jetpack-Compose

Examen de android

Para utilizarlo se debe correr el servidor node.js en la maquina local y correr un emulador android dentro de android studio, las instrucciones de instalacion son para una Mac.

## Instalacion de node

1) Asegúrate de tener Node.js instalado en tu sistema. Puedes descargarlo desde https://nodejs.org/.

## Instalacion SQL

1) Primero se debe contar con sql instalado en el equipo
   Ejecuta el siguiente comando en la Terminal para instalar MySQL con Homebrew:

### brew install mysql

2) Iniciar el Servicio MySQL:
   Una vez que la instalación esté completa, puedes iniciar el servicio MySQL con el siguiente comando:

### brew services start mysql

3) Verificar el Estado de MySQL:
   Puedes verificar si MySQL está ejecutándose utilizando:

### brew services list

Asegúrate de que MySQL esté marcado como "started".

4) Abrir consola SQL:
   Deberas abrir la consola desde cualquier terminal de la mac con el siguiente comando:

### mysql -u tu_usuario -p

Reemplaza tu_usuario con el nombre de usuario de MySQL al que deseas acceder. Después de ejecutar este comando, se te pedirá que ingreses la contraseña correspondiente al usuario.

Sera importante cambiar el usuario y la contrasenia del archivo index.js en las lineas 21 y 22 por el usuario y la contrasenia correspondiente o si no usar la contrasenia de root y usuario root que estan por defecto si es que asi esta configurado.

5) Importa el Archivo SQL:
   Ahora, utiliza el comando source para ejecutar el contenido del archivo SQL dentro de la consola de MySQL:

### source /ruta/al/archivo/db_examen.sql;

Asegúrate de reemplazar /ruta/al/archivo/ con la ruta completa hasta el directorio que contiene tu archivo db_examen.sql
Si el archivo está en el mismo directorio donde ejecutaste el comando, puedes usar:

### source db_examen.sql;

Esto ejecutará todas las instrucciones SQL del archivo y creará las tablas y datos en la base de datos "examen".
En caso de no lograr crearla revisar la seccion de errores.

5) Salir de la Consola de MySQL:
  Cuando hayas terminado, puedes salir de la consola de MySQL:

### exit;

Después de realizar estos pasos, deberías tener la base de datos "examen" y sus tablas creadas en tu servidor MySQL local. 


## Servidor node.js

1) Abrir una terminal y dirigirse a la carpeta examen_node

2) Ejecutar el siguiente comando para descargar las dependencias
### npm install

3) Ejecutar el siguiente comando para lanzar el servidor esto funcionara solo si la base de datos esta creada.
### npm run dev

## Instalacion de aplicacion

1) Instale en un emulador dentro del pc que tiene corriendo el servidor node y creada la base de datos, una vez creada sera posible ver la informacion de la base de datos dentro del emulador.

## Errores posibles

1) ## ER_NOT_SUPPORTED_AUTH_MODE

En caso de que al correr el servidor y sea la primera vez que se instala sql en la mac no puedes tener una base de datos con user root y sin contrasenia por lo tanto hay que actualizarlo:

Inicia la Sesión en MySQL:
Una vez que la terminal esté abierta, ejecuta el siguiente comando y presiona Enter:

### mysql -u tu_usuario -p

Reemplaza tu_usuario con el nombre de usuario de MySQL al que deseas acceder. Después de ejecutar este comando, se te pedirá que ingreses la contraseña correspondiente al usuario.

Si MySQL está instalado en tu sistema y el usuario y contraseña son correctos, deberías entrar en la consola de MySQL.

Ejecuta el Comando ALTER USER:
Ahora, dentro de la consola de MySQL, ejecuta el comando ALTER USER para ajustar la configuración del usuario y cambiar al método de autenticación mysql_native_password:

### ALTER USER 'tu_usuario'@'localhost' IDENTIFIED WITH 'mysql_native_password' BY 'tu_contraseña';

Reemplaza 'tu_usuario' y 'tu_contraseña' con tu nombre de usuario y contraseña de MySQL. Es posible que si acabas de instalar tu sql el usuario sea root y password ''.

Después de realizar estos pasos, intenta reiniciar tu aplicación Node.js

2) ## ER_BAD_DB_ERROR  Unknown database 'examen'

Este error puede deberse a que no se ejecuto correctamente el archivo db_examen.sql regresar a la instalacion de sql a partir del paso 4.

3) ## Error por contrasenia incorrecta en DB

El archivo index.js de node esta configurado en la linea 22 para que la contrasenia sea root, si tienes otra contrasenia o esta en blanco la contrasenia tendra error, es necesario cambiar esa linea por la contrasenia especificada.

4) ## java.net.ConnectException: Failed to connect to /10.0.2.2:3000

Significa que tu servidor no esta corriendo adecuadamente.