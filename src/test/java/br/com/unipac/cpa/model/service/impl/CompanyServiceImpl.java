package br.com.unipac.cpa.model.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceFoundException;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.service.CompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unipac.cpa.model.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger log = LogManager.getLogger(CompanyServiceImpl.class);

	@Autowired
	private CompanyRepository clienteRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.uaijug.indikoj.model.service.ClienteSerivce#salvar(br.com.uaijug.
	 * indikoj.model.domain.Cliente)
	 */
	@Override
	public Company save(Company company) {
		if (company != null) {
			Optional<Company> result = findByName(company.getName());
			if (!result.isPresent()) {
				//if (!CPFUtil.valida(professor.getDocumentId())) {
					//throw new CPFValidationException("Erro na validação do cpf!");
				//} else {
					return clienteRepository.save(company);
				//}
			} else {
				throw new ResourceFoundException("Cliente já existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.uaijug.indikoj.model.service.ClienteSerivce#listar()
	 */
	@Override
	@Cacheable(Constants.COMPANYS_IN_CACHE)
	public List<Company> listAll() {
		return clienteRepository.findAll();
	}

	@Override
	@Cacheable(Constants.COMPANYS_IN_CACHE)
	public Page<Company> findAllPageable(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.uaijug.indikoj.model.service.ClienteSerivce#alterar(java.lang.
	 * Long, br.com.uaijug.indikoj.model.domain.Cliente)
	 */
	@Override
	public Company edit(Long id, Company company) {
		if (id != null && company != null) {
			Optional<Company> result = findById(id);
			if (result.isPresent()) {
				//if (!CPFUtil.valida(professor.getDocumentId())) {
				//	throw new CPFValidationException("Erro na validação do cpf!");
				//} else {
					result.get().updade(id, company);
					log.info("Objeto Gravado!");
					return clienteRepository.save(result.get());
				//}

			} else {
				throw new ResourceNotFoundException("Cliente não existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.indikoj.model.service.ClienteSerivce#buscarPorId(java.lang.
	 * Long)
	 */
	@Override
	public Optional<Company> findById(Long id) {
		return clienteRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.indikoj.model.service.ClienteSerivce#buscarPorId(java.lang.
	 * Long)
	 */
	@Override
	public Optional<Company> findByName(String name) {
		return clienteRepository.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.indikoj.model.service.ClienteSerivce#delete(java.lang.Long)
	 */
	@Override
	public boolean remove(Long id) {
		Optional<Company> result = findById(id);
		if (result != null) {
			clienteRepository.deleteById(id);
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}	
}
