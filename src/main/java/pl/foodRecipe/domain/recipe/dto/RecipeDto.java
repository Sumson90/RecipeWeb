package pl.foodRecipe.domain.recipe.dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class RecipeDto {
    private Long id;
    private String title;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;

    private String category;
    private boolean promoted;
    private String poster;
    private double avgRating;
    private int ratingCount;


}
