package org.silvachristian.searchfilms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "favorites")
public class FavoriteSaved {
    @Id
    private Long id;
    @NonNull
    private Long movieId;
    @NonNull
    private Long userId;
}
