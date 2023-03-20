package pl.foodRecipe.domain.recipe.dto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecipeSaveDto {

    private Long id;
    private String title;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;

    private String category;
    private boolean promoted;
    private MultipartFile poster;
}
