package br.com.unipac.cpa.resources;

import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.service.CompanyService;
import br.com.unipac.cpa.util.JsonUtil;
import br.com.unipac.cpa.web.resources.CompanyResources;
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
@WebMvcTest(CompanyResources.class)
public class CompanyResourcesTest {
    /** The Constant LOGGER. */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService service;

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
    public void find_by_companys_and_thenStatus200_and_company() throws Exception {
        Long id = 1l;
        String response = JsonUtil.getJson(getCompany());

        Company company = getCompany();
        given(service.findById(id).get()).willReturn(company);

        mockMvc.perform(get("/api/v1/companys/{id}", id)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    public void givenCompanys_whenGetCompanys_thenStatus201() throws Exception {

        Company url = getCompany();
        when(this.service.save(url)).thenReturn(url);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/companys")
                .accept(MediaType.APPLICATION_JSON).content(JsonUtil.getJson(getCompany()))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String content = response.getContentAsString();
        logger.info(content);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }



}
