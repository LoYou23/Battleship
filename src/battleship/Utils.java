package battleship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {
	

	public static int[] getRandomRange(int max) {
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i<max; i++) {
			temp.add(i);
		}
		
		Collections.shuffle(temp);
		return temp.stream().mapToInt(i->i).toArray();
	}


}
