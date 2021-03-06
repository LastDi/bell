package org.example.practice.organization.dao;

import org.example.practice.handler.exception.EntityNotFoundException;
import org.example.practice.organization.dto.OrganizationDtoForListIn;
import org.example.practice.organization.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;
    private CriteriaBuilder builder;
    private Map<String, Supplier<?>> map;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
        this.builder = em.getCriteriaBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> all() {
        TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o", Organization.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Long id) {
        Organization organization = em.find(Organization.class, id);
        if (organization == null)
            throw new EntityNotFoundException("Organization not found");
        return organization;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        em.merge(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> allByTerms(OrganizationDtoForListIn dto) {
        CriteriaQuery<Organization> criteria = buildCriteria(dto);
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getResultList();
    }

    private void initMap(OrganizationDtoForListIn dto) {
        if (map == null)
            map = new HashMap<>();
        map.put("name", dto::getName);
        map.put("inn", dto::getInn);
    }

    private CriteriaQuery<Organization> buildCriteria(OrganizationDtoForListIn dto) {
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> organization = criteria.from(Organization.class);
        initMap(dto);
        List<Predicate> predicatesList = map.entrySet().stream()
                .filter(pair -> pair.getValue().get() != null)
                .map(pair -> builder.equal(organization.get(pair.getKey()), pair.getValue().get()))
                .collect(Collectors.toList());
        if (dto.isActive() != null) {
            Predicate isActive = builder.equal(organization.get("active"), Boolean.parseBoolean(dto.isActive()));
            predicatesList.add(isActive);
        }
        Predicate[] predicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(predicates);
        criteria.where(predicates);
        return criteria;
    }
}
