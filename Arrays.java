public class Arrays {
    public static int binarySearch(int[] arr, int num) {
        int left = 0;
        int right = arr.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == num)
                return mid;
            if (arr[mid] < num)
                left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static int linearSearch(int[] arr, int num) {
        for (int i = 0; i <= arr.length - 1; i++) {
            if (arr[i] == num) return i;
        }
        return -1;
    }

    static void bubbleSorting(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
    }

    static void selectionSorting(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int current = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) current = j;
            }
            int temp = arr[current];
            arr[current] = arr[i];
            arr[i] = temp;
        }
    }

    static void insertionSorting(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = arr[i];
        }
    }

    public static void heapSort(int arr[]) {
        int temp;

        for (int i = arr.length / 2 - 1; i >= 0; i--)
            heapify(arr, arr.length, i);

        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    static void heapify(int arr[], int n, int i) {
        int MAX = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int temp;

        if (left < n && arr[left] > arr[MAX]) {
            MAX = left;
        }

        if (right < n && arr[right] > arr[MAX]) {
            MAX = right;
        }
        if (MAX != i) {
            temp = arr[i];
            arr[i] = arr[MAX];
            arr[MAX] = temp;
            heapify(arr, n, MAX);
        }
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < n2) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    static void quickSorting(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSorting(arr, low, pivot - 1);
            quickSorting(arr, pivot + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    static void bubbleSorting2D(int[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                for (int k = 0; k < columns - j - 1; k++)
                    if (arr[i][k] > arr[i][k + 1]) {
                        int temp = arr[i][k];
                        arr[i][k] = arr[i][k + 1];
                        arr[i][k + 1] = temp;
                    }
    }

    static void selectionSorting2D(int[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns - 1; j++) {
                int current = j;
                for (int k = j + 1; k < columns; k++) {
                    if (arr[i][k] < arr[i][current]) current = k;
                }
                int temp = arr[i][current];
                arr[i][current] = arr[i][j];
                arr[i][j] = temp;
            }
    }

    static void insertionSorting2D(int[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                int key = arr[i][j];
                int k = j - 1;
                while (k >= 0 && arr[i][k] > key) {
                    arr[i][k + 1] = arr[i][k];
                    k--;
                }
                arr[i][k + 1] = key;
            }
    }

    static void quickSorting2D(int[][] arr, int low, int high) {
        if (low < high) {
            int pivot = partition2D(arr, low, high);
            quickSorting2D(arr, low, pivot - 1);
            quickSorting2D(arr, pivot + 1, high);
        }
    }

    static int partition2D(int[][] arr, int low, int high) {
        int[] pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++)
            if (compareArrays(arr[j], pivot) < 0) {
                i++;
                int[] temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        int[] temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    static int compareArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int n = Math.min(n1, n2);
        for (int i = 0; i < n; i++)
            if (arr1[i] < arr2[i]) return -1;
            else if (arr1[i] > arr2[i]) return 1;
        if (n1 < n2) return -1;
        else if (n1 > n2) return 1;
        else return 0;
    }

    static void display(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < arr.length; i++)
            System.out.print("[" + arr[i] + "] ,");
    }

    static void display(int[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; i < columns; j++)
                System.out.print("[" + arr[i][j] + "] ,");
            System.out.println();
        }
    }

    static void rotate(int[] arr, int n) {
        int size = arr.length;
        if (size == n)
            return;
        int distance = size - n;
        for (int i = 0, j = 1; i < size - 1; i++, j++)
            arr[j] = arr[i];
        arr[0] = arr[size - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 15,33,42,43,45,67,73,75,77,89,95};
        System.out.println(binarySearch(arr, 95));
        System.out.println(binarySearch(arr, 47));
    }
}