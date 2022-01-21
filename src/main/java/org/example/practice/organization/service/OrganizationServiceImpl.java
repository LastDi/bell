package org.example.practice.organization.service;

import org.example.practice.mapper.EntityToDtoMapper;
import org.example.practice.organization.dao.OrganizationDao;
import org.example.practice.organization.dto.*;
import org.example.practice.organization.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao dao;
    private final EntityToDtoMapper mapper;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, EntityToDtoMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OrganizationDtoForSave dto) {
        Organization organization = new Organization(dto.getName(), dto.getFullName(), dto.getInn(), dto.getKpp(), dto.getAddress(), dto.getPhone(), dto.isActive());
        dao.save(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationDto> organizations() {
        List<Organization> organizations = dao.all();
        return mapper.mapAll(organizations, OrganizationDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationDtoForUpd dto) {
        Organization organization = dao.loadById(dto.getId());
        if (organization == null)
            throw new IllegalArgumentException();
        organization.setName(dto.getName());
        organization.setFullName(dto.getFullName());
        organization.setInn(dto.getInn());
        organization.setKpp(dto.getKpp());
        organization.setAddress(dto.getAddress());
        organization.setPhone(dto.getPhone());
        organization.setActive(dto.isActive());
        dao.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationDto organization(Long id) {
        Organization organization = dao.loadById(id);
        if (organization == null)
            throw new IllegalArgumentException("Organization not found");
        return mapper.map(organization, OrganizationDto.class);
    }

    @Override
    public List<OrganizationDtoForListOut> organizationsByTerms(OrganizationDtoForListIn dto) {
        return mapper.mapAll(dao.allByTerms(dto), OrganizationDtoForListOut.class);
    }
}
