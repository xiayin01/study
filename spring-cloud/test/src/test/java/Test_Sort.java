/**
 * 冒泡排序
 */
public class Test_Sort {

    public static void main(String[] args) {
        System.out.println(System.getenv());
        int[] arr = {1, 2, 32, 21, 12, 43, 22, 10, 1, 2, 32, 42, 54, 65, 75, 85, 75, 53, 2, 312, 213, 4324, 54456};
        Test_Sort test = new Test_Sort();
        test.sort1(arr);
        test.sort2(arr);
        test.sort3(arr);
        test.sort4(arr);
        test.sort5(arr);
    }

    /**
     * 原始冒泡排序
     *
     * @param arr 需要排序得数组
     */
    private void sort1(int[] arr) {
        long start = System.nanoTime();
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        long end = System.nanoTime();
        System.out.println("----sort1运行时间------：" + (end - start));
        //Arrays.stream(arr).forEach(System.out::println);
    }

    /**
     * 优化后的冒泡排序
     *
     * @param arr 需要排序的数组
     */
    private void sort2(int[] arr) {
        long start = System.nanoTime();
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            for (int i = low; i < high; i++) {
                if (arr[i] > arr[i + 1]) {
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
            --high;
            for (int j = high; j > low; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
            ++low;
        }
        long end = System.nanoTime();
        System.out.println("----sort2运行时间------：" + (end - start));
        //Arrays.stream(arr).forEach(System.out::println);
    }

    /**
     * 选择排序
     *
     * @param arr 需要排序的数组
     */
    private void sort3(int[] arr) {
        long start = System.nanoTime();
        for (int i = 0; i < arr.length - 1; i++) {
            int pos = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[pos] > arr[j]) {
                    pos = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[pos];
            arr[pos] = tmp;
        }
        long end = System.nanoTime();
        System.out.println("----sort3运行时间------：" + (end - start));
        //Arrays.stream(arr).forEach(System.out::println);
    }

    /**
     * 选择排序
     *
     * @param arr 需要排序的数组
     */
    private void sort4(int[] arr) {
        long start = System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            for (int j = i - 1; j >= 0 && arr[j] > tmp; j--) {
                arr[j + 1] = arr[j];
                arr[j + 1] = tmp;
            }
        }
        long end = System.nanoTime();
        System.out.println("----sort4运行时间------：" + (end - start));
        //Arrays.stream(arr).forEach(System.out::println);
    }

    private void sort5(int[] arr) {
        long start = System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i], left = 0, right = i - 1;
            while (left <= right) {
                int mid = (int) Math.floor((double) (left + right) / 2);
                if (tmp < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (i - left >= 0) System.arraycopy(arr, left, arr, left + 1, i - left);
            arr[left] = tmp;
        }
        long end = System.nanoTime();
        System.out.println("----sort4运行时间------：" + (end - start));
        //Arrays.stream(arr).forEach(System.out::println);
    }

}
