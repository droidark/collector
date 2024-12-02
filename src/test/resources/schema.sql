-- DROP TABLES
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS publisher_social_networks;
DROP TABLE IF EXISTS titles;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS titles_genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS titles_authors_roles;
DROP TABLE IF EXISTS issues;

-- CREATE SEQUENCES
CREATE SEQUENCE publishers_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE publisher_social_networks_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE titles_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE genres_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE authors_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE roles_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE issues_seq START WITH 1 INCREMENT BY 50;

-- CREATE TABLES
CREATE TABLE publishers (
                            id INT DEFAULT NEXTVAL('publishers_seq') PRIMARY KEY NOT NULL,
                            name VARCHAR(100),
                            lookup_key VARCHAR(100),
                            information VARCHAR(500),
                            logo VARCHAR(100),
                            url VARCHAR(100)
);

CREATE TABLE publisher_social_networks (
                                           id INT DEFAULT NEXTVAL('publisher_social_networks_seq') PRIMARY KEY NOT NULL,
                                           publisher_id INT NOT NULL,
                                           type VARCHAR(100) NOT NULL,
                                           username VARCHAR(100) NOT NULL,
                                           url VARCHAR(100) NOT NULL,
                                           CONSTRAINT publisher_social_networks_publisher_id_fk FOREIGN KEY (publisher_id) REFERENCES publishers(id) ON DELETE CASCADE
);

CREATE TABLE titles (
                        id INT DEFAULT NEXTVAL('titles_seq') PRIMARY KEY NOT NULL,
                        publisher_id INT NOT NULL,
                        name VARCHAR(255),
                        lookup_key VARCHAR(100),
                        cover VARCHAR(100),
                        demography VARCHAR(100),
                        format VARCHAR(100),
                        frequency VARCHAR(100),
                        release_date TIMESTAMP,
                        status VARCHAR(50),
                        type VARCHAR(100),
                        total_issues INT,
                        CONSTRAINT titles_publisher_id_fk FOREIGN KEY (publisher_id) REFERENCES titles(id) ON DELETE CASCADE
);

CREATE TABLE genres (
                        id INT DEFAULT NEXTVAL('genres_seq') PRIMARY KEY NOT NULL,
                        genre VARCHAR(255)
);

CREATE TABLE titles_genres (
                               title_id INT NOT NULL,
                               genre_id INT NOT NULL,
                               CONSTRAINT titles_genres_id_fk FOREIGN KEY (title_id) REFERENCES titles(id) ON DELETE CASCADE,
                               CONSTRAINT genres_titles_id_fk FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
);

CREATE TABLE authors (
                         id INT DEFAULT NEXTVAL('authors_seq') PRIMARY KEY NOT NULL,
                         name VARCHAR(255)
);

CREATE TABLE roles (
                       id INT DEFAULT NEXTVAL('roles_seq') PRIMARY KEY NOT NULL,
                       role VARCHAR(255)
);

CREATE TABLE titles_authors_roles (
                                      title_id INT NOT NULL,
                                      author_id INT NOT NULL,
                                      role_id INT NOT NULL,
                                      CONSTRAINT titles_titles_authors_roles_id_fk FOREIGN KEY (title_id) REFERENCES titles(id) ON DELETE CASCADE,
                                      CONSTRAINT authors_titles_authors_roles_id_fk FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
                                      CONSTRAINT roles_titles_authors_roles_id_fk FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE issues (
                        id INT DEFAULT NEXTVAL('issues_seq') PRIMARY KEY NOT NULL,
                        title_id INT NOT NULL,
                        name VARCHAR(500) NOT NULL,
                        lookup_key VARCHAR(500) NOT NULL,
                        number float NOT NULL,
                        cover VARCHAR(500) NOT NULL,
                        pages INT NOT NULL,
                        printed_price float NOT NULL,
                        digital_price float NOT NULL,
                        currency VARCHAR(3) NOT NULL,
                        release_date TIMESTAMP NOT NULL,
                        short_review CHARACTER VARYING NOT NULL,
                        event VARCHAR(255),
                        story_arch VARCHAR(255),
                        isbn VARCHAR(17) NOT NULL,
                        edition INT NOT NULL,
                        variant INT NOT NULL,
                        variant_of INT,
                        likes_counter INT DEFAULT 0,
                        dislikes_counter INT DEFAULT 0,
                        CONSTRAINT issues_titles_id_fk FOREIGN KEY (title_id) REFERENCES titles(id) ON DELETE CASCADE
);