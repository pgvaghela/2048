/**
 * AudioManager Class
 * Contributors: Apurbo Barua, Ashutosh Dayal, Pri Vaghela, Jacob
 * 
 * Description:
 * This class handles background music and sound effects for the 2048 game.
 * It provides methods to play, loop, and stop audio files dynamically.
 */

 package backend;

 import java.io.File;
 import java.io.IOException;
 import javax.sound.sampled.*;
 
 public class AudioManager {
     private Clip backgroundMusic;
 
     /**
      * Plays background music in a loop.
      * @param filepath The file path of the music file.
      */
     public void playBackgroundMusic(String filepath) {
         try {
             File audioFile = new File(filepath);
             AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
             backgroundMusic = AudioSystem.getClip();
             backgroundMusic.open(audioStream);
             backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
             backgroundMusic.start();
         } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
             e.printStackTrace();
         }
     }
 
     /**
      * Stops the currently playing background music.
      */
     public void stopBackgroundMusic() {
         if (backgroundMusic != null && backgroundMusic.isRunning()) {
             backgroundMusic.stop();
         }
     }
 
     /**
      * Plays a single sound effect.
      * @param filepath The file path of the sound effect file.
      */
     public void playSoundEffect(String filepath) {
         try {
             File audioFile = new File(filepath);
             AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
             Clip soundEffect = AudioSystem.getClip();
             soundEffect.open(audioStream);
             soundEffect.start();
         } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
             e.printStackTrace();
         }
     }
 }