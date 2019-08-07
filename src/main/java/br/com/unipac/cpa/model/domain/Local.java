package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "local")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = {"city", "uf"})
@Data
public class Local extends AudityEntity {

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String uf;

    public void update(Long id, Local local) {
        super.setId(id);
        this.city = local.getCity();
        this.uf = local.getUf();
    }
}
