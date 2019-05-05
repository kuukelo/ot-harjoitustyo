package hellodbauthentication;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kaveripyynto extends AbstractPersistable<Long> {

    @ManyToOne
    private Henkilo lahettaja;
    private Henkilo kohde;
    private LocalDateTime aika;
    private boolean kasitelty;

}
