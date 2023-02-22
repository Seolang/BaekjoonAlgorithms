import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	Gear[] gears = new Gear[4];
    	
    	/*
    	 * 4개의 기어를 초기화하고, 각 기어의 왼쪽, 오른쪽 기어를 연결해준다.
    	 */
    	for(int i=0; i<4; i++) {
    		gears[i] = new Gear(br.readLine());	
    	}
    	
    	for(int i=0; i<3; i++) {
    		gears[i].rightGear = gears[i+1];
    	}
    	
    	for(int i=1; i<4; i++) {
    		gears[i].leftGear = gears[i-1];
    	}
    	
    	int N = Integer.parseInt(br.readLine());
    	
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int idx = Integer.parseInt(st.nextToken()) - 1;
    		gears[idx].checkRotate(Integer.parseInt(st.nextToken()) == 1 ? true : false);
    	}
    	
    	int sum = 0;
    	int mul = 1;
    	for(int i=0; i<4; i++) {
    		sum += gears[i].getHead() * mul;
    		mul *= 2;
    	}
    	System.out.println(sum);
    	
    }
}

class Gear {
	int[] magnetics;
	int head;
	int left;
	int right;
	Gear leftGear;
	Gear rightGear;
	boolean moved = false;
	
	public Gear(String str) {
		magnetics = new int[8];
		head = 0;
		left = 6;
		right = 2;
		leftGear = null;
		rightGear = null;
		
		for(int i=0; i<8; i++) {
			magnetics[i] = str.charAt(i) - '0';
		}
	}
	
	public void checkRotate(boolean isClock) {
		this.moved = true;
		
		if (leftGear != null && !leftGear.moved && getLeft() != leftGear.getRight()) {
			leftGear.checkRotate(!isClock);
		}
		
		if (rightGear != null && !rightGear.moved && getRight() != rightGear.getLeft()) {
			rightGear.checkRotate(!isClock);
		}
		
		rotate(isClock);
	}
	
	
	private void rotate(boolean isClock) {
		if (isClock) {
			head = rotateIndex(head-1);
			left = rotateIndex(left-1);
			right = rotateIndex(right-1);

		} else {
			head = rotateIndex(head+1);
			left = rotateIndex(left+1);
			right = rotateIndex(right+1);
		}
		this.moved = false;
	}
	
	public int getHead() {
		return magnetics[head];
	}
	
	public int getLeft() {
		return magnetics[left];
	}
	
	public int getRight() {
		return magnetics[right];
	}
	
	private int rotateIndex(int n) {
		if (n < 0) {
			return 8+n;
		}
		
		return n%8;
	}
}