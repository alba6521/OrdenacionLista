/**
 * Ordena `lista` de menor a mayor usando QuickSort.
 *
 * - `<T : Comparable<T>>`: función genérica; T puede ser cualquier tipo comparable (Int, String...).
 * - `MutableList<T>`: lista que se puede modificar; se ordena "in situ" (no se crea otra lista).
 */
fun <T : Comparable<T>> quickSort(lista: MutableList<T>, bajo: Int = 0, alto: Int = lista.size - 1) {
    // Caso base de la recursividad: si bajo >= alto, la zona tiene 0 o 1 elementos
    if (bajo < alto) {
        // Se coloca el pivote en su sitio y se obtiene su posición final `p`.
        val p = particion(lista, bajo, alto)

        quickSort(lista, bajo, p - 1)   // ordena la parte izquierda (los menores)
        quickSort(lista, p + 1, alto)   // ordena la parte derecha (los mayores)
    }
}

/**
 * reorganiza la zona [bajo alto] alrededor de un pivote.
 *
 * Al terminar:  [ elementos < pivote | PIVOTE | elementos >= pivote ]
 *
 * @return la posición final en la que ha quedado el pivote.
 */
private fun <T : Comparable<T>> particion(lista: MutableList<T>, bajo: Int, alto: Int): Int {
    val pivote = lista[alto]
    var i = bajo - 1

    // Se recorre toda la zona excepto el pivote (que está en `alto`).
    for (j in bajo until alto) {
        // Si el elemento actual es menor que el pivote, debe ir a la zona de los menores.
        if (lista[j] < pivote) {
            i++                                              // la zona de menores crece en 1
            val tmp = lista[i]; lista[i] = lista[j]; lista[j] = tmp
        }
    }

    // Por último se coloca el pivote justo detrás del último menor (posición i + 1)
    // intercambiándolo con el elemento que estaba allí.
    val tmp = lista[i + 1]; lista[i + 1] = lista[alto]; lista[alto] = tmp

    return i + 1   // posición definitiva del pivote
}

private fun <T : Comparable<T>> ejecutar(
    titulo: String,
    original: List<T>,
    algoritmo: (MutableList<T>) -> Unit
) {
    // Se crea una COPIA de la lista original. El algoritmo trabajará sobre la copia,
    // de modo que `original` sigue desordenada y podemos mostrarla tal cual estaba.
    val copia = original.toMutableList()

    println("=== $titulo ===")
    println("Lista original:\n$original\n")

    // Medición del tiempo: se anota la hora (en nanosegundos) justo antes y justo después de ordenar.
    val inicio = System.nanoTime()
    algoritmo(copia)
    val fin = System.nanoTime()

    println("Lista ordenada:\n$copia\n")

    // Duración = fin - inicio (ns). Se divide entre 1.000.000 para pasar a milisegundos y "%.4f".format(...) lo muestra con 4 decimales.
    println("Tiempo de ejecución: ${fin - inicio} ns (${"%.4f".format((fin - inicio) / 1_000_000.0)} ms)\n")
}

fun main() {
    ejecutar("QUICKSORT - VEHÍCULOS", GenerarDatos.vehiculos) { quickSort(it) }
    ejecutar("QUICKSORT - NÚMEROS", GenerarDatos.numeros) { quickSort(it) }
}