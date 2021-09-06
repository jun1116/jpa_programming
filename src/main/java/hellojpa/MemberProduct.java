package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MemberProduct {
    @Id @GeneratedValue
    @Column(name = "MEMBER_PRODUCT_ID")
    private Long id;

    private int orderAmount;
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;
}
