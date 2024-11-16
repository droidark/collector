-- DROP TABLES
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS publisher_social_networks;
DROP TABLE IF EXISTS titles;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS titles_genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS titles_authors_roles;

-- CREATE SEQUENCES
CREATE SEQUENCE publishers_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE publisher_social_networks_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE titles_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE genres_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE authors_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE roles_seq START WITH 1 INCREMENT BY 50;

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

-- FILL TABLES

INSERT INTO genres(genre) VALUES ('Fiction'),
                                 ('Non-Fiction'),
                                 ('Fantasy'),
                                 ('Science Fiction'),
                                 ('Mystery'),
                                 ('Thriller'),
                                 ('Horror'),
                                 ('Romance'),
                                 ('Historical Fiction'),
                                 ('Adventure');

INSERT INTO authors(name) VALUES ('Olivia Carter'),
                                 ('Benjamin Hayes'),
                                 ('Sophia Turner'),
                                 ('Jackson Reed'),
                                 ('Isabella Morgan'),
                                 ('Ethan Bennett'),
                                 ('Amelia Brooks'),
                                 ('Mason Cooper'),
                                 ('Charlotte Mitchell'),
                                 ('Liam Parker'),
                                 ('Ava Sullivan'),
                                 ('Noah Griffin');

INSERT INTO roles(role) VALUES ('creator'),
                                 ('writer'),
                                 ('illustrator'),
                                 ('inker'),
                                 ('colorist'),
                                 ('letterer'),
                                 ('cover'),
                                 ('variant'),
                                 ('variant cover');
/*
    +---------------+
    |   TECHWORLD   |
    +---------------+
*/
INSERT INTO publishers (name, lookup_key, information, logo, url) VALUES ('TechWorld', 'techworld', 'Leading tech news publisher', 'https://techworld.com/logo.png', 'https://techworld.com');
-- Social networks for TechWorld
INSERT INTO publisher_social_networks (publisher_id, type, username, url) VALUES ((select max(id) from publishers), 'FACEBOOK', 'techworldFB', 'https://facebook.com/techworld');
INSERT INTO publisher_social_networks (publisher_id, type, username, url) VALUES ((select max(id) from publishers), 'INSTAGRAM', 'techworldIG', 'https://instagram.com/techworld');

