package hello.hellospring.controller;

public class MemberForm {
    private String name;//createMemberForm의 name과 매칭되면서 값이 들어간다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
