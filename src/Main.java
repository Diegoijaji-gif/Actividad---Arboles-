import java.util.Scanner;

public class Main {

    // ========== CLASE INTERNA: NODO ==========
    // Representa cada nodo del árbol binario
    static class Nodo {
        int valor;           // El dato que almacena el nodo
        Nodo izquierda;      // Referencia al hijo izquierdo
        Nodo derecha;        // Referencia al hijo derecho

        // Constructor que inicializa un nodo con un valor
        Nodo(int valor) {
            this.valor = valor;
            this.izquierda = null;   // Inicialmente sin hijo izquierdo
            this.derecha = null;     // Inicialmente sin hijo derecho
        }
    }

    // ========== CLASE INTERNA: ÁRBOL BINARIO ==========
    static class ArbolBinario {
        private Nodo raiz;  // La raíz es el primer nodo del árbol

        // Constructor que inicializa un árbol vacío
        ArbolBinario() {
            this.raiz = null;
        }

        // ===== MÉTODO INSERTAR =====
        // Inserta un número en el árbol manteniendo el orden:
        // Números menores a la izquierda, mayores a la derecha
        public void insertar(int valor) {
            raiz = insertarRecursivo(raiz, valor);
        }

        // Método recursivo que inserta un valor en el árbol
        private Nodo insertarRecursivo(Nodo nodo, int valor) {
            // CASO BASE: si el nodo es nulo, creamos un nuevo nodo
            if (nodo == null) {
                System.out.println("✓ Número " + valor + " insertado correctamente.");
                return new Nodo(valor);
            }

            // Si el valor es menor que el del nodo actual, va hacia la izquierda
            if (valor < nodo.valor) {
                nodo.izquierda = insertarRecursivo(nodo.izquierda, valor);
            }
            // Si el valor es mayor que el del nodo actual, va hacia la derecha
            else if (valor > nodo.valor) {
                nodo.derecha = insertarRecursivo(nodo.derecha, valor);
            }
            // Si es igual, no lo insertamos (evitamos duplicados)
            else {
                System.out.println("⚠ El número " + valor + " ya existe en el árbol.");
            }

            return nodo;
        }

        // ===== MÉTODO MOSTRAR INORDEN =====
        // Recorre el árbol en orden: izquierda -> nodo -> derecha
        // Esto muestra los números ordenados de menor a mayor
        public void mostrarInorden() {
            if (raiz == null) {
                System.out.println("El árbol está vacío.");
            } else {
                System.out.print("Números en orden: ");
                inordenRecursivo(raiz);
                System.out.println();
            }
        }

        // Método recursivo que realiza el recorrido inorden
        private void inordenRecursivo(Nodo nodo) {
            if (nodo != null) {
                // 1. Procesa el subárbol izquierdo
                inordenRecursivo(nodo.izquierda);

                // 2. Muestra el valor del nodo actual
                System.out.print(nodo.valor + " ");

                // 3. Procesa el subárbol derecho
                inordenRecursivo(nodo.derecha);
            }
        }

        // ===== MÉTODO BUSCAR =====
        // Busca un número específico en el árbol
        // Retorna true si existe, false si no existe
        public boolean buscar(int valor) {
            return buscarRecursivo(raiz, valor);
        }

        // Método recursivo que busca un valor en el árbol
        private boolean buscarRecursivo(Nodo nodo, int valor) {
            // Si el nodo es nulo, el valor no existe
            if (nodo == null) {
                return false;
            }

            // Si encontramos el valor, retornamos true
            if (valor == nodo.valor) {
                return true;
            }

            // Si el valor es menor, buscamos en el subárbol izquierdo
            if (valor < nodo.valor) {
                return buscarRecursivo(nodo.izquierda, valor);
            }

            // Si el valor es mayor, buscamos en el subárbol derecho
            return buscarRecursivo(nodo.derecha, valor);
        }
    }

    // ========== MÉTODO PRINCIPAL ==========
    public static void main(String[] args) {
        // Crear un nuevo árbol binario
        ArbolBinario arbol = new ArbolBinario();

        // Scanner para leer entrada del usuario
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Menú interactivo
        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║   ÁRBOL BINARIO DE BÚSQUEDA        ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. Insertar número");
            System.out.println("2. Mostrar recorrido inorden");
            System.out.println("3. Buscar número");
            System.out.println("4. Salir");
            System.out.print("\nSelecciona una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Opción para insertar un número
                    System.out.print("Ingresa el número a insertar: ");
                    int numero = scanner.nextInt();
                    arbol.insertar(numero);
                    break;

                case 2:
                    // Opción para mostrar el recorrido inorden
                    System.out.println();
                    arbol.mostrarInorden();
                    break;

                case 3:
                    // Opción para buscar un número
                    System.out.print("Ingresa el número a buscar: ");
                    int buscar = scanner.nextInt();

                    if (arbol.buscar(buscar)) {
                        System.out.println("✓ El número " + buscar + " SÍ existe en el árbol.");
                    } else {
                        System.out.println("✗ El número " + buscar + " NO existe en el árbol.");
                    }
                    break;

                case 4:
                    // Opción para salir
                    System.out.println("\n¡Hasta luego!");
                    break;

                default:
                    // Si la opción no es válida
                    System.out.println(" Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 4);

        // Cerrar el scanner
        scanner.close();
    }
}