package sorzzzzy.dearmydog.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // JPA 스펙 상 엔티티나 임베디드 타입은 기본 생성자를 protected 또는 public 으로 설정해야 함
    protected Address() {

    }

    // 값 타입은 변경 불가능하게 설계해야 함
    // setter를 제거하고 생성자에서 값을 모두 초기화해 변경 불가능한 클래스를 만듦
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
