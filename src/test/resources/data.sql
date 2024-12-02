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
    ((select max(id) from publishers), 'Future Tech Monthly', 'future-tech-monthly', 'https://techworld.com/covers/future_tech_monthly.png', 'Technology Enthusiasts', 'Digital', 'Monthly', '2024-01-01 00:00:00', 'Active', 'Magazine', 50);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Tech Monthly #1', '1', 1, 'ftm-cover-01.png', 230, 14.99, 9.99, 'USD', '2024-01-20 00:00:00', 'A visionary start to the series.', '979-8-88-879010-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Tech Monthly #2', '2', 2, 'ftm-cover-02.png', 225, 15.49, 10.49, 'USD', '2024-02-20 00:00:00', 'Insights into emerging tech trends.', '979-8-88-879010-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Tech Monthly #3', '3', 3, 'ftm-cover-03.png', 240, 16.49, 11.49, 'USD', '2024-03-20 00:00:00', 'Exploring disruptive innovations.', '979-8-88-879010-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Tech Monthly #4', '4', 4, 'ftm-cover-04.png', 220, 13.99, 9.49, 'USD', '2024-04-20 00:00:00', 'Pioneering advancements in AI.', '979-8-88-879010-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Tech Monthly #5', '5', 5, 'ftm-cover-05.png', 235, 17.99, 12.99, 'USD', '2024-05-20 00:00:00', 'Breakthroughs in renewable energy.', '979-8-88-879010-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Tech Monthly #6', '6', 6, 'ftm-cover-06.png', 215, 14.49, 10.49, 'USD', '2024-06-20 00:00:00', 'A deep dive into space technologies.', '979-8-88-879010-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Tech Monthly #7', '7', 7, 'ftm-cover-07.png', 245, 18.49, 13.49, 'USD', '2024-07-20 00:00:00', 'Exploring the metaverse revolution.', '979-8-88-879010-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Tech Monthly #8', '8', 8, 'ftm-cover-08.png', 200, 12.99, 8.99, 'USD', '2024-08-20 00:00:00', 'Tech reshaping industries.', '979-8-88-879010-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Tech Monthly #9', '9', 9, 'ftm-cover-09.png', 230, 16.99, 11.99, 'USD', '2024-09-20 00:00:00', 'A focus on quantum computing.', '979-8-88-879010-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Tech Monthly #10', '10', 10, 'ftm-cover-10.png', 220, 15.49, 10.99, 'USD', '2024-10-20 00:00:00', 'Future tech that changes the game.', '979-8-88-879011-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Gadget Weekly', 'gadget-weekly', 'https://techworld.com/covers/gadget_weekly.png', 'Tech Gadget Fans', 'Digital', 'Weekly', '2023-01-01 00:00:00', 'Active', 'Magazine', 200);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition,variant)
