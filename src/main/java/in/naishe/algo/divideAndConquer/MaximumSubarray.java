package in.naishe.algo.divideAndConquer;



/**
 * The maximum-subarray problem
 * see detail discussion at http://code.naishe.in/2012/09/maximum-sum-sub-array.html
 * @author Nishant Neeraj (http://naishe.in)
 *
 */
public class MaximumSubarray {

	public static void main(String[] args) {
		int[] test = {12, 10, 11, 20, 2, 6, 19, 1, 3};
		int[] maxSum = getMaxSum(test);
		System.out.println("{" + maxSum[2] + " = [" + (maxSum[0]>0?maxSum[0]-1:maxSum[0]) + ", " + maxSum[1] +"] }");
	}

	private static int[] getMaxSum(int[] a) {
		if (null == a)
			return a;
		int[] sumArray = new int[a.length];
		sumArray[0] = 0;
		for (int i = 1; i < sumArray.length; i++){
			sumArray[i] = a[i] - a[i-1];
		}
		return getMaxSubArray(sumArray, 0, sumArray.length-1);
	}

	private static int[] getMaxSubArray(int[] sumArray, int low, int high) {
		if (low == high)
			return new int[]{low, high, sumArray[low]};
		
		int mid = low + (high-low)/2;
		int[] leftMax = getMaxSubArray(sumArray, low, mid);
		int[] rightMax = getMaxSubArray(sumArray, mid+1, high);
		int[] crossOverMax = getMaxCrossOver(sumArray, low, mid, high);
		
		int leftVal = leftMax[2];
		int rightVal = rightMax[2];
		int crossOverVal = crossOverMax[2];
		if(leftVal > rightVal && leftVal > crossOverVal)
			return leftMax;
		else if (rightVal > leftVal && rightVal > crossOverVal)
			return rightMax;
		else
			return crossOverMax;
	}

	private static int[] getMaxCrossOver(int[] sumArray, int low, int mid, int high) {
		int sumLeft = Integer.MIN_VALUE;
		int sumRight = Integer.MIN_VALUE;
		int tempSum = 0;
		int leftIndex = mid;
		int rightIndex = mid+1;
		
		for(int i=mid; i >= low; i--){
			tempSum = tempSum + sumArray[i];
			if(tempSum > sumLeft){
				leftIndex = i;
				sumLeft = tempSum;
			}
		}
		
		tempSum = 0;
		for(int i=mid+1; i <= high; i++){
			tempSum = tempSum + sumArray[i];
			if(tempSum > sumRight){
				sumRight = tempSum;
				rightIndex = i;
			}
		}
		return new int[]{leftIndex, rightIndex, sumLeft + sumRight};
	}

}
