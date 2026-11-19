/**
 * 05_AlgoritmoLibre.kt  ->  SHELL SORT
 * Mejora de la ordenación por inserción: compara y desplaza elementos separados por
 * un "salto" (gap) que empieza en n/2 y se reduce a la mitad hasta llegar a 1.
 * Al llegar a gap = 1 la lista ya está casi ordenada y la inserción es muy rápida.
 * Coste según la secuencia de saltos; con n/2, n/4... es O(n²) en el peor caso,
 * pero en la práctica va muy por encima de la inserción simple.
 */

fun <T : Comparable<T>> shellSort(lista: MutableList<T>) {
    var salto = lista.size / 2
    while (salto > 0) {
        for (i in salto until lista.size) {
            val actual = lista[i]
            var j = i
            while (j >= salto && lista[j - salto] > actual) {
                lista[j] = lista[j - salto]     // desplaza hacia la derecha
                j -= salto
            }
            lista[j] = actual                   // inserta en su hueco
        }
        salto /= 2
    }
}

private fun <T : Comparable<T>> ejecutar(
    titulo: String,
    original: List<T>,
    algoritmo: (MutableList<T>) -> Unit
) {
    val copia = original.toMutableList()
    println("=== $titulo ===")
    println("Lista original:\n$original\n")

    val inicio = System.nanoTime()
    algoritmo(copia)
    val fin = System.nanoTime()

    println("Lista ordenada:\n$copia\n")
    println("Tiempo de ejecución: ${fin - inicio} ns (${"%.4f".format((fin - inicio) / 1_000_000.0)} ms)\n")
}

fun main() {
    ejecutar("SHELL SORT - VEHÍCULOS", GenerarDatos.vehiculos) { shellSort(it) }
    ejecutar("SHELL SORT - NÚMEROS", GenerarDatos.numeros) { shellSort(it) }
}