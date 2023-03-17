package pl.foodRecipe.recipe.dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecipeDto {
    private Long id;
    private String title;
    private String category;
    private boolean promoted;
}
