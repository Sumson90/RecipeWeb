package pl.foodRecipe.domain.category.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CategoryDto {
    private Long id;
    private String name;
    private String description;


}
