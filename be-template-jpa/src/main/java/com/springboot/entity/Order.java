package com.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    //@Column//ENUM은 COLUMN으로 작성하지 않음
    @Enumerated(EnumType.STRING)
    // enum타입을 매핑
    // enum 이름을 데이터베이스에 저장 - 확장을 위해 EnumType.STRING 사용
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;

    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ManyToOne // 다에서 1로 연결
    @JoinColumn(name = "MEMBER_ID") // member가 외래키처럼 사용
    //(name = "MEMBER_ID") - 데이터베이스에서 연결되는 외래키 컬럼
    private Member member; // 외래키 역할
    public void addMember(Member member) {
        //this.member = member;
        // 외부에서 호출해야돼서 접근제어자 public
        // 주문을 발생시킬때마다 직접 설정해야함
        this.member = member;

        if (!member.getOrders().contains(this)) {
            member.addOrder(this);
        }
         // member입장에서도 연결이 필요
        //member이 가지고 있는 order(List)에 나 자신 Order(this)를 추가

    }
    private enum OrderStatus {
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 완료"),
        ORDER_CANCEL(4, "주문 취소");


        @Getter
        private int stepNumber;
        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
}
