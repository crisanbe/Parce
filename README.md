# Parce
# Qué es este proyecto?
Una App para crear requerimientos con archivos PDF, y realizar intervenciones,
basado en tres roles,Empresa,Docente y estudiante, en el cual el administrador puede asignarlos por notificacion.

Proyecto
[https://github.com/crisanbe/Parce/tree/feature/main](https://github.com/crisanbe/Parce/tree/feature/main).

![](https://i.imgur.com/YS54hA9h.png)

# Características principales
1. Kotlin
1. Arquitectura Multimodular
1. MVVM
1. Jetpack Compose
1. MutableStateOf
1. MutableSharedFlow
1. UIEvent
    - Simpler
1. Hilt
1. DataStore - Para persistir los datos en el Dispositivo al realizar una consulta
1. Navigation Compose
1. Retrofit
1. Custom Fonts
    - Toggle between themes

# Funciones de composición
1. Snackbars
3. Theming /Modo Oscuro
4. Fonts
5. Colors
    - creating
7. ConstraintLayout
8. Rows
9. Columns
10. Scaffold
11. AppBar
12. Circular Progress Indicator

# Pruebas
1. Junit

#  Flow?
1. Flow
    1. Flow es genial. es una característica de Kotlin coroutines que proporciona una forma asincrónica y reactiva de trabajar con secuencias de datos.
       El método getListRecipeUseCase devuelve un Flow ya que se realiza una operación collect en el resultado. Esto indica que probablemente getListRecipeUseCase retorna un flujo de datos asincrónico que el ViewModel está consumiendo.
       1.(https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/).
1. StateFlow Semántica de Estado:
    1. StateFlow  está diseñado específicamente para representar un estado mutable y proporcionar un flujo de eventos que notifica a los suscriptores cuando el estado cambia. Esto es útil cuando necesitas mantener y compartir un estado mutable en toda tu aplicación. (https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState) in viewmodels.
1. StateFlow Semántica de Estado:
    1. SharedFlow  está diseñado para emitir eventos o notificaciones a sus suscriptores. Es adecuado cuando necesitas comunicar eventos que no necesariamente están relacionados con un estado mutable. (https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState) in viewmodels.


# References
1. https://github.com/android/compose-samples
1. https://developer.android.com/jetpack/compose
1. https://developer.android.com/jetpack/compose/state
