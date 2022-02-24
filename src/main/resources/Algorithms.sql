CREATE TABLE "Algorithms"(

                             "id_PK" UUID NOT NULL,

                             "name" VARCHAR(255) NOT NULL,

                             "accuracy" INTEGER NOT NULL,

                             "learning_time" TIME(0) WITHOUT TIME ZONE NOT NULL,

                             "linear" BOOLEAN NOT NULL,

                             "params" INTEGER NOT NULL,

                             "notes" TEXT NOT NULL,

                             "how_works" TEXT NOT NULL,

                             "how_uses" TEXT NOT NULL

);

ALTER TABLE

    "Algorithms" ADD PRIMARY KEY("id_PK");

CREATE TABLE "Users"(

                        "id_PK" UUID NOT NULL,

                        "login" VARCHAR(255) NOT NULL,

                        "password" VARCHAR(255) NOT NULL,

                        "favourites" UUID NOT NULL,

                        "role" VARCHAR(255) NOT NULL

);

ALTER TABLE

    "Users" ADD PRIMARY KEY("id_PK");

CREATE TABLE "Favourites"(

                             "user_id" UUID NOT NULL,

                             "alorithm_id" UUID NOT NULL

);

ALTER TABLE

    "Favourites" ADD PRIMARY KEY("user_id");

ALTER TABLE

    "Favourites" ADD CONSTRAINT "favourites_alorithm_id_foreign" FOREIGN KEY("alorithm_id") REFERENCES "Algorithms"("id_PK");