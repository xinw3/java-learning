package purestorage;

public class SortColors {
	public void sortColors(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		int i = 0;
		
		while(left <= i && i <= right) {
			if (nums[i] == 0) {
				swap(nums, left, i);
				left++;
			} else if (nums[i] == 2) {
				swap(nums, right, i);
				right--;
				continue;
			}
			i++;
		}
	}
	public void swap(int[] nums, int idx1, int idx2) {
		int temp = nums[idx1];
		nums[idx1] = nums[idx2];
		nums[idx2] = temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortColors sc = new SortColors();
		int[] nums = new int[] {1, 2, 0};
		sc.sortColors(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}

}
