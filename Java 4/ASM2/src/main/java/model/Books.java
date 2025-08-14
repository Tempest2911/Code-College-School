package model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Title", nullable = false, length = 200)
    private String title;

    @Column(name = "Author", nullable = false, length = 100)
    private String author;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;
}