-- Titles for TechWorld
INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Future Tech Monthly', 'future_tech_monthly', 'https://techworld.com/covers/future_tech_monthly.png', 'Technology Enthusiasts', 'Digital', 'Monthly', '2024-01-01 00:00:00', 'Active', 'Magazine', 50);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Science Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));
-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Amelia Brooks'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Charlotte Mitchell'), (select id from roles where role = 'cover')),
            ((select max(id) from titles), (select id from authors where name = 'Noah Griffin'), (select id from roles where role = 'writer'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Gadget Weekly', 'gadget_weekly', 'https://techworld.com/covers/gadget_weekly.png', 'Tech Gadget Fans', 'Digital', 'Weekly', '2023-01-01 00:00:00', 'Active', 'Magazine', 200);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Science Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Thriller'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Jackson Reed'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Mason Cooper'), (select id from roles where role = 'illustrator'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'AI Innovations', 'ai_innovations', 'https://techworld.com/covers/ai_innovations.png', 'AI Researchers', 'Digital', 'Quarterly', '2022-06-01 00:00:00', 'Active', 'Journal', 10);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Science Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Thriller'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Benjamin Hayes'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Charlotte Mitchell'), (select id from roles where role = 'cover')),
            ((select max(id) from titles), (select id from authors where name = 'Ethan Bennett'), (select id from roles where role = 'colorist'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Future Trends', 'future_trends', 'https://techworld.com/covers/future_trends.png', 'Trend Enthusiasts', 'Print & Digital', 'Monthly', '2020-01-01 00:00:00', 'Active', 'Magazine', 48);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Science Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Sophia Turner'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Isabella Morgan'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Liam Parker'), (select id from roles where role = 'letterer'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'TechWorld Daily', 'techworld_daily', 'https://techworld.com/covers/techworld_daily.png', 'General Audience', 'Digital', 'Daily', '2021-01-01 00:00:00', 'Active', 'Newsletter', 1000);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Science Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Mason Cooper'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Ava Sullivan'), (select id from roles where role = 'cover')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'inker'));

/*
    +------------------+
    |   FOODIESDAILY   |
    +------------------+
*/
INSERT INTO publishers (name, lookup_key, information, logo, url) VALUES ('FoodiesDaily', 'foodiesdaily', 'Daily food and recipe tips', 'https://foodiesdaily.com/logo.png', 'https://foodiesdaily.com');
-- Social networks for FoodiesDaily
INSERT INTO publisher_social_networks (publisher_id, type, username, url) VALUES ((select max(id) from publishers), 'INSTAGRAM', 'foodiesdailyIG', 'https://instagram.com/foodiesdaily');
INSERT INTO publisher_social_networks (publisher_id, type, username, url) VALUES ((select max(id) from publishers), 'X', 'foodiesdailyX', 'https://x.com/foodiesdaily');

-- Titles for FoodiesDaily
INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Recipe Roundup', 'recipe_roundup', 'https://foodiesdaily.com/covers/recipe_roundup.png', 'Home Cooks', 'Digital', 'Monthly', '2024-01-01 00:00:00', 'Active', 'Magazine', 20);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Romance')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Benjamin Hayes'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Amelia Brooks'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Jackson Reed'), (select id from roles where role = 'illustrator'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Food Trends Quarterly', 'food_trends_quarterly', 'https://foodiesdaily.com/covers/food_trends_quarterly.png', 'Food Enthusiasts', 'Digital & Print', 'Quarterly', '2023-03-01 00:00:00', 'Active', 'Journal', 10);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Romance'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Charlotte Mitchell'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Noah Griffin'), (select id from roles where role = 'cover')),
            ((select max(id) from titles), (select id from authors where name = 'Sophia Turner'), (select id from roles where role = 'letterer'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Baking Basics', 'baking_basics', 'https://foodiesdaily.com/covers/baking_basics.png', 'Bakers', 'Print', 'Monthly', '2021-01-01 00:00:00', 'Active', 'Guide', 36);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Romance'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Ava Sullivan'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Amelia Brooks'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Mason Cooper'), (select id from roles where role = 'colorist'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Culinary Cultures', 'culinary_cultures', 'https://foodiesdaily.com/covers/culinary_cultures.png', 'Food Travelers', 'Digital', 'Monthly', '2022-04-01 00:00:00', 'Active', 'Magazine', 24);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Sophia Turner'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Liam Parker'), (select id from roles where role = 'illustrator')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'writer'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Chef''s Special', 'chefs_special', 'https://foodiesdaily.com/covers/chefs_special.png', 'Professional Chefs', 'Print & Digital', 'Monthly', '2020-06-01 00:00:00', 'Active', 'Magazine', 50);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Romance')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Noah Griffin'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Amelia Brooks'), (select id from roles where role = 'cover')),
            ((select max(id) from titles), (select id from authors where name = 'Ethan Bennett'), (select id from roles where role = 'illustrator'));

/*
    +----------------+
    |   TRAVELISTA   |
    +----------------+
*/
INSERT INTO publishers (name, lookup_key, information, logo, url) VALUES ('Travelista', 'travelista', 'Inspiring travel stories and guides', 'https://travelista.com/logo.png', 'https://travelista.com');
-- Social networks for Travelista
INSERT INTO publisher_social_networks (publisher_id, type, username, url) VALUES ((select max(id) from publishers), 'INSTAGRAM', 'travelistaIG', 'https://instagram.com/travelista');
INSERT INTO publisher_social_networks (publisher_id, type, username, url) VALUES ((select max(id) from publishers), 'FACEBOOK', 'travelistaFB', 'https://facebook.com/travelista');

-- Titles for Travelista
INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Wanderlust Weekly', 'wanderlust_weekly', 'https://travelista.com/covers/wanderlust_weekly.png', 'Travel Enthusiasts', 'Digital', 'Weekly', '2023-01-01 00:00:00', 'Active', 'Magazine', 150);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Romance')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Isabella Morgan'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Jackson Reed'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Benjamin Hayes'), (select id from roles where role = 'variant'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Cultural Escapes', 'cultural_escapes', 'https://travelista.com/covers/cultural_escapes.png', 'Culture Seekers', 'Print & Digital', 'Monthly', '2022-04-01 00:00:00', 'Active', 'Magazine', 24);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Liam Parker'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Charlotte Mitchell'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Ethan Bennett'), (select id from roles where role = 'variant'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Off the Beaten Path', 'off_the_beaten_path', 'https://travelista.com/covers/off_the_beaten_path.png', 'Adventure Travelers', 'Digital', 'Quarterly', '2021-01-01 00:00:00', 'Active', 'Journal', 12);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Mystery')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Ava Sullivan'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'illustrator')),
            ((select max(id) from titles), (select id from authors where name = 'Mason Cooper'), (select id from roles where role = 'writer'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Global Guide', 'global_guide', 'https://travelista.com/covers/global_guide.png', 'Global Travelers', 'Digital', 'Monthly', '2020-06-01 00:00:00', 'Active', 'Guide', 48);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Jackson Reed'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Amelia Brooks'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Benjamin Hayes'), (select id from roles where role = 'cover'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Traveler''s Digest', 'travelers_digest', 'https://travelista.com/covers/travelers_digest.png', 'General Audience', 'Print', 'Monthly', '2019-11-01 00:00:00', 'Active', 'Magazine', 60);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Romance'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Benjamin Hayes'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Mason Cooper'), (select id from roles where role = 'variant cover'));

/*
    +-----------------+
    |   SPORTSARENA   |
    +-----------------+
*/
INSERT INTO publishers (name, lookup_key, information, logo, url) VALUES ('SportsArena', 'sportsarena', 'Latest sports news and updates', 'https://sportsarena.com/logo.png', 'https://sportsarena.com');
-- Social networks for SportsArena
INSERT INTO publisher_social_networks (publisher_id, type, username, url) VALUES ((select max(id) from publishers), 'X', 'sportsarenaX', 'https://x.com/sportsarena');
INSERT INTO publisher_social_networks (publisher_id, type, username, url) VALUES ((select max(id) from publishers), 'TIKTOK', 'sportsarenaTT', 'https://tiktok.com/@sportsarena');

-- Titles for SportsArena
INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Arena Weekly', 'arena_weekly', 'https://sportsarena.com/covers/arena_weekly.png', 'Sports Fans', 'Digital', 'Weekly', '2023-03-01 00:00:00', 'Active', 'Magazine', 80);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Thriller')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Mystery')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

-- Authors and roles for title
INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Sophia Turner'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Noah Griffin'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Ethan Bennett'), (select id from roles where role = 'variant cover'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Pro Sports Quarterly', 'pro_sports_quarterly', 'https://sportsarena.com/covers/pro_sports_quarterly.png', 'Athletes', 'Digital & Print', 'Quarterly', '2022-01-01 00:00:00', 'Active', 'Journal', 10);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Thriller')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Mason Cooper'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Ava Sullivan'), (select id from roles where role = 'cover')),
            ((select max(id) from titles), (select id from authors where name = 'Charlotte Mitchell'), (select id from roles where role = 'inker'));


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Game Day Digest', 'game_day_digest', 'https://sportsarena.com/covers/game_day_digest.png', 'General Audience', 'Print', 'Monthly', '2021-04-01 00:00:00', 'Active', 'Magazine', 24);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Thriller')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Mystery')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Jackson Reed'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Liam Parker'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'illustrator'));


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Scoreboard', 'scoreboard', 'https://sportsarena.com/covers/scoreboard.png', 'Sports Analysts', 'Digital', 'Weekly', '2020-08-01 00:00:00', 'Active', 'Newsletter', 100);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Thriller')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Isabella Morgan'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Amelia Brooks'), (select id from roles where role = 'cover')),
            ((select max(id) from titles), (select id from authors where name = 'Ethan Bennett'), (select id from roles where role = 'letterer'));


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Sports Highlights', 'sports_highlights', 'https://sportsarena.com/covers/sports_highlights.png', 'Sports Enthusiasts', 'Digital', 'Daily', '2021-01-01 00:00:00', 'Active', 'Newsletter', 365);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Mystery')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Noah Griffin'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Mason Cooper'), (select id from roles where role = 'variant cover'));


/*
    +-------------+
    |   ECOLIFE   |
    +-------------+
*/
INSERT INTO publishers (name, lookup_key, information, logo, url) VALUES ('EcoLife', 'ecolife', 'Sustainable living tips and news', 'https://ecolife.com/logo.png', 'https://ecolife.com');
-- Social networks for EcoLife
INSERT INTO publisher_social_networks (publisher_id, type, username, url) VALUES ((select max(id) from publishers), 'FACEBOOK', 'ecolifeFB', 'https://facebook.com/ecolife');
INSERT INTO publisher_social_networks (publisher_id, type, username, url) VALUES ((select max(id) from publishers), 'INSTAGRAM', 'ecolifeIG', 'https://instagram.com/ecolife');

-- Titles for EcoLife
INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Eco Daily', 'eco_daily', 'https://ecolife.com/covers/eco_daily.png', 'Eco-Friendly Audience', 'Digital', 'Daily', '2022-01-01 00:00:00', 'Active', 'Newsletter', 700);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Charlotte Mitchell'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Benjamin Hayes'), (select id from roles where role = 'cover')),
            ((select max(id) from titles), (select id from authors where name = 'Ava Sullivan'), (select id from roles where role = 'illustrator'));


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Green Living Monthly', 'green_living_monthly', 'https://ecolife.com/covers/green_living_monthly.png', 'Environmentalists', 'Print & Digital', 'Monthly', '2023-05-01 00:00:00', 'Active', 'Magazine', 20);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Mason Cooper'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Isabella Morgan'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Sophia Turner'), (select id from roles where role = 'colorist'));


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Eco Guide', 'eco_guide', 'https://ecolife.com/covers/eco_guide.png', 'Sustainability Enthusiasts', 'Digital', 'Quarterly', '2021-01-01 00:00:00', 'Active', 'Guide', 12);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Mystery')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Liam Parker'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Noah Griffin'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Amelia Brooks'), (select id from roles where role = 'cover'));


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Green Innovations', 'green_innovations', 'https://ecolife.com/covers/green_innovations.png', 'Innovators', 'Digital & Print', 'Quarterly', '2022-03-01 00:00:00', 'Active', 'Journal', 8);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Science Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Ethan Bennett'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Benjamin Hayes'), (select id from roles where role = 'illustrator')),
            ((select max(id) from titles), (select id from authors where name = 'Sophia Turner'), (select id from roles where role = 'variant cover'));

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Sustainable Future', 'sustainable_future', 'https://ecolife.com/covers/sustainable_future.png', 'General Audience', 'Print', 'Monthly', '2020-02-01 00:00:00', 'Active', 'Magazine', 40);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Science Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Amelia Brooks'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Jackson Reed'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'cover'));