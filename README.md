<h1># Blackjack-TADs</h1>

<h2> Descripción del producto:</h2>

Se va desarrollar un software en el lenguaje de programación Java, que permita a los usuarios conectarse a un servidor y jugar black jack con otros jugadores, el programa contará con las siguientes caracteristicas:

<h4>Conexión a Servidor:</h4>El software facilita la conexión de los usuarios a un servidor central utilizando sockets, garantizando una experiencia de juego fluida y sin interrupciones.

<h4> Listas Circulares para Almacenamiento de Jugadores:</h4> Se implementarán listas circulares para gestionar eficientemente la información de los jugadores. Esta estructura de datos permite un manejo dinámico de la información de los participantes, facilitando la incorporación, salida y turnos de juego de los jugadores de manera práctica.

<h4>Pilas para Barajas de Jugadores y Crupier:</h4>Cada jugador y el crupier cuentan con su propia pila para gestionar las cartas de manera ordenada y eficiente. El uso de pilas garantiza un manejo estructurado de las barajas, contribuyendo a una distribución justa y aleatoria de las cartas durante el juego.

![image](https://github.com/Y00w1/Blackjack-TADs/assets/102988736/c59241c8-2e37-45a1-992b-b6156f39114b)

<h2>Objetivo del juego:</h2>
  <p>El objetivo principal del juego es obtener una mano de cartas cuyo valor sea lo más cercano posible a 21, sin pasarse. Cada carta tiene un valor numérico, las cartas numéricas conservan su valor nominal, mientras que las figuras (rey (K), reina (Q) y jota) tienen un valor de 10. El as puede tener un valor de 1 u 11, dependiendo de la situación del juego.</p>
  <h3>Inicio del Juego</h3>
    <p>Inicia el programa y conéctate al servidor como jugador. El servidor será el crupier y coordinará el desarrollo del juego.</p>

  <h3>Realización de Apuestas:</h3>
    <p>Cada jugador coloca su apuesta inicial en su zona de juego. Se establece un límite mínimo y máximo para las apuestas.</p>

  <h3>Desarrollo de la Mano</h3>
    <h4>Reparto de Cartas:</h4>
    <p>El crupier reparte dos cartas a cada jugador y a sí mismo. Las cartas se reparten descubiertas, excepto una del crupier, que permanece oculta.</p>

  <h4>Opciones del Jugador:</h4>
    <ul>
        <li>Pedir Carta: Solicita una nueva carta con el objetivo de acercarse a 21 sin pasarse.</li>
        <li>Plantarse: Decide conservar su mano actual y espera el desarrollo de la ronda.</li>
    </ul>

  <h4>Desarrollo de la Mano del Crupier:</h4>
    <p>El crupier revela su carta oculta. Si tiene 16 o menos, está obligado a pedir otra carta. Si tiene 17 o más, está obligado a plantarse.</p>

  <h3>Determinación de Ganadores y Pagos:</h3>
    <p>Se comparan las manos de los jugadores con la del crupier. Cada jugador que supere la mano del crupier recibe un pago 1 a 1, es decir que el jugador recibe la misma cantidad que apostó. Un Blackjack (21 con las dos primeras cartas) paga 3 a 2, es decir, que la ganancia es 1,5 veces mayor su apuesta original.</p>

  <h3>Finalización de la Ronda:</h3>
    <p>Se reparten los premios. Se inicia una nueva ronda de apuestas.</p>

  <h3>Advertencias y Excepciones</h3>
    <p>Si un jugador se pasa de 21 puntos, pierde su apuesta, y el crupier se lleva el monto apostado. El crupier juega contra cada jugador por el mismo monto que hayan apostado. En esta primera entrega del producto, no se permiten acciones adicionales como doblar, dividir, asegurarse, apostar al 21 o al par.</p>

  <h3>Consejos para Jugadores Principiantes</h3>
    <ul>
        <li>Controla tus apuestas y juega de manera estratégica.</li>
        <li>Recuerda que el objetivo es acercarte a 21 sin pasarte.</li>
        <li>Observa las cartas del crupier y toma decisiones informadas.</li>
    </ul>


