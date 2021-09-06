package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id; //PK를 매핑한것
    //객체에선 username을 쓰고싶은데, DB의 Column엔 name이라는 컬럼을 쓰고싶을때

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    @Column(name = "USERNAME")
    private String username;

    @OneToOne
    @JoinColumn(name="LOCKER_ID")//안잡아도 디폴트가 잡히긴 하지만 깔끔하게!
    private Locker locker;


    @ManyToOne
    @JoinColumn(name="TEAM_ID",insertable = false, updatable = false)
    private Team team;

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

    public Member() {}

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

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }
}