package com.carte.archicarte.repository;

import com.carte.archicarte.entity.ViewPoliceProassurEntity;
import com.carte.archicarte.entity.ViewSinisterProassurEntity;

import java.util.List;

public interface ViewSinistreProassurRepository extends ReadOnlyRepository<ViewSinisterProassurEntity, Integer> {
    List<ViewSinisterProassurEntity> findByNSinister(String sinister);


}
