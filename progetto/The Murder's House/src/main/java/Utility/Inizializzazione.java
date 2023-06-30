package Utility;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import Adventure.Giocatore;
import Adventure.Oggetto;
import Adventure.Stanza;
import Parser.ElementoFrase;
import StrutturaDati.Mappa;

/**
 * Classe che inizializza una mappa e un giocatore.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Inizializzazione {

    private static final int DIMENSIONE_MAPPA = 17;

    private static final ImageIcon IMG_CUCINA = new ImageIcon("ImmaginiStanze/CucinaSpettrale.png");
    private static final ImageIcon IMG_ATRIO = new ImageIcon("ImmaginiStanze/AtrioSpettrale.png");
    private static final ImageIcon IMG_SALONE = new ImageIcon("ImmaginiStanze/SaloneSpettraleSpento.png");
    private static final ImageIcon IMG_TRAPPOLA =  new ImageIcon("ImmaginiStanze/TrappolaSpettralePrimaFase.png");
    private static final ImageIcon IMG_STUDIO = new ImageIcon("ImmaginiStanze/Studio.png");
    private static final ImageIcon IMG_CAMERA_DA_LETTO = new ImageIcon("ImmaginiStanze/CameraDaLettoSpettrale.png");
    private static final ImageIcon IMG_BAGNO = new ImageIcon("ImmaginiStanze/BagnoSpettrale.png");
    private static final ImageIcon IMG_INTERRATO = new ImageIcon("ImmaginiStanze/IngressoInterratoSpettrale.png");
    private static final ImageIcon IMG_QUADRI = new ImageIcon("ImmaginiStanze/StanzaQuadriSpettrale.png");
    private static final ImageIcon IMG_PRIGIONE = new ImageIcon("ImmaginiStanze/PrigioneSpettrale.png");
    private static final ImageIcon IMG_RIPOSTIGLIO = new ImageIcon("ImmaginiStanze/RipostiglioSpettrale.png");
    private static final ImageIcon IMG_TAPPETO = new ImageIcon("ImmaginiStanze/BucaSpettrale.png");
    private static final ImageIcon IMG_FOSSATO = new ImageIcon("ImmaginiStanze/FossatoSpettrale.png");
    private static final ImageIcon IMG_GROTTA = new ImageIcon("ImmaginiStanze/GrottaSpettrale.png");
    private static final ImageIcon IMG_MATTATOIO = new ImageIcon("ImmaginiStanze/MattatoioSpettrale.png");
    private static final ImageIcon IMG_ASSASSINO = new ImageIcon("ImmaginiStanze/Assassino.png");
    
    private static final String DESC_GIARDINO = "Davanti a te si trova questa finestra";
    private static final String DESC_CUCINA = "> Ora, sembra, ti trovi in quella che dovrebbe essere una cucina, la luce sfarfalla, il degrado e la sporcizia ha preso il sopravvento in questa stanza, i mobili e i fornelli sono logori. Probabilmente non vengono utilizzati da molto tempo. Al centro della stanza si trova un tavolino. Nella penombra della stanza si intravedono due porte, malconce e ormai consumate dalla muffa.";
    private static final String DESC_ATRIO = "> Sembra che lei sia entrato nell'atrio, l'ingresso dell'abiatazione. Anche da questa parte la porta d'ingresso è sbarrata, chiusa a chiave e non sembra ci sia modo di aprirla.";
    private static final String DESC_SALONE = "> Ti trovi davanti a quello che sembra un salotto. Si fa fatica a scorgere anche il più piccolo dei dettagli le uniche luci che si vedono solo quelle che a fatica attraversano l'uscio della porta alle sue spalle...L'aria è pesante e si sente un tanfo pari a quello di una discarica...";
    private static final String DESC_TRAPPOLA = "> Non si vede nulla. La stanza è quasi del tutto al buio, si intravede la sagoma di alcune statue. Inoltre, ho sentito solo io un leggerissimo suono? Sembrava un 'click'. Va be l'avro immaginato. Ti consiglio di accendere la luce...";
    private static final String DESC_STUDIO = "> Chiudendosi dietro di te la porta scricchilante del salone, ti ritrovi in uno studio. È una stanza piena di oggetti, chissà se riesci a scoprire qualcosa sull'assassino...";
    private static final String DESC_CAMERA_DA_LETTO = "> Questa sembra una camera anonima, è molto pulita e ben illuminata. C'è solo un armadio. Chissà cosa contiene?";
    private static final String DESC_BAGNO = "> Questo dev'essere il bagno. E' uno spettacolo raccapricciante, c'è sporcizia ovunque. Piuttosto sembra esserci qualcosa a terra, sotto il lavandino. Ecco, è una collana, forse apparteneva ad una delle vittime. Forse può servire.";
    private static final String DESC_INTERRATO = "> Sembra essere una stanza molto piccola, una specie di ingresso interrato. A prima vista l'unica cosa che si nota è una cassa.";
    private static final String DESC_QUADRI = "> Questa stanza sembra essere uscita da un museo degli orrori. La stanza è tappezzata di dipinti e c'è uno specchio mezzo rotto a grandezza d'uomo attaccato alla parete a sud. Tutte queste rappresentazioni, raffigurano creature strane, grottesche quasi, ma al tempo stesso hanno una loro eleganza. Molti di questi dipinti sembrano rievocare demoni della mitologia giapponese, credo si chiamassero Shinigami. Se non erro questi rappresenterebbero gli dèi della morte.";
    private static final String DESC_PRIGIONE = "> Se esiste un Dio, di certo non ha mai illuminato con la sua luce questa casa e l'uomo che la abita. Questa stanza corrisponde ad una cella nascosta illuminata da una strana luce rossastra, ma la lampadina non è colorata di rosso è semplicemente sporca di sangue. All'interno della cella ci sono solo brandelli, vestiti, capelli e viscere.";
    private static final String DESC_TAPPETO = "> Che strana stanza, questa è diversa dalle altre sembra anonima. L'unica cosa che si nota e del terreno sopra un tappeto persiano al centro della stanza.";
    private static final String DESC_FOSSATO = "> Un'altra delle trappole dell'assassino, ora sei dentro una fossa";
    private static final String DESC_GROTTA = "> Non so come sia possibile che l'assassino abbia scavato questa grotta, probabilmente dev'essersi formata negli anni in modo naturale. All'interno sembra esserci una corda un po logora e UNA PISTOLA. Quest'arma è un oggetto davvero inaspettato, evidentemente durante una delle \"trans spirituali\" del nostro assassino gli dev'essere caduta, oppure se ne sarà solamente scordato. Mentre la corda, nonostante sia così malconcia, potresti utilizzarla in qualche modo...";
    private static final String DESC_RIPOSTIGLIO = "> Questo sembra essere un semplice ripostiglio, una specie di magazzino con una serie di vestiti logori. Per terra c'è la foto di una donna, il volto non si riesce più a riconoscere, inoltre indossa una collana.";
    private static final String DESC_MATTATOGLIO = "> Che orrore, questa ha l'aria di essere una stanza degli orrori, sembra un vero e proprio MATTATOIO...";
    private static final String DESC_STANZA_ASSASSINO = "> Nella penombra della stanza, eccolo che finalmente si mostra l'assassino.\n\nLe pareti sembrano assorbire la luce e le speranze di chiunque sia mai entrato qui. L'altare, al centro della sala, è una specie di monumento macabro alla ricerca di qualcosa di oscuro, è intriso di sangue secco e segni indelebili di sacrifici passati.\n\nSulla sua superficie, strani simboli e rune misteriose sono incisi con precisione chirurgica, evocando una sensazione di potere proibito a noi esseri umani.\n\nSull'altare ci sono pergamene e libri, come quelli visti nello studio al piano di sopra, sembrano narrare oscure liturgie e rituali per l'evocazione di demoni.\n\nUn senso di angoscia e terrore travolgente e pervade lo spazio, come se il sangue versato e le invocazioni sacrificali avessero contaminato l'ambiente.\n\nL'altare è un portale verso l'ignoto...\n\n\nAssassino:\n>>>>>>\n Presumo che lei sia un poliziotto, probabilemnte mi ucciderà ma aspetti che le racconti il perché di tutto questo.\n\nLe racconterò la storia tormentata di un uomo che ha perso la sua famiglia. Un uomo che non è riuscito a proteggere e a salvare la sua famiglia. Dopo un incidente ho perso mia moglie e mio figlio.\n\nDa quel giorno la mia vita è finita, allora ho provato a rivolgermi a quelle stesse divinità che si sono prese le anime dei miei cari, in modo da costringerli a riportarli in vita...Ma qualcosa è andato storto. Una di queste divinità, uno Shinigami, si è impossessata del mio corpo e ha continuato a costringermi ad uccidere...\n\nNon ero in me...\n\nTuttavia sapevo che se avessi lasciato fare allo Shinigami dentro di me, prima o poi avrebbe esaudito il mio desiderio di rivedere la mia famiglia...Lo Shinigami mi ha parlato di uno scambio. Le anime di coloro che ho ucciso per le anime di mio figlio e di mia moglie...\n\nOggi è il giorno prescelto, oggi ucciderò l'ultima vittima, dopo di chè tutto sarà finito..\n\n\n\nDetective, non crederai mica a questa storia. Ora devi prendere una decisione, uccidere l'uomo che ha posto fine alla vita di molte persone, oppure risparmiarlo, credendo alle idiozie che ha raccontato.";

    private static final ImageIcon IMG_CHIAVE_CELLA = new ImageIcon("OggettiInventario/ChiaveCella.png");
    private static final ImageIcon IMG_COLLANA = new ImageIcon("OggettiInventario/Collana.png");
    private static final ImageIcon IMG_CHIAVE_USCITA = new ImageIcon("OggettiInventario/ChiaveUscita.png");
    private static final ImageIcon IMG_PISTOLA = new ImageIcon("OggettiInventario/Pistola.png");
    private static final ImageIcon IMG_CORDA = new ImageIcon("OggettiInventario/Corda.png");
    private static final ImageIcon IMG_FOTO = new ImageIcon("OggettiInventario/Fotografia.png");
    private static final ImageIcon IMG_SCATOLA = new ImageIcon("OggettiInventario/Scatola.png");
    

    /**
     * Metodo che inizializza una mappa.
     * @param stanzaIniziale
     * @return una mappa
     */
    private static Mappa inizializzaMappa(Stanza stanzaIniziale) {
        //Mappa.
        Mappa mappa = new Mappa(DIMENSIONE_MAPPA);

        //Chiave cella.
        Oggetto chiaveCella = new Oggetto(ElementoFrase.Oggetto.CHIAVE, "Chiave della cella", "Chiave che apre la cella",  IMG_CHIAVE_CELLA, false);
        Oggetto scatola = new Oggetto(ElementoFrase.Oggetto.SCATOLA, "Scatola", "La scatola contiene qualcosa",  IMG_SCATOLA);
        List<Oggetto> oggettiStudio = new ArrayList<>();
        oggettiStudio.add(chiaveCella);
        oggettiStudio.add(scatola);

        //Collana.
        List<Oggetto> oggettiBagno = new ArrayList<>();
        Oggetto collana = new Oggetto(ElementoFrase.Oggetto.COLLANA, "Collana", "Collana di perle", IMG_COLLANA);
        oggettiBagno.add(collana);

        //Pistola e corda.
        List<Oggetto> oggettiGrotta = new ArrayList<>();
        Oggetto pistola = new Oggetto(ElementoFrase.Oggetto.PISTOLA, "Pistola", "Pistola calibro 9", IMG_PISTOLA);
        Oggetto corda = new Oggetto(ElementoFrase.Oggetto.CORDA, "Corda", "Corda logora", IMG_CORDA);
        oggettiGrotta.add(pistola);
        oggettiGrotta.add(corda);
        
        //Fotografia
        List<Oggetto> oggettiRipostiglio = new ArrayList<>();
        Oggetto fotografia = new Oggetto(ElementoFrase.Oggetto.FOTO, "Fotografia", "Fotografia di una donna", IMG_FOTO);
        oggettiRipostiglio.add(fotografia);

        //Chiave uscita.
        List<Oggetto> oggettiStanzaAssassino = new ArrayList<>();
        Oggetto chiaveUscita = new Oggetto(ElementoFrase.Oggetto.CHIAVE, "Chiave dell'ingresso", "Chiave per uscire dal rudere", IMG_CHIAVE_USCITA);
        oggettiStanzaAssassino.add(chiaveUscita);

        //Stanze
        Stanza cucina = new Stanza("Cucina", 1, DESC_CUCINA, IMG_CUCINA, false);
        Stanza atrio = new Stanza("Atrio", 2, DESC_ATRIO, IMG_ATRIO);
        Stanza salone = new Stanza("Salone", 3, DESC_SALONE, IMG_SALONE);
        Stanza trappola = new Stanza("Trappola", 4, DESC_TRAPPOLA, IMG_TRAPPOLA);
        Stanza studio = new Stanza("Studio", 5, DESC_STUDIO, oggettiStudio, IMG_STUDIO);
        Stanza cameraDaLetto = new Stanza("Camera da letto", 6, DESC_CAMERA_DA_LETTO, IMG_CAMERA_DA_LETTO);
        Stanza bagno = new Stanza("Bagno", 7, DESC_BAGNO, oggettiBagno, IMG_BAGNO);
        Stanza interrato = new Stanza("Interrato", 8, DESC_INTERRATO, IMG_INTERRATO, false);
        Stanza quadri = new Stanza("Quadri", 9, DESC_QUADRI, IMG_QUADRI);
        Stanza prigione = new Stanza("Prigione", 10, DESC_PRIGIONE, IMG_PRIGIONE, false);
        Stanza tappeto = new Stanza("Tappeto", 11, DESC_TAPPETO, IMG_TAPPETO);
        Stanza fossato = new Stanza("Fossato", 12, DESC_FOSSATO, IMG_FOSSATO, false);
        Stanza grotta = new Stanza("Grotta", 13, DESC_GROTTA, oggettiGrotta, IMG_GROTTA);
        Stanza ripostiglio = new Stanza("Ripostiglio", 14, DESC_RIPOSTIGLIO, oggettiRipostiglio, IMG_RIPOSTIGLIO);
        Stanza mattatoio = new Stanza("Mattatoio", 15, DESC_MATTATOGLIO, IMG_MATTATOIO, false);
        Stanza assassino = new Stanza("Assassino", 16, DESC_STANZA_ASSASSINO, oggettiStanzaAssassino, IMG_ASSASSINO);

        //Aggiunta delle stanze alla mappa
        mappa.aggiungiStanza(stanzaIniziale);
        mappa.aggiungiStanza(cucina);
        mappa.aggiungiStanza(atrio);
        mappa.aggiungiStanza(salone);
        mappa.aggiungiStanza(trappola);
        mappa.aggiungiStanza(studio);
        mappa.aggiungiStanza(cameraDaLetto);
        mappa.aggiungiStanza(bagno);
        mappa.aggiungiStanza(interrato);
        mappa.aggiungiStanza(quadri);
        mappa.aggiungiStanza(prigione);
        mappa.aggiungiStanza(tappeto);
        mappa.aggiungiStanza(fossato);
        mappa.aggiungiStanza(grotta);
        mappa.aggiungiStanza(ripostiglio);
        mappa.aggiungiStanza(mattatoio);
        mappa.aggiungiStanza(assassino);

        //Colegamenti tra stanze
        mappa.collegaStanze(stanzaIniziale, cucina, "nord");
        mappa.collegaStanze(cucina, atrio, "ovest");
        mappa.collegaStanze(cucina, bagno, "nord");
        mappa.collegaStanze(bagno, cucina, "sud");
        mappa.collegaStanze(atrio, cucina, "est");
        mappa.collegaStanze(atrio, salone, "ovest");
        mappa.collegaStanze(atrio, cameraDaLetto, "nord");
        mappa.collegaStanze(cameraDaLetto, atrio, "sud");
        mappa.collegaStanze(salone, atrio, "est");
        mappa.collegaStanze(salone, trappola, "ovest");
        mappa.collegaStanze(salone, studio, "nord");
        mappa.collegaStanze(studio, salone, "sud");
        mappa.collegaStanze(trappola, salone, "est");
        mappa.collegaStanze(studio, cameraDaLetto, "est");
        mappa.collegaStanze(cameraDaLetto, studio, "ovest");
        mappa.collegaStanze(cameraDaLetto, interrato, "giu");
        mappa.collegaStanze(interrato, cameraDaLetto, "su");
        mappa.collegaStanze(interrato, quadri, "sud");
        mappa.collegaStanze(quadri, interrato, "nord");
        mappa.collegaStanze(quadri, prigione, "sud");
        mappa.collegaStanze(prigione, quadri, "nord");
        mappa.collegaStanze(quadri, tappeto, "est");
        mappa.collegaStanze(tappeto, quadri, "ovest");
        mappa.collegaStanze(tappeto, ripostiglio, "nord");
        mappa.collegaStanze(ripostiglio, tappeto, "sud");
        mappa.collegaStanze(tappeto, fossato, "giu");
        mappa.collegaStanze(fossato, tappeto, "su");
        mappa.collegaStanze(fossato, grotta, "est");
        mappa.collegaStanze(grotta, fossato, "ovest");
        mappa.collegaStanze(interrato, mattatoio, "ovest");
        mappa.collegaStanze(mattatoio, interrato, "est");
        mappa.collegaStanze(mattatoio, assassino, "nord");
        mappa.collegaStanze(assassino, mattatoio, "sud");
        
        return mappa;
    }

    /**
     * Metodo che inizializza un giocatore.
     * @return un giocatore
     */
    public static Giocatore inizializzaGiocatore(){
        Stanza giardino = new Stanza("Giardino", 0, DESC_GIARDINO);

        Mappa mappa = inizializzaMappa(giardino);

        Giocatore g = new Giocatore(giardino, mappa);
        return g;
    }

}
