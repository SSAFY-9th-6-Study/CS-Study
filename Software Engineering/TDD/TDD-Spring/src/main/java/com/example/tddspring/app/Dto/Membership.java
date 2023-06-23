package com.example.tddspring.app.Dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
@Builder //빌더 패턴을 적용할 수 있게 된다. -> 객체 생성과정을 간편하게
@NoArgsConstructor  //기본 생성자
@AllArgsConstructor //매개변수 생성자
@Data
//해당 어노테이션이 없으면 membershipRepository에서 membership 클래스를 인식할 수 없다
//추가로 JPA에서 관리되는 엔티티를 위해서는 기본 생성자가 필요로 한다.
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Membership클래스에 어노테이션 헤제시 entitymanagerFactory라는 예외 발상한다
    //Membership에 식별자(ID)가 없다는 것 -> 어노테이션을 추가한다.
    private Long id;



    //멤버십 등록 테스트를 위한 코드
    @Column(nullable = false, length = 20)
    private String membershipName;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer point;

    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(length = 20)
    private LocalDateTime updatedAt;

}
