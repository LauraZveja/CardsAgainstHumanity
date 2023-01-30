CREATE TABLE IF NOT EXISTS "Players" (
  "id" INTEGER PRIMARY KEY,
  "username" varchar(64) NOT NULL,
  "password" varchar(128) NOT NULL,
  "dob" varchar(60),
  "email" varchar(255),
  "registerdate" varchar(60),
  "gender" varchar(255),
  "country" varchar(255),
  "admin" INTEGER default 0 NOT NULL
);

INSERT INTO "Players" ("id", "username", "password", "dob", "email", "registerdate", "gender", "country", "admin") VALUES (0, "Admin", "esesmuadmins", NULL, "admin@cah.lv", NULL, "OTHER", "LATVIA", 1);

CREATE TABLE IF NOT EXISTS "GameLobby" (
  "id" INTEGER PRIMARY KEY,
  "dateandtime" varchar(60),
  "roundcount" INTEGER
);
CREATE TABLE IF NOT EXISTS "GameHistory" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "gamelobbyid" INTEGER,
  "playerid" INTEGER,
  "roundid" INTEGER,
  "score" INTEGER
);