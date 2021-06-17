import java.util.ArrayList;

public class AllNamesNull {

	public static boolean allNamesNull(ArrayList<Player> players) {
		int count = 0;
		for (int i = 1; i < players.size(); i++) {
			if (players.get(i).getName() != null) {
				count += 1;
			}
		}
		return count == 0;
	}
}
