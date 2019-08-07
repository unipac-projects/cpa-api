package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.response.LocalResponse;
import br.com.unipac.cpa.web.support.LocalSupport;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/locals")
public class LocalResources  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LocalSupport localSupport;

    @GetMapping()
    @ResponseBody
    @Timed
    public ResponseEntity<List<LocalResponse>> getAll() {
        List<LocalResponse> dtos = localSupport.get();

        if (dtos != null) {
            logger.info(Constants.TOTAL + dtos.size());
            return ResponseEntity.ok(dtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }



}
