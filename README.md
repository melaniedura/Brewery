<h1 align="center">Brewery</h1>

Aplicación Android que mediante el uso de la API <a href="http://www.brewerydb.com">Brewery DB</a> permite:

* Obtener una lista de estilos de cerveza y mostrarla. Al pulsar sobre una de estas categorías se accederá a una vista tipo grid.
* La vista tipo grid contiene las cervezas del estilo seleccionado seleccionada en el paso anterior. Cada item contiene el nombre de la cerveza y su imagen.
* Al pulsar sobre uno de los items, mostrar una vista detalle que con la foto, el nombre y la descripción de la cerveza, si la tiene.
* La aplicación permite guardar cervezas como favoritas.
* La aplicación permite hacer zoom sobre la imagen de una cerveza en la pantalla detalle.

## Construido con 🛠️
* [Retrofit](http://square.github.io/retrofit/) + [OkHttp](http://square.github.io/okhttp/) 
* [Hilt](https://dagger.dev/hilt/) - Inyección de dependencias.
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) 
    * [Room](https://developer.android.com/training/data-storage/room) - Librería para BBDD
    * [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel) 
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) 
    * [Navigation component](https://developer.android.com/guide/navigation) 
* [Glide](https://github.com/bumptech/glide) - Librería para cargar imágenes.
* [PhotoView](https://github.com/chrisbanes/PhotoView) - Zoom en ImageView.
* [Coroutines](https://developer.android.com/kotlin/coroutines) 

## Arquitectura

![MVVM](./app-architecture.png)

Para el desarrollo de la aplicación se ha seguido la [Guía de arquitectura de apps](https://developer.android.com/jetpack/guide) con una arquitectura MVVM y repository pattern.

## Package Structure

```
com.melaniedura.brewery 
├── database            
|
├── di                  
|
├── model               
|
├── network
│   ├── model           
│   ├── response          
│   └── service            
|
├── repository               
│
├── ui                 
│   ├── beerdetails          
│   ├── beers            
│   └── beerstyles       
|
├── util                
└── BreweryApp         

```


## API Key 🔑
Para ejecutar la aplicación, necesitaremos una API key de BreweryDB.
* Generar una nueva API key en [BreweryDB](https://brewerydb.com/).
* Añadir la clave en gradle.properties

```
API_KEY="ee45********************8fgc90"
```

* Añadir la clave en build config en `./app/build.gradle`:

```kotlin
defaultConfig {
    ...
    buildConfigField("String", "API_KEY", API_KEY)
    ...
}
```
