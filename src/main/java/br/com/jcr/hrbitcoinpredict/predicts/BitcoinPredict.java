package br.com.jcr.hrbitcoinpredict.predicts;

import ai.djl.MalformedModelException;
import ai.djl.Model;
import ai.djl.inference.Predictor;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.translate.Batchifier;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class BitcoinPredict {
    private Predictor<float [], Float> preditor;

    public BitcoinPredict() throws MalformedModelException, IOException {
        Path modelDir = Paths.get("src/main/java/br/com/jcr/hrbitcoinpredict/utils/models/");
        Model model = Model.newInstance("bitcoin_model.zip");
        model.load(modelDir);

        Translator<float[], Float> translator = new Translator<float[], Float>(){
            @Override
            public NDList processInput(TranslatorContext ctx, float[] input) throws Exception {
                NDManager manager = ctx.getNDManager();
                NDArray array = manager.create(input);
                return new NDList (array);
            }

            @Override
            public Float processOutput(TranslatorContext ctx, NDList list) {
                NDArray temp_arr = list.get(0);
                return temp_arr.getFloat();
            }

            @Override
            public Batchifier getBatchifier() {
                return Batchifier.STACK;
            }
        };

        preditor = model.newPredictor(translator);
    }

    public Float predict (float [] inputPredict) throws TranslateException {
        return preditor.predict(inputPredict);
    }
}
