package com.springboot;

import com.springboot.entity.Order;
import com.springboot.entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;


    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager(); // jpa를 사용
        this.tx = em.getTransaction();

        return args -> {

            // TODO 이 곳에 학습할 코드를 타이핑하세요!
            tx.begin();
            Member currentMember = new Member("lucky@gamil.com", "김러키", "010-1534-1654");
            //em.persist(currentMember);

            Order order = new Order();
            order.addMember(currentMember);
            currentMember.addOrder(order);
            em.persist(order);
            em.persist(currentMember);

            tx.commit();

            Order findOrder = em.find(Order.class, 1L);
            System.out.printf("findOrderMemberName : %s, findOrderMemberEmail : %s\n",
                    findOrder.getMember().getName(), findOrder.getMember().getEmail());

            Member findMember = em.find(Member.class, 1L);
            List<Order> orders = findMember.getOrders();
            orders.stream()
                    .forEach(currentOrder -> {
                        System.out.printf("findOrderId : %d, findOrderStatus : %s",
                                currentOrder.getOrderId(), currentOrder.getOrderStatus());
                    });

//            tx.begin();
//            Member member = em.find(Member.class, 1L);
//            member.setEmail("latte@gmail.com");
//            tx.commit();

       //    System.out.println("MemberId : " + member.getMemberId());
          //  example01();
          //  example02();
          // example03();
          //  example04();
          //  example05();
        };
    }

//    private void example01() {
//        Member member = new Member("lucky@gamil.com");
//
//        em.persist(member); // 영속성
//
//        Member resultMember = em.find(Member.class, 1L); // 조회
//        System.out.printf("Id : %d,  email : %s\n", resultMember.getMemberId(),
//                resultMember.getEmail());
//    }
//
//    private void example02() {
//        tx.begin(); // jpa는 트렌젝션 기준으로 테이블에 뭔가를 함
//        // 트렌젝션 시작
//
//        Member member = new Member("lucky@gamil.com");
//
//        em.persist(member); // 영속성 컨텍스트에 저장
//        // 쿼리문도 저장
//
//        tx.commit(); // commit을 호출하는 순간 객체를 데이터베이스에 저장??
//
//        Member resultMember = em.find(Member.class, 1L); // 조회
//
//        System.out.printf("Id : %d,  email : %s\n", resultMember.getMemberId(),
//                resultMember.getEmail());
//
//        Member resultMember1 = em.find(Member.class, 2L); // 다시 조회 2는 없어서
//
//        System.out.println(resultMember1 == null); // true가 됨
//
//    }
//
//    private void example03() {
//        tx.begin();
//
//        Member member1 = new Member("lucky@gamil.com");
//        Member member2 = new Member("latte@gmail.com");
//
//        em.persist(member1);
//        em.persist(member2);
//
//        tx.commit();
//    }
//
//    private void example04() {
//        tx.begin();
//        em.persist(new Member("lucky@gamil.com"));
//        tx.commit();
//
//        tx.begin(); // commit으로 트렌젝션이 종료되어 다시 시작
//        Member member1 = em.find(Member.class, 1L);
//        // 1L 객체는 영속성컨텍스트에 있어서 select문 사용X
//        member1.setEmail("latte@gmail.com");
//        tx.commit(); // 1차 캐시와 비교해서 달라진 점을 데이터베이스에 변경된 값으로 저장
//    }
//
//    private void example05() {
//        tx.begin();
//        em.persist(new Member("lucky@gmail.com"));
//        tx.commit();
//
//        tx.begin();
//        Member member = em.find(Member.class, 1L);
//        em.remove(member); // 쿼리문의 delete와 같음
//        // 캐시에서 객체를 지우고 쓰기 지연 저장소에 delete를 만듦
//        tx.commit();
 //   }
}
