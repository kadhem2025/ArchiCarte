package com.carte.archicarte.repository;

import com.carte.archicarte.entity.ViewCxpProassurEntity;
import com.carte.archicarte.entity.ViewPoliceProassurEntity;

import java.util.List;

public interface ViewCxpProassurRepository extends ReadOnlyRepository<ViewCxpProassurEntity,Integer> {
    List<ViewCxpProassurEntity> findByNCxp(String cxp);
}