VALUES((select max(id) from titles), 'Gadget Weekly #1', '1', 1, 'default-cover-01.png', 210, 9.99, 6.99, 'USD', '2024-01-01 00:00:00', 'Random short review','979-8-88-877279-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition,variant, variant_of)
VALUES((select max(id) from titles), 'Gadget Weekly #1 [Variant Cover]', '1', 1, 'default-cover-01-vc.png', 210, 9.99, 6.99, 'USD', '2024-01-01 00:00:00', 'Random short review','979-8-88-877279-9', 1, 1, (select max(id) from issues));

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Gadget Weekly #2', '2', 2, 'default-cover-02.png', 198, 8.99, 5.49, 'USD', '2024-02-01 00:00:00', 'An intriguing follow-up issue.', '979-8-88-877279-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Gadget Weekly #3', '3', 3, 'default-cover-03.png', 205, 10.49, 7.49, 'USD', '2024-03-01 00:00:00', 'A must-read for tech enthusiasts.', '979-8-88-877279-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Gadget Weekly #4', '4', 4, 'default-cover-04.png', 214, 9.99, 6.99, 'USD', '2024-04-01 00:00:00', 'Another gem in the series.', '979-8-88-877279-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Gadget Weekly #5', '5', 5, 'default-cover-05.png', 220, 11.99, 8.49, 'USD', '2024-05-01 00:00:00', 'A thrilling issue packed with surprises.', '979-8-88-877279-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Gadget Weekly #6', '6', 6, 'default-cover-06.png', 190, 7.99, 4.99, 'USD', '2024-06-01 00:00:00', 'Keeps you on the edge of your seat.', '979-8-88-877279-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Gadget Weekly #7', '7', 7, 'default-cover-07.png', 212, 10.99, 7.99, 'USD', '2024-07-01 00:00:00', 'Innovation at its finest.', '979-8-88-877279-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Gadget Weekly #8', '8', 8, 'default-cover-08.png', 202, 9.49, 6.49, 'USD', '2024-08-01 00:00:00', 'A great addition to your collection.', '979-8-88-877279-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Gadget Weekly #9', '9', 9, 'default-cover-09.png', 215, 12.49, 8.99, 'USD', '2024-09-01 00:00:00', 'Pushes boundaries of technology.', '979-8-88-877279-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Gadget Weekly #10', '10', 10, 'default-cover-10.png', 200, 8.49, 5.99, 'USD', '2024-10-01 00:00:00', 'An unforgettable milestone.', '979-8-88-877279-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'AI Innovations', 'ai-innovations', 'https://techworld.com/covers/ai_innovations.png', 'AI Researchers', 'Digital', 'Quarterly', '2022-06-01 00:00:00', 'Active', 'Journal', 10);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'AI Innovations #1', '1', 1, 'ai-cover-01.png', 220, 15.99, 10.99, 'USD', '2024-01-15 00:00:00', 'A groundbreaking start to the series.', '979-8-88-879000-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'AI Innovations #2', '2', 2, 'ai-cover-02.png', 230, 16.49, 11.49, 'USD', '2024-02-15 00:00:00', 'Explores the future of AI.', '979-8-88-879000-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'AI Innovations #3', '3', 3, 'ai-cover-03.png', 210, 14.99, 9.99, 'USD', '2024-03-15 00:00:00', 'Innovative insights into AI applications.', '979-8-88-879000-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'AI Innovations #4', '4', 4, 'ai-cover-04.png', 200, 13.99, 9.49, 'USD', '2024-04-15 00:00:00', 'A deep dive into neural networks.', '979-8-88-879000-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'AI Innovations #5', '5', 5, 'ai-cover-05.png', 240, 17.49, 12.49, 'USD', '2024-05-15 00:00:00', 'Discover the latest breakthroughs.', '979-8-88-879000-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'AI Innovations #6', '6', 6, 'ai-cover-06.png', 225, 16.99, 11.99, 'USD', '2024-06-15 00:00:00', 'AI revolution in robotics.', '979-8-88-879000-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'AI Innovations #7', '7', 7, 'ai-cover-07.png', 235, 17.99, 12.99, 'USD', '2024-07-15 00:00:00', 'Insights into AI ethics and morality.', '979-8-88-879000-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'AI Innovations #8', '8', 8, 'ai-cover-08.png', 215, 15.49, 10.49, 'USD', '2024-08-15 00:00:00', 'A focus on machine learning models.', '979-8-88-879000-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'AI Innovations #9', '9', 9, 'ai-cover-09.png', 218, 16.49, 11.49, 'USD', '2024-09-15 00:00:00', 'Revolutionary AI use cases.', '979-8-88-879000-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'AI Innovations #10', '10', 10, 'ai-cover-10.png', 220, 14.99, 10.99, 'USD', '2024-10-15 00:00:00', 'Exploring AI-driven creativity.', '979-8-88-879001-0', 1, 0);

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Future Trends', 'future-trends', 'https://techworld.com/covers/future_trends.png', 'Trend Enthusiasts', 'Print & Digital', 'Monthly', '2020-01-01 00:00:00', 'Active', 'Magazine', 48);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Trends #1', '1', 1, 'ft-cover-01.png', 210, 14.49, 9.49, 'USD', '2024-01-25 00:00:00', 'An insightful start to future possibilities.', '979-8-88-879020-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Trends #2', '2', 2, 'ft-cover-02.png', 220, 15.99, 10.99, 'USD', '2024-02-25 00:00:00', 'Innovative trends shaping tomorrow.', '979-8-88-879020-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Trends #3', '3', 3, 'ft-cover-03.png', 230, 16.49, 11.49, 'USD', '2024-03-25 00:00:00', 'Breakthroughs in technology and science.', '979-8-88-879020-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Trends #4', '4', 4, 'ft-cover-04.png', 200, 13.99, 9.49, 'USD', '2024-04-25 00:00:00', 'A focus on sustainable innovations.', '979-8-88-879020-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Trends #5', '5', 5, 'ft-cover-05.png', 215, 14.99, 10.49, 'USD', '2024-05-25 00:00:00', 'Exploring the forefront of AI.', '979-8-88-879020-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Trends #6', '6', 6, 'ft-cover-06.png', 225, 15.49, 11.49, 'USD', '2024-06-25 00:00:00', 'Revolutionary trends in space tech.', '979-8-88-879020-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Trends #7', '7', 7, 'ft-cover-07.png', 240, 16.99, 12.99, 'USD', '2024-07-25 00:00:00', 'Future of healthcare and biotech.', '979-8-88-879020-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Trends #8', '8', 8, 'ft-cover-08.png', 205, 13.49, 9.49, 'USD', '2024-08-25 00:00:00', 'Innovations in digital finance.', '979-8-88-879020-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Trends #9', '9', 9, 'ft-cover-09.png', 220, 15.99, 11.49, 'USD', '2024-09-25 00:00:00', 'Exploring next-gen communication.', '979-8-88-879020-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Future Trends #10', '10', 10, 'ft-cover-10.png', 210, 14.49, 10.49, 'USD', '2024-10-25 00:00:00', 'A glimpse into the tech of tomorrow.', '979-8-88-879021-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'TechWorld Daily', 'techworld-daily', 'https://techworld.com/covers/techworld_daily.png', 'General Audience', 'Digital', 'Daily', '2021-01-01 00:00:00', 'Active', 'Newsletter', 1000);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'TechWorld Daily #1', '1', 1, 'twd-cover-01.png', 220, 12.99, 8.99, 'USD', '2024-01-10 00:00:00', 'Breaking news in technology.', '979-8-88-879030-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'TechWorld Daily #2', '2', 2, 'twd-cover-02.png', 210, 13.49, 9.49, 'USD', '2024-02-10 00:00:00', 'Tech highlights of the day.', '979-8-88-879030-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'TechWorld Daily #3', '3', 3, 'twd-cover-03.png', 230, 14.99, 10.49, 'USD', '2024-03-10 00:00:00', 'AI advancements covered in depth.', '979-8-88-879030-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'TechWorld Daily #4', '4', 4, 'twd-cover-04.png', 225, 13.99, 9.99, 'USD', '2024-04-10 00:00:00', 'Exploring sustainable technologies.', '979-8-88-879030-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'TechWorld Daily #5', '5', 5, 'twd-cover-05.png', 215, 14.49, 10.49, 'USD', '2024-05-10 00:00:00', 'A spotlight on quantum computing.', '979-8-88-879030-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'TechWorld Daily #6', '6', 6, 'twd-cover-06.png', 240, 15.99, 11.49, 'USD', '2024-06-10 00:00:00', 'The latest in robotics innovation.', '979-8-88-879030-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'TechWorld Daily #7', '7', 7, 'twd-cover-07.png', 200, 12.49, 8.49, 'USD', '2024-07-10 00:00:00', 'Insights into virtual reality.', '979-8-88-879030-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'TechWorld Daily #8', '8', 8, 'twd-cover-08.png', 235, 16.49, 11.99, 'USD', '2024-08-10 00:00:00', 'Exploring trends in digital security.', '979-8-88-879030-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'TechWorld Daily #9', '9', 9, 'twd-cover-09.png', 215, 14.99, 10.49, 'USD', '2024-09-10 00:00:00', 'Renewable tech breakthroughs.', '979-8-88-879030-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'TechWorld Daily #10', '10', 10, 'twd-cover-10.png', 220, 15.49, 11.49, 'USD', '2024-10-10 00:00:00', 'Cutting-edge trends in tech.', '979-8-88-879031-0', 1, 0);


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
    ((select max(id) from publishers), 'Recipe Roundup', 'recipe-roundup', 'https://foodiesdaily.com/covers/recipe_roundup.png', 'Home Cooks', 'Digital', 'Monthly', '2024-01-01 00:00:00', 'Active', 'Magazine', 20);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Recipe Roundup #1', '1', 1, 'rr-cover-01.png', 120, 7.99, 4.99, 'USD', '2024-01-05 00:00:00', 'A delicious collection of recipes.', '979-8-88-879040-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Recipe Roundup #2', '2', 2, 'rr-cover-02.png', 130, 8.49, 5.49, 'USD', '2024-02-05 00:00:00', 'Perfect dishes for every occasion.', '979-8-88-879040-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Recipe Roundup #3', '3', 3, 'rr-cover-03.png', 115, 6.99, 4.49, 'USD', '2024-03-05 00:00:00', 'A must-have for food enthusiasts.', '979-8-88-879040-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Recipe Roundup #4', '4', 4, 'rr-cover-04.png', 125, 7.49, 4.99, 'USD', '2024-04-05 00:00:00', 'Quick and easy meals to make.', '979-8-88-879040-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Recipe Roundup #5', '5', 5, 'rr-cover-05.png', 140, 9.49, 6.49, 'USD', '2024-05-05 00:00:00', 'Healthy recipes for everyday cooking.', '979-8-88-879040-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Recipe Roundup #6', '6', 6, 'rr-cover-06.png', 135, 8.99, 5.99, 'USD', '2024-06-05 00:00:00', 'Seasonal favorites to enjoy.', '979-8-88-879040-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Recipe Roundup #7', '7', 7, 'rr-cover-07.png', 110, 6.49, 3.99, 'USD', '2024-07-05 00:00:00', 'Exquisite desserts to indulge in.', '979-8-88-879040-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Recipe Roundup #8', '8', 8, 'rr-cover-08.png', 150, 10.49, 7.49, 'USD', '2024-08-05 00:00:00', 'International flavors for the adventurous.', '979-8-88-879040-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Recipe Roundup #9', '9', 9, 'rr-cover-09.png', 130, 8.49, 5.99, 'USD', '2024-09-05 00:00:00', 'Comfort food recipes to savor.', '979-8-88-879040-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Recipe Roundup #10', '10', 10, 'rr-cover-10.png', 120, 7.99, 4.99, 'USD', '2024-10-05 00:00:00', 'A collection of classic favorites.', '979-8-88-879041-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Food Trends Quarterly', 'food-trends-quarterly', 'https://foodiesdaily.com/covers/food_trends_quarterly.png', 'Food Enthusiasts', 'Digital & Print', 'Quarterly', '2023-03-01 00:00:00', 'Active', 'Journal', 10);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Food Trends Quarterly #1', '1', 1, 'ftq-cover-01.png', 160, 10.99, 7.49, 'USD', '2024-01-15 00:00:00', 'Exploring the hottest food trends.', '979-8-88-879050-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Food Trends Quarterly #2', '2', 2, 'ftq-cover-02.png', 165, 11.49, 7.99, 'USD', '2024-04-15 00:00:00', 'A look at global culinary innovations.', '979-8-88-879050-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Food Trends Quarterly #3', '3', 3, 'ftq-cover-03.png', 150, 9.99, 6.99, 'USD', '2024-07-15 00:00:00', 'Plant-based diets reimagined.', '979-8-88-879050-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Food Trends Quarterly #4', '4', 4, 'ftq-cover-04.png', 170, 12.49, 8.49, 'USD', '2024-10-15 00:00:00', 'Seasonal dishes to try this quarter.', '979-8-88-879050-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Food Trends Quarterly #5', '5', 5, 'ftq-cover-05.png', 155, 10.49, 7.49, 'USD', '2025-01-15 00:00:00', 'Exploring the rise of fermented foods.', '979-8-88-879050-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Food Trends Quarterly #6', '6', 6, 'ftq-cover-06.png', 180, 13.49, 9.49, 'USD', '2025-04-15 00:00:00', 'The latest in food sustainability.', '979-8-88-879050-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Food Trends Quarterly #7', '7', 7, 'ftq-cover-07.png', 145, 9.49, 6.49, 'USD', '2025-07-15 00:00:00', 'The rise of alternative proteins.', '979-8-88-879050-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Food Trends Quarterly #8', '8', 8, 'ftq-cover-08.png', 175, 12.99, 8.99, 'USD', '2025-10-15 00:00:00', 'Innovations in food packaging.', '979-8-88-879050-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Food Trends Quarterly #9', '9', 9, 'ftq-cover-09.png', 160, 11.49, 7.99, 'USD', '2026-01-15 00:00:00', 'Top flavors dominating the market.', '979-8-88-879050-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Food Trends Quarterly #10', '10', 10, 'ftq-cover-10.png', 185, 14.49, 9.99, 'USD', '2026-04-15 00:00:00', 'Futuristic dining experiences.', '979-8-88-879051-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Baking Basics', 'baking-basics', 'https://foodiesdaily.com/covers/baking_basics.png', 'Bakers', 'Print', 'Monthly', '2021-01-01 00:00:00', 'Active', 'Guide', 36);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Baking Basics #1', '1', 1, 'bb-cover-01.png', 120, 9.99, 6.49, 'USD', '2024-01-10 00:00:00', 'Master the fundamentals of baking.', '979-8-88-879060-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Baking Basics #2', '2', 2, 'bb-cover-02.png', 115, 8.49, 5.49, 'USD', '2024-02-10 00:00:00', 'Essential tips for perfect bread.', '979-8-88-879060-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Baking Basics #3', '3', 3, 'bb-cover-03.png', 130, 10.49, 6.99, 'USD', '2024-03-10 00:00:00', 'Delicious pies and tarts made easy.', '979-8-88-879060-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Baking Basics #4', '4', 4, 'bb-cover-04.png', 140, 11.99, 7.49, 'USD', '2024-04-10 00:00:00', 'Cookies and brownies for beginners.', '979-8-88-879060-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Baking Basics #5', '5', 5, 'bb-cover-05.png', 125, 9.49, 6.49, 'USD', '2024-05-10 00:00:00', 'Perfect your cakes and cupcakes.', '979-8-88-879060-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Baking Basics #6', '6', 6, 'bb-cover-06.png', 135, 10.99, 7.49, 'USD', '2024-06-10 00:00:00', 'Savory baking ideas to try.', '979-8-88-879060-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Baking Basics #7', '7', 7, 'bb-cover-07.png', 110, 7.49, 4.99, 'USD', '2024-07-10 00:00:00', 'Holiday baking tips and tricks.', '979-8-88-879060-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Baking Basics #8', '8', 8, 'bb-cover-08.png', 150, 12.49, 8.49, 'USD', '2024-08-10 00:00:00', 'Artisan bread baking techniques.', '979-8-88-879060-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Baking Basics #9', '9', 9, 'bb-cover-09.png', 145, 11.49, 7.99, 'USD', '2024-09-10 00:00:00', 'Gluten-free baking for everyone.', '979-8-88-879060-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Baking Basics #10', '10', 10, 'bb-cover-10.png', 120, 9.99, 6.99, 'USD', '2024-10-10 00:00:00', 'Classic baking recipes revisited.', '979-8-88-879061-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Culinary Cultures', 'culinary-cultures', 'https://foodiesdaily.com/covers/culinary_cultures.png', 'Food Travelers', 'Digital', 'Monthly', '2022-04-01 00:00:00', 'Active', 'Magazine', 24);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Culinary Cultures #1', '1', 1, 'cc-cover-01.png', 145, 11.99, 8.49, 'USD', '2024-01-20 00:00:00', 'Exploring the flavors of Asia.', '979-8-88-879070-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Culinary Cultures #2', '2', 2, 'cc-cover-02.png', 160, 12.49, 8.99, 'USD', '2024-02-20 00:00:00', 'Delicious dishes from the Mediterranean.', '979-8-88-879070-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Culinary Cultures #3', '3', 3, 'cc-cover-03.png', 150, 11.49, 7.99, 'USD', '2024-03-20 00:00:00', 'A celebration of African cuisine.', '979-8-88-879070-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Culinary Cultures #4', '4', 4, 'cc-cover-04.png', 170, 13.49, 9.49, 'USD', '2024-04-20 00:00:00', 'Tastes and traditions of South America.', '979-8-88-879070-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Culinary Cultures #5', '5', 5, 'cc-cover-05.png', 155, 12.99, 8.99, 'USD', '2024-05-20 00:00:00', 'An adventure in North American flavors.', '979-8-88-879070-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Culinary Cultures #6', '6', 6, 'cc-cover-06.png', 165, 13.99, 9.99, 'USD', '2024-06-20 00:00:00', 'The art of European baking.', '979-8-88-879070-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Culinary Cultures #7', '7', 7, 'cc-cover-07.png', 140, 10.99, 7.49, 'USD', '2024-07-20 00:00:00', 'Spices and scents of the Middle East.', '979-8-88-879070-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Culinary Cultures #8', '8', 8, 'cc-cover-08.png', 175, 14.49, 10.49, 'USD', '2024-08-20 00:00:00', 'Island cuisine from the Caribbean.', '979-8-88-879070-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Culinary Cultures #9', '9', 9, 'cc-cover-09.png', 160, 12.99, 8.99, 'USD', '2024-09-20 00:00:00', 'A culinary journey through Oceania.', '979-8-88-879070-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Culinary Cultures #10', '10', 10, 'cc-cover-10.png', 180, 14.99, 10.49, 'USD', '2024-10-20 00:00:00', 'Exploring the fusion of global flavors.', '979-8-88-879071-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Chef''s Special', 'chefs-special', 'https://foodiesdaily.com/covers/chefs_special.png', 'Professional Chefs', 'Print & Digital', 'Monthly', '2020-06-01 00:00:00', 'Active', 'Magazine', 50);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Chef''s Special #1', '1', 1, 'cs-cover-01.png', 140, 10.99, 7.99, 'USD', '2024-01-30 00:00:00', 'Signature recipes by top chefs.', '979-8-88-879080-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Chef''s Special #2', '2', 2, 'cs-cover-02.png', 150, 11.49, 8.49, 'USD', '2024-02-28 00:00:00', 'Exclusive gourmet creations.', '979-8-88-879080-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Chef''s Special #3', '3', 3, 'cs-cover-03.png', 135, 9.99, 6.99, 'USD', '2024-03-30 00:00:00', 'Desserts that steal the spotlight.', '979-8-88-879080-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Chef''s Special #4', '4', 4, 'cs-cover-04.png', 160, 12.49, 9.49, 'USD', '2024-04-30 00:00:00', 'Savory dishes with a twist.', '979-8-88-879080-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Chef''s Special #5', '5', 5, 'cs-cover-05.png', 145, 10.99, 7.99, 'USD', '2024-05-30 00:00:00', 'Recipes inspired by world cuisines.', '979-8-88-879080-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Chef''s Special #6', '6', 6, 'cs-cover-06.png', 155, 11.99, 8.99, 'USD', '2024-06-30 00:00:00', 'Seasonal ingredients at their finest.', '979-8-88-879080-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Chef''s Special #7', '7', 7, 'cs-cover-07.png', 130, 9.49, 6.49, 'USD', '2024-07-30 00:00:00', 'Classic recipes reimagined.', '979-8-88-879080-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Chef''s Special #8', '8', 8, 'cs-cover-08.png', 165, 12.99, 9.49, 'USD', '2024-08-30 00:00:00', 'Artful presentation and flavors.', '979-8-88-879080-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Chef''s Special #9', '9', 9, 'cs-cover-09.png', 150, 11.49, 8.49, 'USD', '2024-09-30 00:00:00', 'Creative takes on traditional meals.', '979-8-88-879080-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Chef''s Special #10', '10', 10, 'cs-cover-10.png', 140, 10.99, 7.99, 'USD', '2024-10-30 00:00:00', 'The ultimate chef’s cookbook.', '979-8-88-879081-0', 1, 0);


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
    ((select max(id) from publishers), 'Wanderlust Weekly', 'wanderlust-weekly', 'https://travelista.com/covers/wanderlust_weekly.png', 'Travel Enthusiasts', 'Digital', 'Weekly', '2023-01-01 00:00:00', 'Active', 'Magazine', 150);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Wanderlust Weekly #1', '1', 1, 'ww-cover-01.png', 110, 8.99, 5.99, 'USD', '2024-01-07 00:00:00', 'Exploring hidden gems around the world.', '979-8-88-879090-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Wanderlust Weekly #2', '2', 2, 'ww-cover-02.png', 115, 9.49, 6.49, 'USD', '2024-01-14 00:00:00', 'Top destinations for the adventurous.', '979-8-88-879090-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Wanderlust Weekly #3', '3', 3, 'ww-cover-03.png', 120, 9.99, 6.99, 'USD', '2024-01-21 00:00:00', 'City escapes for the modern traveler.', '979-8-88-879090-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Wanderlust Weekly #4', '4', 4, 'ww-cover-04.png', 125, 10.49, 7.49, 'USD', '2024-01-28 00:00:00', 'A guide to eco-friendly travel.', '979-8-88-879090-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Wanderlust Weekly #5', '5', 5, 'ww-cover-05.png', 130, 10.99, 7.99, 'USD', '2024-02-04 00:00:00', 'Discovering culture through food.', '979-8-88-879090-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Wanderlust Weekly #6', '6', 6, 'ww-cover-06.png', 140, 11.49, 8.49, 'USD', '2024-02-11 00:00:00', 'Adventures in the great outdoors.', '979-8-88-879090-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Wanderlust Weekly #7', '7', 7, 'ww-cover-07.png', 135, 11.99, 8.99, 'USD', '2024-02-18 00:00:00', 'The best beaches to unwind.', '979-8-88-879090-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Wanderlust Weekly #8', '8', 8, 'ww-cover-08.png', 125, 10.49, 7.49, 'USD', '2024-02-25 00:00:00', 'Luxury travel on a budget.', '979-8-88-879090-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Wanderlust Weekly #9', '9', 9, 'ww-cover-09.png', 130, 10.99, 7.99, 'USD', '2024-03-03 00:00:00', 'Unique stays for every traveler.', '979-8-88-879090-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Wanderlust Weekly #10', '10', 10, 'ww-cover-10.png', 120, 9.99, 6.99, 'USD', '2024-03-10 00:00:00', 'Tips for traveling like a local.', '979-8-88-879091-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Cultural Escapes', 'cultural-escapes', 'https://travelista.com/covers/cultural_escapes.png', 'Culture Seekers', 'Print & Digital', 'Monthly', '2022-04-01 00:00:00', 'Active', 'Magazine', 24);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Cultural Escapes #1', '1', 1, 'ce-cover-01.png', 130, 10.99, 7.49, 'USD', '2024-01-15 00:00:00', 'Exploring heritage sites worldwide.', '979-8-88-879100-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Cultural Escapes #2', '2', 2, 'ce-cover-02.png', 125, 9.99, 6.99, 'USD', '2024-01-29 00:00:00', 'Discover the art and history of Europe.', '979-8-88-879100-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Cultural Escapes #3', '3', 3, 'ce-cover-03.png', 140, 11.49, 8.49, 'USD', '2024-02-12 00:00:00', 'Immersive experiences in Asian culture.', '979-8-88-879100-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Cultural Escapes #4', '4', 4, 'ce-cover-04.png', 150, 12.99, 9.49, 'USD', '2024-02-26 00:00:00', 'Festivals and traditions of South America.', '979-8-88-879100-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Cultural Escapes #5', '5', 5, 'ce-cover-05.png', 135, 10.49, 7.99, 'USD', '2024-03-11 00:00:00', 'Indigenous arts and crafts uncovered.', '979-8-88-879100-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Cultural Escapes #6', '6', 6, 'ce-cover-06.png', 145, 11.99, 8.49, 'USD', '2024-03-25 00:00:00', 'A journey through African traditions.', '979-8-88-879100-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Cultural Escapes #7', '7', 7, 'ce-cover-07.png', 130, 10.49, 7.49, 'USD', '2024-04-08 00:00:00', 'Middle Eastern culture and heritage.', '979-8-88-879100-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Cultural Escapes #8', '8', 8, 'ce-cover-08.png', 150, 12.49, 8.99, 'USD', '2024-04-22 00:00:00', 'Traditional music and dance from Oceania.', '979-8-88-879100-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Cultural Escapes #9', '9', 9, 'ce-cover-09.png', 140, 11.49, 8.49, 'USD', '2024-05-06 00:00:00', 'Exploring the food culture of the Caribbean.', '979-8-88-879100-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Cultural Escapes #10', '10', 10, 'ce-cover-10.png', 145, 11.99, 8.99, 'USD', '2024-05-20 00:00:00', 'Modern cultural hubs across the globe.', '979-8-88-879101-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Off the Beaten Path', 'off-the-beaten-path', 'https://travelista.com/covers/off_the_beaten_path.png', 'Adventure Travelers', 'Digital', 'Quarterly', '2021-01-01 00:00:00', 'Active', 'Journal', 12);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Off the Beaten Path #1', '1', 1, 'obp-cover-01.png', 125, 9.99, 6.99, 'USD', '2024-01-10 00:00:00', 'Unveiling hidden destinations worldwide.', '979-8-88-879110-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Off the Beaten Path #2', '2', 2, 'obp-cover-02.png', 130, 10.49, 7.49, 'USD', '2024-01-17 00:00:00', 'Explore secluded islands and coastlines.', '979-8-88-879110-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Off the Beaten Path #3', '3', 3, 'obp-cover-03.png', 120, 8.99, 5.99, 'USD', '2024-01-24 00:00:00', 'Find serenity in remote mountain ranges.', '979-8-88-879110-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Off the Beaten Path #4', '4', 4, 'obp-cover-04.png', 135, 11.49, 8.49, 'USD', '2024-01-31 00:00:00', 'Discover ancient trails and paths.', '979-8-88-879110-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Off the Beaten Path #5', '5', 5, 'obp-cover-05.png', 140, 11.99, 8.99, 'USD', '2024-02-07 00:00:00', 'Hidden cultural treasures of the world.', '979-8-88-879110-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Off the Beaten Path #6', '6', 6, 'obp-cover-06.png', 125, 9.49, 6.49, 'USD', '2024-02-14 00:00:00', 'Untouched wilderness to explore.', '979-8-88-879110-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Off the Beaten Path #7', '7', 7, 'obp-cover-07.png', 145, 12.49, 9.49, 'USD', '2024-02-21 00:00:00', 'Villages frozen in time.', '979-8-88-879110-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Off the Beaten Path #8', '8', 8, 'obp-cover-08.png', 130, 10.99, 7.99, 'USD', '2024-02-28 00:00:00', 'Hidden lakes and serene landscapes.', '979-8-88-879110-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Off the Beaten Path #9', '9', 9, 'obp-cover-09.png', 140, 11.99, 8.99, 'USD', '2024-03-06 00:00:00', 'Unexplored deserts and oases.', '979-8-88-879110-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Off the Beaten Path #10', '10', 10, 'obp-cover-10.png', 125, 9.99, 6.99, 'USD', '2024-03-13 00:00:00', 'Find adventure in the unexpected.', '979-8-88-879111-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Global Guide', 'global-guide', 'https://travelista.com/covers/global_guide.png', 'Global Travelers', 'Digital', 'Monthly', '2020-06-01 00:00:00', 'Active', 'Guide', 48);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Global Guide #1', '1', 1, 'gg-cover-01.png', 150, 11.99, 8.49, 'USD', '2024-01-20 00:00:00', 'Your passport to global adventures.', '979-8-88-879120-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Global Guide #2', '2', 2, 'gg-cover-02.png', 140, 10.99, 7.99, 'USD', '2024-01-27 00:00:00', 'Top destinations for 2024.', '979-8-88-879120-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Global Guide #3', '3', 3, 'gg-cover-03.png', 155, 12.49, 9.49, 'USD', '2024-02-03 00:00:00', 'A deep dive into cultural wonders.', '979-8-88-879120-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Global Guide #4', '4', 4, 'gg-cover-04.png', 145, 11.49, 8.49, 'USD', '2024-02-10 00:00:00', 'Discover hidden gems in every continent.', '979-8-88-879120-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Global Guide #5', '5', 5, 'gg-cover-05.png', 160, 12.99, 9.99, 'USD', '2024-02-17 00:00:00', 'Food, art, and history from around the world.', '979-8-88-879120-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Global Guide #6', '6', 6, 'gg-cover-06.png', 150, 11.99, 8.99, 'USD', '2024-02-24 00:00:00', 'Explore natural wonders across the globe.', '979-8-88-879120-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Global Guide #7', '7', 7, 'gg-cover-07.png', 135, 10.49, 7.49, 'USD', '2024-03-02 00:00:00', 'Luxury travel experiences worth trying.', '979-8-88-879120-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Global Guide #8', '8', 8, 'gg-cover-08.png', 155, 12.49, 9.49, 'USD', '2024-03-09 00:00:00', 'An adventure lover’s guide to the world.', '979-8-88-879120-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Global Guide #9', '9', 9, 'gg-cover-09.png', 140, 11.49, 8.49, 'USD', '2024-03-16 00:00:00', 'Affordable getaways for every traveler.', '979-8-88-879120-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Global Guide #10', '10', 10, 'gg-cover-10.png', 145, 11.99, 8.99, 'USD', '2024-03-23 00:00:00', 'Planning your ultimate global trip.', '979-8-88-879121-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Traveler''s Digest', 'travelers-digest', 'https://travelista.com/covers/travelers_digest.png', 'General Audience', 'Print', 'Monthly', '2019-11-01 00:00:00', 'Active', 'Magazine', 60);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Traveler''s Digest #1', '1', 1, 'td-cover-01.png', 145, 10.99, 7.49, 'USD', '2024-01-10 00:00:00', 'Your guide to exploring the unknown.', '979-8-88-879130-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Traveler''s Digest #2', '2', 2, 'td-cover-02.png', 140, 10.49, 7.49, 'USD', '2024-01-17 00:00:00', 'Discover vibrant cities around the globe.', '979-8-88-879130-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Traveler''s Digest #3', '3', 3, 'td-cover-03.png', 150, 11.49, 8.49, 'USD', '2024-01-24 00:00:00', 'The best itineraries for 2024.', '979-8-88-879130-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Traveler''s Digest #4', '4', 4, 'td-cover-04.png', 135, 10.49, 7.49, 'USD', '2024-01-31 00:00:00', 'Hidden treasures waiting to be explored.', '979-8-88-879130-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Traveler''s Digest #5', '5', 5, 'td-cover-05.png', 155, 11.99, 8.99, 'USD', '2024-02-07 00:00:00', 'Tips for sustainable travel.', '979-8-88-879130-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Traveler''s Digest #6', '6', 6, 'td-cover-06.png', 140, 10.99, 7.49, 'USD', '2024-02-14 00:00:00', 'Unforgettable travel experiences.', '979-8-88-879130-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Traveler''s Digest #7', '7', 7, 'td-cover-07.png', 145, 11.49, 8.49, 'USD', '2024-02-21 00:00:00', 'Where to go for off-season adventures.', '979-8-88-879130-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Traveler''s Digest #8', '8', 8, 'td-cover-08.png', 160, 12.99, 9.49, 'USD', '2024-02-28 00:00:00', 'Explore natural wonders up close.', '979-8-88-879130-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Traveler''s Digest #9', '9', 9, 'td-cover-09.png', 150, 11.49, 8.49, 'USD', '2024-03-06 00:00:00', 'Best hiking trails across the world.', '979-8-88-879130-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Traveler''s Digest #10', '10', 10, 'td-cover-10.png', 145, 11.99, 8.99, 'USD', '2024-03-13 00:00:00', 'Essential travel tips and tricks.', '979-8-88-879131-0', 1, 0);


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
    ((select max(id) from publishers), 'Arena Weekly', 'arena-weekly', 'https://sportsarena.com/covers/arena_weekly.png', 'Sports Fans', 'Digital', 'Weekly', '2023-03-01 00:00:00', 'Active', 'Magazine', 80);

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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Arena Weekly #1', '1', 1, 'aw-cover-01.png', 120, 8.99, 5.99, 'USD', '2024-01-08 00:00:00', 'Weekly highlights from the sports world.', '979-8-88-879150-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Arena Weekly #2', '2', 2, 'aw-cover-02.png', 130, 9.49, 6.49, 'USD', '2024-01-15 00:00:00', 'Top plays and moments of the week.', '979-8-88-879150-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Arena Weekly #3', '3', 3, 'aw-cover-03.png', 125, 8.49, 5.49, 'USD', '2024-01-22 00:00:00', 'Interviews with rising sports stars.', '979-8-88-879150-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Arena Weekly #4', '4', 4, 'aw-cover-04.png', 140, 10.99, 7.49, 'USD', '2024-01-29 00:00:00', 'Analysis of recent game-changing matches.', '979-8-88-879150-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Arena Weekly #5', '5', 5, 'aw-cover-05.png', 135, 10.49, 7.49, 'USD', '2024-02-05 00:00:00', 'Sports stats and predictions for the week.', '979-8-88-879150-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Arena Weekly #6', '6', 6, 'aw-cover-06.png', 150, 11.49, 8.49, 'USD', '2024-02-12 00:00:00', 'A tribute to legendary athletes.', '979-8-88-879150-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Arena Weekly #7', '7', 7, 'aw-cover-07.png', 130, 9.99, 6.99, 'USD', '2024-02-19 00:00:00', 'Updates on upcoming tournaments.', '979-8-88-879150-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Arena Weekly #8', '8', 8, 'aw-cover-08.png', 145, 11.99, 8.49, 'USD', '2024-02-26 00:00:00', 'Game recaps and expert opinions.', '979-8-88-879150-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Arena Weekly #9', '9', 9, 'aw-cover-09.png', 125, 8.99, 5.99, 'USD', '2024-03-04 00:00:00', 'Spotlight on underdog victories.', '979-8-88-879150-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Arena Weekly #10', '10', 10, 'aw-cover-10.png', 140, 10.49, 7.49, 'USD', '2024-03-11 00:00:00', 'A review of the week’s standout players.', '979-8-88-879151-0', 1, 0);

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Pro Sports Quarterly', 'pro-sports-quarterly', 'https://sportsarena.com/covers/pro_sports_quarterly.png', 'Athletes', 'Digital & Print', 'Quarterly', '2022-01-01 00:00:00', 'Active', 'Journal', 10);

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
    ((select max(id) from publishers), 'Game Day Digest', 'game-day-digest', 'https://sportsarena.com/covers/game_day_digest.png', 'General Audience', 'Print', 'Monthly', '2021-04-01 00:00:00', 'Active', 'Magazine', 24);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Thriller')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Mystery')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Jackson Reed'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Liam Parker'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'illustrator'));

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Pro Sports Quarterly #1', '1', 1, 'psq-cover-01.png', 140, 10.99, 7.99, 'USD', '2024-01-15 00:00:00', 'In-depth coverage of professional sports.', '979-8-88-879160-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Pro Sports Quarterly #2', '2', 2, 'psq-cover-02.png', 150, 11.49, 8.49, 'USD', '2024-04-15 00:00:00', 'Highlighting the season’s top performances.', '979-8-88-879160-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Pro Sports Quarterly #3', '3', 3, 'psq-cover-03.png', 135, 9.99, 6.99, 'USD', '2024-07-15 00:00:00', 'Breaking down the latest championships.', '979-8-88-879160-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Pro Sports Quarterly #4', '4', 4, 'psq-cover-04.png', 145, 10.49, 7.49, 'USD', '2024-10-15 00:00:00', 'Profiles on top athletes and teams.', '979-8-88-879160-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Pro Sports Quarterly #5', '5', 5, 'psq-cover-05.png', 155, 11.99, 8.99, 'USD', '2025-01-15 00:00:00', 'Updates on transfers and trades.', '979-8-88-879160-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Pro Sports Quarterly #6', '6', 6, 'psq-cover-06.png', 140, 10.99, 7.99, 'USD', '2025-04-15 00:00:00', 'Analyzing trends across sports leagues.', '979-8-88-879160-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Pro Sports Quarterly #7', '7', 7, 'psq-cover-07.png', 130, 9.99, 6.99, 'USD', '2025-07-15 00:00:00', 'Spotlight on upcoming stars.', '979-8-88-879160-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Pro Sports Quarterly #8', '8', 8, 'psq-cover-08.png', 150, 11.49, 8.49, 'USD', '2025-10-15 00:00:00', 'Predictions for the next season.', '979-8-88-879160-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Pro Sports Quarterly #9', '9', 9, 'psq-cover-09.png', 140, 10.99, 7.99, 'USD', '2026-01-15 00:00:00', 'Coverage of the biggest rivalries.', '979-8-88-879160-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Pro Sports Quarterly #10', '10', 10, 'psq-cover-10.png', 145, 11.99, 8.99, 'USD', '2026-04-15 00:00:00', 'A look back at the season’s highlights.', '979-8-88-879161-0', 1, 0);



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

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Scoreboard #1', '1', 1, 'sb-cover-01.png', 130, 9.99, 6.99, 'USD', '2024-01-10 00:00:00', 'The latest scores and highlights.', '979-8-88-879170-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Scoreboard #2', '2', 2, 'sb-cover-02.png', 125, 9.49, 6.49, 'USD', '2024-01-17 00:00:00', 'Top performers of the week.', '979-8-88-879170-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Scoreboard #3', '3', 3, 'sb-cover-03.png', 140, 10.99, 7.49, 'USD', '2024-01-24 00:00:00', 'Highlights from the biggest games.', '979-8-88-879170-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Scoreboard #4', '4', 4, 'sb-cover-04.png', 135, 10.49, 7.49, 'USD', '2024-01-31 00:00:00', 'Stats and analytics for every match.', '979-8-88-879170-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Scoreboard #5', '5', 5, 'sb-cover-05.png', 145, 11.49, 8.49, 'USD', '2024-02-07 00:00:00', 'Player rankings and standings updates.', '979-8-88-879170-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Scoreboard #6', '6', 6, 'sb-cover-06.png', 150, 11.99, 8.99, 'USD', '2024-02-14 00:00:00', 'A focus on game-winning moments.', '979-8-88-879170-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Scoreboard #7', '7', 7, 'sb-cover-07.png', 125, 9.49, 6.49, 'USD', '2024-02-21 00:00:00', 'Upcoming match previews.', '979-8-88-879170-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Scoreboard #8', '8', 8, 'sb-cover-08.png', 135, 10.49, 7.49, 'USD', '2024-02-28 00:00:00', 'Analysis of season-defining games.', '979-8-88-879170-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Scoreboard #9', '9', 9, 'sb-cover-09.png', 140, 10.99, 7.49, 'USD', '2024-03-06 00:00:00', 'Legends of the game in focus.', '979-8-88-879170-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Scoreboard #10', '10', 10, 'sb-cover-10.png', 145, 11.49, 8.49, 'USD', '2024-03-13 00:00:00', 'A wrap-up of the month’s top plays.', '979-8-88-879171-0', 1, 0);



INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Sports Highlights', 'sports-highlights', 'https://sportsarena.com/covers/sports_highlights.png', 'Sports Enthusiasts', 'Digital', 'Daily', '2021-01-01 00:00:00', 'Active', 'Newsletter', 365);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Mystery')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Noah Griffin'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Mason Cooper'), (select id from roles where role = 'variant cover'));

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sports Highlights #1', '1', 1, 'sh-cover-01.png', 140, 10.99, 7.49, 'USD', '2024-01-10 00:00:00', 'The week’s top sports moments.', '979-8-88-879180-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sports Highlights #2', '2', 2, 'sh-cover-02.png', 135, 10.49, 7.49, 'USD', '2024-01-17 00:00:00', 'Breaking down key plays and strategies.', '979-8-88-879180-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sports Highlights #3', '3', 3, 'sh-cover-03.png', 150, 11.99, 8.49, 'USD', '2024-01-24 00:00:00', 'Top performances across leagues.', '979-8-88-879180-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sports Highlights #4', '4', 4, 'sh-cover-04.png', 145, 10.99, 7.99, 'USD', '2024-01-31 00:00:00', 'Exclusive interviews with star athletes.', '979-8-88-879180-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sports Highlights #5', '5', 5, 'sh-cover-05.png', 135, 10.49, 7.49, 'USD', '2024-02-07 00:00:00', 'Underdog stories from the sports world.', '979-8-88-879180-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sports Highlights #6', '6', 6, 'sh-cover-06.png', 150, 11.99, 8.49, 'USD', '2024-02-14 00:00:00', 'A recap of unforgettable games.', '979-8-88-879180-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sports Highlights #7', '7', 7, 'sh-cover-07.png', 140, 10.99, 7.49, 'USD', '2024-02-21 00:00:00', 'A spotlight on emerging talent.', '979-8-88-879180-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sports Highlights #8', '8', 8, 'sh-cover-08.png', 145, 10.99, 7.99, 'USD', '2024-02-28 00:00:00', 'The impact of recent trades and deals.', '979-8-88-879180-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sports Highlights #9', '9', 9, 'sh-cover-09.png', 135, 10.49, 7.49, 'USD', '2024-03-06 00:00:00', 'Expert analysis of current trends.', '979-8-88-879180-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sports Highlights #10', '10', 10, 'sh-cover-10.png', 150, 11.99, 8.49, 'USD', '2024-03-13 00:00:00', 'A look ahead at upcoming matchups.', '979-8-88-879181-0', 1, 0);



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
    ((select max(id) from publishers), 'Eco Daily', 'eco-daily', 'https://ecolife.com/covers/eco_daily.png', 'Eco-Friendly Audience', 'Digital', 'Daily', '2022-01-01 00:00:00', 'Active', 'Newsletter', 700);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Charlotte Mitchell'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Benjamin Hayes'), (select id from roles where role = 'cover')),
            ((select max(id) from titles), (select id from authors where name = 'Ava Sullivan'), (select id from roles where role = 'illustrator'));

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Daily #1', '1', 1, 'ed-cover-01.png', 120, 8.99, 5.99, 'USD', '2024-01-10 00:00:00', 'Daily news on environmental initiatives.', '979-8-88-879190-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Daily #2', '2', 2, 'ed-cover-02.png', 125, 9.49, 6.49, 'USD', '2024-01-17 00:00:00', 'Innovative green technologies unveiled.', '979-8-88-879190-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Daily #3', '3', 3, 'ed-cover-03.png', 115, 8.49, 5.49, 'USD', '2024-01-24 00:00:00', 'Impactful climate change actions.', '979-8-88-879190-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Daily #4', '4', 4, 'ed-cover-04.png', 130, 9.99, 6.99, 'USD', '2024-01-31 00:00:00', 'Exploring sustainable lifestyles.', '979-8-88-879190-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Daily #5', '5', 5, 'ed-cover-05.png', 135, 10.49, 7.49, 'USD', '2024-02-07 00:00:00', 'Nature conservation success stories.', '979-8-88-879190-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Daily #6', '6', 6, 'ed-cover-06.png', 140, 10.99, 7.99, 'USD', '2024-02-14 00:00:00', 'Renewable energy breakthroughs.', '979-8-88-879190-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Daily #7', '7', 7, 'ed-cover-07.png', 125, 9.49, 6.49, 'USD', '2024-02-21 00:00:00', 'Urban farming and green cities.', '979-8-88-879190-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Daily #8', '8', 8, 'ed-cover-08.png', 135, 10.49, 7.49, 'USD', '2024-02-28 00:00:00', 'Plastic-free movements gaining ground.', '979-8-88-879190-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Daily #9', '9', 9, 'ed-cover-09.png', 140, 10.99, 7.99, 'USD', '2024-03-06 00:00:00', 'Green business innovations.', '979-8-88-879190-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Daily #10', '10', 10, 'ed-cover-10.png', 145, 11.49, 8.49, 'USD', '2024-03-13 00:00:00', 'Global movements for a cleaner planet.', '979-8-88-879191-0', 1, 0);

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Green Living Monthly', 'green-living-monthly', 'https://ecolife.com/covers/green_living_monthly.png', 'Environmentalists', 'Print & Digital', 'Monthly', '2023-05-01 00:00:00', 'Active', 'Magazine', 20);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Mason Cooper'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Isabella Morgan'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Sophia Turner'), (select id from roles where role = 'colorist'));

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Living Monthly #1', '1', 1, 'glm-cover-01.png', 130, 9.99, 6.99, 'USD', '2024-01-15 00:00:00', 'Tips for a sustainable lifestyle.', '979-8-88-879200-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Living Monthly #2', '2', 2, 'glm-cover-02.png', 140, 10.99, 7.49, 'USD', '2024-02-15 00:00:00', 'Eco-friendly home improvements.', '979-8-88-879200-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Living Monthly #3', '3', 3, 'glm-cover-03.png', 125, 9.49, 6.49, 'USD', '2024-03-15 00:00:00', 'Sustainable travel ideas.', '979-8-88-879200-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Living Monthly #4', '4', 4, 'glm-cover-04.png', 135, 10.49, 7.49, 'USD', '2024-04-15 00:00:00', 'Green cooking and plant-based diets.', '979-8-88-879200-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Living Monthly #5', '5', 5, 'glm-cover-05.png', 140, 10.99, 7.99, 'USD', '2024-05-15 00:00:00', 'DIY projects with recycled materials.', '979-8-88-879200-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Living Monthly #6', '6', 6, 'glm-cover-06.png', 130, 9.99, 6.99, 'USD', '2024-06-15 00:00:00', 'The best green products of the year.', '979-8-88-879200-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Living Monthly #7', '7', 7, 'glm-cover-07.png', 145, 11.49, 8.49, 'USD', '2024-07-15 00:00:00', 'Community efforts to go green.', '979-8-88-879200-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Living Monthly #8', '8', 8, 'glm-cover-08.png', 135, 10.49, 7.49, 'USD', '2024-08-15 00:00:00', 'Zero-waste living made simple.', '979-8-88-879200-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Living Monthly #9', '9', 9, 'glm-cover-09.png', 140, 10.99, 7.99, 'USD', '2024-09-15 00:00:00', 'Inspiring stories of green innovators.', '979-8-88-879200-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Living Monthly #10', '10', 10, 'glm-cover-10.png', 150, 11.99, 8.49, 'USD', '2024-10-15 00:00:00', 'Seasonal green living tips.', '979-8-88-879201-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Eco Guide', 'eco-guide', 'https://ecolife.com/covers/eco_guide.png', 'Sustainability Enthusiasts', 'Digital', 'Quarterly', '2021-01-01 00:00:00', 'Active', 'Guide', 12);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Mystery')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Liam Parker'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Noah Griffin'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Amelia Brooks'), (select id from roles where role = 'cover'));

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Guide #1', '1', 1, 'eg-cover-01.png', 140, 10.99, 7.99, 'USD', '2024-01-15 00:00:00', 'Your ultimate guide to sustainable living.', '979-8-88-879210-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Guide #2', '2', 2, 'eg-cover-02.png', 135, 10.49, 7.49, 'USD', '2024-02-15 00:00:00', 'Tips to reduce your carbon footprint.', '979-8-88-879210-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Guide #3', '3', 3, 'eg-cover-03.png', 150, 11.99, 8.49, 'USD', '2024-03-15 00:00:00', 'The benefits of renewable energy.', '979-8-88-879210-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Guide #4', '4', 4, 'eg-cover-04.png', 145, 11.49, 8.49, 'USD', '2024-04-15 00:00:00', 'Zero-waste tips for beginners.', '979-8-88-879210-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Guide #5', '5', 5, 'eg-cover-05.png', 140, 10.99, 7.99, 'USD', '2024-05-15 00:00:00', 'Eco-friendly travel destinations.', '979-8-88-879210-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Guide #6', '6', 6, 'eg-cover-06.png', 135, 10.49, 7.49, 'USD', '2024-06-15 00:00:00', 'Green home ideas for every budget.', '979-8-88-879210-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Guide #7', '7', 7, 'eg-cover-07.png', 150, 11.99, 8.49, 'USD', '2024-07-15 00:00:00', 'Innovations in sustainable fashion.', '979-8-88-879210-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Guide #8', '8', 8, 'eg-cover-08.png', 145, 11.49, 8.49, 'USD', '2024-08-15 00:00:00', 'Community projects making a difference.', '979-8-88-879210-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Guide #9', '9', 9, 'eg-cover-09.png', 140, 10.99, 7.99, 'USD', '2024-09-15 00:00:00', 'How to grow your own food sustainably.', '979-8-88-879210-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Eco Guide #10', '10', 10, 'eg-cover-10.png', 150, 11.99, 8.49, 'USD', '2024-10-15 00:00:00', 'Reducing waste with smarter habits.', '979-8-88-879211-0', 1, 0);

INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Green Innovations', 'green-innovations', 'https://ecolife.com/covers/green_innovations.png', 'Innovators', 'Digital & Print', 'Quarterly', '2022-03-01 00:00:00', 'Active', 'Journal', 8);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Science Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Historical Fiction'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Ethan Bennett'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Benjamin Hayes'), (select id from roles where role = 'illustrator')),
            ((select max(id) from titles), (select id from authors where name = 'Sophia Turner'), (select id from roles where role = 'variant cover'));

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Innovations #1', '1', 1, 'gi-cover-01.png', 140, 10.99, 7.49, 'USD', '2024-01-20 00:00:00', 'Exploring the future of green technology.', '979-8-88-879220-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Innovations #2', '2', 2, 'gi-cover-02.png', 150, 11.49, 8.49, 'USD', '2024-02-20 00:00:00', 'Breakthroughs in renewable energy.', '979-8-88-879220-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Innovations #3', '3', 3, 'gi-cover-03.png', 135, 9.99, 6.99, 'USD', '2024-03-20 00:00:00', 'Innovations in sustainable agriculture.', '979-8-88-879220-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Innovations #4', '4', 4, 'gi-cover-04.png', 145, 10.49, 7.49, 'USD', '2024-04-20 00:00:00', 'Revolutionary ideas for green living.', '979-8-88-879220-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Innovations #5', '5', 5, 'gi-cover-05.png', 140, 10.99, 7.99, 'USD', '2024-05-20 00:00:00', 'Smart technologies for energy efficiency.', '979-8-88-879220-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Innovations #6', '6', 6, 'gi-cover-06.png', 150, 11.49, 8.49, 'USD', '2024-06-20 00:00:00', 'The future of electric transportation.', '979-8-88-879220-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Innovations #7', '7', 7, 'gi-cover-07.png', 130, 9.99, 6.99, 'USD', '2024-07-20 00:00:00', 'Startups leading the green revolution.', '979-8-88-879220-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Innovations #8', '8', 8, 'gi-cover-08.png', 145, 10.99, 7.99, 'USD', '2024-08-20 00:00:00', 'Creative solutions to combat pollution.', '979-8-88-879220-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Innovations #9', '9', 9, 'gi-cover-09.png', 140, 10.99, 7.99, 'USD', '2024-09-20 00:00:00', 'How technology is saving biodiversity.', '979-8-88-879220-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Green Innovations #10', '10', 10, 'gi-cover-10.png', 150, 11.99, 8.49, 'USD', '2024-10-20 00:00:00', 'Next-gen materials for a sustainable world.', '979-8-88-879221-0', 1, 0);


