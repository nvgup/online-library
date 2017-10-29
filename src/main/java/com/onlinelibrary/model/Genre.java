package com.onlinelibrary.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre implements Serializable {

    private Long genreId;
    private String name;
    private List<Book> books = new ArrayList<Book>();

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(Long genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    @Id
    @Column(name = "genre_id", nullable = false, unique = true)
    @SequenceGenerator(name = "genre_genre_id_seq",
            sequenceName = "genre_genre_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "genre_genre_id_seq")
    public Long getGenreId() {
        return genreId;
    }

    private void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.toLowerCase().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Genre))
            return false;
        Genre other = (Genre) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equalsIgnoreCase(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
