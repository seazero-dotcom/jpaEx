package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 이걸 꼭 넣어야한다. jpa를 사용하는 얘구나 ~ 인식한다.
//@Table(name = "USER") // Memeber테이블 말고 USER테이블에 넣고싶을 때 이렇게 써줘야한다.
public class Member {

    @Id // 어노테이션을 선택할 때  javax.persistence 를 선택해야합니다
    private Long id;
    //@Column(name = "username") // DB에 컬럼이 name이 아니고 username이야 그러면 이렇게 매핑 해줘야한다.
    // 이렇게 하면 콘솔에 쿼리 나갈때 보면 name, id 가 아니고 username, id 로 나간다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
