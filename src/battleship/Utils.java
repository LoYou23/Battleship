package battleship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
