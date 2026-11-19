fun <T : Comparable<T>> mergeSort(lista: MutableList<T>, izq: Int = 0, der: Int = lista.size - 1) {
    if (izq >= der) return
    val medio = (izq + der) / 2
    mergeSort(lista, izq, medio)           // ordena mitad izquierda
    mergeSort(lista, medio + 1, der)       // ordena mitad derecha
    fusionar(lista, izq, medio, der)       // une ambas mitades
}

private fun <T : Comparable<T>> fusionar(lista: MutableList<T>, izq: Int, medio: Int, der: Int) {
    val izquierda = lista.subList(izq, medio + 1).toMutableList()   // copias auxiliares
    val derecha = lista.subList(medio + 1, der + 1).toMutableList()

    var i = 0; var j = 0; var k = izq
    while (i < izquierda.size && j < derecha.size) {
        if (izquierda[i] <= derecha[j]) lista[k++] = izquierda[i++]
        else lista[k++] = derecha[j++]
    }
    while (i < izquierda.size) lista[k++] = izquierda[i++]   // restos
    while (j < derecha.size) lista[k++] = derecha[j++]
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
    ejecutar("MERGESORT - VEHÍCULOS", GenerarDatos.vehiculos) { mergeSort(it) }
    ejecutar("MERGESORT - NÚMEROS", GenerarDatos.numeros) { mergeSort(it) }
}