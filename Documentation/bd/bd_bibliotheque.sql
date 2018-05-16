
CREATE SEQUENCE categorie_id_cat_seq;

CREATE TABLE categorie (
                id_cat INTEGER NOT NULL DEFAULT nextval('categorie_id_cat_seq'),
                libelle VARCHAR NOT NULL,
                CONSTRAINT categorie_pk PRIMARY KEY (id_cat)
);


ALTER SEQUENCE categorie_id_cat_seq OWNED BY categorie.id_cat;

CREATE SEQUENCE auteur_id_aut_seq;

CREATE TABLE auteur (
                id_aut INTEGER NOT NULL DEFAULT nextval('auteur_id_aut_seq'),
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                datnaiss DATE NOT NULL,
                CONSTRAINT auteur_pk PRIMARY KEY (id_aut)
);


ALTER SEQUENCE auteur_id_aut_seq OWNED BY auteur.id_aut;

CREATE SEQUENCE ouvrage_id_ouvrage_seq;

CREATE TABLE ouvrage (
                id_ouvrage INTEGER NOT NULL DEFAULT nextval('ouvrage_id_ouvrage_seq'),
                titre VARCHAR NOT NULL,
                resume VARCHAR NOT NULL,
                nbrpage INTEGER NOT NULL,
                date_parution DATE  NULL,
                disponibilite BOOLEAN NOT NULL,
                photocouverture VARCHAR NULL,
                id_cat INTEGER NOT NULL,
                id_aut INTEGER NOT NULL,
				isbn VARCHAR NOT NULL,
                CONSTRAINT ouvrage_pk PRIMARY KEY (id_ouvrage)
);


ALTER SEQUENCE ouvrage_id_ouvrage_seq OWNED BY ouvrage.id_ouvrage;

CREATE SEQUENCE exemplaire_id_exemp_seq;

CREATE TABLE exemplaire (
                id_exemp INTEGER NOT NULL DEFAULT nextval('exemplaire_id_exemp_seq'),
                nbrexemp INTEGER NOT NULL,
                num_rayon INTEGER NOT NULL,
                id_ouvrage INTEGER NOT NULL,
                CONSTRAINT exemplaire_pk PRIMARY KEY (id_exemp)
);


ALTER SEQUENCE exemplaire_id_exemp_seq OWNED BY exemplaire.id_exemp;

CREATE SEQUENCE utilisateur_id_user_seq;

CREATE TABLE utilisateur (
                id_user INTEGER NOT NULL DEFAULT nextval('utilisateur_id_user_seq'),
                nom VARCHAR(250) NOT NULL,
                prenom VARCHAR(250) NOT NULL,
                adress VARCHAR(300) NULL,
                email VARCHAR NOT NULL,
                login VARCHAR NULL,
                password VARCHAR NOT NULL,
                photo VARCHAR NULL,
                CONSTRAINT utilisateur_pk PRIMARY KEY (id_user)
);


ALTER SEQUENCE utilisateur_id_user_seq OWNED BY utilisateur.id_user;

CREATE SEQUENCE pret_id_pret_seq;

CREATE TABLE pret (
                id_pret INTEGER NOT NULL DEFAULT nextval('pret_id_pret_seq'),
                date_sortie DATE NOT NULL,
                date_retour DATE NOT NULL,
                id_exemp INTEGER NOT NULL,
                id_user INTEGER NOT NULL,
				rendu BOOLEAN NOT NULL,
                CONSTRAINT pret_pk PRIMARY KEY (id_pret)
);

ALTER SEQUENCE pret_id_pret_seq OWNED BY pret.id_pret;

ALTER TABLE ouvrage ADD CONSTRAINT categorie_ouvrage_fk
FOREIGN KEY (id_cat)
REFERENCES categorie (id_cat)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ouvrage ADD CONSTRAINT auteur_ouvrage_fk
FOREIGN KEY (id_aut)
REFERENCES auteur (id_aut)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE exemplaire ADD CONSTRAINT ouvrage_exemplaire_fk
FOREIGN KEY (id_ouvrage)
REFERENCES ouvrage (id_ouvrage)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE pret ADD CONSTRAINT exemplaire_pret_fk
FOREIGN KEY (id_exemp)
REFERENCES exemplaire (id_exemp)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE pret ADD CONSTRAINT utilisateur_pret_fk
FOREIGN KEY (id_user)
REFERENCES utilisateur (id_user)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
