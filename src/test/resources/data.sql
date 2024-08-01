-- DROP TABLES
drop table publishers IF exists;
drop table publisher_social_networks IF exists;

-- CREATE TABLES
create sequence publishers_seq start with 1 increment by 50;
create table publishers (
    id int default next value for publishers_seq primary key not null,
    name varchar(100),
    lookup_key varchar(100),
    information varchar(500),
    logo varchar(100),
    url varchar(100)
);

create sequence publisher_social_networks start with 1 increment by 50;
create table publisher_social_networks (
    id int default next value for publisher_social_networks primary key not null,
    id_publisher int not null,
    type varchar(100) not null,
    username varchar(100) not null,
    url varchar(100) not null
);

-- CREATE FOREIGN KEYS
alter table publisher_social_networks
    add constraint publisher_social_networks_publisher_id_fk
        foreign key (id_publisher)
            references publishers(id)
            on delete cascade;

-- FILL TABLES
insert into publishers(name, lookup_key, url, logo, information) values ('Panini México', 'panini-mx', 'https://kodansha.us/', 'default.png', 'Subsidiaria mexicana de Panini');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'X', 'PaniniMangaMx', 'https://twitter.com/PaniniMangaMx');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'FACEBOOK', 'PaniniMangaMx', 'https://www.facebook.com/PaniniMangaMx');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'INSTAGRAM', 'paninimangamx', 'https://www.instagram.com/paninimangamx/');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'TIKTOK', 'paninimangamx', 'https://www.tiktok.com/@paninimangamx');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'X', 'PaniniComicsMx', 'https://twitter.com/PaniniComicsMx');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'FACEBOOK', 'PaniniComicsMx', 'https://www.facebook.com/PaniniComicsMx');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'INSTAGRAM', 'paninicomicsmx', 'https://www.instagram.com/paninicomicsmx/');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'TIKTOK', 'paninicomicsmx', 'https://www.tiktok.com/@paninicomicsmx');

insert into publishers(name, lookup_key, url, logo, information) values ('Kamite', 'kamite', 'https://kamite.com.mx', 'default.png', 'Somos una empresa 100% mexicana integrada por profesionales con una experiencia de más de 20 años en el medio editorial y especialistas en el ramo de los cómics, mangas e historietas.');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'X', 'kamite_manga', 'https://twitter.com/kamite_manga');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'FACEBOOK', 'kamitemanga', 'https://www.facebook.com/kamitemanga');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'INSTAGRAM', 'kamite_manga', 'https://www.instagram.com/kamite_manga/');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'TIKTOK', 'editorialkamiteoficial', 'https://www.tiktok.com/@editorialkamiteoficial');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'X', 'Kamite5', 'https://twitter.com/Kamite5');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'FACEBOOK', 'EditorialKamite', 'https://www.facebook.com/EditorialKamite');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'INSTAGRAM', 'editorial_kamite', 'https://www.instagram.com/editorial_kamite/');

insert into publishers(name, lookup_key, url, logo, information) values ('Distrito Manga MX', 'distrito-manga-mx', 'https://www.penguinlibros.com/mx/211002-distrito-manga', 'default.png', 'Somos el sello especializado en manga de Penguin Libros México.');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'X', 'DistritoMangaMx', 'https://twitter.com/DistritoMangaMx');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'FACEBOOK', 'DistritoMangaMx', 'https://www.facebook.com/DistritoMangaMx');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'INSTAGRAM', 'distritomangamx', 'https://www.instagram.com/distritomangamx/');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'TIKTOK', 'distritomangamx', 'https://www.tiktok.com/@distritomangamx');

insert into publishers(name, lookup_key, url, logo, information) values ('Viz', 'viz', 'https://www.viz.com', 'default.png', 'VIZ Media is leading the way in what’s now, new and next. Reaching one in four millennials and half of all GenZ manga readers, VIZ is at the forefront of America’s Japanese pop-culture phenomenon, which today dominates multiple industries from publishing and animation to film and gaming.');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'X', 'VIZMedia', 'https://twitter.com/VIZMedia');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'FACEBOOK', 'OfficialVIZMedia', 'https://www.facebook.com/OfficialVIZMedia');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'INSTAGRAM', 'vizmedia', 'https://www.instagram.com/vizmedia/');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'TIKTOK', 'vizmedia', 'https://www.tiktok.com/@vizmedia');

insert into publishers(name, lookup_key, url, logo, information) values ('Kodansha', 'kodansha', 'https://kodansha.us/', 'default.png', 'For over a century, Kodansha has relentlessly pursued quality and creativity, which allows us to continuously reimagine what could be. We see the world without restrictions. A place of diverse passions, profound perspectives and limitless potential. Filled with curious minds and unexpected voices. And as we continue to shine a light on them, there is no telling how much further we can go.');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'X', 'KODANSHA_EN', 'https://twitter.com/KODANSHA_EN');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'FACEBOOK', 'KodanshaManga', 'https://www.facebook.com/KodanshaManga');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'INSTAGRAM', 'kodanshamanga', 'https://www.instagram.com/kodanshamanga/');
insert into publisher_social_networks (id_publisher, type, username, url) values ((select max(id) from publishers), 'TIKTOK', 'kodanshaus', 'https://www.tiktok.com/@kodanshaus');