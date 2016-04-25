/**
 * This class is responsible for managing the music
 * @author Hristina
 *
 */
public class MusicManager {

	public void ChangeGenre(String genre){
		System.out.println("Changing to playlist: " + genre);
		
		//play the warning sound
		PlayWarning();
		
		//play music
	}

	private void PlayWarning() {
		System.out.println("Badoop Badoop");
		
	}
}
