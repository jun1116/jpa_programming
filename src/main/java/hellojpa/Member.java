package hellojpa;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //PK를 매핑한것
    //객체에선 username을 쓰고싶은데, DB의 Column엔 name이라는 컬럼을 쓰고싶을때

    @Column(name = "USERNAME")
    private String username;


//    @ManyToOne(fetch = FetchType.LAZY)//팀쿼리는 늦게나가! 중요중요
//    @JoinColumn(name = "TEAM_ID")//DB에 직접 이 값이 있어야해
//    private Team team;

//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);//나 자신 인스턴스를 여기 넣음으로, 실수를 방지할 수 있음
//    }

//    @Override
//    public String toString() {
//        return "Member{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
////                ", team=" + team +
//                '}';
//    }

//    public Team getTeam() {
//        return team;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}