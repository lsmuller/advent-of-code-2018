package com.let.aoc2018;

import java.io.IOException;
import java.util.Stack;

import com.let.utils.FileAccess;

public class Day08 {
	private static String[] inputs;
	private static Stack<Integer> childsSizes = new Stack<>();

	public static void main(String args[]) throws IOException {
		inputs = FileAccess.loadFile(Day06.class.getClassLoader(), "day08").split(" ");
		System.out.println(sumMetadata(0, Integer.valueOf(inputs[0]), Integer.valueOf(inputs[1])));
	}

	private static int sumMetadata(int pos, int childsQty, int metadataQty) {
		if (childsQty == 0) {
			return sum(pos, metadataQty);
		}
		else {
			int myChildsQty = childsQty;
			int childSum = 0;
			int jump = 2;
			while (myChildsQty > 0) {
				childSum += sumMetadata(pos + jump, Integer.valueOf(inputs[pos + jump]), Integer.valueOf(inputs[pos + jump + 1]));
				jump += childsSizes.peek();
				myChildsQty--;
			}
			return sum(pos, determineChildsSize(childsQty, metadataQty), metadataQty) + childSum;
		}
	}

	private static int sum(int pos, int metadataQty) {
		int start = pos + 2;
		int sum = 0;
		for (int i = start; i < start + metadataQty; i++) {
			sum += Integer.valueOf(inputs[i]);
		}
		childsSizes.push(2 + metadataQty);
		return sum;
	}

	private static int sum(int pos, int jump, int metadataQty) {
		int start = pos + 2 + jump;
		int sum = 0;
		for (int i = start; i < start + metadataQty; i++) {
			sum += Integer.valueOf(inputs[i]);
		}
		return sum;
	}

	private static int determineChildsSize(int childQty, int metadataQty) {
		int totalChildsSize = 0;
		for (int i = 0; i < childQty; i ++) {
			totalChildsSize += childsSizes.pop();
		}
		childsSizes.push(2 + totalChildsSize + metadataQty);
		return totalChildsSize;
	}

}
