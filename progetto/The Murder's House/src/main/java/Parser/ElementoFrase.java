package Parser;

/**
 * Dato di tipo enumerativo che contiene i diversi elementi che compongono una frase,
 * ovvero predicato (rappresentato da Verbo), articolo (o preposizione) e complemento (a prescindere
 * dalla natura del complemento).
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public enum ElementoFrase {;
    public enum Posizione{
        NORD, 
        SUD, 
        EST, 
        OVEST,
        SU,
        GIU; 
    }

    public enum Verbo{
        VAI, 
        APRI,  
        SFONDA, 
        OSSERVA, 
        PRENDI, 
        ACCENDI, 
        USA, 
        GETTA, 
        SALI, 
        SCENDI, 
        SOLLEVA, 
        LEGGI,
        UCCIDI,
        RISPARMIA,
        SPOSTA; 
    }

    public enum Articolo{
        IL, 
        LO, 
        LA, 
        L,  
        LE, 
        I,
        GLI; 
    }

    public enum Preposizione {
        A, 
        DALLA;
    }

    public enum Oggetto{
        BOTOLA, 
        SCRIVANIA, 
        DIARIO, 
        CELLA, 
        DIPINTO, 
        PISTOLA, 
        SCALA, 
        FINESTRA, 
        COLLANA, 
        FOTO, 
        LUCE, 
        POMELLO, 
        FORNELLO, 
        CHIAVE, 
        GIARDINO, 
        CUCINA, 
        ATRIO, 
        SALONE, 
        TRAPPOLA, 
        STUDIO, 
        CAMERA_DA_LETTO, 
        BAGNO, 
        INGRESSO_SEMINTERRATO,
        STANZA_QUADRO,
        PRIGIONE, 
        FOSSATO, 
        DEPOSITO_VESTIARIO, 
        STANZA_TORTURE, 
        STANZA_CHIVE_USCITA, 
        STANZA, 
        CONTENITORE, 
        PIEDE, 
        SPECCHIO, 
        STATUE, 
        LIBRERIA, 
        LIBRO, 
        SCATOLA, 
        VESTITI, 
        LAVANDINO, 
        QUADRI, 
        TAPPETO,
        FERITOIA,
        GROTTA,
        CORDA,
        RIPOSTIGLIO,
        MATTATOIO,
        ASSASSINO,
        TAVOLO,
        FRIGO,
        PENTOLA,
        PIATTI,
        WATER,
        VASCA,
        TELEFONO,
        LETTO,
        ARMADIO, 
        CASSA,
        OSSA,
        ALTARE,
        STRUMENTI,
        USCITA;
    }

}
