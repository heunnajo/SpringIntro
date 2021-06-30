package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {//이제 이 Member 클래스는 JPA가 관리하는 엔티티가 된다!
    //PK 맵핑!!!
    //DB에 의해 자동으로 생성되는 것을 Identity라고 한다!
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//시스템이 저장하는 ID.
    private String name;//이름

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