INSERT INTO titles (publisher_id, name, lookup_key, cover, demography, format, frequency, release_date, status, type, total_issues) VALUES
    ((select max(id) from publishers), 'Sustainable Future', 'sustainable-future', 'https://ecolife.com/covers/sustainable_future.png', 'General Audience', 'Print', 'Monthly', '2020-02-01 00:00:00', 'Active', 'Magazine', 40);

-- Genres for title
INSERT INTO titles_genres(title_id, genre_id) VALUES
                                                  ((select max(id) from titles), (select id from genres where genre = 'Non-Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Science Fiction')),
                                                  ((select max(id) from titles), (select id from genres where genre = 'Adventure'));

INSERT INTO titles_authors_roles(title_id, author_id, role_id) VALUES
            ((select max(id) from titles), (select id from authors where name = 'Amelia Brooks'), (select id from roles where role = 'creator')),
            ((select max(id) from titles), (select id from authors where name = 'Jackson Reed'), (select id from roles where role = 'writer')),
            ((select max(id) from titles), (select id from authors where name = 'Olivia Carter'), (select id from roles where role = 'cover'));

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sustainable Future #1', '1', 1, 'sf-cover-01.png', 140, 10.99, 7.49, 'USD', '2024-01-25 00:00:00', 'The roadmap to a greener tomorrow.', '979-8-88-879230-1', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sustainable Future #2', '2', 2, 'sf-cover-02.png', 135, 10.49, 7.49, 'USD', '2024-02-25 00:00:00', 'Innovative policies driving sustainability.', '979-8-88-879230-2', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sustainable Future #3', '3', 3, 'sf-cover-03.png', 150, 11.99, 8.49, 'USD', '2024-03-25 00:00:00', 'The rise of eco-friendly industries.', '979-8-88-879230-3', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sustainable Future #4', '4', 4, 'sf-cover-04.png', 145, 11.49, 8.49, 'USD', '2024-04-25 00:00:00', 'Exploring community-led green projects.', '979-8-88-879230-4', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sustainable Future #5', '5', 5, 'sf-cover-05.png', 140, 10.99, 7.99, 'USD', '2024-05-25 00:00:00', 'Green tech startups to watch.', '979-8-88-879230-5', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sustainable Future #6', '6', 6, 'sf-cover-06.png', 150, 11.49, 8.49, 'USD', '2024-06-25 00:00:00', 'Sustainable design in modern architecture.', '979-8-88-879230-6', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sustainable Future #7', '7', 7, 'sf-cover-07.png', 130, 9.99, 6.99, 'USD', '2024-07-25 00:00:00', 'Energy solutions for a sustainable planet.', '979-8-88-879230-7', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sustainable Future #8', '8', 8, 'sf-cover-08.png', 145, 11.49, 8.49, 'USD', '2024-08-25 00:00:00', 'Innovations in circular economies.', '979-8-88-879230-8', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sustainable Future #9', '9', 9, 'sf-cover-09.png', 140, 10.99, 7.99, 'USD', '2024-09-25 00:00:00', 'Nature-based solutions to climate change.', '979-8-88-879230-9', 1, 0);

INSERT INTO issues(title_id, name, lookup_key, number, cover, pages, printed_price, digital_price, currency, release_date, short_review, isbn, edition, variant)
VALUES((select max(id) from titles), 'Sustainable Future #10', '10', 10, 'sf-cover-10.png', 150, 11.99, 8.49, 'USD', '2024-10-25 00:00:00', 'Educational programs shaping tomorrow.', '979-8-88-879231-0', 1, 0);
