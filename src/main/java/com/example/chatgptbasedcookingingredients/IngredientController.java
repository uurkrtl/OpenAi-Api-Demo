package com.example.chatgptbasedcookingingredients;


import com.example.chatgptbasedcookingingredients.service.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final ChatGptService chatGptService;

    @PostMapping
    String categorizeIngredient(@RequestParam String ingredient) {

        // TODO: This should return "vegan", "vegetarian" or "regular" depending on the ingredient.

        String result = chatGptService.askQuestion(ingredient);

        return result;
    }

}
