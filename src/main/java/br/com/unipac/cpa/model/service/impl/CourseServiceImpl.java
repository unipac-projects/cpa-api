package br.com.unipac.cpa.model.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.repository.CourseRepository;
import br.com.unipac.cpa.model.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course save(Course course) {
		logger.info("Saved: " + !StringUtils.isEmpty(course.toString()));
		return courseRepository.save(course);
	}

	@Override
	public Course edit(Long id, Course course) {
		if (id != null && course != null) {
			Optional<Course> result = findById(id);
			if (result.isPresent()) {
				course.setId(id);
				return courseRepository.save(course);
			} else {
				throw new ResourceNotFoundException("ClientTypee não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<Course> findById(Long id) {
		return courseRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.SEGMENTS_IN_CACHE)
	public List<Course> listAll() {
		return courseRepository.findAll();
	}

	@Override
	@Cacheable(Constants.SEGMENTS_IN_CACHE)
	public Page<Course> findAllPageable(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<Course> result = courseRepository.findById(id);

		if (result.isPresent()) {
			courseRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public Optional<Course> findByName(String name) {
		return courseRepository.findByName(name);
	}
}
