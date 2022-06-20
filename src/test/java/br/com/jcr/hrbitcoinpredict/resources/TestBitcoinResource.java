package br.com.jcr.hrbitcoinpredict.resources;

import br.com.jcr.hrbitcoinpredict.entities.SimpleBitcoinPredict;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.jcr.hrbitcoinpredict.builders.ResponseEntityResultDataFullBitcoinBuilder.oneResponseEntityResultDataFullBitcoin;
import static br.com.jcr.hrbitcoinpredict.builders.ResponseEntityResultDataFullBitcoinBuilder.oneResponseEntityResultDataFullBitcoinDateNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BitcoinResource.class)
public class TestBitcoinResource {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BitcoinResource bitcoinResource;

    @Test
    public void getPredictByDateReturnPredict() throws Exception {
        when(bitcoinResource.getBitcoinPredictByDate(eq("14"), eq("06"), eq("2022")))
                .thenReturn(oneResponseEntityResultDataFullBitcoin().getNow());

        mvc.perform( MockMvcRequestBuilders
                        .get("/bitcoin/14/06/2022")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "\t'status': 200,\n" +
                        "\t'message': 'Predição realizada com sucesso!',\n" +
                        "\t'body': {\n" +
                        "\t\t'simpleBitcoin': {\n" +
                        "\t\t\t'open': 111762.21,\n" +
                        "\t\t\t'high': 119425.27,\n" +
                        "\t\t\t'low': 110491.44,\n" +
                        "\t\t\t'close':  113820.47\n" +
                        "\t\t},\n" +
                        "\t\t'prediction':  115040.65,\n" +
                        "\t\t'date': '2022-06-14',\n" +
                        "\t\t'ascend': true\n" +
                        "\t}\n" +
                        "}"));
    }

    @Test
    public void getPredictByNowReturnPredict() throws Exception {
        when(bitcoinResource.getBitcoinPredictByNow())
                .thenReturn(oneResponseEntityResultDataFullBitcoin().getNow());

        mvc.perform( MockMvcRequestBuilders
                        .get("/bitcoin")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "\t'status': 200,\n" +
                        "\t'message': 'Predição realizada com sucesso!',\n" +
                        "\t'body': {\n" +
                        "\t\t'simpleBitcoin': {\n" +
                        "\t\t\t'open': 111762.21,\n" +
                        "\t\t\t'high': 119425.27,\n" +
                        "\t\t\t'low': 110491.44,\n" +
                        "\t\t\t'close':  113820.47\n" +
                        "\t\t},\n" +
                        "\t\t'prediction':  115040.65,\n" +
                        "\t\t'date': '2022-06-14',\n" +
                        "\t\t'ascend': true\n" +
                        "\t}\n" +
                        "}"));
    }

    @Test
    public void getPredictBySimpleBitcoinPredictReturnPredictDateNull() throws Exception {
        SimpleBitcoinPredict simpleBitcoinPredict = SimpleBitcoinPredict.builder()
                .open(111762.21f)
                .high(119425.27f)
                .low(110491.44f)
                .build();
        when(bitcoinResource.getBitcoinPredictBySimpleBitcoinPredict(simpleBitcoinPredict))
                .thenReturn(oneResponseEntityResultDataFullBitcoinDateNull().getNow());

        mvc.perform( MockMvcRequestBuilders
                        .post("/bitcoin")
                        .content(asJsonString(simpleBitcoinPredict))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "\t'status': 200,\n" +
                        "\t'message': 'Predição realizada com sucesso!',\n" +
                        "\t'body': {\n" +
                        "\t\t'simpleBitcoin': {\n" +
                        "\t\t\t'open': 111762.21,\n" +
                        "\t\t\t'high': 119425.27,\n" +
                        "\t\t\t'low': 110491.44,\n" +
                        "\t\t\t'close':  113820.47\n" +
                        "\t\t},\n" +
                        "\t\t'prediction':  115040.65,\n" +
                        "\t\t'date': null,\n" +
                        "\t\t'ascend': true\n" +
                        "\t}\n" +
                        "}"));
    }

    @Test
    public void getPredictBySimpleBitcoinPredictNoBodyReturnError() throws Exception {


        mvc.perform( MockMvcRequestBuilders
                        .post("/bitcoin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}