package pl.foodRecipe.domain.recipe;

import pl.foodRecipe.domain.category.Category;

import javax.persistence.*;

import lombok.*;
import pl.foodRecipe.domain.rating.Rating;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @OneToMany(mappedBy = "recipe")
    private Set<Rating> ratings = new HashSet<>();
    private boolean promoted;
    private String poster;


}