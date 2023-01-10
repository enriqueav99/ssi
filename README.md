# TRABAJO SSI
Proyecto con varios microservicos con comunicaciones sincronas

## Cosas que repasar/mejorar:  
- Añadir dentro de los proyectos un directorio repositorio que vaya entre services y dao. Entonces cambiar la cache a esta en vez de servicios  
- Revisar las documentaciones del openAPI(algun PUT habia duda y creo que faltaba algo por el gateway)  
- Hay que mirar el tema de las comunicaciones asincronas y ver como lo podemos meter
- Meter el apartado web y hacerlo sincrono(Pero con Jakarta en vez quarkus)2ºTrabajo

## Lista de cosas implementadas:  
- cache(solo standAlone en socios) Trabajo 1
- flyway  Trabajo 1
- OpenAPI  Trabajo 2
- Docker compose Trabajos
- Todo con quarkus  Trabajos
- Comunicaciones sincronas Trabajos


## Pequeño recordatorio de como funciona git:  
- Primero descargamos el repositorio con: git clone (url del repo) -b (nombre de la branch que queremos)
- Si queremos actualizar un repositorio que tenemos descargado es con: git pull (url del repo) (nombre de branch)  
- Para actualizar el repositorio online con lo que hemos hecho:  
  - primero: git add .
  - luego: git commit -m "aqui un pequeño comentario de lo que hemos hecho"
  - finalmente hacemos: git push origin (nombre de la branch)   

Ten en cuenta que lo de origin es porque tenemos guardada la url del repositorio dentro de nuestro proyecto(en la carpeta .gitIgnore estara por ahi supongo),
si quieres ver las url de repositorios que tienes guardadas es con: git remote -v  
Para añadir uno nuevo es con: git remote add (nombre que le quieres dar, por ejemplo repositorioDeEnrique) (url del repositorio)  
#### Ya sabes, si tienes alguna duda dime
