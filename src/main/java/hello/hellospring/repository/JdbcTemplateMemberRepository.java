package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;
    //injection 받을 수 없기 때문에 아래에 @Autowired로 구현한다.
//    public JdbcTemplateMemberRepository(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }
    //@Autowired//생성자가 딱 하나면 빈에 등록할 때 Autowired 생략가능하다.
    public JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {//원래 JdbcMemberRepository.java와 비교하면 엄청 길었던 코드가 단 2줄로 바뀐따!
        //쿼리문 날리고 결과를 mmberRowMapper()로 맵핑한다!(객체 생성)
        //결과를 리스트로 받고, Optional로 바꾸고 반환.
        List<Member> result =  jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),id);
        return  result.stream().findAny();//result를 stream으로 바꿔서 findAny() => Optional로 반환한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //sql 결과를 리스트로 반환. resultset결과를 memberRowMapper()에서 객체로 맵핑.
        return jdbcTemplate.query("select * from member", memberRowMapper());//객체 생성에 대한 것은 밑에서 콜백으로 정의.
    }

    private RowMapper<Member> memberRowMapper(){
        //람다로 바꿀 수 있음!옵션 + 엔터
        //jdbc
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
