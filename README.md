# 1. **Activitys**

#### PreLoadActivity:
 - Carga de la API los datos necesarios.

#### MainActivity:
- Carrusel gira mediante un hilo cada 6s.
- Separación de diferentes categorías en las series. Las series se pueden repetir ya que tienen más de un género.
- Si hay películas favoritas aparecerá una fila extra.
- Este activity carga tambien las shared preferences.
- Haciendo clic en la lupa accedemos al SearchActivity.
- Haciendo clic en cualquier imagen de serie cargara el MovieDetails de su respectiva serie.

#### SearchActivity:
- Para buscar una serie después de haber buscado otra o estar buscando una, se debe pulsar la X que aparece en el buscador para buscar la nueva serie.
- Aparecen tarjetas con información superficial de la serie. Haciendo clic en estas cargara el MovieDetails de su respectiva serie.

#### MovieDatails:
- Activity que muestra los datos de la pelicula seleccionada.


# 2. **Funcionalidaes:**
- Se puede hacer click en cualquier imagen de cualquier activity para acceder a los detalles de la pelicula.
- Se puede buscar la pelicula segun su genero en las listas prediseñadas del Main activity.
- Se pueden buscar peliculas segun su nombre en el buscador.
- En el MovieDetailsActivity haciendo clic en el corazón, se puede marcar esa serie como favorita. Siempre aparecerá como favorita hasta que volvamos a hacer clic.


# 3. **Posibles problemas al importar el proyecto:**
- Es posible que los modulos del proyecto no esten sincronizados, para sincronizarlos hay que hacer click en el siguiente boton.
![image](https://user-images.githubusercontent.com/72077646/203274527-17cdd783-361e-4b82-a4f3-5ab712204b64.png)


#### Github del Proyecto https://github.com/kolaso13/SinemaProject.git

