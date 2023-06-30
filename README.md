# The Murder's House
      
## Indice

1. **Descrizione del caso di studio**
  
2. **Diagramma delle Classi e di Sequenza**
  
3. **Specifica Algebrica di una struttura dati**
  
4. **Descrizione degli argomenti applicati al caso di studio**
  

### 1. Descrizione del caso di studio

Si è realizzata un'applicazione, scritta in linguaggio Java che vede la strutturazione di un'*avventura testuale*.

Il *gioco* prende il nome di : ***The Murder's House*** (La casa dell'assassino), quest'ultimo tratta l'avventura del *Detective Smith*, che sta seguendo il caso di un *serial killer*.

Quest'ultimo ha compiuto una serie di omicidi e l'obbiettivo del gioco è quello di immedesimarsi nei panni del detective che dovrà esplorare l'abitazione dell'assassino al fine di trovarlo e prendere una scelta per le sorti di quest'ultimo.

Per creare una narrazione calzante con la trama e, al tempo stesso, coinvolgente, si è cercato di descrivere una storia verosimile, ma con un tratto fantasy.

La trama dell'avventura testuale, infatti rispecchia i principi di una storia di categoria *giallo*. Al suo interno, infatti vi sono *indizi*, *colpi di scena* e uno sviluppo della storia, tale da accattivare e coinvolgere il giocatore.

Si è scelto di rappresentare la mappa di gioco come un *rudere*, nonché abitazione dell'assassino. All'interno del rudere il giocatore avrà la possibilità di muoversi liberamente tra le *stanze* presenti senza dover seguire un percorso preciso.

Infatti, sarà il giocatore a scegliere come orientarsi, esplorando l'ambiente di gioco, quindi progredendo nella trama.

Come richiesto dei requisiti fondamentali dell'esame, il gioco è sviluppato in modo tale che il giocatore abbia una continua interazione con l'applicazione.

Tale interazione è garantita dall'inserimento *libero* (ma **guidato**) di una serie di comandi che sono seguiti da una descrizione come risposta al comando stesso.

Tali comandi prenvedono l'interazione del giocatore con oggetti, stanze, etc.

Inoltre, si è data anche la possibilità di interagire con il programma tramite l'utilizzo di una serie di *bottoni* che gestiscono diverse funzionalità sfruttabili durante una partita.

Queste prevedono funzionalità come il salvataggio e il caricamento di una partita, la possibilità di utilizzare "*trucchi*" al fine di agevolare il proseguimento di una partita, la gestione della mussica di sottofondo, la visualizzazione della mappa di gioco, e dell'inventario, una finestra "*Wiki*" che racconta brevemente cos'è un'avventura testuale, ed infine un bottone per visualizzare le regole e i suggerimenti per giocare una partita.

---
## 2. Diagramma delle Classi e di Sequenza

All'interno della repository si riporta il diagramma delle classi (DiagrammaUMLFunzionamentoParser.pdf) il qualerappresenta la logica dell'analisi testuale di un comando inserito in input da un'utente.

La logica rappresentata nel diagramma esplica il principale funzionamento di quattro classi :

1. La prima corrisponde alla classe **Parser.java** che procede nel riconoscere da quante parole è composto il comando inserito in input delegando il controllo sintattico e semantico ai diversi *case* che istanziano un oggetto specifico per il tipo di comando.
  
2. La seconda corrisponde alla classe **ParserSemplice.java** delegata all'analisi sintattica e semantica di un comando composto da una sola parola (token), che può essere un azione generica (nel caso specifico *osserva*), oppure una delle direzioni espresse tramite punti cardinali (*n, s, e, o*). All'interno di questa classe, inoltre, è conservata la logica per lo spostamento di un giocatore da una stanza ad un'altra.
  
3. La terza corrisponde alla classe **ParserMedio.java** delegata all'analisi sintattica e semantica di un comando composto da due parole (token). Questa versione di parser oltre a verificare l'applicabilità di un comando, verifica una serie di casistiche di comandi strettamente legati al contesto in cui vengono utilizzati e che generano comportamenti differenti.
  
4. La quarta corrisponde alla classe **ParserComplesso.java** delegata all'analisi sintattica e semantica di un comando composto da tre parole (token). Questa classe, come quella di **ParserMedio.java** dovrebbe effettuare i diversi controlli inerenti comandi utilizzabili in determinati contesti; tuttavia, si è scelto di effettuare i vari controlli all'interno di ParserComplesso.java, ridirezionandoli dentro ParserMedio.java.
  

Le restanti classi rappresentate corrispondono a quelle utili alle classi appena citate, per i diversi casi di controllo di contesto, infatti la maggior parte dei collegamenti sono ***dipendenze***, rappresentate con delle frecce direzionate tratteggiate.

