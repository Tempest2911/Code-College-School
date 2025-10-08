public class Average {
//    Viết một phương thức average(int[] arr) để tính trung bình cộng của các phần tử trong mảng số nguyên.
//    Nếu mảng rỗng, phương thức phải ném ra ngoại lệ IllegalArgumentException với thông báo "Mảng rỗng!".
//    Ngược lại, trả về trung bình cộng của các phần tử.

    public double average(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Mảng rỗng!");
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }
}
