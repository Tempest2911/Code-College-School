package model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "BorrowRequests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "BookId", nullable = false)
    private Books book;

    @Column(name = "RequestDate", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime requestDate;


    @Column(name = "Status", nullable = false, length = 10)
    private String status; // PENDING | APPROVED | REJECTED

    public Date getRequestDateAsDate() {
        return java.util.Date.from(requestDate.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }

}