---

## 3. Specifica Algebrica di una struttura dati

Nella specifica la logica implementativa è stata giustamente scissa dalla logica di astrazione, dal momento che a livello implementativo i seguenti operatori sono stati realizzati all'interno della classe stessa del Grafo, in maniera tale da operare direttamente sulla sua istanza senza necessariamente doverla passare in input ai suddetti.

**Specifica sintattica**

**sorts**: Grafo,boolean, Stanza, N (dove N è il tipo del valore contenuto all'interno del nodo), A (dove A è il tipo del peso dell'arco della grafo), Lista, Nodo

newGrafo() ---> Grafo

vuoto(Grafo) ---> boolean

insNodo(Grafo, Nodo) ---> Grafo

leggiNodo(Grafo, Nodo) ---> N

cancellaNodo(Grafo, Nodo) ---> Grafo

insArco(Grafo, Nodo, Nodo, A) ---> Grafo

leggiPeso (Grafo, Nodo, Nodo) ---> A

cancArco(Grafo, Nodo, Nodo) ---> Grafo

adiacenti(Grafo, Nodo) ---> Lista

esisteNodo(Grafo, Nodo) ---> boolean

esisteArco(Grafo, Nodo, Nodo) ---> boolean

**Specifica semantica**

I costruttori operano direttamente per aggiunta, modifica o istanziazione della struttura.

**declare**:

- g: Grafo
  
- st : Stanza
  
- n, n1, n2, n', n'' : Nodo
  
- val: N
  
- peso: A
  
- l = Lista
  

**Costruttori** di **g'**

- newGrafo()
  
- insNodo(g, n)
  
- insArco(g, n1, n2, peso)
  

**Osservatori**:

