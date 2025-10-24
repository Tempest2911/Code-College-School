package org.example.thicuoiki.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "xe")
public class Xe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Nationalized
    @Column(name = "ma_xe", length = 20)
    @NotBlank(message = "Mã không được để trống")
    private String maXe;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ten_xe")
    @NotBlank(message = "Tên không được để trống")
    @Pattern(
            regexp = "^[\\p{L}0-9\\s]+$",
            message = "Tên chỉ được chứa chữ và khoảng trắng hoặc ký tự đặc biệt"
    )
    private String tenXe;

    @Column(name = "gia", precision = 18, scale = 2)
    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải lớn hơn 0")
    @Digits(integer = 20, fraction = 1, message = "Giá chỉ được nhập số và tối đa 2 chữ số thập phân")
    private BigDecimal gia;

    @Column(name = "ngay_san_xuat")
    private LocalDate ngaySanXuat;

    @Column(name = "so_cho_ngoi")
    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
    @Digits(integer = 10, fraction = 0, message = "Số lượng chỉ được nhập số nguyên, không được có chữ hoặc ký tự đặc biệt")
    private Integer soChoNgoi;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_loai_xe")
    private LoaiXe idLoaiXe;

}