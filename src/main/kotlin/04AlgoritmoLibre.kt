/**
 * 04_AlgoritmoLibre.kt  ->  HEAP SORT (ordenación por montículo)
 * 1) Se convierte la lista en un montículo máximo (el padre siempre >= sus hijos).
 * 2) Se intercambia la raíz (el mayor) con el último elemento de la zona activa,
 *    se reduce la zona activa y se "hunde" la nueva raíz para restaurar el montículo.
 */

fun <T : Comparable<T>> heapSort(lista: MutableList<T>) {
    val n = lista.size
    // construir el montículo (desde el último padre hasta la raíz)
    for (i in n / 2 - 1 downTo 0) hundir(lista, n, i)
    //  extraer el máximo repetidamente
    for (fin in n - 1 downTo 1) {
        val tmp = lista[0]; lista[0] = lista[fin]; lista[fin] = tmp
        hundir(lista, fin, 0)
    }
}

/** Hunde el elemento de la posición i dentro de un montículo de tamaño 'tam'. */
private fun <T : Comparable<T>> hundir(lista: MutableList<T>, tam: Int, inicio: Int) {
    var i = inicio
    while (true) {
        val izq = 2 * i + 1
        val der = 2 * i + 2
        var mayor = i
        if (izq < tam && lista[izq] > lista[mayor]) mayor = izq
        if (der < tam && lista[der] > lista[mayor]) mayor = der
        if (mayor == i) return
        val tmp = lista[i]; lista[i] = lista[mayor]; lista[mayor] = tmp
        i = mayor
    }
}

private fun <T : Comparable<T>> ejecutar(
    titulo: String,
    original: List<T>,
    algoritmo: (MutableList<T>) -> Unit
) {
    val copia = original.toMutableList()          // copia: el original no se toca
    println("=== $titulo ===")
    println("Lista original:\n$original\n")

    val inicio = System.nanoTime()
    algoritmo(copia)
    val fin = System.nanoTime()

    println("Lista ordenada:\n$copia\n")
    println("Tiempo de ejecución: ${fin - inicio} ns (${"%.4f".format((fin - inicio) / 1_000_000.0)} ms)\n")
}

fun main() {
    ejecutar("HEAP SORT - VEHÍCULOS", GenerarDatos.vehiculos) { heapSort(it) }
    ejecutar("HEAP SORT - NÚMEROS", GenerarDatos.numeros) { heapSort(it) }
}