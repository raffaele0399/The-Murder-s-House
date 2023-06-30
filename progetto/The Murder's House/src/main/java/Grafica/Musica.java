package Grafica;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Classe che gestisce il controllo della musica di sottofondo del programma.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Musica {

    private Clip musicaGioco;

    /**
     * Metodo che avvia la riproduzione della musica all'esecuzione del programma.
     * @param filePath 
     */
    public void playMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            musicaGioco = AudioSystem.getClip();

            musicaGioco.open(AudioSystem.getAudioInputStream(musicFile));

            musicaGioco.loop(Clip.LOOP_CONTINUOUSLY);

            musicaGioco.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("Errore nella riproduzione.");
        }
    }

    /**
     * Metodo che gestisce lo stop della riproduzione della musica.
     */
    public void stopMusica() {
        if (musicaGioco != null && musicaGioco.isRunning()) {
            musicaGioco.stop();
            musicaGioco.close();
        }
    }
}
