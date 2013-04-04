/*
 * CodeEval medium level challenge: Detecting Cycles
 * Author: Jiatong Wang
 * 4/4/2013
 * first argument is the name of input file,
 * input file contains multiple lines of number sequence like, 1 2 3 4 2 3 4
 * output of program is the loop in the sequence: 2 3 4
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

class Main {
    public static void main(String[] args) throws IOException {
        CircleDetector.main(args);
    }
}
class CircleDetector {

  /**
	 * @param args
	 * @throws IOException 
	 */
	public boolean detect(int []array, ArrayList<Integer>loop){
		loop.clear();
		int slow = 1;
		int fast = 2;
		while (fast <= array.length - 1) {
			if (array[slow] == array[fast])
				break;
			slow++;
			fast += 2;
		}
		if (fast > array.length - 1)
			return false;
		int count = 0;
		slow = 0;
		while (fast <= array.length - 1 && array[slow] != array[fast]) {
			slow++;
			fast++;
			count++;
		}
		HashSet<Integer> hset = new HashSet<Integer>();
		slow = count;
		while (!hset.contains(array[slow])) {
			hset.add(array[slow]);
			loop.add(array[slow]);
			slow++;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File(args[0]);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		while ((line = in.readLine()) != null) {
			String[] strArray;
			CircleDetector cd = new CircleDetector();
			try {
				strArray = line.trim().split(" ");
				int[] array = new int[strArray.length];
				for (int i = 0; i < strArray.length; i++) {
					array[i] = Integer.parseInt(strArray[i]);
				}
				ArrayList<Integer> loop = new ArrayList<Integer>();
				if (cd.detect(array, loop) == true) {
					for(int i=0; i<loop.size(); i++){
						System.out.print(loop.get(i)+ " ");
					}
					System.out.println();
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		in.close();
	}
}
