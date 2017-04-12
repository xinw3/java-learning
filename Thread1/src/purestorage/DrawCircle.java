package purestorage;

import java.util.HashSet;
import java.util.Set;

public class DrawCircle {
	
	public class Point {
		int x , y;
		public Point(int x_val, int y_val) {
			x = x_val;
			y = y_val;
		}
	}
	
	public void addPoints(Set<Point> set, int x, int y) {
		set.add(new Point(x, y));
		set.add(new Point(x, -1 * y));
		set.add(new Point(-1 * x, y));
		set.add(new Point(-1 * x, -1 * y));
		set.add(new Point(y, x));
		set.add(new Point(-1 * y, x));
		set.add(new Point(y, -1 * x));
		set.add(new Point(-1 * y, -1 * x));
	}

	public Set<Point> getPoints(int radius) {
		Set<Point> set = new HashSet<Point>();
		int rSquare = radius * radius;
		
		int x = 1;
		int y = 0;
		
		int y_start = 0;
		int y_end = 0;
		
		while (x * x <= rSquare) {
			y_start = 0;
			y_end = x;
			while (y_start + 1 < y_end) {
				int y_mid = y_start + (y_end - y_start) / 2;
				int curr = x * x + y_mid * y_mid;
				if (curr == rSquare) {
					addPoints(set, x, y_mid);
				} else if (curr < rSquare) {
					y_start = y_mid;
				} else {
					y_end = y_mid;
				}
			}
			x++;
		}
		x--;
		int xSquare = x * x;
//		System.out.println(x);
//		System.out.println(y_start);
//		System.out.println(y_end);
		if (xSquare + y_start * y_start == rSquare) {
			addPoints(set, x, y_start);
		}
		if (xSquare + y_end * y_end == rSquare) {
			addPoints(set, x, y_end);
		}
		return set;
	}
	public static void main(String[] args) {
		
		DrawCircle dc = new DrawCircle();
		Set<Point> set = dc.getPoints(3);
		
		for (Point p : set) {
			System.out.println("[" + p.x + ", " + p.y + "]");
		}

	}

}
