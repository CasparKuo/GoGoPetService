package com.gogopet.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.gogopet.backend.model.entity.MemberInfo;

public interface MemberInfoDao extends CrudRepository<MemberInfo, String> {

}
