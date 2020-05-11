package com.live.zhf.learn;

public class AnnoationTest {
    @Test(min = 6,max = 10,description = "用户名长度在6-10个字符之间")
    private String name;
    public AnnoationTest() {

    }
    public AnnoationTest(String name, String passdword) {
        this.name = name;
        this.passdword = passdword;
    }

    @Test(min = 6,max =10,description = "密码长度在6-10个字符之间")

    private String passdword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassdword() {
        return passdword;
    }

    public void setPassdword(String passdword) {
        this.passdword = passdword;
    }
}
