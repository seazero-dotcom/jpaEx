package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //EntityManagerFactory는 애플리케이션 로딩시점에 딱 하나만 만들어야한다.

        EntityManager em = emf.createEntityManager();
        // 실제 내가 DB에 저장하는 행위같은 트랜잭션 단위(ex. 고객이 들어와서 어떤 행위를 하고 나간다. 고객이 어떤 상품을 장바구니에 담는다.)를 할 때마다 DB 커넥션을 얻어서 쿼리를 날려서 종료되는 행위를 하면
        // 한 일관적인 단위를 할 떄마다 EntityManager를 꼭 만들어줘야한다.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // persist 나 commit에서 문제가 생겼을 때 아래쪽에 close가 안되고 되게 안좋은 코드이다.
        // 정석코드는 try-catch안에다가 한다.
        try{
            Member member = new Member();
            member.setId(2L); // long이라서 1L로 써준다
            member.setName("HelloB");

            em.persist(member);

            tx.commit(); // 정상적일때는 commit을 하고
        } catch (Exception e){
            tx.rollback(); // 문제가 생기면 rollback을 하고
        } finally {
            em.close(); // 작업이 다 끝나면 entitymanager를 닫아주고 **이거 중요해요** em이 내부적으로 DB커넥션을 물고 동작하기 떄문에 사용을 다 하고나면 꼭 닫아줘야한다.
        }

        emf.close(); // 전체 애플리케이션이 끝나면 entitymanagerfactory까지 닫아준다.
    }
}