# OrdenacionLista

## Descripción

Cada algoritmo se aplica a dos listas:

- **45 nombres de vehículos** (`MutableList<String>`), ordenados alfabéticamente.
- **100 enteros** entre 1 y 10000, sin repetir (`MutableList<Int>`), ordenados de menor a mayor.

Todos los programas siguen los mismos pasos:

1. Obtienen la lista de `GenerarDatos`.
2. Muestran la lista original.
3. Ordenan **una copia** (la original no se modifica).
4. Muestran la lista ordenada.
5. Miden el tiempo con `System.nanoTime()`.

Los algoritmos son **genéricos** (`fun <T : Comparable<T>> ...(lista: MutableList<T>)`), por lo que el mismo código sirve para `Int` y para `String`.

## Estructura del proyecto

```
UD01_OrdenacionListas/
├── GenerarDatos.kt                          # Generación de las listas (semilla fija)
├── 01_QuickSort.kt                          # QuickSort (obligatorio)
├── 02_PancakeSort.kt                        # Pancake Sort (obligatorio)
├── 03_MergeSort.kt                          # MergeSort (obligatorio)
├── 04_AlgoritmoLibre.kt                     # Heap Sort (libre elección)
├── 05_AlgoritmoLibre.kt                     # Shell Sort (libre elección)
├── UD01_OrdenacionListas_NombreApellido.pdf # Memoria con explicaciones y resultados
└── README.md
```

Cada fichero de algoritmo tiene su propia función `main()` y se ejecuta de forma independiente.

## Algoritmos implementados

| Fichero | Algoritmo | Mejor caso | Caso medio | Peor caso | Memoria extra | Estable |
|---|---|---|---|---|---|---|
| `01_QuickSort.kt` | QuickSort (partición de Lomuto) | O(n log n) | O(n log n) | O(n²) | O(log n) pila | No |
| `02_PancakeSort.kt` | Pancake Sort | O(n²) | O(n²) | O(n²) | O(1) | No |
| `03_MergeSort.kt` | MergeSort | O(n log n) | O(n log n) | O(n log n) | O(n) | Sí |
| `04_AlgoritmoLibre.kt` | Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No |
| `05_AlgoritmoLibre.kt` | Shell Sort (saltos n/2, n/4…) | O(n log n) | depende de los saltos | O(n²) | O(1) | No |

**Breve descripción de cada uno:**

- **QuickSort:** divide y vencerás. Toma el último elemento como pivote, coloca los menores a su izquierda y los mayores a su derecha, y repite recursivamente.
- **Pancake Sort:** la única operación es *voltear* un prefijo de la lista. En cada pasada lleva el mayor de la zona no ordenada a la cima y luego al fondo. El volteo se implementa a mano (sin `reverse()`).
- **MergeSort:** divide la lista por la mitad hasta tener sublistas de 1 elemento y las fusiona de forma ordenada. Usa `<=` en la fusión para mantener la estabilidad.
- **Heap Sort:** construye un montículo máximo dentro de la propia lista (hijos en `2i+1` y `2i+2`) y extrae repetidamente el máximo.
- **Shell Sort:** inserción con saltos (*gap*) que parten de `n/2` y se reducen a la mitad hasta llegar a 1.

## Datos de prueba

`GenerarDatos` es un `object` (singleton) que usa una **semilla fija (`2026`)**. Así las listas son aleatorias pero **idénticas en cada ejecución y en cada algoritmo**, lo que hace comparables los tiempos.

- Vehículos: 45 nombres distintos mezclados con `shuffle(Random(2026))`.
- Números: `(1..10000).shuffled(Random(2026)).take(100)`, lo que garantiza que no hay repetidos.

Para ver solo las listas generadas, ejecuta `main()` de `GenerarDatos.kt`.

## Requisitos y ejecución

**Requisitos**

- JDK 11 o superior
- Kotlin 
