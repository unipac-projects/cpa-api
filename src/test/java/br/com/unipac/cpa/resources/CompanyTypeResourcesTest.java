package br.com.unipac.cpa.resources;

import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.domain.CompanyType;
import br.com.unipac.cpa.model.service.CompanyTypeService;
import br.com.unipac.cpa.util.JsonUtil;
import br.com.unipac.cpa.web.resources.CompanyTypeResources;
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
@WebMvcTest(CompanyTypeResources.class)
public class CompanyTypeResourcesTest {
    /** The Constant LOGGER. */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyTypeService service;

    private CompanyType getCompanyType() {
        return CompanyType.builder()
                .name("Bradesco")
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
    public void find_by_companyTypes_and_thenStatus200_and_companyType() throws Exception {
        Long id = 1l;
        String response = JsonUtil.getJson(getCompanyType());

        CompanyType companyType = getCompanyType();
        given(service.findById(id).get()).willReturn(companyType);

        mockMvc.perform(get("/api/v1/companyTypes/{id}", id)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    public void givenCompanyTypes_whenGetCompanyTypes_thenStatus201() throws Exception {

        CompanyType url = getCompanyType();
        when(this.service.save(url)).thenReturn(url);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/companyTypes")
                .accept(MediaType.APPLICATION_JSON).content(JsonUtil.getJson(getCompanyType()))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String content = response.getContentAsString();
        logger.info(content);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }



}
