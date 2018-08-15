create table category (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
create table category_recipes (category_id bigint not null, recipes_id bigint not null, primary key (category_id, recipes_id)) engine=InnoDB
create table ingredient (id bigint not null auto_increment, amount decimal(19,2), description varchar(255), recipe_id bigint, uom_id bigint, primary key (id)) engine=InnoDB
create table notes (id bigint not null auto_increment, note varchar(255), recipe_id bigint, primary key (id)) engine=InnoDB
create table recipe (id bigint not null auto_increment, cook_time integer not null, difficulty integer, directions longtext, image longblob, name varchar(255), prep_time integer not null, servings integer not null, source varchar(255), url varchar(255), notes_id bigint, primary key (id)) engine=InnoDB
create table recipe_categories (recipe_id bigint not null, categories_id bigint not null, primary key (recipe_id, categories_id)) engine=InnoDB
create table unit_of_measure (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
