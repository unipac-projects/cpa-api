package br.com.unipac.cpa.resources;

import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.service.ProfessorService;
import br.com.unipac.cpa.util.JsonUtil;
import br.com.unipac.cpa.web.resources.ProfessorResources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfessorResources.class)
public class ProfessorResourcesTest {
    /** The Constant LOGGER. */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService service;

    private Professor getClient() {
        return Professor.builder()
                .name("Bradesco")
                .email("root@localhost")
                .mobile("9999999999")
                .company(getCompany())
                .build();
    }

    private Company getCompany() {
        return Company.builder()
                .name("Everis")
                .email("root@localhost")
                .address("Joao naves de Avela")
                .personType(PersonType.LEGAL)
                .phone("9999999999")
                .mobile("9999999999")
                .documentRegion(DocumentRegion.STATE_MG)
                .socialId(9999999999l)
                .nationality("Brasilian")
                .build();
    }

    @Test
    public void find_by_clients_and_thenStatus200_and_client() throws Exception {
        Long id = 1l;
        String response = JsonUtil.getJson(getClient());

        Professor professor = getClient();
        given(service.findById(id).get()).willReturn(professor);

        mockMvc.perform(get("/api/v1/clients/{id}", id)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    public void givenClients_whenGetClients_thenStatus201() throws Exception {

        Professor url = getClient();
        when(this.service.save(url)).thenReturn(url);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/clients")
                .accept(MediaType.APPLICATION_JSON).content(JsonUtil.getJson(getClient()))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String content = response.getContentAsString();
        logger.info(content);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }



}
