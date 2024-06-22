package com.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Entity(name = "USERS")
//@Table(name = "USERS")
@Getter
@Setter
@Entity // 기본 id 생성도 꼭 있어야함
@NoArgsConstructor
public class Member {
    @Id
   // @GeneratedValue(strategy = GenerationType.SEQUENCE) // SEQUENCE - 기본키만 생성해주는 역할
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성전략. 테이블한테 맡김 주로 사용
    private Long memberId;

    @Setter
    @Column(nullable = false, updatable = false, unique = true)
    // null이 들어올 수 없는 컬럼이 만들어짐, 수정 불가능, 유니크한 값으로 중복된 값이 들어올 수 없음
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 13)
    private String phone;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt = LocalDateTime.now();
// LocalDateTime - 데이터베이스에 자동으로 타임스탬프로 만들어짐

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

//    @Transient // 엔티티클래스에서는 가지고 있는데 데이터베이스에서는 저장하지 않음
//    // 컬럼이랑 매핑하지 않겠다
//    // 엔티티클래스에서만 사용. 임시로 데이터를 보관할 때 사용.
//    // 연산되는 중간결과값 등에서 사용함
//    private String age;

    @OneToMany(mappedBy = "member") // 1에서 N을 연결할 때 사용
    //(mappedBy = "member") - 이미 이어져있는데 양방향으로 연결해야 될 때 사용
    // 나를 참조하고 있는 애를 작성
    // order가 참조하고 있는걸 아는데 뭘로 참조하고 있는지
    // 즉, order에서 외래키처럼 사용되는 애가 누구인지 작성
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);

        if (order.getMember() != this) {
            order.addMember(this);
        }
        //order의 입장에서도 연결이 필요
        //order가 가지고 있는 member에 나 자신 Member(this)fmf cnrk

    }
    public Member(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