- vuoto(g')
  
- leggiNodo(g', n)
  
- cancellaNodo(g', n)
  
- leggiPeso (g', n1, n2)
  
- cancArco(g', n1, n2)
  
- adiacenti(g', n)
  
- esisteNodo(g', n)
  
- esisteArco(g', n1, n2)
  

| **Costruttori** di g' | newGrafo() | insNodo(g, n) | insArco(g, n1, n2, peso) |
| --- | --- | --- | --- |
| **Osservatori** |     |     |     |
| **vuoto(g')** | true | false | **if** esisteNodo(g, n1) **and** esisteNodo(g, n2) **then** false **else** vuoto(g) |
| **leggiNodo(g',n')** | error | **if** n=n' **then** val **else** leggiNodo(g, n') | **if** (esisteNodo(g, n1) **and** esisteNodo(g, n2) **and** ((n'=n1 **or** n'=n2 )) **then** val **else** leggiNodo(g, n') |
| **cancellaNodo(g',n')** | error | **if** n'=n **then** g **else** cancellaNodo(g',n) | **if** (esisteNodo(g, n1) **and** esisteNodo(g, n2) **and** ((n'=n1 **or** n'=n2 )) **then** g'' **else** cancellaNodo(g, n') |
| **leggiPeso(g',n',n'')** | error | **if** ((n' = n **and** **not** n'=n'' **and** esisteNodo(n'')) **or** (n'' = n **and** **not** n''=n') **and** esisteNodo(n')) **and** esisteArco(g', n', n'') **then** peso **else** leggipeso(g, n', n'') | **if** esisteNodo(g, n1) **and** esisteNodo(g, n2) **and** esisteArco(g, n1, n2) **and** ((n'=n1 **and** n'' = n2) **or** (n'=n2 **and** n'' = n1)) **then** peso **else** leggiPeso(g, n', n'') |
| **cancArco(g',n',n'')** | error | **if** (n' = n **and not** n'=n'' **and** esisteNodo(n'')) **or** (n'' = n **and not** n''=n' **and** esisteNodo(n')) **and** esisteArco(g', n', n'') **then** g'' **else** cancArco(g, n', n'') | **if** esisteNodo(g, n1) **and** esisteNodo(g, n2) **and** esisteArco(g, n1, n2) **and** ((n'=n1 **and** n'' = n2) **or** (n'=n2 **and** n'' = n1)) **then** g **else** cancArco(g, n', n'') |
| **adiacenti(g', n')** | error | **if** n'=n **then** l **else** adiacenti(g, n') | **if** esisteNodo(n') **and** ((n'=n1 **and not** n'=n2) **or** (n'=n2 **and not** n'=n1)) **then** l else adiacenti(g, n') |
| **esisteNodo(g',n')** | false | **if** n'=n **then** true **else** esisteNodo(g, n') | **if** (n'=n1 **and not** n' =n2) **or** (n'=n2 **and not** n'=n1) **then** true **else** esisteNodo(g, n') |
| **esisteArco(g',n',n'')** | false | **if** (n'=n **and not** n'=n'' **and** esisteNodo(n'')) **or** (n''=n **and not** n''=n' **and** esisteNodo(n')) **and** **then** true **else** esisteArco(g, n', n'') | **if**  ((n'=n1 **and** n'' = n2) **or** (n'=n2 **and** n'' = n1)) **then** true **else** esisteArco(g, n', n'') |

---

## 4. Descrizione degli argomenti applicati al caso di studio

All'interno di questo progetto si sono utilizzati tutti i diversi argomenti trattati durante il corso di ***Metodi Avanzati di Programmazione***, di seguito l'elenco di come i diversi argomenti sono stati applicati al caso di studio:

### > Socket e Rest

L'utilizzo delle **socket** si è rivelato utile per poter permettere la comunicazione di più utenti durante la sessione di gioco.

- **ServerChat**: classe atta allo scopo di avviare il server di chat del videogioco, gestire le connessioni di più client, inviare messaggi tra i client e tenere traccia degli utenti connessi, evitando che possano esserci client con lo stesso username.
  
- **ClientThread**: rappresenta il cuore della comunicazione tra server e client, questa classe viene impiegata nella gestione delle richieste di ciascun client al server, a partire dalla connessione dello stesso client all'inoltro di messaggi con il server e a tutti gli altri client.
  
- **ClientChat**: corrisponde al lato client della chat. Si segnala l'utilizzo di thread per ottenere un meccanismo di ricezione indipendente rispetto all'interfaccia grafica, in maniera tale da poter eseguire altre operazioni senza dover aspettare attivamente l'arrivo di un messaggio.
  
- **InterfacciaChat**: è l'interfaccia grafica che corrisponde a ciò che l'utente visualizza nel momento in cui desidera inviare messaggi con altri giocatori.
  

L'utilizzo di api **Rest** si rende utile nell'offrire all'utente le principali informazioni su cosa sia un'avventura testuale. Per fare ciò si è utilizzata l'api di Wikipedia nella classe **WikiAdventure** che viene richiamata dall'interfaccia grafica **InterfacciaWiki**.

### > Lambda expression

Le **lambda expression** sono state utilizzate nei seguenti casi:

- **ParseTabella**: classe in cui il metodo **processaFile** è delegato alla lettura del contenuto di un file per poterlo elaborare in una Map che altro non sarà quella utilizzata per i controlli sintattici/semantici del Parser. Si segnala l'utilizzo dell'*interfaccia funzionale* **Function< T, R >**, la quale viene utilizzata per la generazione rispettivamente di chiavi e valori nella mappa.
  
- Attraverso l'interfaccia funzionale **Utils** è stato possibile utilizzare lambda expression in:
  
  - **ParserMedio**: per poter calcolare la chiave corrispondente al valore stringa di output per l'HashMap che viene utilizzata per la gestione dell'analisi semantica.
    
  - **ParserComplesso**: per poter calcolare la chiave corrispondente al valore intero, che altro non è che un riferimento alla chiave precedentemente disussa, in maniera tale da poter delegare il compito di gestione di analisi semantica all'altro parser, minimizzando così le stringhe di output all'interno del file associato.
    
- Con **Swing** per creare le **ActionListener** e gestire i metodi **main** di alcuni **JFrame**.
  
- Per la creazione e l'esecuzione dei Thread come per esempio nella classe **ClientChat** per la gestione dei messaggi ricevuti.
  

### > Reflection

L'utilizzo delle **Reflection** è stato esclusivo della classe **Trucchi**, in maniera tale che nella sessione di gioco fosse possibile effettuare due comandi non previsti a priori dal giocatore:

- **$apri stanza**: per poter settare come apribile la stanza adiacente, accedendo al campo privato corrispondente, così è possibile accedere alle stanze senza far uso di specifici comandi o di specifici oggetti come ad esempio la chiave che apre una porta.
  
- **$punteggio massimo**: per poter accedere al campo privato corrispondente al punteggio del giocatore, in modo tale da settarlo al massimo raggiungibile.
  

### > Thread

L'utilizzo dei thread si è rivelato uno strumento utile nelle seguenti classi:

- **StampaTesto**: per la gestione della stampa di ogni output visualizzato, su una JTextArea, dall'utente conferendogli un effetto "macchina da scrivere", quindi nello specifico è stata creata una classe che si occupa di ciò, stampando le diverse stringhe che compongono ogni messaggio di output con un tempo di attesa pari a 50 millisecondi.
  
  **N.B.** : L'implementazione di questa funzionalità all'interno del programma, ha incontrato un gap rappresentato dalla caratteristica di una JTextArea, che non essendo *Thread Safe* non riesce a gestire eventi terminatori che bloccano la fase di ***run*** di un thread istanziandone un altro. Di conseguenza l'inserimento consecutivo di più comandi, senza che l'utente aspetti la fine della stampa corrente di un messaggio di output, genera la soprapposizione di più thread che porterebberò ad una mal formattazione degli output.
  
- **ClientThread**: affinché il server possa gestire più richieste simultanee di connessione.
  
- **ClientChat**: nel metodo **riceviMessaggio** per i motivi spiegati in precenza.
  

### > File I/O

L'utilizzo dei **file** è stato il fulcro di tutta l'analisi sintattica/semantica effettuata dai Parser. Sono stati realizzati dei file di testo che vengono analizzati dal metodo **processaFile** in **ParseTabella** per la creazione di tabelle corrispondenti a quelle per la verifica di:

- In caso di input dall'utente: Articoli, Oggetti, Posizioni, Transitivi, Intransitivi e Preposizioni.
  
- In caso di output all'utente: Output che viene usato da ParserMedio e ChiaviRiferimento che viene utilizzata dal ParserComplesso per referenziare la gestione dell'output al ParserMedio.
  

### > DataBase

Il concetto dei **DataBase** è stato utile per il controllo del salvataggio di una partita. Infatti al primo avvio dell'applicazione viene creato un DataBase contenente una tabella nella quale vengono inseriti oggetti ***serializzati*** di tipo Giocatore, in modo da salvare lo stato del giocatore in un determinato momento di una partita.

Il Database, infatti, verrà interrogato nel momento in cui ci si interagisce tramite i *button* ***salva*** e ***carica***.

Nel primo caso l'opzione di salvataggio comanda un *INSERT* che registra tutti gli attributi del giocatore all'interno della tabella.

Nel secondo caso l'opzione di caricamento effettua una *QUERY* che interroga il DB verificando che il nome del salvataggio scelto dall'utente è contenuto all'interno del DB. A quel punto tutti gli attributi registrati, rappresentativi dello stato della classe Giocatore, vanno a modificare i riferimenti degli attributi del giocatore corrente nella medesima sessione.

### > Eccezioni

Si è sfruttato questo argomento per gestire tutte le eventuali eccezioni che la classe Grafo potrebbe lanciare.

Come richiesto è stata creata una classe ad hoc che estende la classe *Exception* (ovvero la classe di default di java per la gestione delle eccezioni), in modo tale che racchiudesse tutte le eccezioni utili. Queste sono state studiante in modo tale da poter gestire tutti gli eventuali casi di comportamenti inaspettati che coinvolgono ogni operatore presente nella classe Grafo.

### > Libreria Swing

L'utilizzo della libreria ***Swing*** ha rappresentato una delle principali caratteristiche del progetto.

Il loro utilizzo è stato significativo per la creazione di diverse interfacce grafiche, in modo particolare sono state create le seguenti interfacce:

- **InterfacciaGrafica** : rappresenta il JFrame principale, all'interno del quale sono stati racchiusi tutti i componenti essenziali, visibili all'utente durante tutta la durata di una partita. In questa finestra sono contenuti elmenti grafici come la *JTextArea* per la stampa di tutti gli output, la *JTextField* per la recezione dei comandi in input, i diversi *JButton* per le diverse interazioni che permettono ad un giocatore di personalizzare la propria partita, infine è presente una *JLabel* per la visualizzazione di una serie di immagini che raccontano e arricchiscono l'esperienza di gioco di un'utente.
  
- **InterfacciaChat** : rappresenta la finestra relativa alla simulazione di una semplice funzione di "*chatting*" tra diversi giocatori.
  
- **InterfacciaMappa** : rappresenta la finestra contenente una raffigurazione esemplificativa della mappa corrente, nascondendo all'utente eventuali stanze.
  
- **InterfacciaInventario** : corrisponde alla rappresentazione di uno "*zaino*" che il giocatore può consultare dopo che ha raccolto determinati oggetti all'interno della mappa. Quest'ultimi, infatti, vengono rappresentati da diverse icone che vanno a settare differenti JLabel.
  
- **InterfacciaAiuto** : rappresenta un JFrame descrittivo, che esplica le funzionalità ammesse durante una partita, come fossero le istruzioni di un gioco.
  
- **InterfacciaWiki** : rappresenta un JFrame che va a simulare una pagina Wikipedia che racconta brevemente a cosa corrisponde un'avventura testuale.
