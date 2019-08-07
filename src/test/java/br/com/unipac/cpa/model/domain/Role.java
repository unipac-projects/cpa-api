package br.com.unipac.cpa.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "role" })
@Data
public class Role extends AudityEntity {

	private static final long serialVersionUID = -2719226491222823385L;

	@Getter
	@Setter
	@Column(name = "role")
	private String role;

}