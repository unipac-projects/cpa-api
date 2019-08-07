CREATE TABLE IF NOT EXISTS `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(60) NOT NULL,
  `mobile` varchar(60) NOT NULL,
  `company_type_id` bigint(20) NOT NULL,
  `local_id` bigint(20) NOT NULL,
  `person_type` varchar(60) NOT NULL,
  `document_region` varchar(60) NOT NULL,
  `social_id` bigint(20) NOT NULL,
  `nationality` varchar(60) NOT NULL,
  `create_by` varchar(255) NOT NULL DEFAULT 'system_user',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255),
  `last_modified_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;