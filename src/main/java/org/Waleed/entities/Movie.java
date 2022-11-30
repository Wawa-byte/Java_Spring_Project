package org.Waleed.entities;

import lombok.*;

@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class Movie {
    private int movie_id;
    private String name;
    private int yearReleased;
    private int takings;
    private int director_id;
}
