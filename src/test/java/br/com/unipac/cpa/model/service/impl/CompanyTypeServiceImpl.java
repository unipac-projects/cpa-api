package br.com.unipac.cpa.model.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceFoundException;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.CompanyType;
import br.com.unipac.cpa.model.service.CompanyTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unipac.cpa.model.repository.CompanyTypeRepository;

@Service
public class CompanyTypeServiceImpl implements CompanyTypeService {

	private static final Logger log = LogManager.getLogger(CompanyTypeServiceImpl.class);

	@Autowired
	private CompanyTypeRepository clientTypeeRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.indikoj.model.service.ClientTypeeSerivce#salvar(br.com.uaijug.
	 * indikoj.model.domain.ClientTypee)
	 */
	@Override
	public CompanyType save(CompanyType clientType) {
		if (clientType != null) {
			Optional<CompanyType> result = findByName(clientType.getName());
			if (!result.isPresent()) {
				return clientTypeeRepository.save(clientType);
			} else {
				throw new ResourceFoundException("ClientTypee já existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.uaijug.indikoj.model.service.ClientTypeeSerivce#listar()
	 */
	@Override
	@Cacheable(Constants.COMPANYS_TYPES_IN_CACHE)
	public List<CompanyType> listAll() {
		return clientTypeeRepository.findAll();
	}

	@Override
	@Cacheable(Constants.COMPANYS_TYPES_IN_CACHE)
	public Page<CompanyType> findAllPageable(Pageable pageable) {
		return clientTypeeRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.indikoj.model.service.ClientTypeeSerivce#alterar(java.lang.
	 * Long, br.com.uaijug.indikoj.model.domain.ClientTypee)
	 */
	@Override
	public CompanyType edit(Long id, CompanyType clientType) {
		if (id != null && clientType != null) {
			Optional<CompanyType> result = findById(id);
			if (result.isPresent()) {
				result.get().updade(id, clientType);
				log.info("Objeto Gravado!");
				return clientTypeeRepository.save(result.get());
			} else {
				throw new ResourceNotFoundException("ClientTypee não existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.indikoj.model.service.ClientTypeeSerivce#buscarPorId(java.lang.
	 * Long)
	 */
	@Override
	public Optional<CompanyType> findById(Long id) {
		return clientTypeeRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.indikoj.model.service.ClientTypeeSerivce#buscarPorId(java.lang.
	 * Long)
	 */
	@Override
	public Optional<CompanyType> findByName(String name) {
		return clientTypeeRepository.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.indikoj.model.service.ClientTypeeSerivce#delete(java.lang.Long)
	 */
	@Override
	public boolean remove(Long id) {
		Optional<CompanyType> result = findById(id);
		
		if (result != null) {
			clientTypeeRepository.deleteById(id);
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
}
