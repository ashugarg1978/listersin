DROP TABLE IF EXISTS categories_us;
DROP TABLE IF EXISTS categories_australia;
DROP TABLE IF EXISTS categories_austria;
DROP TABLE IF EXISTS categories_belgium_dutch;
DROP TABLE IF EXISTS categories_belgium_french;
DROP TABLE IF EXISTS categories_canada;
DROP TABLE IF EXISTS categories_canadafrench;
DROP TABLE IF EXISTS categories_france;
DROP TABLE IF EXISTS categories_germany;
DROP TABLE IF EXISTS categories_hongkong;
DROP TABLE IF EXISTS categories_india;
DROP TABLE IF EXISTS categories_ireland;
DROP TABLE IF EXISTS categories_italy;
DROP TABLE IF EXISTS categories_malaysia;
DROP TABLE IF EXISTS categories_netherlands;
DROP TABLE IF EXISTS categories_philippines;
DROP TABLE IF EXISTS categories_poland;
DROP TABLE IF EXISTS categories_singapore;
DROP TABLE IF EXISTS categories_spain;
DROP TABLE IF EXISTS categories_switzerland;
DROP TABLE IF EXISTS categories_uk;
DROP TABLE IF EXISTS categories_ebaymotors;

CREATE TABLE categories_us (
	CategoryID			int primary key,
	CategoryLevel 		int,
	CategoryParentID	int,
	CategoryName		varchar(200),
	LeafCategory		boolean	
);

CREATE TABLE categories_australia		LIKE categories_us;
CREATE TABLE categories_austria 		LIKE categories_us;
CREATE TABLE categories_belgium_dutch	LIKE categories_us;
CREATE TABLE categories_belgium_french	LIKE categories_us;
CREATE TABLE categories_canada			LIKE categories_us;
CREATE TABLE categories_canadafrench	LIKE categories_us;
CREATE TABLE categories_france			LIKE categories_us;
CREATE TABLE categories_germany			LIKE categories_us;
CREATE TABLE categories_hongkong		LIKE categories_us;
CREATE TABLE categories_india			LIKE categories_us;
CREATE TABLE categories_ireland			LIKE categories_us;
CREATE TABLE categories_italy			LIKE categories_us;
CREATE TABLE categories_malaysia		LIKE categories_us;
CREATE TABLE categories_netherlands		LIKE categories_us;
CREATE TABLE categories_philippines		LIKE categories_us;
CREATE TABLE categories_poland			LIKE categories_us;
CREATE TABLE categories_singapore		LIKE categories_us;
CREATE TABLE categories_spain			LIKE categories_us;
CREATE TABLE categories_switzerland		LIKE categories_us;
CREATE TABLE categories_uk				LIKE categories_us;
CREATE TABLE categories_ebaymotors		LIKE categories_us;

rehash;
