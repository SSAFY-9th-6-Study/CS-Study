package com.example.tddspring.app.repository;

import com.example.tddspring.app.Dto.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
//    Membership findByUserIdAndMembershipType(final String userId, final MembershipType membershipType);
//    List<Membership> findAllByUserId(final String userId);
}
