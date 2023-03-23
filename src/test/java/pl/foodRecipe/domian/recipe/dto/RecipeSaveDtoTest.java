package pl.foodRecipe.domian.recipe.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import pl.foodRecipe.domain.recipe.dto.RecipeSaveDto;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeSaveDtoTest {

    private RecipeSaveDto recipeSaveDto;

    @BeforeEach
    public void setUp() {
        recipeSaveDto = new RecipeSaveDto();
        recipeSaveDto.setId(1L);
        recipeSaveDto.setTitle("Recipe Title");
        recipeSaveDto.setShortDescription("Recipe Short Description");
        recipeSaveDto.setDescription("Recipe Description");
        recipeSaveDto.setYoutubeTrailerId("Recipe Trailer Id");
        recipeSaveDto.setCategory("Recipe Category");
        recipeSaveDto.setPromoted(true);
        recipeSaveDto.setPoster(new MockMultipartFile("poster", "poster.jpg", "image/jpeg", new byte[10]));
    }

    @Test
    public void testRecipeSaveDto() {
        assertThat(recipeSaveDto.getId()).isEqualTo(1L);
        assertThat(recipeSaveDto.getTitle()).isEqualTo("Recipe Title");
        assertThat(recipeSaveDto.getShortDescription()).isEqualTo("Recipe Short Description");
        assertThat(recipeSaveDto.getDescription()).isEqualTo("Recipe Description");
        assertThat(recipeSaveDto.getYoutubeTrailerId()).isEqualTo("Recipe Trailer Id");
        assertThat(recipeSaveDto.getCategory()).isEqualTo("Recipe Category");
        assertThat(recipeSaveDto.isPromoted()).isEqualTo(true);
        assertThat(recipeSaveDto.getPoster().getOriginalFilename()).isEqualTo("poster.jpg");
        assertThat(recipeSaveDto.getPoster().getContentType()).isEqualTo("image/jpeg");
        assertThat(recipeSaveDto.getPoster().getSize()).isEqualTo(10);
    }

}

