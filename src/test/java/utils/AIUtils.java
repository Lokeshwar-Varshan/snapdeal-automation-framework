package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AIUtils {

    // Simulating AI-generated suggestions
    public static String getAISuggestedProduct() {

        List<String> aiGenerated = Arrays.asList(
                "gaming laptop",
                "wireless headphones",
                "running shoes",
                "smart watch",
                "office chair"
        );

        return aiGenerated.get(new Random().nextInt(aiGenerated.size()));
    }
}