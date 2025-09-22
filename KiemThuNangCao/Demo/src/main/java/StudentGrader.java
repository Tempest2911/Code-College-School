public class StudentGrader {
    public String XepLoai(int diem) {

        if (diem < 0 || diem > 100) {
            throw new IllegalArgumentException("Diem khong hop le");
        }
        if (diem <= 40) {
            return "Xếp loại: F";
        } else if (diem <= 60) {
            return "Xếp loại: D";
        } else if (diem <= 70) {
            return "Xếp loại: C";
        } else if (diem <= 80) {
            return "Xếp loại: B";
        } else {
            return "Xếp loại: A";
        }
    }
}
