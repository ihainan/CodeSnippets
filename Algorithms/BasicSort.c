#define N 10
#include <stdio.h>

// Print an array
void printArr(int arr[], int len){
	int i;
	for(i = 0; i < len; ++i){
		printf("%d%s", arr[i], i == len - 1 ? "\n" : " ");
	}
}

// Swap two number
void swap(int* a, int *b){
	int tmp = *a;
	*a = *b;
	*b = tmp;
}

// Bubble Sort / O(N ^ 2) / O(N ^ 2) / O(1) / stable
void bubbleSort(int* arr, int len){
	int i, j;
	for(i = 0; i < len - 1; ++i){
		for(j = 0; j < len - i - 1; ++j){
			if(arr[j] > arr[j + 1]){
				swap(arr + j, arr + j + 1);
			}
		}
	}
}

// Modified Bubble Sort / O(N ^ 2) / O(N) / O(1) / stable
void bubbleSortPlus(int* arr, int len){
	int i, j, didSwap;
	for(i = 0; i < len - 1; ++i){
		didSwap = 0;
		for(j = 0; j < len - i - 1; ++j){
			if(arr[j] > arr[j + 1]){
				swap(arr + j, arr + j + 1);
				didSwap = 1;
			}
		}
		if(!didSwap) return;
	}
}

// Selection Sort / O(N ^ 2) / O(N ^ 2) / O(1) / unstable
void selectionSort(int* arr, int len){
	int i, j;
	for(i = 0; i < len - 1; ++i){
		for(j = i + 1; j < len; ++j){
			if(arr[i] > arr[j]){
				swap(arr + i, arr + j);
			}
		}
	}
}

// Insertion Sort / O(N ^ 2) / O(N) / O(1) / stable
void insertionSort(int* arr, int len){
	int i, j, tmp;
	for(i = 1; i < len; ++i){
		tmp = arr[i];
		for(j = i - 1; j >= 0 && arr[j] > tmp; --j){
			arr[j + 1] = arr[j];
		}
		arr[j + 1] = tmp;
	}
}

// Shell Sort / O(N ^ lambda) / best: O(N) | worst: O(N ^ 2) / O(1) / unstable
void shellSort(int* arr, int len){
	int gap, i, j, tmp;
	for(gap = len >> 1; gap > 0; gap >>= 1){
		for(i = gap; i < len; ++i){
			tmp = arr[i];
			for(j = i - gap; j >= 0 && arr[j] > tmp; j -= gap){
				arr[j + gap] = arr[j];
			}
			arr[j + gap] = tmp;
		}
	}
}

// Heapify
void heapify(int* arr, int size, int i){
	int left = 2 * i + 1, right = 2 * i + 2, largest = i;
	if(left < size && arr[left] > arr[largest]) largest = left;
	if(right < size && arr[right] > arr[largest]) largest = right;
	if(largest != i){
		swap(arr + i, arr + largest);
		heapify(arr, size, largest);
	}
}

// Build a heap
void buildHeap(int* arr, int size){
	int i;
	for(i = (size - 2) / 2; i >= 0; --i){
		heapify(arr, size, i);
	}
}

// Heap Sort / O(NlogN) + O(N) / O(NlogN) / unstable 
void heapSort(int* arr, int size){
	int n = size, i;
	buildHeap(arr, size);
	for(i = 0; i < size - 1; ++i){
		swap(arr, arr + n - 1);
		heapify(arr, --n, 0);
	}
}

// Quick Sort / O(NlogN) / O(N ^ 2) / O(logN)、O(N) / unstable
void quickSort(int* arr, int start, int end){
	int pos = start, i = start + 1, j = end;
	if(start < end){
		while(i <= j){
			while(i <= end && arr[i] <= arr[pos]) i++;	
			while(j > start && arr[j] > arr[pos]) j--;	
			if(i < j){
				swap(arr + i, arr + j);
			}
		}
		swap(arr + pos, arr + j);
		quickSort(arr, start, j - 1);
		quickSort(arr, j + 1, end);
	}
}

// Merge two sorted array
void merge(int* arr, int start, int mid, int end){
	int temArr[end - start + 1], i = start, j = mid + 1, k;
	for(k = 0; k < end - start + 1; ++k){
		if(i >= mid + 1) temArr[k] = arr[j++];
		else if(j > end) temArr[k] = arr[i++];
		else if(arr[i] > arr[j]) temArr[k] = arr[j++];
		else temArr[k] = arr[i++];
	}
	for(i = 0; i < end - start + 1; ++i){
		arr[i + start] = temArr[i];
	}
}

// Merge Sort / O(NlogN) / O(NlogN) / O(N) + O(logN) | O(1) / stable
void mergeSort(int* arr, int start, int end){
	int mid;
	if(start < end){
		mid = start + (end - start) / 2;	
		mergeSort(arr, start, mid);
		mergeSort(arr, mid + 1, end);
		merge(arr, start, mid, end);
	}
}

// Application Entry
int main(int argc, char** argv){
	int arr[N] = {100, 5, 67, 32, 89, 56, 78, 21, 23, 10};
	printArr(arr, N);
	mergeSort(arr, 0, N - 1);
	printArr(arr, N);
	return 0;
}
