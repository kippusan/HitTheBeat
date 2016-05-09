import java.util.ArrayList;
import java.util.Map;

/**
 * This class is responsible for managing the music
 * @author Hristina
 *
 */
public class MusicManager {

//    private void PlayWarning() {
//        //todo: add proper sound file and use
//        MusicPlayer musicPlyr = new MusicPlayer();
//        musicPlyr.playMusic("warningsound.wav");
//	}

    public void playPlaylist(String nameofPlaylist ) {
        //decides which playlist are we going to play
        String[] WarmupPlaylist  = {"D:/test/test.wav", "D:/test/test2.wav", "D:/test/test3.wav"};
        String[] LowIntensityPlaylist  = {"D:/test/test.wav", "D:/test/test2.wav", "D:/test/test3.wav"};
        String[] HighIntensityPlaylist  = {"D:/test/test.wav", "D:/test/test2.wav", "D:/test/test3.wav"};
        String[] CooldownPlaylist  = {"D:/test/test.wav", "D:/test/test2.wav", "D:/test/test3.wav"};


        String[] Playlist = WarmupPlaylist;
        MusicPlayer musicPlyr = new MusicPlayer();
        switch  (nameofPlaylist) {
            case "warmup":
                Playlist = WarmupPlaylist;
                break;
            case "lowIntensity":
                Playlist = LowIntensityPlaylist;
                break;
            case "highIntensity":
                Playlist = HighIntensityPlaylist;
                break;
            case "cooldown":
                Playlist = CooldownPlaylist;
                break;
        }
        //the playlist loop (it plays all the songs in the playlist)
        System.out.println("Changing to playlist: " + nameofPlaylist);
        for (String song: Playlist) {
            musicPlyr.playCompleted = false; // tells the music player that we are not done playing music, this is used to play an entire playlist and not just the first song
            try {
                musicPlyr.playMusic(song);
            } catch (InterruptedException e) {
               break;
            }
            System.out.println("Playing next song in playlist: " + nameofPlaylist);
        }
    }
}
