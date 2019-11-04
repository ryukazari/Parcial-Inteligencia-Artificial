
# MisionerosCanibales

# Examen Parcial Inteligencia Artificial
# Enunciado
Disponemos de un casillero con cuatro monedas colocadas de la siguiente forma (estado inicial):
>A R A R _

El anverso de la moneda está representado por `A` y el reverso por `R`. Son posibles los siguientes movimientos y este orden:

- Giro (costo=1): Cualquier moneda puede ser girada sin ninguna condición adicional. Sólo una cada vez (En total se pueden generar 4 movimientos, uno por cada moneda).
- Desplazamiento (costo=1): Una moenda puede ser desplzada a la casilla contigua si esta se encuentra vacía. Si la casilla vacía tiene una moneda a la izquierda y a la derecha primero mover la moneda de la izquierda para enerar el movimiento; luego la moneda de la derecha para generar otro movimiento. (En total se pueden generar 2 movimientos).
- Salto (costo=2): Una moneda puede saltar sobre su vecina si a continuación hay una casilla vacía, es decir, sólo es posible saltar por encima de una moneda. Cuando una moneda salta, cae realizandoun giro. Si pudieran saltar dos monedas, primero saltaría la monedade la izquierda para generar un movimiento, luego el de la derecha para generar otro movimiento (En total se podría generar hasta 2 movimientos). Un ejemplo de salto(costo=2) es pasar del estado `AR_RR` a los estados `_RRRR` y `ARAR_`.

La meta es la siguiente:
> _ R A R A

Dada la función heurística:

$$
H(n)=p1+p2+p3+p4+p5+dv
$$

Donde `pi` vale 0 si la casilla i contiene la asignación correcta respecto al estado final y vale 1 en caso contrario y dv es la distancia del blanco respecto al blanco de la meta.
Por ejemplo, h(estado inicial) = 1+0+0+0+1+4=6
En base a este problema a acontinuación se proponen varias preguntas de búsqueda. Para cada una de las preguntas de búsqueda considerar:

- Considerar una búsqueda basada en árbol.
- Para controlar los estados repetidos: No añadir un estado como hoja si este estado está en el camino a la raíz al nodo actual.
- Las alternativas se generan de acuerdo al orden de los operadores descritos.



## Pregunta 1

¿Cuál sería el orden en el que salen los nodos de la cola en búsqueda primero en amplitud (BFS)? Implementación: La alternativa es una cola FIFO y los nuevos sucesores (en orden) se van al final de la cola. Muestre los 10 primeros nodos que salen de la cola.

Solución: `ARAR_ | RRAR_ | AAAR_ | ARRR_ | ARAA_ | ARA_R | AR_RR | RAAR_ | RRRR_ | RRAA_ `


## Pregunta 2

¿Cuál sería el orden en el que salen los nodos de la cola en búsqueda primero en profundidad (DFS)? Implementación: La alternativa es una cola FIFO y los nuevos sucesores (en orden) se van al inicio de la cola. Muestre los 5 primeros nodos que salen de la cola.

Solución: `ARAR_ | RRAR_ | RAAR_ | AAAR_ | AARR_ `


## Pregunta 3

¿Cuál sería el orden en el que salen los nodos de la cola con la búsqueda de costo uniforme? Muestre los 5 primeros nodos que se expanden.

Solución: `ARAR_ | RRAR_ | AAAR_ | ARRR_ | ARAA_ `


## Pregunta 4

¿Cuál sería la solución con el algoritmo primero el mejor considerando la heurística? Muestre el orden de expansión de los nodos.

Solución: `ARAR_ | AR_RR | _RRRR | _RARR | _RARA`


## Pregunta 5

Según lo sugerido como heurística ¿En qué orden se expanden los nodos en la búsqueda A*? Muestre los 5 primeros nodos que se expanden.

Solución: `ARAR_ | RRAR_ | ARA_R | AR_RR | _RRRR`

