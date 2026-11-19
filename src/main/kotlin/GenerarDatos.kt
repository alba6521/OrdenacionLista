import kotlin.random.Random

/**
 * Parte 1 - Generación de datos.
 *
 * Se usa una semilla fija (SEMILLA) para que las listas sean SIEMPRE las mismas
 * en cada ejecución y en cada algoritmo. Así todos trabajan con los mismos datos
 * y los tiempos obtenidos son comparables.
 *
 * Al ser un "object", las listas se crean una sola vez por ejecución .
 */
object GenerarDatos {

    private const val SEMILLA = 2026

    /** Lista de 45 vehículos distintos, mezclada (desordenada alfabéticamente). */
    val vehiculos: MutableList<String> = mutableListOf(
        "Moto", "Coche", "Camión", "Autobús", "Bicicleta", "Furgoneta", "Tractor",
        "Patinete", "Tranvía", "Metro", "Tren", "Avión", "Helicóptero", "Barco",
        "Velero", "Yate", "Submarino", "Globo aerostático", "Ambulancia", "Taxi",
        "Quad", "Scooter", "Caravana", "Remolque", "Excavadora", "Grúa", "Carretilla",
        "Monopatín", "Segway", "Trineo", "Kayak", "Canoa", "Lancha", "Ferry",
        "Dron", "Hovercraft", "Teleférico", "Funicular", "Limusina", "Descapotable",
        "Pickup", "Todoterreno", "Bulldozer", "Hidropedal", "Autocaravana"
    ).also { it.shuffle(Random(SEMILLA)) }

    /** 100 enteros aleatorios entre 1 y 10000, sin repetir y desordenados. */
    val numeros: MutableList<Int> = (1..10000)
        .shuffled(Random(SEMILLA))   // los 10000 valores sin repetir, mezclados
        .take(100)                   // nos quedamos con 100
        .toMutableList()
}

fun main() {
    println("=== LISTA DE VEHÍCULOS (${GenerarDatos.vehiculos.size}) ===")
    println(GenerarDatos.vehiculos)
    println()
    println("=== LISTA NUMÉRICA (${GenerarDatos.numeros.size}) ===")
    println(GenerarDatos.numeros)
}