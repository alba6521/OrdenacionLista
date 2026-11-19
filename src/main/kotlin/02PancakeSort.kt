
/**Ordena `lista` de menor a mayor usando Pancake Sort (se modifica la propia lista).*/
fun <T : Comparable<T>> pancakeSort(lista: MutableList<T>) {
    // `tam` es el tamaño de la parte DESORDENADA (índices 0 .. tam-1).
    // Lo que queda a la derecha de `tam` ya está ordenado y no se vuelve a tocar.
    var tam = lista.size

    while (tam > 1) {
        // 1) Buscar la posición del mayor elemento dentro de lista[0 until tam]
        var posMax = 0
        for (i in 1 until tam) {
            if (lista[i] > lista[posMax]) posMax = i   // ...y se actualiza si aparece uno mayor
        }

        // 2) y 3) Colocar el mayor al final de la zona desordenada.
        // Si ya está en la última posición (tam - 1), no hace falta hacer nada.
        if (posMax != tam - 1) {
            // Si ya está arriba del todo (posición 0) se salta el primer volteo.
            if (posMax != 0) voltear(lista, posMax)   // volteo 1: el mayor pasa a la cima (posición 0)
            voltear(lista, tam - 1)                   // volteo 2: el mayor baja al fondo (posición tam-1)
        }
        tam--
    }
}

/**
 * Invierte el orden de los elementos de la posición 0 a la k (ambas incluidas).
 * Es "dar la vuelta a las k+1 tortitas de arriba".
 * Ejemplo: [1, 2, 3, 4, 5] voltear(lista, 2) -> [3, 2, 1, 4, 5]
 *
 * Aquí T no necesita ser Comparable porque solo se intercambian posiciones, no se compara.
 */
private fun <T> voltear(lista: MutableList<T>, k: Int) {
    var i = 0   // puntero que avanza desde el principio
    var j = k
    // Se intercambian los extremos y se van acercando hasta cruzarse en el centro.
    while (i < j) {
        val tmp = lista[i]; lista[i] = lista[j]; lista[j] = tmp   // intercambio (swap)
        i++; j--
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

    // Duración = fin - inicio (ns). Se divide entre 1.000.000 para pasar a milisegundos y "%.4f".format(...) lo muestra con 4 decimales.
    println("Tiempo de ejecución: ${fin - inicio} ns (${"%.4f".format((fin - inicio) / 1_000_000.0)} ms)\n")
}

fun main() {
    ejecutar("PANCAKE SORT - VEHÍCULOS", GenerarDatos.vehiculos) { pancakeSort(it) }
    ejecutar("PANCAKE SORT - NÚMEROS", GenerarDatos.numeros) { pancakeSort(it) }
}